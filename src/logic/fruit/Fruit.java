package logic.fruit;

import gui.game.Field;
import java.awt.*;
import logic.field.Board;
import logic.field.GridObject;
import logic.snake.Snake;
import logic.util.Utils;
import logic.util.Vector;

public abstract class Fruit extends GridObject{

    protected Fruit(Board b, Vector p) {
        board = b;        
        pos = p;
    }

    public void eaten(Field f, Snake s){
        s.grow();
        s.setPoint(s.getPoint() + getValue());
        f.newFruit();
    }

    @Override
    public void steppedOn(Field f, Snake s) {
        destroy();
        eaten(f, s);
    }

    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(
                (pos.x + 1 + (board.getPos().x) * (Utils.BOARD_SIZE + 3))
                        * Utils.TILE_SIZE,
                (pos.y + 1 + (board.getPos().y) * (Utils.BOARD_SIZE + 3))
                        * Utils.TILE_SIZE,
                Utils.TILE_SIZE,
                Utils.TILE_SIZE);
    }

    protected abstract int getValue();
}
