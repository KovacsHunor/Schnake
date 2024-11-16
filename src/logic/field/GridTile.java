package logic.field;

import gui.game.Field;
import java.util.ArrayList;
import java.util.List;
import logic.snake.Snake;

public class GridTile {
    private final List<GridObject> items = new ArrayList<>();

    public void put(GridObject item){
        items.addFirst(item);
    }

    public void remove(GridObject item){
        items.remove(item);
    }

    public void steppedOn(Field f, Snake s){
        List<GridObject> temp = new ArrayList<>();
        temp.addAll(items);
        for (GridObject gridObject : temp) {
            gridObject.steppedOn(f, s);
        }
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public void removeFirst(){
        if(!isEmpty()){
            items.removeFirst();
        }
    }

    public GridObject upper(){
        if(isEmpty()) return null;
        return items.getFirst();
    }
}
