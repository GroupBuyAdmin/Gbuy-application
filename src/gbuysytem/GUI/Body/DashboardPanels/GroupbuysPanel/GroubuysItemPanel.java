package gbuysytem.GUI.Body.DashboardPanels.GroupbuysPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gbuysytem.GUI.Body.DashboardPanels.ColorPalettes.GBuyPalette;
import gbuysytem.GUI.Body.DashboardPanels.Misc.RoundedButton;
import gbuysytem.GUI.Body.DashboardPanels.Misc.RoundedPanel;
import gbuysytem.GUI.Body.DashboardPanels.ProductsPanel.Product;
import gbuysytem.GUI.Body.fonts.GbuyFont;

public class GroubuysItemPanel extends RoundedPanel{
    private RoundedButton viewGroupbuysButton;

    public RoundedButton getViewGroupbuysButton() {
        return viewGroupbuysButton;
    }

    public GroubuysItemPanel(Product product){
        setToCustomBorder(this);
        setPreferredSize(new Dimension(270, 338));
        setMaximumSize(new Dimension(270, 338));

        //product name
        JLabel productLabel = new JLabel(product.getName());
        productLabel.setHorizontalAlignment(SwingConstants.LEFT);
        productLabel.setForeground(GBuyPalette.CUSTOM_BLACK);
        productLabel.setFont(GbuyFont.MULI_SEMI_BOLD.deriveFont(20f));

        //product image
        ImageContainer imageContainer = new ImageContainer(product.getImageIcon(), getPreferredSize().width);

        //product view groupbuys button
        JPanel buttonContainer = new JPanel(new FlowLayout());
        viewGroupbuysButton = new RoundedButton("View Groupbuys");
        viewGroupbuysButton.setDrawBorder(false);
        viewGroupbuysButton.setButtonColor(GBuyPalette.CUSTOM_BLUE);
        viewGroupbuysButton.setForeground(Color.white);
        viewGroupbuysButton.setButtonFont(GbuyFont.MULI_SEMI_BOLD.deriveFont(14f));
        viewGroupbuysButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonContainer.add(viewGroupbuysButton);

        setLayout(new BorderLayout(0,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(productLabel, BorderLayout.NORTH);
        add(imageContainer, BorderLayout.CENTER);
        add(buttonContainer, BorderLayout.SOUTH);
    }

    private void setToCustomBorder(RoundedPanel rPanel){
        rPanel.setShady(true);
        rPanel.setShadowAlpha(50);
        rPanel.setShadowGap(4);
        rPanel.setShadowOffset(5);
        int arc = 30;
        rPanel.setArcs(new Dimension(arc, arc));
    }

    public void testPanel(){
        JFrame f = new JFrame();
        f.add(this);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // f.setResizable(false);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    // public static void main(String[] args) {
    //     ImageIcon dummyImage = new ImageIcon("src/gbuysytem/GUI/Body/DashboardPanels/ProductsPanel/img/dummyImage.png");
    //     Product p = new Product(dummyImage, "Dummy Product", "null", "null", "null", "null");
    //     GroubuysItemPanel g = new GroubuysItemPanel(p);
    //     g.testPanel();
    // }

    class ImageContainer extends JPanel {
        private ImageIcon imageIcon;

        public ImageContainer(ImageIcon imageIcon, int width) {
            this.imageIcon = resizeImage(imageIcon, width);
        }

        private ImageIcon resizeImage(ImageIcon imageIcon, int width){
            int newHeight = imageIcon.getIconHeight() - (imageIcon.getIconWidth() - width);
            Image image = imageIcon.getImage().getScaledInstance(width, newHeight, Image.SCALE_SMOOTH);
            ImageIcon resizedImage = new ImageIcon(image);
            return resizedImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imageIcon != null) {
                int x = (getWidth() - imageIcon.getIconWidth()) / 2;
                int y = (getHeight() - imageIcon.getIconHeight()) / 2;
                imageIcon.paintIcon(this, g, x, y);
            }
        }
    }
}
