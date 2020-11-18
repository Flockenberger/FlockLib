package at.flockenberger.flocklib.flockres;

import java.io.InputStream;

public interface FlockResourceLoader<V>
{

	public V loadFlockResource(InputStream is);
}
