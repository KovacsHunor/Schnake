package logic.fruit;

import java.awt.Color;
import logic.field.Field;
import logic.field.FieldPos;

public class ShuffleFruit extends Fruit {
    public ShuffleFruit(FieldPos fp) {
        super(fp);
    }

    @Override
    public void eaten() {
        Field.getInstance().shuffleSides();
        super.eaten();
    }

    @Override
    public Color getColor() {
        return new Color(0xFFFFFF);
    }

    @Override
    protected int getValue() {
        return 5;
    }
}
