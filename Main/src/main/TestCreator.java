package main;

import main.generator.scripts.XilinxUtils;
import main.generator.simple_generator.SimpleMarchTestGenerator;
import main.generator.utils.FileUtils;
import main.test.TestController;
import main.xml.entity.fault.Fault;
import main.xml.entity.tests.step_elements.SimpleMarchTest;
import main.xml.factories.*;

import java.io.Console;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 18.04.2010
 * Time: 19:43:16
 * To change this template use File | Settings | File Templates.
 */
public final class TestCreator {

    /**
     * Available properties
     * --help - help
     * --gen_path - path to generation files
     * --faults_xml - path to faults file xml
     * --test_xml   - path to test file xml
     */
    private final static String HELP_TEXT = "Available parametrs: \n" +
            " --help : print this text\n" +
            "--gen_path : path to generate files" +
            "--faults_xml : path to faults file xml" +
            "--test_xml  : path to test file xml" +
            "--mem_size  : count memory cell";

    public static void main(String[] args) {
        Console console = System.console();
        String genPath = "";
        String faultPath = "";
        String testPath = "";
        int memorySize = 0;
        if (args.length < 4) {
            console.writer().println(HELP_TEXT);
            System.exit(1);
        } else {
            for (int i = 0; i < args.length; i++) {
                if (args[i].startsWith("--gen_path")) {
                    genPath = args[i].substring(args[i].lastIndexOf("=") + 1, args[i].length());
                } else if (args[i].startsWith("--faults_xml")) {
                    faultPath = args[i].substring(args[i].lastIndexOf("=") + 1, args[i].length());
                } else if (args[i].startsWith("--test_xml")) {
                    testPath = args[i].substring(args[i].lastIndexOf("=") + 1, args[i].length());
                } else if (args[i].startsWith("--mem_size")) {
                    try {
                        memorySize = Integer.parseInt(args[i].substring(args[i].lastIndexOf("=") + 1, args[i].length()));
                    } catch (NumberFormatException e) {
                        console.writer().println("Invalid count memory cells");
                        System.exit(1);
                    }
                } else if (args[i].startsWith("--help")) {
                    console.writer().println(HELP_TEXT);
                    System.exit(1);
                } else {
                    console.writer().println("Unknow parametr: " + args[i]);
                    System.exit(1);
                }
            }
        }
        try {
            FaultFactory faultFactory = new FaultFactory(faultPath);
            TestFactory testFactory = new TestFactory(testPath);
            ArrayList<Fault> faults = faultFactory.getFaults();
            ArrayList <SimpleMarchTest> tests = testFactory.getTests();
            SimpleMarchTestGenerator generator = new SimpleMarchTestGenerator();
            for (SimpleMarchTest test : tests) {
                TestController.testingMarchTest(faults, test, memorySize, faults.get(0));/**It's evil*/
                String code = generator.generateVHDLBehaviour(test);
                FileUtils.writeFile(genPath +"\\" + test.getName() + ".vhdl", code);
                String tclScript = XilinxUtils.createTclScript(test.getName(), test.getName() + ".vhdl", genPath);
                String batScript = XilinxUtils.createBatScript(genPath + test.getName() + ".tcl",
                        genPath + "\\" + test.getName() + ".report", genPath +  test.getName() + ".ncd");
                FileUtils.writeFile(genPath + test.getName() + ".tcl", tclScript);
                FileUtils.writeFile(genPath + test.getName() + ".bat", batScript);
                Process process = Runtime.getRuntime().exec(genPath + "\\" + test.getName() + ".bat");
                process.waitFor();
            }
        } catch (Exception e) {
//            console.writer().println(e);
            e.printStackTrace();
        }
     }


}
