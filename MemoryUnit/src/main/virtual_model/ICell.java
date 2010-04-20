package main.virtual_model;

/**
 * Created by IntelliJ IDEA.
 * User: s.sheluhin
 * Date: 05.02.2010
 * Time: 12:34:27
 * To change this template use File | Settings | File Templates.
 */
public interface ICell {

    public void doOperationWrite(String value);
    public String doOperationRead();
}
