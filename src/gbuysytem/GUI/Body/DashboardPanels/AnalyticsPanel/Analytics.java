package gbuysytem.GUI.Body.DashboardPanels.AnalyticsPanel;


import javax.swing.JPanel;
import gbuysytem.GUI.Body.DashboardPanels.PanelReturner;

public class Analytics implements PanelReturner{
    private JPanel allPanels, customerPanel, profitsPanel, revenuePanel, invoicesPanel;

    public Analytics(){
        allPanels = new JPanel();

        customerPanel = new JPanel();
        profitsPanel = new JPanel();
        revenuePanel = new JPanel();
        invoicesPanel = new JPanel();

        createAnalyticsPanel();
    }

    private void createAnalyticsPanel(){
        allPanels.setLayout(null);
    }

    
    @Override
    public JPanel getPanel() {
        return allPanels;
    }
}
