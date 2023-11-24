package gbuysytem.GUI.Sidebar;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Sidebar  extends JPanel{
    
    
    public Sidebar(){
          
        
        Dimension dim = getPreferredSize();
        dim.width =180;
        setPreferredSize(dim);
        Border border = BorderFactory.createEtchedBorder();
        setBorder(border);
        setBackground(Color.decode("#FCFDFF"));

    }
}
