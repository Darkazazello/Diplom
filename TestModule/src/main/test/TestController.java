package main.test;


import main.emulation.Emulator;
import main.virtual_model.Cell;
import main.virtual_model.Controller;
import main.virtual_model.linear_model.LinearAddress;
import main.virtual_model.linear_model.LinearAddressController;
import main.xml.entity.fault.Fault;
import main.xml.entity.tests.step_elements.SimpleMarchTest;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: aza
 * Date: 21-Feb-2010
 * Time: 16:28:10
 * To change this template use File | Settings | File Templates.
 */
public class TestController {

    public static void testingMarchTest(ArrayList<Fault> faults, SimpleMarchTest test, int memorySize, Fault goodCell) {
        Random random = new Random(7892734);
        Emulator emulator = new Emulator();
        for (Fault fault : faults) {
            Cell[] cells = new Cell[memorySize];
            for (int i = 0; i < cells.length; i++) {
                cells[i] = new Cell(goodCell);
                cells[i].setCurrentState(goodCell.getStates().get(random.nextInt(goodCell.getStates().size())));
            }
            int randomFaultPosition = random.nextInt(memorySize);
            cells[randomFaultPosition] = new Cell(fault);
            cells[randomFaultPosition].setCurrentState(fault.getStates().get(random.nextInt(fault.getStates().size())));
            LinearAddressController addressController = new LinearAddressController(cells);
            LinearAddress address = new LinearAddress();
            Controller controller = new Controller(addressController, address, memorySize);
            emulator.emulateTesting(controller, test);
        }
    }

}
