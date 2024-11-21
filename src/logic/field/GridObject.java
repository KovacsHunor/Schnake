package logic.field;

import java.awt.Color;
import logic.snake.Snake;
import logic.util.Vector;

public class GridObject {
    protected Board board;
    protected Vector pos;

    public void steppedOn(){
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
