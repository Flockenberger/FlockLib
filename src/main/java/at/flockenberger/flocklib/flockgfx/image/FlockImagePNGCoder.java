package at.flockenberger.flocklib.flockgfx.image;

import java.io.InputStream;
import java.io.Writer;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import at.flockenberger.flocklib.flockgfx.FlockColor;
import at.flockenberger.flocklib.flockutil.ArrayUtils;
import at.flockenberger.flocklib.flockutil.StreamUtils;

public class FlockImagePNGCoder implements FlockImageCoder
{

	/** black and white image mode. */
	private static final byte BW_MODE = 0;
	/** grey scale image mode. */
	private static final byte GREYSCALE_MODE = 1;
	/** full color image mode. */
	private static final byte COLOR_MODE = 2;

	@Override
	public void encode(FlockImage image, Writer writer)
	{

	}

	@Override
	public FlockImage decode(InputStream stream)
	{
		byte[] id = StreamUtils.read(stream, 12);
		if (!ArrayUtils.compare(id, new byte[] { -119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13 }))
		{ throw (new RuntimeException("Format error")); }

		byte[] ihdr = StreamUtils.read(stream, 4);
		if (!ArrayUtils.compare(ihdr, "IHDR".getBytes()))
		{ throw (new RuntimeException("Format error")); }

		int width = StreamUtils.readInt(stream);
		int height = StreamUtils.readInt(stream);

		FlockImage result = new FlockImage(width, height);

		byte[] head = StreamUtils.read(stream, 5);
		int mode;
		if (ArrayUtils.compare(head, new byte[] { 1, 0, 0, 0, 0 }))
		{
			mode = BW_MODE;
		} else if (ArrayUtils.compare(head, new byte[] { 8, 0, 0, 0, 0 }))
		{
			mode = GREYSCALE_MODE;
		} else if (ArrayUtils.compare(head, new byte[] { 8, 2, 0, 0, 0 }))
		{
			mode = COLOR_MODE;
		} else
		{
			mode = COLOR_MODE;
		}

		StreamUtils.readInt(stream);// !!crc

		int size = StreamUtils.readInt(stream);

		byte[] idat = StreamUtils.read(stream, 4);
		if (!ArrayUtils.compare(idat, "IDAT".getBytes()))
		{ throw (new RuntimeException("Format error")); }
		byte[] data = StreamUtils.read(stream, size);

		Inflater inflater = new Inflater();
		inflater.setInput(data, 0, size);

		int color;

		try
		{
			switch (mode)
			{
			case BW_MODE:
				{
					int bytes = (int) (width / 8);
					if ((width % 8) != 0)
					{
						bytes++;
					}
					byte colorset;
					byte[] row = new byte[bytes];
					for (int y = 0; y < height; y++)
					{
						inflater.inflate(new byte[1]);
						inflater.inflate(row);
						for (int x = 0; x < bytes; x++)
						{
							colorset = row[x];
							for (int sh = 0; sh < 8; sh++)
							{
								if (x * 8 + sh >= width)
								{
									break;
								}
								if ((colorset & 0x80) == 0x80)
								{
									result.setPixel(x * 8 + sh, y, FlockColor.WHITE);
								} else
								{
									result.setPixel(x * 8 + sh, y, FlockColor.BLACK);
								}
								colorset <<= 1;
							}
						}
					}
				}
				break;
			case GREYSCALE_MODE:
				{
					byte[] row = new byte[width];
					for (int y = 0; y < height; y++)
					{
						inflater.inflate(new byte[1]);
						inflater.inflate(row);
						for (int x = 0; x < width; x++)
						{
							color = row[x];
							result.setPixelRGB(x, y, (color << 16) + (color << 8) + color);
						}
					}
				}
				break;
			case COLOR_MODE:
				{
					byte[] row = new byte[width * 3];
					for (int y = 0; y < height; y++)
					{
						inflater.inflate(new byte[1]);
						inflater.inflate(row);
						for (int x = 0; x < width; x++)
						{
							result.setPixelRGB(x, y, ((row[x * 3 + 0] & 0xff) << 16) + ((row[x * 3 + 1] & 0xff) << 8)
									+ ((row[x * 3 + 2] & 0xff)));
						}
					}
				}
			}
		} catch (DataFormatException e)
		{
			throw (new RuntimeException("ZIP error" + e));
		}

		StreamUtils.readInt(stream);// !!crc
		StreamUtils.readInt(stream);// 0

		byte[] iend = StreamUtils.read(stream, 4);
		if (!ArrayUtils.compare(iend, "IEND".getBytes()))
		{ throw (new RuntimeException("Format error")); }

		StreamUtils.readInt(stream);// !!crc
		StreamUtils.closeStream(stream);

		return result;
	}

}
