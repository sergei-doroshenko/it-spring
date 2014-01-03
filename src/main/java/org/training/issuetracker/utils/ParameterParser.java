package org.training.issuetracker.utils;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;

import org.training.issuetracker.exceptions.ParameterNotFoundException;

public class ParameterParser {
	private ServletRequest request;

	public ParameterParser() {
	}

	public ParameterParser(ServletRequest request) {
		super();
		this.request = request;
	}
	
	public String getStringParameter(String name) throws ParameterNotFoundException {
		String[] values = null;
		String param = null;
		try{
			values = request.getParameterValues(name);
		} catch (NullPointerException e){
			throw new ParameterNotFoundException(e.getMessage());
		}
		
		if(values == null){
			throw new ParameterNotFoundException(name + " not found");
		} else if (values[0].length() == 0) {
			throw new ParameterNotFoundException(name + " not found");
		} else if (values[0].isEmpty()) {
			throw new ParameterNotFoundException(name + " not found");
		} else {
			param = values[0];
		}
		System.out.println(">" + param + "<");
		return param;
	}
	
	public int getIntParameter(String name) 
			throws ParameterNotFoundException, NumberFormatException{
		return Integer.parseInt(getStringParameter(name));
		
	}
	
	public Map<Integer,Integer> getMapParameter(String idParameter) 
									throws NumberFormatException, ParameterNotFoundException{
		
		String[] ids = request.getParameterValues(idParameter);
			
		Map<Integer, Integer> quantitiesParam = new HashMap<Integer, Integer>();
		
		if(ids != null){
			for(int i = 0; i < ids.length; i++){
				String idStr = ids[i];
				int id = Integer.parseInt(idStr);
				int quantity = getIntParameter(idStr);
				System.out.println("Putting into map: key = " + id + "; value = " + quantity);
				quantitiesParam.put(id, quantity);
			}
		}
				
		return quantitiesParam;
	}
	
	public Map<Integer,Integer> getIdQuantityParam(String idParameter, String quantityParameter) 
									throws NumberFormatException, ParameterNotFoundException{

		String[] ids = request.getParameterValues(idParameter);
		String[] quantities = request.getParameterValues(quantityParameter);		
		
		Map<Integer, Integer> quantitiesParam = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < ids.length; i++){
			int id = Integer.parseInt(ids[i]);
			int quantity = Integer.parseInt(quantities[i]);
			System.out.println("Putting into map: key = " + id + "; value = " + quantity);
			quantitiesParam.put(id, quantity);
		}
		
		return quantitiesParam;
	}
}
