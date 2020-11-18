package at.flockenberger.flocklib.flockres;

import java.io.InputStream;

public class FlockResource<V extends Object>
{

	/**
	 * the path from the resource to load
	 */
	protected InputStream resourceStream;

	/**
	 * the loaded data this resource holds
	 */
	protected V data;

	/**
	 * @return the data this resource holds
	 */
	public V getData()
	{ return this.data; }

	@Override
	public String toString()
	{
		return "FlockResource [resourceStream=" + resourceStream + "]";
	}

}
