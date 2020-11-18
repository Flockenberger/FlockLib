package at.flockenberger.flocklib.flocklog;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;

import at.flockenberger.flocklib.flockutil.ObjectUtils;

/**
 * <h1>LogStreamHandler</h1><br>
 * The LogStreamHandler class is responsible for logging into streams, this can
 * be file streams or just {@link PrintStream} etc.
 * 
 * @author Florian Wagner
 *
 */
public class LogStreamHandler extends LogHandler
{
	private OutputStream output;
	private volatile Writer writer;

	public LogStreamHandler()
	{

	}

	public LogStreamHandler(OutputStream ops, LogFormatter logfrom)
	{
		setFormatter(logfrom);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void log(LogEntry entry)
	{
		if (writer == null || entry == null)
		{ return; }
		if (!shouldLog(entry))
		{ return; }
		String msg;
		try
		{
			msg = getFormatter().formatLogEntry(entry);
		} catch (Exception ex)
		{
			return;
		}

		try
		{
			writer.write(msg);
		} catch (Exception ex)
		{
			return;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void close()
	{
		flushAndClose();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void flush()
	{
		if (writer != null)
		{
			try
			{
				writer.flush();
			} catch (Exception ex)
			{
				return;
			}
		}

	}

	protected synchronized void flushAndClose()
	{
		if (writer != null)
		{
			try
			{
				writer.flush();
				writer.close();
			} catch (Exception ex)
			{
			}
			writer = null;
			output = null;
		}
	}

	protected synchronized void setOutputStream(OutputStream out) throws SecurityException
	{
		if (ObjectUtils.isNull(out))
			return;
		flushAndClose();
		output = out;
		writer = new OutputStreamWriter(output);
	}

}
