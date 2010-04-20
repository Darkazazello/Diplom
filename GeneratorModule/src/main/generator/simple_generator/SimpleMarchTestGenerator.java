package main.generator.simple_generator;

import main.generator.vhdl_entities.VHDLEntitiesGenerator;
import main.generator.vhdl_entities.VHDLTypeStatesGenerator;
import main.xml.entity.OperationTerm;
import main.xml.entity.tests.step_elements.SimpleMarchTest;
import main.xml.entity.tests.step_elements.normal_element.Direction;
import main.xml.entity.tests.step_elements.simple_element.Command;
import main.xml.entity.tests.step_elements.simple_element.SimpleMarchElement;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 07.04.2010
 * Time: 10:50:14
 * To change this template use File | Settings | File Templates.
 */
public class SimpleMarchTestGenerator {

    private final String VHDL_HEADER = "library IEEE;\n" +
            "use ieee.std_logic_1164.all;\n";

    private VHDLEntitiesGenerator entitiesGenerator = new VHDLEntitiesGenerator();
    private VHDLTypeStatesGenerator statesGenerator = new VHDLTypeStatesGenerator();


    public String generateVHDLBehaviour(SimpleMarchTest test) {
        ArrayList<String> inPort = new ArrayList<String>();
        inPort.add("Clk : in std_logic");
        inPort.add("Reset : in std_logic");
        inPort.add("Data : in std_logic");
        inPort.add("Is_End : in std_logic");

        ArrayList<String> outPort = new ArrayList<String>();
        outPort.add("ClkC : out std_logic");
        outPort.add("Is_wr : out std_logic");
        outPort.add("DataOut : out std_logic");
        outPort.add("Direction : out std_logic");
        outPort.add("Fail_Test : out std_logic");
        outPort.add("Fail_Process : out std_logic");
        outPort.add("Finish_Successfully : out std_logic");

        String[] states = generateAvailableStates(test);
        String type = statesGenerator.generateType("States", states);
        /**TODO Add requires libryries*/
        String libs = "library IEEE;\n" +
                "\n" +
                "\n" +
                "use ieee.std_logic_1164.all;\n" +
                "use ieee.std_logic_unsigned.all;\n" +
                "use types.all;\n";
        String entity = entitiesGenerator.getVHDLEntity(test.getName(), inPort, outPort, null);
        String architectureHeader = generateHeader("behav_of_" + test.getName(), test.getName());
        String processSource = generateProcess(test, new String[]{"Clk"}, states);
        String footer = "end " + "behav_of_" + test.getName() + ";\n";
        return type + libs + entity+ architectureHeader + processSource + footer;
    }

