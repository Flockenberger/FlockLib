package at.flockenberger.flocklib.flockgfx;

import at.flockenberger.flocklib.flockutil.MathUtils;

/**
 * <h1>FlockColor</h1><br>
 * 
 * 
 * @author Florian Wagner
 *
 */
public class FlockColor
{

	/**
	 * red component of the color
	 */
	private double red;

	/**
	 * green component of the color
	 */
	private double green;

	/**
	 * blue component of the color
	 */
	private double blue;

	/**
	 * alpha component of the color
	 */
	private double alpha;

	/**
	 * default brighten/darken factor.<br>
	 * <code>0.6</code>
	 */
	private final float FACTOR = 0.6F;

// ==== static methods

	public static FlockColor random()
	{
		return new FlockColor(Math.random(), Math.random(), Math.random());
	}

	public static final FlockColor WHITE = new FlockColor(1, 1, 1);

	public static final FlockColor BLACK = new FlockColor(0, 0, 0);

	public static final FlockColor TRANSPARENT = new FlockColor(0, 0, 0, 0);

	public static final FlockColor RED = FlockColor.rgb(247, 55, 60);

	public static final FlockColor BRIGHT_RED = FlockColor.rgb(254, 134, 137);

	public static final FlockColor DARK_RED = FlockColor.rgb(224, 12, 17);

	public static final FlockColor GREEN = FlockColor.rgb(128, 222, 50);

	public static final FlockColor BRIGHT_GREEN = FlockColor.rgb(178, 239, 127);

	public static final FlockColor DARK_GREEN = FlockColor.rgb(97, 201, 11);

	public static final FlockColor LIME = FlockColor.rgb(181, 233, 55);

	public static final FlockColor BRIGHT_LIME = FlockColor.rgb(214, 247, 133);

	public static final FlockColor DARK_LIME = FlockColor.rgb(154, 211, 14);

	public static final FlockColor YELLOW = FlockColor.rgb(247, 243, 58);

	public static final FlockColor BRIGHT_YELLOW = FlockColor.rgb(255, 253, 137);

	public static final FlockColor DARK_YELLOW = FlockColor.rgb(224, 220, 15);

	public static final FlockColor PINK = FlockColor.rgb(215, 51, 111);

	public static final FlockColor BRIGHT_PINK = FlockColor.rgb(236, 127, 168);

	public static final FlockColor DARK_PINK = FlockColor.rgb(195, 13, 80);

	public static final FlockColor PURPLE = FlockColor.rgb(157, 38, 161);

	public static final FlockColor BRIGHT_PURPLE = FlockColor.rgb(202, 111, 205);

	public static final FlockColor DARK_PURPLE = FlockColor.rgb(141, 10, 146);

	public static final FlockColor VIOLET = FlockColor.rgb(105, 49, 167);

	public static final FlockColor BRIGHT_VIOLET = FlockColor.rgb(161, 120, 209);

	public static final FlockColor DARK_VIOLET = FlockColor.rgb(82, 21, 152);

	public static final FlockColor TURQUOISE = FlockColor.rgb(35, 148, 148);

	public static final FlockColor BRIGHT_TURQUOISE = FlockColor.rgb(106, 198, 198);

	public static final FlockColor DARK_TURQUOISE = FlockColor.rgb(9, 134, 134);

	public static final FlockColor BROWN = FlockColor.rgb(247, 144, 58);

	public static final FlockColor BRIGHT_BROWN = FlockColor.rgb(255, 191, 137);

	public static final FlockColor DARK_BROWN = FlockColor.rgb(224, 110, 15);

	public static final FlockColor BLUE = FlockColor.rgb(52, 86, 165);

	public static final FlockColor BRIGHT_BLUE = FlockColor.rgb(122, 148, 208);

	public static final FlockColor DARK_BLUE = FlockColor.rgb(25, 63, 150);

	public static final FlockColor ORANGE = FlockColor.rgb(249, 153, 56);

	public static final FlockColor BRIGHT_ORANGE = FlockColor.rgb(255, 195, 135);

	public static final FlockColor DARK_ORANGE = FlockColor.rgb(226, 120, 12);

