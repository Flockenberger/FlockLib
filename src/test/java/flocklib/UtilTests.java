package flocklib;

import at.flockenberger.flocklib.flockani.AnimateableFloat;
import at.flockenberger.flocklib.flockbus.FlockBus;
import at.flockenberger.flocklib.flocklog.FlockLogManager;
import at.flockenberger.flocklib.flockres.FlockRes;
import at.flockenberger.flocklib.flockres.FlockResource;
import at.flockenberger.flocklib.flockutil.FileUtils;
import at.flockenberger.flocklib.flockutil.NumberUtils;
import at.flockenberger.flocklib.flockutil.ObjectUtils;
import at.flockenberger.flocklib.flockutil.ReflectUtils;

public class UtilTests
{
	static FlockBus bus = new FlockBus("");

	public static void main(String[] args)
	{
		FlockLogManager.getLogger().info("hello World");
		FlockLogManager.getLogger("hihi").info("bla");
		UtilTests obj = new UtilTests();
		ObjectUtils.isNullThrow(obj);

		A a = new A();
		B b = new B();
		C c = new C();

		bus.register(a);
		bus.register(b);
		bus.register(c);
		
		bus.postEvent(new TestEvent1());
		testPost();
	/*
		@SuppressWarnings("unused")
		MyObject obj2 = new MyObject(bus);
		obj2 = null;
		testPost();

		ReflectUtils.printStackTrace();
		System.out.println(FileUtils.isJar());

		FlockRes fr = FlockRes.get();

		
		//FlockResource<String> resource = fr.loadResource("evc", "evc_black_level.m", String.class);

		FlockResource<?> cached = fr.getCachedResource("evc");

		AnimateableFloat aniFloat = new AnimateableFloat(0f, 20f);

		aniFloat.update(0.2f);
		System.out.println(aniFloat.get());
		while (aniFloat.get() < 20f)
		{
			aniFloat.update(0.5f);
			System.out.println(aniFloat.get());
		}
		
		System.out.println(NumberUtils.isDouble("1.2"));
		System.out.println(NumberUtils.isFloat("1.2"));
		System.out.println(NumberUtils.isInteger("1.2"));
		System.out.println(NumberUtils.isInteger("1"));
		*/

	}

	public static void testPost()
	{
		bus.postEvent(new TestEvent2());
	}
}
