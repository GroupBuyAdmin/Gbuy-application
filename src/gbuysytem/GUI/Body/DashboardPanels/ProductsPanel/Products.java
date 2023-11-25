package gbuysytem.GUI.Body.DashboardPanels.ProductsPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gbuysytem.GUI.Body.DashboardPanels.PanelReturner;

public class Products implements PanelReturner{
    private JPanel allPanels;
    private JPanel topPanel;
    private JPanel listPanel;

    //might change to jlabelfor icon
    private JButton filterButton;
    private JButton addProductButton;
    
    //sizes and borders
    private Dimension productDimension;
    private Dimension topPanelDimension;
    private Dimension lisPanelDimension;

    public Products(Dimension d){
        productDimension = d;
        setSizes();

        allPanels = new JPanel();
        topPanel = new JPanel();
        listPanel = new JPanel();
        filterButton = new JButton();
        addProductButton = new JButton();
        
        createTopPanel();
        createlistPanel();
        
        allPanels.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        allPanels.setLayout(new BoxLayout(allPanels, BoxLayout.Y_AXIS));
        allPanels.setPreferredSize(productDimension);
        allPanels.add(topPanel);
        allPanels.add(rigidSpacing(productDimension, 10));
        allPanels.add(listPanel);
    }


    //top panel
    private void createTopPanel(){
        topPanel.setPreferredSize(topPanelDimension);
        topPanel.setBackground(Color.gray);
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, topPanelDimension.height));
        topPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        createTopPanelButtons();
    }
    
    private void createTopPanelButtons(){
        topPanel.add(filterButton);
        filterButton.setSize(new Dimension(50,50));
        filterButton.setText("Filter");

        topPanel.add(addProductButton);
        addProductButton.setSize(new Dimension(50,50));
        addProductButton.setText("Add Product");
    }

    //list panel
    private void createlistPanel(){
        listPanel.setPreferredSize(lisPanelDimension);
        listPanel.setBackground(Color.lightGray);
    }

    private Component rigidSpacing(Dimension d, int height){
        return Box.createRigidArea(new Dimension(d.width, height));
    }

    private void setSizes(){
        topPanelDimension = new Dimension(productDimension.width, productDimension.height/10);
        lisPanelDimension = new Dimension(productDimension.width, productDimension.height*9/10);
    }

    @Override
    public JPanel getPanel(){
        return allPanels;
    }

    public void testPanel(){
        JFrame test = new JFrame();
        test.setSize(productDimension);
        test.add(allPanels);
        test.setVisible(true);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Products p = new Products(new Dimension(1000,600));
        p.testPanel();
    }

}