	/**
	 * Creates a new {@link FlockColor}.<br>
	 * The input is an RGBA integer.
	 * 
	 * @param rgba the rgba value to create this color from
	 * @return the created color from this value
	 */
	public static FlockColor rgba(int rgba)
	{
		return new FlockColor(rgba);
	}

	/**
	 * Creates a new {@link FlockColor}.<br>
	 * The input is an RGBA byte array.<br>
	 * The first 4 values are used.
	 * 
	 * @param rgba the byte array containing the color components
	 * @return teh created color from the given input
	 */
	public static FlockColor rgba(byte[] rgba)
	{
		if (rgba.length < 4)
			throw new IllegalArgumentException("Input array must be at least length 4!");
		return rgb(rgba[0], rgba[1], rgba[2], rgba[3]);
	}

	/**
	 * Creates a new {@link FlockColor} from the given values.<br>
	 * Value must be in range 0-255.<br>
	 * 
	 * @param red   the red component of the color
	 * @param green the green component of the color
	 * @param blue  the blue component of the color
	 * @return the color created from the given input
	 */
	public static FlockColor rgb(int red, int green, int blue)
	{
		return rgb(red, green, blue, 255);
	}

	/**
	 * Creates a new {@link FlockColor} from the given values.<br>
	 * Value must be in range 0-255.<br>
	 * 
	 * @param red   the red component of the color
	 * @param green the green component of the color
	 * @param blue  the blue component of the color
	 * @param alpha the alpha component of this color
	 * @return the color created from the given input
	 */
	public static FlockColor rgb(int red, int green, int blue, int alpha)
	{
		return new FlockColor(red, green, blue, alpha);
	}

	/**
	 * Creates a new {@link FlockColor} from the given values.<br>
	 * Value must be in range 0-1.<br>
	 * 
	 * @param red   the red component of the color
	 * @param green the green component of the color
	 * @param blue  the blue component of the color
	 * @return the color created from the given input
	 */
	public static FlockColor rgb(double red, double green, double blue)
	{
		return rgb(red, green, blue, 1.0);
	}

	/**
	 * Creates a new {@link FlockColor} from the given values.<br>
	 * Value must be in range 0-1.<br>
	 * 
	 * @param red   the red component of the color
	 * @param green the green component of the color
	 * @param blue  the blue component of the color
	 * @param alpha the alpha component of this color
	 * @return the color created from the given input
	 */

	public static FlockColor rgb(double red, double green, double blue, double alpha)
	{
		return new FlockColor(red, green, blue, alpha);
	}

	/**
	 * Creates a new {@link FlockColor} from the given values.<br>
	 * 
	 * @param hue        the hue component of the color in range 0-360
	 * @param saturation the saturation component of the color in range 0-1
	 * @param brightness the brightness component of the color in range 0-1
	 * @return the color created from the given input
	 */
	public static FlockColor hsb(double hue, double saturation, double brightness)
	{
		FlockColor fc = new FlockColor();
		fc.setHSB(hue, saturation, brightness);
		fc.setAlpha(1.0);
		return fc;
	}

	/**
	 * Creates a copy of the given {@link FlockColor}.<br>
	 * 
	 * @param color the color to copy
	 * @return the color created from the given input
	 */
	public static FlockColor copy(FlockColor color)
	{
		return new FlockColor(color);
	}

	/**
	 * Creates a new gray {@link FlockColor} from the given value.<br>
	 * Value must be in range 0-1.<br>
	 * 
	 * @param gray the gray component of the color
	 * @return the color created from the given input
	 */
	public static FlockColor gray(double gray)
	{
		return rgb(gray, gray, gray);
	}

	/**
	 * Creates a new gray {@link FlockColor} from the given value.<br>
	 * Value must be in range 0-25.<br>
	 * 
	 * @param gray the gray component of the color
	 * @return the color created from the given input
	 **/
	public static FlockColor gray(int gray)
	{
		return rgb(gray, gray, gray);
	}

// ==== class methods

	/**
	 * Constructor.<br>
	 * Creates a new white {@link FlockColor}.<br>
	 */
	public FlockColor()
	{
		this(0xFFFFF);
	}

	/**
	 * Constructor.<br>
	 * Copies the given {@link FlockColor} <code>color</code>.<br>
	 * 
	 * @param color the color to copy
	 */
	public FlockColor(FlockColor color)
	{
		this(color.red, color.green, color.blue, color.alpha);
	}

