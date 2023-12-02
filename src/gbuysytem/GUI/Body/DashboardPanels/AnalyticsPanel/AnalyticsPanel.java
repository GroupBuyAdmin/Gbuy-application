package gbuysytem.GUI.Body.DashboardPanels.AnalyticsPanel;

import javax.swing.*;

import gbuysytem.GUI.Body.DashboardPanels.Misc.PanelReturner;
import gbuysytem.GUI.Body.DashboardPanels.Misc.RoundedPanel;
import gbuysytem.GUI.Body.fonts.CustomFont;

import java.awt.*;

public class AnalyticsPanel implements PanelReturner{
    private String analyticName;
    private String analyticValue;
    private String analyticPercent;


    public String getAnalyticName() {
        return analyticName;
    }
    public void setAnalyticName(String analyticName) {
        this.analyticName = analyticName;
    }
    public String getAnalyticValue() {
        return analyticValue;
    }
    public void setAnalyticValue(String analyticValue) {
        this.analyticValue = analyticValue;
    }
    public String getAnalyticPercent() {
        return analyticPercent;
    }
    public void setAnalyticPercent(String analyticPercent) {
        this.analyticPercent = analyticPercent;
    }

    private JPanel masterPanel;

    public AnalyticsPanel(String analyticName, Color leftGraphicColor, ImageIcon rightIcon, Dimension analyticDimension){
        this.analyticName  = analyticName;
        this.analyticValue = "null";
        this.analyticPercent = "null";
        this.masterPanel = new RoundedPanel();
        setToCustomBorder((RoundedPanel) masterPanel);

        masterPanel.setPreferredSize(analyticDimension);
        masterPanel.setMaximumSize(analyticDimension);
        masterPanel.setLayout(new GridBagLayout());
        masterPanel.setBackground(Color.white);

        DetailsPanel detailsPanel = new DetailsPanel(analyticName, analyticValue, leftGraphicColor, rightIcon);
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.red);
        
        JLabel bottomPanel = new JLabel(analyticPercent);
        bottomPanel.setFont(CustomFont.Kamerik_Book.getFont().deriveFont(20f));
        bottomPanel.setForeground(Color.green);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        masterPanel.add(detailsPanel, gbc);
        
        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 0, 0);
        masterPanel.add(bottomPanel, gbc);

    }

    public void testPanel(){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(masterPanel);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        AnalyticsPanel newAnal = new AnalyticsPanel("Customer", Color.red , new ImageIcon("src/gbuysytem/GUI/Body/DashboardPanels/ProductsPanel/dummyImage.png"), new Dimension(500, 300));
        newAnal.testPanel();
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

    class DetailsPanel extends JPanel{
        public DetailsPanel(String analyticName, String analyticValue, Color leftGraphicColor, ImageIcon rightIcon){
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weighty = 0.5;

            gbc.weightx = 0.0;
            gbc.fill = GridBagConstraints.VERTICAL;
            //create IconPanel
            RoundedPanel graphic = new RoundedPanel();
            graphic.setPreferredSize(new Dimension(1, Short.MAX_VALUE));
            // graphic.setMaximumSize(new Dimension(3, Short.MAX_VALUE));
            graphic.setBackground(leftGraphicColor);
            graphic.setShady(false);
            graphic.setArcs(new Dimension(50, 50));
            gbc.gridx++;
            add(graphic,gbc);
            
            JPanel centerPanel = new CenterPanel(analyticName, analyticValue);
            centerPanel.setBackground(Color.WHITE);
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx++;
            gbc.weightx = 1.0;
            add(centerPanel,gbc);
            
            
            JPanel iconPanel = new JPanel();
            iconPanel.setLayout(new BorderLayout());
            ImageIcon resizedIcon = resizeIcon(rightIcon);
            JLabel imageLabel = new JLabel(resizedIcon);
            iconPanel.add(imageLabel, BorderLayout.CENTER);
            iconPanel.setBackground(Color.WHITE);
            gbc.gridx++;
            gbc.weightx = 0.0;
            add(iconPanel, gbc);
            
        }

        private ImageIcon resizeIcon(ImageIcon icon){ 
            if(icon != null){
                ImageIcon thisIcon = icon;   
                Image scaledIcon = thisIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon setIcon = new ImageIcon(scaledIcon);    
                return setIcon;
            }
            return null;
        }


    }

    class CenterPanel extends JPanel{
        public CenterPanel(String analyticName, String analyticValue){
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 0.5;
            gbc.weighty = 0.5;

            JLabel namePanel = new JLabel(analyticName);
            namePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            namePanel.setFont(CustomFont.Kamerik_Book.getFont().deriveFont(35f));
            add(namePanel,gbc);
            
            gbc.gridy++;
            JLabel valuePanel = new JLabel(analyticValue);
            valuePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            valuePanel.setFont(CustomFont.Kamerik_Book.getFont().deriveFont(30f));
            add(valuePanel, gbc);
        }
    }

}
