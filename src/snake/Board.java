package snake;

import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class Board {
    private final Point pos;
    private final EnumMap<Dir, Side> sides;
    private Object[][] grid;

    public Board(Point pos) {
        sides = new EnumMap<>(Dir.class);

        sides.put(Dir.UP, new Side(new Color(0, 0, 0), this, Dir.UP));
        sides.put(Dir.DOWN, new Side(new Color(0, 0, 0), this, Dir.DOWN));
        sides.put(Dir.RIGHT, new Side(new Color(0, 0, 0), this, Dir.RIGHT));
        sides.put(Dir.LEFT, new Side(new Color(0, 0, 0), this, Dir.LEFT));

        grid = new Object[Util.BOARD_SIZE][Util.BOARD_SIZE];

        this.pos = pos;
    }

    public Side getSide(Dir d) {
        return sides.get(d);
    }

    public Map<Dir, Side> getSides() {
        return sides;
    }

    public void draw(Graphics g) {
        //  draw the teleporter sides
        g.setColor(sides.get(Dir.UP).getColor());
        g.fillRect(
                Util.TILE_SIZE * (1 + pos.x * (Util.BOARD_SIZE + 3)),
                Util.TILE_SIZE * (pos.y * (Util.BOARD_SIZE + 3)), Util.BOARD_SIZE * Util.TILE_SIZE,
                Util.TILE_SIZE / 2);
        g.setColor(sides.get(Dir.DOWN).getColor());
        g.fillRect(
                Util.TILE_SIZE * (1 + pos.x * (Util.BOARD_SIZE + 3)),
                Util.TILE_SIZE * (pos.y * (Util.BOARD_SIZE + 3) + Util.BOARD_SIZE + 1) + Util.TILE_SIZE / 2,
                Util.BOARD_SIZE * Util.TILE_SIZE,
                Util.TILE_SIZE / 2);

        g.setColor(sides.get(Dir.RIGHT).getColor());
        g.fillRect(
                Util.TILE_SIZE * (pos.x * (Util.BOARD_SIZE + 3) + Util.BOARD_SIZE + 1) + Util.TILE_SIZE / 2,
                Util.TILE_SIZE * (1 + pos.y * (Util.BOARD_SIZE + 3)), Util.TILE_SIZE / 2,
                Util.BOARD_SIZE * Util.TILE_SIZE);
        g.setColor(sides.get(Dir.LEFT).getColor());
        g.fillRect(Util.TILE_SIZE * (pos.x * (Util.BOARD_SIZE + 3)),
                Util.TILE_SIZE * (1 + pos.y * (Util.BOARD_SIZE + 3)), Util.TILE_SIZE / 2,
                Util.BOARD_SIZE * Util.TILE_SIZE);

        // draw the checkered grid
        for (int row = 0; row < Util.BOARD_SIZE; row++) {
            for (int col = 0; col < Util.BOARD_SIZE; col++) {
                if ((row + col) % 2 == 1) {
                    g.setColor(new Color(60, 60, 60));
                } else {
                    g.setColor(new Color(50, 50, 50));
                }
                g.fillRect(
                        (col + 1) * Util.TILE_SIZE
                                + (pos.x) * (Util.BOARD_SIZE * Util.TILE_SIZE + 3 * Util.TILE_SIZE),
                        (row + 1) * Util.TILE_SIZE
                                + (pos.y) * (Util.BOARD_SIZE * Util.TILE_SIZE + 3 * Util.TILE_SIZE),
                        Util.TILE_SIZE,
                        Util.TILE_SIZE);
            }
        }
    }

    public Point getPos() {
        return pos;
    }
}
