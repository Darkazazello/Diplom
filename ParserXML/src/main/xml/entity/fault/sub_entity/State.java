package main.xml.entity.fault.sub_entity;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: aza
 * Date: 28-Jan-2010
 * Time: 20:59:21
 * To change this template use File | Settings | File Templates.
 */
public class State {

    protected final String stateValue;

    protected final ArrayList <JumpOperation> jumpOperation = new ArrayList<JumpOperation>();

    public State(String stateValue) {
        this.stateValue = stateValue;
    }

    public String getStateValue() {
        return stateValue;
    }

    public void addJumpOperation(JumpOperation operation) {
        jumpOperation.add(operation);
    }

    public ArrayList<JumpOperation> getJumpOperations() {
        return jumpOperation;
    }
}
