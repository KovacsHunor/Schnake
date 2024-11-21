package logic.fruit;

import java.awt.Color;
import logic.field.Field;
import logic.field.FieldPos;
import logic.snake.Snake;

public class ShuffleFruit extends Fruit {
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
