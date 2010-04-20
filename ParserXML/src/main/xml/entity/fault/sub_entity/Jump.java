package main.xml.entity.fault.sub_entity;

import main.xml.entity.OperationTerm;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: aza
 * Date: 28-Jan-2010
 * Time: 21:13:26
 * To change this template use File | Settings | File Templates.
 */
public class Jump {
    private StateFault startState;
    private StateFault finishState;
    private ArrayList<Operation> operations;

    public StateFault getStartState() {
        return startState;
    }

    public void setStartState(StateFault startState) {
        this.startState = startState;
    }

    public StateFault getFinishState() {
        return finishState;
    }

    public void setFinishState(StateFault finishState) {
        this.finishState = finishState;
    }

    public ArrayList<Operation> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<Operation> operations) {
        this.operations = operations;
    }
}
