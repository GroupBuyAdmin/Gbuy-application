package gbuysytem.GUI.Body;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Header extends JPanel{
      
   public Header(){

    Dimension dim = getPreferredSize();
    dim.height =50;
    setPreferredSize(dim);
    setBackground(Color.decode("#FDFEFE"));
    setBorder(BorderFactory.createEtchedBorder());
   
   }
}
