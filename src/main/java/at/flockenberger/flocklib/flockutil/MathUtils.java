package at.flockenberger.flocklib.flockutil;

/**
 * Math utility methods.
 * 
 * @author Florian Wagner
 *
 */
public final class MathUtils
{

	/**
	 * The number e is a mathematical constant approximately equal to 2.71828 and is
	 * the base of the natural logarithm, that is the unique number whose natural
	 * logarithm equals one. It is the limit of (1 + 1/n)n as n approaches infinity,
	 * an expression that arises in the study of compound interest. It can also be
	 * calculated as the sum of the infinite series
	 */
	public static final double E = 2.7182818284590452354;

	/**
	 * Pi or π is a mathematical constant equal to a circle's circumference divided
	 * by its diameter<br>
	 */
	public static final double PI = 3.14159265358979323846;

	// Precise method, which guarantees v = v1 when t = 1.
	/**
	 * Precise method for interpolating two values.<br>
	 * It guarantees that the returned parameter = <code>v1</code> when
	 * <code>t=1</code>.
	 * 
	 * @param v0 first value to mix
	 * @param v1 second value to mix
	 * @param t  interpolation factor between 0-1.
	 * @return an interpolation between two inputs (v0, v1) for a parameter (t) in
	 *         the closed unit interval [0, 1]
	 */
	public static float lerpf(float v0, float v1, float t)
	{
		return (1 - t) * v0 + t * v1;

	}

	/**
	 * Double method of {@link #lerpf(float, float, float)}<br>
	 * Precise method for interpolating two values.<br>
	 * It guarantees that the returned parameter = <code>v1</code> when
	 * <code>t=1</code>.
	 * 
	 * @param v0 first value to mix
	 * @param v1 second value to mix
	 * @param t  interpolation factor between 0-1.
	 * @return an interpolation between two inputs (v0, v1) for a parameter (t) in
	 *         the closed unit interval [0, 1]
	 * 
	 */
	public static double lerpd(double v0, double v1, double t)
	{
		return (1 - t) * v0 + t * v1;

	}

	/**
	 * Returns the value of the first argument raised to the power of the second
	 * argument.
	 * 
	 * @param a the base
	 * @param b the exponent
	 * @return <code>a</code> raised to the power of <code>b</code>
	 */
	public static long pow(int a, int b)
	{
		a = clampLower(a, 0);
		b = clampLower(b, 0);

		long bigValue = 1;
		for (int i = 0; i < b; i++)
		{
			bigValue *= a;
			if (bigValue >= Long.MAX_VALUE / a && (i + 1) < b)
			{ throw new RuntimeException("Result Long would Overflow"); }
		}

		return bigValue;
	}

	/**
	 * Puts the given parameter <code>value</code> into the bounds given with
	 * <code>upper</code> and <code>lower</code>.<br>
	 * If <code>value</code> is greater than <code>upper</code> then
	 * <code>value = upper</code> will be performed. Vice verca with the lower
	 * bound.
	 * 
	 * @param value the value to put into bounds
	 * @param upper the upper bound to put the value in
	 * @param lower the lower bound to put the value in
	 * @return the bounded value
	 */
	public static int clamp(int value, int upper, int lower)
	{
		value = clampUpper(value, upper);
		value = clampLower(value, lower);
		return value;
	}

	/**
	 * Puts the given parameter <code>value</code> into the bounds given with
	 * <code>upper</code> and <code>lower</code>.<br>
	 * If <code>value</code> is greater than <code>upper</code> then
	 * <code>value = upper</code> will be performed. Vice verca with the lower
	 * bound.
	 * 
	 * @param value the value to put into bounds
	 * @param upper the upper bound to put the value in
	 * @param lower the lower bound to put the value in
	 * @return the bounded value
	 */
	public static float clamp(float value, float upper, float lower)
	{
		return clampLower(clampUpper(value, upper), lower);
	}

	/**
	 * Puts the given parameter <code>value</code> into the bounds given with
	 * <code>upper</code> and <code>lower</code>.<br>
	 * If <code>value</code> is greater than <code>upper</code> then
	 * <code>value = upper</code> will be performed. Vice verca with the lower
	 * bound.
	 * 
	 * @param value the value to put into bounds
	 * @param upper the upper bound to put the value in
	 * @param lower the lower bound to put the value in
	 * @return the bounded value
	 */
	public static double clamp(double value, double upper, double lower)
	{
		return clampLower(clampUpper(value, upper), lower);
	}

