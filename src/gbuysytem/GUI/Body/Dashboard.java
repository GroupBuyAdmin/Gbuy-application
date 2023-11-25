package gbuysytem.GUI.Body;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

import gbuysytem.GUI.Body.DashboardPanels.DynamicPanel;

public class Dashboard extends JPanel{
    private DynamicPanel dynamicPanel;
    private Dimension dashBoardSize = new Dimension(1000,600);

    Dashboard(){
        setSize(dashBoardSize);
        setLayout(new BorderLayout(5, 5));
        dynamicPanel = new DynamicPanel();
        add(dynamicPanel, BorderLayout.CENTER);
    }
}
