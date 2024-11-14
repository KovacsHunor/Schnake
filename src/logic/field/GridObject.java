package logic.field;

import gui.game.Field;
import java.awt.Color;
import logic.snake.Snake;

public class GridObject {
    public void steppedOn(Field f, Snake s){
        //emtpy by default
    }

    public Color getColor(){
        return new Color(0x000000);
    }
}
