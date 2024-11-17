package logic.fruit;

import gui.game.Field;
import java.awt.*;
import java.util.Random;
import logic.field.Board;
import logic.field.GridObject;
import logic.snake.Snake;
import logic.util.Utils;
import logic.util.Vector;

public abstract class Fruit extends GridObject {
    private static Random rnd = new Random();

    public static void newFruit(Board[][] boards) {
        double ran = rnd.nextDouble();
        Fruit fruit;
        Board board;
        Vector pos;
        do {
            board = boards[rnd.nextInt(Utils.FIELD_SIZE)][rnd.nextInt(Utils.FIELD_SIZE)];
            pos = new Vector(rnd.nextInt(Utils.BOARD_SIZE), rnd.nextInt(Utils.BOARD_SIZE));
        } while (!board.getTile(pos).isEmpty());

        //0.7, 0.85, 1
        if (ran <= 0.7) {
            fruit = new NormalFruit(board, pos);
        } else if (ran <= 0.85) {
            TeleportFruit pair = new TeleportFruit(board, pos);
            board.putOnTile(pos, pair);

            do {
                board = boards[rnd.nextInt(Utils.FIELD_SIZE)][rnd.nextInt(Utils.FIELD_SIZE)];
                pos = new Vector(rnd.nextInt(Utils.BOARD_SIZE), rnd.nextInt(Utils.BOARD_SIZE));
            } while (!board.getTile(pos).isEmpty());

            fruit = new TeleportFruit(board, pos);
            ((TeleportFruit) fruit).setPair(pair);
            pair.setPair(fruit);

        } else {
            fruit = new ShuffleFruit(board, pos);
        }

        board.putOnTile(pos, fruit);
    }

    protected Fruit(Board b, Vector p) {
        board = b;
        pos = p;
    }

    public void eaten(Field f, Snake s) {
        s.grow();
        s.setPoint(s.getPoint() + getValue());
        newFruit(f.getBoards());
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
