package gbuysytem.GUI.Body;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

import gbuysytem.GUI.Body.DashboardPanels.AnalyticsPanel.Analytics;
import gbuysytem.GUI.Body.DashboardPanels.ProductsPanel.ProductsPanel;


public class Dashboard extends JPanel{
    private Analytics analyticsPanel;
    private ProductsPanel productsPanel;
    private Dimension dashBoardSize = new Dimension(1000,600);

    Dashboard(){
        setSize(dashBoardSize);
        setLayout(new BorderLayout(5, 5));

        analyticsPanel = new Analytics(dashBoardSize);
        productsPanel = new ProductsPanel(dashBoardSize);

        //if mahimo pre pwede nimo imodify ang layout sa dashboardItemPanel og HeaderPanel 
        //ako giset to gridlayout man gud kay naglibog kos gridbag hahahahah
        
        //  sige  
        //  -carl

        add(analyticsPanel.getPanel(), BorderLayout.CENTER);            //use getPanel() method lang
        add(productsPanel.getPanel(),BorderLayout.CENTER);
        
    }  
} 
