package gbuysytem.GUI.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class header  extends JPanel{
     JLabel logo;
      
    public header(){
        logo = new JLabel("Groupbuy");
        ImageIcon LogoIcon = new ImageIcon("src/gbuysytem/GUI/Sidebar/img/logo.png");       
        Image LogoScaledImage = LogoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon setLogoIcon = new ImageIcon(LogoScaledImage);    
        Font projectFont = logo.getFont();
        logo.setFont(new Font(projectFont.getName(),Font.BOLD,20));
        logo.setIcon(setLogoIcon);                         
        logo.setForeground(new Color(0x697081));
    
        Dimension dim = getPreferredSize();
        dim.height =60;
        setPreferredSize(dim);
        setBackground(Color.decode("#FDFEFE"));
       
        setHeaderLayout();
      }

      public void setHeaderLayout(){
        setLayout(new BorderLayout());
        add(logo, BorderLayout.WEST);
      }
}
