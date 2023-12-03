package gbuysytem.GUI.Body.DashboardPanels.ProductsPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import gbuysytem.GUI.Body.DashboardPanels.ColorPalettes.GBuyPalette;
import gbuysytem.GUI.Body.fonts.GbuyFont;


public class DashboardItemPanel extends JPanel {
    private Product product;
    
    public Product getProduct() {
        return product;
    }

    private final Color dashboardItemPanelColor = Color.decode("#FFFFFF");
    private final Color gridColor = Color.decode("#EEF0F3");

    public DashboardItemPanel(Product product, JButton editButton, JButton deleteButton) {
        this.product = product;
        setPreferredSize(new Dimension(50, 50)); // Set a fixed size for each item
        setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // Ensure a fixed height
        setBorder(new BottomLineBorder(gridColor));
        setBackground(dashboardItemPanelColor);

        setLayout(new GridLayout(1,8));
        add(createIcon(product.getImageIcon()));
        add(createPanelWithLabel(product.getName()));
        add(createPanelWithLabel(product.getPrice()));
        add(createPanelWithLabel(String.valueOf(product.getQuantity())));
        add(createPanelWithLabel(product.getCategory()));
        add(createPanelWithLabel(product.getDescription()));
        add(createButtonPanel(deleteButton));
        add(createButtonPanel(editButton));
    }

    private JPanel createPanelWithLabel(String text) {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 15, 15));
        JLabel label = new JLabel(text);
        label.setFont(GbuyFont.MULI_LIGHT.deriveFont(14f));
        label.setForeground(GBuyPalette.CUSTOM_BLACK);
        labelPanel.setBackground(dashboardItemPanelColor);
        labelPanel.add(label);
        return labelPanel;
    }

    private JPanel createIcon(ImageIcon imageIcon) {
        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 15, 9));
        iconPanel.setBackground(dashboardItemPanelColor);

        // change if it is an image or image icon...for now from image to image icon
        Image i = imageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(i);

        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(scaledImageIcon);

        iconPanel.add(iconLabel);
        return iconPanel;
    }

    private JPanel createButtonPanel(JButton button) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 10));
        buttonPanel.setBackground(dashboardItemPanelColor);
        buttonPanel.add(button);
        button.setPreferredSize(new Dimension(100, 30));
        return buttonPanel;
    }

    private class BottomLineBorder implements Border {

        private Color lineColor;

        public BottomLineBorder(Color lineColor) {
            this.lineColor = lineColor;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(lineColor);
            g.drawLine(x, y + height - 1, x + width - 1, y + height - 1);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(0, 0, 1, 0); // Top, left, bottom, right
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

    
    }
}

