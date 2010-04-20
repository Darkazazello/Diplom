package main.generator.scripts;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 18.04.2010
 * Time: 18:00:04
 * To change this template use File | Settings | File Templates.
 */
public class XilinxUtils {

    public static String createTclScript(String projectName, String fileName, String directorPath) {
        StringBuilder result = new StringBuilder();
        directorPath.replaceAll("/", "\\");
        result.append(
        "set project_name \"" + projectName + "\"\n" +
                "set file_name \"" + fileName +"\"\n" +
                "set device_family \"spartan3e\"\n" +
                "set device_name \"xc3s100e\"\n" +
                "set absolute_path \"" + directorPath + "\"\n" +
                "namespace import xilinx::*\n" +
                "\n" +
                "project new $project_name\n" +
                "project set family $device_family\n" +
                "project set device $device_name\n" +
                "\n" +
                "xfile add $absolute_path$file_name\n" +
                "\n" +
                "if { [catch { time {process run \"Synthesize - XST\"} } synthTime]} {\n" +
                "    puts \"Synthesis failed\"\n" +
                "} else { \n" +
                "    puts \"Synthesis ran in $synthTime\"\n" +
                "}\n" +
                "\n" +
                "process run \"Implement Design\""
        );
        return result.toString();
    }

    public static String createBatScript(String scriptPath, String xpowerReportPath, String ncdFilePath) {
        StringBuilder builder = new StringBuilder();
        builder.append(
            "xtclsh " + scriptPath + "\n" +
            "xpwr " + ncdFilePath + "-o " + xpowerReportPath
        );
        return builder.toString();
    }

}
