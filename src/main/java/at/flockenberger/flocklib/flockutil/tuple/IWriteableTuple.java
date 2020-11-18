package at.flockenberger.flocklib.flockutil.tuple;

/**
 * <h1>IWriteableTuple<V, C></h1><br>
 * A tuple interface used by {@link Tuple}.
 * 
 * @author Florian Wagner
 *
 * @param <V> the type for the first (A) parameter
 * @param <C> the type for the second (B) parameter
 */
public interface IWriteableTuple<V, C>
{
	/**
	 * Sets the first (A) value of the tuple.<br>
	 * 
	 * @param a the first value of the tuple to set
	 */
	void setA(V a);

	/**
	 * Sets the second (B) value of the tuple.<br>
	 * 
	 * @param a the second value of the tuple to set
	 */
	void setB(C b);

	void put(V a, C b);
}
