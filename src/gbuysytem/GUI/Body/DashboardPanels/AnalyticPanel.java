package gbuysytem.GUI.Body.DashboardPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gbuysytem.GUI.Body.DashboardPanels.ColorPalettes.AnalyticsColorPalette.AnalyticsPalette;
import gbuysytem.GUI.Body.fonts.GbuyFonts;

public class AnalyticPanel implements PanelReturner{
    //main attributes
    private String analyticName;
    private String analyticValue;
    private String analyticPercent;

    //getter and setters
    public String getAnalyticName() {return analyticName;}
    public String getAnalyticValue() {return analyticValue;}
    public String getAnalyticPercent() {return analyticPercent;}

    public void setAnalyticName(String analyticName) {this.analyticName = analyticName;}
    public void setAnalyticValue(String analyticValue) {this.analyticValue = analyticValue;}
    public void setAnalyticPercent(String analyticPercent) {this.analyticPercent = analyticPercent;}

    //ALL PANELS MAIN CONTAINERS-------------------------
    private JPanel masterPanel; //this is the panel that will be returned
    private JPanel topPanel;
    private JPanel bottomPanel;

    //TOP PANEL COMPONENTS-------------------------------
    private JPanel topLeft;
    private JPanel topCenter;
    private JLabel topRight;

    //TOPLEFT Graphic color----------------------------------------
    Color leftGraphicColor;

    //TOPRIGHT ICON--------------------------------------
    ImageIcon rightIcon;

    //TOPCENTER COMPONENTS-------------------------------
    JLabel analyticNameLabel;
    JLabel analyticValueLabel;

    //BOTTOM PANEL COMPONENTS----------------------------
    JLabel analyticStat;

    //DIMENSIONS-----------------------------------------
    private Dimension analyticDimension;
    private Dimension topPanelDimension;
    private Dimension bottomPanelDimension;

    //FONT SIZES---------------------------------------
    private float analyticNameFontSize = 12f;
    private float analyticValueFontSize = 30f;
    private float analyticPercentFontSize = 15f;

    //temporary colorpallete will change latur
    Color white = Color.decode("#FFFFFF");

    Color masterPanelColor = white;
    Color topPanelcolor = white;
    Color bottomPanelcolor = white;
    Color topLeftColor = white;
    Color topCenterColor = white;
    Color topRightColor = white;

    //textColors
    Color topCenterNameColor = white;
    Color topCenterFieldColor = white;
    Color analyticStatColor = Color.decode("#69db7c");

    //main constructor to call in analytics class
    public AnalyticPanel(String analyticName, Color leftGraphicColor, ImageIcon rightIcon, Dimension analyticDimension){

        this.analyticDimension = analyticDimension;
        this.analyticName = analyticName;
        this.analyticValue = "null";
        this.analyticPercent = "null";
        this.leftGraphicColor = leftGraphicColor;
        this.rightIcon = rightIcon;
        
        //setting up sizes, initializing components, and master panel
        setSizes();
        generateComponents();
        setupMasterPanel();

        //setting up components in top panel
        setupTopPanel();
        setupTopPanelComponents();
        setupTopCenterPanel();
        
        //setting up components in bottom panel
        setupBottomPanel();
        
        //add all generated components to masterpanel
        addAlltoMasterPanel();
    }

    private void setSizes() {
        topPanelDimension = new Dimension(analyticDimension.width, analyticDimension.height*3/4);
        bottomPanelDimension = new Dimension(analyticDimension.width, analyticDimension.height/4);
    }

    private void generateComponents() {
        this.masterPanel = new RoundedPanel();
        setToCustomBorder((RoundedPanel) masterPanel);
        this.topPanel = new JPanel();
        this.bottomPanel = new JPanel();
        this.topLeft = new JPanel();
        this.topCenter = new JPanel();
        this.analyticNameLabel = new JLabel();
        this.analyticValueLabel = new JLabel();
        this.analyticPercent = "null";
        this.analyticStat = new JLabel();
    }
    
