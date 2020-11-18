package at.flockenberger.flocklib.flocklog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h1>FlockLogger</h1><br>
 * 
 * @author Florian Wagner
 *
 */
public class FlockLogger
{
	/**
	 * the name of this logger
	 */
	private String name;

	/**
	 * all handlers that are assigned to this logger
	 */
	private List<LogHandler> handlers;

	/**
	 * the default console handler
	 */
	private LogConsoleHandler defaultHandler;

	/**
	 * flag for debug output
	 */
	private boolean debug = false;

	/**
	 * flag to suppress warning output
	 */
	private boolean suppressWarning = false;

	protected FlockLogger(String name)
	{
		this.handlers = new ArrayList<LogHandler>();
		this.name = name;
		this.defaultHandler = new LogConsoleHandler();
		this.handlers.add(defaultHandler);
	}

	protected void printHeader()
	{
		info(defaultHandler.getFormatter().getHead());
	}

	protected void printTail()
	{
		info(defaultHandler.getFormatter().getTail());
	}

	/**
	 * Enables the debug output.<br>
	 * Debug messages can be print using any {@link #debug(Object)} methods.
	 */
	public void enableDebugOutput()
	{
		this.debug = true;
	}

	/**
	 * Disables the debug output<br>
	 */
	public void disableDebugOutput()
	{
		this.debug = false;
	}

	/**
	 * Suppresses warning messages.<br>
	 */
	public void suppressWarnings()
	{
		this.suppressWarning = true;
	}

	/**
	 * Enables warning output.<br>
	 */
	public void enableWarnings()
	{
		this.suppressWarning = false;
	}

	/**
	 * Removes the default {@link LogConsoleHandler} from this logger.
	 */
	public void removeDefaultConsoleHandler()
	{
		if (handlers.contains(defaultHandler))
			handlers.remove(defaultHandler);
	}

	/**
	 * Adds a new {@link LogHandler} to the Cardinal Logger.
	 * 
	 * @param hndl the handler to add
	 */
	public void addHandler(LogHandler hndl)
	{
		this.handlers.add(hndl);
	}

	/**
	 * Removes the given {@link LogHandler} from the Cardinal Logger.
	 * 
	 * @param hndl the logger to remove
	 */
	public void removeHandlre(LogHandler hndl)
	{
		this.handlers.remove(hndl);
	}

	/**
	 * Logs an {@link Exception}.
	 * 
	 * @param e the {@link Exception} to log
	 */
	public void except(Exception e)
	{
		error(e);
	}

	/**
	 * Logs an {@link Throwable}.
	 * 
	 * @param t the {@link Throwable} to log
	 */
	public void except(Throwable t)
	{
		error(t);
	}

	/**
	 * Logs a {@link StackTraceElement} array.
	 * 
	 * @param trace the stack trace to log
	 */
	public void trace(StackTraceElement[] trace)
	{
		StackTraceElement[] _trace;
		_trace = Arrays.copyOfRange(trace, 1, trace.length);
		for (StackTraceElement traceElement : _trace)
			error("\tat " + traceElement);
	}

	/**
	 * Logs a given message to the given {@link LogLevel}.
	 * 
	 * @param lvl the {@link LogLevel} to assign this message to
	 * @param msg the message to log
	 */
	public void log(LogLevel lvl, String msg)
	{
		_log(new LogEntry(name, System.currentTimeMillis(), msg, lvl));
	}

	/**
	 * Logs a given message to the given {@link LogLevel}.
	 * 
	 * @param lvl the {@link LogLevel} to assign this message to
	 * @param msg the message to log
	 */
	public void log(LogLevel lvl, Object msg)
	{
		log(lvl, msg.toString());
	}

	/**
	 * Logs a given message to the given {@link LogLevel}.
	 * 
	 * @param lvl the {@link LogLevel} to assign this message to
	 * @param msg the message to log
	 */
	public void log(LogLevel lvl, int msg)
	{
		log(lvl, String.valueOf(msg));
	}

