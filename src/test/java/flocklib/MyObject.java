package flocklib;

import at.flockenberger.flocklib.flockbus.FlockBus;

public class MyObject
{

	private FlockBus bus;

	public MyObject(FlockBus bus)
	{
		this.bus = bus;
		yoIamCalling();
		
	}

	public void yoIamCalling()
	{
		bus.postEvent(new TestEvent3());
		bus.postEvent(new TestEvent2());
	}

}