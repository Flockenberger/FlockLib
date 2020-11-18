package at.flockenberger.flocklib.flockres;

import java.io.InputStream;

import at.flockenberger.flocklib.flockutil.FileUtils;

public class FlockStringLoader implements FlockResourceLoader<String>
{

	@Override
	public String loadFlockResource(InputStream is)
	{
		return FileUtils.readFile(is);
	}

}
