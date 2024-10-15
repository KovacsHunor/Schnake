package snake;

import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.image.*;

public class Snake {
    private BufferedImage img;
    private Graphics2D g2d;

    private Color pColor;
    private Point pos;
    private Point dir;

    public Snake() {
        img = new BufferedImage(Field.TILE_SIZE, Field.TILE_SIZE,
                BufferedImage.TYPE_INT_RGB);
        g2d = img.createGraphics();

        pColor = new Color(0, 0, 0);
        dir = new Point(1, 0);
        pos = new Point(Field.COLUMNS / 2, Field.ROWS / 2);
    }

    public void draw(Graphics g, ImageObserver observer) {
        pColor = new Color(255, 89, 94);

        g2d.setColor(pColor);
        g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
        g.drawImage(img, pos.x * Field.TILE_SIZE, pos.y * Field.TILE_SIZE, observer);
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

        if (pos.x < 0) {
            pos.x = 0;
        } else if (pos.x >= Field.COLUMNS) {
            pos.x = Field.COLUMNS - 1;
        }
        if (pos.y < 0) {
            pos.y = 0;
        } else if (pos.y >= Field.ROWS) {
            pos.y = Field.ROWS - 1;
        }
    }

    public Point getPos() {
        return pos;
    }

}
