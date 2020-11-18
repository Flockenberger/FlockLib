package at.flockenberger.flocklib.flockutil;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

/**
 * Loading utilities.
 * 
 * @author Florian Wagner
 *
 */
public final class LoaderUtils {
	/**
	 * Loads all external Jar files which are accessible through the given directory
	 * <code>path</code> and its sub-directories into the classpath.<br>
	 * 
	 * @param path the path to the directory to load all Jar files into the
	 *             classpath
	 */
	public static void loadExternalJars(Path path) {
		if (java.nio.file.Files.exists(path)) {
			String[] jarPaths = FileUtils.getJarFilesFromPath(path);
			for (int i = 0; i < jarPaths.length; i++) {
				try {
					URL url = new URL("jar:file:" + jarPaths[i] + "!/");
					FileUtils.addURLToClasspath(url);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static synchronized void loadLibrary(String libraryName) {
		
	}

}