package gui.game;

import gui.*;
import gui.views.Game;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import logic.field.Board;
import logic.field.GridTile;
import logic.field.Side;
import logic.fruit.Fruit;
import logic.fruit.NormalFruit;
import logic.fruit.ShuffleFruit;
import logic.fruit.TeleportFruit;
import logic.snake.Snake;
import logic.util.Dir;
import logic.util.Utils;
import logic.util.Vector;
import main.Main;

public class Field extends JPanel implements ActionListener, Resettable {

    private final Random rnd = new Random();
    private final Timer timer = new Timer(Utils.TICK, this);
    private final Board[][] boards = new Board[Utils.FIELD_SIZE][Utils.FIELD_SIZE];
    private final Game game;

    private int dTime = 0;
    private Snake player;

    public void updatePoint() {
        game.setPointLabel(player.getPoint());
    }

    public void shuffleSides() {
        List<Side> sideShuffle = new ArrayList<>();
        List<Board> boardShuffle = new ArrayList<>();

        for (Board[] boardArray : boards) {
            for (Board board : boardArray) {
                boardShuffle.add(board);
                sideShuffle.addAll(board.getSides().values());
            }
        }

        sideShuffle.sort((a, b) -> rnd.nextInt());
        boardShuffle.sort((a, b) -> rnd.nextInt());

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

        player.setBoard(boards[player.getBoard().getPos().x][player.getBoard().getPos().y]);
    }

    private void init() {
        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards[i].length; j++) {
                boards[i][j] = new Board(new Vector(i, j));
            }
        }

        player = new Snake(boards[0][0], new Color(255, 89, 94));

        shuffleSides();
        newFruit();

        timer.restart();
        stopTimer();
    }

    public Field(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(Utils.TILE_SIZE * (Utils.FIELD_SIZE * (Utils.BOARD_SIZE + 3) - 1),
                Utils.TILE_SIZE * (Utils.FIELD_SIZE * (Utils.BOARD_SIZE + 3) - 1)));
        setKeyBindings();

        init();
    }

    public void newFruit() {
        double ran = rnd.nextDouble();
        Fruit fruit;
        Board board;
        Vector pos;
        do {
            board = boards[rnd.nextInt(Utils.FIELD_SIZE)][rnd.nextInt(Utils.FIELD_SIZE)];
            pos = new Vector(rnd.nextInt(Utils.BOARD_SIZE), rnd.nextInt(Utils.BOARD_SIZE));
        } while (!board.getTile(pos).isEmpty());

        //0.7, 0.8, 1

        if (ran <= 0.7) {
            fruit = new NormalFruit(board, pos);
        } else if (ran <= 0.8) {
            TeleportFruit pair = new TeleportFruit(board, pos);
            board.putOnTile(pos, pair);

            do {
                board = boards[rnd.nextInt(Utils.FIELD_SIZE)][rnd.nextInt(Utils.FIELD_SIZE)];
                pos = new Vector(rnd.nextInt(Utils.BOARD_SIZE), rnd.nextInt(Utils.BOARD_SIZE));
            } while (!board.getTile(pos).isEmpty());

            fruit = new TeleportFruit(board, pos);
            ((TeleportFruit) fruit).setPair(pair);
            pair.setPair(fruit);

            

        } else {
            fruit = new ShuffleFruit(board, pos);
        }

        board.putOnTile(pos, fruit);
    }

    private void setKeyBindings() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "up");
        getActionMap().put("up", upButton());

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down");
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "down");
        getActionMap().put("down", downButton());

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "left");
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "left");
        getActionMap().put("left", leftButton());

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "right");
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "right");
        getActionMap().put("right", rightButton());
    }

    private List<Color> distributedColors(int n) {
        List<Color> palette = new ArrayList<>();
        float offset = 1.0f/(2*n)*player.getPoint()/5;
        float h;
        float s;
        float b;
        for (int i = 0; i < n; i++) {
            double x = ((double)i/n + offset) - (int)((double)i/n + offset);
            //function for colors distinct to the human eye
            h = (float)(6.2016 * (0.0911254 *x*x*x*x*x - 0.107401 *x*x*x*x - 0.281072 *x*x*x + 0.408596 *x*x + 0.15 *x));
            s = 0.5f+(i%3)*0.25f;
            b = 1.0f-((i+2)%3)*0.25f;

            palette.add(Color.getHSBColor(h, s, b));
        }

        return palette;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dTime = (dTime + 1) % ((1000 / Utils.SPEED) / Utils.TICK);
        if (dTime == 0) {
            player.posUpdate();
            player.move();

            GridTile gt = player.getBoard().getTile((player.getPos()));
            gt.steppedOn(this, player);

            if (player.checkDeath()) {
                Main.toDeathScreen(player.getPoint());
                reset();
            }
            updatePoint();
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Board[] row : boards) {
            for (Board board : row) {
                board.draw(g, this);
            }
        }
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

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public void start() {
        startTimer();
    }
}
