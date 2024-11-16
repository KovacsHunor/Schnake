package logic.util;

import java.awt.Polygon;

public class MyPolygon extends Polygon {

    public MyPolygon(int[] xpoints, int[] ypoints, int npoints) {
        super(xpoints, ypoints, npoints);
    }

    public MyPolygon(Polygon p) {
        super(p.xpoints, p.ypoints, p.npoints);
    }

    public MyPolygon mirrorH(){
        MyPolygon p = new MyPolygon(this);
        for (int i = 1; i < xpoints.length; i++) {
            p.xpoints[i] *= -1;
        }
        return p;
    }

    public MyPolygon rotate() {
        MyPolygon p = new MyPolygon(this);
        for (int i = 0; i < xpoints.length; i++) {
            p.xpoints[i] = ypoints[i];
            p.ypoints[i] = xpoints[i];
        }
        return p;
    }

    public MyPolygon translate(Vector v){
        MyPolygon p = new MyPolygon(this);
        for (int i = 0; i < xpoints.length; i++) {
            p.xpoints[i] += v.x;
            p.ypoints[i] += v.y;
        }
        return p;
    }
}
