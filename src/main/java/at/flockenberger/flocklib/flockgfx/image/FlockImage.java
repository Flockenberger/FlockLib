package at.flockenberger.flocklib.flockgfx.image;

import java.awt.Color;
import java.io.Serializable;
import java.nio.ByteBuffer;

import at.flockenberger.flocklib.flockgfx.FlockColor;
import at.flockenberger.flocklib.flockutil.BufferUtils;
import at.flockenberger.flocklib.flockutil.MathUtils;
import at.flockenberger.flocklib.flockutil.ObjectUtils;

public class FlockImage implements Serializable, Cloneable
{
	private static final long serialVersionUID = -79281404100019240L;

	/**
	 * default width and height when uninitialized
	 */
	private final static int SIZE_UNITIALIZED = -1;

	/**
	 * data length.<br>
	 * Because the image data is stored in bytes of r,g,b,a instead of one int rgba
	 * value this scales our buffer by 4.<br>
	 */
	private final static int IMAGE_DATA_LENGTH = 4;
	/**
	 * the image data.<br>
	 */
	protected ByteBuffer imageData;

	/**
	 * the width of the image
	 */
	protected int width = SIZE_UNITIALIZED;

	/**
	 * the height of the image
	 */
	protected int height = SIZE_UNITIALIZED;

	/**
	 * Instantiates a new {@link FlockImage}. <br>
	 * It is guaranteed that {@link #data} is never null!
	 */
	protected FlockImage()
	{
		// we create an 'empty' ByteBuffer
		this.imageData = BufferUtils.createByteBuffer(0);

	}

	/**
	 * Constructs a new {@link FlockImage}.<br>
	 * Copies the given {@link FlockImage} <code>image</code>. The given image is
	 * not changed! <br>
	 * 
	 * @throws {@link NullPointerException} if the image to copy is null!
	 * 
	 * @param image the image to copy
	 */
	public FlockImage(FlockImage image)
	{
		this(image.width, image.height, BufferUtils.cloneByteBuffer(image.imageData));
	}

	/**
	 * Constructs a new {@link FlockImage}.<br>
	 * Sets the width and height and internally allocates the pixel data.
	 * 
	 * @param width  the width of the image
	 * @param height the height of the image
	 */
	public FlockImage(int width, int height)
	{
		this(width, height, BufferUtils.createByteBuffer(width * height * IMAGE_DATA_LENGTH));
	}

	/**
	 * Constructs a new {@link FlockImage}.<br>
	 * Sets the width and height and internally allocates the pixel data.
	 * 
	 * @throws {@link NullPointerException} if the <code>data</code> array is
	 *                null.<br>
	 * @throws {@link IllegalArgumentException} if the width and height do not match
	 *                the data's capacity.<br>
	 * 
	 * @param width  the width of the image
	 * @param height the height of the image
	 * @param data   the data of the image
	 */
	public FlockImage(int width, int height, byte[] data)
	{
		this(width, height, BufferUtils.fromArray(data));
	}

	/**
	 * Constructs a new {@link FlockImage}.<br>
	 * Sets the width and height and internally allocates the pixel data.<br>
	 * <br>
	 * 
	 * @throws {@link NullPointerException} if the <code>data</code>
	 *                {@link ByteBuffer} is null.<br>
	 * @throws {@link IllegalArgumentException} if the width and height do not match
	 *                the data's capacity.<br>
	 * 
	 * @param width  the width of the image
	 * @param height the height of the image
	 * 
	 * @param data   the pixel data to set
	 */
	public FlockImage(int width, int height, ByteBuffer data)
	{
		ObjectUtils.isNullThrow(data);
		if ((width * height * IMAGE_DATA_LENGTH) != data.capacity())
			throw new IllegalArgumentException("Data size does not match width and height!");

		this.width = width;
		this.height = height;
		this.imageData = data;
	}

	/**
	 * @return the width of this image
	 */
	public int getWidth()
	{ return this.width; }

	/**
	 * @return the height of this image
	 */
	public int getHeight()
	{ return this.height; }

