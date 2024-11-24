package source.logic.field;

import source.logic.util.Vector;

/**
 * Determines a unique position on a field
 */
public class FieldPos {
    private final Board board;
    private final Vector pos;

    /**
     * the Constructor
     * @param board the Board
     * @param pos the position on the board
     */
    public FieldPos(Board board, Vector pos){
        this.board = board;
        this.pos = new Vector(pos);
    }

    /**
     * copy constructor
     * @param fp    the FieldPos to copy
     */
    public FieldPos(FieldPos fp){
        board = fp.board;
        pos = new Vector(fp.pos);
    }

    /**
     * returns the board
     * @return  the board
     */
    public Board getBoard(){
        return board;
    }

    /**
     * returns the postions on the board
     * @return the position
     */
    public Vector getPos(){
        return pos;
    }
}
