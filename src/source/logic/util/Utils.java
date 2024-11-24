package source.logic.util;

import java.awt.Color;
import java.util.*;

/**
 * useful constants and functions which does not really belong to other classes
 */
public class Utils {
    public static final int SPEED = 35;

    public static final Color BACKGROUND_COLOR = new Color(0x202029);

    private static final Map<Vector, Dir> vectorDir = new HashMap<>();
    private static final Map<Dir, Vector> dirVector = new EnumMap<>(Dir.class);

    /**
     * private constructor to hide the public one
     */
    private Utils(){}

    /**
     * filling the maps for the directions
     */
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
    
    /**
     * gets the vector form of a direction
     * @param d the enum form of the direction
     * @return  the vector form of the direction
     */
    public static Vector getVector(Dir d){
        return new Vector(dirVector.get(d));
    }

    /**
     * gets the enum form of a direction
     * @param p the vector form of the direction
     * @return  the enum form of the direction
     */
    public static Dir getDir(Vector p){
        return vectorDir.get(p);
    }
}