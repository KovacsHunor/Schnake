package snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Field extends JPanel implements ActionListener, KeyListener {
    private static final int TICK = 10;
    private static final int SPEED = 120;
   
    public static final int FIELD_SIZE = 2;
    public static final int BOARD_SIZE = 15/FIELD_SIZE;
    public static final int TILE_SIZE = 50;

    private int dTime;
    private Timer timer;
    private Snake player;
    private Board[][] boards;
    
    public Field() {
        setPreferredSize(new Dimension(TILE_SIZE * (BOARD_SIZE * FIELD_SIZE + FIELD_SIZE * 3 - 1),
        TILE_SIZE * (BOARD_SIZE * FIELD_SIZE + FIELD_SIZE * 3 - 1)));
        setBackground(new Color(30, 30, 30));
        
        
        boards = new Board[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards[i].length; j++) {
                boards[i][j] = new Board(new Point(i, j));
            }
        }
        
        
        boards[0][0].getSide(Dir.DOWN).set(boards[0][1].getSide(Dir.UP), new Color(180, 40, 70));
        boards[0][1].getSide(Dir.UP).set(boards[0][0].getSide(Dir.DOWN), new Color(180, 40, 70));
        boards[0][0].getSide(Dir.LEFT).set(boards[0][1].getSide(Dir.RIGHT), new Color(50, 210, 20));
        boards[0][1].getSide(Dir.RIGHT).set(boards[0][0].getSide(Dir.LEFT), new Color(50, 210, 20));
        boards[0][0].getSide(Dir.UP).set(boards[0][1].getSide(Dir.DOWN), new Color(200, 190, 50));
        boards[0][1].getSide(Dir.DOWN).set(boards[0][0].getSide(Dir.UP), new Color(200, 190, 50));
        boards[0][0].getSide(Dir.RIGHT).set(boards[0][1].getSide(Dir.LEFT), new Color(60, 40, 190));
        boards[0][1].getSide(Dir.LEFT).set(boards[0][0].getSide(Dir.RIGHT), new Color(60, 40, 190));
        
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
