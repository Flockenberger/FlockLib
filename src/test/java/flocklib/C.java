package flocklib;

import at.flockenberger.flocklib.flockbus.Subscribe;

public class C extends B
{
	@Override
	public void printFromB(TestEvent2 ev)
	{
		System.out.println(ev);
	}

	@Subscribe
	public void printFromC(TestEvent3 ev)
	{
		System.out.println(ev);
	}
}
