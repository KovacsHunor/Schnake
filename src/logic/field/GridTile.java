package logic.field;

import java.util.ArrayList;
import java.util.List;

public class GridTile {
    private final List<GridObject> items = new ArrayList<>();

    public void put(GridObject item){
        items.addFirst(item);
    }

    public void remove(GridObject item){
        items.remove(item);
    }

    public void steppedOn(){
        List<GridObject> temp = new ArrayList<>();
        temp.addAll(items);
        for (GridObject gridObject : temp) {
            gridObject.steppedOn();
        }
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public GridObject upper(){
        if(isEmpty()) return null;
        return items.getFirst();
    }
}
