package gbuysytem.GUI.Body.fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public enum GbuyFonts {
    Kamerik_Bold("src/gbuysytem/GUI/Body/fonts/kamerik/Kamerik105-Book.ttf"),
    Kamerik_Book("src/gbuysytem/GUI/Body/fonts/kamerik/Kamerik105-Book.ttf"),
    Kamerik_Heavy("src/gbuysytem/GUI/Body/fonts/kamerik/Kamerik105-Heavy.ttf"),
    Kamerik_Light("src/gbuysytem/GUI/Body/fonts/kamerik/Kamerik105-Light.ttf"),
    Kamerik_Thin("src/gbuysytem/GUI/Body/fonts/kamerik/Kamerik105-Thin.ttf");

    Font font;
    String fontPath;

    GbuyFonts(String path){
        this.fontPath = path;
        this.font = createFont(fontPath);
    }

    public Font getFont(){
        return font;
    }

    public String getFontPath(){
        return fontPath;
    }

    private static Font createFont(String path){
        Font f = null;
        try {
            f = Font.createFont(Font.TRUETYPE_FONT, new File(path));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path)));
            return f;
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return f;
    }
}
