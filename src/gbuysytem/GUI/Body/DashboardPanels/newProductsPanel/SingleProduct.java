package gbuysytem.GUI.Body.DashboardPanels.newProductsPanel;

public class SingleProduct {
    private String image;
    private String category;
    private String price;
    private String quantity;
    private String name;
    private String description;

    public SingleProduct(String name, String price){
        this.image = "null";
        this.category = "null";
        this.price = price;
        this.quantity = "null";
        this.name = name;
        this.description = "null";
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
