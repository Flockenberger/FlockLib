package flocklib;

import at.flockenberger.flocklib.flockbus.Subscribe;

public class B extends A
{
	
	@Override
	public void printFromA(TestEvent1 ev) {
		System.out.println(ev);
	}
	@Subscribe
	public void printFromB(TestEvent2 ev)
	{
		System.out.println(ev);
	}
}
