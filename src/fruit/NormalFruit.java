package fruit;

import java.awt.Color;

import snake.Board;
import snake.Snake;
import snake.Vector;

public class NormalFruit extends Fruit{
    public NormalFruit(Board b, Vector p, Color c){
        super(b, p, c);
    }

    public void eatenBy(Snake s){
        s.grow();
    }
}
