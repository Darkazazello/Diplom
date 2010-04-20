package main.emulation;

import main.generator.simple_generator.SimpleMarchTestGenerator;
import main.virtual_model.Controller;
import main.virtual_model.IMemory;
import main.xml.entity.OperationTerm;
import main.xml.entity.tests.step_elements.SimpleMarchTest;
import main.xml.entity.tests.step_elements.normal_element.Direction;
import main.xml.entity.tests.step_elements.simple_element.Command;
import main.xml.entity.tests.step_elements.simple_element.SimpleMarchElement;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 12.04.2010
 * Time: 17:50:14
 * To change this template use File | Settings | File Templates.
 */
public class Emulator {

    public void emulateTesting(Controller controller, SimpleMarchTest test) {
        for (SimpleMarchElement element : test.getSimpleMarchElements()) {
            int firstCell = 0;
            int lastCell = controller.getSize();

            if (Direction.Increasing.equals(element.getDirection())
                    || Direction.Unspecified.equals(element.getDirection())) {
                for (int i = firstCell; i < lastCell; i++) {
                    for (Command command : element.getCommands()) {
                        if (OperationTerm.WRITE.equals(command.getOperation())) {
                            controller.writeCell(i, command.getValue());
                        } else if (OperationTerm.READ.equals(command.getOperation())){
                            String data = controller.readCell(i);
                            if (!command.getValue().equals(data)) {
                                System.out.println("Found fault in " + Integer.toString(i) + " cell.");
                            }

                        }
                    }
                }
            } else if (Direction.Decreasing.equals(element.getDirection())) {
                for (int i = lastCell - 1; i <= firstCell; i--) {
                    for (Command command : element.getCommands()) {
                        if (OperationTerm.WRITE.equals(command.getOperation())) {
                            controller.writeCell(i, command.getValue());
                        } else if (OperationTerm.READ.equals(command.getOperation())){
                            String data = controller.readCell(i);
                            if (!command.getValue().equals(data)) {
                                System.out.println("Found fault in " + Integer.toString(i) + " cell.");
                            }

                        }
                    }
                }
            }
        }
    }
}
