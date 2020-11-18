package at.flockenberger.flocklib.flockutil;

/**
 * Number utility methods.
 * 
 * @author Florian Wagner
 *
 */
public final class NumberUtils
{
	/**
	 * Checks if the given {@link String} object <code>number</code> represents this
	 * number type and can safely be cast.<br>
	 * This method will return true if it is possible to cast the given
	 * {@link String} otherwise false.<br>
	 * 
	 * @param number the {@link String} to check if it can safely be cast to this
	 *               type
	 * @return true if it can be cast otherwise false
	 */
	public static boolean isInteger(String number)
	{
		try
		{
			Integer.valueOf(number);
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * Checks if the given {@link String} object <code>number</code> represents this
	 * number type and can safely be cast.<br>
	 * This method will return true if it is possible to cast the given
	 * {@link String} otherwise false.<br>
	 * 
	 * @param number the {@link String} to check if it can safely be cast to this
	 *               type
	 * @return true if it can be cast otherwise false
	 */
	public static boolean isDouble(String number)
	{
		try
		{
			Double.valueOf(number);
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * Checks if the given {@link String} object <code>number</code> represents this
	 * number type and can safely be cast.<br>
	 * This method will return true if it is possible to cast the given
	 * {@link String} otherwise false.<br>
	 * 
	 * @param number the {@link String} to check if it can safely be cast to this
	 *               type
	 * @return true if it can be cast otherwise false
	 */
	public static boolean isFloat(String number)
	{
		try
		{
			Float.valueOf(number);
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * Checks if the given {@link String} object <code>number</code> represents this
	 * number type and can safely be cast.<br>
	 * This method will return true if it is possible to cast the given
	 * {@link String} otherwise false.<br>
	 * 
	 * @param number the {@link String} to check if it can safely be cast to this
	 *               type
	 * @return true if it can be cast otherwise false
	 */
	public static boolean isLong(String number)
	{
		try
		{
			Long.valueOf(number);
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

}