	/**
	 * Puts the value into the given upper bound <code>upper</code>. If
	 * <code>value</code> is greater than <code>upper</code> then
	 * <code>value = upper</code> will be performed and the upper bound will be
	 * returned.
	 * 
	 * @param value the value to put into bounds
	 * @param upper the upper bound to put the value in
	 * @return the value or the upper bound
	 */
	public static int clampUpper(int value, int upper)
	{
		if (value > upper)
			value = upper;
		return value;
	}

	/**
	 * Puts the value into the given upper bound <code>upper</code>. If
	 * <code>value</code> is greater than <code>upper</code> then
	 * <code>value = upper</code> will be performed and the upper bound will be
	 * returned.
	 * 
	 * @param value the value to put into bounds
	 * @param upper the upper bound to put the value in
	 * @return the value or the upper bound
	 */
	public static float clampUpper(float value, float upper)
	{
		if (value > upper)
			value = upper;
		return value;
	}

	/**
	 * Puts the value into the given upper bound <code>upper</code>. If
	 * <code>value</code> is greater than <code>upper</code> then
	 * <code>value = upper</code> will be performed and the upper bound will be
	 * returned.
	 * 
	 * @param value the value to put into bounds
	 * @param upper the upper bound to put the value in
	 * @return the value or the upper bound
	 */
	public static double clampUpper(double value, double upper)
	{
		if (value > upper)
			value = upper;
		return value;
	}

	/**
	 * Puts the value into the given lower bound <code>lower</code>. If
	 * <code>value</code> is less than <code>lower</code> then
	 * <code>value = lower</code> will be performed and the upper bound will be
	 * returned.
	 * 
	 * @param value the value to put into bounds
	 * @param lower the lower bound to put the value in
	 * @return the value or the lower bound
	 */
	public static int clampLower(int value, int lower)
	{
		if (value < lower)
			value = lower;
		return value;
	}

	/**
	 * Puts the value into the given lower bound <code>lower</code>. If
	 * <code>value</code> is less than <code>lower</code> then
	 * <code>value = lower</code> will be performed and the upper bound will be
	 * returned.
	 * 
	 * @param value the value to put into bounds
	 * @param lower the lower bound to put the value in
	 * @return the value or the lower bound
	 */
	public static float clampLower(float value, float lower)
	{
		if (value < lower)
			value = lower;
		return value;
	}

	/**
	 * Puts the value into the given lower bound <code>lower</code>. If
	 * <code>value</code> is less than <code>lower</code> then
	 * <code>value = lower</code> will be performed and the upper bound will be
	 * returned.
	 * 
	 * @param value the value to put into bounds
	 * @param lower the lower bound to put the value in
	 * @return the value or the lower bound
	 */
	public static double clampLower(double value, double lower)
	{
		if (value < lower)
			value = lower;
		return value;
	}

	/**
	 * Checks if the given value is within the given lower and upper bounds.<br>
	 * The bounds are inclusive!<br>
	 * 
	 * @param value the value to check if it is in bounds
	 * @param upper the upper, inclusive, bound
	 * @param lower the lower, inclusive, bound
	 * @return true if within bounds otherwise false
	 */
	public static boolean isWithin(int value, int upper, int lower)
	{
		return (value <= upper && value >= lower);
	}

	/**
	 * Checks if the given value is within the given lower and upper bounds.<br>
	 * The bounds are inclusive!<br>
	 * 
	 * @param value the value to check if it is in bounds
	 * @param upper the upper, inclusive, bound
	 * @param lower the lower, inclusive, bound
	 * @return true if within bounds otherwise false
	 */
	public static boolean isWithin(float value, float upper, float lower)
	{
		return (value <= upper && value >= lower);
	}

	/**
	 * Checks if the given value is within the given lower and upper bounds.<br>
	 * The bounds are inclusive!<br>
	 * 
	 * @param value the value to check if it is in bounds
	 * @param upper the upper, inclusive, bound
	 * @param lower the lower, inclusive, bound
	 * @return true if within bounds otherwise false
	 */
	public static boolean isWithin(double value, double upper, double lower)
	{
		return (value <= upper && value >= lower);
	}

