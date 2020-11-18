package flocklib;

import at.flockenberger.flocklib.flockplug.IFlockPlugin;

public class PluginTest implements IFlockPlugin
{

	@Override
	public boolean onLoad()
	{
		System.out.println("Hello World!");
		return true;
	}

	@Override
	public boolean onUnload()
	{
		System.out.println("Bye World!");
		return true;
	}

	@Override
	public String getName()
	{ return "Test Plugin"; }

	@Override
	public long getPluginID()
	{ return 1; }

}
