package main.virtual_model.linear_model;

import main.virtual_model.IAddress;

/**
 * Created by IntelliJ IDEA.
 * User: aza
 * Date: 06-Feb-2010
 * Time: 15:59:20
 * To change this template use File | Settings | File Templates.
 */
public class LinearAddress implements IAddress {

    private int cellIndex;

    @Override
    public int getCellIndex() {
        return cellIndex;
    }

    @Override
    public void setCellIndex(int key) {
        this.cellIndex = key;
    }
}
