package at.flockenberger.flocklib.flockutil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import at.flockenberger.flocklib.flocklog.FlockLogManager;

/**
 * Reflection utilities.
 * 
 * @author Florian Wagner
 *
 */
public final class ReflectUtils
{
	/**
	 * Retrieves all super classes of the given {@link Object}
	 * <code>object</code>.<br>
	 * If the give Object <code>object</code> is already of type {@link Object} this
	 * method will return an empty {@link List}.
	 * 
	 * @param <T>    any type that extends {@link Object}
	 * @param object the object to get all super classes from
	 * @return a {@link List} of {@link Class} with all super classes
	 */
	public static <T extends Object> List<Class<?>> getSuperClasses(T object)
	{
		ObjectUtils.isNullThrow(object);

		List<Class<?>> classList = new ArrayList<Class<?>>();

		Class<?> cls = object.getClass();
		classList.add(cls);

		// check if the given T was in fact object
		if (cls.getName().equals("java.lang.Object"))
			return classList;

		Class<?> superclass = cls.getSuperclass();

		while (superclass != null)
		{
			classList.add(superclass);
			superclass = superclass.getSuperclass();

		}
		return classList;
	}

	/**
	 * Prints the current stack trace into the console.<br>
	 */
	public static void printStackTrace()
	{
		for (StackTraceElement ste : getStackTrace())
			FlockLogManager.getGlobalLogger().info(ste);
	}

	/**
	 * @return a defined array of {@link StackTraceElement}
	 */
	public static StackTraceElement[] getStackTrace()
	{ return new Throwable().getStackTrace(); }

	/**
	 * Checks if the given {@link StackTraceElement} is a "valid" element.<br>
	 * We check, and ignore, for the internal sun.reflect frames and invocation
	 * methods.<br>
	 * 
	 * @param element the element to validate
	 * @return true if valid otherwise false
	 */
	private static boolean isValid(final StackTraceElement element)
	{
		if (element.isNativeMethod())
		{ return false; }
		final String cn = element.getClassName();
		if (cn.startsWith("sun.reflect."))
		{ return false; }
		final String mn = element.getMethodName();
		if (cn.startsWith("java.lang.reflect.") && (mn.equals("invoke") || mn.equals("newInstance")))
		{ return false; }
		if (cn.equals("java.lang.Class") && mn.equals("newInstance"))
		{ return false; }
		if (cn.equals("java.lang.invoke.MethodHandle") && mn.startsWith("invoke"))
		{ return false; }

		return true;
	}

	/**
	 * Gets the {@link StackTraceElement} at the given <code>depth</code>.<br>
	 * The <code>depth</code> parameter is clamped to 0 and the maximum stacktrace
	 * length.<br>
	 * To get the last {@link StackTraceElement} one can pass
	 * {@link Integer#MAX_VALUE}.
	 * 
	 * @param depth gets the {@link StackTraceElement} at the index of depth
	 * @return the {@link StackTraceElement} at the provided depth
	 */
	public static StackTraceElement getStackTraceElement(int depth)
	{
		StackTraceElement[] stack = getStackTrace();
		int i = 0;
		depth = MathUtils.clampUpper(depth, stack.length - 1);
		for (final StackTraceElement element : stack)
			if (isValid(element))
			{
				if (i == depth)
				{ return element; }
				++i;
			}

		return null;
	}

	/**
	 * Retrieves the calling class name of the caller class for this method.<br>
	 * With the given <code>depth</code> parameter it is possible to step through
	 * the current stacktrace and go back to a specific depth.
	 * 
	 * @param depth the depth to get the calling class from
	 * @return the name of the calling class
	 */
	public static String getCallingClassName(int depth)
	{
		// System.out.println(getStackTraceElement());
		return getStackTraceElement(depth).getClassName();
	}

	/**
	 * Gets the calling class where this method is being called from.<br>
	 * In the stacktrace this would be the first class of the trace.
	 * 
	 * @return the name of the calling class
	 */
	public static String getCallingClassName()
	{ return getCallingClassName(Integer.MAX_VALUE); }

	/**
	 * Gets the calling class where this method is being called from.<br>
	 * In the stacktrace this would be the first class of the trace.
	 * 
	 * @return the class of the caller
	 */
	public static Class<? extends Object> getCallingClass()
	{ return getCallingClass(Integer.MAX_VALUE); }

