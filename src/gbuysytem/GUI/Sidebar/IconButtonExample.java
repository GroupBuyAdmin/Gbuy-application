package gbuysytem.GUI.Sidebar;

import javax.swing.JButton;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class IconButtonExample {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Icon and Text Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create a JButton with an icon and text
        ImageIcon icon = new ImageIcon("src/gbuysytem/GUI/Body/DashboardPanels/ProductsPanel/filter.png"); // Replace with the actual path to your icon
        Image resizedImage = icon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        RoundedIconButton iconButton = new RoundedIconButton("Click me");

        // Add the button to a JPanel
        JPanel panel = new JPanel();
        panel.add(iconButton);

        // Add the JPanel to the JFrame
        frame.add(panel);

        // Set the JFrame visible
        frame.setVisible(true);
    }
}
