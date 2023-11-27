package gbuysytem.GUI.Body.DashboardPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Product implements PanelReturner{
    private JPanel masterPanel;

    public Product(String name, Dimension masterPanelDimension){
        this.masterPanel = new JPanel(new BorderLayout());
        JLabel tempLabel = new JLabel(name);

        masterPanel.add(tempLabel,BorderLayout.CENTER);

        masterPanel.setPreferredSize(masterPanelDimension);
        masterPanel.setBackground(Color.lightGray);
    }


    public void resizer(Dimension newDimension){
        masterPanel.setPreferredSize(newDimension);
        masterPanel.revalidate();
    }

    @Override
    public JPanel getPanel() {
        return masterPanel;
    }
}
