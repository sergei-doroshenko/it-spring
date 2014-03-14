package org.training.issuetracker.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.PropertyType;
import org.training.issuetracker.exceptions.DaoException;

@Service
public class SearchRule {
	private String field;
	private String op;
	private String data;
	
	@Autowired
	private PropDAO propDAO;
	
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
	
//	public Object getData() throws NumberFormatException, DaoException {
//		Object value = data;
//		
//		if (field.equals("id")){
//			value = Long.parseLong(data);
//		}
//		
//		if (field.equals("status")){
//			value = propDAO.getProp(PropertyType.STATUS, Long.parseLong(data));
//		}
//		return value;
//	}

	public void setData(String data) {
		this.data = data;
	}
	
	public PropDAO getPropDAO() {
		return propDAO;
	}

	public void setPropDAO(PropDAO propDAO) {
		this.propDAO = propDAO;
	}

	@Override
	public String toString() {
		return "SearchRule [field=" + field + ", op=" + op + ", data=" + data
				+ "]";
	}

	public enum Op {eq, bw}
	
//	"rules":[
	//{"field":"id","op":"eq","data":"3"},
	//{"field":"createBy","op":"bw","data":"Tim"}]}
}
