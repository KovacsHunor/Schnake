package source.logic.util;

import java.awt.Polygon;

/**
 * polygons, it is used to define how the sides look
 */
public class MyPolygon extends Polygon {

    /**
     * constructor
     * @param xpoints   the x coordinates
     * @param ypoints   the y coordinates
     * @param npoints   the number of vertexes
     */
    public MyPolygon(int[] xpoints, int[] ypoints, int npoints) {
        super(xpoints, ypoints, npoints);
    }

    /**
     * copy constructor
     * @param p the polygon to copy
     */
    public MyPolygon(Polygon p) {
        super(p.xpoints, p.ypoints, p.npoints);
    }

    /**
     * mirrors the polygon horizontally
     * @return  the mirrored polygon
     */
    public MyPolygon mirrorH(){
        MyPolygon p = new MyPolygon(this);
        for (int i = 1; i < xpoints.length; i++) {
            p.xpoints[i] *= -1;
        }
        return p;
    }

    /**
     * rotates the polygon by 90 degrees then mirrors it horizontally
     * @return  the rotated polygon (its technically like a transpose of the polygon)
     */
    public MyPolygon rotate() {
        MyPolygon p = new MyPolygon(this);
        for (int i = 0; i < xpoints.length; i++) {
            p.xpoints[i] = ypoints[i];
            p.ypoints[i] = xpoints[i];
        }
        return p;
    }

    /**
     * translates the polygon by the given vector
     * @param v the vector
     * @return  the translated polygon
     */
    public MyPolygon translate(Vector v){
        MyPolygon p = new MyPolygon(this);
        for (int i = 0; i < xpoints.length; i++) {
            p.xpoints[i] += v.x;
            p.ypoints[i] += v.y;
        }
        return p;
    }
}
