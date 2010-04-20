package main.xml.entity;

/**
 * Created by IntelliJ IDEA.
 * User: aza
 * Date: 28-Jan-2010
 * Time: 21:14:01
 * To change this template use File | Settings | File Templates.
 */
public enum OperationTerm {
    READ, WRITE;

    public static OperationTerm getOperationTermByString(String data) {
        if (data == null || data.trim().equals("")) {
            return null;
        } else if (data.equalsIgnoreCase("w")) {
            return OperationTerm.WRITE;
        } else if (data.equalsIgnoreCase("r")) {
            return OperationTerm.READ;
        } else {
            return null;
        }
    }
}
