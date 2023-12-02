package gbuysytem.GUI.Sidebar;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class RoundedIconButton extends JButton {

    private Color buttonColor;
    private Color hoverColor;
    private Color pressedColor;
    private Font buttonFont;
    private ImageIcon icon;
    private int cornerRadius;

    public RoundedIconButton(String text) {
        super(text);
        this.buttonColor = getBackground();
        this.cornerRadius = 10;
        setContentAreaFilled(false);
        updateHoverColor();
        updatePressedColor();
    }

    public RoundedIconButton(String text, ImageIcon icon){
        super(text, icon);
        this.buttonColor = getBackground();
        this.cornerRadius = 10;
        setContentAreaFilled(false);
        updateHoverColor();
        updatePressedColor();
    }

    private void updateHoverColor() {
        float[] hsb = Color.RGBtoHSB(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), null);
        float brightness = Math.max(0, hsb[2] - 0.1f);
        hoverColor = Color.getHSBColor(hsb[0], hsb[1], brightness);
    }

    private void updatePressedColor() {
        float[] hsb = Color.RGBtoHSB(hoverColor.getRed(), hoverColor.getGreen(), hoverColor.getBlue(), null);
        float brightness = Math.max(0, hsb[2] - 0.1f);
        pressedColor = Color.getHSBColor(hsb[0], hsb[1], brightness);
    }

    public void setButtonColor(Color buttonColor) {
        this.buttonColor = buttonColor;
        updateHoverColor();
        updatePressedColor();
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public void setButtonFont(Font buttonFont) {
        this.buttonFont = buttonFont;
        setFont(buttonFont);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2.setColor(pressedColor);
        } else if (getModel().isRollover()) {
            g2.setColor(hoverColor);
        } else {
            g2.setColor(buttonColor);
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // if (icon != null) {
        //     int iconX = (getWidth() - icon.getIconWidth()) / 2;
        //     int iconY = (getHeight() - icon.getIconHeight()) / 2;
        //     icon.paintIcon(this, g2, iconX, iconY);
        // }

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // You can implement border painting if needed
    }

    @Override
    public boolean isFocusPainted() {
        return false;
    }
}
