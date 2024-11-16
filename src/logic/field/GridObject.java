package logic.field;

import gui.game.Field;
import java.awt.Color;
import logic.snake.Snake;
import logic.util.Vector;

public class GridObject {
    protected Board board;
    protected Vector pos;

    public void steppedOn(Field f, Snake s){
        //emtpy by default
    }

    public void destroy() {
        getBoard().getTile(getPos()).remove(this);
    }

    public Color getColor(){
        return new Color(0x000000);
    }

    public Board getBoard(){
        return board;
    }

    public Vector getPos(){
        return new Vector(pos);
    }
}
