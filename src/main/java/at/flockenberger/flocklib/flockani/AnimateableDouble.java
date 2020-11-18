package at.flockenberger.flocklib.flockani;

/**
 * <h1>AnimateableDouble</h1><br>
 * 
 * @author Florian Wagner
 *
 */
public class AnimateableDouble extends AnimateableValueBase<Double>
{

	public AnimateableDouble()
	{
		this.target = 1.;
		this.actual = 1.;
		this.agility = 1f;
	}

	public AnimateableDouble(Double initialValue, Double target)
	{
		super(initialValue, target);
	}

	public AnimateableDouble(Double initValue, Double target, float speed)
	{
		super(initValue, target, speed);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(float dt)
	{
		double offset = target - actual;
		double change = offset * dt * agility;
		actual += change;
	}

}
