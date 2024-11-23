package source.logic.field;

import java.awt.Color;

import source.logic.snake.Snake;

public abstract class GridObject {
    protected FieldPos fieldPos;

    public abstract void steppedOn(Snake player);

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
