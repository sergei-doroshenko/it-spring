package org.training.issuetracker.command;

import java.io.IOException;

import javax.servlet.ServletException;


/**Interface for implementing by commands class.
 * @author Sergei_Doroshenko
 *
 */
public interface Command {
	/**Method execute command.
	 * @throws IOException If an input or output
	 * exception occurred
	 * @throws ServletException
	 */
	void execute() throws IOException, ServletException;
}
