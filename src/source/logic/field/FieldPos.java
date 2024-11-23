package source.logic.field;

import source.logic.util.Vector;

public class FieldPos {
    private final Board board;
    private final Vector pos;

    public FieldPos(Board b, Vector v){
        board = b;
        pos = new Vector(v);
    }

    public FieldPos(FieldPos fp){
        board = fp.board;
        pos = new Vector(fp.pos);
    }

    public Board getBoard(){
        return board;
    }

    public Vector getPos(){
        return pos;
    }
}
