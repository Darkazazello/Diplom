package main.generator.vhdl_entities;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: aza
 * Date: 10.02.2010
 * Time: 22:06:20
 * To change this template use File | Settings | File Templates.
 */
public class VHDLEntitiesGenerator {
    /**
     * Todo implements full functionality.
     * @param entityName
     * @param inputPorts
     * @param outputPorts
     * @param inoutPorts
     * @return
     */
    public String getVHDLEntity(String entityName, ArrayList<String> inputPorts,
                                       ArrayList <String> outputPorts,
                                       ArrayList<String> inoutPorts ) {
        StringBuilder code = new StringBuilder("");
        code.append("entity " + entityName + " is\n");
        code.append("Port(\n");
        /**
         * Add input ports
         */
        String inputPortsCode = generatePorts("in", inputPorts);
        String outputPortsCode = generatePorts("out", outputPorts);
        //String inoutPortsCode = "";//generatePorts("inout",  inoutPorts);
        code.append(inputPortsCode);
        code.append(";\n");
        code.append(outputPortsCode);
        code.append(");\n");
//        if (!inputPortsCode.equals("") && (!outputPortsCode.equals("") || inoutPortsCode.equals(""))) {
//            code.append("\n;\n");
//        }
//        code.append("\n");
//        code.append(outputPortsCode);
//        if (!outputPortsCode.equals("") && (inoutPortsCode.equals(""))) {
//            code.append("\n;\n");
//        }
//        code.append("\n");
//        code.append(inoutPortsCode);
        code.append("end " + entityName + ";\n");
        return code.toString();
    }

    private String generatePorts(String portsDirection, ArrayList<String> ports) {
        StringBuilder code = new StringBuilder("");
        if (ports == null) {
            return "";
        }
        for (int i = 0; i < ports.size(); i++)
        {
            code.append(ports.get(i));
            if (i + 1 < ports.size()) {
                code.append(";");
            }
            code.append("\n");
        }
//        if (isEndPortsSet) {
//            code.append(";\n");
//        }
        return code.toString();
    }
}
