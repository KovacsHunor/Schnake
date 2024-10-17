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

public class DirUtil {
    protected static final Map<Vector, Dir> vectorDir;
    protected static final Map<Dir, Vector> dirVector;
    static{
        vectorDir = new HashMap<>();
        vectorDir.put(new Vector(0, -1), Dir.UP);
        vectorDir.put(new Vector(0, 1), Dir.DOWN);
        vectorDir.put(new Vector(1, 0), Dir.RIGHT);
        vectorDir.put(new Vector(-1, 0), Dir.LEFT);

        dirVector = new EnumMap<>(Dir.class);
        dirVector.put(Dir.UP, new Vector(0, -1));
        dirVector.put(Dir.DOWN, new Vector(0, 1));
        dirVector.put(Dir.RIGHT, new Vector(1, 0));
        dirVector.put(Dir.LEFT, new Vector(-1, 0));
    }
    
    DirUtil(){}

    public static Vector getVector(Dir d){
        return new Vector(dirVector.get(d));
    }

    public static Dir getDir(Vector p){
        return vectorDir.get(p);
    }
}
