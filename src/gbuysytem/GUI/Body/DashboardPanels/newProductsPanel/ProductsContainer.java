package gbuysytem.GUI.Body.DashboardPanels.newProductsPanel;

import javax.swing.*;
import java.awt.*;
import gbuysytem.GUI.Body.DashboardPanels.PanelReturner;

public class ProductsContainer implements PanelReturner{
    //main container
    private JPanel masterPanel;
    private JPanel topPanel;
    private JPanel centerPanel;
    
    //center panel 
    private JPanel centerHeaderPanel;
    private JPanel centerListPanel;
    private JPanel centerScrollBarPanel;

    //centerHeaderPanel
    private JLabel image, category, price, quantity, name, description, controls;

    //centerlistpanel

    
    public ProductsContainer(Dimension masterPanelDimension){
        setupMainContainerPanel(masterPanelDimension);

        //inside centerPanel
        this.centerHeaderPanel = new JPanel();
        this.centerListPanel = new JPanel();
        this.centerScrollBarPanel = new JPanel();

        centerPanel.setLayout(new BorderLayout());

        centerPanel.add(centerHeaderPanel, BorderLayout.NORTH);
        centerPanel.add(centerListPanel, BorderLayout.CENTER);
        centerPanel.add(centerScrollBarPanel, BorderLayout.EAST);

        centerHeaderPanel.setBackground(Color.red);
        centerListPanel.setBackground(Color.GREEN);
        centerScrollBarPanel.setBackground(Color.orange);

        //inside centerHeader Panel ..might change to icons
        this.image = new JLabel("image", SwingConstants.CENTER);
        this.category = new JLabel("category", SwingConstants.CENTER);
        this.price = new JLabel("price", SwingConstants.CENTER);
        this.quantity = new JLabel("quantity", SwingConstants.CENTER);
        this.name = new JLabel("name", SwingConstants.CENTER);
        this.description = new JLabel("description", SwingConstants.CENTER);
        this.controls = new JLabel("controls", SwingConstants.CENTER);

        centerHeaderPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        int x = 0;
        int y = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(0, 2, 0, 2);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addToCenterHeader(image, x, y, gbc);
        addToCenterHeader(category, ++x, y, gbc);
        addToCenterHeader(price, ++x, y, gbc);
        addToCenterHeader(quantity, ++x, y, gbc);
        addToCenterHeader(name, ++x, y, gbc);
        addToCenterHeader(description, ++x, y, gbc);

        gbc.weightx = 1;
        gbc.gridwidth = 2;
        addToCenterHeader(controls, ++x, y, gbc);


        setupCenterListPanel();

        

    }

    private void setupCenterListPanel() {





        //inside centerList panel
        centerListPanel.setLayout(new GridBagLayout());

        SingleProduct p1 = new SingleProduct("show", "$10");
        
        JPanel createdProduct1 = productContainerCreator(p1);

        GridBagConstraints gcb = new GridBagConstraints();
        int x = 0;
        int y = 0;

        gcb.weightx = 1.0;
        gcb.weighty = 1.0;
        gcb.insets = new Insets(0, 0, 10, 0);
        gcb.fill = GridBagConstraints.HORIZONTAL;
        









    }

    private JPanel productContainerCreator(SingleProduct product) {
        JPanel createdProduct;
        JLabel listImage, listCategory, listPrice, listQuantity, listName, listDescription, listEdit, listDelete;
        
        listImage = new JLabel(product.getImage(), SwingConstants.CENTER);
        listCategory = new JLabel(product.getCategory(), SwingConstants.CENTER);
        listPrice = new JLabel(product.getPrice(), SwingConstants.CENTER);
        listQuantity = new JLabel(product.getQuantity(), SwingConstants.CENTER);
        listName = new JLabel(product.getName(), SwingConstants.CENTER);
        listDescription = new JLabel(product.getDescription());
        listEdit = new JLabel("Edit", SwingConstants.CENTER);
        listDelete = new JLabel("Delete", SwingConstants.CENTER);

        createdProduct = new JPanel();
        createdProduct.setBackground(Color.lightGray);

        createdProduct.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();        
        int x = 0;
        int y = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(0, 2, 0, 2);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addToCreatedProduct(createdProduct, listImage, x, y, gbc);
        addToCreatedProduct(createdProduct, listCategory, ++x, y, gbc);
        addToCreatedProduct(createdProduct, listPrice, ++x, y, gbc);
        addToCreatedProduct(createdProduct, listQuantity, ++x, y, gbc);
        addToCreatedProduct(createdProduct, listName, ++x, y, gbc);
        addToCreatedProduct(createdProduct, listDescription, ++x, y, gbc);
        addToCreatedProduct(createdProduct, listEdit, ++x, y, gbc);
        addToCreatedProduct(createdProduct, listDelete, ++x, y, gbc);

        return createdProduct;

    }

    private void addToCreatedProduct(JPanel jPanel, JLabel jLabel, int x, int y, GridBagConstraints gbc){
        gbc.gridx = x;
        gbc.gridy = y;
        jPanel.add(jLabel, gbc);
    }   










    private void addToCenterHeader(JLabel jLabel, int x, int y, GridBagConstraints gbc){
        gbc.gridx = x;
        gbc.gridy = y;
        centerHeaderPanel.add(jLabel, gbc);
    }
    

    private void setupMainContainerPanel(Dimension masterPanelDimension) {
        this.masterPanel = new JPanel();
        this.topPanel = new JPanel();
        this.centerPanel = new JPanel();

        masterPanel.setPreferredSize(masterPanelDimension);
        masterPanel.setLayout(new BorderLayout());
        masterPanel.add(topPanel,BorderLayout.NORTH);
        masterPanel.add(centerPanel, BorderLayout.CENTER);

        topPanel.setBackground(Color.yellow);
        centerPanel.setBackground(Color.blue);

        //setting up topPanel
        JButton filter = new JButton("FILTER");
        JButton createProduct = new JButton("CREATE PRODUCT");

        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        topPanel.add(filter);
        topPanel.add(createProduct);
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
        ProductsContainer p = new ProductsContainer(new Dimension(1000, 600));
        p.testPanel();
    }
}
