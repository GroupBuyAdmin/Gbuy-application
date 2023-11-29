package gbuysytem.GUI.Body.fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public enum CustomFont {

    //Kameric
    Kamerik_Bold("src/gbuysytem/GUI/Body/fonts/kamerik/Kamerik105-Book.ttf"),
    Kamerik_Book("src/gbuysytem/GUI/Body/fonts/kamerik/Kamerik105-Book.ttf"),
    Kamerik_Heavy("src/gbuysytem/GUI/Body/fonts/kamerik/Kamerik105-Heavy.ttf"),
    Kamerik_Light("src/gbuysytem/GUI/Body/fonts/kamerik/Kamerik105-Light.ttf"),
    Kamerik_Thin("src/gbuysytem/GUI/Body/fonts/kamerik/Kamerik105-Thin.ttf"),

    //Franca
    Franca_Black("src/gbuysytem/GUI/Body/fonts/franca/Franca-Black.ttf"),
    Franca_Bold("src/gbuysytem/GUI/Body/fonts/franca/Franca-Bold.ttf"),
    Franca_Book("src/gbuysytem/GUI/Body/fonts/franca/Franca-Book.ttf"),
    Franca_Extra_Bold("src/gbuysytem/GUI/Body/fonts/franca/Franca-ExtraBold.ttf"),
    Franca_Extra_Light("src/gbuysytem/GUI/Body/fonts/franca/Franca-ExtraLight.ttf"),
    Franca_Light("src/gbuysytem/GUI/Body/fonts/franca/Franca-Light.ttf"),
    Franca_Medium("src/gbuysytem/GUI/Body/fonts/franca/Franca-Medium.ttf"),
    Franca_SemiBold("src/gbuysytem/GUI/Body/fonts/franca/Franca-SemiBold.ttf"),
    Franca_Thin("src/gbuysytem/GUI/Body/fonts/franca/Franca-Thin.ttf");

    Font font;
    String fontPath;

    CustomFont(String path){
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
