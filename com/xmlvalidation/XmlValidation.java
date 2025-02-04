package com.xmlvalidation;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class XmlValidation {
    public static void main(String[] args) {

        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("pac008_sample.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File("pac008_sample.xml")));
            System.out.println("XML is valid.");
        } catch (Exception e) {
            System.out.println("XML is not valid. Error: " + e.getMessage());
        }
    }
}