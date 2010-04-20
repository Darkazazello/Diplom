package main.virtual_model;

/**
 * Created by IntelliJ IDEA.
 * User: aza
 * Date: 06-Feb-2010
 * Time: 16:24:20
 * To change this template use File | Settings | File Templates.
 */
public interface IMemory {

    public void write(Cell cell, String value);
    public String read(Cell cell);
}
