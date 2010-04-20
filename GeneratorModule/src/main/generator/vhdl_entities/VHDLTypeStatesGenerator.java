package main.generator.vhdl_entities;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 07.04.2010
 * Time: 11:21:42
 * To change this template use File | Settings | File Templates.
 */
public class VHDLTypeStatesGenerator {

    public String generateType(String name, String[] statesName) {
        if (statesName == null || statesName.length <= 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder("");
        builder.append("package types is\n");
        builder.append("Type " + name + " is (");
        for (int i = 0; i < statesName.length; i++) {
            if (i < statesName.length - 1)  {
                builder.append(statesName[i] + ", ");
            } else {
                builder.append(statesName[i] + ");\n");
            }
        }
        builder.append("end types;\n");
        return builder.toString();
    }
}
