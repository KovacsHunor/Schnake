package test.snake;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import source.logic.field.Field;
import source.logic.field.FieldPos;
import source.logic.fruit.NormalFruit;
import source.logic.fruit.TeleportFruit;
import source.logic.snake.Node;
import source.logic.snake.Snake;
import source.logic.util.Vector;

public class FruitTest {
    Snake snake;
    Field field;

    public void preTest1() {
        Field.newInstance(1, 4);
        field = Field.getInstance();
        snake = field.getPlayer();
    }

    @Test
    public void normalFruitTest() {
        preTest1();
        NormalFruit fruit = new NormalFruit(new FieldPos(snake.getFieldPos().getBoard(),
                snake.getFieldPos().getPos().sum(snake.getDir())));
        fruit.place();

        assertEquals(snake.getSize(), 1);
        assertEquals(snake.getPoint(), 0);
        fruit.eatenBy(snake);
        assertEquals(snake.getSize(), 2);
        assertEquals(snake.getPoint(), fruit.getValue());
    }

    @Test
    public void teleportFruitTest() {
        preTest1();
        TeleportFruit fruit = new TeleportFruit(new FieldPos(snake.getFieldPos().getBoard(),
                snake.getFieldPos().getPos()));
        fruit.place();

        TeleportFruit pair = new TeleportFruit(new FieldPos(snake.getFieldPos().getBoard(),
        new Vector(3,1)));
        pair.place();
        pair.setPair(fruit);
        fruit.setPair(pair);

        assertEquals(snake.getSize(), 1);
        assertEquals(snake.getFieldPos().getPos(), fruit.getFieldPos().getPos());
        fruit.eatenBy(snake);
        assertEquals(snake.getSize(), 2);
        assertEquals(snake.getFieldPos().getPos(), pair.getFieldPos().getPos());
    }
}
