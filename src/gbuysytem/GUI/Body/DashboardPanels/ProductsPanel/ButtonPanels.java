package gbuysytem.GUI.Body.DashboardPanels.ProductsPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gbuysytem.GUI.Body.fonts.GbuyFont;

public class ButtonPanels extends JPanel{
    private final Color buttonPanelColor = Color.decode("#FFFFFF");
    private final Color gridColor = Color.decode("#EEF0F3");

    public ButtonPanels(JButton filter, JButton createProduct){
        setPreferredSize(new Dimension(Short.MAX_VALUE,50));
        setBorder(BorderFactory.createLineBorder(gridColor));
        setBackground(buttonPanelColor);
        JLabel title = new JLabel("Products List");
        title.setFont(GbuyFont.MULI_BOLD.deriveFont(20f));


        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(buttonPanelColor);
        titlePanel.add(title);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttons.setBackground(buttonPanelColor);
        buttons.add(filter);
        buttons.add(createProduct);

        add(titlePanel, BorderLayout.WEST);
        add(buttons, BorderLayout.EAST);
    }
}