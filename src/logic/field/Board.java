package logic.field;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.EnumMap;
import java.util.Map;
import logic.util.Dir;
import logic.util.Utils;
import logic.util.Vector;

public class Board {

    private BufferedImage img = new BufferedImage(Utils.BOARD_SIZE * Utils.TILE_SIZE + 3 * Utils.TILE_SIZE, Utils.BOARD_SIZE * Utils.TILE_SIZE + 3 * Utils.TILE_SIZE, BufferedImage.TYPE_INT_RGB);
    private Graphics2D g2d = img.createGraphics();

    private final Point pos;
    private final EnumMap<Dir, Side> sides;

    private final GridObject[][] grid = new GridObject[Utils.BOARD_SIZE][Utils.BOARD_SIZE];

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
        g2d.setColor(sides.get(Dir.UP).getColor());
        g2d.fillRect(
                Utils.TILE_SIZE,
                0, Utils.BOARD_SIZE * Utils.TILE_SIZE,
                Utils.TILE_SIZE / 2);
        g2d.setColor(sides.get(Dir.DOWN).getColor());
        g2d.fillRect(
                Utils.TILE_SIZE,
                Utils.TILE_SIZE * (Utils.BOARD_SIZE + 1) + Utils.TILE_SIZE / 2,
                Utils.BOARD_SIZE * Utils.TILE_SIZE,
                Utils.TILE_SIZE / 2);

        g2d.setColor(sides.get(Dir.RIGHT).getColor());
        g2d.fillRect(
                Utils.TILE_SIZE * (Utils.BOARD_SIZE + 1) + Utils.TILE_SIZE / 2,
                Utils.TILE_SIZE, Utils.TILE_SIZE / 2,
                Utils.BOARD_SIZE * Utils.TILE_SIZE);
        g2d.setColor(sides.get(Dir.LEFT).getColor());
        g2d.fillRect(0,
                Utils.TILE_SIZE, Utils.TILE_SIZE / 2,
                Utils.BOARD_SIZE * Utils.TILE_SIZE);
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
