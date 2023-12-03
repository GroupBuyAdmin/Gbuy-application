package gbuysytem.GUI.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.MouseEvent;

public class ProductView extends JPanel{


  ProductData productData;
  View view;
  CardLayout crd;  
  
  
    public ProductView(){
       
        productData = new ProductData(this);
        view = new View(this,productData); 

        crd = new CardLayout();
        setLayout(crd);

        add(productData, "productData");
        add(view, "view");

      //  view.displayProducts();
        crd.show(this, "view");

    }

    public void productClick(){
        crd.show(this, "productData");
    }

    public void backBtn(){
        crd.show(this, "view");
    }

}

 class View extends JPanel{

    private ProductView productView;
    private ProductData productData;
    
    public View(ProductView productView,ProductData productData){
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(displayProducts());      
        add(scrollPane, BorderLayout.CENTER);
        setSize(1500, 800);
         
        this.productView = productView;
       this.productData =productData;
     
    }

      public JPanel displayProducts() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#F3EBEB"));

        // Use a GridBagLayout to arrange products in multiple rows
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20); // Adjust spacing as needed


        String url = "jdbc:mysql://localhost:3306/gbuy";
        String username = "root";
        String password = "";

        String sqlQuery = "SELECT name, price, quantity, details, image FROM product";

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
               
                float price = resultSet.getFloat("price");
                String qty = resultSet.getString("quantity");
                String detail = resultSet.getString("details");
                byte[] imageData = resultSet.getBytes("image");
    
                // Convert image data to an Image object
                ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(imageData).getImage());  

                
                // creates products 
                panel.add(product(name, String.valueOf(price),  imageIcon,qty,detail), gbc);
                gbc.gridx++;
                if (gbc.gridx > 2) {
                    gbc.gridx = 0;
                    gbc.gridy++;
                }     
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      
        panel.setBackground(Color.decode("#F5F6F7"));
        
        return panel;
    }

     public JPanel product(String prodName , String prodPrice,ImageIcon image,String qty,String details) {
        JPanel panel = new JPanel();
        Dimension dim = new Dimension(250, 300);
        panel.setPreferredSize(dim);
        panel.setLayout(new GridBagLayout());
         panel.setBackground(Color.decode("#FFFFFF"));

        JLabel picture = new JLabel();
 
        JLabel name = new JLabel(prodName);
        name.setFont(new Font(name.getName(),Font.BOLD,20));
        JLabel price = new JLabel("$"+prodPrice);
        price.setFont(new Font(price.getName(),Font.ITALIC,15));
        price.setForeground(Color.decode("#4531EF"));

          //set ang image
        Image analyticsScaledImage = image.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon setAnalyticsIcon = new ImageIcon(analyticsScaledImage);  
        picture.setIcon(setAnalyticsIcon);  

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx=2;
        gbc.gridy=0;     
        panel.add(picture,gbc);

        gbc.gridx=2;
        gbc.gridy=1;
        panel.add(name,gbc);

        gbc.gridx=2;
        gbc.gridy=2;
        panel.add(price,gbc);

        panel.add(price,gbc);
        
        
         panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
               
                productView.productClick(); 
                productData.setTxt(prodName);
                productData.setImage(image);
                productData.setprodName(prodName);
                productData.price(prodPrice);
                productData.setQty(qty);
                productData.setDetails(details);
                }
            });
     

        return panel;
    }
 
   
}


class ProductData extends JPanel{

   JLabel  text;
   JButton back;
   String txt;
    
   JLabel image;
   JLabel prodName,price,qty,details;

    ProductView prodView;
   public ProductData(ProductView prodView){
  
    setBorder(BorderFactory.createEtchedBorder());

    this.prodView= prodView;

    text = new JLabel();
    back = new JButton("Back");


    back.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
           prodView.backBtn();
        }

    });
   
    // setLayout(new BorderLayout());
    // add(text,BorderLayout.CENTER);
    // add(img(),BorderLayout.WEST);

    ProductDataLayout();
   }

   public void ProductDataLayout(){
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

     gbc.gridx= 0;
     gbc.gridy=0;
     gbc.ipadx=100;
     add(img(),gbc);
     gbc.ipadx=0;
     gbc.gridx= 4;
     gbc.gridy=0;
     add(details(),gbc);
     
   }

   public void setTxt(String txt){
     text.setText(txt);    
   }


   public JPanel img(){   
    JPanel panel = new JPanel(); 
    setBorder(BorderFactory.createEtchedBorder());
    image = new JLabel();
    panel.add(image);  
    return panel;
   }

   public void setImage(ImageIcon img){
     Image analyticsScaledImage = img.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
     ImageIcon setAnalyticsIcon = new ImageIcon(analyticsScaledImage);   
     image.setIcon(setAnalyticsIcon);
}



   public JPanel details(){
     
    JPanel panel = new JPanel();
    prodName = new JLabel();
    price = new JLabel();
    qty = new JLabel();
    details = new JLabel();
    


    panel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

   
    gbc.gridwidth = 1;
    gbc.weightx = 1; // Allow components to expand horizontally
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.LINE_START; 

    gbc.insets = new Insets(0, 0, 30, 0);
    panel.add(prodName, gbc);

    gbc.gridy++;
    panel.add(price, gbc);

    gbc.gridy++;
    panel.add(qty, gbc);

    gbc.gridy++;
    panel.add(details, gbc);

    return panel;
   } 

   public void setprodName(String txt){
    prodName.setText(txt);
   }

    public void price(String txt){
    price.setText("$"+txt);
   }
    public void setQty(String txt){
    qty.setText("Quantity: "+txt);
   }
     public void setDetails(String txt){
      details.setText("Details: \n\n"+txt);
   }

  

   
    
}