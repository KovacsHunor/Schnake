package logic.snake;

import java.awt.Color;
import logic.field.Field;
import logic.field.FieldPos;
import logic.field.GridObject;

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
    public void steppedOn() {
        Snake s = Field.getInstance().getPlayer();
        if(!this.equals(s.getNodes().get(0))){
            s.kill();
        }
    }

    @Override
    public Color getColor(){
        return color;
    }
    
}
