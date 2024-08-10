

package com.example.receivexmlfile.service;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.StringReader;
import org.xml.sax.InputSource;
import org.springframework.stereotype.Service;

@Service
public class XmlParserService {

    public String extractTagValue(String xml, String tagName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            Document doc = builder.parse(is);

            NodeList list = doc.getElementsByTagName(tagName);
            if (list.getLength() > 0) {
                Element element = (Element) list.item(0);
                return element.getTextContent();
            }
        } catch (Exception e) {
            System.out.println("Exception while extracting tag value: " + e.getMessage());
        }
        return null;
    }
}