package logic.fruit;

import gui.game.Field;
import java.awt.Color;
import java.awt.Graphics;
import logic.field.Board;
import logic.snake.Snake;
import logic.util.Vector;

public class TeleportFruit extends Fruit{

    private Fruit pair;

    public TeleportFruit(Board b, Vector p) {
        super(b, p);
    }

    @Override
    public void eaten(Field f, Snake s) {
        s.setBoard(pair.getBoard());
        s.setPos(pair.getPos());
        super.eaten(f, s);
    }

    @Override
    public void destroy() {
        getBoard().getTile(getPos()).remove(this);
        pair.getBoard().getTile(pair.getPos()).remove(pair);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.fillRect(
                (pair.getPos().x + 1 + (pair.getBoard().getPos().x) * (board.getTileNum() + 3))
                * board.getTileSize(),
                (pair.getPos().y + 1 + (pair.getBoard().getPos().y) * (board.getTileNum() + 3))
                * board.getTileSize(),
                board.getTileSize(),
                board.getTileSize());
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
