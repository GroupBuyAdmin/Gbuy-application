package gbuysytem.GUI.Body.DashboardPanels.ProductsPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gbuysytem.GUI.Body.fonts.CustomFont;

class HeaderPanel extends JPanel {
    private final Color headerPanelColor = Color.decode("#F6F6F6");
    private final Color gridColor = Color.decode("#EEF0F3");

    public HeaderPanel(String[] headerNames) {
        setBorder(BorderFactory.createLineBorder(gridColor));
        setBackground(headerPanelColor);

        setLayout(new GridLayout(1, 8));
        for (String headerName : headerNames) {
            add(createPanelWithLabel(headerName));        
        }

        // Add an additional empty panel at the end
        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);
        add(emptyPanel);
    }

    private JPanel createPanelWithLabel(String text) {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 15, 15));
        JLabel label = new JLabel(text);
        label.setFont(CustomFont.Franca_Medium.getFont().deriveFont(14f));
        labelPanel.setBackground(headerPanelColor);
        labelPanel.add(label);
        return labelPanel;
    }
}