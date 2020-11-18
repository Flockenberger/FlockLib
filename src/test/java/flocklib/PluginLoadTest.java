package flocklib;

import java.io.File;

import at.flockenberger.flocklib.flockbus.FlockBus;
import at.flockenberger.flocklib.flockplug.FlockPluginManager;
import at.flockenberger.flocklib.flockutil.Defines;

public class PluginLoadTest
{

	public static void main(String[] args)
	{
		FlockBus customPluginBus = new FlockBus("Plugin Bus");
		
		FlockPluginManager fpm = FlockPluginManager.get();
		
		A a = new A();
		customPluginBus.register(a);
		
		fpm.setEventBus(customPluginBus);
		
		fpm.loadPlugins(new File("C:\\Users\\Florian Wagner\\Desktop\\testPlugin.jar"));	
		
		PluginTest plugin = (PluginTest) fpm.getPluginUnsafe(1);
		
		System.out.println(plugin.getName());
		
		fpm.unloadPlugin(plugin);
		
		System.out.println(fpm.getPlugin(1));
		
		System.out.println(fpm.getPluginState(plugin));
		fpm.loadPlugin(plugin);
		
		System.out.println(fpm.getPluginState(plugin));
		
		System.out.println(fpm.getLoadedPlugins());
		
		fpm.setEventBus(null);
	}

}
