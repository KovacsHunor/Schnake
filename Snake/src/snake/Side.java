package snake;
import java.awt.*;


public class Side {
    private Color color;
    private Side pair;
    private Board board;

    public Side(Color c, Board b){
        color = c;
        board = b;
    }

    public Color getColor(){
        return color;
    }

    public void set(Side s2, Color c){
        pair = s2;
        color = c;
    }
}
