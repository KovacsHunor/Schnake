package source.logic.snake;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import source.logic.field.Board;
import source.logic.field.Field;
import source.logic.field.FieldPos;
import source.logic.field.Side;
import source.logic.util.Utils;
import source.logic.util.Vector;

/**
 * a snake
 */
public class Snake {

    private FieldPos fieldPos;
    private final Color color;
    private Vector originalDir = new Vector(0, 1);
    private Vector dir = new Vector(0, 1);
    private int point = 0;
    private boolean deathFlag = false;
    private final List<Node> nodes = new LinkedList<>();

    /**
     * kills the snake
     */
    public void kill() {
        deathFlag = true;
    }

    /**
     * the constructor
     * @param b the board the snake is currantly on
     * @param c the color of the snake
     */
    public Snake(Board b, Color c) {
        fieldPos = new FieldPos(b, new Vector(0, 0));
        color = c;
        Node node = new Node(fieldPos, color);
        nodes.add(node);
        fieldPos.getBoard().putOnTile(fieldPos.getPos(), node);
    }

    /**
     * returns the current score of the snake
     * @return  the score
     */
    public int getPoint() {
        return point;
    }

    /**
     * sets the score of the snake
     * @param newPoint  the new score
     */
    public void setPoint(int newPoint) {
        point = newPoint;
    }

    /**
     * checks if the snake is still alive
     * @return  true if the snake is dead
     */
    public boolean checkDeath() {
        return deathFlag;
    }

    /**
     * makes the snake grow with one node
     */
    public void grow() {
        // does not matter where it is as long as the position is valid
        FieldPos nodePos = new FieldPos(Field.getInstance().getBoards()[0][0], new Vector(0, 0));

        Node endNode = new Node(nodePos, color);
        nodes.add(endNode);
    }

    /**
     * updates the position of the snake
     */
    private void posUpdate() {
        int tileNum = Field.getInstance().getTileNum();
        Vector pos = fieldPos.getPos();
        Board board = fieldPos.getBoard();

        pos.translate(dir.x, dir.y);

        if (pos.x < 0 || pos.x >= tileNum || pos.y < 0 || pos.y >= tileNum) {

            if (pos.x < 0 || pos.x >= tileNum) {
                pos.y = tileNum - pos.y - 1;
            } else {
                pos.x = tileNum - pos.x - 1;
            }

            Side current = board.getSide(Utils.getDir(dir));
            Side pair = current.getPair();

            board = pair.getBoard();
            Vector newdir = Utils.getVector(pair.getDir());

            int torotate = newdir.toRotate(dir);
            pos.sub(dir);
            pos.rotateInSquare(tileNum, torotate);

            dir = newdir.negated();
        }

        setFieldPos(new FieldPos(board, pos));
    }

    /**
     * sets the position of the snake
     * @param fp
     */
    public void setFieldPos(FieldPos fp) {
        fieldPos = new FieldPos(fp);
    }

    /**
     * returns the position of the snake
     * @return  the position
     */
    public FieldPos getFieldPos() {
        return fieldPos;
    }

    /**
     * makes the snake move based on its direction
     */
    public void move() {
        posUpdate();

        Node node;
        for (int i = nodes.size() - 1; i >= 0; i--) {
            node = nodes.get(i);
            node.withdraw();
            if (i != 0) {
                node.setFieldPos(nodes.get(i - 1).getFieldPos());
            } else {
                node.setFieldPos(fieldPos);
            }
            node.place();
        }

        originalDir = dir;
    }

    /**
     * returns a list of the nodes of the snake
     * @return  a list of the nodes
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * returns the size of the snake
     * @return the size
     */
    public int getSize() {
        return nodes.size();
    }

    /**
     * returns the direction of the snake
     * @return  the direction
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * returns the original direciton of the snake
     * relevant, because the snake can turn where it came from
     * @return  the original direction
     */
    public Vector getOriginalDir() {
        return originalDir;
    }

    /**
     * sets the direction of the snake
     * @param v the new direction
     */
    public void setDir(Vector v) {
        dir = v;
    }

}
