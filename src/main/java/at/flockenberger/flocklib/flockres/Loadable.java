package at.flockenberger.flocklib.flockres;

import java.io.InputStream;

/**
 * <h1>Loadable</h1><br>
 * The loadable interface indicates that an object has/needs resources that need
 * to be loaded first.<br>
 * 
 * @author Florian Wagner
 *
 */
public interface Loadable
{ public void load(InputStream is); }
