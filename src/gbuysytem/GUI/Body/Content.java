package gbuysytem.GUI.Body;

 
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import gbuysytem.GUI.Sidebar.Sidebar;




// inside body 
//  - Products
//  -Groupbuys
//  -analytics  
public class Content extends JPanel{

   
    private Dashboard dashboard;
    private Header header;
    public Content(Sidebar sidebar){

        header = new Header();
        dashboard = new Dashboard(sidebar);
        setBorder(BorderFactory.createEmptyBorder(15, 60, 30, 60));

        setSize(1000,600);
        setBodyLayout();
    }

    public void setBodyLayout(){

        setLayout(new GridBagLayout());

        GridBagConstraints gbc =  new GridBagConstraints();
          
        gbc.weightx = 0.1;
        gbc.weighty = 0.0;
        gbc.gridx = 0;
        gbc.gridy =0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.ipady =10;
        add(header, gbc);
        
        gbc.weightx = 0.1;
        gbc.weighty = 3;
        gbc.gridx = 0;
        gbc.gridy =1;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.ipady =10;   
        add(dashboard, gbc);
    }  
}
