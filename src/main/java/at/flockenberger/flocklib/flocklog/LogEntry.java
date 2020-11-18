package at.flockenberger.flocklib.flocklog;

import java.io.Serializable;

import at.flockenberger.flocklib.flockutil.ReflectUtils;

/**
 * <h1>LogEntry</h1><br>
 * Every message that is being logged with the {@link FlockLogger} is converted
 * into a {@link LogEntry}. A LogEntry stores the time stamp, message and the
 * initial {@link LogLevel}.
 * 
 * @author Florian Wagner
 *
 */
public class LogEntry implements Serializable
{

	private static final long serialVersionUID = 6188903225366177665L;

	/**
	 * the name of the logger
	 */
	protected String loggerName;

	/**
	 * the time stamp when this entry was created
	 */
	protected long timeStamp;

	/**
	 * the message this entry holds
	 */
	protected String message;

	/**
	 * the logging level of this entry
	 */
	protected LogLevel level;

	/**
	 * the calling class name
	 */
	protected String sourceClassName = "";

	/**
	 * the calling method name
	 */
	protected String sourceMethodName = "";

	/**
	 * the calling line number
	 */
	protected int sourceLineNumber = 0;

	/**
	 * Creates a new LogEntry.
	 * 
	 * @param time the time stamp of the log message
	 * @param msg  the message that was being logged
	 * @param lvl  the {@link LogLevel} for the message
	 */
	public LogEntry(String loggerName, long time, String msg, LogLevel lvl)
	{
		this.timeStamp = time;
		this.message = msg;
		this.level = lvl;
		this.loggerName = loggerName;
		this.sourceClassName = ReflectUtils.getCallingClassName();
		this.sourceMethodName = ReflectUtils.getCallingMethodName();
		this.sourceLineNumber = ReflectUtils.getCallingLineNumber();
	}
}