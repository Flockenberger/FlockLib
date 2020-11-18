package at.flockenberger.flocklib.flockutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * File utilities.
 * 
 * @author Florian Wagner
 *
 */
public final class FileUtils {

	/**
	 * the protocol for packed jar files.
	 */
	public static final String JAR_EXT_PROTOCOL = "jar";

	/**
	 * the current protocol.<br>
	 * This tells whether we are inside a packed jar or inside some ide
	 */
	protected static final String CUR_PROTOCOL;

	/**
	 * Checks if this code is run from a jar file or some ide environment
	 * 
	 * @return
	 */
	public static boolean isJar() {
		return ObjectUtils.equals(CUR_PROTOCOL, JAR_EXT_PROTOCOL);

	}

	static {
		/**
		 * here we load a empty resource just to check whether we are inside a packed
		 * jar file or not.
		 */
		CUR_PROTOCOL = FileUtils.class.getResource("").getProtocol();
	}

	/**
	 * Opens a {@link InputStream} for the given {@link String} path
	 * <code>path</code>.<br>
	 * If the given {@link String} is null a {@link NullPointerException} will be
	 * thrown.<br>
	 * If the {@link InputStream} is null a {@link NullPointerException} will be
	 * thrown and wont return the actual stream!
	 * 
	 * @param path the path to the resource to load
	 * @return an {@link InputStream} of the resource
	 */
	public static InputStream openResourceStream(String path) {
		ObjectUtils.isNullThrow(path);

		/**
		 * as a precaution we check if the path starts with a /
		 */
		if (!path.startsWith("/"))
			path = "/" + path;

		/**
		 * if we are inside a jar file we need to add the /resource prefix to the path
		 */
		if (isJar())
			path = "/resources" + path;

		InputStream resourceStream = FileUtils.class.getResourceAsStream(path);

		ObjectUtils.isNullThrow(resourceStream);
		return resourceStream;
	}

