package org.training.issuetracker.exceptions;

/**Class represent exception that appear, when validation.
 * Process occurred error.
 * @author Sergei_Doroshenko
 *
 */
public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	/**General purpose constructor.
	 * @param message which contains error cause.
	 */
	public ValidationException(String message) {
    	super(message);
    }

	/**General purpose constructor.
     * @param cause - Throwable object.
     */
    public ValidationException(Throwable cause) {
    	super(cause);
    }

    /**General purpose constructor.
     * @param message which contains error cause.
     * @param cause - Throwable object.
     */
    public ValidationException(String message, Throwable cause) {
    	super(message, cause);
    }

}