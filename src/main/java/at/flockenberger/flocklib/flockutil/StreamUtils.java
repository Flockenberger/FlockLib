package at.flockenberger.flocklib.flockutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * Stream utilities.
 * 
 * @author Florian Wagner
 *
 */
public final class StreamUtils
{

	/**
	 * Opens an {@link InputStream} from the given {@link Path}
	 * <code>path</code>.<br>
	 * If the given {@link Path} object is null a {@link NullPointerException} will
	 * be thrown. <br>
	 * This method may return null if the File the <code>path</code> points to does
	 * not exist or if the file is not readable.
	 * 
	 * @param path the path which points to the File to open a stream
	 * @return the opened {@link InputStream} or null
	 */
	public static InputStream openStream(Path path)
	{
		ObjectUtils.isNullThrow(path);
		return openStream(path.toFile());
	}

	/**
	 * Opens an {@link InputStream} from the given {@link String}
	 * <code>filePath</code>.<br>
	 * If the given {@link String} object is null a {@link NullPointerException}
	 * will be thrown. <br>
	 * This method may return null if the File the <code>filePath</code> points to
	 * does not exist or if the file is not readable.
	 * 
	 * @param filePath the path which points to the File to open a stream
	 * @return the opened {@link InputStream} or null
	 */
	public static InputStream openStream(String filePath)
	{
		ObjectUtils.isNullThrow(filePath);
		return openStream(new File(filePath));
	}

	/**
	 * Opens an {@link InputStream} from the given {@link File}
	 * <code>file</code>.<br>
	 * If the given {@link File} object is null a {@link NullPointerException} will
	 * be thrown. <br>
	 * This method may return null if the File the <code>filePath</code> points to
	 * does not exist or if the file is not readable.
	 * 
	 * @param file the file to open a {@link InputStream} to
	 * @return the opened {@link InputStream} or null
	 */
	public static InputStream openStream(File file)
	{
		ObjectUtils.isNullThrow(file);

		InputStream is = null;
		try
		{
			if (file.exists())
				if (file.canRead())
					is = new FileInputStream(file);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * Reads one byte of the given {@link InputStream} <code>is</code> . If any
	 * {@link IOException} happens 0 is returned.
	 * 
	 * @param is the {@link InputStream} to read
	 * @return a read byte
	 */
	public static byte read(InputStream is)
	{

		ObjectUtils.isNullThrow(is);
		try
		{
			return (byte) is.read();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Reads <code>count</code> bytes of the given {@link InputStream}
	 * <code>is</code>.
	 * 
	 * @param is the {@link InputStream} to read
	 * @return array of <code>count</code> bytes
	 */
	public static byte[] read(InputStream is, int count)
	{
		ObjectUtils.isNullThrow(is);
		byte[] result = new byte[count];
		for (int i = 0; i < count; i++)
		{
			result[i] = read(is);
		}
		return (result);
	}

	/**
	 * Reads an integer value from the input stream.<br>
	 * s
	 * 
	 * @param is the {@link InputStream} to read from
	 * @return the integer value
	 */
	public static int readInt(InputStream is)
	{
		byte b[] = read(is, 4);
		return (((b[0] & 0xff) << 24) + ((b[1] & 0xff) << 16) + ((b[2] & 0xff) << 8) + ((b[3] & 0xff)));
	}

	/**
	 * Closes the given {@link InputStream} <code>is</code>.<br>
	 * If the stream is null an {@link NullPointerException} will be thrown.
	 * 
	 * @param is the {@link InputStream} to close
	 */
	public static void closeStream(InputStream is)
	{
		ObjectUtils.isNullThrow(is);
		try
		{
			is.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}