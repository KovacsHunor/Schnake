package logic.field;

import java.awt.Color;

public abstract class GridObject {
    protected FieldPos fieldPos;

    public abstract void steppedOn();

    protected GridObject(FieldPos fp){
        fieldPos = new FieldPos(fp);
    }

    public void withdraw() {
        fieldPos.getBoard().getTile(fieldPos.getPos()).remove(this);
    }

    public void place(){
        fieldPos.getBoard().getTile(fieldPos.getPos()).put(this);
    }

    public abstract Color getColor();

    public FieldPos getFieldPos(){
        return fieldPos;
    }
}
