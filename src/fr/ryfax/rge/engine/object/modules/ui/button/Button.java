package fr.ryfax.rge.engine.object.modules.ui.button;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.image.Image;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.drawing.font.Font;
import fr.ryfax.rge.engine.utils.drawing.font.FontLoader;
import fr.ryfax.rge.engine.utils.drawing.font.FontRenderer;
import fr.ryfax.rge.engine.utils.drawing.scaler.Scaler;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;

public class Button implements VisualGameObject {

    private final ButtonListener listener;

    private Engine engine;
    private Font font;
    private String textStr;
    private Image sprite, text;
    private Scaler scaler;
    private Vector2D position;
    private Dimension size;
    private boolean hover = false, click = false;

    //TODO: Faire les fonts
    public Button(Scaler scaler, Image sprite, String text, ButtonListener listener, Font font) {
        this.listener = listener;
        this.scaler = scaler;
        this.position = scaler.getPosition();
        this.size = scaler.getSize();
        this.sprite = sprite;
        this.textStr = text;

        if(listener != null) listener.init(this);
    }

    public void init(Engine engine) {
        this.engine = engine;

        Font font = FontLoader.getLoadedFonts().get(FontLoader.RGE_SHADOW);
        FontRenderer fr = new FontRenderer(font);

        this.text = fr.build(textStr);
    }

    public void update(double delta, int accumulator) {
        position = scaler.getPosition();

        Point mouse = engine.getMousePosition();

        if(mouse != null) {
            // "If the mouse is on the button"
            if(mouse.x <= position.x + size.width && mouse.x >= position.x && mouse.y <= position.y + size.height && mouse.y >= position.y){
                if(!hover) {
                    listener.onMouseEntered();
                    hover = true;
                }

                if(!click) {
                    if(engine.getButtonsPressed().contains(1)) {
                        listener.onClick();
                        click = true;
                    }
                }else if(!engine.getButtonsPressed().contains(1)) {
                    listener.onClickExit();
                    click = false;
                }

            }else if(hover) {
                listener.onMouseExited();
                hover = false;
                click = false;
            }
        }
    }

    public void draw(Drawer drawer) {
        drawer.imageNotRelative(sprite, (int) position.x, (int) position.y);
        drawer.imageNotRelative(text,
                (int) position.x + size.width/2.f - text.getBufferedImage().getWidth()/2.f,
                (int) position.y + size.height/2.f - text.getBufferedImage().getHeight()/2.f);
    }


    /*
     * Getters
     */
    public String getText() { return textStr; }

    /*
     * Setters
     */
    public void setPosition(Vector2D position) { this.position = position; }
    public void setSize(Dimension size) { this.size = size; }
    public void setSprite(Image sprite) { this.sprite = sprite; }
    public void setText(Image text) { this.text = text; }

}
