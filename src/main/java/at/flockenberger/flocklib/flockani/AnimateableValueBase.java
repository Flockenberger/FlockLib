package at.flockenberger.flocklib.flockani;

/**
 * <h1>AnimateableValueBase<T></h1><br>
 * Default implementation and base class for all animateable values.<br>
 * 
 * @author Florian Wagner
 *
 * @param <T> the type of the value to animate
 */
public abstract class AnimateableValueBase<T> implements AnimateableValue<T>
{
	/**
	 * the agility is a extra constant that allows to quicken the animation or slow
	 * it down, depending on the {@link #update(float)} implementation.
	 */
	protected float agility;

	/**
	 * the target value of this animateable value
	 */
	protected T target;

	/**
	 * the actual value
	 */
	protected T actual;

	/**
	 * the initial value
	 */
	protected T initial;

	/**
	 * Constructor.<br>
	 * Initializes only the {@link #agility} of the animateable value.<br>
	 */
	public AnimateableValueBase()
	{
		this.agility = 1f;
	}

	/**
	 * Constructor.<br>
	 * Initializes the current and the target value to the values given.<br>
	 * 
	 * @param init   the initial value
	 * @param target the target value
	 */
	public AnimateableValueBase(T init, T target)
	{
		this(init, target, 1f);
	}

	/**
	 * Constructor.<br>
	 * Initializes the current and the target value to the values given.<br>
	 * This constructor also sets the speed / {@link #agility} of this value.
	 * 
	 * @param init   the initial value
	 * @param target the target value
	 * @param speed  the update speed / agility of this value
	 */
	public AnimateableValueBase(T init, T target, float speed)
	{
		this.actual = this.initial = init;
		this.target = target;
		this.agility = speed;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset()
	{
		this.actual = this.initial;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T get()
	{
		return actual;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void target(T target)
	{
		this.target = target;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void value(T value)
	{
		this.actual = value;
	}
}
