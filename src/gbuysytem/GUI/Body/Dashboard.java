package gbuysytem.GUI.Body;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

import gbuysytem.GUI.Body.DashboardPanels.Analytics;
import gbuysytem.GUI.Body.DashboardPanels.Products;

public class Dashboard extends JPanel{
    private Products productsPanel;
    private Analytics analyticsPanel;
    private Dimension dashBoardSize = new Dimension(1000,600);

    Dashboard(){
        setSize(dashBoardSize);
        setLayout(new BorderLayout(5, 5));


        productsPanel = new Products(dashBoardSize);
        analyticsPanel = new Analytics(dashBoardSize);

        add(productsPanel.getPanel(), BorderLayout.CENTER);
        add(analyticsPanel.getPanel(), BorderLayout.CENTER);
    }  
} 
