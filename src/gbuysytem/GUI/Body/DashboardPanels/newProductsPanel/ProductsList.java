package gbuysytem.GUI.Body.DashboardPanels.newProductsPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gbuysytem.GUI.Body.DashboardPanels.PanelReturner;

public class ProductsList implements PanelReturner{

    private JPanel masterPanel;
    private JPanel headerPanel;
    private JPanel listPanel;
    private JPanel scrollBarPanel;

    public ProductsList(Dimension masterPanelDimension){
        this.masterPanel = new JPanel();
        this.headerPanel = new JPanel();
        this.listPanel = new JPanel();
        this.scrollBarPanel = new JPanel();

        masterPanel.setPreferredSize(masterPanelDimension);
        masterPanel.setLayout(new BorderLayout());

        masterPanel.add(headerPanel, BorderLayout.NORTH);
        masterPanel.add(listPanel, BorderLayout.CENTER);
        masterPanel.add(scrollBarPanel, BorderLayout.EAST);

        headerPanel.setBackground(Color.red);
        listPanel.setBackground(Color.green);
        scrollBarPanel.setBackground(Color.orange);

    }


    @Override
    public JPanel getPanel() {
        return masterPanel;
    }

    public void testPanel(){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(masterPanel);
        f.pack();
        f.setVisible(true);
    }
    
    public static void main(String[] args) {
        ProductsList p = new ProductsList(new Dimension(1000, 600));
        p.testPanel();
    }
    
}
