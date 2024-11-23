package source.logic.field;
import java.awt.*;

import source.logic.util.Dir;


public class Side {
    private Color color;
    private Side pair;
    private final Board board;
    private final Dir dir;
    private boolean defaultOriented;

    public Side(Color c, Board b, Dir d){
        color = c;
        board = b;
        dir = d;

        defaultOriented = true;
    }

    public static void connect(Side s1, Side s2, Color c){
        s1.set(s2, c);
        s1.setDefaultOriented(true);
        s2.set(s1, c);
        s2.setDefaultOriented(false);
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

    public boolean isDefaultOriented(){
        return defaultOriented;
    }

    public void setDefaultOriented(boolean b){
        defaultOriented = b;
    }

    private void set(Side s2, Color c){
        pair = s2;
        color = c;
    }
}
