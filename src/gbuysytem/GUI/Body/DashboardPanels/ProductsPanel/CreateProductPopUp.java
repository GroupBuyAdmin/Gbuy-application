package gbuysytem.GUI.Body.DashboardPanels.ProductsPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateProductPopUp  extends JFrame{
     private JLabel name, price, qty, details;
    private JTextField nameField,priceField,qtyField;
    private JTextArea detailsArea;
    private JButton select,add,test;
    private JLabel imageLabel,comboLabel;
    private  JFileChooser fileChooser;
    private JComboBox empCombo;

    private ProductsPanel productsPanel;
    
    public CreateProductPopUp(ProductsPanel productsPanel){
        setLayout(new BorderLayout());  
        setSize(800,500);
       
        setLocationRelativeTo(null);
     
        add(titlePanel(),BorderLayout.NORTH);
        add(displayImage(),BorderLayout.CENTER);
        add(Inputs(),BorderLayout.EAST);

        this.productsPanel = productsPanel;
        
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

        ProductsPanel pp = new ProductsPanel();

        
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
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedFile.getPath()).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

            Product createdProduct = new Product(imageIcon, nameToInsert, priceToInsert, qtyToInsert, categoryToInsert, detailsToInsert);
            productsPanel.addDashboardItem(createdProduct);
       

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

    // public static void main(String[] args) {
    //     ProductsPanel p = new ProductsPanel();
    //     CreateProductPopUp cpp = new CreateProductPopUp(p);
    //     cpp.PopUp();
    // }

}
