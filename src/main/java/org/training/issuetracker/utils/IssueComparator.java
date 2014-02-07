package org.training.issuetracker.utils;

import java.util.Comparator;

import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.User;

public class IssueComparator implements Comparator<Issue> {
	private User user;

	public IssueComparator() { }

	public IssueComparator(User user) {
		super();
		this.user = user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int compare(Issue o1, Issue o2) {
		if (user.getId() == o1.getAssignee().getId()) {
			return -1;
		}
		return 1;
	}

}
