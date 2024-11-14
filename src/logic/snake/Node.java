package logic.snake;

import gui.game.Field;
import java.awt.Color;
import logic.field.Board;
import logic.field.GridObject;
import logic.util.Vector;

public class Node extends GridObject{
    private Vector pos;
    private Board board;
    private final Color color;
    public Node(Board b, Vector p, Color c){
        board = b;
        pos = p;
        color = c;
    }

    public Node(Node n){
        board = n.board;
        pos = n.pos;
        color = n.color;
    }

    public Vector getPos(){
        return pos;
    }

    public void setPos(Vector p){
        pos = new Vector(p);
    }

    public Board getBoard(){
        return board;
    }

    public void setBoard(Board b) {
        board = b;
    }

    @Override
    public void steppedOn(Field f, Snake s) {
        if(!this.equals(s.getNodes().get(0))){
            s.kill();
        }
    }

    @Override
    public Color getColor(){
        return color;
    }
    
}