	/**
	 * Logs a given message to the given {@link LogLevel}.
	 * 
	 * @param lvl the {@link LogLevel} to assign this message to
	 * @param msg the message to log
	 */
	public void log(LogLevel lvl, boolean msg)
	{
		log(lvl, String.valueOf(msg));
	}

	/**
	 * Logs a given message to the given {@link LogLevel}.
	 * 
	 * @param lvl the {@link LogLevel} to assign this message to
	 * @param msg the message to log
	 */
	public void log(LogLevel lvl, double msg)
	{
		log(lvl, String.valueOf(msg));
	}

	/**
	 * Logs a given message to the given {@link LogLevel}.
	 * 
	 * @param lvl the {@link LogLevel} to assign this message to
	 * @param msg the message to log
	 */
	public void log(LogLevel lvl, float msg)
	{
		log(lvl, String.valueOf(msg));
	}

	/**
	 * Logs a given message to the given {@link LogLevel}.
	 * 
	 * @param lvl the {@link LogLevel} to assign this message to
	 * @param msg the message to log
	 */
	public void log(LogLevel lvl, long msg)
	{
		log(lvl, String.valueOf(msg));
	}

	/**
	 * Logs a given message to the given {@link LogLevel}.
	 * 
	 * @param lvl the {@link LogLevel} to assign this message to
	 * @param msg the message to log
	 */
	public void log(LogLevel lvl, char msg)
	{
		log(lvl, String.valueOf(msg));
	}

	/**
	 * Logs a given message to the given {@link LogLevel}.
	 * 
	 * @param lvl the {@link LogLevel} to assign this message to
	 * @param msg the message to log
	 */
	public void log(LogLevel lvl, byte msg)
	{
		log(lvl, String.valueOf(msg));
	}

