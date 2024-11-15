package logic.util;

import java.awt.Font;
import java.util.*;

public class Util {
    public static final int TICK = 10;
    public static final int SPEED = 5;

    public static final int FIELD_SIZE = 2;
    public static final int BOARD_SIZE = 8;
    public static final int TILE_SIZE = 1000/(Math.max(FIELD_SIZE, 2)*(BOARD_SIZE+2));

    public static final Font BUTTON_FONT = new Font("Serif", Font.PLAIN, 32);

    private static final Map<Vector, Dir> vectorDir = new HashMap<>();
    private static final Map<Dir, Vector> dirVector = new EnumMap<>(Dir.class);

    private Util(){}

    static{
        vectorDir.put(new Vector(0, -1), Dir.UP);
        vectorDir.put(new Vector(0, 1), Dir.DOWN);
        vectorDir.put(new Vector(1, 0), Dir.RIGHT);
        vectorDir.put(new Vector(-1, 0), Dir.LEFT);

        dirVector.put(Dir.UP, new Vector(0, -1));
        dirVector.put(Dir.DOWN, new Vector(0, 1));
        dirVector.put(Dir.RIGHT, new Vector(1, 0));
        dirVector.put(Dir.LEFT, new Vector(-1, 0));
    }
    
    public static Vector getVector(Dir d){
        return new Vector(dirVector.get(d));
    }

    public static Dir getDir(Vector p){
        return vectorDir.get(p);
    }
}