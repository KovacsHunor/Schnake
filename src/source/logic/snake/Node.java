package source.logic.snake;

import java.awt.Color;

import source.logic.field.FieldPos;
import source.logic.field.GridObject;

public class Node extends GridObject{
    private final Color color;
    public Node(FieldPos fp, Color c){
        super(fp);
        color = c;
    }

    public Node(Node n){
        super(n.fieldPos);
        color = n.color;
    }

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
