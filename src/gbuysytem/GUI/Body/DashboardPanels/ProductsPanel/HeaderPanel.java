package gbuysytem.GUI.Body.DashboardPanels.ProductsPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gbuysytem.GUI.Body.DashboardPanels.ColorPalettes.GBuyPalette;
import gbuysytem.GUI.Body.fonts.GbuyFont;

class HeaderPanel extends JPanel {
    private final Color headerPanelColor = GBuyPalette.CUSTOM_BLUE;

    public HeaderPanel(String[] headerNames) {
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
        label.setForeground(Color.white);
        label.setFont(GbuyFont.MULI_BOLD.deriveFont(14f));
        labelPanel.setBackground(headerPanelColor);
        labelPanel.add(label);
        return labelPanel;
    }
}