package logic.field;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.EnumMap;
import java.util.Map;
import logic.util.Dir;
import logic.util.Util;
import logic.util.Vector;

public class Board {

    private BufferedImage img = new BufferedImage(Util.BOARD_SIZE * Util.TILE_SIZE + 3 * Util.TILE_SIZE, Util.BOARD_SIZE * Util.TILE_SIZE + 3 * Util.TILE_SIZE, BufferedImage.TYPE_INT_RGB);
    private Graphics2D g2d = img.createGraphics();

    private final Point pos;
    private final EnumMap<Dir, Side> sides;

    private final GridObject[][] grid = new GridObject[Util.BOARD_SIZE][Util.BOARD_SIZE];

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

    public void draw(Graphics g, ImageObserver observer) {
        //  draw the teleporter sides
        g2d.setColor(sides.get(Dir.UP).getColor());
        g2d.fillRect(
                Util.TILE_SIZE * (1),
                0, Util.BOARD_SIZE * Util.TILE_SIZE,
                Util.TILE_SIZE / 2);
        g2d.setColor(sides.get(Dir.DOWN).getColor());
        g2d.fillRect(
                Util.TILE_SIZE * (1),
                Util.TILE_SIZE * (Util.BOARD_SIZE + 1) + Util.TILE_SIZE / 2,
                Util.BOARD_SIZE * Util.TILE_SIZE,
                Util.TILE_SIZE / 2);

        g2d.setColor(sides.get(Dir.RIGHT).getColor());
        g2d.fillRect(
                Util.TILE_SIZE * (Util.BOARD_SIZE + 1) + Util.TILE_SIZE / 2,
                Util.TILE_SIZE * (1), Util.TILE_SIZE / 2,
                Util.BOARD_SIZE * Util.TILE_SIZE);
        g2d.setColor(sides.get(Dir.LEFT).getColor());
        g2d.fillRect(0,
                Util.TILE_SIZE * (1), Util.TILE_SIZE / 2,
                Util.BOARD_SIZE * Util.TILE_SIZE);

        // draw the checkered grid
        for (int row = 0; row < Util.BOARD_SIZE; row++) {
            for (int col = 0; col < Util.BOARD_SIZE; col++) {
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
                        (col + 1) * Util.TILE_SIZE,
                        (row + 1) * Util.TILE_SIZE,
                        Util.TILE_SIZE,
                        Util.TILE_SIZE);
            }
        }

        g.drawImage(img, (pos.x) * (Util.BOARD_SIZE * Util.TILE_SIZE + 3 * Util.TILE_SIZE),
                (pos.y) * (Util.BOARD_SIZE * Util.TILE_SIZE + 3 * Util.TILE_SIZE), observer);
    }

    public Point getPos() {
        return pos;
    }
}
