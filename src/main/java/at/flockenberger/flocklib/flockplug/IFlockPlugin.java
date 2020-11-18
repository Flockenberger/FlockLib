package at.flockenberger.flocklib.flockplug;

import at.flockenberger.flocklib.flockbus.FlockBus;
import at.flockenberger.flocklib.flockplug.event.PluginLoadFailEvent;
import at.flockenberger.flocklib.flockplug.event.PluginLoadSuccessEvent;
import at.flockenberger.flocklib.flockplug.event.PluginUnloadFailEvent;
import at.flockenberger.flocklib.flockplug.event.PluginUnloadSuccessEvent;

/**
 * <h1>IFlockPlugin</h1><br>
 * The base interface for every plugin.
 * 
 * @author Florian Wagner
 *
 */
public interface IFlockPlugin
{
	/**
	 * Called when the plugin is being loaded.<br>
	 * If a plugin successfully loads this method <b>must</b> return true otherwise
	 * the plugin wont be loaded by the manger and will not be accessible! <br>
	 * <br>
	 * Note: For every plugin the plugin manager will send out a message (event) to
	 * the global {@link FlockBus} with an {@link PluginLoadSuccessEvent} if loading
	 * was successful otherwise a {@link PluginLoadFailEvent} will be send.
	 * 
	 * @return true if the plugin was loaded correctly otherwise false
	 */
	public boolean onLoad();

	/**
	 * Called when the plugin is being unloaded.<br>
	 * This can be called dynamically or when the program is being closed. <br>
	 * <br>
	 * Note: For every plugin the plugin manager will send out a message (event) to
	 * the global {@link FlockBus} with an {@link PluginUnloadSuccessEvent} if
	 * unloadin was successful otherwise a {@link PluginUnloadFailEvent} will be
	 * send.
	 * 
	 * @return true if it unloading was successfull otherwise false.
	 */
	public boolean onUnload();

	/**
	 * The name of the plugin can be what ever you want.<br>
	 * The plugin system does not sort / store plugins by their name but rather by
	 * their respective id.
	 * 
	 * @return the name of the plugin
	 * @see #getPluginID()
	 */
	public String getName();

	/**
	 * Every plugin should have a unique id.<br>
	 * The id is used to identify the plugin at load and runtime.<br>
	 * 
	 * @return the id of this plugin
	 */
	public long getPluginID();

}
