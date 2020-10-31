package fr.ryfax.rge.engine.object.modules.tilemap;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.image.Image;
import fr.ryfax.rge.engine.image.ImageBuilder;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.movements.Vector2D;
import fr.ryfax.rge.engine.utils.path.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;

public class TileMap implements VisualGameObject {

    /*
     * TODO: Système chunk pour opti l'affichage
     */

    private Image[] tiles;
    private Vector2D location = new Vector2D(0, 0);
    private Vector2D highestLoc = new Vector2D(0, 0);
    private int cellWidth, cellHeight;
    private BufferedImage tilesImg;
    private HashMap<int[], Integer> cells = new HashMap<>();

    public void init(Engine engine) {}
    public TileMap(Resource resource, int baseX, int baseY, int width, int height) {
        Image image = new ImageBuilder(resource).build();

        cellWidth = width;
        cellHeight = height;

        int nbCellsX = image.getWidth() / width;
        int nbCellsY = image.getHeight() / height;

        tiles = new Image[nbCellsX * nbCellsY];

        for (int y = 0; y < nbCellsY; y++) {
            for (int x = 0; x < nbCellsX; x++) {
                tiles[y*nbCellsY + x] = new ImageBuilder(image.getBufferedImage().getSubimage(baseX + x * width, baseY + y * height, width, height)).build();
            }
        }
    }

    public TileMap setLocation(Vector2D location) {
        this.location = location;
        return this;
    }

    public TileMap setCell(int x, int y, int id) {
        Vector2D pos = new Vector2D(x, y);
        Vector2D nullVec = new Vector2D(0, 0);
        cells.put(pos.toTableInt(), id);

        if(pos.distance(nullVec) > highestLoc.distance(nullVec)) {
            highestLoc = pos;
        }

        return this;
    }

    public TileMap build() {
        Image img = ImageBuilder.createBlankImage((int) highestLoc.x * cellWidth + cellWidth, (int) highestLoc.y * cellHeight + cellHeight, true);
        Graphics2D g2d = (Graphics2D) img.getBufferedImage().getGraphics();

        for(int[] coords : cells.keySet())
            g2d.drawImage(tiles[cells.get(coords)].getBufferedImage(), coords[0] * cellWidth, coords[1] * cellHeight, null);

        cells.clear();
        tilesImg = img.getBufferedImage();
        return this;
    }

    /*
     * Visuals
     */
    public void draw(Drawer drawer) {
        drawer.image(tilesImg, location.x, location.y);
    }

    public void update(int tick) {}
}