	/**
	 * Adds the given {@link URL} <code>url</code> to the classpath.<br>
	 * This method does not check for correct syntax of the URL! <br>
	 * 
	 * @param url the url of the jar file to add to the classpath
	 */
	public static void addURLToClasspath(URL url) {
		ObjectUtils.isNullThrow(url, "The URL must not be null!");
		URLClassLoader loader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		Method addURL;
		try {
			addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			addURL.setAccessible(true);
			addURL.invoke(loader, url);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a Jar file given with the path <code>jarPath</code> to the
	 * {@link ClassLoader#getSystemClassLoader()}.
	 * 
	 * @param jarPath the path to the jar file
	 */
	public static void addJarToClasspath(String jarPath) {
		ObjectUtils.isNullThrow(jarPath, "The jar path must not be null!");
		URL url;
		try {
			url = new URL("jar:file:" + jarPath + "!/");
			addURLToClasspath(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds all jar files given with <code>jars</code> (<code>jars</code> is a list
	 * of paths of jar files) to the current classpath.<br>
	 * The {@link ClassLoader#getSystemClassLoader()} is used.
	 * 
	 * @param jars a list of paths of jar files
	 */
	public static void addJarsToClasspath(String[] jars) {
		ObjectUtils.isNullThrow(jars, "The jar path must not be null!");
		URL url;
		try {
			for (String jarPath : jars) {
				if (ObjectUtils.isNull(jarPath))
					continue;

				url = new URL("jar:file:" + jarPath + "!/");
				addURLToClasspath(url);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves all Jar files which are present in the given folder
	 * <code>path</code>. <br>
	 * This method takes all sub-directories into consideration meaning every Jar
	 * file from every sub-directory is in the returning {@link String} array. <br>
	 * 
	 * @param path the path to the folder to search for jar files
	 * @return guaranteed to never be null.<br>
	 *         Either a populated {@link String} array or an array with length 0.
	 * 
	 * @see #getFilesFromPath(Path, String)
	 */
	public static String[] getJarFilesFromPath(Path path) {
		return getFilesFromPath(path, ".jar");
	}

	/**
	 * Retrieves all files, with the extension given with <code>extension</code>,
	 * which are present in the given folder <code>path</code>. <br>
	 * This method takes all sub-directories into consideration meaning every file
	 * from every sub-directory is in the returning {@link String} array. <br>
	 * 
	 * @param path      the path to the folder to search for files of the given
	 *                  extension
	 * @param extension the file extension to search for. The extension string
	 *                  starts with a leading '.'<br>
	 *                  Example: <code>".jar" to get all Java-Jar files.</code>
	 * @return guaranteed to never be null.<br>
	 *         Either a populated {@link String} array or an array with length 0.
	 */
	public static String[] getFilesFromPath(Path path, String extension) {
		try {
			ObjectUtils.isNullThrow(path);
			return java.nio.file.Files
					.find(path, Integer.MAX_VALUE, (plugin, a) -> plugin.getFileName().toString().endsWith(extension))
					.map(plugin -> plugin.toString()).toArray(String[]::new);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String[0];

	}

	/**
	 * Returns the file extension of the given filename.
	 * 
	 * @param file the file to get the filename from
	 * @return the file extension or null
	 */
	public static String getFileExtension(File file) {
		ObjectUtils.isNullThrow(file);
		return getFileExtension(file.getName());
	}

	/**
	 * Returns the file extension of the given filename.
	 * 
	 * @param filename the filename
	 * @return the file extension or null
	 */
	public static String getFileExtension(String filename) {
		ObjectUtils.isNullThrow(filename);
		Optional<String> opt = Optional.ofNullable(filename).filter(f -> f.contains("."))
				.map(f -> f.substring(filename.lastIndexOf(".") + 1));
		return opt.get();
	}

	/**
	 * Reads the given {@link File} object into a {@link String}.
	 * 
	 * @param path the path which points to the file to read
	 * @return a {@link String} objects with the file's content or an empty
	 *         {@link String}
	 */
	public static String readFile(String path) {
		ObjectUtils.isNullThrow(path);
		return readFile(new File(path));
	}

	/**
	 * Reads the given {@link File} object into a {@link String}.
	 * 
	 * @param path the path which points to the file to read
	 * @return a {@link String} objects with the file's content or an empty
	 *         {@link String}
	 */
	public static String readFile(Path path) {
		ObjectUtils.isNullThrow(path);
		return readFile(path.toFile());
	}

	/**
	 * Reads the given {@link File} object into a {@link String}.
	 * 
	 * @param file the file to read
	 * @return a {@link String} objects with the file's content or an empty
	 *         {@link String}
	 */
	public static String readFile(File file) {
		ObjectUtils.isNullThrow(file);

		return readFile(StreamUtils.openStream(file));
	}

	/**
	 * Reads the given {@link InputStream} into a {@link String}
	 * 
	 * @param fis the input stream to read as {@link String}
	 * @return the read string
	 */
	public static String readFile(InputStream fis) {
		return new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8)).lines()
				.collect(Collectors.joining(System.lineSeparator()));

	}

	/**
	 * Reads the {@link File} provided by the given {@link String} <code>path</code>
	 * into a {@link List} of {@link String}.<br>
	 * Every entry in the {@link List} object represents a line of the file.<br>
	 * <br>
	 * This method never returns null!<br>
	 * 
	 * @param path the path to the file to read all the lines from
	 * @return the populated list of lines or an empty list
	 */
	public static List<String> readLines(String path) {
		ObjectUtils.isNullThrow(path);
		return readLines(new File(path));
	}

	/**
	 * Reads the {@link File} provided by the given {@link Path} <code>path</code>
	 * into a {@link List} of {@link String}.<br>
	 * Every entry in the {@link List} object represents a line of the file.<br>
	 * <br>
	 * This method never returns null!<br>
	 * 
	 * @param path the path to the file to read all the lines from
	 * @return the populated list of lines or an empty list
	 */
	public static List<String> readLines(Path path) {
		ObjectUtils.isNullThrow(path);
		return readLines(path.toFile());
	}

	/**
	 * Reads the given {@link File} into a {@link List} of {@link String}.<br>
	 * Every entry in the {@link List} object represents a line of the file.<br>
	 * <br>
	 * This method never returns null!<br>
	 * 
	 * @param file the file to read all the lines from
	 * @return the populated list of lines or an empty list
	 */
	public static List<String> readLines(File file) {
		ObjectUtils.isNullThrow(file);

		BufferedReader br = openReader(file);
		List<String> lines = new ArrayList<String>();

		String line;
		try {
			while ((line = br.readLine()) != null)
				lines.add(line);

		} catch (IOException e) {
			e.printStackTrace();
		}
		closeReader(br);

		return lines;
	}

	/**
	 * Opens a new {@link BufferedReader} for the given {@link String}.<br>
	 * If the {@link File} derived from <code>path</code> does not exist or is not
	 * readable this method will return a null reference.
	 * 
	 * @param path the path to the file to open a {@link BufferedReader}
	 * @return the {@link BufferedReader} for this file or null
	 */
	public static BufferedReader openReader(String path) {
		ObjectUtils.isNullThrow(path);
		return openReader(new File(path));
	}

	/**
	 * Opens a new {@link BufferedReader} for the given {@link Path}.<br>
	 * If the {@link File} derived from <code>path</code> does not exist or is not
	 * readable this method will return a null reference.
	 * 
	 * @param path the path to the file to open a {@link BufferedReader}
	 * @return the {@link BufferedReader} for this file or null
	 */
	public static BufferedReader openReader(Path path) {
		ObjectUtils.isNullThrow(path);
		return openReader(path.toFile());
	}

	/**
	 * Opens a new {@link BufferedReader} for the given {@link File}.<br>
	 * If the given {@link File} <code>file</code> does not exist or is not readable
	 * this method will return a null reference.
	 * 
	 * @param file the file to open a {@link BufferedReader}
	 * @return the {@link BufferedReader} for this file or null
	 */
	public static BufferedReader openReader(File file) {

		ObjectUtils.isNullThrow(file);

		BufferedReader reader = null;
		if (file.exists())
			if (file.canRead())
				try {
					reader = new BufferedReader(new FileReader(file));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		return reader;
	}

	/**
	 * Creates a new {@link File} from the given path.<br>
	 * 
	 * @param path the path to create the file object from.<br>
	 * @return a new {@link File} object
	 */
	public static File newFile(Path path) {
		ObjectUtils.isNullThrow(path);
		File file = path.toFile();
		try {
			file.createNewFile();
		} catch (IOException e) {
		}
		return file;
	}

	/**
	 * Closes the given {@link BufferedReader}.<br>
	 * If the given {@link BufferedReader} <code>reader</code> is null this method
	 * will throw a {@link NullPointerException}!
	 * 
	 * @param reader the reader to close
	 */
	public static void closeReader(BufferedReader reader) {
		ObjectUtils.isNullThrow(reader);

		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}