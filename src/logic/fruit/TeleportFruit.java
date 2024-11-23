package logic.fruit;

import java.awt.Color;
import logic.field.FieldPos;
import logic.snake.Snake;

public class TeleportFruit extends Fruit{

    private TeleportFruit pair;

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
