package at.flockenberger.flocklib.flockbus;

import java.lang.reflect.Method;

/**
 * <h1>EventBase</h1><br>
 * Base class for all events<br>
 * This class holds the time stamp of when this event was created the calling
 * class as well as the calling method.<br>
 * 
 * @author Florian Wagner
 *
 */
public abstract class EventBase
{
	/**
	 * the time stamp of when this event was created.
	 */
	protected long timeStamp;

	/**
	 * the calling class.<br>
	 * This class is the class where the {@link FlockBus#postEvent(EventBase)} was
	 * called from.<br>
	 */
	protected Class<?> callingClass;

	/**
	 * the calling method of the calling class.<br>
	 * This method is the method where the {@link FlockBus#postEvent(EventBase)} was
	 * called from.<br>
	 */
	protected Method callingMethod;

	/**
	 * flag that indicates that this event has been consumed and should not be
	 * reused.
	 */
	protected boolean consumed;

	/**
	 * Constructs a new {@link EventBase} object.<br>
	 * This constructor sets the time stamp, the calling class and method.
	 */
	public EventBase()
	{
		this.timeStamp = System.currentTimeMillis();
	}

	@Override
	public String toString()
	{
		return "EventBase [timeStamp=" + timeStamp + ", callingClass=" + callingClass + ", callingMethod="
				+ callingMethod + "]";
	}

}
