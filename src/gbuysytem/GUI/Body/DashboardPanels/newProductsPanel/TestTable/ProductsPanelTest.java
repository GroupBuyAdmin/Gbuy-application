package gbuysytem.GUI.Body.DashboardPanels.newProductsPanel.TestTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProductsPanelTest extends JFrame {

    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private List<DashboardItemPanel> itemPanels;

    public ProductsPanelTest() {
        itemPanels = new ArrayList<>();

        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); 
        
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setColumnHeaderView(new HeaderPanel());
        
        
        JButton addButton = new JButton("Add Product");
        JButton filterButton = new JButton("Filter");

        ButtonPanels buttonPanels = new ButtonPanels(filterButton, addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add product button clicked!");
                TestProduct p = new TestProduct("dummy", "dfd", "$343242", "gdagdasg", "agadgfdg");
                addDashboardItem(p);
            }
        });


        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Filter button clicked!");
            }
        });



        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanels, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        tester();
    }

    private void tester() {
        itemPanels = new ArrayList<>();
        TestProduct p1 = new TestProduct("hello", "shoes", "10$", "10", "dum");
        TestProduct p2 = new TestProduct("th", "fdaf", "asf$", "afs", "afdas");
        TestProduct p3 = new TestProduct("hello", "shoes", "10$", "10", "asdf");
        TestProduct p4 = new TestProduct("very large asfdsafdasfdasfsdafdsafdasfdsafdasfdasfdsafsdafasdfdasfsdafsad", "afasd", "10$", "fdasf", "gfadg");

        addDashboardItem(p1);
        addDashboardItem(p2);
        addDashboardItem(p3);
        addDashboardItem(p4);

        updateDashboard();
    }

    private void addDashboardItem(TestProduct p) {
        JButton deleteButton = new JButton("Delete");
        JButton editButton = new JButton("Edit");

        DashboardItemPanel itemPanel = new DashboardItemPanel(p, editButton, deleteButton);
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDashboardItem(itemPanel);
                updateDashboard();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editDashboardItem(itemPanel);
                updateDashboard();
            }
        });

        itemPanel.add(deleteButton);
        itemPanel.add(editButton);
        itemPanels.add(itemPanel);

        updateDashboard();
    }

    private void deleteDashboardItem(DashboardItemPanel itemPanel) {
        System.out.println("deleteing itemPanel at row " + itemPanels.indexOf(itemPanel));
        itemPanels.remove(itemPanel);
        mainPanel.remove(itemPanel);
    }

    private void editDashboardItem(DashboardItemPanel itemPanel) {
        System.out.println("editing itemPanel at row " + itemPanels.indexOf(itemPanel));
        // Implement edit functionality here
    }

    private void updateDashboard(){
        mainPanel.removeAll();

        for(DashboardItemPanel dashboardItem : itemPanels){
            mainPanel.add(Box.createVerticalStrut(5));
            mainPanel.add(dashboardItem);
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProductsPanelTest().setVisible(true);
            }
        });
    }
}

//for customization
class DashboardItemPanel extends JPanel {
    public DashboardItemPanel(TestProduct testProduct, JButton editButton, JButton deleteButton) {
        setPreferredSize(new Dimension(150, 50)); // Set a fixed size for each item
        setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // Ensure a fixed height
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setLayout(new GridLayout(1,0));

        add(createCenteredLabel(testProduct.getImage()));

        add(createCenteredLabel(testProduct.getName()));
        
        add(createCenteredLabel(testProduct.getPrice()));
        
        add(createCenteredLabel(testProduct.getQuantity()));
        
        add(createCenteredLabel(testProduct.getDescription()));

        add(deleteButton);

        add(editButton);
    }
    
    private JPanel createCenteredLabel(String text) {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15,10));
        JLabel label = new JLabel(text);
        labelPanel.add(label);
        return labelPanel;
    }
}

class HeaderPanel extends JPanel{

    public HeaderPanel(){
        setPreferredSize(new Dimension(20, 50)); // Set a fixed size for each item
        setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // Ensure a fixed height
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setLayout(new GridLayout(1,0));

        add(createCenteredLabel("Image"));
        
        add(createCenteredLabel("Name"));
        
        add(createCenteredLabel("Price"));
        
        add(createCenteredLabel("Quantity"));
        
        add(createCenteredLabel("Description"));
                
        add(createCenteredLabel("Controls"));

        add(new JPanel());
    }
    
    private JPanel createCenteredLabel(String text) {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15,5));
        labelPanel.setPreferredSize(new Dimension(20,50));
        JLabel label = new JLabel(text);
        labelPanel.add(label);
        return labelPanel;
    }

}

class ButtonPanels extends JPanel{
    public ButtonPanels(JButton filter, JButton createProduct){
        setPreferredSize(new Dimension(20,50));
        // setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setLayout(new FlowLayout(FlowLayout.TRAILING, 15, 5));

        add(filter);
        add(createProduct);
    }
}

//test class
class TestProduct{
    String image;
    String name;
    String price;
    String quantity;
    String description;

    
    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
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


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public TestProduct(String image, String name, String price, String quantity, String description) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }


}
