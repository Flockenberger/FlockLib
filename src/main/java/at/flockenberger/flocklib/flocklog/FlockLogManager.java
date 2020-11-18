package at.flockenberger.flocklib.flocklog;

import java.util.HashMap;
import java.util.Map;

import at.flockenberger.flocklib.flockutil.ReflectUtils;

/**
 * <h1>FlockLogManager</h1><br>
 * The FlockLogManager class is responsible for handling all {@link FlockLogger}
 * objects that might have been created.<br>
 * All {@link FlockLogger}s are cached so there will always be only one logger
 * with the same name!
 * 
 * @author Florian Wagner
 *
 */
public class FlockLogManager
{
	/**
	 * global FlockLogManager instance
	 */
	private static FlockLogManager instance;

	/**
	 * default global logger
	 */
	private static final String GLOBAL = "Global";

	/**
	 * cache for logging objects.
	 */
	private Map<String, FlockLogger> logCache;

	/**
	 * the depth of the calling class
	 */
	private static final int CALLER_DEPTH = 4;

	static
	{
		if (instance == null)
			instance = new FlockLogManager();

		// add global logger
		FlockLogger fl = new FlockLogger(GLOBAL);
		LogConsoleHandler handler = new LogConsoleHandler();

		handler.setFormatter(new LogGlobalFormatter());
		fl.removeDefaultConsoleHandler();
		fl.addHandler(handler);
		instance.logCache.put(GLOBAL, fl);
		fl.printHeader();
		
	}

	private FlockLogManager()
	{
		logCache = new HashMap<String, FlockLogger>();
	}

	/**
	 * @return the global logger
	 */
	public static FlockLogger getGlobalLogger()
	{ return getLogger(GLOBAL); }

	/**
	 * This method gets a {@link FlockLogger} that corresponds to the calling class
	 * name.<br>
	 * All loggers are cached, so calling this method multiple times inside the same
	 * class does not create multiple instances of different {@link FlockLogger}s
	 * with the same name! <br>
	 * This method is guaranteed to never return null!<br>
	 * 
	 * @return a {@link FlockLogger} object with the name of the calling class
	 */
	public static FlockLogger getLogger()
	{ return getLogger(ReflectUtils.getCallingClassName(CALLER_DEPTH)); }

	/**
	 * 
	 * All loggers are cached, so calling this method multiple times with the same
	 * name does not create multiple instances of different
	 * {@link FlockLogger}s!<br>
	 * 
	 * This method is guaranteed to never return null!<br>
	 * 
	 * @param name the name of the logger to get
	 * @return a new or cached instance of {@link FlockLogger} with the given name.
	 */
	public static FlockLogger getLogger(String name)
	{
		FlockLogger logger = null;
		if (instance.logCache.containsKey(name))
			logger = instance.logCache.get(name);
		else
		{
			FlockLogger fl = new FlockLogger(name);
			instance.logCache.put(name, fl);
			logger = fl;
		}

		return logger;
	}

	/**
	 * Closes and frees all the loggers that are currently allocated by the
	 * LogManager.<br>
	 */
	public static void close()
	{
		getLogger(GLOBAL).printTail();
		for (FlockLogger fl : instance.logCache.values())
			fl.free();
	}
}
