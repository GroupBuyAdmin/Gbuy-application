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

import gbuysytem.GUI.Body.DashboardPanels.ColorPalettes.AnalyticsColorPalette.AnalyticsPalette;

//use getPanel() method to return instance of panel

public class Analytics implements PanelReturner{
    private JPanel masterPanel, customerPanel, profitsPanel, revenuePanel, invoicesPanel;
    private AnalyticPanel customerData, revenueData, invoicesData, profitData;

    private Dimension analyticDimension;
    private Dimension panelDimension;

    //final
    private final Border analyticsBorder = BorderFactory.createEmptyBorder(50, 50, 50, 50);
    private final GridLayout analyticsLayout = new GridLayout(0, 2, 50, 50);
    private final Color backgroundColor = Color.decode("#F7F8FA");
    
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
        panelDimension = new Dimension(analyticDimension.width/2, analyticDimension.height/2);
    }


    private void generatePanels() {
        masterPanel = new JPanel();
        masterPanel.setPreferredSize(analyticDimension);

        customerPanel = new JPanel();
        profitsPanel = new JPanel();
        revenuePanel = new JPanel();
        invoicesPanel = new JPanel();

        customerData = new AnalyticPanel("Customer", AnalyticsPalette.CUSTOMER.getColor(), customerIcon, panelDimension);
        revenueData = new AnalyticPanel("Revenue", AnalyticsPalette.REVENUE.getColor(), revenueIcon, panelDimension);
        invoicesData = new AnalyticPanel("Invoices", AnalyticsPalette.INVOICE.getColor(), invoicesIcon, panelDimension);
        profitData = new AnalyticPanel("Profit", AnalyticsPalette.PROFIT.getColor(), profitsIcon, panelDimension);

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
        customerPanel.setBackground(backgroundColor);
        
        customerPanel.add(customerData.getPanel(), BorderLayout.CENTER);

    }

    private void createRevenuePanel() {
        revenuePanel.setLayout(new BorderLayout());
        
        revenuePanel.setPreferredSize(panelDimension);
        revenuePanel.setBackground(backgroundColor);

        revenuePanel.add(revenueData.getPanel(), BorderLayout.CENTER);
    }


    private void createInvoicePanel() {
        invoicesPanel.setLayout(new BorderLayout());
        
        invoicesPanel.setPreferredSize(panelDimension);
        invoicesPanel.setBackground(backgroundColor);

        invoicesPanel.add(invoicesData.getPanel(), BorderLayout.CENTER);
    }


    private void createProfitsPanel() {
        profitsPanel.setLayout(new BorderLayout());

        profitsPanel.setPreferredSize(panelDimension);
        profitsPanel.setBackground(backgroundColor);

        profitsPanel.add(profitData.getPanel(), BorderLayout.CENTER);

    }


    private void addAllToAllPanels(){
        masterPanel.setBorder(analyticsBorder);
        masterPanel.setLayout(analyticsLayout);
        masterPanel.setBackground(backgroundColor);
        masterPanel.add(customerPanel);
        masterPanel.add(profitsPanel);
        masterPanel.add(revenuePanel);
        masterPanel.add(invoicesPanel);
    }

    
    @Override
    public JPanel getPanel() {
        return masterPanel;
    }

    public void testPanel(){
        JFrame test = new JFrame();
        test.setSize(analyticDimension);
        test.add(masterPanel);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setVisible(true);
    }

    public static void main(String[] args) {
        Analytics a = new Analytics(new Dimension(1000, 600));
        a.testPanel();
    }
}
