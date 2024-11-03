package snake;

import fruit.Fruit;
import fruit.NormalFruit;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class Field extends JPanel implements ActionListener, KeyListener {
    private boolean action = false;
    private Random rnd = new Random();
    private int dTime = 0;
    private Timer timer = new Timer(Util.TICK, this);
    private Fruit fruit;
    private Board[][] boards = new Board[Util.FIELD_SIZE][Util.FIELD_SIZE];
    private Snake player;

    public Field() {
        List<Side> sideShuffle = new ArrayList<>();
        List<Board> boardShuffle = new ArrayList<>();

        setPreferredSize(new Dimension(Util.TILE_SIZE * (Util.FIELD_SIZE * (Util.BOARD_SIZE + 3) - 1),
                Util.TILE_SIZE * (Util.FIELD_SIZE * (Util.BOARD_SIZE + 3) - 1)));
        setBackground(new Color(30, 30, 30));

        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards[i].length; j++) {
                boards[i][j] = new Board(new Vector(i, j));
                boardShuffle.add(boards[i][j]);
                sideShuffle.addAll(boards[i][j].getSides().values());
            }
        }
        player = new Snake(boards[rnd.nextInt(Util.FIELD_SIZE)][rnd.nextInt(Util.FIELD_SIZE)], new Color(255, 89, 94));

        sideShuffle.sort((a, b) -> rnd.nextInt());
        boardShuffle.sort((a, b) -> rnd.nextInt());

        fruit = new NormalFruit(boardShuffle.get(0),
                new Vector(rnd.nextInt(Util.BOARD_SIZE), rnd.nextInt(Util.BOARD_SIZE)),
                new Color(0, 255, 0));

        int dirIndex1 = rnd.nextInt(4);
        int dirIndex2 = rnd.nextInt(4);

        List<Color> colors = distributedColors(sideShuffle.size());
        for (int i = 1; i < boardShuffle.size(); i++) {
            Color c = colors.getFirst();
            colors.removeFirst();

            while (dirIndex1 == dirIndex2) {
                dirIndex1 = rnd.nextInt(4);
            }
            dirIndex2 = rnd.nextInt(4);

            Side s1 = boardShuffle.get(i - 1).getSide(Dir.values()[dirIndex1]);
            Side s2 = boardShuffle.get(i).getSide(Dir.values()[dirIndex2]);
            Side.connect(s1, s2, c);
            sideShuffle.remove(s1);
            sideShuffle.remove(s2);
        }

        for (int i = 0; i < sideShuffle.size(); i += 2) {
            Color c = colors.getFirst();
            colors.removeFirst();

            Side.connect(sideShuffle.get(i), sideShuffle.get(i + 1), c);
        }
        timer.start();
    }

    private  List<Color> distributedColors(int n){
        int range = 1000;
        List<Color> palette = new ArrayList<>();
        
        int r;
        int g;
        int b;
        for (int i = 0; i < n; i++) {

            int pos = range/n*i + rnd.nextInt(range/n/3);
            int lower = 66;
            int upper = 245;

            if(pos < range/6) r = upper;
            else if(pos < 2*range/6) r = upper - pos%(range/6);
            else if(pos < 4*range/6) r = lower;
            else if(pos < 5*range/6) r = lower + pos%(range/6);
            else r = upper;

            if(pos < range/6) g = lower+pos;
            else if(pos < 1.7*range/6) g = upper;
            else if(pos < 3.1*range/6) g = upper - pos%(range/6);
            else g = lower;

            if(pos < 2*range/6) b = lower;
            else if(pos < 3*range/6) b = lower + pos%(range/6);
            else if(pos < 5*range/6) b = upper;
            else b = upper - pos%(range/6);


            palette.add(new Color(r, g, b));
        }


        return palette;
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dTime = (dTime + 1) % ((1000 / Util.SPEED) / Util.TICK);
        if (dTime == 0) {
            action = false;
            player.move();
            if (player.checkDeath()) {
                //TODO
            }
            if (fruit.getPos().equals(player.getPos()) && fruit.getBoard().equals(player.getBoard())) {
                fruit.eatenBy(player);
                fruit = new NormalFruit(boards[rnd.nextInt(Util.FIELD_SIZE)][rnd.nextInt(Util.FIELD_SIZE)],
                        new Vector(rnd.nextInt(Util.BOARD_SIZE), rnd.nextInt(Util.BOARD_SIZE)), new Color(0, 255, 0));
            }
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
        fruit.draw(g);
        player.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // must be defined - KeyListener interface
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!action) {
            action = player.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // must be defined - KeyListener interface
    }
}
