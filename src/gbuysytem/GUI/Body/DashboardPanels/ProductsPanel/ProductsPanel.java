package gbuysytem.GUI.Body.DashboardPanels.ProductsPanel;
import javax.swing.*;

import gbuysytem.GUI.Body.DashboardPanels.PanelReturner;
import gbuysytem.GUI.Body.DashboardPanels.RoundedButton;
import gbuysytem.GUI.Body.fonts.CustomFont;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsPanel implements PanelReturner{
    private JPanel masterPanel;
    private JPanel scrollablePanel;
    private JScrollPane scrollPane;
    private List<DashboardItemPanel> itemPanels;

    private final Color scrollablePanelColor = Color.decode("#FFFFFF");
    private final Color gridColor = Color.decode("#EEF0F3");

    public ProductsPanel(Dimension masterPanelDimension) {
        masterPanel = new JPanel();
        masterPanel.setPreferredSize(masterPanelDimension);

        itemPanels = new ArrayList<>();

        scrollablePanel = new JPanel();
        scrollablePanel.setLayout(new BoxLayout(scrollablePanel, BoxLayout.Y_AXIS)); 
        scrollablePanel.setBorder(BorderFactory.createLineBorder(gridColor));
        scrollablePanel.setBackground(scrollablePanelColor);

        scrollPane = new JScrollPane(scrollablePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createLineBorder(gridColor));
        scrollPane.setBackground(scrollablePanelColor);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
  
        setupHeaderPanel();                                                                 
        
        ButtonPanels buttonPanels = getButtonPanels();                                      

        masterPanel.setLayout(new BorderLayout());
        masterPanel.add(buttonPanels, BorderLayout.NORTH);
        masterPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private ButtonPanels getButtonPanels() {
        RoundedButton addButton = new RoundedButton("+  add product");
        addButton.setButtonColor(Color.blue);
        addButton.setForeground(Color.WHITE);
        addButton.setDrawBorder(false);
        addButton.setButtonFont(CustomFont.Franca_Medium.getFont().deriveFont(14f));      

        RoundedButton filterButton = new RoundedButton("Filter");
        filterButton.setButtonColor(Color.white);
        filterButton.setForeground(Color.BLACK);
        filterButton.setBorderColor(Color.gray);
        filterButton.setButtonFont(CustomFont.Franca_Medium.getFont().deriveFont(14f));

        ButtonPanels buttonPanels = new ButtonPanels(filterButton, addButton);
        setupButtonPanelBehaviour(addButton, filterButton);

       
        return buttonPanels;
    }

    private void setupButtonPanelBehaviour(JButton addButton, JButton filterButton) {

         addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             CreateProductPopUp popUp = new CreateProductPopUp();
             popUp.PopUp();         
            }
        });

        String url = "jdbc:mysql://localhost:3306/gbuy";
        String username = "root";
        String password = "";

        String sqlQuery = "SELECT name, price, quantity, category,details, image FROM product";

        try (
            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);
            // Creating a PreparedStatement for the SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            // Executing the query and getting the ResultSet
            ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            // Processing the result set     
            while (resultSet.next()) {
                String name = resultSet.getString("name");    
                String cat = resultSet.getString("category");
                String price = resultSet.getString("price");
                String qty = resultSet.getString("quantity");
                String detail = resultSet.getString("details");
                byte[] imageData = resultSet.getBytes("image");
    
                // Convert image data to an Image object
                ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(imageData).getImage());  
     
                Product p = new Product(imageIcon, name,  price, qty,cat, detail);
                addDashboardItem(p);
              
              
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Filter button clicked!");
                
            }
        });
    }

    private void setupItemPanelButtonListener(RoundedButton deleteButton, RoundedButton editButton, DashboardItemPanel itemPanel) {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                //implement delete algo here
                
                deleteDashboardItem(itemPanel);
                updateDashboard();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //implement edit algo here


                editDashboardItem(itemPanel);
                updateDashboard();
            }
        });
    }

    private void setupHeaderPanel() {
        String[] headerNames = {"Image", "Name", "Price", "Quantity", "Category", "Description", "Controls"};
        HeaderPanel headerPanel = new HeaderPanel(headerNames);
        scrollPane.setColumnHeaderView(headerPanel);
    }

    private void addDashboardItem(Product p) {
        Color deleteButtonColor = Color.decode("#E55A4F");
        Color editButtonColor = Color.decode("#49C0E5");


        RoundedButton deleteButton = new RoundedButton("Delete");
        deleteButton.setButtonColor(deleteButtonColor);
        deleteButton.setForeground(Color.white);
        deleteButton.setDrawBorder(false);
        deleteButton.setButtonFont(CustomFont.Franca_Medium.getFont().deriveFont(14f));

        RoundedButton editButton = new RoundedButton("Edit");
        editButton.setButtonColor(editButtonColor);
        editButton.setForeground(Color.white);
        editButton.setDrawBorder(false);
        editButton.setButtonFont(CustomFont.Franca_Medium.getFont().deriveFont(14f));

        DashboardItemPanel itemPanel = new DashboardItemPanel(p, editButton, deleteButton);
        
        setupItemPanelButtonListener(deleteButton, editButton, itemPanel);

        itemPanels.add(itemPanel);

        updateDashboard();
    }


    private void deleteDashboardItem(DashboardItemPanel itemPanel) {
        System.out.println("deleteing itemPanel at row " + itemPanels.indexOf(itemPanel));
        itemPanels.remove(itemPanel);
        scrollablePanel.remove(itemPanel);
    }

    private void editDashboardItem(DashboardItemPanel itemPanel) {
        System.out.println("editing itemPanel at row " + itemPanels.indexOf(itemPanel));
        // Implement edit functionality here
    }

    private void updateDashboard(){
        scrollablePanel.removeAll();

        for(DashboardItemPanel dashboardItem : itemPanels){
            // scrollablePanel.add(Box.createVerticalStrut(5));
            scrollablePanel.add(dashboardItem);
        }

        scrollablePanel.revalidate();
        scrollablePanel.repaint();
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

    // public static void main(String[] args) {
    //     ProductsPanel p = new ProductsPanel(new Dimension(1000, 600));
    //     p.testPanel();
    // }


}







