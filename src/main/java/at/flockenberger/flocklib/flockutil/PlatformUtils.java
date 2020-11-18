package at.flockenberger.flocklib.flockutil;

/**
 * Platform utilities.
 * 
 * @author Florian Wagner
 *
 */
public final class PlatformUtils {
	/**
	 * the OS String in lower case
	 */
	private final static String _OS = System.getProperty("os.name").toLowerCase();

	/**
	 * the runtime
	 */
	private final static Runtime rt = Runtime.getRuntime();

	/**
	 * @return the operating system as {@link OS}.
	 */
	public static OS getOS() {
		return OS.fromString(_OS);
	}

	/**
	 * Gets the current Operating System {@link String} in lower case!<br>
	 * 
	 * @return the current OS
	 */
	public static String getOSString() {
		return _OS;
	}

	/**
	 * @return true if the current OS is Android otherwise false
	 */
	public static boolean isAndroid() {
		return (_OS.indexOf("andr") >= 0);
	}

	/**
	 * @return true if the current OS is Windows otherwise false
	 */
	public static boolean isWindows() {
		return (_OS.indexOf("win") >= 0);
	}

	/**
	 * @return true if the current OS is MacOS otherwise false
	 */
	public static boolean isMac() {
		return (_OS.indexOf("mac") >= 0);
	}

	/**
	 * @return true if the current OS is a Unix based system otherwise false
	 */
	public static boolean isUnix() {
		return (_OS.indexOf("uni") >= 0);
	}

	/**
	 * @return the name of the current user
	 */
	public static String getSystemUser() {
		return System.getProperty("user.name");
	}

	/**
	 * @return the amount of free memory in MB
	 */
	public static long getFreeMemory() {
		return (long) (rt.freeMemory() / 1e6);
	}

	/**
	 * @return the amount of total memory in MB
	 */
	public static long getTotalMemory() {
		return (long) (rt.totalMemory() / 1e6);
	}

	/**
	 * @return the amount of used memory in MB
	 */
	public static long getUsedMemory() {
		return getTotalMemory() - getFreeMemory();
	}

	public enum Architecture {

		/**
		 * x86 platform
		 */
		X86("X86"),

		/**
		 * amd64 platform
		 */
		AMD64("amd64"),

		/**
		 * arm platform
		 */
		ARM("arm"),

		/**
		 * sparc platform
		 */
		SPARC("sparc"),

		/**
		 * sparc64 platform
		 */
		SPARC64("sparc64"),

		/**
		 * ppc platform
		 */
		PPC("ppc"),

		/**
		 * ppc64 platform
		 */
		PPC64("ppc64"),

		/**
		 * any.<br>
		 * usually only when the architecture was not recognised by {@link #get()}
		 */
		ANY("any");

		private String identifier;

		private Architecture(String identifier) {
			this.identifier = identifier;
		}

		public String getIdentifier() {
			return this.identifier;
		}

		public static Architecture fromString(String osArch) {
			if ((osArch.startsWith("i") || osArch.startsWith("x")) && osArch.endsWith("86"))
				return X86;
			else if ((osArch.equals("i86") || osArch.startsWith("amd")) && osArch.endsWith("64"))
				return AMD64;
			else if (osArch.startsWith("arm"))
				return ARM;
			else if (osArch.startsWith("sparc"))
				return !osArch.endsWith("64") ? SPARC : SPARC64;
			else if (osArch.startsWith("ppc"))
				return !osArch.endsWith("64") ? PPC : PPC64;
			else
				return ANY;
		}

		/**
		 * @return the current architecture
		 */
		public static Architecture get() {
			return Architecture.fromString(System.getProperty("os.arch").toLowerCase());
		}

	}

	/**
	 * <h1>OS</h1><br>
	 * Simple enum for defining the operating systems available.
	 * 
	 * @author Florian Wagner
	 *
	 */
	public enum OS {
		/**
		 * windows operating system
		 */
		WINDOWS,

		/**
		 * mac operating system
		 */
		MAC,

		/**
		 * unix based operating system
		 */
		UNIX,

		/**
		 * android operating system
		 */
		ANDROID,

		/**
		 * undefined.<br>
		 * we don't know which one that is
		 */
		UNDEFINED;

		/**
		 * Returns an {@link Enum} representation of the os string given.<br>
		 * 
		 * @param os the os string
		 * @return enum representation of the given os string
		 */
		public static OS fromString(String os) {
			if (os.indexOf("andr") >= 0)
				return ANDROID;
			else if (os.indexOf("win") >= 0)
				return WINDOWS;
			else if (os.indexOf("mac") >= 0)
				return MAC;
			else if (os.indexOf("uni") >= 0)
				return UNIX;
			else
				return UNDEFINED;
		}

		/**
		 * @return the currently running {@link OS}
		 */
		public static OS get() {
			return OS.fromString(System.getProperty("os.name").toLowerCase());
		}

	}
}