	/**
	 * Constructor.<br>
	 * Creates a new RGBA {@link FlockColor}.<br>
	 * 
	 * @param rgba the rgba value of this color
	 */
	public FlockColor(int rgba)
	{
		setAlpha((rgba >> 24) & 0xFF);
		setRed((rgba >> 16) & 0xFF);
		setGreen((rgba >> 8) & 0xFF);
		setBlue((rgba >> 0) & 0xFF);
	}

	/**
	 * Constructor.<br>
	 * Creates a new gray {@link FlockColor}.<br>
	 * The given gray value must be between 0 and 1<br>
	 * 
	 * @param gray the gray value of this color between 0 and 1
	 */
	public FlockColor(double gray)
	{
		this(gray, gray, gray);
	}

	/**
	 * Constructor.<br>
	 * Creates a new gray {@link FlockColor}.<br>
	 * The given color components must be between 0 and 1<br>
	 * 
	 * @param red   the red color component
	 * @param green the green color component
	 * @param blue  the blue color component
	 */
	public FlockColor(double red, double green, double blue)
	{
		this(red, green, blue, 1.0f);
	}

	/**
	 * Constructor.<br>
	 * Creates a new gray {@link FlockColor}.<br>
	 * The given color components must be between 0 and 1<br>
	 * 
	 * @param red   the red color component
	 * @param green the green color component
	 * @param blue  the blue color component
	 * @param alpha the alpha color component
	 */
	public FlockColor(double red, double green, double blue, double alpha)
	{
		set(red, green, blue, alpha);
	}

	/**
	 * Constructor.<br>
	 * Creates a new gray {@link FlockColor}.<br>
	 * The given color components must be between 0 and 255<br>
	 * 
	 * @param red   the red color component
	 * @param green the green color component
	 * @param blue  the blue color component
	 */
	public FlockColor(int red, int green, int blue)
	{
		this(red, green, blue, 255);
	}

	/**
	 * Constructor.<br>
	 * Creates a new gray {@link FlockColor}.<br>
	 * The given color components must be between 0 and 255<br>
	 * 
	 * @param red   the red color component
	 * @param green the green color component
	 * @param blue  the blue color component
	 * @param alpha the alpha color component
	 */
	public FlockColor(int red, int green, int blue, int alpha)
	{
		set(red, green, blue, alpha);
	}

	/**
	 * Sets the red, green and blue color components of this color.<br>
	 * The alpha value is unchanged!.<br>
	 * 
	 * @param red   the red color component
	 * @param green the green color component
	 * @param blue  the blue color component
	 */
	public void set(double red, double green, double blue)
	{
		set(red, green, blue, alpha);
	}

	/**
	 * Sets this color to the given hsb values.<br>
	 * 
	 * @param hue        the hue of the color 0-360
	 * @param saturation the saturation of the color 0-1
	 * @param brightness the brightness of the color 0-1
	 */
	public void setHSB(double hue, double saturation, double brightness)
	{
		hue = MathUtils.clamp(hue, 360, 0);
		saturation = MathUtils.clamp(saturation, 1, 0);
		brightness = MathUtils.clamp(brightness, 1, 0);
		set(HSBtoRGB(hue, saturation, brightness));
	}

	/**
	 * Sets this color to the colors given in the array <code>colorArray</code>. The
	 * array must be at least size 3 and the values must be in between the range of
	 * 0-1.
	 * 
	 * @param colorArray the array of color components to set
	 */
	public void set(double[] colorArray)
	{
		if (colorArray.length < 3)
			throw new IllegalArgumentException("Color array must be of at least size 3");
		set(colorArray[0], colorArray[1], colorArray[2], 1.0);
	}

	/**
	 * Sets this color to the colors given in the array <code>colorArray</code>. The
	 * array must be at least size 3 and the values must be in between the range of
	 * 0-255.
	 * 
	 * @param colorArray the array of color components to set
	 */
	public void set(int[] colorArray)
	{
		if (colorArray.length < 3)
			throw new IllegalArgumentException("Color array must be of at least size 3");
		set(colorArray[0], colorArray[1], colorArray[2], 255);
	}

