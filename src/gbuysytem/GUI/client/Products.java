package gbuysytem.GUI.client;

import javax.swing.ImageIcon;

public class Products {

    // int prodId
   public ImageIcon imageIcon;
   public String name;
   public String price;
   public String qty;
   public String cat;
   public String detail;

    public  Products(ImageIcon imageIcon,String name, String price,String qty,String cat,String detail){
        this.imageIcon=imageIcon;
        this.name =name;
        this.price= price;
        this.qty=qty;
        this.cat =cat;
        this.detail=detail;
    }
    

    public ImageIcon getImageIcon() {
        return imageIcon;
    }


    public String getName() {
        return name;
    }


    public String getPrice() {
        return price;
    }


    public String getQty() {
        return qty;
    }


    public String getCat() {
        return cat;
    }


    public String getDetail() {
        return detail;
    }


    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setPrice(String price) {
        this.price = price;
    }


    public void setQty(String qty) {
        this.qty = qty;
    }


    public void setCat(String cat) {
        this.cat = cat;
    }


    public void setDetail(String detail) {
        this.detail = detail;
    }


  
}





