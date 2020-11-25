package fr.ryfax.rge.engine.object.modules;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.scenes.Scene;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.image.Image;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.drawing.scaler.Scaler;
import fr.ryfax.rge.engine.utils.drawing.scaler.ScalerLayout;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;

public class SplashScreen implements VisualGameObject {

    private final Color background;
    private final Scene sceneAfter;
    private final Image image;
    private Engine engine;

    private Scaler scaler;
    private Dimension screenSize;
    private float opacity;
    private int sec = 0;

    public void init(Engine engine) {
        this.engine = engine;

        int width = image.getBufferedImage().getWidth();
        int height = image.getBufferedImage().getHeight();

        scaler = new Scaler(engine).setSize(new Dimension(width, height)).setLayout(ScalerLayout.CENTER);

        screenSize = new Dimension(
                engine.getWindow().getCanvas().getWidth(),
                engine.getWindow().getCanvas().getHeight());

    }

    public SplashScreen(Image image, Color background, Scene sceneAfter) {
        this.background = background;
        this.sceneAfter = sceneAfter;
        this.image = image;
    }

    public void update(double delta, int accumulator) {
        if (accumulator >= 1000) {
            sec++;
        }

        if (sec < 2) {
            if (opacity < 100) {
                opacity += 0.25;
            }
        } else {
            if (opacity > 0) {
                opacity -= 0.25;
            } else {
                SceneManager.setScene(sceneAfter);
            }
        }

        image.setOpacity(opacity / 100f);
    }

    public void draw(Drawer d) {
        Vector2D position = scaler.getPosition();
        d.setColor(background).fillRectNotRelative(new Vector2D(0, 0), screenSize).image(image, position);
    }

    /*
     * Setters
     */
    public SplashScreen setSize(int width, int height) {
        if (engine != null) {
            scaler.setSize(new Dimension(width, height));
        }
        image.resize(width, height);
        return this;
    }

}
