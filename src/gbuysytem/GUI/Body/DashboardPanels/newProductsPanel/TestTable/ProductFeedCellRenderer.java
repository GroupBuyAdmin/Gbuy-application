package gbuysytem.GUI.Body.DashboardPanels.newProductsPanel.TestTable;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import gbuysytem.GUI.Body.DashboardPanels.newProductsPanel.SingleProduct;

public class ProductFeedCellRenderer implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        SingleProduct product = (SingleProduct)value;

        JPanel createdProduct;
        JLabel listImage, listCategory, listPrice, listQuantity, listName, listDescription, listEdit, listDelete;
        
        listImage = new JLabel(product.getImage(), SwingConstants.CENTER);
        listCategory = new JLabel(product.getCategory(), SwingConstants.CENTER);
        listPrice = new JLabel(product.getPrice(), SwingConstants.CENTER);
        listQuantity = new JLabel(product.getQuantity(), SwingConstants.CENTER);
        listName = new JLabel(product.getName(), SwingConstants.CENTER);
        listDescription = new JLabel(product.getDescription(), SwingConstants.CENTER);
        listEdit = new JLabel("Edit", SwingConstants.CENTER);
        listDelete = new JLabel("Delete", SwingConstants.CENTER);
        
        listImage.setOpaque(true);
        listCategory.setOpaque(true);
        listPrice.setOpaque(true);
        listQuantity.setOpaque(true);
        listName.setOpaque(true);
        listDescription.setOpaque(true);
        listEdit.setOpaque(true);
        listDelete.setOpaque(true);

        createdProduct = new JPanel();
        createdProduct.setBackground(Color.lightGray);

        createdProduct.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();        
        int x = 0;
        int y = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addToCreatedProduct(createdProduct, listImage, x, y, gbc);
        addToCreatedProduct(createdProduct, listCategory, ++x, y, gbc);
        addToCreatedProduct(createdProduct, listPrice, ++x, y, gbc);
        addToCreatedProduct(createdProduct, listQuantity, ++x, y, gbc);
        addToCreatedProduct(createdProduct, listName, ++x, y, gbc);
        addToCreatedProduct(createdProduct, listDescription, ++x, y, gbc);
        addToCreatedProduct(createdProduct, listEdit, ++x, y, gbc);
        addToCreatedProduct(createdProduct, listDelete, ++x, y, gbc);

        if(isSelected){
            createdProduct.setBackground(table.getSelectionBackground());
        }
        else{
            createdProduct.setBackground(table.getBackground());
        }


        return createdProduct;
    }
    private void addToCreatedProduct(JPanel jPanel, JLabel jLabel, int x, int y, GridBagConstraints gbc){
        gbc.gridx = x;
        gbc.gridy = y;
        jPanel.add(jLabel, gbc);
    }   
}
