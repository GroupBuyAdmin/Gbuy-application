package gbuysytem.GUI.Body.DashboardPanels.AnalyticsPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gbuysytem.GUI.Body.DashboardPanels.ColorPalettes.AnalyticsPalette;
import gbuysytem.GUI.Body.DashboardPanels.Misc.PanelReturner;

public class Analytics implements PanelReturner{
    private JPanel masterPanel;
    private final Color backgroundColor = Color.decode("#F7F8FA");

    public Analytics(Dimension dimension){
        this.masterPanel = new JPanel();
        masterPanel.setBackground(backgroundColor);
        masterPanel.setPreferredSize(dimension);
        masterPanel.setLayout(new BorderLayout());
        
        GridLayout layout = new GridLayout(0, 2, 50, 50);
        JPanel analyticContainer = new JPanel();
        analyticContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        analyticContainer.setLayout(layout);
 
        ImageIcon customerIcon = new ImageIcon("src/gbuysytem/GUI/Body/DashboardPanels/AnalyticsPanel/img/customer.png", "customer icon");
        ImageIcon revenueIcon = new ImageIcon("src/gbuysytem/GUI/Body/DashboardPanels/AnalyticsPanel/img/revenue.png", "revenue icon");
        ImageIcon profitsIcon = new ImageIcon("src/gbuysytem/GUI/Body/DashboardPanels/AnalyticsPanel/img/profits.png", "profits icon");
        ImageIcon invoicesIcon = new ImageIcon("src/gbuysytem/GUI/Body/DashboardPanels/AnalyticsPanel/img/invoice.png", "invoices icon");

        Dimension panelDimension = new Dimension(250, 150);

  
        AnalyticsPanel customerPanel = new AnalyticsPanel("Customer", AnalyticsPalette.CUSTOMER.getColor(), customerIcon, panelDimension);
        analyticContainer.add(customerPanel.getPanel());

        AnalyticsPanel revenuePanel = new AnalyticsPanel("Revenue", AnalyticsPalette.REVENUE.getColor(), revenueIcon, panelDimension);
        analyticContainer.add(revenuePanel.getPanel());
    
        AnalyticsPanel profitsPanel = new AnalyticsPanel("Profits", AnalyticsPalette.PROFIT.getColor(), profitsIcon, panelDimension);
        analyticContainer.add(profitsPanel.getPanel());
    
        AnalyticsPanel invoicePanel = new AnalyticsPanel("Invoice", AnalyticsPalette.INVOICE.getColor(), invoicesIcon, panelDimension);
        analyticContainer.add(invoicePanel.getPanel());

        masterPanel.add(analyticContainer,BorderLayout.CENTER);
    }


    @Override
    public JPanel getPanel() {
        return masterPanel;
    }

    public void testPanel(){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(masterPanel);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        Analytics n = new Analytics(new Dimension(1000, 600));
        n.testPanel();
    }
}
