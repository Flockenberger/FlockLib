package at.flockenberger.flocklib.flockutil.tuple;

public class Tuple<V, C> extends TupleBase<V, C> implements IReadableTuple<V, C>, IWriteableTuple<V, C> {

	public Tuple() {
		super(null, null);
	}

	public Tuple(V _a, C _b) {
		super(_a, _b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V getA() {
		return a;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public C getB() {
		return b;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setA(V a) {
		this.a = a;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setB(C b) {
		this.b = b;
	}

	@Override
	public void put(V a, C b) {
		this.a = a;
		this.b = b;
	}
}
