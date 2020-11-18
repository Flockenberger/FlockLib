package at.flockenberger.flocklib.flockani;

/**
 * <h1>AnimateableValue</h1><br>
 * An AnimateableValue allows to animate any given type given with <T> to be
 * animated.<br>
 * One can set the target value and the current value apart from in the
 * constructor to dynamically change the value during animation.<br>
 * The current value can always be retrieved with the {@link #get()} method.
 * 
 * @author Florian Wagner
 * 
 * @param <T> the type of the object to animate
 */
public interface AnimateableValue<T>
{
	/**
	 * @return the current value
	 */
	public T get();

	/**
	 * Sets the target for this animateable value.<br>
	 * 
	 * @param target the target value
	 */
	public void target(T target);

	/**
	 * Sets the "actual", the current, value of this value.
	 * 
	 * @param value the value to set
	 */
	public void value(T value);

	/**
	 * Resets this value to the value given in the constructor.<br>
	 */
	public void reset();

	/**
	 * Update this value. <br>
	 * This should be called on every frame.<br>
	 * 
	 * @param dt the delta value to update. Usually this is the delta time of one
	 *           frame
	 */
	public void update(float dt);
}
