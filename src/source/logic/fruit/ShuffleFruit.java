package source.logic.fruit;

import java.awt.Color;

import source.logic.field.Field;
import source.logic.field.FieldPos;
import source.logic.snake.Snake;

/**
 * a Fruit that if eaten, reshuffles the sides
 */
public class ShuffleFruit extends Fruit {
    /**
     * the constructor
     * @param fp
     */
    public ShuffleFruit(FieldPos fp) {
        super(fp);
    }

    @Override
    public void eatenBy(Snake player) {
        Field.getInstance().shuffleSides();
        super.eatenBy(player);
    }

    @Override
    public Color getColor() {
        return new Color(0xFFFFFF);
    }

    @Override
    public int getValue() {
        return 2;
    }
}
