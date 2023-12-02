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
           
        sidebar = new Sidebar();
        body = new Content(sidebar);
        setDashboardLayout();  
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setDashboardLayout(){
        
        setLayout(new BorderLayout());

        add(sidebar,BorderLayout.WEST);
        add(body,BorderLayout.CENTER);
        
        setSize(1440,800);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    public Sidebar getSideBar(){
        return sidebar;
    }
}
