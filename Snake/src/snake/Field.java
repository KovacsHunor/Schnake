package snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Field extends JPanel implements ActionListener, KeyListener {
    private static final int TICK = 10;
    private static final int SPEED = 400;

    public static final int FIELD_SIZE = 2;
    public static final int BOARD_SIZE = 8;
    public static final int TILE_SIZE = 50;

    private Random rnd;
    private int dTime;
    private Timer timer;
    private Snake player;
    private Board[][] boards;

    public Field() {
        setPreferredSize(new Dimension(TILE_SIZE * (FIELD_SIZE * (BOARD_SIZE + 3) - 1),
                TILE_SIZE * (FIELD_SIZE * (BOARD_SIZE + 3) - 1)));
        setBackground(new Color(30, 30, 30));
        rnd = new Random();

        List<Side> sideShuffle = new ArrayList<>();
        List<Board> boardShuffle = new ArrayList<>();

        boards = new Board[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards[i].length; j++) {
                boards[i][j] = new Board(new Vector(i, j));
            }
        }

        for (Board[] row : boards) {
            for (Board board : row) {
                sideShuffle.addAll(board.getSides().values());
            }
        }

        sideShuffle.sort((a, b) -> rnd.nextInt());

        for (int i = 0; i < sideShuffle.size(); i += 2) {
            Color c = new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            Side.connect(sideShuffle.get(i), sideShuffle.get(i+1), c);
        }

        player = new Snake(boards[0][0]);

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