	/**
	 * Sets the red, green, blue and alpha color components of this color to the
	 * given color <code>color</code><br>
	 * 
	 * @param color the color to set
	 */
	public void set(FlockColor color)
	{
		set(color.red, color.green, color.blue, color.alpha);
	}

	/**
	 * Sets the red, green, blue and alpha color components of this color.<br>
	 * 
	 * @param red   the red color component
	 * @param green the green color component
	 * @param blue  the blue color component
	 * @param alpha the alpha color component
	 */
	public void set(double red, double green, double blue, double alpha)
	{
		setRed(red);
		setGreen(green);
		setBlue(blue);
		setAlpha(alpha);
	}

	/**
	 * Sets the red, green and blue color components of this color.<br>
	 * Values must be in between range 0-255.<br>
	 * 
	 * @param red   the red color component
	 * @param green the green color component
	 * @param blue  the blue color component
	 */
	public void set(int red, int green, int blue)
	{
		set(red, green, blue, 255);
	}

	/**
	 * Sets the red, green, blue and alpha color components of this color.<br>
	 * Values must be in between range 0-255.<br>
	 * 
	 * @param red   the red color component
	 * @param green the green color component
	 * @param blue  the blue color component
	 * @param alpha the alpha color component
	 */
	public void set(int red, int green, int blue, int alpha)
	{
		setRed(red);
		setGreen(green);
		setBlue(blue);
		setAlpha(alpha);
	}

	/**
	 * Sets the red component of this color.<br>
	 * 
	 * @param red the red component to set
	 */
	public void setRed(double red)
	{ this.red = MathUtils.clamp(red, 1, 0); }

	/**
	 * Sets the green component of this color.<br>
	 * 
	 * @param green the green component to set
	 */
	public void setGreen(double green)
	{ this.green = MathUtils.clamp(green, 1, 0); }

	/**
	 * Sets the blue component of this color.<br>
	 * 
	 * @param blue the blue component to set
	 */
	public void setBlue(double blue)
	{ this.blue = MathUtils.clamp(blue, 1, 0); }

	/**
	 * Sets the alpha component of this color.<br>
	 * 
	 * @param alpha the alpha component to set
	 */
	public void setAlpha(double alpha)
	{ this.alpha = MathUtils.clamp(alpha, 1, 0); }

	/**
	 * Sets the red component of this color.<br>
	 * 
	 * @param red the red component to set
	 */
	public void setRed(int red)
	{ this.red = intToDouble(red); }

	/**
	 * Sets the green component of this color.<br>
	 * 
	 * @param green the green component to set
	 */
	public void setGreen(int green)
	{ this.green = intToDouble(green); }

	/**
	 * Sets the blue component of this color.<br>
	 * 
	 * @param blue the blue component to set
	 */
	public void setBlue(int blue)
	{ this.blue = intToDouble(blue); }

	/**
	 * Sets the alpha component of this color.<br>
	 * 
	 * @param alpha the alpha component to set
	 */
	public void setAlpha(int alpha)
	{ this.alpha = intToDouble(alpha); }

	/**
	 * @return the red component of this color
	 */
	public double getRed()
	{ return red; }

	/**
	 * @return the green component of this color
	 */
	public double getGreen()
	{ return green; }

	/**
	 * @return the blue component of this color
	 */
	public double getBlue()
	{ return blue; }

	/**
	 * @return the alpha component of this color
	 */
	public double getAlpha()
	{ return alpha; }

	/**
	 * @return the hue component of this color
	 */
	public double getHue()
	{ return RGBtoHSB(red, green, blue)[0]; }

	/**
	 * @return the saturation component of this color
	 */
	public double getSaturation()
	{ return RGBtoHSB(red, green, blue)[1]; }

	/**
	 * @return the brightness component of this color
	 */
	public double getBrightness()
	{ return RGBtoHSB(red, green, blue)[2]; }

	/**
	 * Sets the hue component of this color
	 * 
	 * @param hue the hue to set
	 */
	public void setHue(double hue)
	{
		double[] hsb = RGBtoHSB(red, green, blue);
		set(HSBtoRGB(hue, hsb[1], hsb[2]));
	}

	/**
	 * Sets the saturation component of this color
	 * 
	 * @param saturation the saturation to set
	 */
	public void setSaturation(double saturation)
	{
		double[] hsb = RGBtoHSB(red, green, blue);
		set(HSBtoRGB(hsb[0], saturation, hsb[2]));

	}

