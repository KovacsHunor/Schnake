package snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Field extends JPanel implements ActionListener, KeyListener {
    private static final int TICK = 10;
    private static final int SPEED = 100;
    public static final int TILE_SIZE = 50;
    public static final int ROWS = 19;
    public static final int COLUMNS = 19;

    private int dTime;
    private Timer timer;
    private Snake player;

    public Field() {
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));
        setBackground(new Color(30, 30, 30));

        player = new Snake();

        dTime = 0;
        timer = new Timer(TICK, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dTime = (dTime + 1) % (SPEED / TICK);
        if (dTime == 0) {
            player.move();
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        player.draw(g, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // must be defined - KeyListener interface
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // must be defined - KeyListener interface
    }

    private void drawBackground(Graphics g) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if ((row + col) % 2 == 1) {
                    g.setColor(new Color(60, 60, 60));
                } else {
                    g.setColor(new Color(50, 50, 50));
                }
                g.fillRect(
                        col * TILE_SIZE,
                        row * TILE_SIZE,
                        TILE_SIZE,
                        TILE_SIZE);
            }
        }
    }
}
