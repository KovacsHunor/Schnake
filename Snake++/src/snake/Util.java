package snake;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

enum Dir {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class Util {
    public static final int TICK = 10;
    public static final int SPEED = 5;

    public static final int FIELD_SIZE = 2;
    public static final int BOARD_SIZE = 8;
    public static final int TILE_SIZE = 40;

    protected static final Map<Vector, Dir> vectorDir = new HashMap<>();
    protected static final Map<Dir, Vector> dirVector = new EnumMap<>(Dir.class);

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
    
    Util(){}

    public static Vector getVector(Dir d){
        return new Vector(dirVector.get(d));
    }

    public static Dir getDir(Vector p){
        return vectorDir.get(p);
    }
}