	/**
	 * @return the raw byte buffer containing the image pixels
	 */
	public ByteBuffer getRawData()
	{ return this.imageData; }

	/**
	 * Gets the color data of one pixel at <code>x, y</code> and stores it inside of
	 * <code>dst</code>.<br>
	 * The components are written into the first 4 indices of the destination
	 * array.<br>
	 * If the destination array <code>dst</code> has a length less than 4 an
	 * {@link IllegalArgumentException} will be thrown!
	 * 
	 * @param x   the x coordinate of the pixel to get
	 * @param y   the y coordinate of the pixel to get
	 * @param dst the destination array to store the pixel components to
	 */
	public void getPixelData(int x, int y, byte[] dst)
	{
		getPixelData(x, y, dst, 0);
	}

	/**
	 * Gets the color data of one pixel at <code>x, y</code> and stores it inside of
	 * <code>dst</code>.<br>
	 * The components are written into the first 4 indices of the destination array
	 * after <code>offset</code>.<br>
	 * If the destination array <code>dst</code> has a length less than 4 an
	 * {@link IllegalArgumentException} will be thrown!
	 * 
	 * @param x      the x coordinate of the pixel to get
	 * @param y      the y coordinate of the pixel to get
	 * @param dst    the destination array to store the pixel components to
	 * @param offset starting offset of where to write the components into the array
	 */
	public void getPixelData(int x, int y, byte[] dst, int offset)
	{
		if (dst.length < offset + IMAGE_DATA_LENGTH)
			throw new IllegalArgumentException("Destination array must be at least 4 bytes long!");
		byte[] data = getPixelData(x, y);
		dst[offset + PixelComponent.RED.offset] = data[PixelComponent.RED.offset];
		dst[offset + PixelComponent.GREEN.offset] = data[PixelComponent.GREEN.offset];
		dst[offset + PixelComponent.BLUE.offset] = data[PixelComponent.BLUE.offset];
		dst[offset + PixelComponent.ALPHA.offset] = data[PixelComponent.ALPHA.offset];
	}

	/**
	 * Gets the color data of one pixel at <code>x, y</code> and returns it in a new
	 * byte array.<br>
	 * The components are written into the first 4 indices of the destination
	 * array.<br>
	 * 
	 * @param x the x coordinate of the pixel to get
	 * @param y the y coordinate of the pixel to get
	 * @return an array containing the pixel data for the pixel at x,y
	 */
	public byte[] getPixelData(int x, int y)
	{
		byte[] data = new byte[IMAGE_DATA_LENGTH];
		data[PixelComponent.RED.offset] = getPixelComponent(x, y, PixelComponent.RED);
		data[PixelComponent.GREEN.offset] = getPixelComponent(x, y, PixelComponent.GREEN);
		data[PixelComponent.BLUE.offset] = getPixelComponent(x, y, PixelComponent.BLUE);
		data[PixelComponent.ALPHA.offset] = getPixelComponent(x, y, PixelComponent.ALPHA);
		return data;
	}

	/**
	 * Gets a component of the pixel at <code>x, y</code>.<br>
	 * 
	 * @param x         the x coordinate of the pixel
	 * @param y         the y coordinate of the pixel
	 * @param component the component to get
	 * @return the pixel component data as {@link Byte}
	 */
	public byte getPixelComponent(int x, int y, PixelComponent component)
	{
		return (byte) (this.imageData.get(IMAGE_DATA_LENGTH * index(x, y) + component.offset) & 0xFF);
	}

	/**
	 * Sets the pixel component <code>component</code> at the coordinates
	 * <code>x, y</code> to the value <code>value</code>.<br>
	 * 
	 * @param x         the x coordinate of the pixel
	 * @param y         the y coordinate of the pixel
	 * @param value     the value to set this pixel component to
	 * @param component the component to set
	 */
	public void setPixelComponent(int x, int y, byte value, PixelComponent component)
	{
		this.imageData.put(IMAGE_DATA_LENGTH * index(x, y) + component.offset, value);

	}

