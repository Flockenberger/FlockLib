package at.flockenberger.flocklib.flockres;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import at.flockenberger.flocklib.flockutil.FileUtils;
import at.flockenberger.flocklib.flockutil.ObjectUtils;
import at.flockenberger.flocklib.flockutil.StreamUtils;

/**
 * <h1>FlockRes</h1><br>
 * FlockRes is responsible for loading in any resources that one might have
 * inside a jar file.
 * 
 * @author Florian Wagner
 *
 */
public class FlockRes
{
	/**
	 * resource cache
	 */
	private Map<String, FlockResource<? extends Object>> resourceCache;

	/**
	 * loader cache
	 */
	private Map<Class<?>, FlockResourceLoader<?>> loaderCache;

	/**
	 * the global resource loader
	 */
	private static FlockRes GLOBAL_RESOURCES;

	public static FlockRes get()
	{
		if (ObjectUtils.isNull(GLOBAL_RESOURCES))
			GLOBAL_RESOURCES = new FlockRes();
		return GLOBAL_RESOURCES;
	}

	protected FlockRes()
	{
		this.resourceCache = new HashMap<String, FlockResource<? extends Object>>();
		this.loaderCache = new HashMap<Class<?>, FlockResourceLoader<?>>();
	}

	public void registerLoader(Class<FlockResource<? extends Object>> cls, FlockResourceLoader<? extends Object> loader)
	{
		this.loaderCache.put(cls, loader);
	}

	@SuppressWarnings("unchecked")
	public <V extends Object> FlockResource<V> loadResource(String name, String resource, FlockResourceLoader<V> loader)
	{

		ObjectUtils.isAnyNullThrow(name, resource, loader);
		FlockResource<V> cached = null;
		try
		{
			cached = (FlockResource<V>) getCachedResource(name);
		} catch (Exception e)
		{
			// do nothing
		}
		if (ObjectUtils.isNull(cached))
		{
			InputStream is = FileUtils.openResourceStream(resource);
			cached = new FlockResource<V>();
			cached.resourceStream = is;
			cached.data = loader.loadFlockResource(is);
			this.resourceCache.put(name, cached);
			StreamUtils.closeStream(is);
		}
		return cached;
	}

	@SuppressWarnings("unchecked")
	public <V extends Object> FlockResource<V> loadResource(String name, String resource)
	{

		ObjectUtils.isAnyNullThrow(name, resource);
		FlockResource<V> cached = null;
		try
		{
			cached = (FlockResource<V>) getCachedResource(name);
		} catch (Exception e)
		{
			// do nothing
		}
		if (ObjectUtils.isNull(cached))
		{

			InputStream is = FileUtils.openResourceStream(resource);
			cached = new FlockResource<V>();
			cached.resourceStream = is;
			FlockResourceLoader<?> loader = loaderCache.get(cached.getClass());
			cached.data = (V) loader.loadFlockResource(is);
			this.resourceCache.put(name, cached);
			StreamUtils.closeStream(is);

		}
		return cached;
	}

	@SuppressWarnings("unchecked")
	public <V extends Object> FlockResource<V> getCachedResource(String name)
	{
		ObjectUtils.isNullThrow(name);
		return (FlockResource<V>) resourceCache.get(name);
	}

}
