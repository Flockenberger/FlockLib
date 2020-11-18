package at.flockenberger.flocklib.flockplug.event;

import at.flockenberger.flocklib.flockplug.IFlockPlugin;

/**
 * <h1>PluginUnloadSuccessEvent</h1> <br>
 * The event that is being send when a plugin was unloaded unsuccessfully.
 * 
 * @author Florian Wagner
 *
 */
public class PluginUnloadSuccessEvent extends PluginBaseEvent
{

	public PluginUnloadSuccessEvent(IFlockPlugin plugin)
	{
		super(plugin);
	}

}
