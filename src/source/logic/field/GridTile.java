package source.logic.field;

import java.util.ArrayList;
import java.util.List;

import source.logic.snake.Snake;

/**
 * the tiles on the grid
 */
public class GridTile {
    private final List<GridObject> items = new ArrayList<>();

    /**
     * puts an item on the tile
     * @param item  the item
     */
    public void put(GridObject item){
        items.addFirst(item);
    }

    /**
     * removes an item from the tile
     * @param item  the item to be removed
     */
    public void remove(GridObject item){
        items.remove(item);
    }

    /**
     * tells the objects on it that they have been stepped on by a player
     * @param player    the player
     */
    public void steppedOn(Snake player){
        List<GridObject> temp = new ArrayList<>();
        temp.addAll(items);
        for (GridObject gridObject : temp) {
            gridObject.steppedOn(player);
        }
    }

    /**
     * tells if the tile is empty
     * @return  true if there are no objects on this tile
     */
    public boolean isEmpty(){
        return items.isEmpty();
    }

    /**
     * returns the uppermost object on this tile
     * @return
     */
    public GridObject upper(){
        if(isEmpty()) return null;
        return items.getFirst();
    }
}