	/**
	 * Logs a message with the {@link LogLevel#INFO} level.
	 * 
	 * @param msg the message to log
	 */
	public void info(String msg)
	{
		log(LogLevel.INFO, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#INFO} level.
	 * 
	 * @param msg the message to log
	 */
	public void info(int msg)
	{
		log(LogLevel.INFO, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#INFO} level.
	 * 
	 * @param msg the message to log
	 */
	public void info(long msg)
	{
		log(LogLevel.INFO, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#INFO} level.
	 * 
	 * @param msg the message to log
	 */
	public void info(float msg)
	{
		log(LogLevel.INFO, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#INFO} level.
	 * 
	 * @param msg the message to log
	 */
	public void info(double msg)
	{
		log(LogLevel.INFO, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#INFO} level.
	 * 
	 * @param msg the message to log
	 */
	public void info(boolean msg)
	{
		log(LogLevel.INFO, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#INFO} level.
	 * 
	 * @param msg the message to log
	 */
	public void info(byte msg)
	{
		log(LogLevel.INFO, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#INFO} level.
	 * 
	 * @param msg the message to log
	 */
	public void info(char msg)
	{
		log(LogLevel.INFO, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#INFO} level.
	 * 
	 * @param msg the message to log
	 */
	public void info(Object msg)
	{
		log(LogLevel.INFO, msg.toString());
	}

	/**
	 * Logs a message with the {@link LogLevel#WARN} level.
	 * 
	 * @param msg the message to log
	 */
	public void warn(String msg)
	{
		if (!suppressWarning)
			log(LogLevel.WARN, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#WARN} level.
	 * 
	 * @param msg the message to log
	 */
	public void warn(int msg)
	{
		if (!suppressWarning)
			log(LogLevel.WARN, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#WARN} level.
	 * 
	 * @param msg the message to log
	 */
	public void warn(long msg)
	{
		if (!suppressWarning)

			log(LogLevel.WARN, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#WARN} level.
	 * 
	 * @param msg the message to log
	 */
	public void warn(float msg)
	{
		if (!suppressWarning)

			log(LogLevel.WARN, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#WARN} level.
	 * 
	 * @param msg the message to log
	 */
	public void warn(double msg)
	{
		if (!suppressWarning)

			log(LogLevel.WARN, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#WARN} level.
	 * 
	 * @param msg the message to log
	 */
	public void warn(boolean msg)
	{
		if (!suppressWarning)

			log(LogLevel.WARN, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#WARN} level.
	 * 
	 * @param msg the message to log
	 */
	public void warn(char msg)
	{
		if (!suppressWarning)

			log(LogLevel.WARN, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#WARN} level.
	 * 
	 * @param msg the message to log
	 */
	public void warn(byte msg)
	{
		if (!suppressWarning)

			log(LogLevel.WARN, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#WARN} level.
	 * 
	 * @param msg the message to log
	 */
	public void warn(Object msg)
	{
		if (!suppressWarning)

			log(LogLevel.WARN, msg.toString());
	}

	/**
	 * Logs a message with the {@link LogLevel#ERROR} level.
	 * 
	 * @param msg the message to log
	 */
	public void error(String msg)
	{
		log(LogLevel.ERROR, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#ERROR} level.
	 * 
	 * @param msg the message to log
	 */
	public void error(Object msg)
	{
		log(LogLevel.ERROR, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#ERROR} level.
	 * 
	 * @param msg the message to log
	 */
	public void error(int msg)
	{
		log(LogLevel.ERROR, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#ERROR} level.
	 * 
	 * @param msg the message to log
	 */
	public void error(double msg)
	{
		log(LogLevel.ERROR, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#ERROR} level.
	 * 
	 * @param msg the message to log
	 */
	public void error(float msg)
	{
		log(LogLevel.ERROR, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#ERROR} level.
	 * 
	 * @param msg the message to log
	 */
	public void error(long msg)
	{
		log(LogLevel.ERROR, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#ERROR} level.
	 * 
	 * @param msg the message to log
	 */
	public void error(boolean msg)
	{
		log(LogLevel.ERROR, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#ERROR} level.
	 * 
	 * @param msg the message to log
	 */
	public void error(char msg)
	{
		log(LogLevel.ERROR, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#ERROR} level.
	 * 
	 * @param msg the message to log
	 */
	public void error(byte msg)
	{
		log(LogLevel.ERROR, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#DEBUG} level.
	 * 
	 * @param msg the message to log
	 */
	public void debug(String msg)
	{
		if (debug)
			log(LogLevel.DEBUG, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#DEBUG} level.
	 * 
	 * @param msg the message to log
	 */
	public void debug(Object msg)
	{
		if (debug)
			log(LogLevel.DEBUG, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#DEBUG} level.
	 * 
	 * @param msg the message to log
	 */
	public void debug(int msg)
	{
		if (debug)
			log(LogLevel.DEBUG, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#DEBUG} level.
	 * 
	 * @param msg the message to log
	 */
	public void debug(long msg)
	{
		if (debug)
			log(LogLevel.DEBUG, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#DEBUG} level.
	 * 
	 * @param msg the message to log
	 */
	public void debug(double msg)
	{
		if (debug)
			log(LogLevel.DEBUG, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#DEBUG} level.
	 * 
	 * @param msg the message to log
	 */
	public void debug(boolean msg)
	{
		if (debug)
			log(LogLevel.DEBUG, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#DEBUG} level.
	 * 
	 * @param msg the message to log
	 */
	public void debug(float msg)
	{
		if (debug)
			log(LogLevel.DEBUG, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#DEBUG} level.
	 * 
	 * @param msg the message to log
	 */
	public void debug(char msg)
	{
		if (debug)
			log(LogLevel.DEBUG, msg);
	}

	/**
	 * Logs a message with the {@link LogLevel#DEBUG} level.
	 * 
	 * @param msg the message to log
	 */
	public void debug(byte msg)
	{
		if (debug)
			log(LogLevel.DEBUG, msg);
	}

	// caller method to all handlers
	private void _log(LogEntry entry)
	{
		for (LogHandler h : handlers)
			h.log(entry);
	}

	/**
	 * Closes all log handlers.<br>
	 */
	public void free()
	{
		for (LogHandler h : handlers)
			h.close();
	}

}