	/**
	 * Truncates the given double value <code>unroundedNumber</code> to the given
	 * decimal places <code>decimalPlaces</code>.<br>
	 * 
	 * @param unroundedNumber the number to round
	 * @param decimalPlaces   the number of decimal places
	 * @return the rounded value
	 */
	public static double truncate(double unroundedNumber, int decimalPlaces)
	{
		double s = pow(10, decimalPlaces);
		int truncatedNumberInt = (int) (unroundedNumber * s);
		double truncatedNumber = (double) (truncatedNumberInt / s);
		return truncatedNumber;
	}

	/**
	 * Truncates the given double value <code>unroundedNumber</code> to the given
	 * decimal places <code>decimalPlaces</code>.<br>
	 * 
	 * @param unroundedNumber the number to round
	 * @param decimalPlaces   the number of decimal places
	 * @return the rounded value
	 */
	public static float truncate(float unroundedNumber, int decimalPlaces)
	{
		double s = pow(10, decimalPlaces);
		int truncatedNumberInt = (int) (unroundedNumber * s);
		float truncatedNumber = (float) (truncatedNumberInt / s);
		return truncatedNumber;
	}

	/**
	 * Transforms the given <code>deg</code> value in degrees to radians.<br>
	 * 1° × π/180 = 0,01745
	 * 
	 * @param deg the value to convert from degree into radians
	 * @return the converted value
	 */
	public static float degToRad(float deg)
	{
		return (float) (deg * PI / 180.f);
	}

	/**
	 * <code>double</code> version of {@link #degToRad(float)}.<br>
	 * Transforms the given <code>deg</code> value in degrees to radians.<br>
	 * 1° × π/180 = 0,01745
	 * 
	 * @param deg the value to convert from degree into radians
	 * @return the converted value
	 */
	public static double degToRad(double deg)
	{
		return (deg * PI / 180.f);
	}

	/**
	 * Transforms the given <code>rad</code> value in radians to degrees.<br>
	 * 1 rad × 180/π = 57,296°
	 * 
	 * @param rad the radians value to convert into degrees
	 * @return the converted value
	 */
	public static float radToDeg(float rad)
	{
		return rad * 180.0f / (float) PI;
	}

	/**
	 * <code>double</code> versoin of {@link #radToDeg(float)} Transforms the given
	 * <code>rad</code> value in radians to degrees.<br>
	 * 1 rad × 180/π = 57,296°
	 * 
	 * @param rad the radians value to convert into degrees
	 * @return the converted value
	 */
	public static double radToDeg(double rad)
	{
		return rad * 180.0f / PI;
	}

	/**
	 * Range maps the given parameter <code>value</code> from its input range given
	 * with <code>in_min and in_max</code> into the given output range
	 * <code> out_min and out_max</code>.
	 * 
	 * @param value   the value to range map
	 * @param in_min  the minimum value the given parameter <code>value</code> can
	 *                be
	 * @param in_max  the maximum value the given parameter <code>value</code> can
	 *                be
	 * @param out_min the new minimum the given parameter <code>value</code> should
	 *                be mapped to
	 * @param out_max the new maximum the given parameter <code>value</code> should
	 *                be mapped to
	 * @return the mapped value in the new range of <code>out_min and out_max</code>
	 */
	public static float map(float value, float in_min, float in_max, float out_min, float out_max)
	{
		return (value - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;

	}

	/**
	 * <code>double</code> version of
	 * {@link #map(float, float, float, float, float)}<br>
	 * Range maps the given parameter <code>value</code> from its input range given
	 * with <code>in_min and in_max</code> into the given output range
	 * <code> out_min and out_max</code>.
	 * 
	 * @param value   the value to range map
	 * @param in_min  the minimum value the given parameter <code>value</code> can
	 *                be
	 * @param in_max  the maximum value the given parameter <code>value</code> can
	 *                be
	 * @param out_min the new minimum the given parameter <code>value</code> should
	 *                be mapped to
	 * @param out_max the new maximum the given parameter <code>value</code> should
	 *                be mapped to
	 * @return the mapped value in the new range of <code>out_min and out_max</code>
	 */
	public static double map(double value, double in_min, double in_max, double out_min, double out_max)
	{
		return (value - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;

	}
}