package logic.field;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.EnumMap;
import java.util.Map;
import logic.util.Dir;
import logic.util.MyPolygon;
import logic.util.Utils;
import logic.util.Vector;

public class Board {

    private BufferedImage img = new BufferedImage(Utils.boardSize * Utils.tileSize + 3 * Utils.tileSize, Utils.boardSize * Utils.tileSize + 3 * Utils.tileSize, BufferedImage.TYPE_INT_RGB);
    private Graphics2D g2d = img.createGraphics();

    private final Point pos;
    private final EnumMap<Dir, Side> sides;

    private final GridTile[][] grid = new GridTile[Utils.boardSize][Utils.boardSize];
    private static final MyPolygon[] sidePolygons = new MyPolygon[2];

    public static void setPolygons() {
        int offset = (int) (0.433f * Utils.tileSize);
        int trix = ((Utils.boardSize) * Utils.tileSize);

        MyPolygon arrow = new MyPolygon(
                new int[]{0, trix - offset, trix, trix - offset, 0, offset},
                new int[]{0, 0, Utils.tileSize / 4, Utils.tileSize / 2, Utils.tileSize / 2, Utils.tileSize / 4},
                6
        );
        sidePolygons[0] = arrow;

        MyPolygon rectangle = new MyPolygon(
                new int[]{0, trix, trix, 0},
                new int[]{0, 0, Utils.tileSize / 2, Utils.tileSize / 2},
                4
        );
        sidePolygons[1] = rectangle;
    }

    public Board(Point pos) {
        for (GridTile[] gridTiles : grid) {
            for (int i = 0; i < gridTiles.length; i++) {
                gridTiles[i] = new GridTile();
            }
        }

        sides = new EnumMap<>(Dir.class);

        sides.put(Dir.UP, new Side(new Color(0, 0, 0), this, Dir.UP));
        sides.put(Dir.DOWN, new Side(new Color(0, 0, 0), this, Dir.DOWN));
        sides.put(Dir.RIGHT, new Side(new Color(0, 0, 0), this, Dir.RIGHT));
        sides.put(Dir.LEFT, new Side(new Color(0, 0, 0), this, Dir.LEFT));

        this.pos = pos;
    }

    public GridTile getTile(Vector v) {
        return grid[v.x][v.y];
    }

    public void putOnTile(Vector v, GridObject go) {
        grid[v.x][v.y].put(go);
    }

    public Side getSide(Dir d) {
        return sides.get(d);
    }

    public Map<Dir, Side> getSides() {
        return sides;
    }

    public void drawSides() {

        //  draw the teleporter sides
        Side side;
        Vector translation;

        side = sides.get(Dir.UP);
        MyPolygon polygon = sidePolygons[0];

        g2d.setColor(side.getColor());
        if (side.getOrientation()) {
            g2d.fillPolygon(polygon.translate(new Vector(Utils.tileSize, 0)));
        } else {
            g2d.fillPolygon(polygon.mirrorH().translate(new Vector(Utils.tileSize * (1 + Utils.boardSize), 0)));
        }

        side = sides.get(Dir.DOWN);
        g2d.setColor(side.getColor());
        if (side.getOrientation()) {
            translation = new Vector(Utils.tileSize * (1 + Utils.boardSize), (int) ((Utils.boardSize + 1.5) * Utils.tileSize));
            g2d.fillPolygon(polygon.mirrorH().translate(translation));
        } else {
            translation = new Vector(Utils.tileSize, (int) ((1.5 + Utils.boardSize) * Utils.tileSize));
            g2d.fillPolygon(polygon.translate(translation));
        }

        side = sides.get(Dir.LEFT);
        g2d.setColor(side.getColor());
        if (side.getOrientation()) {
            translation = new Vector(0, ((Utils.boardSize + 1) * Utils.tileSize));
            g2d.fillPolygon(polygon.mirrorH().rotate().translate(translation));
        } else {
            translation = new Vector(0, Utils.tileSize);
            g2d.fillPolygon(polygon.rotate().translate(translation));

        }

        side = sides.get(Dir.RIGHT);
        g2d.setColor(side.getColor());
        if (side.getOrientation()) {
            translation = new Vector((int) (Utils.tileSize * (1.5 + Utils.boardSize)), Utils.tileSize);
            g2d.fillPolygon(polygon.rotate().translate(translation));
        } else {
            translation = new Vector((int) (Utils.tileSize * (1.5 + Utils.boardSize)), ((Utils.boardSize + 1) * Utils.tileSize));
            g2d.fillPolygon(polygon.mirrorH().rotate().translate(translation));
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        g2d.setColor(Utils.BACKGROUND_COLOR);
        g2d.fillRect(0, 0, Utils.boardSize * Utils.tileSize + 3 * Utils.tileSize, Utils.boardSize * Utils.tileSize + 3 * Utils.tileSize);

        drawSides();

        // draw the checkered grid
        for (int row = 0; row < Utils.boardSize; row++) {
            for (int col = 0; col < Utils.boardSize; col++) {
                if (!grid[col][row].isEmpty()) {
                    GridObject go = grid[col][row].upper();
                    g2d.setColor(go.getColor());
                } else {
                    if ((row + col) % 2 == 1) {
                        g2d.setColor(new Color(60, 60, 60));
                    } else {
                        g2d.setColor(new Color(50, 50, 50));
                    }
                }

                g2d.fillRect(
                        (col + 1) * Utils.tileSize,
                        (row + 1) * Utils.tileSize,
                        Utils.tileSize,
                        Utils.tileSize);
            }
        }

        g.drawImage(img, (pos.x) * (Utils.boardSize * Utils.tileSize + 3 * Utils.tileSize),
                (pos.y) * (Utils.boardSize * Utils.tileSize + 3 * Utils.tileSize), observer);
    }

    public Point getPos() {
        return pos;
    }
}
