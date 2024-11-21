package logic.field;

import java.awt.Color;

public class GridObject {
    protected FieldPos fieldPos;

    public void steppedOn(){
        //emtpy by default
    }

    public GridObject(FieldPos fp){
        fieldPos = new FieldPos(fp);
    }

    public void withdraw() {
        fieldPos.getBoard().getTile(fieldPos.getPos()).remove(this);
    }

    public void place(){
        fieldPos.getBoard().getTile(fieldPos.getPos()).put(this);
    }

    public Color getColor(){
        return null;
    }

    public FieldPos getFieldPos(){
        return fieldPos;
    }
}
