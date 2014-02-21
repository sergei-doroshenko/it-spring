package org.training.issuetracker.exceptions;

/**Class represents DAO Exception.
 * Which may cause when object can't get from
 * database or file
 * @author Sergei_Doroshenko
 */
public class DaoException extends Exception {

    /**
     * Serialization id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default contractor.
     */
    public DaoException() { }

    /**General purpose constructor.
     * @param message which contains error cause.
     */
    public DaoException(String message) {
    	super(message);
    }

    /**General purpose constructor.
     * @param cause - Throwable object.
     */
    public DaoException(Throwable cause) {
    	super(cause);
    }

    /**General purpose constructor.
     * @param message which contains error cause.
     * @param cause - Throwable object.
     */
    public DaoException(String message, Throwable cause) {
    	super(message, cause);
    }
}
