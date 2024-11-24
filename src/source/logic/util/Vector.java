package source.logic.util;
import java.awt.Point;

/**
 * 2D vectors
 */
public class Vector extends Point{
    /**
     * constructor
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Vector(int x, int y){
        super(x, y);
    }

    /**
     * copy constructor
     * @param v the vector to copy
     */
    public Vector(Vector v){
        super(v.x, v.y);
    }

    /**
     * returns the sum of this vector and another one
     * @param p the vector to add
     * @return  the new vector
     */
    public Vector sum(Vector p){
        return new Vector(x+p.x, y+p.y);
    }

    /**
     * substitutes a given vector from this one
     * @param p
     */
    public void sub(Vector p){
        x -= p.x;
        y -= p.y;
    }

    /**
     * negates the vector
     */
    public void negate(){
        x = -x;
        y = -y;
    }

    /**
     * returns the negated version of the vector
     * @return  the new vector
     */
    public Vector negated(){
        return new Vector(-x, -y);
    }

    /**
     * rotates the vector clockwise in a square by 90 degrees a given number of times
     * @param squareSize the size of the square
     * @param rotNum    the number of times to rotate
     */
    public void rotateInSquare(int squareSize, int rotNum){
        for (int i = 0; i < rotNum%4; i++) {
            int temp = x;
            x = squareSize-1-y;
            y = temp;
        }
    }

    /**
     * rotates the vector clockwise a given number of times by 90 degrees
     * @param rotNum    the number of times to rotate
     */
    public void rotate(int rotNum){
        for (int i = 0; i < rotNum%4; i++) {
            int temp = x;
            x = -y;
            y = temp;
        }
    }

    /**
     * returns the rotated version of the vector
     * @param rotNum    the number of times to rotate
     * @return  the new vector
     */
    public Vector rotated(int rotNum){
        Vector rot = new Vector(x, y);
        rot.rotate(rotNum);
        return rot;
    }

    /**
     * returns how many times one has to rotate the vector to get the provided one
     * @param p the goal vector
     * @return  the number of rotations needed
     */
    public int toRotate(Vector p){
        int c = 0;
        while(!p.equals(this)){
            p = p.rotated(1);
            c++;
            if(c >= 4){
                throw new IllegalArgumentException("cannot reach goal vector");
            }
        }
        return c;
    }
}
