package gbuysytem.GUI.Sidebar;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gbuysytem.GUI.Body.DashboardPanels.ColorPalettes.GBuyPalette;
import gbuysytem.GUI.Body.DashboardPanels.Misc.RoundedButton;
import gbuysytem.GUI.Body.fonts.GbuyFont;

public class Sidebar extends JPanel{
    
    JLabel logo;
    RoundedButton productsButton;
    RoundedButton groupbuysButton;
    RoundedButton analyticsButton;
    
    public RoundedButton getProductsButton() {
        return productsButton;
    }


    public RoundedButton getGroupbuysButton() {
        return groupbuysButton;
    }


    public RoundedButton getAnalyticsButton() {
        return analyticsButton;
    }


    public Sidebar(){
        logo = new JLabel("Groupbuy");
        ImageIcon LogoIcon = new ImageIcon("src/gbuysytem/GUI/Sidebar/img/logo.png");       
        Image LogoScaledImage = LogoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon setLogoIcon = new ImageIcon(LogoScaledImage);    
        Font projectFont = GbuyFont.MULI_BOLD;
        logo.setFont(new Font(projectFont.getName(),Font.BOLD,20));
        logo.setIcon(setLogoIcon);                         
        logo.setForeground(GBuyPalette.CUSTOM_BLUE);
   
        Dimension dim = getPreferredSize();
        dim.width = 180;
        setPreferredSize(dim);
        setBackground(Color.decode("#FCFDFF"));
        createSideBarButtons();
        setSidebarLayout();
    }
    

    void setIcons(JLabel jLabel,String groupbuysIcon,int color){
        ImageIcon analyticsIcon = new ImageIcon(groupbuysIcon);       
        Image analyticsScaledImage = analyticsIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon setAnalyticsIcon = new ImageIcon(analyticsScaledImage);    
        Font analyticsFont = jLabel.getFont();
        jLabel.setFont(new Font(analyticsFont.getName(),Font.ROMAN_BASELINE,20));
        jLabel.setIcon(setAnalyticsIcon);                         
        jLabel.setForeground(new Color(color));
        jLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    

    public void setSidebarLayout(){
            
       setLayout(new GridBagLayout());

       GridBagConstraints gbc =  new GridBagConstraints();
      
       gbc.weightx = 0.1;
       gbc.weighty = 0.3;
       gbc.gridx = 0;
       gbc.gridy = 0;
       gbc.insets = new Insets(4, 10, 0, 10); // Add some spacing to the left
       gbc.fill = GridBagConstraints.NONE;
       gbc.anchor = GridBagConstraints.FIRST_LINE_START;    
       add(logo, gbc);
       
       gbc.weightx = 0.1;
       gbc.weighty = 0.2;
       gbc.gridx = 0;
       gbc.gridy++;
       gbc.insets = new Insets(0, 20, 0, 10); // Add some spacing to the left
       gbc.fill = GridBagConstraints.HORIZONTAL;
       gbc.anchor = GridBagConstraints.FIRST_LINE_END;  
       add(productsButton, gbc);

       gbc.weightx = 0.1;
       gbc.weighty = 0.2;
       gbc.gridx = 0;
       gbc.gridy++;
       gbc.insets = new Insets(0, 20, 0, 10); // Add some spacing to the left
       gbc.fill = GridBagConstraints.HORIZONTAL;
       gbc.anchor = GridBagConstraints.FIRST_LINE_END;
       add(groupbuysButton, gbc);

       gbc.weightx = 0.1;
       gbc.weighty = 3;
       gbc.gridx = 0;
       gbc.gridy++;
       gbc.insets = new Insets(0, 20, 0, 10); // Add some spacing to the left
       gbc.fill = GridBagConstraints.HORIZONTAL;
       gbc.anchor = GridBagConstraints.FIRST_LINE_END;
       add(analyticsButton, gbc);

    }

    private void createSideBarButtons(){
        ImageIcon productIcon = resizeIconForButton("src/gbuysytem/GUI/Sidebar/img/product.png");
        productsButton = new RoundedButton("  Products", productIcon);
        productsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        productsButton.setButtonColor(Color.white);
        productsButton.setForeground(Color.BLACK);
        productsButton.setDrawBorder(false);
        productsButton.setButtonFont(GbuyFont.MULI_SEMI_BOLD.deriveFont(14f));
        productsButton.setHorizontalAlignment(SwingConstants.LEFT);

        ImageIcon groupbuysIcon = resizeIconForButton("src/gbuysytem/GUI/Sidebar/img/groupbuys.png");
        groupbuysButton = new RoundedButton("  Groupbuys", groupbuysIcon);
        groupbuysButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        groupbuysButton.setButtonColor(Color.white);
        groupbuysButton.setForeground(Color.BLACK);
        groupbuysButton.setDrawBorder(false);
        groupbuysButton.setButtonFont(GbuyFont.MULI_SEMI_BOLD.deriveFont(14f));
        groupbuysButton.setHorizontalAlignment(SwingConstants.LEFT);

        ImageIcon analyticsIcon = resizeIconForButton("src/gbuysytem/GUI/Sidebar/img/analytics.png");
        analyticsButton = new RoundedButton("  Analytics", analyticsIcon);
        analyticsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        analyticsButton.setButtonColor(Color.white);
        analyticsButton.setForeground(Color.BLACK);
        analyticsButton.setDrawBorder(false);
        analyticsButton.setButtonFont(GbuyFont.MULI_SEMI_BOLD.deriveFont(14f));
        analyticsButton.setHorizontalAlignment(SwingConstants.LEFT);

    }

    private ImageIcon resizeIconForButton(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image resizedImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        return resizedIcon;
    }

}
