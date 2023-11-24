package gbuysytem.GUI.Sidebar;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Sidebar  extends JPanel{
    
    JLabel logo,product,groupbuys,analytics;
    JLabel separator;
    
    public Sidebar(){

        logo = new JLabel("Groupbuy");
        ImageIcon LogoIcon = new ImageIcon("src/gbuysytem/GUI/Sidebar/img/logo.png");       
        Image LogoScaledImage = LogoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon setLogoIcon = new ImageIcon(LogoScaledImage);    
        Font projectFont = logo.getFont();
        logo.setFont(new Font(projectFont.getName(),Font.BOLD,20));
        logo.setIcon(setLogoIcon);                         
        logo.setForeground(new Color(0x4D7CFF));
   

        product = new JLabel("Products");
        setIcons( product, "src/gbuysytem/GUI/Sidebar/img/product.png",0x0D1720);
        groupbuys = new JLabel("Groupbuys");
        setIcons( groupbuys, "src/gbuysytem/GUI/Sidebar/img/groupbuys.png",0x0D1720);  
        analytics = new JLabel("Analytics");
        setIcons( analytics, "src/gbuysytem/GUI/Sidebar/img/analytics.png",0x0D1720);

  
        
        Dimension dim = getPreferredSize();
        dim.width =180;
        setPreferredSize(dim);
        Border border = BorderFactory.createEtchedBorder();
        setBorder(border);
        setBackground(Color.decode("#FCFDFF"));
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
       gbc.weighty = 0.5;
       gbc.gridx = 0;
       gbc.gridy=0;
       gbc.insets = new Insets(4, 5, 0, 0); // Add some spacing to the left
       gbc.fill = GridBagConstraints.NONE;
       gbc.anchor = GridBagConstraints.FIRST_LINE_START;    
       add(logo, gbc);
       
  

       gbc.weightx = 0.1;
       gbc.weighty = 0.2;
       gbc.gridx = 0;
       gbc.gridy++;
       gbc.insets = new Insets(0, 30, 0, 0); // Add some spacing to the left
       gbc.fill = GridBagConstraints.HORIZONTAL;
       gbc.anchor = GridBagConstraints.FIRST_LINE_END;  
       add(product, gbc);

       gbc.weightx = 0.1;
       gbc.weighty = 0.2;
       gbc.gridx = 0;
       gbc.gridy++;
       gbc.insets = new Insets(0, 30, 0, 0); // Add some spacing to the left
       gbc.fill = GridBagConstraints.HORIZONTAL;
       gbc.anchor = GridBagConstraints.FIRST_LINE_END;
       add(groupbuys, gbc);

       gbc.weightx = 0.1;
       gbc.weighty = 3;
       gbc.gridx = 0;
       gbc.gridy++;
       gbc.insets = new Insets(0, 30, 0, 0); // Add some spacing to the left
       gbc.fill = GridBagConstraints.HORIZONTAL;
       gbc.anchor = GridBagConstraints.FIRST_LINE_END;
       add(analytics, gbc);

    }
}
