package gbuysytem.GUI.Body.DashboardPanels.ProductsPanel;
import javax.swing.*;

import gbuysytem.GUI.Body.DashboardPanels.ColorPalettes.GBuyPalette;
import gbuysytem.GUI.Body.DashboardPanels.Misc.PanelReturner;
import gbuysytem.GUI.Body.DashboardPanels.Misc.RoundedButton;
import gbuysytem.GUI.Body.DashboardPanels.Misc.RoundedPanel;
import gbuysytem.GUI.Body.fonts.GbuyFont;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsPanel implements PanelReturner{
    private JPanel masterPanel;
    private JPanel scrollablePanel;
    private JScrollPane scrollPane;
    private List<DashboardItemPanel> itemPanels;

    private final Color scrollablePanelColor = Color.decode("#FFFFFF");
    private final Color gridColor = Color.decode("#FFFFFF");
    // private final Color gridColor = Color.decode("#EEF0F3");

    public ProductsPanel(){}

    public ProductsPanel(Dimension masterPanelDimension) {
        masterPanel = new RoundedPanel();
        setToCustomBorder((RoundedPanel) masterPanel);
        masterPanel.setPreferredSize(masterPanelDimension);

        itemPanels = new ArrayList<>();

        scrollablePanel = new JPanel();

        scrollablePanel.setLayout(new BoxLayout(scrollablePanel, BoxLayout.Y_AXIS)); 
        scrollablePanel.setBorder(BorderFactory.createLineBorder(gridColor));
        scrollablePanel.setBackground(scrollablePanelColor);

        scrollPane = new JScrollPane(scrollablePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(scrollablePanelColor);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        setupHeaderPanel();                                                                 
        
        ButtonPanels buttonPanels = getButtonPanels();                                      

        masterPanel.setLayout(new BorderLayout());
        masterPanel.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
        masterPanel.setBackground(scrollablePanelColor);
        masterPanel.add(buttonPanels, BorderLayout.NORTH);
        masterPanel.add(scrollPane, BorderLayout.CENTER);

        addDummyPanels();
    }
 
    private ButtonPanels getButtonPanels() {
        ImageIcon resizedPlusButton = resizeIconForButton("src/gbuysytem/GUI/Body/DashboardPanels/ProductsPanel/img/white plus icon.png");
        RoundedButton addButton = new RoundedButton("add product", resizedPlusButton);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.setButtonColor(GBuyPalette.CUSTOM_BLUE);
        addButton.setForeground(Color.WHITE);
        addButton.setDrawBorder(false);
        addButton.setButtonFont(GbuyFont.MULI_SEMI_BOLD.deriveFont(14f));      

        ImageIcon resizedFilterIcon = resizeIconForButton("src/gbuysytem/GUI/Body/DashboardPanels/ProductsPanel/img/filter.png");
        RoundedButton filterButton = new RoundedButton("Filter", resizedFilterIcon);
        filterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        filterButton.setButtonColor(Color.white);
        filterButton.setForeground(Color.BLACK);
        filterButton.setBorderColor(GBuyPalette.CUSTOM_LIGHT_GRAY);
        filterButton.setButtonFont(GbuyFont.MULI_SEMI_BOLD.deriveFont(14f));

        ButtonPanels buttonPanels = new ButtonPanels(filterButton, addButton);
        setupButtonPanelBehaviour(addButton, filterButton);

       
        return buttonPanels;
    }

    private ImageIcon resizeIconForButton(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image resizedImage = icon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        return resizedIcon;
    }

    private void setupButtonPanelBehaviour(JButton addButton, JButton filterButton) {
         addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateProductPopUp popUp = new CreateProductPopUp(ProductsPanel.this);
                popUp.PopUp();
            }
        });

        String url = "jdbc:mysql://localhost:3306/gbuy";
        String username = "root";
        String password = "";

        String sqlQuery = "SELECT name, price, quantity, category,details, image FROM product";

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
                String cat = resultSet.getString("category");
                String price = resultSet.getString("price");
                String qty = resultSet.getString("quantity");
                String detail = resultSet.getString("details");
                byte[] imageData = resultSet.getBytes("image");
    
                // Convert image data to an Image object
                ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(imageData).getImage());  
     
                Product p = new Product(imageIcon, name,  price, qty,cat, detail);
                addDashboardItem(p);
                updateDashboard();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Filter button clicked!");
                
            }
        });
    }

    private void setupItemPanelButtonListener(RoundedButton deleteButton, RoundedButton editButton, DashboardItemPanel itemPanel) {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //implement delete algo here
                
                deleteDashboardItem(itemPanel);
                updateDashboard();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //implement edit algo here


                editDashboardItem(itemPanel);
                updateDashboard();
            }
        });
    }

    private void setupHeaderPanel() {
        String[] headerNames = {"Image", "Name", "Price", "Quantity", "Category", "Description", "Controls"};
        HeaderPanel headerPanel = new HeaderPanel(headerNames);
        scrollPane.setColumnHeaderView(headerPanel);
    }

    public void addDashboardItem(Product p) {

        Color deleteButtonColor = GBuyPalette.CUSTOM_RED;
        Color editButtonColor = GBuyPalette.CUSTOM_YELLOW;

        RoundedButton deleteButton = new RoundedButton("Delete");
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.setButtonColor(deleteButtonColor);
        deleteButton.setForeground(Color.white);
        deleteButton.setDrawBorder(false);
        deleteButton.setButtonFont(GbuyFont.MULI_SEMI_BOLD.deriveFont(14f));

        RoundedButton editButton = new RoundedButton("Edit");
        editButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        editButton.setButtonColor(editButtonColor);
        editButton.setForeground(Color.white);
        editButton.setDrawBorder(false);
        editButton.setButtonFont(GbuyFont.MULI_SEMI_BOLD.deriveFont(14f));

        DashboardItemPanel itemPanel = new DashboardItemPanel(p, editButton, deleteButton);
        
        setupItemPanelButtonListener(deleteButton, editButton, itemPanel);

        itemPanels.add(itemPanel);

        updateDashboard();
    }


    private void deleteDashboardItem(DashboardItemPanel itemPanel) {
        System.out.println("deleteing itemPanel at row " + itemPanels.indexOf(itemPanel));
        itemPanels.remove(itemPanel);
        scrollablePanel.remove(itemPanel);
    }

    private void editDashboardItem(DashboardItemPanel itemPanel) {
        System.out.println("editing itemPanel at row " + itemPanels.indexOf(itemPanel));
        // Implement edit functionality here
    }

    private void updateDashboard(){
        scrollablePanel.removeAll();

        for(DashboardItemPanel dashboardItem : itemPanels){
            scrollablePanel.add(dashboardItem);
        }

        scrollablePanel.revalidate();
        scrollablePanel.repaint();
    }

    private void setToCustomBorder(RoundedPanel rPanel){
        rPanel.setShady(false);

        int arc = 30;
        rPanel.setArcs(new Dimension(arc, arc));
    }

    @Override
    public JPanel getPanel() {
        return masterPanel;
    }

    private void addDummyPanels(){
        int dummyPanelCount = 10;
        ImageIcon dummyImage = new ImageIcon("src/gbuysytem/GUI/Body/DashboardPanels/ProductsPanel/img/dummyImage.png");
        for(int i = 0; i < dummyPanelCount; i++){
            addDashboardItem(new Product(dummyImage, "product " + String.valueOf(i+1), "null", "null", "null", "null"));
        }
    }

    public void testPanel(){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(masterPanel);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        ProductsPanel p = new ProductsPanel(new Dimension(1000, 600));
        p.testPanel();
    }
}