    private String generateProcess(SimpleMarchTest test, String[] sensitiveSignals, String[] states) {

        StringBuilder builder = new StringBuilder("");
        builder.append("process (\n");
        for (int i = 0; i < sensitiveSignals.length - 1; i++) {
            builder.append(sensitiveSignals[i] + ", ");
        }
        builder.append(sensitiveSignals[sensitiveSignals.length - 1] + ")\n");
        builder.append("variable current_state : States := " + states[0] + "\n");
        builder.append("begin\n");
        builder.append("    if Clk\'event and Clk=\'1\'  then\n");
        builder.append("        case current_state is \n");
        int index = 0;
        for (int i = 0; i < test.getSimpleMarchElements().size(); i++) {
            SimpleMarchElement currentElement = test.getSimpleMarchElements().get(i);
            SimpleMarchElement nextElement = test.getSimpleMarchElements().get(i);
            if (i + 1 < test.getSimpleMarchElements().size()) {
                nextElement = test.getSimpleMarchElements().get(i + 1);
            }
            for (int j = 0; j < currentElement.getCommands().size(); j++) {
                Command currentCommand = currentElement.getCommands().get(j);
                Command nextCommand = currentCommand;
                if (j == currentElement.getCommands().size() - 1) {
                    nextCommand = nextElement.getCommands().get(0);/**It's circle for last command*/
                } else {
                    nextCommand = currentElement.getCommands().get(j + 1);
                }
                builder.append("            when " + states[index] + "=> \n");

                if (index < states.length - 1) {
                    builder.append("\t\t\t\tif Is_End=\'1\' then \n " +
                            "\t\t\t\t\tcurrent_state := "+ states[index + 1] + ";\n");
                    if (OperationTerm.READ.equals(nextCommand.getOperation())){
                        builder.append("\t\t\t\t\tIs_wr <= \'0\';\n");
                    }else{
                        builder.append("\t\t\t\t\tIs_wr <= \'1\';\n");

                    }
                    if (Direction.Increasing.equals(nextElement.getDirection())) {
                        builder.append("\t\t\t\t\tDirection <= \'1\';\n");
                    } else {
                        builder.append("\t\t\t\t\tDirection <= \'0\';\n");
                    }
                    builder.append("\t\t\t\telse\n");
                    if (OperationTerm.READ.equals(currentCommand.getOperation())) {
                        builder.append("\t\t\t\t\tif not(Data = \'" + currentCommand.getValue() + "\') then \n");
                        builder.append("\t\t\t\t\t\tFail_Test <= \'1\';\n end if; \n");
                        builder.append("\t\t\t\t\t\tIs_wr <= \'0\';\n");
                        builder.append("\t\t\t\t\t\tDirection <= " + (Direction.Increasing.equals(currentElement.getDirection())
                                        ? "\'1\';\n" : "\'0\';\n"));
                    } else if (OperationTerm.WRITE.equals(currentCommand.getOperation())){
                        builder.append("\t\t\t\t\t\tDataOut <= \'" + currentCommand.getValue() + "\';\n");
                        builder.append("\t\t\t\t\t\tIs_wr <= \'1\';\n");
                        builder.append("\t\t\t\t\t\tDirection <= " + (Direction.Increasing.equals(currentElement.getDirection())
                                        ? "\'1\';\n" : "\'0\';\n"));
                    }
                    builder.append("\t\t\t\t\tend if;\n");
                } else {
                    if (OperationTerm.READ.equals(currentCommand.getOperation())) {
                        builder.append("\t\t\t\tif not(Data = \'" + currentCommand.getValue() + "\') then\n");
                        builder.append("\t\t\t\t\tFailt_Test <= \'1\';\n");
                        builder.append("\t\t\t\telse\n if Is_End = \'1\' then \n Finish_Successfully <= \'1\';\n end if;\n");
                        builder.append("\t\t\t\t\tIs_wr <= \'0\';\n");
                        builder.append("\t\t\t\t\tDirection <= " + (Direction.Increasing.equals(currentElement.getDirection())
                                        ? "\'1\';\n" : "\'0\';\n"));
                    } else {
                        builder.append("\t\t\t\t\tDataOut <= \'" + currentCommand.getValue() + "\';\n");
                        builder.append("\t\t\t\t\tIs_wr <= \'1\';");
                        builder.append("\t\t\t\t\tDirection <= " + (Direction.Increasing.equals(currentElement.getDirection())
                                        ? "\'1\';\n" : "\'0\';\n"));
                    }
                }

                index++;
            }
        }
        builder.append("\t\t\t\twhen others =>\n Fail_Process <= \'1\';\n");
        builder.append("\t\t\tend case;\n");
        builder.append("\t\tClkC <= \'1\';\n");
        builder.append("\tend if;\n");
        builder.append("end process;\n");
        return builder.toString();
    }

    private String generateHeader(String architectureName, String entityName) {
        /**
         * TODO Rewrite this list of signals
         */
        StringBuilder builder = new StringBuilder("");
        builder.append("architecture " + architectureName + " of " + entityName + " is \n");
        builder.append("begin\n");
        return builder.toString();
    }

    private String[] generateAvailableStates(SimpleMarchTest test) {
        int countStates = 0;
        for (SimpleMarchElement element : test.getSimpleMarchElements()) {
            countStates += element.getCommands().size();
        }
        String [] result = new String[countStates + 2];
        for (int i = 0; i < result.length - 2; i++) {
            result[i]  = "S" + Integer.toString(i);
        }
        result[result.length - 2] = "Fault";
        result[result.length - 1] = "Process_fail";
        return result;
    }

}
