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
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import gbuysytem.GUI.Body.DashboardPanels.ProductsPanel.DashboardItemPanel;
import gbuysytem.GUI.Body.DashboardPanels.ProductsPanel.ProductsPanel;

import java.awt.event.MouseEvent;

public class ProductView extends JPanel{


  private ProductData productData;
  private View view;
  private CardLayout crd;  
  private ProductsPanel productsPanel;
   
  
  
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
        this.productView = productView;
        this.productData =productData;
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(displayProducts());      
        add(scrollPane, BorderLayout.CENTER);
        setSize(1500, 800);
    
    }

      public JPanel displayProducts() {
        JPanel panel = new JPanel();
 
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

        }catch (SQLException e) {
            e.printStackTrace();
        }
       
       
       
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      
        panel.setBackground(Color.decode("#FFFFFF"));
        
        return panel;
    }

    JLabel name,price,cart,view;
   
     public JPanel product(String prodName , String prodPrice,ImageIcon image,String qty,String details) {
        JPanel panel = new JPanel();
        Dimension dim = new Dimension(255, 360);
        panel.setPreferredSize(dim);
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.decode("#E3E3E3"));

        
        JLabel picture = new JLabel();

          //set ang image
        Image analyticsScaledImage = image.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon setAnalyticsIcon = new ImageIcon(analyticsScaledImage);  
        picture.setIcon(setAnalyticsIcon);  

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(60, 0, 0, 0);
        gbc.gridx=2;
        gbc.gridy=0;     
        panel.add(picture,gbc);

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx=2;
        gbc.gridy=1;
        gbc.ipadx=215;
        panel.add(productTag(prodName,prodPrice),gbc);
        gbc.gridy++;
        gbc.ipadx=150;
        gbc.ipady=50;
        panel.add(productButtons(),gbc);


        
        
         panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {            
                productView.productClick(); 
             //   productData.setTxt(prodName);
                productData.setImage(image);
                productData.setprodName(prodName);
                productData.price(prodPrice);
                productData.setQty(qty);
                productData.setDetails("Details: "+details);
              
                }
            });
        return panel;
    }


    public JPanel productTag(String prodName,String prodPrice){
      JPanel panel = new JPanel();
   
      
        panel.setBackground(Color.decode("#FFFFFF"));

        name = new JLabel(prodName); 
        name.setForeground(Color.decode("#0A0A0A"));
        name.setFont(new Font(name.getName(),Font.ROMAN_BASELINE,22));
        price = new JLabel("$"+prodPrice);
        price.setFont(new Font(price.getName(),Font.ITALIC,15));
        price.setForeground(Color.decode("#0A0A0A"));

      panel.setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

      gbc.gridx=0;
      gbc.gridy=0;  
      gbc.insets = new Insets(10, 0, 0, 370);
      panel.add(name,gbc);
      gbc.gridy++;

      panel.add(price,gbc);
     

      return panel;
    }

    public JPanel productButtons(){
      JPanel panel = new JPanel();
      panel.setBackground(Color.decode("#FFFFFF"));
      
      cart = new JLabel("Cart");
      JPanel cartBtn = new JPanel();
      Dimension dartDim = getPreferredSize();
      dartDim.width=115;
      dartDim.height=40;
      cartBtn.setPreferredSize(dartDim);
      cart.setFont(new Font(cart.getName(),Font.ROMAN_BASELINE,18));
      cartBtn.setBackground(Color.decode("#404040"));
      cart.setForeground(Color.WHITE);
      cartBtn.add(cart);


      view = new JLabel("View");
      JPanel viewBtn = new JPanel();
      Dimension viewDim = getPreferredSize();
      viewDim.width=115;
      viewDim.height=40;
      viewBtn.setPreferredSize(viewDim);
      viewBtn.setBackground(Color.decode("#E2E2E2"));
      view.setForeground(Color.decode("#444444"));
      view.setFont(new Font(view.getName(),Font.ROMAN_BASELINE,18));
      viewBtn.add(view);
   

      panel.setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

      gbc.gridx=0;
      gbc.gridy=0;
     
      gbc.insets = new Insets(0, 0, 0, 10);
      panel.add(cartBtn,gbc);
      gbc.gridx++;
      panel.add(viewBtn,gbc);


      return panel;
    }

    
   
}





