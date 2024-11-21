package test.snake;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import logic.field.Field;
import logic.field.FieldPos;
import logic.fruit.NormalFruit;
import logic.fruit.TeleportFruit;
import logic.snake.Node;
import logic.snake.Snake;
import logic.util.Vector;

public class FruitTest {
    Snake snake;
    Field field;

    public void preTest1() {
        Field.newInstance(1, 4);
        field = Field.getInstance();
        snake = field.getPlayer();
    }

    @Test
    public void eatenByTest1() {
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
    public void eatenByTest2() {
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
