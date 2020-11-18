package at.flockenberger.flocklib.flockinterfaces;

/**
 * <h1>IInitializable</h1><br>
 * Marks any object that it has an {@link #init()} function which should be
 * called to initialize said object.<br>
 * 
 * @author Florian Wagner
 *
 */
public interface IInitializable
{
	/**
	 * Initializes the object.<br>
	 * 
	 * @return true if the initialization was successfull otherwise false
	 */
	public boolean init();

}
