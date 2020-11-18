package at.flockenberger.flocklib.flockbus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import at.flockenberger.flocklib.flockutil.ObjectUtils;

public class Subscriber
{

	/**
	 * the target object
	 */
	private final Object target;

	/**
	 * the method that is to be invoked
	 */
	private final Method method;

	/**
	 * Constructor.<br>
	 * Creates a new Subscriber for the given method and the target object.<br>
	 * 
	 * @param method the method to be invoked by this subscriber
	 * @param target the object on which to invoke this method on
	 */
	protected Subscriber(Method method, Object target)
	{
		ObjectUtils.isNullThrow(target);
		ObjectUtils.isNullThrow(method);

		this.target = target;
		this.method = method;

	}

	/**
	 * Tries to invoke the method that is associated with this
	 * {@link Subscriber}.<br>
	 * The event {@link Object} <code>event</code> must not be null!<br>
	 * In internal use {@link #method} is guaranteed to not be null!
	 * 
	 * @param event the event to invoke
	 */
	protected void invoke(final Object event)
	{
		ObjectUtils.isNullThrow(event);

		try
		{
			method.invoke(this.target, event);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
	}
}