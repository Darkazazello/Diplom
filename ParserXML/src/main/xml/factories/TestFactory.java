package main.xml.factories;

/**
 * Create march tests
 */
import java.util.*;
import java.io.*;
import javax.xml.parsers.*;

import main.xml.entity.OperationTerm;
import main.xml.entity.tests.step_elements.SimpleMarchTest;
import main.xml.entity.tests.step_elements.normal_element.Direction;
import main.xml.entity.tests.step_elements.simple_element.Command;
import main.xml.entity.tests.step_elements.simple_element.SimpleMarchElement;
import main.xml.factories.AbstractFactory;
import main.xml.factories.UnsuppotedErrorException;
import org.xml.sax.*;
import org.w3c.dom.*;


public class TestFactory extends AbstractFactory {

    private final static String TEST_NAME_NODE = "test";
    private final static String TEST_NAME_ATTRIBUTE = "name";
    private final static String TEST_ELEMENT_NODE_NAME = "part";
    private final static String ORDER_NAME_ATTRIBUTE = "order";
    private final static String COMMAND_NAME_NODE = "command";
    private final static String OPERATION_ATTRIBUTE_NAME = "operation";
    private final static String VALUE_ATTRIBUTE_NAME = "value";

    private ArrayList <SimpleMarchTest> tests;

    public TestFactory(String pathName) throws IOException, UnsuppotedErrorException,
            SAXException, ParserConfigurationException {
        super(pathName);
        tests = new ArrayList<SimpleMarchTest>();
        createObjectFromXml();
    }

    /**
     * Create march tests from document
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws UnsuppotedErrorException
     */
    protected void createObjectFromXml() throws ParserConfigurationException, IOException,
            SAXException {

        Element root = document.getDocumentElement();
        NodeList testNodes = root.getElementsByTagName(TEST_NAME_NODE);
        for (int i = 0; i < testNodes.getLength(); i++)
        {
            Element testNode = (Element) testNodes.item(i);
            SimpleMarchTest test = new SimpleMarchTest();
            test.setName(getChildValue(testNode, TEST_NAME_ATTRIBUTE));
            NodeList partNodes = getChildren(testNode, TEST_ELEMENT_NODE_NAME);
            for (int j = 0; j < partNodes.getLength(); j++)
            {
                Element partNode = (Element) partNodes.item(j);
                SimpleMarchElement part = new SimpleMarchElement();
                part.setDirection(getDirectionByString(partNode.getAttribute(ORDER_NAME_ATTRIBUTE)));
                NodeList commandNodes = getChildren(partNode, COMMAND_NAME_NODE);
                for (int k = 0; k < commandNodes.getLength(); k++)
                {
                    Element commandNode = (Element) commandNodes.item(k);
                    Command command = new Command();
                    command.setOperation(OperationTerm.getOperationTermByString(
                                commandNode.getAttribute(OPERATION_ATTRIBUTE_NAME)));
                    command.setValue(commandNode.getAttribute(VALUE_ATTRIBUTE_NAME));
                    part.addCommand(command);
                }
                test.addPart(part);
            }
            tests.add(test);
        }
    }

    private Direction getDirectionByString(String data) {
        if (data == null || data.trim().equals("")) {
            return null;
        } else if (data.equalsIgnoreCase("either")) {
            return Direction.Unspecified;
        } else if (data.equalsIgnoreCase("up")) {
            return Direction.Increasing;
        } else if (data.equalsIgnoreCase("down")) {
            return Direction.Decreasing;
        } else {
            return null;
        }
    }

     public ArrayList<SimpleMarchTest> getTests() {
        return tests;
    }
}
