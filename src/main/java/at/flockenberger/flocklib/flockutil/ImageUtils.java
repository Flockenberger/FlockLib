package at.flockenberger.flocklib.flockutil;

import java.awt.image.BufferedImage;

import at.flockenberger.flocklib.flockgfx.image.FlockImage;

/**
 * Image utilities.
 * 
 * @author Florian Wagner
 *
 */
public final class ImageUtils
{

	/**
	 * Converts a {@link FlockImage} into a awt {@link BufferedImage}.<br>
	 * 
	 * @param image the image to convert
	 * @return the converted {@link BufferedImage}
	 */
	public static BufferedImage flockToBuffered(FlockImage image)
	{
		ObjectUtils.isNullThrow(image);
		BufferedImage bImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int y = 0; y < image.getHeight(); y++)
			for (int x = 0; x < image.getWidth(); x++)
			{
				bImage.setRGB(x, y, image.getPixelRGBA(x, y));
			}
		return bImage;
	}

	/**
	 * Converts a {@link BufferedImage} into a {@link FlockImage}.<br>
	 * 
	 * @param image the image to convert
	 * @return the converted {@link FlockImage}
	 */
	public static FlockImage bufferedToFlock(BufferedImage image)
	{
		ObjectUtils.isNullThrow(image);
		FlockImage fImage = new FlockImage(image.getWidth(), image.getHeight());
		for (int y = 0; y < image.getHeight(); y++)
			for (int x = 0; x < image.getWidth(); x++)
			{
				fImage.setPixelRGBA(x, y, image.getRGB(x, y));
			}
		return fImage;
	}
}