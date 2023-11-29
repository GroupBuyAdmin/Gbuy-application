package gbuysytem.GUI.Body.DashboardPanels.ProductsPanel;

import javax.swing.*;

import gbuysytem.GUI.Body.DashboardPanels.CustomButton;
import gbuysytem.GUI.Body.DashboardPanels.RoundedButton;
import gbuysytem.GUI.Body.fonts.CustomFont;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProductsPanelTest {
    private JPanel masterPanel;
    private JPanel scrollablePanel;
    private JScrollPane scrollPane;
    private List<DashboardItemPanel> itemPanels;

    private final Color scrollablePanelColor = Color.decode("#FFFFFF");
    private final Color gridColor = Color.decode("#EEF0F3");

    public ProductsPanelTest(Dimension masterPanelDimension) {
        masterPanel = new JPanel();
        masterPanel.setPreferredSize(masterPanelDimension);

        itemPanels = new ArrayList<>();

        scrollablePanel = new JPanel();
        scrollablePanel.setLayout(new BoxLayout(scrollablePanel, BoxLayout.Y_AXIS)); 
        scrollablePanel.setBorder(BorderFactory.createLineBorder(gridColor));
        scrollablePanel.setBackground(scrollablePanelColor);

        scrollPane = new JScrollPane(scrollablePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createLineBorder(gridColor));
        scrollPane.setBackground(scrollablePanelColor);
  


        setupHeaderPanel();                                                                 //setup HeaderPanel for scrollpane
        
        RoundedButton addButton = new RoundedButton("+  add product");
        addButton.setButtonColor(Color.blue);
        addButton.setForeground(Color.WHITE);
        addButton.setDrawBorder(false);
        addButton.setButtonFont(CustomFont.Franca_Medium.getFont().deriveFont(14f));      

        RoundedButton filterButton = new RoundedButton("Filter");
        filterButton.setButtonColor(Color.white);
        filterButton.setForeground(Color.BLACK);
        filterButton.setBorderColor(Color.gray);
        filterButton.setButtonFont(CustomFont.Franca_Medium.getFont().deriveFont(14f));

        ButtonPanels buttonPanels = new ButtonPanels(filterButton, addButton);
        setupButtonPanelBehaviour(addButton, filterButton);                                 //MODIFY BUTTON BEHAVIORS HERE

        masterPanel.setLayout(new BorderLayout());
        masterPanel.add(buttonPanels, BorderLayout.NORTH);
        masterPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void setupButtonPanelBehaviour(JButton addButton, JButton filterButton) {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //dummy product added, remove this part 
                String dummyImage = "src/gbuysytem/GUI/Body/DashboardPanels/ProductsPanel/dummyImage.png";
                System.out.println("Add product button clicked!");
                Product p = new Product(new ImageIcon(dummyImage), "null", "null", "null", "null", "null");
                //-----------------------------------------------------------------------------------------

                addDashboardItem(p);
            }
        });

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Filter button clicked!");
            }
        });
    }


    private void setupHeaderPanel() {
        String[] headerNames = {"Image", "Name", "Price", "Quantity", "Category", "Description", "Controls"};
        HeaderPanel headerPanel = new HeaderPanel(headerNames);
        scrollPane.setColumnHeaderView(headerPanel);
    }


    private void addDashboardItem(Product p) {
        RoundedButton deleteButton = new RoundedButton("Delete");
        deleteButton.setButtonColor(Color.red);
        deleteButton.setForeground(Color.white);
        deleteButton.setDrawBorder(false);
        deleteButton.setButtonFont(CustomFont.Franca_Medium.getFont().deriveFont(14f));

        RoundedButton editButton = new RoundedButton("Edit");
        editButton.setButtonColor(Color.BLUE);
        editButton.setForeground(Color.white);
        editButton.setDrawBorder(false);
        editButton.setButtonFont(CustomFont.Franca_Medium.getFont().deriveFont(14f));

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
        scrollablePanel.remove(itemPanel);
    }

    private void editDashboardItem(DashboardItemPanel itemPanel) {
        System.out.println("editing itemPanel at row " + itemPanels.indexOf(itemPanel));
        // Implement edit functionality here
    }

    private void updateDashboard(){
        scrollablePanel.removeAll();

        for(DashboardItemPanel dashboardItem : itemPanels){
            // scrollablePanel.add(Box.createVerticalStrut(5));
            scrollablePanel.add(dashboardItem);
        }

        scrollablePanel.revalidate();
        scrollablePanel.repaint();
    }


    public void testPanel(){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(masterPanel);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        ProductsPanelTest p = new ProductsPanelTest(new Dimension(1000, 600));
        p.testPanel();
    }
}

//for customization
class DashboardItemPanel extends JPanel {
    private final Color dashboardItemPanelColor = Color.decode("#FFFFFF");
    private final Color gridColor = Color.decode("#EEF0F3");

    public DashboardItemPanel(Product product, JButton editButton, JButton deleteButton) {
        setPreferredSize(new Dimension(50, 50)); // Set a fixed size for each item
        setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // Ensure a fixed height
        setBorder(BorderFactory.createLineBorder(gridColor));
        setBackground(dashboardItemPanelColor);

        setLayout(new GridLayout(1,0));

        add(createIcon(product.getImageIcon()));
        add(createCenteredLabel(product.getName()));
        add(createCenteredLabel(product.getCategory()));
        add(createCenteredLabel(product.getPrice()));
        add(createCenteredLabel(product.getQuantity()));
        add(createCenteredLabel(product.getDescription()));
        add(deleteButton);
        add(editButton);
    }
    
    private JPanel createCenteredLabel(String text) {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15,15));
        JLabel label = new JLabel(text);
        labelPanel.setBackground(dashboardItemPanelColor);
        label.setFont(CustomFont.Franca_Medium.getFont().deriveFont(14f));
        labelPanel.add(label);
        return labelPanel;
    }

    private JPanel createIcon(ImageIcon imageIcon){
        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 9));
        iconPanel.setBackground(dashboardItemPanelColor);
        //change if it is image or image icon...for now from image to image icon
        Image i = imageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(i);

        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(scaledImageIcon);

        iconPanel.add(iconLabel);
        return iconPanel;
    }
}




