package logic.fruit;

import java.util.Random;
import logic.field.Board;
import logic.field.FieldPos;
import logic.field.Field;
import logic.field.GridObject;
import logic.snake.Snake;
import logic.util.Vector;

public abstract class Fruit extends GridObject {
    private static final Random rnd = new Random();

    private static FieldPos findEmpty() {
        int tileNum = Field.getInstance().getTileNum();

        for (Board[] boardRow : Field.getInstance().getBoards()) {
            for (Board board : boardRow) {
                for (int j = 0; j < tileNum; j++) {
                    for (int k = 0; k < tileNum; k++) {
                        Vector pos = new Vector(j, k);
                        if (board.getTile(pos).isEmpty()) {
                            return new FieldPos(board, pos);
                        }
                    }
                }
            }
        }
        return null;
    }

    protected static FieldPos newFruitPos() {
        Field field = Field.getInstance();
        Board board;
        Vector pos;
        int i = 0;
        do {
            board = field.getBoards()[rnd.nextInt(field.getBoards().length)][rnd.nextInt(field.getBoards().length)];
            pos = new Vector(rnd.nextInt(field.getTileNum()), rnd.nextInt(field.getTileNum()));
            i++;
        } while (!(board.getTile(pos).isEmpty() &&
                (field.getBoardNum() == 1 || board != field.getPlayer().getFieldPos().getBoard()))
                && i < 100);
        if (i < 100) {
            return new FieldPos(board, pos);
        }

        FieldPos fieldPos = findEmpty();
        if (fieldPos != null) {
            return fieldPos;
        }

        field.getPlayer().kill();
        return null;

    }

    public static void newFruit() {
        double ran = rnd.nextDouble();
        FieldPos fieldPos = newFruitPos();
        Fruit fruit;

        if (fieldPos == null) {
            return;
        }

        if (ran <= 0.15) {
            fruit = new ShuffleFruit(fieldPos);
        } else if (ran <= 0.3) {
            fruit = new TeleportFruit(fieldPos);

            FieldPos pairFieldPos = newFruitPos();
            if (pairFieldPos == null){
                return;
            }

            TeleportFruit pair = new TeleportFruit(pairFieldPos);
            pair.setPair((TeleportFruit) fruit);
            ((TeleportFruit) fruit).setPair(pair);

            pair.place();
        } else {
            fruit = new NormalFruit(fieldPos);
        }

        fruit.place();
    }

    protected Fruit(FieldPos fp) {
        super(fp);
    }

    public void eaten() {
        Snake s = Field.getInstance().getPlayer();
        s.grow();
        s.setPoint(s.getPoint() + getValue());
        newFruit();
    }

    @Override
    public void steppedOn() {
        withdraw();
        eaten();
    }

    protected abstract int getValue();
}
