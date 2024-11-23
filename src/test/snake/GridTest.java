package test.snake;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.awt.Color;

import org.junit.Test;

import logic.field.Field;
import logic.field.FieldPos;
import logic.field.GridObject;
import logic.field.GridTile;
import logic.snake.Node;
import logic.snake.Snake;

public class GridTest {
    Snake snake;
    Field field;
    TestGridObject go;
    GridTile gt;

    public void preTest1() {
        Field.newInstance(1, 4);
        field = Field.getInstance();
        snake = field.getPlayer();
        go = new TestGridObject(snake.getFieldPos(), new Color(0x000000));
        gt = go.getFieldPos().getBoard().getTile(go.getFieldPos().getPos());
    }

    class TestGridObject extends GridObject {
        private boolean bool = false;
        private final Color color;

        public TestGridObject(FieldPos fp, Color c) {
            super(fp);
            color = c;
        }

        @Override
        public void steppedOn(Snake player) {
            bool = true;
        }

        @Override
        public Color getColor() {
            return color;
        }

        public boolean gotSteppedOn() {
            return bool;
        }

    }

    //tests place() and withdraw() as well, since it's logical to test them simultaniously
    @Test
    public void gridOperationTest() {
        preTest1();

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

    @Test
    public void steppedOnTest() {
        preTest1();
        assertFalse(go.gotSteppedOn());
        go.place();
        gt.steppedOn(snake);
        assertTrue(go.gotSteppedOn());
    }

    @Test
    public void upperTest() {
        preTest1();

        assertFalse(gt.isEmpty());
        assertNotSame(gt.upper(), go);
        go.place();
        assertSame(gt.upper(), go);
    }


}
