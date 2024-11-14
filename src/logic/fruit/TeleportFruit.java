package logic.fruit;

import gui.game.Field;
import java.awt.Color;
import java.awt.Graphics;
import logic.field.Board;
import logic.snake.Snake;
import logic.util.Util;
import logic.util.Vector;

public class TeleportFruit extends Fruit {

    private Fruit pair;

    public TeleportFruit(Board b, Vector p) {
        super(b, p);
    }

    @Override
    public void eaten(Field f, Snake s) {
        super.eaten(f, s);
        s.setBoard(pair.getBoard());
        s.setPos(pair.getPos());
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.fillRect(
                (pair.getPos().x + 1 + (pair.getBoard().getPos().x) * (Util.BOARD_SIZE + 3))
                * Util.TILE_SIZE,
                (pair.getPos().y + 1 + (pair.getBoard().getPos().y) * (Util.BOARD_SIZE + 3))
                * Util.TILE_SIZE,
                Util.TILE_SIZE,
                Util.TILE_SIZE);
    }

    public void setPair(Fruit pair) {
        this.pair = pair;
    }

    @Override
    public Color getColor() {
        return new Color(0xFFF455);
    }

    @Override
    protected int getValue() {
        return 2;
    }
}
