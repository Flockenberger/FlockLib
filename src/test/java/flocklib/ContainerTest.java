package flocklib;

import at.flockenberger.flocklib.flockbus.FlockBus;
import at.flockenberger.flocklib.flockutil.data.DataContainer;
import at.flockenberger.flocklib.flockutil.data.DataStore;

public class ContainerTest {
	static FlockBus bus = new FlockBus("");

	public static void main(String[] args) {

		DataContainer container = new DataContainer();
		container.setDouble("hello world", 1.2f);
	
		MyObject object = new MyObject(bus);

		container.setObject("MyObject1", object);
		container.print();
		
		DataContainer cnt = container.getContainerInstance("MyObject1");
		cnt.setFloat("dummyValue1", 5f);
		
		MyObject loadedObj = cnt.toObject(MyObject.class);
		
		System.out.println(loadedObj);
		System.out.println(object);

		String path = "C:\\Users\\NEON\\Desktop\\store.txt";
		DataStore.storeObject(path, container);
		
		DataContainer loadedCnt = DataStore.loadObject(path);
		
		loadedCnt.print();

	}

}
