package gbuysytem.GUI.Body.DashboardPanels.ProductsPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gbuysytem.GUI.Body.fonts.CustomFont;

class HeaderPanel extends JPanel{
    private final Color headerPanelColor = Color.decode("#F6F6F6");
    private final Color gridColor = Color.decode("#EEF0F3");

    public HeaderPanel(String[] headerNames){
        setPreferredSize(new Dimension(50, 50)); // Set a fixed size for each item
        setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // Ensure a fixed height
        setBorder(BorderFactory.createLineBorder(gridColor));
        setBackground(headerPanelColor);

        setLayout(new GridLayout(1,0));

        for(String headerName : headerNames){
            add(createCenteredLabel(headerName));
        }
        JPanel emptyJPanel = new JPanel();
        emptyJPanel.setOpaque(false);
        add(emptyJPanel);
    }
    
    private JPanel createCenteredLabel(String text) {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15,15));
        JLabel label = new JLabel(text);
        label.setFont(CustomFont.Franca_Medium.getFont().deriveFont(14f));
        labelPanel.setBackground(headerPanelColor);
        labelPanel.add(label);
        return labelPanel;
    }
}