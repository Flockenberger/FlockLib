package at.flockenberger.flocklib.flockinterfaces;

/**
 * <h1>IFreeable</h1><br>
 * Marks that an object has resources that need to be freed at some point.<br>
 * While the GC in Java handles the memory pretty well, in some circumstances in
 * can be usefull to manually free data (in any shape or form).<br>
 * As well as for native dependand libraries where memory does not get handled
 * by the Java GC.
 * 
 * @author Florian Wagner
 *
 */
public interface IFreeable
{
	/**
	 * Frees any resources.<br>
	 * 
	 * @return true if successfull otherwise false
	 */
	public boolean free();

}
