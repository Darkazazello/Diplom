package main.xml.factories;

/**
 * Getting document from xml-file
 */


import java.io.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;


public class XmlReader {

    private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp" +
                    "/properties/schemaLanguage";

    private static final String JAXP_SCHEMA_SOURCE = "http://www.w3.org/2001/XMLSchema";
    private static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLShema";
    private static  final String W3C = "http://java.sun.com/xml/jaxp/properties/schemaSource";

//    private static final String PATH_XSD_SCHEMA1 = "d:\\main\\test.xsd";//Main.class.getResource("data/tests.xsd").toString();
//    private static final String PATH_XSD_SCHEMA2 = "d:\\main\\faults.xsd";//Main.class.getResource("data/tests.xsd").toString();


    //private static final String PATH_XSD_SCHEMA2 = Main.class.getResource("data/faults.xsd").toString();

    /**
     * Path to Xml file
     */
    private String pathName;

    /**
     * Factory for document
     */
    private DocumentBuilderFactory factory;

    /**
     * Builder of the document
     */
    private DocumentBuilder builder;

    /**
     *  Document of the xml file
     */
    private Document document;


    public XmlReader(String pathName) throws IOException,
            SAXException, ParserConfigurationException {

        this.pathName = pathName;
        initialXmlResourse();
    }

    /**
     * Initial parser
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    private void initialXmlResourse() throws ParserConfigurationException,
            IOException, SAXException{

        factory = DocumentBuilderFactory.newInstance();

        factory.setValidating(true);
                
        factory.setNamespaceAware(true);
        try {
            factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
            //factory.setAttribute(W3C, PATH_XSD_SCHEMA2);
            //factory.setAttribute(JAXP_SCHEMA_SOURCE, PATH_XSD_SCHEMA2);
        } catch (IllegalArgumentException e) {
            
        }

        factory.setFeature("http://apache.org/xml/features/validation/schema", true);
        builder = factory.newDocumentBuilder();
    //    builder.setErrorHandler(new XmlErrorHandler());

        document = builder.parse(pathName);
    }

    public Document getDocument() {
        return document;
    }
}
