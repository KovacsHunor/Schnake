package logic.fruit;

import java.awt.Color;
import logic.field.Board;
import logic.util.Vector;

public class NormalFruit extends Fruit{
    public NormalFruit(Board b, Vector p){
        super(b, p);
    }

    @Override
    public Color getColor(){
        return new Color(0x219C90);
    }

    @Override
    protected int getValue(){
        return 1;
    }
}
