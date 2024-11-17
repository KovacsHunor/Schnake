package gui.game;

import gui.main.Main;
import gui.views.Game;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import logic.field.Board;
import logic.field.GridTile;
import logic.field.Side;
import logic.fruit.Fruit;
import logic.snake.Snake;
import logic.util.Dir;
import logic.util.Utils;
import logic.util.Vector;

public class Field implements ActionListener {
    
    private final Random rnd = new Random();
    private final Timer timer = new Timer(Utils.TICK, this);
    private Board[][] boards;
    private final Game game;
    private final FieldGui gui;
    
    private int dTime = 0;
    private Snake player;
    
    public Field(Game game, FieldGui gui) {
        this.gui = gui;
        this.game = game;
        init();
    }

    public Game getGame(){
        return game;
    }

    public Snake getPlayer(){
        return player;
    }

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
        Collections.shuffle(sideShuffle);
        Collections.shuffle(boardShuffle);

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

    public void init() {
        boards = new Board[Utils.fieldSize][Utils.fieldSize];

        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards[i].length; j++) {
                boards[i][j] = new Board(new Vector(i, j));
            }
        }

        player = new Snake(boards[0][0], new Color(255, 89, 94));

        shuffleSides();
        Fruit.newFruit(boards);

        timer.restart();
        stopTimer();
    }


    

    //function for colors distinct to the human eye
    private float pleasingColorFunction(float x) {
        return (float) (6.2016 * (0.0911254 * Math.pow(x, 5) - 0.107401 * Math.pow(x, 4) - 0.281072 * Math.pow(x, 3) + 0.408596 * Math.pow(x, 2) + 0.15 * x));
    }

    private List<Color> distributedColors(int n) {
        List<Color> palette = new ArrayList<>();
        float offset = 1.0f / (2 * n) * player.getPoint() / 5;
        float h;
        float s;
        float b;
        for (int i = 0; i < n; i++) {
            float x = ((float) i / n + offset) - (int) ((float) i / n + offset);
            h = pleasingColorFunction(x);
            s = 0.5f + (i % 3) * 0.25f;
            b = 1.0f - ((i + 2) % 3) * 0.25f;

            palette.add(Color.getHSBColor(h, s, b));
        }

        return palette;
    }

    public Board[][] getBoards() {
        return boards;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dTime = (dTime + 1) % ((1000 / Utils.SPEED) / Utils.TICK);
        if (dTime == 0) {
            player.move();

            GridTile gt = player.getBoard().getTile((player.getPos()));
            gt.steppedOn(this, player);

            if (player.checkDeath()) {
                Main.toDeathScreen(player.getPoint());
                reset();
            }
            updatePoint();
        }
        gui.repaint();
    }

    public void draw(Graphics g) {
        for (Board[] row : boards) {
            for (Board board : row) {
                board.draw(g, gui);
            }
        }
    }

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
