package at.flockenberger.flocklib.flockplug;

import static at.flockenberger.flocklib.flockutil.Defines.FALSE;
import static at.flockenberger.flocklib.flockutil.Defines.NULL;
import static at.flockenberger.flocklib.flockutil.Defines.TRUE;

import java.io.File;
import java.io.IOException;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import at.flockenberger.flocklib.flockbus.FlockBus;
import at.flockenberger.flocklib.flockplug.event.PluginLoadFailEvent;
import at.flockenberger.flocklib.flockplug.event.PluginLoadSuccessEvent;
import at.flockenberger.flocklib.flockplug.event.PluginUnloadFailEvent;
import at.flockenberger.flocklib.flockplug.event.PluginUnloadSuccessEvent;
import at.flockenberger.flocklib.flockutil.FileUtils;
import at.flockenberger.flocklib.flockutil.ObjectUtils;
import at.flockenberger.flocklib.flockutil.tuple.Tuple;

public class FlockPluginManager
{
	/**
	 * the bus associated with this manager
	 */
	private FlockBus flockBus;

	/**
	 * the plugin manager only has one public instance
	 */
	private static FlockPluginManager instance;

	/**
	 * the plugin mapping list.<br>
	 * Since we work with generics / types we do not know we use a map to corretly
	 * map the types of plugins.<br>
	 * Map<PluginID, Map<Plugin, LoadedFlag>>
	 */
	private Map<Long, Tuple<IFlockPlugin, Boolean>> plugins;

	/**
	 * Use this method to get the global {@link FlockPluginManager}.<br>
	 * 
	 * @return the global {@link FlockPluginManager}
	 */
	public static FlockPluginManager get()
	{
		if (ObjectUtils.isNull(instance))
			instance = new FlockPluginManager();

		return instance;
	}

	private FlockPluginManager()
	{
		this.flockBus = FlockBus.getGlobal();
		this.plugins = new HashMap<Long, Tuple<IFlockPlugin, Boolean>>();
	}

	/**
	 * Sets the {@link FlockBus} where load and unload events are being send.
	 * 
	 * @param bus the new bus to set
	 */
	public void setEventBus(FlockBus bus)
	{
		ObjectUtils.isNullThrow(bus, "FlockBus must not be null!");
		this.flockBus = bus;
	}

	/**
	 * Can be used to retrieved the state of the plugin.
	 * 
	 * @param plugin the plugin to check the state.<br>
	 *               Must not be null.
	 * @return the current state of the plugin
	 */
	public PluginState getPluginState(IFlockPlugin plugin)
	{
		ObjectUtils.isNullThrow(plugin, "Plugin to check the state from must not be null!");
		return getPluginState(plugin.getPluginID());
	}

	/**
	 * Can be used to retrieved the state of the plugin.
	 * 
	 * @param id the id of the plugin to check the state.<br>
	 * @return the current state of the plugin
	 */
	public PluginState getPluginState(long id)
	{
		Tuple<IFlockPlugin, Boolean> plugin = plugins.get(id);

		if (ObjectUtils.isNull(plugin))
		{ return PluginState.NOT_FOUND; }

		return (plugin.getB() == TRUE) ? PluginState.LOADED : PluginState.UNLOADED;
	}

	/**
	 * Retrieves the plugin with the given id. <br>
	 * This method does not differentiate between loaded and unloaded plugins.<br>
	 * 
	 * @param id the id of the plugin to get
	 * @return the found plugin or null
	 */
	public IFlockPlugin getPlugin(long id)
	{
		Tuple<IFlockPlugin, Boolean> plugin = plugins.get(id);

		if (ObjectUtils.isNull(plugin))
		{ return (IFlockPlugin) NULL; }

		return plugin.getA();
	}

	/**
	 * Retrieves the plugin with the given id. <br>
	 * This method does not differentiate between loaded and unloaded plugins.<br>
	 * <br>
	 * The plugin is cast automatically to the correct return type.<br>
	 * But keep in mind this method is unchecked, meaning it does not check whether
	 * the cast is correct or not!
	 * 
	 * @param id the id of the plugin to get
	 * @return the found plugin or null
	 */
	@SuppressWarnings("unchecked")
	public <T extends IFlockPlugin> T getPluginUnsafe(long id)
	{
		Tuple<T, Boolean> plugin = (Tuple<T, Boolean>) plugins.get(id);

		if (ObjectUtils.isNull(plugin))
		{ return (T) NULL; }

		return (T) plugin.getA();
	}

	/**
	 * Loads all plugins that are in the file given with <code>path</code>.<br>
	 * 
	 * @param path the path of a jar file or directory where one or more plugin(s)
	 *             are located
	 */
	public void loadPlugins(String path)
	{
		loadPlugins(new File(path));
	}

	/**
	 * Loads all plugins that are in the .jar file given with <code>path</code>.<br>
	 * 
	 * @param path the path of a jar file or directory where one or more plugin(s)
	 *             are located
	 */
	public void loadPlugins(File path)
	{
		if (!ObjectUtils.isNull(path))
			if (path.exists())
			{
				loadPlugins(path.toPath());
			}
	}

