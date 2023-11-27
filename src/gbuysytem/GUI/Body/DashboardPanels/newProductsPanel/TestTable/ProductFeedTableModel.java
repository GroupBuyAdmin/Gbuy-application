package gbuysytem.GUI.Body.DashboardPanels.newProductsPanel.TestTable;

import java.util.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import gbuysytem.GUI.Body.DashboardPanels.newProductsPanel.SingleProduct;


public class ProductFeedTableModel extends AbstractTableModel{

    private List<SingleProduct> products;

    public ProductFeedTableModel(ArrayList<SingleProduct> products){
        this.products = products;
    }

    public Class getColumnClass(int columnIndex) { 
        return SingleProduct.class;
    }

    public boolean isCellEditable(int columnIndex, int rowIndex) { 
        return true; 
    }

    public String getColumnName(int columnIndex){
        return "Products";
    }

    @Override
    public int getRowCount() {
        return (products == null) ? 0 : products.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return (products == null) ? null : products.get(rowIndex);
    }
    
    
}
