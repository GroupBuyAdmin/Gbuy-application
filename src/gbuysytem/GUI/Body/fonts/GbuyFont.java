package gbuysytem.GUI.Body.fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class GbuyFont {
    public static final Font MULI_BOLD = createFont("src/gbuysytem/GUI/Body/fonts/Muli/Muli-Bold.ttf");
    public static final Font MULI_EXTRA_LIGHT = createFont("src/gbuysytem/GUI/Body/fonts/Muli/Muli-ExtraLight.ttf");
    public static final Font MULI_LIGHT = createFont("src/gbuysytem/GUI/Body/fonts/Muli/Muli-Light.ttf");
    public static final Font MULI_SEMI_BOLD = createFont("src/gbuysytem/GUI/Body/fonts/Muli/Muli-SemiBold.ttf");

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
