package main.xml.factories.util;

/**
 * Getting document from util-file
 */


import java.io.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.validation.Schema;

public class XmlReader {

    private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/util/jaxp" +
                    "/properties/schemaLanguage";

    private static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLShema";

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
     *  Document of the util file
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
       // factory.setValidating(true);
                
        factory.setNamespaceAware(false);
        try {
           // factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
            //factory.setAttribute(W3C, PATH_XSD_SCHEMA2);
            //factory.setAttribute(JAXP_SCHEMA_SOURCE, PATH_XSD_SCHEMA2);
        } catch (IllegalArgumentException e) {
            
        }

      //  factory.setFeature("http://apache.org/util/features/validation/schema", true);
        builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new main.xml.factories.XmlErrorHandler());

        document = builder.parse(pathName);
    }

    public Document getDocument() {
        return document;
    }
}
