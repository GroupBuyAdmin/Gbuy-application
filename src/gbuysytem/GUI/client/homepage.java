package gbuysytem.GUI.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;



public class homepage extends JFrame{
    private sidebar sidebar;
    private header header;
    private ProductView prodView;

    public homepage (){
        super("GBuy");
           
     
        sidebar = new sidebar();
        header = new header();
        prodView = new ProductView();
        
        setDashboardLayout();  
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void setDashboardLayout(){
        
        setLayout(new BorderLayout());

        add(sidebar,BorderLayout.WEST);
         add(prodView,BorderLayout.CENTER);
        add(header,BorderLayout.NORTH);
      
        
        
        setSize(1300,700);
        setVisible(true);
        setLocationRelativeTo(null);

    }
}
