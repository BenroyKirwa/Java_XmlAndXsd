package com.xpathreader;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import java.io.File;
import java.io.FileReader;

public class XpathReader {
    public void fieldType(XPath xPath, String xmlFilePath) {
        try {
            File file = new File(xmlFilePath);
            if (!file.exists()) {
                System.out.println("File does not exist: " + xmlFilePath);
                return;
            }

            String xpath = "//*";  // Select all elements
            NodeList fieldTypes = (NodeList) xPath.evaluate(
                    xpath,
                    new InputSource(new FileReader(xmlFilePath)),
                    XPathConstants.NODESET
            );

            System.out.println("Reading all XML elements:");
            System.out.println("=========================");

            for (int i = 0; i < fieldTypes.getLength(); i++) {
                Node node = fieldTypes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Check if the element has direct text content
                    NodeList children = element.getChildNodes();
                    if (children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE) {
                        String value = element.getTextContent().trim();
                        if (!value.isEmpty()) {
                            // Print element name and its value
                            System.out.println(getFullPath(element) + ": " + value);
                        }
                    }

                    // Check for attributes
                    if (element.hasAttributes()) {
                        for (int j = 0; j < element.getAttributes().getLength(); j++) {
                            Node attr = element.getAttributes().item(j);
                            System.out.println(getFullPath(element) + "@" + attr.getNodeName() + ": " + attr.getNodeValue());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String getFullPath(Node node) {
        StringBuilder path = new StringBuilder();
        while (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
            path.insert(0, "/" + node.getNodeName());
            node = node.getParentNode();
        }
        return path.toString();
    }
}