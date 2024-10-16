package snake;

import java.awt.*;

public class Board {
    private Side[] sides;
    private Point pos;

    public Board(Point pos) {
        this.pos = pos;
    }

    public void draw(Graphics g) {
        for (int row = 0; row < Field.BOARD_SIZE; row++) {
            for (int col = 0; col < Field.BOARD_SIZE; col++) {
                if ((row + col) % 2 == 1) {
                    g.setColor(new Color(60, 60, 60));
                } else {
                    g.setColor(new Color(50, 50, 50));
                }
                g.fillRect((col+Field.OFFSET) * Field.TILE_SIZE + (pos.x) * (Field.BOARD_SIZE * Field.TILE_SIZE + 3*Field.TILE_SIZE),
                        (row+Field.OFFSET) * Field.TILE_SIZE + (pos.y) * (Field.BOARD_SIZE * Field.TILE_SIZE + 3*Field.TILE_SIZE), Field.TILE_SIZE,
                        Field.TILE_SIZE);
            }
        }
    }
}
