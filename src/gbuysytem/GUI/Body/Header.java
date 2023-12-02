package gbuysytem.GUI.Body;

import java.awt.Color;
import java.awt.Dimension;

import gbuysytem.GUI.Body.DashboardPanels.Misc.RoundedPanel;

public class Header extends RoundedPanel{
      
   public Header(){

      Dimension dim = getPreferredSize();
      dim.height =50;
      setPreferredSize(dim);
      setBackground(Color.decode("#FDFEFE"));
      //  setBorder(BorderFactory.createEtchedBorder());
      setShady(false);
      int arc = 20;
      setArcs(new Dimension(arc, arc));
   }
}
