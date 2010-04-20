package main.xml.entity.tests.step_elements;

import main.xml.entity.tests.step_elements.simple_element.SimpleMarchElement;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: s.sheluhin
 * Date: 01.03.2010
 * Time: 20:38:06
 * To change this template use File | Settings | File Templates.
 */
public class SimpleMarchTest {
    private ArrayList<SimpleMarchElement> simpleMarchElements = new ArrayList<SimpleMarchElement>();
    private String name;

    public void addPart(SimpleMarchElement part) {
        simpleMarchElements.add(part);
    }

    public ArrayList<SimpleMarchElement> getSimpleMarchElements() {
        return simpleMarchElements;
    }

    public void setSimpleMarchElements(ArrayList<SimpleMarchElement> simpleMarchElements) {
        this.simpleMarchElements = simpleMarchElements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

