package snake;

import java.awt.*;
import java.util.EnumMap;

enum Dir {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class Board {
    private Side upSide;
    private Side downSide;
    private Side leftSide;
    private Side rigthSide;
    private Point pos;

    public Board(Point pos) {
        upSide = new Side(new Color(0, 0, 0));
        downSide = new Side(new Color(0, 0, 0));
        leftSide = new Side(new Color(0, 0, 0));
        rigthSide = new Side(new Color(0, 0, 0));
        this.pos = pos;
    }

    public void draw(Graphics g) {
        g.setColor(upSide.getColor());
        g.fillRect(
                Field.TILE_SIZE * (1 + pos.x * (Field.BOARD_SIZE + 3)),
                Field.TILE_SIZE * (pos.y * (Field.BOARD_SIZE + 3)), Field.BOARD_SIZE * Field.TILE_SIZE,
                Field.TILE_SIZE);
        g.setColor(downSide.getColor());
        g.fillRect(
                Field.TILE_SIZE * (1 + pos.x * (Field.BOARD_SIZE + 3)),
                Field.TILE_SIZE * (pos.y * (Field.BOARD_SIZE + 3) + Field.BOARD_SIZE + 1),
                Field.BOARD_SIZE * Field.TILE_SIZE,
                Field.TILE_SIZE);

        g.setColor(rigthSide.getColor());
        g.fillRect(
            Field.TILE_SIZE * (pos.x * (Field.BOARD_SIZE + 3) + Field.BOARD_SIZE + 1),
            Field.TILE_SIZE * (1 + pos.y * (Field.BOARD_SIZE + 3)), Field.TILE_SIZE,
            Field.BOARD_SIZE * Field.TILE_SIZE);
        g.setColor(leftSide.getColor());
        g.fillRect(Field.TILE_SIZE * (pos.x * (Field.BOARD_SIZE + 3)),
                Field.TILE_SIZE * (1 + pos.y * (Field.BOARD_SIZE + 3)), Field.TILE_SIZE,
                Field.BOARD_SIZE * Field.TILE_SIZE);

        for (int row = 0; row < Field.BOARD_SIZE; row++) {
            for (int col = 0; col < Field.BOARD_SIZE; col++) {
                if ((row + col) % 2 == 1) {
                    g.setColor(new Color(60, 60, 60));
                } else {
                    g.setColor(new Color(50, 50, 50));
                }
                g.fillRect(
                        (col + 1) * Field.TILE_SIZE
                                + (pos.x) * (Field.BOARD_SIZE * Field.TILE_SIZE + 3 * Field.TILE_SIZE),
                        (row + 1) * Field.TILE_SIZE
                                + (pos.y) * (Field.BOARD_SIZE * Field.TILE_SIZE + 3 * Field.TILE_SIZE),
                        Field.TILE_SIZE,
                        Field.TILE_SIZE);
            }
        }
    }
}
