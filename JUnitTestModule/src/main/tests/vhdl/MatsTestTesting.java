package main.tests.vhdl;



import main.generator.simple_generator.SimpleMarchTestGenerator;
import main.xml.entity.OperationTerm;
import main.xml.entity.tests.step_elements.SimpleMarchTest;
import main.xml.entity.tests.step_elements.normal_element.Direction;
import main.xml.entity.tests.step_elements.simple_element.Command;
import main.xml.entity.tests.step_elements.simple_element.SimpleMarchElement;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 08.04.2010
 * Time: 17:24:02
 * To change this template use File | Settings | File Templates.
 */
public class MatsTestTesting {

    @Test
    public void createMats() {
        SimpleMarchTest test = new SimpleMarchTest();

        SimpleMarchElement element1 = new SimpleMarchElement();
        element1.setDirection(Direction.Unspecified);
        Command command1 = new Command();
        command1.setOperation(OperationTerm.WRITE);
        command1.setValue("0");
        ArrayList <Command> commands = new ArrayList<Command>();
        commands.add(command1);
        element1.setOperation(commands);
        test.addPart(element1);

        SimpleMarchElement element2 = new SimpleMarchElement();
        element2.setDirection(Direction.Unspecified);
        Command command2 = new Command();
        command2.setOperation(OperationTerm.READ);
        command2.setValue("0");
        Command command3 = new Command();
        command3.setOperation(OperationTerm.WRITE);
        command3.setValue("1");
        element2.addCommand(command2);                
        element2.addCommand(command3);
        test.addPart(element2);
        
        SimpleMarchElement element3 = new SimpleMarchElement();
        element2.setDirection(Direction.Unspecified);
        Command command4 = new Command();
        command4.setOperation(OperationTerm.READ);
        command4.setValue("1");
        element3.addCommand(command4);
        test.addPart(element3);
        
        SimpleMarchTestGenerator generator = new SimpleMarchTestGenerator();
        String data = generator.generateVHDLBehaviour(test);
        try {
            BufferedWriter fout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("c://temp.vhdl")));
            fout.write(data);
            fout.close();
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.WARNING, e.getLocalizedMessage());
        }
    }
}
