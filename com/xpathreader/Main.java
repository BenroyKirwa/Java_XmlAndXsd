package com.xpathreader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

public class Main {
    public static void main(String[] args) {
        try {
            // Create XPath instance
            XPath xPath = XPathFactory.newInstance().newXPath();

            // Create instance of XpathReader
            XpathReader xpaths = new XpathReader();

            // Specify the path to your XML file
            // Option 1: Using relative path (if XML is in project root)
            String xmlFilePath = "pac008_sample.xml";

            // Option 2: Using absolute path (replace with your actual path)
            // String xmlFilePath = "C:/Your/Path/To/pac008_sample.xml";

            System.out.println("Starting XML parsing...");
            System.out.println("============================");

            // Call the fieldType method
            xpaths.fieldType(xPath, xmlFilePath);

        } catch (Exception e) {
            System.out.println("Error in main: " + e.getMessage());
            e.printStackTrace();
        }
    }
}