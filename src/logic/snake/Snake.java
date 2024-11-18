package logic.snake;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import logic.field.Board;
import logic.field.Side;
import logic.util.Utils;
import logic.util.Vector;

public class Snake {

    private Board board;
    private final Color color;
    private Vector pos = new Vector(0, 0);
    private Vector originalDir = new Vector(0, 1);
    private Vector dir = new Vector(0, 1);
    private int point = 0;

    private boolean deathFlag = false;

    private final List<Node> nodes = new LinkedList<>();

    public void kill() {
        deathFlag = true;
    }

    public Snake(Board b, Color c) {
        board = b;
        color = c;
        Node node = new Node(board, new Vector(pos), color);
        nodes.add(node);
        board.putOnTile(pos, node);
    }

    public void setPos(Vector newPos) {
        pos = newPos;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int newPoint) {
        point = newPoint;
    }

    public boolean checkDeath() {
        return deathFlag;
    }

    public void grow() {
        Node endNode = new Node(nodes.get(nodes.size() - 1));
        nodes.add(endNode);
    }

    public void setBoard(Board b) {
        board = b;
    }

    private void posUpdate() {
        pos.translate(dir.x, dir.y);

        if (pos.x < 0 || pos.x >= board.getTileNum() || pos.y < 0 || pos.y >= board.getTileNum()) {

            if (pos.x < 0 || pos.x >= board.getTileNum()) {
                pos.y = board.getTileNum() - pos.y - 1;
            } else {
                pos.x = board.getTileNum() - pos.x - 1;
            }

            
            Side current = board.getSide(Utils.getDir(dir));
            Side pair = current.getPair();
            
            board = pair.getBoard();
            Vector newdir = Utils.getVector(pair.getDir());
            
            int torotate = newdir.toRotate(dir);
            pos.sub(dir);
            pos.rotateInSquare(board.getTileNum(), torotate);

            dir = newdir.negated();
        }
    }

    public void move() {
        posUpdate();

        Node node;
        for (int i = nodes.size() - 1; i > 0; i--) {
            node = nodes.get(i);
            node.getBoard().getTile(node.getPos()).remove(node);

            node.setPos(nodes.get(i - 1).getPos());
            node.setBoard(nodes.get(i - 1).getBoard());
            node.getBoard().getTile(node.getPos()).put(node);
        }
        node = nodes.get(0);
        node.getBoard().getTile(node.getPos()).remove(node);
        node.setPos(pos);
        node.setBoard(board);
        node.getBoard().getTile(node.getPos()).put(node);

        originalDir = dir;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Vector getPos() {
        return pos;
    }

    public int getSize() {
        return nodes.size();
    }

    public Board getBoard() {
        return board;
    }

    public Vector getDir() {
        return dir;
    }

    public Vector getOriginalDir() {
        return originalDir;
    }

    public void setDir(Vector v) {
        dir = v;
    }

}