class CreateProductPopUp extends JFrame{

    private JLabel name, price, qty, details;
    private JTextField nameField,priceField,qtyField;
    private JTextArea detailsArea;
    private JButton select,add,test;
    private JLabel imageLabel,comboLabel;
    private  JFileChooser fileChooser;
    private JComboBox empCombo;


    
    public CreateProductPopUp(){
        
        
        setLayout(new BorderLayout());  
        setSize(800,500);
       
        setLocationRelativeTo(null);
     
        add(titlePanel(),BorderLayout.NORTH);
        add(displayImage(),BorderLayout.CENTER);
        add(Inputs(),BorderLayout.EAST);

      
        
    }
    public JPanel titlePanel(){
        JPanel title = new JPanel();
        JLabel AddProduct = new JLabel("Add Product");
        title.setBackground(Color.decode("#FFFFFF"));
        AddProduct.setFont(new Font(AddProduct.getName(), Font.BOLD, 24));
        AddProduct.setForeground(Color.decode("#115E59"));
        Dimension dim = getPreferredSize();
        dim.height = 100;
        setPreferredSize(dim);
        

        title.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx =0;
        gbc.gridy=0;
        title.add(AddProduct,gbc);
        
        return title;
    }
 
    
    public  JPanel Inputs(){
      
        JPanel productDetails =  new JPanel();
        productDetails.setBackground(Color.decode("#FFFFFF"));
        Dimension dim = getPreferredSize();
        dim.width = 400;
        productDetails.setPreferredSize(dim);
       
        GridBagConstraints gbc = new GridBagConstraints();
     
        productDetails.setLayout(new GridBagLayout());

        gbc.weightx=0;
        name = new JLabel("Name: ");
        name.setFont(new Font(name.getName(), Font.PLAIN, 18));
        name.setForeground(Color.decode("#374151"));
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        productDetails.add(name, gbc);

        nameField = new JTextField(20); // Adjust the size according to your needs
        nameField.setText(nameField.getText());
        nameField.setFont(new Font(nameField.getName(), Font.PLAIN, 18));  
        nameField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        gbc.gridx = 1;
        gbc.gridy = 0;
        productDetails.add(nameField, gbc);

        price = new JLabel("Price: ");
        price.setFont(new Font(price.getName(), Font.PLAIN, 18));
        price.setForeground(Color.decode("#374151"));
        gbc.gridx = 0;
        gbc.gridy = 1;
        productDetails.add(price, gbc);

        priceField = new JTextField(20); // Adjust the size according to your needs
        priceField.setFont(new Font(priceField.getName(), Font.PLAIN, 18));  
        priceField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        gbc.gridx = 1;
        gbc.gridy = 1;
        productDetails.add(priceField, gbc);
        
        qty = new JLabel("Quantity: ");
        qty.setForeground(Color.decode("#374151"));
        qty.setFont(new Font(qty.getName(), Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 2;
        productDetails.add(qty, gbc);

        qtyField = new JTextField(20); // Adjust the size according to your needs
        qtyField.setFont(new Font(qtyField.getName(), Font.PLAIN, 18));
        qtyField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        gbc.gridx = 1;
        gbc.gridy = 2;
        productDetails.add(qtyField, gbc);
        
        comboLabel = new JLabel("Category: ");
        comboLabel.setForeground(Color.decode("#374151"));
        qty.setFont(new Font(comboLabel.getName(), Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 3;
        productDetails.add(comboLabel, gbc);

        empCombo = new JComboBox(); 
        DefaultComboBoxModel empModel =  new DefaultComboBoxModel();    
        empCombo.setFont(new Font(empCombo.getName(), Font.PLAIN, 18));
        empCombo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        empModel.addElement("Shoes");
        empModel.addElement("Shirts/Pants");
        empModel.addElement("Pants");
        empModel.addElement("Electronics");
        empModel.addElement("Fashion/Pants");
        empModel.addElement("Books");
        empCombo.setModel(empModel);
        gbc.gridx = 1;
        gbc.gridy = 3;
        productDetails.add(empCombo, gbc);
        
        details = new JLabel("Details: ");
        
        details.setForeground(Color.decode("#374151"));
        gbc.gridx = 0;
        gbc.gridy = 4;
        productDetails.add(details, gbc);
        details.setFont(new Font(details.getName(), Font.PLAIN, 18));
        detailsArea = new JTextArea(5, 20); // Adjust the size according to your needs
        detailsArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        productDetails.add(new JScrollPane(detailsArea), gbc);

        productDetails.setBorder(BorderFactory.createEtchedBorder());
        
        
        select = new JButton("Select:"); 
        select.setForeground(Color.decode("#374151")); 
        gbc.gridx = 1;
        gbc.gridy = 8;
        select.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        productDetails.add(select, gbc);
        
        select.setBackground(Color.decode("#115E59"));
        select.setFont(new Font("Arial", Font.BOLD, 14));
        select.setForeground(Color.WHITE);
        fileChooser = new JFileChooser();
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {          
                int result = fileChooser.showOpenDialog(CreateProductPopUp.this);
                 if (result == JFileChooser.APPROVE_OPTION) {                   
                    File selectedFile = fileChooser.getSelectedFile();
                     ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedFile.getPath()).getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT));
                     imageLabel.setIcon(imageIcon);    
            }        
       }});

