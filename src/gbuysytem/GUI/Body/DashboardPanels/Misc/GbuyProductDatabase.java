package gbuysytem.GUI.Body.DashboardPanels.Misc;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import gbuysytem.GUI.Body.DashboardPanels.ProductsPanel.Product;

public class GbuyProductDatabase {

    private static volatile GbuyProductDatabase instance;

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String databaseName = "leandergbuydb";                                      //modify this
    private String url = "jdbc:mysql://localhost:3306/" + databaseName;
    private String username = "root";
    private String password = "";

    private String query = null;
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    private GbuyProductDatabase(){} //for singleton pattern

    public static GbuyProductDatabase getInstance(){
        GbuyProductDatabase thisInstance = instance;
        if(thisInstance == null){
            synchronized (GbuyProductDatabase.class){
                thisInstance = instance;
                if(instance == null){
                    instance = thisInstance = new GbuyProductDatabase();
                }
            }
        }
        return thisInstance;
    }

    //insert to database the product
    public void insertProduct(SingleProductContainer spc){
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            query = "INSERT INTO products (productName, productCategory, productPrice, productDescription, productQuantity, productImage) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, spc.productName);
            preparedStatement.setString(2, spc.productCategory);
            preparedStatement.setDouble(3, spc.productPrice);
            preparedStatement.setString(4, spc.productDescription);
            preparedStatement.setInt(5, spc.productQuantity);
            try (FileInputStream inputStream = new FileInputStream(spc.selectedFile)) {
                byte[] imageData = new byte[(int) spc.selectedFile.length()];
                inputStream.read(imageData);
                preparedStatement.setBytes(6, imageData);
            }

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected");
            System.out.println(spc.productName + " was product added");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //get all products from database
    public List<Product> getProducts(){
        List<Product> allproducts = new ArrayList<>();
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            query = "SELECT `productID`,`productName`, `productCategory`, `productPrice`, `productDescription`, `productQuantity`, `productImage` FROM `products`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int productID = resultSet.getInt("productID");
                String productName = resultSet.getString("productName");
                String productCategory = resultSet.getString("productCategory");
                double productPrice = resultSet.getDouble("productPrice");
                String productDescription = resultSet.getString("productDescription");
                int productQuantity = resultSet.getInt("productQuantity");
                byte[] byteImage = resultSet.getBytes("productImage");
                
                //convert image data to image object
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(byteImage).getImage());

                Product p = new Product(imageIcon, productName, "$" + String.valueOf(productPrice), productQuantity, productCategory, productDescription);
                p.setId(productID);
                allproducts.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();   
            }
        }

        return allproducts;
    }

    public void deleteProduct(int productIdToDelete){
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            query = "DELETE FROM `products` WHERE `productId` = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productIdToDelete);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Row with ID " + productIdToDelete + " deleted successfully.");
            } else {
                System.out.println("No rows deleted. ID " + productIdToDelete + " not found.");
            }

            preparedStatement.close();
            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editProduct(SingleProductContainer spc, int productIdtoEdit){
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            query = "UPDATE products SET productName = ?, productCategory = ?, productPrice = ?, productDescription = ?, productQuantity = ?, productImage = ? WHERE productId = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, spc.productName);
            preparedStatement.setString(2, spc.productCategory);
            preparedStatement.setDouble(3, spc.productPrice);  
            preparedStatement.setString(4, spc.productDescription);
            preparedStatement.setInt(5, spc.productQuantity);  
            try (FileInputStream inputStream = new FileInputStream(spc.selectedFile)) {
                byte[] imageData = new byte[(int) spc.selectedFile.length()];
                inputStream.read(imageData);
                preparedStatement.setBytes(6, imageData);
            }

            preparedStatement.setInt(7, productIdtoEdit);  
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Update successful");
            } else {
                System.out.println("No rows updated. Check if the productId exists.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getSingleProduct(int productID, SingleProductContainer spc){
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            query = "SELECT * FROM products WHERE productId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productID);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                spc.productName = resultSet.getString("productName");
                spc.productCategory = resultSet.getString("productCategory");
                spc.productPrice = resultSet.getDouble("productPrice");
                spc.productDescription = resultSet.getString("productDescription");
                spc.productQuantity = resultSet.getInt("productQuantity");
                spc.byteImage = resultSet.getBytes("productImage");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}