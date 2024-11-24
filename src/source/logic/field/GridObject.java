package source.logic.field;

import java.awt.Color;

import source.logic.snake.Snake;

/**
 * The objects that are put on the tiles of the grid of a board
 */
public abstract class GridObject {
    protected FieldPos fieldPos;

    /**
     * determines what to do when a player steps on it
     * @param player    the player
     */
    public abstract void steppedOn(Snake player);

    /**
     * the constructor
     * @param fp    the position of the object
     */
    protected GridObject(FieldPos fp){
        fieldPos = new FieldPos(fp);
    }

    /**
     * remove the object from the tile it is on
     */
    public void withdraw() {
        fieldPos.getBoard().getTile(fieldPos.getPos()).remove(this);
    }

    /**
     * place the object on the tile its position determines
     */
    public void place(){
        fieldPos.getBoard().getTile(fieldPos.getPos()).put(this);
    }

    /**
     * returns the color of the object
     * @return the color
     */
    public abstract Color getColor();

    /**
     * returns the position of the object
     * @return  the position
     */
    public FieldPos getFieldPos(){
        return fieldPos;
    }
}
