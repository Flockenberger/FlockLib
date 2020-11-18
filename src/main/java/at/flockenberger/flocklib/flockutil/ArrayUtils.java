package at.flockenberger.flocklib.flockutil;

public final class ArrayUtils
{
	/**
	 * Compares the two given byte arrays for equality.<br>
	 * If they are equal this method will return true otherwise false.
	 * 
	 * @param b1 the first byte array
	 * @param b2 the second byte array
	 * @return true if equal otherwise false
	 */
	public static boolean compare(byte[] b1, byte[] b2)
	{
		if (b1.length != b2.length)
		{ return false; }
		for (int i = 0; i < b1.length; i++)
		{
			if (b1[i] != b2[i])
			{ return false; }
		}
		return true;
	}
}