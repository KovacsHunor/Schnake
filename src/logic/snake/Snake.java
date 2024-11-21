package logic.snake;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import logic.field.Board;
import logic.field.Field;
import logic.field.FieldPos;
import logic.field.Side;
import logic.util.Utils;
import logic.util.Vector;

public class Snake {

    private FieldPos fieldPos;
    private final Color color;
    private Vector originalDir = new Vector(0, 1);
    private Vector dir = new Vector(0, 1);
    private int point = 0;
    private boolean deathFlag = false;
    private final List<Node> nodes = new LinkedList<>();

    public void kill() {
        deathFlag = true;
    }

    public Snake(Board b, Color c) {
        fieldPos = new FieldPos(b, new Vector(0, 0));
        color = c;
        Node node = new Node(fieldPos, color);
        nodes.add(node);
        fieldPos.getBoard().putOnTile(fieldPos.getPos(), node);
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
        // does not matter where it is as long as the position is valid
        FieldPos nodePos = new FieldPos(Field.getInstance().getBoards()[0][0], new Vector(0, 0));

        Node endNode = new Node(nodePos, color);
        nodes.add(endNode);
    }

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

    public void setFieldPos(FieldPos fp) {
        fieldPos = new FieldPos(fp);
    }

    public FieldPos getFieldPos() {
        return fieldPos;
    }

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

    public List<Node> getNodes() {
        return nodes;
    }

    public int getSize() {
        return nodes.size();
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
