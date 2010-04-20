package main.xml.factories;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.parsers.*;

import main.xml.entity.fault.sub_entity.JumpOperation;
import main.xml.entity.fault.Fault;
import main.xml.entity.fault.sub_entity.State;
import org.xml.sax.SAXException;

import org.w3c.dom.*;


/**
 * Factory for creating suppoted faults
 */

public class FaultFactory extends AbstractFactory {

    private final static String FAULT_NAME_ATTRIBUTE = "name";
    private final static String STATE_VALUE_ATTRIBUTE = "stateValue";
    private final static String OPERATION_TYPE_ATTRIBUTE = "operation";
    private final static String POSITION_OPERATION_ATTRIBUTE = "position";
    private final static String VALUE_OPERATION_ATTRIBUTE = "value";

//    private final static String

    /**
     * Supported faults
     */
    private ArrayList<Fault> faults;

    public FaultFactory(String pathName) throws IOException, SAXException,
            ParserConfigurationException {
        super(pathName);
        faults = new ArrayList<Fault>();
        createObjectFromXml();
    }

    /**
     * Create suppoted error from document
     *
     * @throws
     */
    protected void createObjectFromXml() throws ParserConfigurationException,
            IOException, SAXException {
        //faults = new ArrayList<Fault>();
        Element root = document.getDocumentElement();
        //Node faultsNode = root.getChildNodes().item(0);
        NodeList faultNodes = root.getChildNodes();
        for (int i = 0; i < faultNodes.getLength(); i++) {
            Element faultElement = (Element) faultNodes.item(i);
            String faultName = faultElement.getAttribute(FAULT_NAME_ATTRIBUTE);
            Fault fault = new Fault(faultName);
            NodeList stateNodes = faultElement.getChildNodes();
            ArrayList<State> buffer = new ArrayList<State>();
            /**
             * Todo may writing optimization for saving graph of state
             */
            for (int j = 0; j < stateNodes.getLength(); j++) {
                Element stateElement = (Element) stateNodes.item(j);
                String stateValue = stateElement.getAttribute(STATE_VALUE_ATTRIBUTE);
                State state = getStateByValue(buffer, stateValue);
                if (state == null) {
                    state = new State(stateValue);
                    buffer.add(state);
                }
                createJumpOperation(buffer, stateElement, stateValue, state);
                fault.addState(state);
            }
            faults.add(fault);
        }
    }

    private void createJumpOperation(ArrayList<State> buffer, Element stateElement, String stateValue, State state) {
        NodeList jumpOperations = stateElement.getChildNodes();
        for (int k = 0; k < jumpOperations.getLength(); k++) {
            Element jumpOperationElement = (Element) jumpOperations.item(k);
            String operationAttribute = jumpOperationElement.getAttribute(OPERATION_TYPE_ATTRIBUTE);
            int positionAttribute = Integer.parseInt(jumpOperationElement.getAttribute(POSITION_OPERATION_ATTRIBUTE));
            String valueAttribute = jumpOperationElement.getAttribute(VALUE_OPERATION_ATTRIBUTE);
            if (jumpOperationElement.getChildNodes() != null) {
                Element nextStateElement = (Element) jumpOperationElement.getChildNodes().item(0);
                String nextStateValue = nextStateElement.getAttribute(STATE_VALUE_ATTRIBUTE);
                State nextState = getStateByValue(buffer, nextStateValue);
                if (nextState == null) {
                    nextState = new State(nextStateValue);
                    buffer.add(nextState);
                }
                JumpOperation jumpOperation = new JumpOperation(operationAttribute, positionAttribute,
                        valueAttribute, nextState);
                state.addJumpOperation(jumpOperation);
            }
        }
    }

    private State getStateByValue(ArrayList<State> buffer, String stateValue) {
//        AtomicInteger index = new AtomicInteger(Collections.binarySearch(buffer, stateValue, new Comparator() {
//            public int compare(Object o1, Object o2) {
//                State state1 = (State) o1;
//                State state2 = (State) o2;
//                return state1.getStateValue().compareToIgnoreCase(state2.getStateValue());
//            }
//        }));
//        if (index.get() == -1) {
//            return null;
//        } else {
//            return buffer.get(index.get());
//        }
        for (int i = 0; i < buffer.size(); i++) {
            if (buffer.get(i).getStateValue().equalsIgnoreCase(stateValue)) {
                return buffer.get(i);
            }
        }
        return null;
    }

    public ArrayList<Fault> getFaults() {
        return faults;
    }
}
