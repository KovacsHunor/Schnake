package snake;
import java.awt.Point;

public class Vector extends Point{
    public Vector(int x, int y){
        super(x, y);
    }

    public Vector(Vector v){
        super(v.x, v.y);
    }

    public void add(Point p){
        x += p.x;
        y += p.y;
    }

    public void sub(Point p){
        x -= p.x;
        y -= p.y;
    }

    public void negate(){
        x = -x;
        y = -y;
    }

    public Vector negated(){
        return new Vector(-x, -y);
    }

    //numbered from 0
    public void rotateInSquare(int squareSize, int rotNum){
        for (int i = 0; i < rotNum%4; i++) {
            int temp = x;
            x = squareSize-1-y;
            y = temp;
        }
    }

    public void rotate(int rotNum){
        for (int i = 0; i < rotNum%4; i++) {
            int temp = x;
            x = -y;
            y = temp;
        }
    }

    public Vector rotated(int rotNum){
        Vector rot = new Vector(x, y);
        rot.rotate(rotNum);
        return rot;
    }

    public int toRotate(Vector p){
        int c = 0;
        while(!p.equals(this)){
            p = p.rotated(1);
            c++;
            if(c == 4) return -1; // todo:exception
        }
        return c;
    }
}
