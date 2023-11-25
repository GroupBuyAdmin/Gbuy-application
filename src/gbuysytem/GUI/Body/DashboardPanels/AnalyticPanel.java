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
    private JPanel allPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;

    //TOP PANEL COMPONENTS-------------------------------
    private JPanel topLeft;
    private JPanel topCenter;
    private JLabel topRight;

    //TOPLEFT ICON----------------------------------------
    ImageIcon leftIcon;

    //TOPRIGHT ICON--------------------------------------
    ImageIcon rightIcon;

    //TOPCENTER COMPONENTS-------------------------------
    JLabel analyticNameLabel;
    JLabel analyticValueLabel;

    //BOTTOM PANEL COMPONENTS----------------------------
    JLabel analyticStat;
    
    //temporary colorpallete will change latur
    Color allPanelcolor = Color.decode("#e7f5ff");
    Color topPanelcolor = Color.decode("#fff9db");
    Color bottomPanelcolor = Color.decode("#ffffff");
    Color topLeftColor = Color.decode("#d0bfff");
    Color topCenterColor = Color.decode("#fff5f5");
    Color topRightColor = Color.decode("#d2bab0");
    Color topCenterNameColor = Color.decode("#e6fcf5");
    Color topCenterFieldColor = Color.decode("#eebefa");
    Color analyticStatColor = Color.decode("#69db7c");

    //sizes--------------------------------------------
    private Dimension analyticDimension;
    private Dimension topPanelDimension;
    private Dimension bottomPanelDimension;

    public AnalyticPanel(String s, ImageIcon leftIcon, ImageIcon rightIcon, Dimension analyticDimension){
        this.analyticDimension = analyticDimension;
        this.analyticName = s;
        this.analyticValue = "null";
        this.analyticPercent = "null";
        this.leftIcon = leftIcon;
        this.rightIcon = rightIcon;

        setSizes();
        generateComponents();
        setupAllPanels();
        setupTopPanel();
        setupBottomPanel();
        addAlltoAllPanels();
    }

    private void generateComponents() {
        this.allPanel = new JPanel();
        this.topPanel = new JPanel();
        this.bottomPanel = new JPanel();
        this.topLeft = new JPanel();
        this.topCenter = new JPanel();
        this.leftIcon = null;
        this.rightIcon = null;
        this.analyticNameLabel = new JLabel();
        this.analyticValueLabel = new JLabel();
        this.analyticPercent = "null";
        this.analyticStat = new JLabel();
    }


    private void setSizes() {
        topPanelDimension = new Dimension(analyticDimension.width, analyticDimension.height*3/4);
        bottomPanelDimension = new Dimension(analyticDimension.width, analyticDimension.height/4);
    }

    private void setFonts(JLabel jlabel, GbuyFonts gFont, float fontSize){
        jlabel.setFont(gFont.getFont().deriveFont(fontSize));
    }

    private void resizeFont(JLabel jLabel, float newSize){
        jLabel.setFont(jLabel.getFont().deriveFont((newSize)));
    }

    private void addAlltoAllPanels() {
        allPanel.add(topPanel);
        allPanel.add(rigidSpacing(analyticDimension, 10));
        allPanel.add(bottomPanel);
    }

    private void setupTopPanel() {
        topPanel.setBackground(topPanelcolor);
        topPanel.setPreferredSize(topPanelDimension);
        setupTopPanelComponents();
    }

    private void setupTopPanelComponents() {
        topPanel.setLayout(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topRight = new JLabel(resizeIcon(rightIcon), JLabel.CENTER);

        topLeft.setBackground(topLeftColor);
        topCenter.setBackground(topCenterColor);
        topRight.setBackground(topRightColor);

        topLeft.setPreferredSize(new Dimension(topPanelDimension.width*6/30, topPanelDimension.height));
        topCenter.setPreferredSize(new Dimension(topPanelDimension.width*14/30, topPanelDimension.height));
        topRight.setPreferredSize(new Dimension(topPanelDimension.width*10/30, topPanelDimension.height));
        
        
        // setIcons(topRight, rightIcon);

        topPanel.add(topLeft, BorderLayout.WEST);
        topPanel.add(topCenter, BorderLayout.CENTER);
        topPanel.add(topRight, BorderLayout.EAST);

        setupTopCenterPanel();
    }

    private void setupTopCenterPanel() {
        topCenter.setLayout(new BoxLayout(topCenter, BoxLayout.Y_AXIS));
        topCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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
        setFonts(analyticNameLabel, GbuyFonts.Kamerik_Book, 20f);
        addFontResizer(namePanel, analyticNameLabel);

        fieldPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        fieldPanel.add(analyticValueLabel);
        analyticValueLabel.setText(analyticValue);
        setFonts(analyticValueLabel, GbuyFonts.Kamerik_Book, 30f);
        addFontResizer(fieldPanel, analyticValueLabel);
    }

    private void setupBottomPanel() {
        bottomPanel.setBackground(bottomPanelcolor);
        bottomPanel.setPreferredSize(bottomPanelDimension);

        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        bottomPanel.add(analyticStat);
        
        analyticStat.setText(analyticPercent);
        setFonts(analyticStat, GbuyFonts.Kamerik_Bold, 20f);
        analyticStat.setForeground(analyticStatColor);
        // addFontResizer(bottomPanel, analyticStat);
    }

    private void setupAllPanels() {
        allPanel.setLayout(new BoxLayout(allPanel, BoxLayout.Y_AXIS));
        allPanel.setBackground(allPanelcolor);
        allPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
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

    private void addFontResizer(JPanel jPanel, JLabel jLabel) {
        jPanel.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = jPanel.getWidth();
                resizeFont(jLabel, width/10);
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

    @Override
    public JPanel getPanel() {
        return allPanel;
    }

    public void testPanel(){
        JFrame f = new JFrame();
        f.setSize(analyticDimension);
        f.add(allPanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    private Component rigidSpacing(Dimension d, int height){
        return Box.createRigidArea(new Dimension(d.width, height));
    }

    public static void main(String[] args) {
        AnalyticPanel a = new AnalyticPanel("Customer", null, new ImageIcon("src/gbuysytem/GUI/Body/DashboardPanels/img/customer.png", "customer icon"), new Dimension(500, 300));
        a.testPanel();
    }
}
