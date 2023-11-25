package gbuysytem.GUI.Body;

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
import javax.swing.border.Border;

public class Dashboard extends JPanel{
       
   
    Dashboard(){
        
      
    
       
        setBackground(Color.decode("#F8FAFC"));
        setBackground(Color.decode("#FDFEFE"));
        setSize(1000,600);


        
    }
}


class CreateProductPopUp extends JFrame{

    private JLabel name, price, qty, details;
    private JTextField nameField, priceField, qtyField;
    private JTextArea detailsArea;
    private JButton select,add;
    private JLabel imageLabel,comboLabel;
    private  JFileChooser fileChooser;
    private JComboBox empCombo;
    
    public CreateProductPopUp(){
        
        
        setLayout(new BorderLayout());  
        setSize(800,450);
        setLocationRelativeTo(null);
        
        
        add( displayImage(),BorderLayout.CENTER);
        add(Inputs(),BorderLayout.EAST);
    }

    public void Pop(){
        Inputs();
    }
   
    
    public  JPanel Inputs(){
      
        JPanel productDetails =  new JPanel();
        Dimension dim = getPreferredSize();
        dim.width = 400;
        productDetails.setPreferredSize(dim);
       
        GridBagConstraints gbc = new GridBagConstraints();
     
        productDetails.setLayout(new GridBagLayout());
        
        gbc.weightx=0;
        name = new JLabel("Name:");
        name.setFont(new Font(name.getName(), Font.PLAIN, 18));
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        productDetails.add(name, gbc);

        nameField = new JTextField(20); // Adjust the size according to your needs
        nameField.setText(nameField.getText());
        nameField.setFont(new Font(nameField.getName(), Font.PLAIN, 18));  
        gbc.gridx = 1;
        gbc.gridy = 0;
        productDetails.add(nameField, gbc);

        price = new JLabel("Price:");
        price.setFont(new Font(price.getName(), Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        productDetails.add(price, gbc);

        priceField = new JTextField(20); // Adjust the size according to your needs
        priceField.setFont(new Font(priceField.getName(), Font.PLAIN, 18));  
        gbc.gridx = 1;
        gbc.gridy = 1;
        productDetails.add(priceField, gbc);
        
        qty = new JLabel("Quantity:");
        qty.setFont(new Font(qty.getName(), Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 2;
        productDetails.add(qty, gbc);

        qtyField = new JTextField(20); // Adjust the size according to your needs
        qtyField.setFont(new Font(qtyField.getName(), Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 2;
        productDetails.add(qtyField, gbc);
        
        comboLabel = new JLabel("Category:");
        qty.setFont(new Font(comboLabel.getName(), Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 3;
        productDetails.add(comboLabel, gbc);

        empCombo = new JComboBox(); 
        DefaultComboBoxModel empModel =  new DefaultComboBoxModel();
        empModel.addElement("Shoes");
        empModel.addElement("Shirts/Pants");
        empModel.addElement("Pants");
        empModel.addElement("Electronics");
        empModel.addElement("Fashion/Pants");
        empModel.addElement("Books");
        empCombo.setModel(empModel);
        
        qtyField.setFont(new Font(nameField.getName(), Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 3;
        productDetails.add(empCombo, gbc);
        
        details = new JLabel("Details:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        productDetails.add(details, gbc);

        detailsArea = new JTextArea(5, 20); // Adjust the size according to your needs
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        productDetails.add(new JScrollPane(detailsArea), gbc);

        productDetails.setBorder(BorderFactory.createEtchedBorder());
        
        
        select = new JButton("Select:");  
        gbc.gridx = 1;
        gbc.gridy = 8;
        select.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        productDetails.add(select, gbc);
        
        fileChooser = new JFileChooser();
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int result = fileChooser.showOpenDialog(CreateProductPopUp.this);
                 if (result == JFileChooser.APPROVE_OPTION) {                   
                    File selectedFile = fileChooser.getSelectedFile();
                     ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedFile.getPath()).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
                     imageLabel.setIcon(imageIcon);    
            }        
       }});

        add = new JButton("Add");  
        gbc.gridx = 1;
        gbc.gridy = 9;
        add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        productDetails.add(add, gbc);


         add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
            
              connection();    
              createProduct();         
       }});
 
        
        productDetails.setBorder(BorderFactory.createEtchedBorder());
        
    
    return productDetails;
    }
    
    public JPanel displayImage(){
        JPanel imagePanel =  new JPanel();
        
        Dimension dim = getPreferredSize();
        dim.width = 300;
        imagePanel.setPreferredSize(dim);      
        imagePanel.setBorder(BorderFactory.createEtchedBorder());
        
        imageLabel = new JLabel();
        
        imageLabel.setPreferredSize(new Dimension(300, 300));
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
            String nameToInsert = nameField.getText()+"name";
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

            // Close the connection when done
            con.close();

        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public void PopUp(){
        setVisible(true);
    }


}