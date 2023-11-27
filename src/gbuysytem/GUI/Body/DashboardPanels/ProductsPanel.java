package gbuysytem.GUI.Body.DashboardPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class ProductsPanel implements PanelReturner{
    //return instance of productPane to access products
    private ProductPane productPane;

    //main containers
    private JPanel masterPanel;
    private JPanel buttonPanel;
    private JPanel headerPanel;
    private JPanel listPanel;

    private Dimension buttonPanelDimension;
    private Dimension headerPanelDimension;
    private Dimension listPanelDimension;

    //inside buttonPanel;
    private JPanel buttonContainer;
    private JLabel filter;
    private JLabel createProduct;

    //inside headerPanel;
    private JPanel headerContainer;
    private JPanel imagePanel, categoryPanel, pricePanel, quantityPanel, namePanel, descriptionPanel, emptyPanel1, emptyPanel2;
    private JLabel image, category, price, quantity, name, description;

    //inside listpanel

    //colors
    Color buttonPanelColor = Color.decode("#a5d8ff");
    Color headerPanelColor = Color.decode("#ffc9c9");
    Color listPanelColor = Color.decode("#99e9f2");

    Color jLabelColor = Color.decode("#ffec99");
    Color buttonContainerColor = Color.decode("#b2f2bb");


    public ProductsPanel(Dimension masterPanelDimension) {
        generateMainComponents();
        setSizeMainComponents(masterPanelDimension);
        setInnerPanelSizes();
        
        setupButtonPanel();
        setupHeaderPanels();
        setupHeaderPanelTexts();

        productPane = new ProductPane(listPanel.getPreferredSize());
        productPaneTester();
        listPanel.add(productPane.getPanel());
        listPanel.addComponentListener(new ComponentListener() {
            public void componentResized(ComponentEvent e) {
                productPane.autoResizeAll(new Dimension(listPanel.getWidth(), listPanel.getHeight()), headerPanel.getPreferredSize());
            }
            public void componentMoved(ComponentEvent e) {}
            public void componentShown(ComponentEvent e) {}
            public void componentHidden(ComponentEvent e) {}
        });

        colorizeMainComponents();
        addAllToMasterPanel();
    }

    private void setupHeaderPanelTexts() {
        image = new JLabel("Image", SwingConstants.CENTER);
        category = new JLabel("Categeory", SwingConstants.CENTER);
        price = new JLabel("Price", SwingConstants.CENTER);
        quantity = new JLabel("Quantity", SwingConstants.CENTER);
        name = new JLabel("Name", SwingConstants.CENTER);
        description = new JLabel("Description", SwingConstants.CENTER);

        imagePanel.add(image);
        categoryPanel.add(category);
        pricePanel.add(price);
        quantityPanel.add(quantity);
        namePanel.add(name);
        descriptionPanel.add(description);
    }

    private void setupHeaderPanels() {
        headerContainer = new JPanel();
        imagePanel = new JPanel(new BorderLayout());
        categoryPanel = new JPanel(new BorderLayout());
        pricePanel = new JPanel(new BorderLayout());
        quantityPanel = new JPanel(new BorderLayout());
        namePanel = new JPanel(new BorderLayout());
        descriptionPanel = new JPanel(new BorderLayout());
        emptyPanel1 = new JPanel(new BorderLayout());
        emptyPanel2 = new JPanel(new BorderLayout());
        
        headerContainer.setPreferredSize(new Dimension(headerPanel.getSize()));
        headerContainer.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        headerPanel.addComponentListener(new ComponentListener() {
            public void componentResized(ComponentEvent e) {
                headerContainer.setPreferredSize(new Dimension(headerPanel.getWidth(), headerPanel.getHeight()));
                setHeaderPanelComponentSizes(new LinkedList<>(Arrays.asList(imagePanel, categoryPanel, pricePanel, quantityPanel, namePanel, descriptionPanel, emptyPanel1, emptyPanel2)), headerContainer.getPreferredSize());
                headerContainer.revalidate();
            }
            public void componentMoved(ComponentEvent e) {}
            public void componentShown(ComponentEvent e) {}
            public void componentHidden(ComponentEvent e) {}
        });

        SpringLayout springLayout = new SpringLayout();
        headerContainer.setLayout(springLayout);

        //spring layout constraints
        Spring xSpring = Spring.constant(5);
        Spring ySpring = Spring.constant(0);

        //imagePanel constraints
        springLayout.putConstraint(SpringLayout.WEST, imagePanel, xSpring, SpringLayout.WEST, headerContainer);
        springLayout.putConstraint(SpringLayout.NORTH, imagePanel, ySpring, SpringLayout.NORTH, headerContainer);
                
        //categoryPanel constraints
        springLayout.putConstraint(SpringLayout.WEST, categoryPanel, xSpring, SpringLayout.EAST, imagePanel);
        springLayout.putConstraint(SpringLayout.NORTH, categoryPanel, ySpring, SpringLayout.NORTH, headerContainer);

        //pricePanel constraints
        springLayout.putConstraint(SpringLayout.WEST, pricePanel, xSpring, SpringLayout.EAST, categoryPanel);
        springLayout.putConstraint(SpringLayout.NORTH, pricePanel, ySpring, SpringLayout.NORTH, headerContainer);
        
        //quantityPanel constraints
        springLayout.putConstraint(SpringLayout.WEST, quantityPanel, xSpring, SpringLayout.EAST, pricePanel);
        springLayout.putConstraint(SpringLayout.NORTH, quantityPanel, ySpring, SpringLayout.NORTH, headerContainer);
        
        //namePanel constraints
        springLayout.putConstraint(SpringLayout.WEST, namePanel, xSpring, SpringLayout.EAST, quantityPanel);
        springLayout.putConstraint(SpringLayout.NORTH, namePanel, ySpring, SpringLayout.NORTH, headerContainer);
        
        //descriptionPanel constraints
        springLayout.putConstraint(SpringLayout.WEST, descriptionPanel, xSpring, SpringLayout.EAST, namePanel);
        springLayout.putConstraint(SpringLayout.NORTH, descriptionPanel, ySpring, SpringLayout.NORTH, headerContainer);
        
        //emptyPanel1 constraits
        springLayout.putConstraint(SpringLayout.WEST, emptyPanel1 , xSpring, SpringLayout.EAST, descriptionPanel);
        springLayout.putConstraint(SpringLayout.NORTH, emptyPanel1, ySpring, SpringLayout.NORTH, headerContainer);

        //emptyPanel2 constraints
        springLayout.putConstraint(SpringLayout.WEST, emptyPanel2 , xSpring, SpringLayout.EAST, emptyPanel1);
        springLayout.putConstraint(SpringLayout.NORTH, emptyPanel2, ySpring, SpringLayout.NORTH, headerContainer);
        springLayout.putConstraint(SpringLayout.EAST, emptyPanel2 , 20, SpringLayout.EAST, headerContainer);
        
        
        LinkedList<JPanel> allHeaderPanels = new LinkedList<>(Arrays.asList(imagePanel, categoryPanel, pricePanel, quantityPanel, namePanel, descriptionPanel, emptyPanel1, emptyPanel2));
        addToHeaderContainer(allHeaderPanels);
        colorizeHeaderPanelComponents(allHeaderPanels);
        setHeaderPanelComponentSizes(allHeaderPanels, headerContainer.getPreferredSize());
        headerPanel.add(headerContainer);

    }

    private void setHeaderPanelComponentSizes(LinkedList<JPanel> allHeaderPanels, Dimension containerSize) {
        Dimension d = containerSize;
        for(JPanel j : allHeaderPanels){
            j.setPreferredSize(new Dimension(d.width * 1/8, d.height-10));
        }
    }

    private void colorizeHeaderPanelComponents(LinkedList<JPanel> allHeaderPanels){
        for(JPanel j : allHeaderPanels){
            j.setBackground(jLabelColor);
        }
    }

    private void addToHeaderContainer(LinkedList<JPanel> allHeaderPanels){
        for(JPanel j : allHeaderPanels){
            headerContainer.add(j);
        }
    }

    private void setupButtonPanel() {
        //setting up headerpanel
        buttonContainer = new JPanel();
        filter = new JLabel("Filter");
        createProduct = new JLabel("+ Add Product");
        buttonPanel.setLayout(new BorderLayout());        
        buttonContainer.setBackground(buttonContainerColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
        buttonPanel.add(buttonContainer, BorderLayout.CENTER);
        buttonContainer.setLayout(new FlowLayout(FlowLayout.RIGHT, 80, 0));
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        buttonContainer.add(filter);
        buttonContainer.add(createProduct);
    }

    private void addAllToMasterPanel() {
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel.add(buttonPanel);
        masterPanel.add(headerPanel);
        masterPanel.add(listPanel);
    }

    private void setInnerPanelSizes() {
        buttonPanel.setPreferredSize(buttonPanelDimension);
        headerPanel.setPreferredSize(headerPanelDimension);
        listPanel.setPreferredSize(listPanelDimension);
    }

    private void setSizeMainComponents(Dimension masterPanelDimension) {
        this.buttonPanelDimension = new Dimension(masterPanelDimension.width, masterPanelDimension.height/12);
        this.headerPanelDimension = new Dimension(masterPanelDimension.width, masterPanelDimension.height/12);
        this.listPanelDimension = new Dimension(masterPanelDimension.width, masterPanelDimension.height*8/10);
    }

    private void generateMainComponents() {
        this.masterPanel = new JPanel();
        createGlobalPanelResizer();
        this.buttonPanel = new JPanel();
        this.headerPanel = new JPanel();
        this.listPanel = new JPanel();
    }

    private void createGlobalPanelResizer() {
        masterPanel.addComponentListener(new ComponentListener() {
            public void componentResized(ComponentEvent e) {
                buttonPanel.setPreferredSize(new Dimension(masterPanel.getWidth(), masterPanel.getHeight()/15));
                buttonPanel.setMaximumSize(new Dimension(masterPanel.getWidth(), buttonPanel.getHeight()));

                headerPanel.setPreferredSize(new Dimension(masterPanel.getWidth(), masterPanel.getHeight()/17));
                headerPanel.setMaximumSize(new Dimension(masterPanel.getWidth(), headerPanel.getHeight()));

                listPanel.setPreferredSize(new Dimension(masterPanel.getWidth(), masterPanel.getHeight()*8/10));

                buttonPanel.revalidate();
                headerPanel.revalidate();
                listPanel.revalidate();
            }
            public void componentMoved(ComponentEvent e) {}
            public void componentShown(ComponentEvent e) {}
            public void componentHidden(ComponentEvent e) {}
            
        });
    }

    private void colorizeMainComponents() {
        buttonPanel.setBackground(buttonPanelColor);
        headerPanel.setBackground(headerPanelColor);
        listPanel.setBackground(listPanelColor);
    }

    @Override
    public JPanel getPanel() {
        return masterPanel;
    }

    private void productPaneTester(){
        LinkedList<Product> list = new LinkedList<>();

        for(int i = 1; i <= 2; i++){
            list.add(new Product("p".concat(String.valueOf(i)), headerPanel.getPreferredSize()));
        }
        productPane.setListOfProducts(list);
    }

    public void testPanel(){
        JFrame f = new JFrame();
        f.setSize(1000,600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(masterPanel);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        ProductsPanel p = new ProductsPanel(new Dimension(1000, 600));
        p.testPanel();
    }

}
