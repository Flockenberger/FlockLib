package at.flockenberger.flocklib.flockplug.event;

import at.flockenberger.flocklib.flockbus.EventBase;
import at.flockenberger.flocklib.flockplug.IFlockPlugin;

public class PluginBaseEvent extends EventBase
{
	/**
	 * the plugin that corresponds to this event
	 */
	private IFlockPlugin plugin;

	public PluginBaseEvent(IFlockPlugin plugin)
	{
		super();
		this.plugin = plugin;
	}

	/**
	 * @return the associated plugin with this event
	 */
	public IFlockPlugin getPlugin()
	{ return this.plugin; }
}
