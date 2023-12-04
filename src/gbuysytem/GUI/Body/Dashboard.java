package gbuysytem.GUI.Body;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import gbuysytem.GUI.Body.DashboardPanels.AnalyticsPanel.Analytics;
import gbuysytem.GUI.Body.DashboardPanels.GroupbuysPanel.Groupbuys;
import gbuysytem.GUI.Body.DashboardPanels.ProductsPanel.ProductsPanel;
import gbuysytem.GUI.Sidebar.Sidebar;


public class Dashboard extends JPanel{
    private JPanel dashboardContainer;

    private Analytics analyticsPanel;
    private ProductsPanel productsPanel;
    private Groupbuys groupbuysPanel;

    private Dimension dashBoardSize = new Dimension(1000,600);
    private CardLayout cLayout = new CardLayout();

    private static final String PRODUCTS_PANEL = "products panel";
    private static final String GROUPBUYS_PANEL = "groupbuys panel";
    private static final String ANALYTICS_PANEL = "analytics panel";

    Dashboard(Sidebar sidebar){
        analyticsPanel = new Analytics(dashBoardSize);
        productsPanel = new ProductsPanel(dashBoardSize);
        groupbuysPanel = new Groupbuys(dashBoardSize);
       
        dashboardContainer = new JPanel(cLayout); 
        dashboardContainer.add(productsPanel.getPanel(), PRODUCTS_PANEL);
        dashboardContainer.add(groupbuysPanel.getPanel(), GROUPBUYS_PANEL);
        dashboardContainer.add(analyticsPanel.getPanel(), ANALYTICS_PANEL);           
        
        setActionsSideBarButtons(sidebar);
        
        setLayout(new BorderLayout(5, 5));
        setSize(dashBoardSize);
        add(dashboardContainer, BorderLayout.CENTER);
    }
    
    public void setActionsSideBarButtons(Sidebar sidebar){
        sidebar.getProductsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cLayout.show(dashboardContainer, PRODUCTS_PANEL);
            }
        });

        sidebar.getGroupbuysButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cLayout.show(dashboardContainer, GROUPBUYS_PANEL);
            }
        });

        sidebar.getAnalyticsButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cLayout.show(dashboardContainer, ANALYTICS_PANEL);
            }
        });
    }

} 
