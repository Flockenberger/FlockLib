package flocklib;

import at.flockenberger.flocklib.flockbus.Subscribe;
import at.flockenberger.flocklib.flockplug.event.PluginLoadSuccessEvent;
import at.flockenberger.flocklib.flockplug.event.PluginUnloadSuccessEvent;

public class A
{

	@Subscribe
	public void printFromA(TestEvent1 ev)
	{
		System.out.println(ev);
	}
	
	@Subscribe
	public void printPluginEvent(PluginLoadSuccessEvent event)
	{
		System.out.println(event.getPlugin().getName() + " loaded !!!!!");
	}

	@Subscribe
	public void printPluginEvent2(PluginUnloadSuccessEvent eve)
	{
		System.out.println(eve.getPlugin().getName() + " unloadedededed!!!!");
	}
}