    private void setupMasterPanel() {
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel.setBackground(masterPanelColor);
        masterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void setupTopPanel() {
        topPanel.setBackground(topPanelcolor);
        topPanel.setPreferredSize(topPanelDimension);

    }
    
    private void setupTopPanelComponents() {
        topPanel.setLayout(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Dimension topLeftDimension = new Dimension(topPanelDimension.width*6/30, topPanelDimension.height);
        Dimension topCenterDimension = new Dimension(topPanelDimension.width*14/30, topPanelDimension.height);
        Dimension topRightDimension = new Dimension(topPanelDimension.width*10/30, topPanelDimension.height);

        topLeft.setPreferredSize(topLeftDimension);
        topCenter.setPreferredSize(topCenterDimension);
        
        //setup toprightpanel
        topRight = new JLabel(resizeIcon(rightIcon), JLabel.CENTER);
        topRight.setPreferredSize(topRightDimension);

        //setup topleftpanel graphic
        topLeft.setBackground(topLeftColor);
        JPanel topLeftGraphic = new RoundedPanel();
        topLeftGraphic.setPreferredSize(new Dimension(6, topLeftDimension.height));
        topLeftGraphic.setBackground(leftGraphicColor);
        topLeft.setLayout(new FlowLayout(FlowLayout.LEADING));
        setToCustomBorder((RoundedPanel) topLeftGraphic);
        topLeft.add(topLeftGraphic);
        topLeft.addComponentListener(new ComponentListener() {
            public void componentResized(ComponentEvent e) {
                topLeftGraphic.setPreferredSize(new Dimension(6, topLeft.getHeight()));
                topLeft.revalidate();
            }
            public void componentMoved(ComponentEvent e) {
            }
            public void componentShown(ComponentEvent e) {
            }
            public void componentHidden(ComponentEvent e) {
            }
            
        });

        topCenter.setBackground(topCenterColor);
        topRight.setBackground(topRightColor);


        
        topPanel.add(topLeft, BorderLayout.WEST);
        topPanel.add(topCenter, BorderLayout.CENTER);
        topPanel.add(topRight, BorderLayout.EAST);
    }

    private void setupTopCenterPanel() {
        topCenter.setLayout(new BoxLayout(topCenter, BoxLayout.Y_AXIS));
        // topCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel namePanel = new JPanel();
        JPanel fieldPanel = new JPanel();
        Dimension topCenterDimension = topCenter.getPreferredSize();

        namePanel.setPreferredSize(new Dimension(topCenterDimension.width, topCenterDimension.height/2));
        fieldPanel.setPreferredSize(new Dimension(topCenterDimension.width, topCenterDimension.height/2));

        namePanel.setBackground(topCenterNameColor);
        fieldPanel.setBackground(topCenterFieldColor);

        topCenter.add(namePanel);
        topCenter.add(fieldPanel);

        namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(analyticNameLabel);
        analyticNameLabel.setText(analyticName);
        setFonts(analyticNameLabel, GbuyFonts.Kamerik_Book, analyticNameFontSize);
        addFontResizer(namePanel, analyticNameLabel, analyticNameFontSize);

        fieldPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        fieldPanel.add(analyticValueLabel);
        analyticValueLabel.setText(analyticValue);
        setFonts(analyticValueLabel, GbuyFonts.Kamerik_Bold, analyticValueFontSize);
        addFontResizer(fieldPanel, analyticValueLabel, analyticValueFontSize);
    }

    private void setupBottomPanel() {
        bottomPanel.setBackground(bottomPanelcolor);
        bottomPanel.setPreferredSize(bottomPanelDimension);

        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        bottomPanel.add(analyticStat);
        
        analyticStat.setText(analyticPercent);
        setFonts(analyticStat, GbuyFonts.Kamerik_Book, analyticPercentFontSize);
        analyticStat.setForeground(analyticStatColor);
        // addFontResizer(bottomPanel, analyticStat);
    }

    private void addAlltoMasterPanel() {
        masterPanel.add(topPanel);
        masterPanel.add(rigidSpacing(analyticDimension, 10));
        masterPanel.add(bottomPanel);
    }

    //HELPER methods--------------------------------------------------------
    private void setFonts(JLabel jlabel, GbuyFonts gFont, float fontSize){
        jlabel.setFont(gFont.getFont().deriveFont(fontSize));
    }

    private void resizeFont(JLabel jLabel, float newSize){
        jLabel.setFont(jLabel.getFont().deriveFont((newSize)));
    }

    private ImageIcon resizeIcon(ImageIcon icon){ //imong code carl hahahha
        if(icon != null){
            ImageIcon thisIcon = icon;   
            Image scaledIcon = thisIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon setIcon = new ImageIcon(scaledIcon);    
            return setIcon;
        }
        return null;
    }

    private void addFontResizer(JPanel jPanel, JLabel jLabel, float fontSize) {
        jPanel.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = jPanel.getWidth();
                resizeFont(jLabel, width/15 + fontSize );   //idk arithmetic magic guro
                jLabel.revalidate();
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
    }
    
    private Component rigidSpacing(Dimension d, int height){
        return Box.createRigidArea(new Dimension(d.width, height));
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
        f.setSize(analyticDimension);
        f.add(masterPanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        AnalyticPanel a = new AnalyticPanel("Customer", AnalyticsPalette.CUSTOMER.getColor(), new ImageIcon("src/gbuysytem/GUI/Body/DashboardPanels/img/customer.png", "customer icon"), new Dimension(500, 300));
        a.testPanel();
    }
}
