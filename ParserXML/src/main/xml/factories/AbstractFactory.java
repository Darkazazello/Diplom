package main.xml.factories;

import main.xml.factories.util.XmlReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Abstract factory
 */
public abstract class AbstractFactory {
    /**
     * Xml reader
     */
    protected XmlReader xmlReader;
    /**
     * Path to util file
     */
    protected String pathName;
    /**
     * Document with settings of entities
     */
    protected Document document;

    public AbstractFactory(String pathName) throws IOException, SAXException,
             ParserConfigurationException {
        this.pathName = pathName;
        xmlReader = new XmlReader(pathName);
        document = xmlReader.getDocument();
    }

    /**
     * Create object from util file
     */
    protected abstract void createObjectFromXml() throws ParserConfigurationException,
            IOException, SAXException;

    /**
     * Get child element of parent
     * @param parent parent element
     * @param child  name of child element
     * @return child element
     */
    protected Element getChild(Element parent, String child) {
        return (Element) parent.getElementsByTagName(child).item(0);
    }

    /**
     * Get value child of parent
     * @param parent parent element
     * @param child name of child element
     * @return value of child element
     */
    protected String getChildValue(Element parent, String child) {
        return getChild(parent, child).getFirstChild().getNodeValue();
    }

    /**
     * Get children of the parent
     * @param parent parent element
     * @param child  name of child element
     * @return list of the children
     */
    protected NodeList getChildren(Element parent, String child) {
        return parent.getElementsByTagName(child);
    }
}
