package fr.ryfax.rge.test.scenes;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.scenes.Scene;
import fr.ryfax.rge.engine.global.sprite.SpriteSheetLoader;
import fr.ryfax.rge.engine.object.modules.InformationsPanel;
import fr.ryfax.rge.engine.object.modules.button.Button;
import fr.ryfax.rge.engine.object.modules.button.ButtonBuilder;
import fr.ryfax.rge.engine.utils.movements.Vector2D;
import fr.ryfax.rge.test.ButtonEvents;
import fr.ryfax.rge.test.Main;

public class Menu {

    private final Engine engine = Main.getInstance();
    private final Scene scene;

    public Menu() {
        scene = engine.getSceneBuilder().setName("menu").build();
        setButtons();
    }

    private void setButtons() {
        SpriteSheetLoader ssl = new SpriteSheetLoader("fr/ryfax/rge/assets/gui/button.png");
        ssl.setCellSize(401, 174);
        ssl.setSpritesSize(200, 87);
        ssl.load();

        ButtonBuilder bb = new ButtonBuilder();
        bb.setBackground(ssl.getSprites().get(0));
        bb.setListener(new ButtonEvents(ssl.getSprites()));
        bb.setPosition(new Vector2D(engine.getWindow().getCanvas().getWidth()/2d - 100, engine.getWindow().getCanvas().getHeight()/2d - 100));
        bb.setSize(200, 87);
        bb.setText("Play");
        Button playButton = bb.build();

        bb.setPosition(new Vector2D(engine.getWindow().getCanvas().getWidth()/2d - 100, engine.getWindow().getCanvas().getHeight()/2d));
        bb.setListener(new ButtonEvents(ssl.getSprites()));
        bb.setText("Quit");
        Button quitButton = bb.build();

        scene.addGameObject(playButton, 10);
        scene.addGameObject(quitButton, 10);
        scene.addGameObject(new InformationsPanel(), 10);
    }

    /*
     * Getters
     */
    public Scene getScene() { return scene; }
}
