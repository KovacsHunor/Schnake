package logic.fruit;

import gui.game.Field;
import java.awt.*;
import logic.field.Board;
import logic.field.GridObject;
import logic.snake.Snake;
import logic.util.Util;
import logic.util.Vector;

public abstract class Fruit extends GridObject{
    protected Board board;
    protected Vector pos;

    protected Fruit(Board b, Vector p) {
        board = b;        
        pos = p;
    }

    public void eaten(Field f, Snake s){
        s.setPoint(s.getPoint() + getValue());
        board.setGrid(pos, null);
        f.newFruit();
    }

    @Override
    public void steppedOn(Field f, Snake s) {
        eaten(f, s);
    }

    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(
                (pos.x + 1 + (board.getPos().x) * (Util.BOARD_SIZE + 3))
                        * Util.TILE_SIZE,
                (pos.y + 1 + (board.getPos().y) * (Util.BOARD_SIZE + 3))
                        * Util.TILE_SIZE,
                Util.TILE_SIZE,
                Util.TILE_SIZE);
    }

    protected abstract int getValue();

    public Vector getPos() {
        return pos;
    }

    public Board getBoard() {
        return board;
    }
}
