package source.logic.fruit;

import java.awt.Color;

import source.logic.field.FieldPos;

public class NormalFruit extends Fruit {
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
