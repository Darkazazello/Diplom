package main.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: aza
 * Date: 28-Jan-2010
 * Time: 20:14:37
 * To change this template use File | Settings | File Templates.
 */
public class FaultsCreatorPanel extends JPanel {

    public FaultsCreatorPanel() {
        setLayout(new FlowLayout());
        setSize(Contants.WINDOW_HEIGHT, Contants.WINDOW_WIDTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);    
    }
}
