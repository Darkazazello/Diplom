package main.virtual_model;

import main.xml.entity.fault.Fault;
import main.xml.entity.fault.sub_entity.JumpOperation;
import main.xml.entity.fault.sub_entity.State;

import java.util.ArrayList;

/**
 * Simulator simple cell of memory
 */
public class Cell implements ICell{

    private Fault stateGraph;
    private State currentState;

    public void doOperationWrite(String value) {
        ArrayList<JumpOperation> operations = currentState.getJumpOperations();
        boolean isValueDetermine = false;
        for (JumpOperation operation : operations)
        {
            if (operation.getValue().equals(value)) {
                isValueDetermine = true;
                currentState = operation.getNextState();
            }
        }
        if (!isValueDetermine) {
            currentState = new XState("X");
        }
    }

    public String doOperationRead() {
        return currentState.getStateValue();
    }

    public Cell(Fault stateGraph) {
        this.stateGraph = stateGraph;
    }

    public Fault getStateGraph() {
        return stateGraph;
    }

    public void setStateGraph(Fault stateGraph) {
        this.stateGraph = stateGraph;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
}
