package gbuysytem.GUI.Body;

 
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;




// inside body 
//  - Products
//  -Groupbuys
//  -analytics  
public class Content extends JPanel{

   
    private Dashboard dashboard;
    private Header header;
    private CreateProductPopUp cpp;
    public Content(){

        header = new Header();
        dashboard = new Dashboard();
        cpp = new CreateProductPopUp();

        header.setDashboard(cpp);

        Border border = BorderFactory.createEtchedBorder();
        Border margin = BorderFactory.createEmptyBorder(0, 10, 0, 0);     
        setBorder(BorderFactory.createCompoundBorder(margin, border));

        setSize(1000,600);
        setBodyLayout();
    }

    public void setBodyLayout(){

        setLayout(new GridBagLayout());

        GridBagConstraints gbc =  new GridBagConstraints();
          
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy =0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.ipady =10;
        add(header, gbc);
        
        gbc.weightx = 0.1;
        gbc.weighty = 3;
        gbc.gridx = 0;
        gbc.gridy =1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.ipady =10;   
        add(dashboard, gbc);
    }  
}
