package test.snake;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.awt.Color;

import org.junit.Test;

import source.logic.field.Board;
import source.logic.field.Field;
import source.logic.field.FieldPos;
import source.logic.field.Side;
import source.logic.fruit.NormalFruit;
import source.logic.snake.Snake;
import source.logic.util.Dir;

public class SideTest {
    Snake snake;
    Field field;

    public void preTest1() {
        Field.newInstance(1, 4);
        field = Field.getInstance();
        snake = field.getPlayer();
    }

    @Test
    public void connectTest() {
        preTest1();
        Board board = field.getBoards()[0][0];
        
        Side s1 = new Side(new Color(0,0,0), board, Dir.DOWN);
        Side s2 = new Side(new Color(0,0,1), board, Dir.RIGHT);

        assertNotEquals(s1.getColor(), s2.getColor());
        assertEquals(s1.isDefaultOriented(), s2.isDefaultOriented());
        assertNull(s1.getPair());
        assertNull(s2.getPair());

        Side.connect(s1, s2, new Color(0xFFFFFF));
        assertEquals(s1.getColor(), s2.getColor());
        assertTrue(s1.isDefaultOriented() == !s2.isDefaultOriented());
        assertEquals(s1, s2.getPair());
        assertEquals(s2, s1.getPair());

    }
}
