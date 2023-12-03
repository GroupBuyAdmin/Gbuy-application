package gbuysytem.GUI.client;

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
 

public class sidebar extends JPanel {
      JLabel home,groupbuys,cart,profile;
    JLabel separator;
 
    public sidebar(){
         
   
  
        home = new JLabel("Home");
        setIcons( home, "src/gbuysytem/GUI/client/clientSidebarImg/home.png", 0x697081);
        groupbuys = new JLabel("Groupbuys");
        setIcons( groupbuys, "src/gbuysytem/GUI/client/clientSidebarImg/groupbuy.png",0x697081);  
        cart = new JLabel("Cart");
        setIcons( cart, "src/gbuysytem/GUI/client/clientSidebarImg/cart.png",0x697081);
        profile = new JLabel("Profile");
        setIcons( profile, "src/gbuysytem/GUI/client/clientSidebarImg/profile.png",0x697081);

  
        
        Dimension dim = getPreferredSize();
        dim.width =180;
        setPreferredSize(dim);
      
        setBackground(Color.decode("#FCFDFF"));
        setSidebarLayout();
    }



    void setIcons(JLabel jLabel,String groupbuysIcon,int color){
        ImageIcon Icon = new ImageIcon(groupbuysIcon);       
        Image ScaledImage = Icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon setIcon = new ImageIcon(ScaledImage);    
        Font Font = jLabel.getFont();
        jLabel.setFont(new Font(Font.getName(),Font.ROMAN_BASELINE,18));
        jLabel.setIcon(setIcon);                         
        jLabel.setForeground(new Color(color));
        jLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    

    public void setSidebarLayout(){
            
       setLayout(new GridBagLayout());

       GridBagConstraints gbc =  new GridBagConstraints();


       gbc.weightx = 0.1;
       gbc.weighty = 0.2;
       gbc.gridx = 0;
       gbc.gridy=0;
       gbc.insets = new Insets(50, 30, 0, 0); // Add some spacing to the left
       gbc.fill = GridBagConstraints.HORIZONTAL;
       gbc.anchor = GridBagConstraints.FIRST_LINE_END;  
       add(home, gbc);

       gbc.weightx = 0.1;
       gbc.weighty = 0.2;
       gbc.gridx = 0;
       gbc.gridy++;
       gbc.insets = new Insets(0, 30, 0, 0); // Add some spacing to the left
       gbc.fill = GridBagConstraints.HORIZONTAL;
       gbc.anchor = GridBagConstraints.FIRST_LINE_END;
       add(groupbuys, gbc);

       gbc.weightx = 0.1;
       gbc.weighty = 0.2;
       gbc.gridx = 0;
       gbc.gridy++;
       gbc.insets = new Insets(0, 30, 0, 0); // Add some spacing to the left
       gbc.fill = GridBagConstraints.HORIZONTAL;
       gbc.anchor = GridBagConstraints.FIRST_LINE_END;
       add(cart, gbc);

       gbc.weightx = 0.1;
       gbc.weighty = 3;
       gbc.gridx = 0;
       gbc.gridy++;
       gbc.insets = new Insets(0, 30, 0, 0); // Add some spacing to the left
       gbc.fill = GridBagConstraints.HORIZONTAL;
       gbc.anchor = GridBagConstraints.FIRST_LINE_END;
       add(profile, gbc);

   }

}