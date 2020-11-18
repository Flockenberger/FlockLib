package at.flockenberger.flocklib.flockgfx.image;

/**
 * <h1>PixelComponent</h1><br>
 * 
 * @author Florian Wagner
 *
 */
public enum PixelComponent
{
	/**
	 * the red component of a pixel
	 */
	RED(0),

	/**
	 * the green component of a pixel
	 */
	GREEN(1),

	/**
	 * the blue component of a pixel
	 */
	BLUE(2),

	/**
	 * the alpha component of a pixel
	 */
	ALPHA(3);

	int offset;

	PixelComponent(int off)
	{
		this.offset = off;
	}

	/**
	 * @return the offset of this component in the pixel data
	 */
	public int getOffset()
	{ return this.offset; }
}
