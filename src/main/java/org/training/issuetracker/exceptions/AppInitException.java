package org.training.issuetracker.exceptions;

/**Exception appear when constant map don't init.
 * @author Sergei_Doroshenko
 *
 */

public class AppInitException extends RuntimeException {
	/**
	 * Serialized Id
	 */
	private static final long serialVersionUID = -3450736619065073467L;

	/**
     * Default contractor.
     */
    public AppInitException() { }

    /**General purpose constructor.
     * @param message which contains error cause.
     */
    public AppInitException(String message) {
    	super(message);
    }

    /**General purpose constructor.
     * @param cause - Throwable object.
     */
    public AppInitException(Throwable cause) {
    	super(cause);
    }

    /**General purpose constructor.
     * @param message which contains error cause.
     * @param cause - Throwable object.
     */
    public AppInitException(String message, Throwable cause) {
    	super(message, cause);
    }
}
