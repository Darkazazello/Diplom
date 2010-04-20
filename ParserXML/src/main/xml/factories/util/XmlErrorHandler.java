package main.factories.xml;

/**
 *   
 */

import org.xml.sax.*;

public class XmlErrorHandler implements ErrorHandler{

    public void warning(SAXParseException exception) throws SAXException {
        System.out.println("Warning: " + exception.getSystemId());
    }

    public void error(SAXParseException exception) throws SAXException {
//       System.out.println("Error: " + exception.getSystemId() + " " + "in the line: "
//                + exception.getLineNumber());
//
     exception.printStackTrace();
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println("Fatal error: " + exception.getSystemId());
    }
}
