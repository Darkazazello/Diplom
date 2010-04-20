package main.virtual_model.linear_model;

import main.virtual_model.Cell;
import main.virtual_model.IAddress;
import main.virtual_model.IAddressController;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: aza
 * Date: 06-Feb-2010
 * Time: 15:58:08
 * To change this template use File | Settings | File Templates.
 */
public class LinearAddressController implements IAddressController {

    private Cell[] linearMemory;

    public LinearAddressController(Cell[] linearMemory) {
        this.linearMemory = linearMemory;
    }

    @Override
    public Cell getCell(IAddress address) {
        return linearMemory[address.getCellIndex()];
    }
}
