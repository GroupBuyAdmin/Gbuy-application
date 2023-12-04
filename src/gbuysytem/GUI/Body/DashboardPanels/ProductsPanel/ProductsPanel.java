package gbuysytem.GUI.Body.DashboardPanels.ProductsPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import gbuysytem.GUI.Body.DashboardPanels.ColorPalettes.GBuyPalette;
import gbuysytem.GUI.Body.DashboardPanels.Misc.GbuyProductDatabase;
import gbuysytem.GUI.Body.DashboardPanels.Misc.PanelReturner;
import gbuysytem.GUI.Body.DashboardPanels.Misc.RoundedButton;
import gbuysytem.GUI.Body.DashboardPanels.Misc.RoundedPanel;
import gbuysytem.GUI.Body.fonts.GbuyFont;

public class ProductsPanel implements PanelReturner{
    private JPanel masterPanel;
    private JPanel scrollablePanel;
    private JScrollPane scrollPane;
    private List<DashboardItemPanel> itemPanels;

    private final Color scrollablePanelColor = Color.decode("#FFFFFF");
    private final Color gridColor = Color.decode("#FFFFFF");

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

        updateDashboard();
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
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new ProductCreator(ProductsPanel.this);
                    }
                    
                });
            }

        });
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //might be combo box
                System.out.println("Filter button clicked!");
            }
        });
    }

    private void setupItemPanelButtonListener(RoundedButton deleteButton, RoundedButton editButton, DashboardItemPanel itemPanel) {
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

        //add to local list of panels
        itemPanels.add(itemPanel);
    }

    private void deleteDashboardItem(DashboardItemPanel itemPanel) {
        System.out.println("deleteing itemPanel at row " + itemPanels.indexOf(itemPanel));
        //delete from database using id
        GbuyProductDatabase db = GbuyProductDatabase.getInstance();
        db.deleteProduct(itemPanel.getProduct().getId());
    }

    private void editDashboardItem(DashboardItemPanel itemPanel) {
        System.out.println("editing itemPanel at row " + itemPanels.indexOf(itemPanel));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProductCreator(ProductsPanel.this, itemPanel.getProduct());
            }
            
        });
    }

    public void updateDashboard(){
        GbuyProductDatabase db = GbuyProductDatabase.getInstance();
        List<Product> allProducts = db.getProducts();
        
        scrollablePanel.removeAll();
        itemPanels.clear();

        for(Product p : allProducts){
            addDashboardItem(p);
        }

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







