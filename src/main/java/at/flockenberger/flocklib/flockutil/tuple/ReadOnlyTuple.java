package at.flockenberger.flocklib.flockutil.tuple;

/**
 * <h1>ReadOnlyTuple<V, C></h1><br>
 * An read-only tuple pair. <br>
 * As the name suggests the values are only initially set with the constructor
 * and then read-only.
 * 
 * @author Florian Wagner
 *
 * @param <V> the type for the first parameter
 * @param <C> the type for the second parameter
 */
public class ReadOnlyTuple<V, C> extends TupleBase<V, C> implements IReadableTuple<V, C>
{
	/**
	 * Creates a new Read-Only Tuple.
	 * 
	 * @param _a the first value
	 * @param _b the second value
	 */
	public ReadOnlyTuple(V _a, C _b)
	{
		super(_a, _b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V getA()
	{ return a; }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public C getB()
	{ return b; }

}
