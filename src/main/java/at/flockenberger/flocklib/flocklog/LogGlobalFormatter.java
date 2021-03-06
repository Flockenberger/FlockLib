package at.flockenberger.flocklib.flocklog;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h1>LogDefaultFormatter</h1><br>
 * The default formatter used by the {@link FlockLogger}.
 * 
 * @author Florian Wagner
 *
 */
public class LogGlobalFormatter extends LogFormatter
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String formatLogEntry(LogEntry entry)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(entry.message);
		sb.append(System.lineSeparator());
		return sb.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getHead()
	{ return "LOG STARTED " + calcDate(System.currentTimeMillis()); }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTail()
	{ return "LOG STOPPED " + calcDate(System.currentTimeMillis()); }

	private String calcDate(long millisecs)
	{
		SimpleDateFormat date_format = new SimpleDateFormat("dd.mm.yyyy HH:mm:ss");
		Date resultdate = new Date(millisecs);
		return date_format.format(resultdate);
	}
}
