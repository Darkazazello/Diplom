package main.xml.entity.tests.step_elements.normal_element;

/**
 * Created by IntelliJ IDEA.
 * User: s.sheluhin
 * Date: 16.02.2010
 * Time: 14:56:34
 * To change this template use File | Settings | File Templates.
 */
public enum Sequence {
    Linear, _2power, Gray, Pingpong;
    private int factor = 0;

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }
}
