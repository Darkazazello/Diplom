package main.virtual_model;

/**
 * Created by IntelliJ IDEA.
 * User: aza
 * Date: 07-Feb-2010
 * Time: 21:47:30
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    //private IMemory memory;
    private IAddressController addressController;
    private IAddress addressType;
    private int memorySize;
    public Controller(IMemory memory, IAddressController addressController, IAddress addressType, int memorySize) {
        //this.memory = memory;
        this.addressController = addressController;
        this.addressType = addressType;
        this.memorySize = memorySize;
    }

    public Controller(IAddressController addressController, IAddress addressType, int memorySize) {
        this.addressController = addressController;
        this.addressType = addressType;
        this.memorySize = memorySize;
    }

    public String readCell(int index) {
        addressType.setCellIndex(index);
        Cell currentCell = addressController.getCell(addressType);
        return currentCell.doOperationRead();
    }

    public void writeCell(int index, String value) {
        addressType.setCellIndex(index);
        Cell currentCell = addressController.getCell(addressType);
        currentCell.doOperationWrite(value);
    }

    public int getSize() {
        return memorySize;
    }
}