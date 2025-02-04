package com.xmlmodifier;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class XmlModifier {
    public static void main(String[] args) {
        try {
            // Load and parse the XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("pac008_sample.xml"));

            // Find the OrgnlTxRef element
            NodeList orgnlTxRefList = doc.getElementsByTagName("OrgnlTxRef");
            if (orgnlTxRefList.getLength() > 0) {
                Element orgnlTxRef = (Element) orgnlTxRefList.item(0);

                // Create new elements
                Element cdtrAcct = doc.createElement("CdtrAcct");
                Element id = doc.createElement("Id");
                Element othr = doc.createElement("Othr");
                Element innerId = doc.createElement("Id");

                // Build the structure
                othr.appendChild(innerId);
                id.appendChild(othr);
                cdtrAcct.appendChild(id);

                // Add to OrgnlTxRef
                orgnlTxRef.appendChild(cdtrAcct);

                // Write the modified XML back to file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                // Add indentation to output
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("modified_pac008_sample.xml"));
                transformer.transform(source, result);

                System.out.println("XML file has been modified successfully!");
            } else {
                System.out.println("OrgnlTxRef element not found in the XML file.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