	/**
	 * Sets the first four entries of the given byte array <code>data</code> as
	 * pixel components to the given pixel at <code>x, y</code>.<br>
	 * If the data array is less than 4 bytes long an
	 * {@link IllegalArgumentException} will be thrown! <br>
	 * The given input data will be stored as follows:<br>
	 * <br>
	 * <code>
	 * pixel[red] = data[0];<br>
	 * pixel[green] = data[1];<br>
	 * pixel[blue] = data[2];<br>
	 * pixel[alpha] = data[3];<br>
	 * </code>
	 * 
	 * @param x    the x coordinate of the pixel
	 * @param y    the y coordinate of the pixel
	 * @param data the data to set this pixel to
	 */
	public void setPixelData(int x, int y, byte[] data)
	{
		if (data.length < IMAGE_DATA_LENGTH)
			throw new IllegalArgumentException("The input array must be at least 4 bytes long!");

		setPixelData(x, y, data[PixelComponent.RED.offset], data[PixelComponent.GREEN.offset],
				data[PixelComponent.BLUE.offset], data[PixelComponent.ALPHA.offset]);
	}

	/**
	 * Sets the pixel data for the given coordinate <code>x, y</code> to the given
	 * rgba values <code>red, green, blue, alpha</code>.<br>
	 * 
	 * @param x     the x coordinate of the pixel
	 * @param y     the y coordinate of the pixel
	 * @param red   the red component to set
	 * @param green the green component to set
	 * @param blue  the blue component to set
	 * @param alpha the alpha component to set
	 */
	public void setPixelData(int x, int y, byte red, byte green, byte blue, byte alpha)
	{
		setPixelComponent(x, y, red, PixelComponent.RED);
		setPixelComponent(x, y, green, PixelComponent.GREEN);
		setPixelComponent(x, y, blue, PixelComponent.BLUE);
		setPixelComponent(x, y, alpha, PixelComponent.ALPHA);
	}

	/**
	 * Returns the pixel at the given coordinates <code>x, y</code> as a
	 * {@link Color} objects.<br>
	 * 
	 * @param x the x coordinate of the pixel
	 * @param y the y coordinate of the pixel
	 * @return a color representing this pixel
	 */
	public FlockColor getPixel(int x, int y)
	{
		return FlockColor.rgba(getPixelData(x, y));
	}

	/**
	 * Returns the pixel at the given coordinates <code>x, y</code> as a integer
	 * rgba value.<br>
	 * 
	 * @param x the x coordinate of the pixel
	 * @param y the y coordinate of the pixel
	 * @return a color representing this pixel as integer rgba
	 */
	public int getPixelRGBA(int x, int y)
	{
		byte[] data = getPixelData(x, y);
		return (data[3] << 24) | (data[0] << 16) | (data[1] << 8) | data[2];
	}

	/**
	 * Returns the pixel at the given coordinates <code>x, y</code> as a integer rgb
	 * value.<br>
	 * 
	 * @param x the x coordinate of the pixel
	 * @param y the y coordinate of the pixel
	 * @return a color representing this pixel as integer rgb
	 */
	public int getPixelRGB(int x, int y)
	{
		byte[] data = getPixelData(x, y);
		return (data[0] << 16) | (data[1] << 8) | data[2];

	}

	/**
	 * Sets the pixel at the given coordinates <code>x, y</code> to the given
	 * {@link Color} <code>color</code>.<br>
	 * 
	 * @param x     the x coordinate of the pixel
	 * @param y     the y coordinate of the pixel
	 * @param color the color to set this pixel to
	 */
	public void setPixel(int x, int y, FlockColor color)
	{
		setPixelData(x, y, color.toBytearray());
	}

	/**
	 * Sets the pixel at the given coordinates <code>x, y</code> to the given
	 * integer representation of an rgba color <code>rgba</code>.<br>
	 * 
	 * @param x    the x coordinate of the pixel
	 * @param y    the y coordinate of the pixel
	 * @param rgba the integer representation of the color to set
	 */
	public void setPixelRGBA(int x, int y, int rgba)
	{
		byte a = (byte) ((rgba >> 24) & 0xff);
		byte r = (byte) ((rgba >> 16) & 0xff);
		byte g = (byte) ((rgba >> 8) & 0xff);
		byte b = (byte) (rgba & 0xff);
		setPixelData(x, y, r, g, b, a);
	}

