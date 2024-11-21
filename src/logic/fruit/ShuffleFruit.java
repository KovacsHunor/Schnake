package logic.fruit;

import java.awt.Color;
import logic.field.Board;
import logic.field.Field;
import logic.util.Vector;

public class ShuffleFruit extends Fruit{
    public ShuffleFruit(Board b, Vector p){
        super(b, p);
    }

    @Override
    public void eaten(){
        Field.getInstance().shuffleSides();
        super.eaten();
    }

    @Override
    public Color getColor(){
        return new Color(0xFFFFFF);
    }

    @Override
    protected int getValue(){
        return 5;
    }
}
