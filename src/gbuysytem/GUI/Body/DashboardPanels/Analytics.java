package gbuysytem.GUI.Body.DashboardPanels;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

//use getPanel() method to return instance of panel

public class Analytics implements PanelReturner{
    private JPanel allPanels, customerPanel, profitsPanel, revenuePanel, invoicesPanel;
    private AnalyticPanel customerData, revenueData, invoicesData, profitData;

    private Dimension analyticDimension;
    private Dimension panelDimension;

    //final
    private final Border analyticsBorder = BorderFactory.createEmptyBorder(50, 50, 50, 50);
    private final GridLayout analyticsLayout = new GridLayout(0, 2, 50, 50);
    private final Color tempColor = Color.CYAN;
    
    //images
    private ImageIcon customerIcon;
    private ImageIcon profitsIcon;
    private ImageIcon revenueIcon;
    private ImageIcon invoicesIcon;

    public AnalyticPanel getCustomerData() {
        return customerData;
    }

    public void setCustomerData(AnalyticPanel customerData) {
        this.customerData = customerData;
    }

    public AnalyticPanel getRevenueData() {
        return revenueData;
    }

    public void setRevenueData(AnalyticPanel revenueData) {
        this.revenueData = revenueData;
    }

    public AnalyticPanel getInvoicesData() {
        return invoicesData;
    }

    public void setInvoicesData(AnalyticPanel invoicesData) {
        this.invoicesData = invoicesData;
    }

    public AnalyticPanel getProfitData() {
        return profitData;
    }

    public void setProfitData(AnalyticPanel profitData) {
        this.profitData = profitData;
    }



    public Analytics(Dimension d){
        analyticDimension = d;
        setSizes();

        generateIcons();

        generatePanels();

        createAnalyticsPanel();


        addAllToAllPanels();

    }

    private void generateIcons() {
        this.customerIcon = new ImageIcon("src/gbuysytem/GUI/Body/DashboardPanels/img/customer.png", "customer icon");
        this.revenueIcon = new ImageIcon("src/gbuysytem/GUI/Body/DashboardPanels/img/revenue.png", "revenue icon");
        this.profitsIcon = new ImageIcon("src/gbuysytem/GUI/Body/DashboardPanels/img/profits.png", "profits icon");
        this.invoicesIcon = new ImageIcon("src/gbuysytem/GUI/Body/DashboardPanels/img/invoice.png", "invoices icon");
    }

    private void setSizes() {
        panelDimension = new Dimension(analyticDimension.width/4, analyticDimension.height/4);
    }


    private void generatePanels() {
        allPanels = new JPanel();
        allPanels.setPreferredSize(analyticDimension);

        customerPanel = new JPanel();
        profitsPanel = new JPanel();
        revenuePanel = new JPanel();
        invoicesPanel = new JPanel();

        customerData = new AnalyticPanel("Customer", null, customerIcon, panelDimension);
        revenueData = new AnalyticPanel("Revenue", null, revenueIcon, panelDimension);
        invoicesData = new AnalyticPanel("Invoices", null, invoicesIcon, panelDimension);
        profitData = new AnalyticPanel("Profit", null, profitsIcon, panelDimension);

    }

    private void createAnalyticsPanel(){
        createCustomerPanel();
        createRevenuePanel();
        createInvoicePanel();
        createProfitsPanel();
    }

    //creating panels method
    private void createCustomerPanel() {
        customerPanel.setLayout(new BorderLayout());
        
        customerPanel.setPreferredSize(panelDimension);
        customerPanel.setBackground(tempColor);
        
        customerPanel.add(customerData.getPanel(), BorderLayout.CENTER);

    }

    private void createRevenuePanel() {
        revenuePanel.setLayout(new BorderLayout());
        
        revenuePanel.setPreferredSize(panelDimension);
        revenuePanel.setBackground(tempColor);

        revenuePanel.add(revenueData.getPanel(), BorderLayout.CENTER);
    }


    private void createInvoicePanel() {
        invoicesPanel.setLayout(new BorderLayout());
        
        invoicesPanel.setPreferredSize(panelDimension);
        invoicesPanel.setBackground(tempColor);

        invoicesPanel.add(invoicesData.getPanel(), BorderLayout.CENTER);
    }


    private void createProfitsPanel() {
        profitsPanel.setLayout(new BorderLayout());

        profitsPanel.setPreferredSize(panelDimension);
        profitsPanel.setBackground(tempColor);

        profitsPanel.add(profitData.getPanel(), BorderLayout.CENTER);

    }


    private void addAllToAllPanels(){
        allPanels.setBorder(analyticsBorder);
        allPanels.setLayout(analyticsLayout);
        allPanels.add(customerPanel);
        allPanels.add(profitsPanel);
        allPanels.add(revenuePanel);
        allPanels.add(invoicesPanel);
    }

    
    @Override
    public JPanel getPanel() {
        return allPanels;
    }

    public void testPanel(){
        JFrame test = new JFrame();
        test.setSize(analyticDimension);
        test.add(allPanels);
        test.setVisible(true);
    }

    public static void main(String[] args) {
        Analytics a = new Analytics(new Dimension(1000, 600));
        a.testPanel();
    }
}
