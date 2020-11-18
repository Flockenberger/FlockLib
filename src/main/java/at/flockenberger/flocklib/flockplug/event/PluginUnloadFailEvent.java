package at.flockenberger.flocklib.flockplug.event;

import at.flockenberger.flocklib.flockplug.IFlockPlugin;

/**
 * <h1>PluginUnloadFailEvent</h1> <br>
 * The event that is being send when a plugin was unloaded with an error.
 * 
 * @author Florian Wagner
 *
 */
public class PluginUnloadFailEvent extends PluginBaseEvent
{

	public PluginUnloadFailEvent(IFlockPlugin plugin)
	{
		super(plugin);
	}
}
