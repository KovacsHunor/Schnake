package fruit;

import java.awt.*;
import logic.field.Board;
import logic.snake.Snake;
import logic.util.Util;
import logic.util.Vector;

public abstract class Fruit {
    protected Color color;
    protected Board board;
    protected Vector pos;

    protected Fruit(Board b, Vector p, Color c) {
        color = c;
        board = b;
        pos = p;
    }

    public abstract void eatenBy(Snake s);

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(
                (pos.x + 1 + (board.getPos().x) * (Util.BOARD_SIZE + 3))
                        * Util.TILE_SIZE,
                (pos.y + 1 + (board.getPos().y) * (Util.BOARD_SIZE + 3))
                        * Util.TILE_SIZE,
                Util.TILE_SIZE,
                Util.TILE_SIZE);
    }

    public Vector getPos() {
        return pos;
    }

    public Board getBoard() {
        return board;
    }
}