	/**
	 * @return a Color that is brighter than this Color
	 */
	public FlockColor brighter()
	{
		return brighter(FACTOR);
	}

	/**
	 * @return a Color that is darker than this Color
	 */
	public FlockColor darker()
	{
		return darker(FACTOR);
	}

	/**
	 * @return a Color that is more saturated than this Color
	 */
	public FlockColor saturate()
	{
		return saturate(FACTOR);
	}

	/**
	 * @return a Color that is less saturated than this Color
	 */
	public FlockColor desaturate()
	{
		return desaturate(FACTOR);
	}

	/**
	 * Returns a Color that is brighter than this Color
	 * 
	 * @param factor the color factor, must be between 0-1
	 * @return a Color that is brighter than this Color
	 */
	public FlockColor brighter(float factor)
	{
		factor = MathUtils.clamp(factor, 0, 1);
		return deriveColor(0, 1.0, 1.0 / factor, 1.0);
	}

	/**
	 * Returns a Color that is darker than this Color
	 * 
	 * @param factor the color factor, must be between 0-1
	 * @return a Color that is darker than this Color
	 */
	public FlockColor darker(float factor)
	{
		factor = MathUtils.clamp(factor, 0, 1);
		return deriveColor(0, 1.0, factor, 1.0);
	}

	/**
	 * Returns a Color that is more saturated than this Color
	 * 
	 * @param factor the color factor, must be between 0-1
	 * @return a Color that is more saturated than this Color
	 */
	public FlockColor saturate(float factor)
	{
		factor = MathUtils.clamp(factor, 0, 1);
		return deriveColor(0, 1.0 / factor, 1.0, 1.0);
	}

	/**
	 * Returns a Color that is less saturated than this Color
	 * 
	 * @param factor the color factor, must be between 0-1
	 * @return a Color that is less saturated than this Color
	 */
	public FlockColor desaturate(float factor)
	{
		factor = MathUtils.clamp(factor, 0, 1);
		return deriveColor(0, factor, 1.0, 1.0);
	}

	/**
	 * Interpolates between two colors
	 * 
	 * @param endValue the end color to interpolate
	 * @param t        the strength
	 * @return the newly interpolated color
	 */
	public FlockColor interpolate(FlockColor endValue, double t)
	{

		double ft = MathUtils.clamp(t, 1, 0);
		return new FlockColor(MathUtils.lerpd(red, endValue.red, ft),
				MathUtils.lerpd(green, endValue.green, ft), MathUtils.lerpd(blue, endValue.blue, ft));
	}

	/**
	 * Sets the brightness component of this color
	 * 
	 * @param brightness the brightness to set
	 */
	public void setBrightness(double brightness)
	{
		double[] hsb = RGBtoHSB(red, green, blue);
		set(HSBtoRGB(hsb[0], hsb[1], brightness));

	}

	/**
	 * @return a float array with r, g, b, a stored inside
	 */
	public float[] toFloatArray()
	{
		return new float[] { (float) getRed(), (float) getGreen(), (float) getBlue(), (float) getAlpha() };
	}

	/**
	 * @return a byte array with r, g, b, a stored inside
	 */
	public byte[] toBytearray()
	{
		byte[] arr = new byte[4];
		arr[0] = (byte) MathUtils.map(getRed(), 0, 1, 0, 255);
		arr[1] = (byte) MathUtils.map(getGreen(), 0, 1, 0, 255);
		arr[2] = (byte) MathUtils.map(getBlue(), 0, 1, 0, 255);
		arr[3] = (byte) MathUtils.map(getAlpha(), 0, 1, 0, 255);
		return arr;
	}

	/**
	 * @return a double array with r, g, b, a stored inside
	 */
	public double[] toDoubleArray()
	{
		double[] f = { getRed(), getGreen(), getBlue(), getAlpha() };
		return f;
	}

