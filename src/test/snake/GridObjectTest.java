package test.snake;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;

import logic.field.Field;
import logic.field.GridObject;
import logic.field.GridTile;
import logic.fruit.NormalFruit;
import logic.snake.Node;
import logic.snake.Snake;
import logic.util.Vector;

public class GridObjectTest {
    Snake snake;
    Field field;
    public void preTest1(){
        Field.newInstance(1, 4);
        field = Field.getInstance();
        snake = field.getPlayer();
    }

    @Test
    public void gridOperationTest() {
        preTest1();
        GridObject go = new Node(snake.getNodes().getFirst());
        GridTile gt = snake.getFieldPos().getBoard().getTile(snake.getFieldPos().getPos());
        
        //the head of the snake is on it already
        assertFalse(gt.isEmpty());
       
        go.place();

        //there are now two items on it
        assertFalse(gt.isEmpty());
        
        snake.getNodes().getFirst().withdraw();
        
        //down to one
        assertFalse(gt.isEmpty());
        
        go.withdraw();
        
        //it is empty
        assertTrue(gt.isEmpty());
    }
}
