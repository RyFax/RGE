package fr.ryfax.rge.engine.utils.drawing.font;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class FontLoader {

    public static final String RGE_DEFAULT_FONT = "RGE_DEFAULT";
    public static final String RGE_SHADOW = "RGE_SHADOW";

    private Color backgroundColor, fontColor;
    private String path;
    private int spaceToRemove = 0;
    private final int[] shadow = new int[]{0, 0};
    private HashMap<Character, Integer> chars;

    private final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    private final HashMap<String, Font> fonts = new HashMap<>();

    public void setSpecialCharsSize(HashMap<Character, Integer> chars) {
        this.chars = chars;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public void setShadow(int x, int y) {
        this.shadow[0] = x;
        this.shadow[1] = y;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setSpacingChar(int toRemove) {
        this.spaceToRemove = toRemove;
    }

    public void load(String nameFont) {
        InputStream stream = classloader.getResourceAsStream(path);
        try {
            assert stream != null;
            BufferedImage buff = ImageIO.read(stream);
            fonts.put(nameFont, new Font(buff, chars, fontColor, backgroundColor, spaceToRemove, shadow));
        } catch (IOException e) { e.printStackTrace(); }
    }

    public Font getDefaultFont() { return fonts.get(RGE_DEFAULT_FONT); }
    public HashMap<String, Font> getLoadedFonts() { return fonts; }
}
