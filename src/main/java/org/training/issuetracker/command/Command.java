package org.training.issuetracker.command;

import java.io.IOException;


/**Interface for implementing by commands class.
 * @author Sergei_Doroshenko
 *
 */
public interface Command {
	/**Method execute command.
	 * @throws IOException If an input or output
	 * exception occurred
	 */
	void execute() throws IOException;
}
