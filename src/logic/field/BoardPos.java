package logic.field;

import logic.util.Vector;

public class BoardPos {
    private final Board board;
    private final Vector pos;

    public BoardPos(Board b, Vector v){
        board = b;
        pos = v;
    }

    public Board getBoard(){
        return board;
    }

    public Vector getPos(){
        return pos;
    }
}
