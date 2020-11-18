package at.flockenberger.flocklib.flockplug.event;

import at.flockenberger.flocklib.flockplug.IFlockPlugin;

/**
 * <h1>PluginLoadSuccessEvent</h1> <br>
 * The event that is being send when a plugin was successfully loaded
 * 
 * @author Florian Wagner
 *
 */
public class PluginLoadSuccessEvent extends PluginBaseEvent
{

	public PluginLoadSuccessEvent(IFlockPlugin plugin)
	{
		super(plugin);
	}

}
