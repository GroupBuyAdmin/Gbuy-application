package gbuysytem.GUI.Body;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

import gbuysytem.GUI.Body.DashboardPanels.Analytics;
import gbuysytem.GUI.Body.DashboardPanels.ProductsPanel;
import gbuysytem.GUI.Body.DashboardPanels.newProductsPanel.ProductsContainer;

public class Dashboard extends JPanel{
    private ProductsPanel productsPanel;
    private Analytics analyticsPanel;
    private Dimension dashBoardSize = new Dimension(1000,600);

    Dashboard(){
        setSize(dashBoardSize);
        setLayout(new BorderLayout(5, 5));

        productsPanel = new ProductsPanel(dashBoardSize);
        analyticsPanel = new Analytics(dashBoardSize);

        add(analyticsPanel.getPanel(), BorderLayout.CENTER);
        // add(productsPanel.getPanel(), BorderLayout.CENTER);


        ProductsContainer p = new ProductsContainer(dashBoardSize);
        add(p.getPanel(), BorderLayout.CENTER);
    }  
} 
