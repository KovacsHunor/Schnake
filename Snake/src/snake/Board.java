package snake;

import java.awt.*;
import java.util.EnumMap;



public class Board {
    private Point pos;
    private EnumMap<Dir, Side> sides;

    public Side getSide(Dir d){
        return sides.get(d);
    }

    public Board(Point pos) {
        sides = new EnumMap<>(Dir.class); 

        sides.put(Dir.UP, new Side(new Color(0, 0, 0), this, Dir.UP));
        sides.put(Dir.DOWN, new Side(new Color(0, 0, 0), this, Dir.DOWN));
        sides.put(Dir.RIGHT, new Side(new Color(0, 0, 0), this, Dir.RIGHT));
        sides.put(Dir.LEFT, new Side(new Color(0, 0, 0), this, Dir.LEFT));
        this.pos = pos;
    }

    public void draw(Graphics g) {
        g.setColor(sides.get(Dir.UP).getColor());
        g.fillRect(
                Field.TILE_SIZE * (1 + pos.x * (Field.BOARD_SIZE + 3)),
                Field.TILE_SIZE * (pos.y * (Field.BOARD_SIZE + 3)), Field.BOARD_SIZE * Field.TILE_SIZE,
                Field.TILE_SIZE/2);
        g.setColor(sides.get(Dir.DOWN).getColor());
        g.fillRect(
                Field.TILE_SIZE * (1 + pos.x * (Field.BOARD_SIZE + 3)),
                Field.TILE_SIZE * (pos.y * (Field.BOARD_SIZE + 3) + Field.BOARD_SIZE + 1) + Field.TILE_SIZE/2,
                Field.BOARD_SIZE * Field.TILE_SIZE,
                Field.TILE_SIZE/2);

        g.setColor(sides.get(Dir.RIGHT).getColor());
        g.fillRect(
            Field.TILE_SIZE * (pos.x * (Field.BOARD_SIZE + 3) + Field.BOARD_SIZE + 1) + Field.TILE_SIZE/2,
            Field.TILE_SIZE * (1 + pos.y * (Field.BOARD_SIZE + 3)), Field.TILE_SIZE/2,
            Field.BOARD_SIZE * Field.TILE_SIZE);
        g.setColor(sides.get(Dir.LEFT).getColor());
        g.fillRect(Field.TILE_SIZE * (pos.x * (Field.BOARD_SIZE + 3)),
                Field.TILE_SIZE * (1 + pos.y * (Field.BOARD_SIZE + 3)), Field.TILE_SIZE/2,
                Field.BOARD_SIZE * Field.TILE_SIZE);

        for (int row = 0; row < Field.BOARD_SIZE; row++) {
            for (int col = 0; col < Field.BOARD_SIZE; col++) {
                if ((row + col) % 2 == 1) {
                    g.setColor(new Color(60, 60, 60));
                } else {
                    g.setColor(new Color(50, 50, 50));
                }
                g.fillRect(
                        (col + 1) * Field.TILE_SIZE
                                + (pos.x) * (Field.BOARD_SIZE * Field.TILE_SIZE + 3 * Field.TILE_SIZE),
                        (row + 1) * Field.TILE_SIZE
                                + (pos.y) * (Field.BOARD_SIZE * Field.TILE_SIZE + 3 * Field.TILE_SIZE),
                        Field.TILE_SIZE,
                        Field.TILE_SIZE);
            }
        }
    }

    Point getPos(){
        return pos;
    }
}
