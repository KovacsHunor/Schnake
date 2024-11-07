package logic.field;
import java.awt.*;
import logic.util.Dir;


public class Side {
    private Color color;
    private Side pair;
    private final Board board;
    private final Dir dir;

    public Side(Color c, Board b, Dir d){
        color = c;
        board = b;
        dir = d;
    }

    public static void connect(Side s1, Side s2, Color c){
        s1.set(s2, c);
        s2.set(s1, c);
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

    private void set(Side s2, Color c){
        pair = s2;
        color = c;
    }
}
