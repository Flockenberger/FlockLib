package at.flockenberger.flocklib.flockutil;


import static at.flockenberger.flocklib.flockutil.Defines.*;

/**
 * Provides {@link static} methods for object handling.
 * 
 * @author Florian Wagner
 *
 */
public final class ObjectUtils
{

	/**
	 * Compares the two given {@link Object} <code>a</code> and <code>b</code> for
	 * equality.
	 * 
	 * @param a the first object
	 * @param b the second object
	 * @return true if they are equal otherwise false
	 */
	public static boolean equals(Object a, Object b)
	{
		return (a == b) || (a != NULL && a.equals(b));
	}

	/**
	 * Checks if the given parameter <code>object</code> of any type <T> which
	 * extends {@link Object} is pointing to a null reference. If that is the case
	 * this method will throw a {@link NullPointerException}.<br>
	 * Otherwise the same {@link Object} will be returned.<br>
	 * This method internally calls {@link #isNullThrow(Object)}.
	 * 
	 * @param <T>    any class that extends {@link Object}
	 * @param object the object to check if it is pointing to a null reference
	 * @return the given object if not null
	 */
	public static <T extends Object> T notNull(T object)
	{
		return notNull(object, "");
	}

	/**
	 * Checks if the given parameter <code>object</code> of any type <T> which
	 * extends {@link Object} is pointing to a null reference. If that is the case
	 * this method will throw a {@link NullPointerException}.<br>
	 * Otherwise the same {@link Object} will be returned.<br>
	 * This method internally calls {@link #isNullThrow(Object)}.
	 * 
	 * @param <T>     any class that extends {@link Object}
	 * @param object  the object to check if it is pointing to a null reference
	 * @param message an optional message to pass to the exception
	 * @return the given object if not null
	 */
	public static <T extends Object> T notNull(T object, String message)
	{
		isNullThrow(object, message);
		return object;
	}

	/**
	 * Checks if any of the given objects <code>objects</code> of type
	 * <code>T</code> is pointing to a null reference. This method will throw a
	 * {@link NullPointerException} if any of the given objects <code>objects</code>
	 * is pointing to a null reference.
	 * 
	 * @param <T>     any class that extends {@link Object}
	 * @param objects the objects to check if any is pointing to a null reference
	 */
	@SafeVarargs
	public static <T extends Object> void isAnyNullThrow(T... objects)
	{
		for (T object : objects)
			isNullThrow(object);
	}

	/**
	 * Checks if any of the given objects <code>objects</code> of type
	 * <code>T</code> is pointing to a null reference. This method will throw a
	 * {@link NullPointerException} if any of the given objects <code>objects</code>
	 * is pointing to a null reference.
	 * 
	 * @param <T>     any class that extends {@link Object}
	 * @param message an optional message to pass to the exception
	 * @param objects the objects to check if any is pointing to a null reference
	 */
	@SafeVarargs
	public static <T extends Object> void isAnyNullThrow(String message, T... objects)
	{
		for (T object : objects)
			isNullThrow(object, message);
	}

	/**
	 * Checks if the given parameter <code>object</code> of type <code>T</code> is
	 * pointing to a null reference. This method will throw a
	 * {@link NullPointerException} when the given parameter <code>object</code> is
	 * pointing to a null reference.
	 * 
	 * @param <T>    any class that extends {@link Object}
	 * @param object the object to check if it is pointing to a null reference
	 */
	public static <T extends Object> void isNullThrow(T object)
	{
		isNullThrow(object, "");
	}

	/**
	 * Checks if the given parameter <code>object</code> of type <code>T</code> is
	 * pointing to a null reference. This method will throw a
	 * {@link NullPointerException} when the given parameter <code>object</code> is
	 * pointing to a null reference.
	 * 
	 * @param <T>     any class that extends {@link Object}
	 * @param object  the object to check if it is pointing to a null reference
	 * @param message an optional message to pass to the exception
	 */
	public static <T extends Object> void isNullThrow(T object, String message)
	{
		if (isNull(object))
			throw new NullPointerException(message);
	}

	/**
	 * Checks if any of the given objects <code>objects</code> of type
	 * <code>T</code> is pointing to a null reference. If any one does in fact point
	 * to a null reference this method returns true otherwise false. <br>
	 * Note: This method will <b>not</b> throw a {@link NullPointerException}!
	 * 
	 * @param <T>     any class that extends {@link Object}
	 * @param objects the objects to check if any is pointing to a null reference
	 * @return true if any one is pointing to a null reference otherwise false
	 */
	@SafeVarargs
	public static <T extends Object> boolean isAnyNull(T... objects)
	{
		boolean anyNull = FALSE;
		for (T object : objects)
			anyNull = isNull(object);
		return anyNull;
	}

	/**
	 * Checks if the given parameter <code>object</code> of type <code>T</code> is
	 * pointing to a null reference. If it does in fact point to a null reference
	 * this method returns true otherwise false. <br>
	 * Note: This method will <b>not</b> throw a {@link NullPointerException}!
	 * 
	 * @param <T>    any class that extends {@link Object}
	 * @param object the object to check if it is pointing to a null reference
	 * @return true if it is pointing to a null reference otherwise false
	 */
	public static <T extends Object> boolean isNull(T object)
	{
		return object == NULL;
	}
}