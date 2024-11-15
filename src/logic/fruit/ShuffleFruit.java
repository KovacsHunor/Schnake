package logic.fruit;

import gui.game.Field;
import java.awt.Color;
import logic.field.Board;
import logic.snake.Snake;
import logic.util.Vector;

public class ShuffleFruit extends Fruit{
    public ShuffleFruit(Board b, Vector p){
        super(b, p);
    }

    @Override
    public void eaten(Field f, Snake s){
        f.shuffleSides();
        super.eaten(f, s);
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
