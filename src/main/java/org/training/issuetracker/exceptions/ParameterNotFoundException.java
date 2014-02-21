package org.training.issuetracker.exceptions;

/**This class represent error, which may cause.
 * when needed parameter is absent.
 * @author Sergei_Doroshenko
 */
public class ParameterNotFoundException extends Exception {
	/**
     * Serialization id.
     */
	private static final long serialVersionUID = 1L;

	/**Constructor.
	 * @param message with error information
	 */
	public ParameterNotFoundException(String message) {
		super(message);
	}
}
