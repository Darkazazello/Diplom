package main.virtual_model.linear_model;

import main.virtual_model.Cell;
import main.virtual_model.IAddress;
import main.virtual_model.IMemory;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: s.sheluhin
 * Date: 05.02.2010
 * Time: 11:31:25
 * To change this template use File | Settings | File Templates.
 */
public class LinearMemory implements IMemory {
    //private Cell[] cells;
    //private int size;

//    public LinearMemory(int size) {
//        this.size = size;
//        cells = new Cell[size];
//    }

    @Override
    public void write(Cell cell, String value) {
        cell.doOperationWrite(value);
    }

    @Override
    public String read(Cell currentCell) {
        return currentCell.doOperationRead();  
    }
//    @Deprecated
//    public void setCell(Cell cell, int index) {
//        if (index < size) {
//            cells[index] = cell;
//        } else {
//            Logger.getAnonymousLogger().log(Level.WARNING, "Index of cell more than size of memory");
//        }
//    }
}