	/**
	 * Loads all plugins that are in the .jar file given with <code>path</code>.<br>
	 * 
	 * @param path the path of a jar file or directory where one or more plugin(s)
	 *             are located
	 */
	public void loadPlugins(Path path)
	{
		try
		{
			if (!ObjectUtils.isNull(path))
			{
				// get jar files from folder and add them to the classpath
				String[] jarFiles = FileUtils.getJarFilesFromPath(path);
				FileUtils.addJarsToClasspath(jarFiles);

				// get the system classloader
				URLClassLoader cl = (URLClassLoader) ClassLoader.getSystemClassLoader();
				ObjectUtils.isNullThrow(cl, "Error while loading plugins - Classloader was null!");

				for (String file : jarFiles)
				{
					// if a entry is null simply skip it
					if (ObjectUtils.isNull(file))
						continue;

					IFlockPlugin plugin = (IFlockPlugin) NULL;

					JarFile jarFile = new JarFile(file);

					Enumeration<JarEntry> entries = jarFile.entries();

					while (entries.hasMoreElements())
					{
						JarEntry jarEntry = entries.nextElement();

						if (ObjectUtils.isNull(jarEntry))
							continue;

						String jarName = jarEntry.getName();

						if (ObjectUtils.isNull(jarName))
							continue;

						if (jarEntry.isDirectory() || !jarName.endsWith(".class"))
							continue;

						// get the classname and load the class using the classloader
						String className = jarName.substring(0, jarName.length() - ".class".length());
						className = className.replace('/', '.');
						Class<?> clazz = cl.loadClass(className);

						// this should never happen as loadClass would already throw an
						// ClassNotFoundException
						if (ObjectUtils.isNull(clazz))
							continue;

						Class<? extends IFlockPlugin> pluginCLS = null;
						pluginCLS = clazz.asSubclass(IFlockPlugin.class);

						if (ObjectUtils.isNull(pluginCLS))
							continue;

						plugin = pluginCLS.newInstance();
						loadPlugin(plugin);

					}
					// close file after we are done parsing
					jarFile.close();
				}
			}
		} catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @return all loaded plugins.
	 */
	public List<IFlockPlugin> getLoadedPlugins()
	{
		List<IFlockPlugin> pluginList = new ArrayList<>();
		for (Tuple<IFlockPlugin, Boolean> plugin : plugins.values())
		{
			if (plugin.getB() == TRUE)
				pluginList.add(plugin.getA());
		}
		return pluginList;
	}

	/**
	 * Loads the given plugin <code>plugin</code>.<br>
	 * Calls the {@link IFlockPlugin#onLoad()} method and sets its state to
	 * {@link PluginState#LOADED}.
	 * 
	 * @param plugin the plugin to load
	 * @return true if loaded successfully otherwise false
	 */
	public boolean loadPlugin(IFlockPlugin plugin)
	{
		ObjectUtils.isNullThrow(plugin, "The plugin to load must not be null!");

		long id = plugin.getPluginID();

		Tuple<IFlockPlugin, Boolean> plMap = plugins.get(id);

		// the plugin was not found inside the loaded plugins
		if (ObjectUtils.isNull(plMap))
		{
			// create a new map for the plugin
			Tuple<IFlockPlugin, Boolean> map = new Tuple<IFlockPlugin, Boolean>();

			return tryLoadPlugin(plugin, map);

		} else
		{
			boolean isLoaded = plMap.getB();
			if (isLoaded)
				return FALSE;
			else
			{
				return tryLoadPlugin(plugin, plMap);
			}
		}
	}

	/**
	 * Unloads the given plugin <code>plugin</code>.<br>
	 * Calls the {@link IFlockPlugin#onUnload()} method and sets its state to
	 * {@link PluginState#UNLOADED}.
	 * 
	 * @param plugin the plugin to load
	 * @return true if unloaded successfully otherwise false
	 */
	public boolean unloadPlugin(IFlockPlugin plugin)
	{
		ObjectUtils.isNullThrow(plugin, "The plugin to unload must not be null!");

		long id = plugin.getPluginID();

		Tuple<IFlockPlugin, Boolean> plMap = plugins.get(id);

		if (ObjectUtils.isNull(plMap))
			return TRUE;

		// plugin was loaded
		if (plMap.getB())
		{
			if (tryUnloadPlugin(plugin, plMap))
			{ return TRUE; }
		}

		return FALSE;

	}

	private boolean tryUnloadPlugin(IFlockPlugin plugin, Tuple<IFlockPlugin, Boolean> map)
	{
		if (unload(plugin))
		{
			setLoadState(plugin, map, FALSE);
			return TRUE;
		} else
		{
			setLoadState(plugin, map, TRUE);
			return FALSE;
		}
	}

	private boolean tryLoadPlugin(IFlockPlugin plugin, Tuple<IFlockPlugin, Boolean> map)
	{
		if (load(plugin))
		{
			setLoadState(plugin, map, TRUE);
			return TRUE;
		} else
		{
			setLoadState(plugin, map, FALSE);
			return FALSE;
		}
	}

	private void setLoadState(IFlockPlugin plugin, Tuple<IFlockPlugin, Boolean> map, boolean value)
	{
		ObjectUtils.isNullThrow(plugin, "Plugin to set the state must not be null!");
		ObjectUtils.isNullThrow(map, "Tuple to set the state must not be null!");
		map.put(plugin, value);
		plugins.put(plugin.getPluginID(), map);
	}

	private boolean unload(IFlockPlugin plugin)
	{
		if (ObjectUtils.isNull(plugin))
			return FALSE;

		if (plugin.onUnload())
		{
			this.flockBus.postEvent(new PluginUnloadSuccessEvent(plugin));
			return TRUE;
		} else
		{
			this.flockBus.postEvent(new PluginUnloadFailEvent(plugin));
			return FALSE;
		}
	}

	private boolean load(IFlockPlugin plugin)
	{
		if (ObjectUtils.isNull(plugin))
			return FALSE;

		if (plugin.onLoad())
		{
			this.flockBus.postEvent(new PluginLoadSuccessEvent(plugin));
			return TRUE;
		} else
		{
			this.flockBus.postEvent(new PluginLoadFailEvent(plugin));
			return FALSE;
		}
	}
}
