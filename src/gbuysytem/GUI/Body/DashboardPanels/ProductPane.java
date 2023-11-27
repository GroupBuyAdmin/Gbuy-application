package gbuysytem.GUI.Body.DashboardPanels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ProductPane implements PanelReturner{

    //main ui
    private JPanel masterPanel;
    private JPanel scrollPaneContainer;
    private JScrollPane scrollablePane;

    private LinkedList<Product> listOfProducts;


    public void setListOfProducts(LinkedList<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;

        for(Product p : listOfProducts){
            scrollPaneContainer.add(p.getPanel());
            scrollPaneContainer.add(Box.createRigidArea(new Dimension(2, 2)));
        }

    }

    public ProductPane(Dimension masterPanelDimension){
        listOfProducts = new LinkedList<>();

        generateMainComponents();
        
        masterPanel.setPreferredSize(masterPanelDimension);      
        scrollablePane = new JScrollPane(scrollPaneContainer, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollablePane.setPreferredSize(masterPanel.getSize());
        masterPanel.add(scrollablePane, BorderLayout.CENTER);

    }

    private void generateMainComponents() {
        masterPanel = new JPanel();
        masterPanel.setLayout(new BorderLayout());

        masterPanel.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                autoResizeAll();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
            
        });

        scrollPaneContainer = new JPanel();
        scrollPaneContainer.setLayout(new BoxLayout(scrollPaneContainer, BoxLayout.Y_AXIS));
    }

    //for testing
    private void autoResizeAll() {
        Dimension newSize = masterPanel.getSize();
        scrollablePane.setPreferredSize(newSize);
        scrollablePane.revalidate();
    }

    //called from client
    public void autoResizeAll(Dimension newSize, Dimension headerDimension) {
        masterPanel.setPreferredSize(newSize);
        scrollablePane.setPreferredSize(newSize);

        for(Product p: listOfProducts){
            p.resizer(headerDimension);
        }

        scrollablePane.revalidate();
        scrollablePane.repaint();
    }

    public void testPanel(Dimension d){
        JFrame f = new JFrame();
        f.setSize(d);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(masterPanel);
        f.setVisible(true);
    }

    @Override
    public JPanel getPanel() {
        return masterPanel;
    }

    public static void main(String[] args) {
        Dimension d = new Dimension(1000, 600);
        ProductPane p = new ProductPane(d);
        
        
        Dimension prodD = new Dimension(d.width, 50);

        LinkedList<Product> list = new LinkedList<>();

        for(int i = 1; i <= 100; i++){
            list.add(new Product("p".concat(String.valueOf(i)), prodD));
        }

        p.setListOfProducts(list);
        p.testPanel(d);
    }

}
