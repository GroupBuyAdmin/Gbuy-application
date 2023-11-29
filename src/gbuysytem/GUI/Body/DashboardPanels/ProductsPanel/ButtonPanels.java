package gbuysytem.GUI.Body.DashboardPanels.ProductsPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanels extends JPanel{
    private final Color buttonPanelColor = Color.decode("#FFFFFF");
    private final Color gridColor = Color.decode("#EEF0F3");

    public ButtonPanels(JButton filter, JButton createProduct){
        setPreferredSize(new Dimension(20,50));
        setBorder(BorderFactory.createLineBorder(gridColor));
        setBackground(buttonPanelColor);

        setLayout(new FlowLayout(FlowLayout.TRAILING, 15, 5));

        add(filter);
        add(createProduct);
    }
}