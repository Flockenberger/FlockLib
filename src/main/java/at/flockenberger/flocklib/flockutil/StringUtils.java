package at.flockenberger.flocklib.flockutil;

/**
 * String utilities.
 * 
 * @author Florian Wagner
 *
 */
public final class StringUtils
{
	/**
	 * Checks if the given {@link String} <code>str</code> contains the search
	 * string <code>searchStr</code>.<br>
	 * The case of the individual characters is ignored.
	 * 
	 * @param str       the String to check if it contains <code>searchStr</code>
	 * @param searchStr the search string
	 * @return true <code>str</code> contains <code>searchStr</code> otherwise false
	 */
	public static boolean containsIgnoreCase(String str, String searchStr)
	{
		if (str == null || searchStr == null)
			return false;

		final int length = searchStr.length();
		if (length == 0)
			return true;

		for (int i = str.length() - length; i >= 0; i--)
		{
			if (str.regionMatches(true, i, searchStr, 0, length))
				return true;
		}
		return false;
	}
}