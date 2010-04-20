package main.xml.entity.tests.step_elements.simple_element;

import main.xml.entity.OperationTerm;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 05.04.2010
 * Time: 17:04:15
 * To change this template use File | Settings | File Templates.
 */
public class Command {
    private OperationTerm operation;
    private String value;

    public OperationTerm getOperation() {
        return operation;
    }

    public void setOperation(OperationTerm operation) {
        this.operation = operation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
