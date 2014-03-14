package org.training.issuetracker.utils;

import java.util.List;

public class SearchFilter {
	private String groupOp;
	private List<SearchRule> rules;
	
	public SearchFilter() { }
	
	public SearchFilter(String groupOp, List<SearchRule> rules) {
		super();
		this.groupOp = groupOp;
		this.rules = rules;
	}

	public String getGroupOp() {
		return groupOp;
	}

	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}

	public List<SearchRule> getRules() {
		return rules;
	}

	public void setRules(List<SearchRule> rules) {
		this.rules = rules;
	}
	
	@Override
	public String toString() {
		return "SearchFilter [groupOp=" + groupOp + ", rules=" + rules + "]";
	}



	public enum GroupOp {AND, OR}
	//filters:{"groupOp":"AND",
}
