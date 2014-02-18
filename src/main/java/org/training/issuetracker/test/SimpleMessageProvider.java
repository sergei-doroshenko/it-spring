package org.training.issuetracker.test;

public class SimpleMessageProvider implements MessageProvider {
	
	private MessageResolver resolver;

	@Override
	public String getTitle() {
		return resolver.getTitle();
	}

	@Override
	public String getHelloMessage() {
		return resolver.getHelloMessage();
	}

	public void setResolver(MessageResolver resolver) {
		this.resolver = resolver;
	}
	
}
