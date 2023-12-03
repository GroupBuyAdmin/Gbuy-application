package gbuysytem.GUI.Body.DashboardPanels.GroupbuysPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import gbuysytem.GUI.Body.DashboardPanels.ColorPalettes.GBuyPalette;
import gbuysytem.GUI.Body.DashboardPanels.Misc.PanelReturner;
import gbuysytem.GUI.Body.DashboardPanels.ProductsPanel.Product;
import gbuysytem.GUI.Body.fonts.GbuyFont;

public class Groupbuys implements PanelReturner{
    private JPanel masterPanel;
    private JPanel scrollablePanel;
    private JScrollPane scrollPane;
    private List<GroubuysItemPanel> groupbuyPanels;

    public Groupbuys(Dimension dimension){
        masterPanel = new JPanel();
        masterPanel.setPreferredSize(dimension);

        groupbuyPanels = new ArrayList<>();

        scrollablePanel = new JPanel();

        scrollablePanel.setLayout(new GridLayout(0, 5, 30, 15));
        scrollablePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        scrollablePanel.setBackground(Color.white);

        scrollPane = new JScrollPane(scrollablePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
        scrollPane.setBackground(Color.white);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

        masterPanel.setLayout(new BorderLayout());
        masterPanel.setBackground(Color.white);


        JLabel groupbuyLabel = new JLabel("Groupbuys");
        groupbuyLabel.setFont(GbuyFont.MULI_BOLD.deriveFont(20f));
        groupbuyLabel.setForeground(GBuyPalette.CUSTOM_BLACK);
        groupbuyLabel.setHorizontalAlignment(SwingConstants.LEFT);

        masterPanel.add(groupbuyLabel,BorderLayout.NORTH);
        masterPanel.add(scrollPane, BorderLayout.CENTER);

        addDummyPanels();
    }

    public void addGroupbuyPanel(Product p){
        GroubuysItemPanel g = new GroubuysItemPanel(p);
        groupbuyPanels.add(g);

        updateGroupbuys();
    }

    private void updateGroupbuys(){
        scrollablePanel.removeAll();
        for(GroubuysItemPanel groubuysItemPanel : groupbuyPanels){
            JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
            p.setOpaque(false);
            p.add(groubuysItemPanel);
            scrollablePanel.add(p);
        }

        scrollablePanel.revalidate();
        scrollablePanel.repaint();
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

    private void addDummyPanels(){
        // int dummyPanelCount = 20;
        // ImageIcon dummyImage = new ImageIcon("src/gbuysytem/GUI/Body/DashboardPanels/ProductsPanel/img/dummyImage.png");
        // for(int i = 0; i < dummyPanelCount; i++){
        //     addGroupbuyPanel(new Product(dummyImage, "product " + String.valueOf(i+1), "null", "null", "null", "null"));
        // }
    }

    public static void main(String[] args) {
        Groupbuys g = new Groupbuys(new Dimension(1000, 600));
        g.testPanel();
    }
}
