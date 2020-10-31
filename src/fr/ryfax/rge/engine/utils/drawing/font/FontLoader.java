package fr.ryfax.rge.engine.utils.drawing.font;

import fr.ryfax.rge.engine.utils.path.Resource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class FontLoader {

    // Constants
    public static final String RGE_DEFAULT = "RGE_DEFAULT";
    public static final String RGE_SHADOW = "RGE_SHADOW";
    public static final String RGE_DEFAULT_BACKGROUND = "RGE_DEFAULT_BACKGROUND";
    public static final String RGE_SHADOW_BACKGROUND = "RGE_SHADOW_BACKGROUND";

    // Variables
    private Color backgroundColor = new Color(0, 0, 0, 0), fontColor;
    private BufferedImage buff;
    private HashMap<Character, Integer> chars;
    private int spaceToRemove = 0;
    private final int[] shadow = new int[]{0, 0};

    private static final HashMap<String, Font> fonts = new HashMap<>();

    // Setters
    public void setSpecialCharsSize(HashMap<Character, Integer> chars) {
        this.chars = chars;
    }
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }
    public void setASCII(Resource resource) {
        try { this.buff = ImageIO.read(resource.getStream()); }
        catch (IOException ignore) {}
    }
    public void setSpacingChar(int toRemove) {
        this.spaceToRemove = toRemove;
    }

    public void setShadow(int x, int y) {
        this.shadow[0] = x;
        this.shadow[1] = y;
    }


    public void load(String nameFont) {
        fonts.put(nameFont, new Font(buff, chars, fontColor, backgroundColor, spaceToRemove, shadow));
    }

    // Getters
    public static Font getDefaultFont() { return fonts.get(RGE_DEFAULT); }
    public static HashMap<String, Font> getLoadedFonts() { return fonts; }
}
