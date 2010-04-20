package main.xml.entity.fault.sub_entity;

/**
 * Created by IntelliJ IDEA.
 * User: aza
 * Date: 28-Jan-2010
 * Time: 21:13:26
 * To change this template use File | Settings | File Templates.
 */
public class JumpOperation {
    private final String operation;
    private final int position;
    private final String value;

    private final State nextState;

    public JumpOperation(String operation, int position, String value, State nextState) {
        this.operation = operation;
        this.position = position;
        this.value = value;
        this.nextState = nextState;
    }

    public String getOperation() {
        return operation;
    }


    public int getPosition() {
        return position;
    }


    public String getValue() {
        return value;
    }

    public State getNextState() {
        return nextState;
    }
}
