package at.flockenberger.flocklib.flockbus;

import java.io.Serializable;

import at.flockenberger.flocklib.flockutil.ObjectUtils;
import at.flockenberger.flocklib.flockutil.ReflectUtils;

/**
 * <h1>FlockBus</h1><br>
 * 
 * @author Florian Wagner
 *
 */
public class FlockBus implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6518485948508085416L;

	/**
	 * global bus
	 */
	private static FlockBus globalBus;

	/**
	 * the name / identifier of the flockbus
	 */
	private String name;

	/**
	 * subscriber registry
	 */
	private SubscriberRegistry subRegistry;

	/**
	 * the caller depth for the calling class and method
	 */
	private final int CALLER_DEPTH = 5;

	/**
	 * This method can be used to retrieve the global {@link FlockBus} instance.<br>
	 * Note: The global {@link FlockBus} is guaranteed to always be the same
	 * 
	 * @return the global {@link FlockBus}
	 */
	public static FlockBus getGlobal()
	{
		if (ObjectUtils.isNull(globalBus))
			globalBus = new FlockBus();
		
		return globalBus;
	}

	/**
	 * Constructor<br>
	 * Creates a new FlockBus instance.<br>
	 */
	private FlockBus()
	{
		this("FlockBus");
	}

	/**
	 * Constructor<br>
	 * Creates a new FlockBus instance.<br>
	 * Additionally sets a name / identifier for this bus.
	 * 
	 * @param name the name / identifier for this bus
	 */
	public FlockBus(String name)
	{
		this.name = ObjectUtils.notNull(name);
		this.subRegistry = new SubscriberRegistry();
	}

	/**
	 * Registers an {@link Object} to the bus.<br>
	 * 
	 * @param <T>    any object that extends {@link Object}
	 * @param object the object to register to this bus
	 */
	public <T extends Object> void register(T object)
	{
		this.subRegistry.register(object);
	}

	/**
	 * Unregisters an {@link Object} to the bus.<br>
	 * 
	 * @param <T>    any object that extends {@link Object}
	 * @param object the object to unregister to this bus
	 */
	public <T extends Object> void unregister(T object)
	{
		this.subRegistry.unregister(object);
	}

	/**
	 * Posts an event to this bus.<br>
	 * Every registered subscriber's method that is annotated with {@link Subscribe}
	 * and has the given event as only parameter will be invoked.
	 * 
	 * @param <T>   any event that extends {@link EventBase}
	 * @param event the event to post
	 */
	public <T extends EventBase> void postEvent(T event)
	{
		ObjectUtils.isNullThrow(event);
		event.callingMethod = ReflectUtils.getCallingMethod(CALLER_DEPTH);
		event.callingClass = ReflectUtils.getCallingClass(CALLER_DEPTH);
		this.subRegistry.postEvent(event);
	}

	/**
	 * @return the name of this bus
	 */
	public String name()
	{
		return this.name;
	}

}
