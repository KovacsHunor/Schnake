package source.logic.fruit;

import java.util.Random;

import source.logic.field.Board;
import source.logic.field.Field;
import source.logic.field.FieldPos;
import source.logic.field.GridObject;
import source.logic.snake.Snake;
import source.logic.util.Vector;

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

        if (ran <= 0.33) {
            fruit = new ShuffleFruit(fieldPos);
            fruit.place();
        } else if (ran <= 0.66) {
            fruit = new TeleportFruit(fieldPos);
            fruit.place();
            
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
            fruit.place();
        }

    }

    protected Fruit(FieldPos fp) {
        super(fp);
    }

    public void eatenBy(Snake player) {
        player.grow();
        player.setPoint(player.getPoint() + getValue());
        newFruit();
    }

    @Override
    public void steppedOn(Snake player) {
        withdraw();
        eatenBy(player);
    }

    protected abstract int getValue();
}