	/**
	 * Sets the pixel at the given coordinates <code>x, y</code> to the given
	 * integer representation of an rgb color <code>rgb</code>.<br>
	 * 
	 * @param x   the x coordinate of the pixel
	 * @param y   the y coordinate of the pixel
	 * @param rgb the integer representation of the color to set
	 */
	public void setPixelRGB(int x, int y, int rgb)
	{
		byte r = (byte) ((rgb >> 16) & 0xff);
		byte g = (byte) ((rgb >> 8) & 0xff);
		byte b = (byte) (rgb & 0xff);
		setPixelData(x, y, r, g, b, (byte) 255);
	}

	/**
	 * Returns a sub region of this {@link FlockImage}.<br>
	 * The rectangular bounds are given with <code>x1, y1, width, height</code>.<br>
	 * 
	 * @param x1     the upper left x bound of the image region
	 * @param y1     the upper left y bound of the image region
	 * @param width  the width of the new image region
	 * @param height the height of the new image region
	 * @return a new image with the sub region as its data
	 */
	public FlockImage subImage(int x1, int y1, int width, int height)
	{
		if (!MathUtils.isWithin(x1 + width, getWidth() + 1, 0))
			throw new IllegalArgumentException("x1 and width must together be smaller than original width");
		if (!MathUtils.isWithin(y1 + height, getHeight() + 1, 0))
			throw new IllegalArgumentException("y1 and height must together be smaller than original height");

		int x2 = x1 + width;
		int y2 = y1 + height;
		FlockImage image = new FlockImage(width, height);
		for (int x = x1, xx = 0; x < x2; x++, xx++)
			for (int y = y1, yy = 0; y < y2; y++, yy++)
				image.setPixelRGBA(xx, yy, getPixelRGBA(x, y));
		return image;
	}

	/**
	 * Trims this image to only opaque pixels. Essentially cropping out any
	 * transparent pixels.
	 * 
	 * @return a new cropped Image
	 */
	public FlockImage trimImage()
	{
		int width = getWidth();
		int height = getHeight();
		int top = height / 2;
		int bottom = top;
		int left = width / 2;
		int right = left;

		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)

				if (getPixelRGBA(x, y) != 0)
				{
					top = Math.min(top, y);
					bottom = Math.max(bottom, y);

					left = Math.min(left, x);
					right = Math.max(right, x);
				}

		return subImage(left, top, right - left, bottom - top);
	}

	/**
	 * Resizes the image to the given width <code>newWidth</code> and height
	 * <code>newHeight</code> and stores it inside a new {@link FlockImage}.
	 * 
	 * @param newWidth  the width of the new image
	 * @param newHeight the height of the new image
	 * @return the resized image
	 */
	public FlockImage resize(int newWidth, int newHeight)
	{
		int x_ratio = (int) ((width << 16) / newWidth) + 1;
		int y_ratio = (int) ((height << 16) / newHeight) + 1;
		int x2, y2;

		FlockImage sized = new FlockImage(newWidth, newHeight);

		for (int i = 0; i < newHeight; i++)
		{
			for (int j = 0; j < newWidth; j++)
			{
				x2 = ((j * x_ratio) >> 16);
				y2 = ((i * y_ratio) >> 16);
				sized.setPixelRGBA(j, i, getPixelRGBA(x2, y2));
			}
		}
		return sized;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object clone()
	{
		return new FlockImage(this);
	}

	/**
	 * Returns the index this pixel has in the data array.<br>
	 * If the given <code>x</code> or <code>y</code> values are greater than
	 * {@link #getWidth()} or {@link #getHeight()} respectively they will be set to
	 * {@link #getWidth()} or {@link #getHeight()}.<br>
	 * 
	 * @param x the x pixel
	 * @param y the y pixel
	 * @return the index of this pixel
	 */
	private int index(int x, int y)
	{
		MathUtils.clamp(x, getWidth(), 0);
		MathUtils.clamp(y, getHeight(), 0);
		return y * getWidth() + x;
	}
}
