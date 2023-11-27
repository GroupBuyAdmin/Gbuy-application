package gbuysytem.GUI.Body.DashboardPanels.newProductsPanel.TestTable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductTableExample {
    public static void main(String[] args) {
        // Create sample Product objects
        Product product1 = new Product("Product1", "20.0", "5");
        Product product2 = new Product("Product2", "25.0", "8");

        // Create a JFrame and a JTable
        JFrame frame = new JFrame("Product Table Example");
        JTable table = createTable();

        // Populate the table with Product objects
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        addProductToTable(model, product1);
        addProductToTable(model, product2);

        // Add the table to the frame
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        // Set frame properties
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Create a JTable with headers
    private static JTable createTable() {
        String[] columnNames = {"Name", "Price", "Quantity", "Controls"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 3 ? JPanel.class : Object.class;
            }
        };
        JTable table = new JTable(model);

        // Set custom renderer and editor for the "Controls" column
        table.getColumnModel().getColumn(3).setCellRenderer(new ControlsRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new ControlsEditor());
        table.setRowHeight(100);
        return table;
    }

    // Add a Product to the table
    private static void addProductToTable(DefaultTableModel model, Product product) {
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new FlowLayout());
        controlsPanel.add(editButton);
        controlsPanel.add(deleteButton);

        model.addRow(new Object[]{product.getName(), product.getPrice(), product.getQuantity(), controlsPanel});

        // Add action listeners to the buttons (you can customize the functionality)
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add edit functionality here
                System.out.println("Edit button clicked for: " + product.getName());
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add delete functionality here
                System.out.println("Delete button clicked for: " + product.getName());
            }
        });
    }

    // Product class
    static class Product {
        private String name;
        private String price;
        private String quantity;

        public Product(String name, String price, String quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }

        public String getQuantity() {
            return quantity;
        }
    }

    // Custom cell renderer for the "Controls" column
    static class ControlsRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof Component) {
                return (Component) value;
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }

    // Custom cell editor for the "Controls" column
    static class ControlsEditor extends AbstractCellEditor implements TableCellEditor {
        private JPanel controlsPanel;

        public ControlsEditor() {
            controlsPanel = new JPanel();
            controlsPanel.setLayout(new FlowLayout());

            JButton editButton = new JButton("Edit");
            JButton deleteButton = new JButton("Delete");

            controlsPanel.add(editButton);
            controlsPanel.add(deleteButton);

            // Add action listeners to the buttons
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Add edit functionality here
                    System.out.println("Edit button clicked");
                    fireEditingStopped();
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Add delete functionality here
                    System.out.println("Delete button clicked");
                    fireEditingStopped();
                }
            });

            // Add mouse listener to handle button clicks
            controlsPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Object getCellEditorValue() {
            return controlsPanel;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return controlsPanel;
        }
    }
}
