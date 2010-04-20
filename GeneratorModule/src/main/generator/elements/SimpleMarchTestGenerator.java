package main.generator.elements;

import main.xml.entity.tests.step_elements.SimpleMarchTest;
import main.xml.entity.tests.step_elements.simple_element.SimpleMarchElement;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: s.sheluhin
 * Date: 01.03.2010
 * Time: 20:36:14
 * To change this template use File | Settings | File Templates.
 */
public class SimpleMarchTestGenerator implements IElementGenerator{

    private SimpleMarchTest simpleMarchTest;

    private final static int MEMORY_SIZE = 8;

    public SimpleMarchTestGenerator(SimpleMarchTest simpleMarchTest) {
        this.simpleMarchTest = simpleMarchTest;
    }

    public String generateVHDLCode() {
        StringBuilder code = new StringBuilder();
        ArrayList<SimpleMarchElement> elements = simpleMarchTest.getSimpleMarchElements();
        for (int i = 0; i < elements.size(); i++)
        {
            SimpleMarchElement element = elements.get(i);
//            if ()
        }
        return "";
    }
}
