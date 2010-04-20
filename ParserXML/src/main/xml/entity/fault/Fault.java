package main.xml.entity.fault;

import main.xml.entity.fault.sub_entity.State;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: aza
 * Date: 28-Jan-2010
 * Time: 20:58:15
 * To change this template use File | Settings | File Templates.
 */
public class Fault {

    private final String name;

    private final ArrayList <State> states = new ArrayList<State>();

    public Fault(String name) {
        this.name = name;
    }

    public void addState(State state) {
        states.add(state);
    }

    public String getName() {
        return name;
    }

    public ArrayList<State> getStates() {
        return states;
    }
}
