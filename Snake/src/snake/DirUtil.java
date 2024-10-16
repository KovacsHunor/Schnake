package snake;

import java.awt.Point;
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
    protected static final Map<Point, Dir> vectorDir;
    protected static final Map<Dir, Point> dirVector;
    static{
        vectorDir = new HashMap<>();
        vectorDir.put(new Point(0, -1), Dir.UP);
        vectorDir.put(new Point(0, 1), Dir.DOWN);
        vectorDir.put(new Point(1, 0), Dir.RIGHT);
        vectorDir.put(new Point(-1, 0), Dir.LEFT);

        dirVector = new EnumMap<>(Dir.class);
        dirVector.put(Dir.UP, new Point(0, -1));
        dirVector.put(Dir.DOWN, new Point(0, 1));
        dirVector.put(Dir.RIGHT, new Point(1, 0));
        dirVector.put(Dir.LEFT, new Point(-1, 0));
    }
    DirUtil(){

    }

    public static Point rotateRight (Point vec, int n){
        for (int i = 0; i < n%4; i++) {
            vec = new Point(Field.BOARD_SIZE-1-vec.y, vec.x);
        }
        return vec;
    }

    //csak egységvektorokra
    public static int toRotate (Point v1, Point v2){
        int c = 0;
        while(!v1.equals(v2)){
            v1 = new Point(-v1.y, v1.x);
            c++;
            if(c == 4) return -1; // todo:exception
        }
        return c;
    }

    public static Point subtract(Point a, Point b){
        return new Point(a.x-b.x, a.y-b.y);
    }

    public static Point getVector(Dir d){
        return dirVector.get(d);
    }

    public static Dir getDir(Point p){
        return vectorDir.get(p);
    }
}
