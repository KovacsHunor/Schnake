package fruit;

import java.awt.Color;
import logic.field.Board;
import logic.snake.Snake;
import logic.util.Vector;

public class NormalFruit extends Fruit{
    public NormalFruit(Board b, Vector p, Color c){
        super(b, p, c);
    }

    @Override
    public void eatenBy(Snake s){
        s.grow();
    }
}
