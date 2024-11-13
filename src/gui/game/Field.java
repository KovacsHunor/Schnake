package gui.game;

import fruit.Fruit;
import fruit.NormalFruit;
import gui.*;
import gui.views.Game;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import logic.field.Board;
import logic.field.Side;
import logic.snake.Snake;
import logic.util.Dir;
import logic.util.Util;
import logic.util.Vector;
import main.Main;

public class Field extends JPanel implements ActionListener, Resettable {

    private Random rnd = new Random();
    private int dTime = 0;
    private Timer timer = new Timer(Util.TICK, this);
    private Fruit fruit;
    private Board[][] boards = new Board[Util.FIELD_SIZE][Util.FIELD_SIZE];
    private Snake player;
    private Game game;

    public void updatePoint(){
        player.setPoint(player.getSize()-1);
        game.setPointLabel(player.getPoint());
    }

    private void init() {
        List<Side> sideShuffle = new ArrayList<>();
        List<Board> boardShuffle = new ArrayList<>();

        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards[i].length; j++) {
                boards[i][j] = new Board(new Vector(i, j));
                boardShuffle.add(boards[i][j]);
                sideShuffle.addAll(boards[i][j].getSides().values());
            }
        }
        player = new Snake(boards[rnd.nextInt(Util.FIELD_SIZE)][rnd.nextInt(Util.FIELD_SIZE)], new Color(255, 89, 94));
        updatePoint();

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

        timer.restart();
        stopTimer();
    }

    public Field(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(Util.TILE_SIZE * (Util.FIELD_SIZE * (Util.BOARD_SIZE + 3) - 1),
                Util.TILE_SIZE * (Util.FIELD_SIZE * (Util.BOARD_SIZE + 3) - 1)));

        setBackground(new Color(30, 30, 30));
        setKeyBindings();

        init();
    }

    private void setKeyBindings() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");
        getActionMap().put("up", upButton());

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down");
        getActionMap().put("down", downButton());

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "left");
        getActionMap().put("left", leftButton());

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "right");
        getActionMap().put("right", rightButton());
    }

    private List<Color> distributedColors(int n) {
        List<Color> palette = new ArrayList<>();

        float h = 0;
        float s;
        float b;
        for (int i = 1; i <= n; i++) {

            h += (float) i / n;
            s = rnd.nextFloat(0.33f, 1);
            b = rnd.nextFloat(0.33f, 1);

            palette.add(Color.getHSBColor(h, s, b));
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
            player.move();
            if (player.checkDeath()) {
                Main.toDeathScreen(player.getPoint());
                reset();
            }
            if (fruit.getPos().equals(player.getPos()) && fruit.getBoard().equals(player.getBoard())) {
                fruit.eatenBy(player);
                updatePoint();
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

    private Action upButton() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.getOriginalDir().y != 1) {
                    player.setDir(new Vector(0, -1));
                }
            }
        };
    }

    private Action downButton() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.getOriginalDir().y != -1) {
                    player.setDir(new Vector(0, 1));
                }
            }
        };
    }

    private Action leftButton() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.getOriginalDir().x != 1) {
                    player.setDir(new Vector(-1, 0));
                }
            }
        };
    }

    private Action rightButton() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.getOriginalDir().x != -1) {
                    player.setDir(new Vector(1, 0));
                }
            }
        };
    }

    @Override
    public void reset() {
        init();
    }

    public void start(){
        startTimer();
    }
}
