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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + ((op == null) ? 0 : op.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchRule other = (SearchRule) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (op == null) {
			if (other.op != null)
				return false;
		} else if (!op.equals(other.op))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SearchRule [field=" + field + ", op=" + op + ", data=" + data
				+ "]";
	}

	public enum Op {eq, bw}
	
}
