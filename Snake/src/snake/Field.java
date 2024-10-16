package snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Field extends JPanel implements ActionListener, KeyListener {
    private static final int TICK = 10;
    private static final int SPEED = 100;

    public static final int OFFSET = 1;
    public static final int BOARD_SIZE = 8;
    public static final int FIELD_SIZE = 2;
    public static final int TILE_SIZE = 50;

    private int dTime;
    private Timer timer;
    private Snake player;

    private Board[][] boards = new Board[FIELD_SIZE][FIELD_SIZE];

    public Field() {
        setPreferredSize(new Dimension(TILE_SIZE * (BOARD_SIZE * FIELD_SIZE + FIELD_SIZE * 3 - 1),
        TILE_SIZE * (BOARD_SIZE * FIELD_SIZE + FIELD_SIZE * 3 - 1)));
        setBackground(new Color(30, 30, 30));

        player = new Snake();

        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards[i].length; j++) {
                boards[i][j] = new Board(new Point(i, j));
            }
        }

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
        for (Board[] row : boards) {
            for (Board board : row) {
                board.draw(g);
            }
        }
        player.draw(g);
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
}
