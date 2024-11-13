package logic.snake;

import java.awt.*;
import java.awt.image.*;
import java.util.LinkedList;
import java.util.List;
import logic.field.Board;
import logic.field.Side;
import logic.util.Util;
import logic.util.Vector;

public class Snake {

    private final BufferedImage img = new BufferedImage(Util.TILE_SIZE, Util.TILE_SIZE,
            BufferedImage.TYPE_INT_RGB);
    private final Graphics2D g2d = img.createGraphics();

    private Board board;
    private Color color = new Color(0, 0, 0);
    private final Vector pos = new Vector(0, 0);
    private Vector originalDir = new Vector(0, 1);
    private Vector dir = new Vector(0, 1);
    private int point = 0;

    private final List<Node> nodes = new LinkedList<>();

    public Snake(Board b, Color c) {
        board = b;
        color = c;
        nodes.add(new Node(board, pos));
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int newPoint) {
        point = newPoint;
    }

    public boolean checkDeath() {
        for (int i = 4; i < nodes.size(); i++) {
            if (nodes.get(i).getPos().equals(pos) && nodes.get(i).getBoard().equals(board)) {
                return true;
            }
        }
        return false;
    }

    public void grow() {
        nodes.add(new Node(nodes.get(nodes.size() - 1)));
    }

    public void draw(Graphics g) {
        g2d.setColor(color);
        g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
        for (Node node : nodes) {
            g.drawImage(img, (node.getPos().x + 1 + (node.getBoard().getPos().x) * (Util.BOARD_SIZE + 3))
                    * Util.TILE_SIZE,
                    ((node.getPos().y + 1 + node.getBoard().getPos().y * (Util.BOARD_SIZE + 3)) * Util.TILE_SIZE),
                    null);
        }

    }

    public void move() {
        
        pos.translate(dir.x, dir.y);

        if (pos.x < 0 || pos.x >= Util.BOARD_SIZE || pos.y < 0 || pos.y >= Util.BOARD_SIZE) {

            if (pos.x < 0 || pos.x >= Util.BOARD_SIZE) {
                pos.y = Util.BOARD_SIZE - pos.y - 1;
            } else {
                pos.x = Util.BOARD_SIZE - pos.x - 1;
            }

            pos.sub(dir);

            Side current = board.getSide(Util.getDir(dir));
            Side pair = current.getPair();

            board = pair.getBoard();

            Vector newdir = Util.getVector(pair.getDir());

            int torotate = newdir.toRotate(dir);
            pos.rotateInSquare(Util.BOARD_SIZE, torotate);

            dir = newdir.negated();
        }

        for (int i = nodes.size() - 1; i > 0; i--) {
            nodes.get(i).setPos(nodes.get(i - 1).getPos());
            nodes.get(i).setBoard(nodes.get(i - 1).getBoard());
        }
        nodes.get(0).setPos(pos);
        nodes.get(0).setBoard(board);

        originalDir = dir;
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
