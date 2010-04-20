package main.xml.entity.fault.sub_entity;

import main.xml.entity.OperationTerm;

/**
 * Created by IntelliJ IDEA.
 * User: aza
 * Date: 28-Jan-2010
 * Time: 21:19:13
 * To change this template use File | Settings | File Templates.
 */
public class Operation {
    private OperationTerm operationTerm;
    private String value;

    public OperationTerm getOperationTerm() {
        return operationTerm;
    }

    public void setOperationTerm(OperationTerm operationTerm) {
        this.operationTerm = operationTerm;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
