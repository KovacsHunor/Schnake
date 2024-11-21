package test.snake;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import logic.field.Field;
import logic.snake.Node;
import logic.snake.Snake;
import logic.util.Vector;

public class NodeTest {
    Snake snake;
    Field field;
    public void preTest1(){
        Field.newInstance(1, 4);
        field = Field.getInstance();
        snake = field.getPlayer();
    }

    @Test
    public void stepTest() {
        preTest1();
        Node node = new Node(snake.getNodes().get(0));

        assertFalse(snake.checkDeath());
        node.steppedOn();
        assertTrue(snake.checkDeath());
    }
}