class ProductData extends JPanel{

   JLabel text;
   String txt;
   JLabel image;
   JLabel prodName,price,qty,details;
   JSpinner  numOfOrder;
   JButton Cart,Buy;
  
    ProductView prodView;

   public ProductData(ProductView prodView){
  
    setBorder(BorderFactory.createEtchedBorder());

    this.prodView= prodView;

    text = new JLabel();
  
   
    setLayout(new BorderLayout());
    add(header(),BorderLayout.NORTH);
    add(productDetails(),BorderLayout.CENTER);
    add(Groupbuys(),BorderLayout.EAST);
   
   }

   public JPanel header(){
    
    JButton back = new JButton("Back" );
    JPanel panel = new JPanel();

    back.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
           prodView.backBtn();
        }

    });

    panel.setLayout(new BorderLayout());
    panel.add(back,BorderLayout.WEST);

    return panel;
   }

   public JPanel productDetails(){
    JPanel panel = new JPanel();
    Dimension dim = getPreferredSize();
    dim.width=300;
    panel.setPreferredSize(dim);
    panel.setBorder(BorderFactory.createEtchedBorder());
    panel.setLayout(new GridBagLayout());

   

    GridBagConstraints gbc = new GridBagConstraints();
     gbc.gridx= 0;
     gbc.gridy=0;
     gbc.ipadx=100;
     panel.add(img(),gbc);
     gbc.ipadx=0;
     gbc.gridx= 3;
     gbc.gridy=0;
     panel.add(details(),gbc);
     gbc.gridx=3;
     gbc.gridy=1;
     gbc.anchor = GridBagConstraints.FIRST_LINE_START; 
     panel.add(CartAndOrder(),gbc);
    

     
    

     return panel;
   }

    public JPanel CartAndOrder(){
     
      JPanel panel = new JPanel();
        Cart = new JButton("Cart");
         Buy = new JButton("Buy");
        
      panel.setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx=0;
      gbc.gridy=0;
      gbc.insets= new Insets(0,30 ,0 , 0);
      panel.add(Cart,gbc);
      gbc.gridx=1;
      gbc.gridy=0;
      panel.add(Buy,gbc);

      return panel;
    }

   public JPanel img(){   
    JPanel panel = new JPanel(); 
    setBorder(BorderFactory.createEtchedBorder());
    image = new JLabel();
    panel.add(image);  
    return panel;
   }

   public void setImage(ImageIcon img){
     Image analyticsScaledImage = img.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
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
    gbc.weightx = 1; 
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
    panel.add(setNumOfOrder(), gbc);

    gbc.gridy++;
    panel.add(details, gbc);

    return panel;
   } 

   public void setprodName(String txt){
    prodName.setText(txt);
   }

    public void price(String txt){
    price.setText("Price: $"+txt);
   }
    public void setQty(String txt){
    qty.setText("Stock: "+txt);
   }
  
   public void setDetails(String txt){      
    details.setText(trimDetails(txt,60));
   }

  static String trimDetails(String txt,int length){
        StringBuilder details = new StringBuilder();
        details.append("<html>"); 
        int x =0;
        for(int i=0; i<txt.length();i+=length){   
            if(x >length)
                x = txt.length();
            else
                x = Math.min(i + length, txt.length());     

            details.append(txt.substring(i, x));
            details.append("<br>");       
        }
        details.append("</html>");
        return details.toString();
   }

   public JPanel setNumOfOrder(){
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Quantity: ");
     SpinnerModel value = new SpinnerNumberModel(1,1,100,1);
     numOfOrder = new JSpinner(value);

     panel.setLayout(new GridBagLayout());
     GridBagConstraints gbc = new GridBagConstraints();
     gbc.gridx=0;
     gbc.gridy=0;
     gbc.insets = new Insets(0,0 ,0 ,5 );
     panel.add(label,gbc);
     gbc.gridx=1;
     panel.add(numOfOrder,gbc);
     return panel;
   }


   public JPanel Groupbuys(){
      JPanel panel = new JPanel();
      JLabel l= new JLabel();

      Dimension dim = getPreferredSize();
      dim.width=300;
      panel.setPreferredSize(dim);
      panel.setBorder(BorderFactory.createEtchedBorder());



    return panel;
   }
  

   
    
}