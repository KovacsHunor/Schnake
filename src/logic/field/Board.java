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

    private BufferedImage img = new BufferedImage(Utils.BOARD_SIZE * Utils.TILE_SIZE + 3 * Utils.TILE_SIZE, Utils.BOARD_SIZE * Utils.TILE_SIZE + 3 * Utils.TILE_SIZE, BufferedImage.TYPE_INT_RGB);
    private Graphics2D g2d = img.createGraphics();

    private final Point pos;
    private final EnumMap<Dir, Side> sides;

    private final GridObject[][] grid = new GridObject[Utils.BOARD_SIZE][Utils.BOARD_SIZE];
    private static final MyPolygon[] sidePolygons = new MyPolygon[2];

    static {
        int offset = (int) (0.433f * Utils.TILE_SIZE);
        int trix = ((Utils.BOARD_SIZE) * Utils.TILE_SIZE);

        sidePolygons[0] = new MyPolygon(
                new int[]{0, trix - offset, trix, trix - offset, 0, offset},
                new int[]{0, 0, Utils.TILE_SIZE / 4, Utils.TILE_SIZE / 2, Utils.TILE_SIZE / 2, Utils.TILE_SIZE / 4},
                6
        );

        sidePolygons[1] = new MyPolygon(
                new int[]{0, trix, trix, 0},
                new int[]{0, 0, Utils.TILE_SIZE/2, Utils.TILE_SIZE/2},
                4
        );
    }

    public Board(Point pos) {
        sides = new EnumMap<>(Dir.class);

        sides.put(Dir.UP, new Side(new Color(0, 0, 0), this, Dir.UP));
        sides.put(Dir.DOWN, new Side(new Color(0, 0, 0), this, Dir.DOWN));
        sides.put(Dir.RIGHT, new Side(new Color(0, 0, 0), this, Dir.RIGHT));
        sides.put(Dir.LEFT, new Side(new Color(0, 0, 0), this, Dir.LEFT));

        this.pos = pos;
    }

    public GridObject getGridAt(Vector v) {
        return grid[v.x][v.y];
    }

    public void setGrid(Vector v, GridObject go) {
        grid[v.x][v.y] = go;
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
            g2d.fillPolygon(polygon.translate(new Vector(Utils.TILE_SIZE, 0)));
        } else {
            g2d.fillPolygon(polygon.mirrorH().translate(new Vector(Utils.TILE_SIZE * (1 + Utils.BOARD_SIZE), 0)));
        }

        side = sides.get(Dir.DOWN);
        g2d.setColor(side.getColor());
        if (side.getOrientation()) {
            translation = new Vector(Utils.TILE_SIZE * (1 + Utils.BOARD_SIZE), (int) ((Utils.BOARD_SIZE + 1.5) * Utils.TILE_SIZE));
            g2d.fillPolygon(polygon.mirrorH().translate(translation));
        } else {
            translation = new Vector(Utils.TILE_SIZE, (int) ((1.5 + Utils.BOARD_SIZE) * Utils.TILE_SIZE));
            g2d.fillPolygon(polygon.translate(translation));
        }

        side = sides.get(Dir.LEFT);
        g2d.setColor(side.getColor());
        if (side.getOrientation()) {
            translation = new Vector(0, ((Utils.BOARD_SIZE + 1) * Utils.TILE_SIZE));
            g2d.fillPolygon(polygon.mirrorH().rotate().translate(translation));
        } else {
            translation = new Vector(0, Utils.TILE_SIZE);
            g2d.fillPolygon(polygon.rotate().translate(translation));

        }

        side = sides.get(Dir.RIGHT);
        g2d.setColor(side.getColor());
        if (side.getOrientation()) {
            translation = new Vector((int) (Utils.TILE_SIZE * (1.5 + Utils.BOARD_SIZE)), Utils.TILE_SIZE);
            g2d.fillPolygon(polygon.rotate().translate(translation));
        } else {
            translation = new Vector((int) (Utils.TILE_SIZE * (1.5 + Utils.BOARD_SIZE)), ((Utils.BOARD_SIZE + 1) * Utils.TILE_SIZE));
            g2d.fillPolygon(polygon.mirrorH().rotate().translate(translation));
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        g2d.setColor(Utils.BACKGROUND_COLOR);
        g2d.fillRect(0, 0, Utils.BOARD_SIZE * Utils.TILE_SIZE + 3 * Utils.TILE_SIZE, Utils.BOARD_SIZE * Utils.TILE_SIZE + 3 * Utils.TILE_SIZE);

        drawSides();

        // draw the checkered grid
        for (int row = 0; row < Utils.BOARD_SIZE; row++) {
            for (int col = 0; col < Utils.BOARD_SIZE; col++) {
                if (grid[col][row] != null) {
                    g2d.setColor(grid[col][row].getColor());
                } else {
                    if ((row + col) % 2 == 1) {
                        g2d.setColor(new Color(60, 60, 60));
                    } else {
                        g2d.setColor(new Color(50, 50, 50));
                    }
                }

                g2d.fillRect(
                        (col + 1) * Utils.TILE_SIZE,
                        (row + 1) * Utils.TILE_SIZE,
                        Utils.TILE_SIZE,
                        Utils.TILE_SIZE);
            }
        }

        g.drawImage(img, (pos.x) * (Utils.BOARD_SIZE * Utils.TILE_SIZE + 3 * Utils.TILE_SIZE),
                (pos.y) * (Utils.BOARD_SIZE * Utils.TILE_SIZE + 3 * Utils.TILE_SIZE), observer);
    }

    public Point getPos() {
        return pos;
    }
}
