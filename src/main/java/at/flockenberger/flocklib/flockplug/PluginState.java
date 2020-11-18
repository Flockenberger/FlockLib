package at.flockenberger.flocklib.flockplug;

/**
 * <h1>PluginState</h1><br>
 * The {@link PluginState} enum indicates whether a plugin is loaded or
 * unloaded.
 * 
 * @author Florian Wagner
 *
 */
public enum PluginState
{
	/**
	 * indicates that a plugin has been loaded
	 */
	LOADED,

	/**
	 * indicates that a plugin has been or is unloaded
	 */
	UNLOADED,
	
	/**
	 * the plugin was not found by the manager
	 */
	NOT_FOUND;

	PluginState()
	{

	}
}
