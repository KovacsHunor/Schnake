package snake;
import java.awt.*;


public class Side {
    private Color color;
    private Side pair;
    private Board board;
    private Dir dir;

    public Side(Color c, Board b, Dir d){
        color = c;
        board = b;
        dir = d;
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

    public void set(Side s2, Color c){
        pair = s2;
        color = c;
    }
}