	/**
	 * Retrieves the calling class of the caller class for this method.<br>
	 * With the given <code>depth</code> parameter it is possible to step through
	 * the current stacktrace and go back to a specific depth.
	 * 
	 * @param depth the depth to get the calling class from
	 * @return the class of the caller at the given depth
	 */
	public static Class<? extends Object> getCallingClass(int depth)
	{
		try
		{
			return Class.forName(ObjectUtils.notNull(getCallingClassName(depth)));
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Retrieves the name of the method from where this method has been called from.
	 * 
	 * @return the name of the calling method
	 */
	public static String getCallingMethodName()
	{ return getCallingMethodName(Integer.MAX_VALUE); }

	/**
	 * Retrieves the name of the method from where this method has been called from.
	 * With the given <code>depth</code> parameter it is possible to step through
	 * the current stacktrace and go back to a specific depth.
	 * 
	 * @param depth the depth to get the calling method from
	 * @return the name of the calling method
	 */
	public static String getCallingMethodName(int depth)
	{
		return getStackTraceElement(depth).getMethodName();
	}

	/**
	 * Retrieves the {@link Method} from where this method has been called from.
	 * 
	 * <b>Note: </b> The {@link Method} object is only derived by name only. Not the
	 * parameters of the calling method.<br>
	 * This means while the returned {@link Method} might be equivalent to the
	 * actual calling method in name but not necessarily the exact same.
	 * 
	 * @return the {@link Method} object representing the calling method
	 */
	public static Method getCallingMethod()
	{ return getCallingMethod(Integer.MAX_VALUE); }

	/**
	 * Retrieves the {@link Method} from where this method has been called from.
	 * With the given <code>depth</code> parameter it is possible to step through
	 * the current stacktrace and go back to a specific depth. <br>
	 * <br>
	 * <b>Note: </b> The {@link Method} object is only derived by name only. Not the
	 * parameters of the calling method.<br>
	 * This means while the returned {@link Method} might be equivalent to the
	 * actual calling method in name but not necessarily the exact same.
	 * 
	 * @param depth the depth to get the calling method from
	 * @return the {@link Method} object representing the calling method
	 */
	public static Method getCallingMethod(int depth)
	{
		String mName = getCallingMethodName(depth);
		Class<? extends Object> cls = getCallingClass(6);
		for (Method m : cls.getDeclaredMethods())
		{
			if (m.getName().equals(mName))
				return m;
		}
		return null;
	}

	/**
	 * Returns the name of the source file containing the execution point
	 * represented by this stack trace element. Generally, this corresponds to the
	 * SourceFile attribute of the relevant classfile (as per The Java Virtual
	 * Machine Specification, Section 4.7.7). In some systems, the name may refer to
	 * some source code unitother than a file, such as an entry in source
	 * repository.
	 * 
	 * @return the name of the file containing the execution point represented by
	 *         this stack trace element, or null if this information is unavailable.
	 */
	public static String getCallingFile()
	{ return getCallingFile(Integer.MAX_VALUE); }

	/**
	 * Returns the name of the source file containing the execution point
	 * represented by this stack trace element. Generally, this corresponds to the
	 * SourceFile attribute of the relevant classfile (as per The Java Virtual
	 * Machine Specification, Section 4.7.7). In some systems, the name may refer to
	 * some source code unitother than a file, such as an entry in source
	 * repository.<br>
	 * With the given <code>depth</code> parameter it is possible to step through
	 * the current stacktrace and go back to a specific depth. <br>
	 * 
	 * @param depth the depth to get the calling line number from
	 * @return the name of the file containing the execution point represented by
	 *         this stack trace element, or null if this information is unavailable.
	 */
	public static String getCallingFile(int depth)
	{
		return getStackTraceElement(depth).getFileName();
	}

	/**
	 * Returns the line number of the source line containing the executionpoint
	 * represented by this stack trace element. Generally, this is derived from the
	 * LineNumberTable attribute of the relevant class file (as per The Java Virtual
	 * MachineSpecification, Section 4.7.8).<br>
	 * 
	 * @return the line number of the source line containing the executionpoint
	 *         represented by this stack trace element, or a negative number if this
	 *         information is unavailable
	 */
	public static int getCallingLineNumber()
	{ return getCallingLineNumber(Integer.MAX_VALUE); }

	/**
	 * Returns the line number of the source line containing the executionpoint
	 * represented by this stack trace element. Generally, this is derived from the
	 * LineNumberTable attribute of the relevant class file (as per The Java Virtual
	 * MachineSpecification, Section 4.7.8). <br>
	 * With the given <code>depth</code> parameter it is possible to step through
	 * the current stacktrace and go back to a specific depth. <br>
	 * 
	 * @param depth the depth to get the calling line number from
	 * @return the line number of the source line containing the executionpoint
	 *         represented by this stack trace element, or a negative number if this
	 *         information is unavailable
	 */
	public static int getCallingLineNumber(int depth)
	{
		return getStackTraceElement(depth).getLineNumber();
	}

}