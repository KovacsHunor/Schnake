package snake;

import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.image.*;

public class Snake {
    private BufferedImage img;
    private Graphics2D g2d;

    private Board board;
    private Color pColor;
    private Point pos;
    private Point dir;

    public Snake(Board b) {
        img = new BufferedImage(Field.TILE_SIZE, Field.TILE_SIZE,
                BufferedImage.TYPE_INT_RGB);
        g2d = img.createGraphics();

        board = b;
        pColor = new Color(0, 0, 0);
        dir = new Point(0, 1);
        pos = new Point(0, 0);
    }

    public void draw(Graphics g) {
        pColor = new Color(255, 89, 94);

        g2d.setColor(pColor);
        g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
        g.drawImage(img, (pos.x + 1 + (board.getPos().x) * (Field.BOARD_SIZE + 3))
                * Field.TILE_SIZE, ((pos.y + 1 + (board.getPos().y) * (Field.BOARD_SIZE + 3)) * Field.TILE_SIZE), null);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP && dir.y != 1) {
            dir = new Point(0, -1);
        } else if (key == KeyEvent.VK_DOWN && dir.y != -1) {
            dir = new Point(0, 1);
        } else if (key == KeyEvent.VK_RIGHT && dir.x != -1) {
            dir = new Point(1, 0);
        } else if (key == KeyEvent.VK_LEFT && dir.x != 1) {
            dir = new Point(-1, 0);
        }
    }

    public void move() {
        pos.translate(dir.x, dir.y);

        if (pos.x < 0 || pos.x >= Field.BOARD_SIZE || pos.y < 0 || pos.y >= Field.BOARD_SIZE) {
            
            if(pos.x < 0 || pos.x >= Field.BOARD_SIZE){
                pos.y = Field.BOARD_SIZE - pos.y - 1;
            }
            else{
                pos.x = Field.BOARD_SIZE - pos.x - 1;
            }
            
            pos = DirUtil.subtract(pos, dir);

            Side current = board.getSide(DirUtil.getDir(dir));
            Side pair = current.getPair();

            board = pair.getBoard();

            Point center = new Point(Field.BOARD_SIZE/2, Field.BOARD_SIZE/2);

            Point newdir = DirUtil.rotateRight(DirUtil.getVector(pair.getDir()), 2);
            
            Point relpos = DirUtil.subtract(pos, center);
            int torotate = DirUtil.toRotate(dir, DirUtil.rotateRight(newdir, 2));
            pos = DirUtil.rotateRight(relpos, torotate);
            pos = DirUtil.subtract(pos, new Point(-center.x, -center.y));

            //todo: refactor
            if(Field.BOARD_SIZE %2 == 0){
                pos.x--;
                pos.y--;
            }

            dir = newdir;
        }
    }

    public Point getPos() {
        return pos;
    }

}
