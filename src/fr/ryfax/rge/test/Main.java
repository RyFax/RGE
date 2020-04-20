package fr.ryfax.rge.test;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Parameters;
import fr.ryfax.rge.engine.global.sprite.SpriteAnimation;
import fr.ryfax.rge.engine.global.sprite.SpriteSheetLoader;
import fr.ryfax.rge.engine.global.scenes.Scene;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.object.elements.entity.Entity;
import fr.ryfax.rge.engine.object.elements.entity.modules.AnimatedSprite;
import fr.ryfax.rge.engine.object.modules.InformationsPanel;
import fr.ryfax.rge.engine.utils.Sleep;
import fr.ryfax.rge.engine.utils.movements.Vector2D;
import fr.ryfax.rge.test.player.Player;
import fr.ryfax.rge.test.scenes.Introduction;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    /*
     * To test RGE
     */
    public static void main(String[] args) {
        Engine engine = new Engine("RGE - Example", 1280, 720);
        SceneManager sm = engine.getSceneManager();
        Parameters param = engine.getParameters();

        param.setLimitFps(false);
        param.setAntiAliasing(false);
        param.setQualityRendering(false);
        param.setCursor(Parameters.RGE_CURSOR_DEFAULT);
        param.setLimitOverload(Parameters.RGE_OVERLOAD_MEDIUM);
        param.setClearBufferColor(new Color(26, 150, 238));

        Scene game = engine.getSceneBuilder().setName("menu").build();
        Scene introScene = new Introduction(engine, game).getScene();
        Player player = new Player(engine);

        game.addGameObject(player.getEntity(), 1);
        game.addGameObject(new InformationsPanel(engine), 1000);

        sm.setScene(introScene);

        engine.init();
    }

}
