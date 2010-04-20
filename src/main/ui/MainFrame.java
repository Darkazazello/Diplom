package main.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: aza
 * Date: 28-Jan-2010
 * Time: 20:07:59
 * To change this template use File | Settings | File Templates.
 */
public class MainFrame extends JFrame {

    private JTabbedPane tabbedPane = new JTabbedPane();

    private FaultsCreatorPanel faultCreatorPanel = new FaultsCreatorPanel();

    public MainFrame() {
        setLayout(new FlowLayout());
        tabbedPane.setSize(new Dimension(Contants.WINDOW_HEIGHT, Contants.WINDOW_WIDTH));
        tabbedPane.addTab("Faults", faultCreatorPanel);
        tabbedPane.addTab("Tests", new JPanel());
        tabbedPane.addTab("Memory", new JPanel());
        add(tabbedPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setVisible(true);
    }
}
