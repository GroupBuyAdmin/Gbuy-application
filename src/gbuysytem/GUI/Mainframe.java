package gbuysytem.GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import gbuysytem.GUI.Body.Content;
import gbuysytem.GUI.Sidebar.Sidebar;

public class Mainframe extends JFrame{
    
    private Content body;
    private Sidebar sidebar;

    public Mainframe(){
        super("GBuy");
           
        body = new Content();
        sidebar = new Sidebar();
        setDashboardLayout();  
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setDashboardLayout(){
        
        setLayout(new BorderLayout());

        add(sidebar,BorderLayout.WEST);
        add(body,BorderLayout.CENTER);
        
        
        setSize(1300,700);
        setVisible(true);
        setLocationRelativeTo(null);

    }
}
