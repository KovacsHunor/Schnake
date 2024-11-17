package logic.util;

import java.awt.Color;
import java.util.*;

public class Utils {
    public static final int TICK = 10;
    public static final int SPEED = 5;

    public static int fieldSize;
    public static int boardSize;
    public static int tileSize;

    public static final Color BACKGROUND_COLOR = new Color(0x202029);

    private static final Map<Vector, Dir> vectorDir = new HashMap<>();
    private static final Map<Dir, Vector> dirVector = new EnumMap<>(Dir.class);

    private Utils(){}

    public static void updateTileSize(){
        tileSize = 1000/(Math.max(fieldSize, 2)*(boardSize+3)-1);
    }

    static{
        fieldSize = 2;
        boardSize = 7;
        updateTileSize();

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