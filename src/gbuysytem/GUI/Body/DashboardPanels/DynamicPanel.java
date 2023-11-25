package gbuysytem.GUI.Body.DashboardPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import gbuysytem.GUI.Body.DashboardPanels.ProductsPanel.Products;

public class DynamicPanel extends JPanel{
    private Products prodPanel;

    private Dimension dynamicPanelDimension = new Dimension(1000, 600);

    public DynamicPanel(){
        prodPanel = new Products(dynamicPanelDimension);
        setBackground(Color.GREEN);

        setupPanels();
    }

    private void setupPanels(){
        setLayout(new BorderLayout());
        add(prodPanel.getPanel(), BorderLayout.CENTER);
        
    }

    public void changePanel(){
        //method for changing panels here...
    }
}
