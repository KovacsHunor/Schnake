package source.logic.snake;

import java.awt.Color;

import source.logic.field.FieldPos;
import source.logic.field.GridObject;

/**
 * a node of a snake
 */
public class Node extends GridObject{
    private final Color color;

    /**
     * the constructor
     * @param fp position in the field
     * @param c the color of the node
     */
    public Node(FieldPos fp, Color c){
        super(fp);
        color = c;
    }

    /**
     * copy constructor
     * @param n the node to copy
     */
    public Node(Node n){
        super(n.fieldPos);
        color = n.color;
    }

    /**
     * sets the position of the node
     * @param fp    the new position
     */
    public void setFieldPos(FieldPos fp){
        fieldPos = new FieldPos(fp);
    }

    @Override
    public void steppedOn(Snake player) {
        if(!this.equals(player.getNodes().get(0))){
            player.kill();
        }
    }

    @Override
    public Color getColor(){
        return color;
    }
    
}
