package logic.field;

import java.util.ArrayList;
import java.util.List;

public class GridTile {
    private final List<GridObject> items = new ArrayList<>();

    public void add(GridObject item){
        if(!isEmpty()){
            items.removeFirst();
        }
        items.addFirst(item);
    }

    public void remove(GridObject item){
        items.remove(item);
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
