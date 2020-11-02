package fr.ryfax.rge.test;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Parameters;
import fr.ryfax.rge.engine.global.scenes.Scene;
import fr.ryfax.rge.engine.global.scenes.SceneBuilder;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.object.GameObject;
import fr.ryfax.rge.engine.object.modules.DebugTitle;
import fr.ryfax.rge.engine.object.modules.InformationsPanel;
import fr.ryfax.rge.engine.object.modules.tilemap.TileMap;
import fr.ryfax.rge.engine.utils.movements.Vector2D;
import fr.ryfax.rge.engine.utils.path.Resource;

import java.awt.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Engine engine = new Engine("RyGame", 1280, 720);
        Parameters parameters = engine.getParameters();
        parameters.setClearBufferColor(Color.BLACK);
        parameters.setLimitFps(false);
        parameters.setAntiAliasing(true);
        parameters.setQualityRendering(true);
        parameters.setLimitOverload(0);

        SceneBuilder sb = engine.getSceneBuilder();

        Scene scene = sb.setName("TestingScene").build();
        SceneManager.setScene(scene);

        scene.addGameObject(new DebugTitle(), 0);
        scene.addGameObject(new InformationsPanel(), 9999);

        TileMap tm = new TileMap(new Resource("resource/Tiles.png"), 0, 0, 16, 16);
        tm.setLocation(new Vector2D(200, 200));

        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                tm.setCell(x, y, new Random().nextInt(6));
            }
        }

        scene.addGameObject(tm, 1);
        engine.init();
    }

}
