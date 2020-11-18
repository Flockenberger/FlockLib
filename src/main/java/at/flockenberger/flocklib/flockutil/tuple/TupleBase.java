package at.flockenberger.flocklib.flockutil.tuple;

public abstract class TupleBase<V, C>
{
	/**
	 * the first value of the tuple pair
	 */
	protected V a;

	/**
	 * the second value of the tuple pair
	 */
	protected C b;

	/**
	 * Creates a new TupleBase.<br>
	 * 
	 * @param _a the first value
	 * @param _b the second valueF
	 */
	public TupleBase(V _a, C _b)
	{
		this.a = _a;
		this.b = _b;
	}

}
