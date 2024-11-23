package test.snake;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import source.logic.field.Field;
import source.logic.snake.Snake;
import source.logic.util.Vector;

public class SnakeTest {
    Snake snake;
    Field field;
    public void preTest1(){
        Field.newInstance(1, 4);
        field = Field.getInstance();
        snake = field.getPlayer();
    }

    @Test
    public void moveTest() {
        preTest1();
        snake.move();
        assertEquals(snake.getDir(), new Vector(0, 1));

        assertSame(snake.getFieldPos().getBoard(), field.getBoards()[0][0]);
        assertEquals(snake.getFieldPos().getPos(), new Vector(0, 1));
    }

    @Test
    public void killTest(){
        preTest1();
        assertFalse(snake.checkDeath());
        snake.kill();
        assertTrue(snake.checkDeath());
    }

    @Test
    public void growTest(){
        preTest1();
        assertEquals(snake.getSize(), 1);
        snake.grow();
        assertEquals(snake.getSize(), 2);
    }
}
