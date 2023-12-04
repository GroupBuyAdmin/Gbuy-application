package gbuysytem.GUI.Body.DashboardPanels.ProductsPanel;


import javax.swing.ImageIcon;

public class Product {
    private int id;
    private ImageIcon imageIcon;
    private String name;
    private String category;
    private String price;
    private int quantity;
    private String description;

    public Product(ImageIcon imageIcon, String name, String price, int quantity, String category, String description) {
        this.id = 0;
        this.imageIcon = imageIcon;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }
    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
