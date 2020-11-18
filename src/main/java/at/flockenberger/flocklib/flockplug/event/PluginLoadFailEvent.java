package at.flockenberger.flocklib.flockplug.event;

import at.flockenberger.flocklib.flockplug.IFlockPlugin;

/**
 * <h1>PluginLoadFailEvent</h1> <br>
 * The event that is being send when a plugin was loaded unsuccessfully.
 * 
 * @author Florian Wagner
 *
 */
public class PluginLoadFailEvent extends PluginBaseEvent
{
	public PluginLoadFailEvent(IFlockPlugin plugin)
	{
		super(plugin);
	}
}
