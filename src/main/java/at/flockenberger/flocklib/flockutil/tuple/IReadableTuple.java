package at.flockenberger.flocklib.flockutil.tuple;

/**
 * <h1>IReadableTuple<V, C></h1><br>
 * A tuple interface used by {@link Tuple} and {@link ReadOnlyTuple}.<br>
 * 
 * @author Florian Wagner
 *
 * @param <V> the type for the first (A) parameter
 * @param <C> the type for the second (B) parameter
 */
public interface IReadableTuple<V, C>
{
	/**
	 * @return the first value of the tuple pair
	 */
	V getA();

	/**
	 * @return the second value of the tuple pair
	 */
	C getB();
}
