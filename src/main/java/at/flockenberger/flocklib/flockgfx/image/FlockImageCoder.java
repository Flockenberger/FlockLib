package at.flockenberger.flocklib.flockgfx.image;

import java.io.InputStream;
import java.io.Writer;

public interface FlockImageCoder
{
	public void encode(FlockImage image, Writer writer);

	public FlockImage decode(InputStream stream);
}