        add = new JButton("Add");  
        gbc.gridx = 1;
        gbc.gridy = 9;
        add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add.setBackground(Color.decode("#115E59"));
        add.setFont(new Font("Arial", Font.BOLD, 14));
        add.setForeground(Color.WHITE);
        productDetails.add(add, gbc);
        add.addActionListener(new ActionListener() {          
            @Override
            public void actionPerformed(ActionEvent e) {
                createProduct();
                 erase();
               
                setVisible(false);
                
            }
        });

        productDetails.setBorder(BorderFactory.createEtchedBorder());
        
    
    return productDetails;
    }
    
    public JPanel displayImage(){
        JPanel imagePanel =  new JPanel();
        imagePanel.setBackground(Color.decode("#FFFFFF"));
        
        Dimension dim = getPreferredSize();
        dim.width = 300;
        imagePanel.setPreferredSize(dim);      
        imagePanel.setBorder(BorderFactory.createEtchedBorder());
        
        imageLabel = new JLabel();
        
        imageLabel.setPreferredSize(new Dimension(350, 350));
        imageLabel.setBorder(BorderFactory.createDashedBorder( Color.GRAY, 25, 10));
         
        imagePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx =0;
        gbc.gridy=0;
        imagePanel.add(imageLabel,gbc);
        
        return imagePanel;
    }

     private void connection() {
        String url = "jdbc:mysql://localhost:3306/client";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }   
    }


    public void createProduct(){

        String url = "jdbc:mysql://localhost:3306/gbuy";
        String username = "root";
        String password = "";
       

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);

            // Get the values from the text fields
            String nameToInsert = nameField.getText();
            String priceToInsert = priceField.getText();
            String qtyToInsert = qtyField.getText();
            String categoryToInsert = (String) empCombo.getItemAt(empCombo.getSelectedIndex());  
            String detailsToInsert = detailsArea.getText();
            // Get the image file
            File selectedFile = fileChooser.getSelectedFile();
            FileInputStream fis = new FileInputStream(selectedFile);

            // Insert data into the database
            String query = "INSERT INTO product (name, price,quantity ,category,details,image) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, nameToInsert);
            preparedStatement.setString(2, priceToInsert);
            preparedStatement.setString(3, qtyToInsert);
            preparedStatement.setString(4, categoryToInsert);
            preparedStatement.setString(5, detailsToInsert);
            preparedStatement.setBinaryStream(6, fis, (int) selectedFile.length());
            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully!");

       

        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public void PopUp(){
        setVisible(true);
    }

   public void erase() {
        nameField.setText("");
        priceField.setText("");
        qtyField.setText("");
        detailsArea.setText("");
        imageLabel.setIcon(null);
    }


}
