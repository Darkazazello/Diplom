package main.xml.entity.fault.sub_entity;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: aza
 * Date: 28-Jan-2010
 * Time: 21:04:10
 * To change this template use File | Settings | File Templates.
 */
public class StateFault {
    public ArrayList<Cell> singleStates = new ArrayList <Cell>();

    public StateFault(ArrayList<Cell> singleState) {
        this.singleStates = singleState;
    }

    public ArrayList<Cell> getSingleStates() {
        return singleStates;
    }

    public void setSingleStates(ArrayList<Cell> singleStates) {
        this.singleStates = singleStates;
    }
}
