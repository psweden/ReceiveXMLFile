package com.example.receivexmlfile.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.StringReader;

@Service
public class XmlValidatorService {

    @Autowired
    private ResourceLoader resourceLoader;

    public boolean validate(String xml) {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Resource resource = resourceLoader.getResource("classpath:schemas/employees.xsd");
            Schema schema = factory.newSchema(resource.getFile());
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new StringReader(xml)));
            return true;
        } catch (SAXException | IOException e) {
            // Handle exceptions
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
    }
}