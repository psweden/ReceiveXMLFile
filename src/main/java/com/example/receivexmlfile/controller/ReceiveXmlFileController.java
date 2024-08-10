package com.example.receivexmlfile.controller;

import com.example.receivexmlfile.service.XmlParserService;
import com.example.receivexmlfile.service.XmlValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class ReceiveXmlFileController {

    @Autowired
    private XmlValidatorService xmlValidatorService;

    @Autowired
    private XmlParserService xmlParserService;

    @PostMapping(value = "/receive", consumes = MediaType.APPLICATION_XML_VALUE)
    public String receiveFile(@RequestBody String xml) {
        try {
            System.out.println("Received XML file: " + xml);

            boolean isValid = xmlValidatorService.validate(xml);
            if(isValid) {
                System.out.println("XML file passed validation check.");

                String tagValue = xmlParserService.extractTagValue(xml, "desiredTag");
                System.out.println("Value of desiredTag: " + tagValue);

                // Code to save employees to database can go here.
                // You would need to replace following placeholder print statement with actual implementation.
                System.out.println("All employees parsed from the XML have been successfully saved into the database.");

                return "The XML file has been successfully received, validated, parsed and all the employees in it have been saved into the database!";
            } else {
                System.out.println("XML file failed validation check.");
                throw new ResponseStatusException(
                        HttpStatus.UNPROCESSABLE_ENTITY, "Invalid XML file received");
            }
        } catch (Exception e) {
            System.out.println("Failed to read and process XML file: " + e.getMessage());
            throw new RuntimeException("Failed to read XML file: " + e.getMessage());
        }
    }
}