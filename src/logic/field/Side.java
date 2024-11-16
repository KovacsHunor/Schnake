package logic.field;
import java.awt.*;
import logic.util.Dir;


public class Side {
    private Color color;
    private Side pair;
    private final Board board;
    private final Dir dir;
    private boolean orientation;

    public Side(Color c, Board b, Dir d){
        color = c;
        board = b;
        dir = d;
    }

    public static void connect(Side s1, Side s2, Color c){
        s1.set(s2, c);
        s1.setOrientation(true);
        s2.set(s1, c);
        s2.setOrientation(false);
    } 

    public Color getColor(){
        return color;
    }

    public Dir getDir(){
        return dir;
    }

    public Side getPair(){
        return pair;
    }

    public Board getBoard(){
        return board;
    }

    public boolean getOrientation(){
        return orientation;
    }

    public void setOrientation(boolean b){
        orientation = b;
    }

    private void set(Side s2, Color c){
        pair = s2;
        color = c;
    }
}
