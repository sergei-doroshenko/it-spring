package org.training.issuetracker.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.issuetracker.domain.DAO.PropDAO;

public class SearchRule {
	private String field;
	private String op;
	private String data;
	
	public SearchRule() { }
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
	
	public String getData() {
		
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "SearchRule [field=" + field + ", op=" + op + ", data=" + data
				+ "]";
	}

	public enum Op {eq, bw}
	
}
