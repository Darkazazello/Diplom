package main.xml.entity.tests.step_elements.normal_element;

/**
 * Created by IntelliJ IDEA.
 * User: s.sheluhin
 * Date: 16.02.2010
 * Time: 14:51:46
 * To change this template use File | Settings | File Templates.
 */
public enum Direction {
    Increasing, Decreasing, Unspecified;
    public static Direction getDirectionByString(String data) {
        if (data == null || data.trim().equals("")) {
            return null;
        } else if (data.equalsIgnoreCase("either")) {
            return Direction.Unspecified;
        } else if (data.equalsIgnoreCase("up")) {
            return Direction.Increasing;
        } else if (data.equalsIgnoreCase("down")) {
            return Direction.Decreasing;
        } else {
            return null;
        }
    }
 }
