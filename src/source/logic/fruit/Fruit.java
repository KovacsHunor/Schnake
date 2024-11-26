package source.logic.fruit;

import java.util.Random;

import source.logic.field.Board;
import source.logic.field.Field;
import source.logic.field.FieldPos;
import source.logic.field.GridObject;
import source.logic.snake.Snake;
import source.logic.util.Vector;

/**
 * Abstract class for fruits
 */
public abstract class Fruit extends GridObject {
    private static final Random rnd = new Random();

    /**
     * returns an empty tile in the field
     * @return  an empty tile, null if there are none
     */
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

    /**
     * returns a new position for a fruit to be placed
     * @return  the position for a fruit, null if there are no more empty tiles
     */
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
                (field.getBoardNum() == 1 || board != field.getSnake().getFieldPos().getBoard()))
                && !pos.equals(field.getSnake().getFieldPos().getPos().sum(field.getSnake().getDir()))
                && i < 100);
        if (i < 100) {
            return new FieldPos(board, pos);
        }

        FieldPos fieldPos = findEmpty();
        if (fieldPos != null) {
            return fieldPos;
        }

        field.getSnake().kill();
        return null;

    }

    /**
     * places one or more (e.g.: teleportFruit) fruits on the field
     */
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
            if (pairFieldPos == null) {
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

    /**
     * the Constructor
     * @param fp    the position of the fruit
     */
    protected Fruit(FieldPos fp) {
        super(fp);
    }

    /**
     * determines what will happen when a player eats it
     * @param player    the player
     */
    public void eatenBy(Snake player) {
        player.grow();
        player.setPoint(player.getPoint() + getValue());
        newFruit();
    }

    /**
     * determines what will happen when a player stepps on it
     */
    @Override
    public void steppedOn(Snake player) {
        withdraw();
        eatenBy(player);
    }

    /**
     * determines how much points will the player gain if they eat this fruit
     * @return  the value of the fruit
     */
    protected abstract int getValue();
}
