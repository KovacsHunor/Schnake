package source.logic.field;
import java.awt.*;

import source.logic.util.Dir;

/**
 * the sides of boards
 */
public class Side {
    private Color color;
    private Side pair;
    private final Board board;
    private final Dir dir;
    private boolean defaultOriented;

    /**
     * the constructor
     * @param c the color of the side
     * @param b the board which this side belongs to
     * @param d the directon of this side
     */
    public Side(Color c, Board b, Dir d){
        color = c;
        board = b;
        dir = d;

        defaultOriented = true;
    }

    /**
     * connects two side and gives them both the same color
     * @param s1    the first side
     * @param s2    the second side
     * @param c     the common color
     */
    public static void connect(Side s1, Side s2, Color c){
        s1.set(s2, c);
        s1.setDefaultOriented(true);
        s2.set(s1, c);
        s2.setDefaultOriented(false);
    } 

    /**
     * returns the side's color
     * @return  the color of this side
     */
    public Color getColor(){
        return color;
    }

    /**
     * returns the directions of this side
     * @return  the direction
     */
    public Dir getDir(){
        return dir;
    }

    /**
     * returns the pair of this side
     * @return  the pair, null if it hasn't been set
     */
    public Side getPair(){
        return pair;
    }

    /**
     * returns the board this side belongs to
     * @return  the board
     */
    public Board getBoard(){
        return board;
    }

    /**
     * returns wether the orientation of this side is the default or not
     * @return  true is the orientation is the default
     */
    public boolean isDefaultOriented(){
        return defaultOriented;
    }

    /**
     * sets the orientation of this side
     * @param b if true, the side's orientation will be default
     */
    public void setDefaultOriented(boolean b){
        defaultOriented = b;
    }

    /**
     * sets a sides pair and color
     * @param s2    the pair
     * @param c     the colory
     */
    private void set(Side s2, Color c){
        pair = s2;
        color = c;
    }
}
