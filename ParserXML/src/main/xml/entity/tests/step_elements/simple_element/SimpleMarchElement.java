package main.xml.entity.tests.step_elements.simple_element;

import main.xml.entity.OperationTerm;
import main.xml.entity.fault.sub_entity.Operation;
import main.xml.entity.tests.step_elements.normal_element.Direction;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: s.sheluhin
 * Date: 01.03.2010
 * Time: 20:28:09
 * To change this template use File | Settings | File Templates.
 */
public class SimpleMarchElement {
    private ArrayList<Command> operations = new ArrayList<Command>();
    private Direction direction;

    public void addCommand(Command command) {
        operations.add(command);
    }


    public ArrayList <Command> getCommands() {
        return operations;
    }


    public void setOperation(ArrayList <Command> operation) {
        this.operations = operation;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
