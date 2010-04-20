package main.tests.vhdl;

import main.generator.vhdl_entities.VHDLEntitiesGenerator;
import org.junit.*;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: aza
 * Date: 10.02.2010
 * Time: 22:38:10
 * To change this template use File | Settings | File Templates.
 */

public class VHDLCodeTester {

    @Test
    public void createVHDLEntity() {
      VHDLEntitiesGenerator generator = new VHDLEntitiesGenerator();
        ArrayList<String> inputPortsName = new ArrayList<String>();
        inputPortsName.add("a");
        ArrayList<String> inputPortsType = new ArrayList<String>();
        inputPortsType.add("std_logic");
      //  String code = generator.getVHDLEntity("hello", inputPortsName, inputPortsType, null, null, null, null);
     //   Assert.assertNotNull(code);
    }

}
