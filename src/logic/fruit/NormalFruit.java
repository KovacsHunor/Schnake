package logic.fruit;

import gui.game.Field;
import java.awt.Color;
import logic.field.Board;
import logic.snake.Snake;
import logic.util.Vector;

public class NormalFruit extends Fruit{
    public NormalFruit(Board b, Vector p){
        super(b, p);
    }

    @Override
    public void eaten(Field f, Snake s){
        super.eaten(f, s);
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
