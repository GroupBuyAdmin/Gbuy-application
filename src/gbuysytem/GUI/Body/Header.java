package gbuysytem.GUI.Body;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Header extends JPanel {
      
   Dashboard dashboard;
   JButton button;
   CreateProductPopUp cpp;
   public Header(){
      button = new JButton("Create");
      button.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
              cpp.Inputs();          
              cpp.PopUp();
             
         }
      });
      
    
      setLayout(new GridBagLayout());

      Dimension dim = getPreferredSize();
      dim.height =50;
      setPreferredSize(dim);
      setBackground(Color.decode("#FDFEFE"));
      setBorder(BorderFactory.createEtchedBorder());

    
      GridBagConstraints gbc = new java.awt.GridBagConstraints();

      gbc.gridx=0;
      gbc.gridy =0;
      add(button,gbc);
   
   }

   public void setDashboard(CreateProductPopUp cpp){
            this.cpp = cpp;
   }

 
}
