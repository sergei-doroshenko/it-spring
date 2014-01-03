package org.training.issuetracker.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.training.issuetracker.exceptions.ValidationException;
import org.xml.sax.SAXException;

public class XMLValidator {

	public XMLValidator() {
	}
	
	public boolean validateXML (String schemaUrl, String xmlUrl) throws ValidationException {
		boolean result = false;
		Source schemaFile = new StreamSource(new File(schemaUrl));
		Source xmlFile = new StreamSource(new File(xmlUrl));
        
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema;
        
		try {
			schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);
			result = true;
		} catch (SAXException | IOException e) {
			throw new ValidationException (e);
		}
		
		return result;
	}
}