	/**
	 * Converts a HSB Color to RGB.
	 * 
	 * @param hue        the hue of the color to convert
	 * @param saturation the saturation of the color to convert
	 * @param brightness the brightness of the color to convert
	 * @return an array of double with size 3 representing RGB
	 */
	public static double[] HSBtoRGB(double hue, double saturation, double brightness)
	{
		// normalize the hue
		double normalizedHue = ((hue % 360) + 360) % 360;
		hue = normalizedHue / 360;

		double r = 0, g = 0, b = 0;
		if (saturation == 0)
		{
			r = g = b = brightness;
		} else
		{
			double h = (hue - Math.floor(hue)) * 6.0;
			double f = h - java.lang.Math.floor(h);
			double p = brightness * (1.0 - saturation);
			double q = brightness * (1.0 - saturation * f);
			double t = brightness * (1.0 - (saturation * (1.0 - f)));
			switch ((int) h)
			{
			case 0:
				r = brightness;
				g = t;
				b = p;
				break;
			case 1:
				r = q;
				g = brightness;
				b = p;
				break;
			case 2:
				r = p;
				g = brightness;
				b = t;
				break;
			case 3:
				r = p;
				g = q;
				b = brightness;
				break;
			case 4:
				r = t;
				g = p;
				b = brightness;
				break;
			case 5:
				r = brightness;
				g = p;
				b = q;
				break;
			}
		}
		double[] f = new double[3];
		f[0] = r;
		f[1] = g;
		f[2] = b;
		return f;
	}

	/**
	 * Converts a RGB Color to HSB.
	 * 
	 * @param r the red component of the color
	 * @param g the green component of the color
	 * @param b the blue component of the color
	 * @return an array of size 3 with the HSV values
	 */
	public static double[] RGBtoHSB(double r, double g, double b)
	{
		double hue, saturation, brightness;
		double[] hsbvals = new double[3];
		double cmax = (r > g) ? r : g;
		if (b > cmax)
			cmax = b;
		double cmin = (r < g) ? r : g;
		if (b < cmin)
			cmin = b;

		brightness = cmax;
		if (cmax != 0)
			saturation = (cmax - cmin) / cmax;
		else
			saturation = 0;

		if (saturation == 0)
		{
			hue = 0;
		} else
		{
			double redc = (cmax - r) / (cmax - cmin);
			double greenc = (cmax - g) / (cmax - cmin);
			double bluec = (cmax - b) / (cmax - cmin);
			if (r == cmax)
				hue = bluec - greenc;
			else if (g == cmax)
				hue = 2.0 + redc - bluec;
			else
				hue = 4.0 + greenc - redc;
			hue = hue / 6.0;
			if (hue < 0)
				hue = hue + 1.0;
		}
		hsbvals[0] = hue * 360;
		hsbvals[1] = saturation;
		hsbvals[2] = brightness;
		return hsbvals;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(alpha);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(blue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(green);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(red);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlockColor other = (FlockColor) obj;
		if (Double.doubleToLongBits(alpha) != Double.doubleToLongBits(other.alpha))
			return false;
		if (Double.doubleToLongBits(blue) != Double.doubleToLongBits(other.blue))
			return false;
		if (Double.doubleToLongBits(green) != Double.doubleToLongBits(other.green))
			return false;
		if (Double.doubleToLongBits(red) != Double.doubleToLongBits(other.red))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "FlockColor [" + red + ", " + green + ", " + blue + ", " + alpha + "]";
	}

	private FlockColor deriveColor(double hueShift, double saturationFactor, double brightnessFactor,
			double alphaFactor)
	{

		double[] hsb = RGBtoHSB(red, green, blue);

		/* Allow brightness increase of black color */
		double b = hsb[2];

		if (b == 0 && brightnessFactor > 1.0)
		{
			b = 0.05;
		}

		/* the tail "+ 360) % 360" solves shifts into negative numbers */
		double h = (((hsb[0] + hueShift) % 360) + 360) % 360;
		double s = Math.max(Math.min(hsb[1] * saturationFactor, 1.0), 0.0);
		b = Math.max(Math.min(b * brightnessFactor, 1.0), 0.0);
		double _a = Math.max(Math.min(alpha * alphaFactor, 1.0), 0.0);
		FlockColor newColor = new FlockColor();
		newColor.setHSB(h, s, b);
		newColor.setAlpha(_a);
		return newColor;
	}

	private static double intToDouble(int value)
	{
		value = MathUtils.clamp(value, 255, 0);
		return MathUtils.map(value, 0, 255, 0, 1);
	}
}
