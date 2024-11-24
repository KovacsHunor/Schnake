package source.logic.fruit;

import java.awt.Color;

import source.logic.field.FieldPos;

/**
 * the standard snake fruit
 */
public class NormalFruit extends Fruit {
    /**
     * the constructor
     * @param fp    the position of the fruit
     */
    public NormalFruit(FieldPos fp) {
        super(fp);
    }

    @Override
    public Color getColor() {
        return new Color(0x219C90);
    }

    @Override
    public int getValue() {
        return 1;
    }
}
