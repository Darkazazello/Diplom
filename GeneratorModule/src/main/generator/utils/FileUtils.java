package main.generator.utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Администратор
 * Date: 18.04.2010
 * Time: 19:40:11
 * To change this template use File | Settings | File Templates.
 */
public class FileUtils {

    public static void writeFile(String filePath, String data) {
        try {
            BufferedWriter fout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
            fout.write(data);
            fout.close();
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.WARNING, e.getLocalizedMessage());
        }
    }
}
