package source.logic.fruit;

import java.awt.Color;

import source.logic.field.FieldPos;
import source.logic.snake.Snake;

/**
 * a Fruit that appears in pairs. If eaten, the player gets teleported to the other fruit
 */
public class TeleportFruit extends Fruit{

    private TeleportFruit pair;

    /**
     * the constructor
     * @param fp    the position of the Fruit
     */
    public TeleportFruit(FieldPos fp) {
        super(fp);
    }

    @Override
    public void eatenBy(Snake player) {
        player.setFieldPos(pair.getFieldPos());
        super.eatenBy(player);
    }

    @Override
    public void withdraw() {
        fieldPos.getBoard().getTile(fieldPos.getPos()).remove(this);
        pair.getFieldPos().getBoard().getTile(pair.getFieldPos().getPos()).remove(pair);
    }

    /**
     * sets the pair of this fruit
     * @param pair  the pair
     */
    public void setPair(TeleportFruit pair) {
        this.pair = pair;
    }

    @Override
    public Color getColor() {
        return new Color(0xFFF455);
    }

    @Override
    public int getValue() {
        return 3;
    }
}
