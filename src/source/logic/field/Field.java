package source.logic.field;

import source.gui.main.Main;
import source.logic.fruit.Fruit;
import source.logic.snake.Snake;
import source.logic.util.Dir;
import source.logic.util.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * the field, the class follows the singleton design pattern
 */
public final class Field {
    private final Random rnd = new Random();
    private Board[][] boards;
    private int boardNum = 2;
    private int tileNum = 6;
    private int tileSize = 50;
    private Snake snake;

    private static Field field;

    /**
     * returns n where n*n is the number of boards on the field
     * @return  the sidelength of the field in boards
     */
    public int getBoardNum() {
        return boardNum;
    }

    /**
     * returns n where n*n is the number of tiles on a board
     * @return  the sidelength of a board in tiles
     */
    public int getTileNum() {
        return tileNum;
    }

    /**
     * returns the size of the tiles
     * @return  the size of the tiles
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * a private constructor to hide the default public one, essential for the class to be a singleton
     */
    private Field() {

    }

    /**
     * creates a new field with the given parameters
     * @param boardNum  the sidelingth of the field
     * @param tileNum   the sidelength of the boards
     * @return  the new field
     */
    public static Field newInstance(int boardNum, int tileNum) {
        field = new Field(boardNum, tileNum);
        field.init();
        return field;
    }

    /**
     * creates a new field, same size as the last one
     * @return  the new field
     */
    public static Field newInstance() {
        field = new Field(field.boardNum, field.tileNum);
        field.init();
        return field;
    }

    /**
     * returns the only field instance if exists, if not throws an illegalArgumentException
     * @return  the field
     */
    public static Field getInstance() {
        if (field == null) {
            throw new IllegalArgumentException();
        }
        return field;
    }

    /**
     * Creates a field with the given parameters
     * @param boardNum  the sidelength of the field
     * @param tileNum   the sidelength of the boards
     */
    private Field(int boardNum, int tileNum) {
        this.boardNum = boardNum;
        this.tileNum = tileNum;
    }

    /**
     * returns the snake on the field
     * @return  the snake
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * randomly connects the sides of the boards with the only constraint being,
     * that there must be a route from every board to any other
     */
    public void shuffleSides() {
        List<Side> sideShuffle = new ArrayList<>();
        List<Board> boardShuffle = new ArrayList<>();

        for (Board[] boardArray : boards) {
            for (Board board : boardArray) {
                Collection<Side> sides = board.getSides().values();

                sideShuffle.addAll(sides);
                boardShuffle.add(board);
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
    }

    /**
     * initializes the field
     */
    private void init() {
        tileSize = 1000 / (Math.max(boardNum, 2) * (tileNum + 3) - 1);
        boards = new Board[boardNum][boardNum];

        for (int i = 0; i < boardNum; i++) {
            for (int j = 0; j < boardNum; j++) {
                boards[i][j] = new Board(new Vector(i, j));
            }
        }

        snake = new Snake(boards[0][0], new Color(255, 89, 94));

        shuffleSides();
        Fruit.newFruit();
        Board.setPolygons();
    }

    /**
     * function for creating colors more distinguishable to the human eye
     * (created with the help of desmos and wolphramalpha)
     * @param hue the original hue value 
     * @return the new hue value
     */
    private float distinctColorFunction(float hue) {
        hue = hue - (int)hue;
        return (float) (6.2016 * (0.0911254 * Math.pow(hue, 5) - 0.107401 * Math.pow(hue, 4) - 0.281072 * Math.pow(hue, 3)
                + 0.408596 * Math.pow(hue, 2) + 0.15 * hue));
    }

    /**
     * computes n colors distributed in a way that they are as distinguishable from eachother as possible
     * while also keeping them pleasant to the eye
     * @param n the number of colors needed
     * @return  the List of colors
     */
    private List<Color> distributedColors(int n) {
        List<Color> palette = new ArrayList<>();
        float offset = 1.0f / (10 * n) * snake.getPoint();
        float h;
        float s;
        float b;
        for (int i = 0; i < n; i++) {
            h = distinctColorFunction((float)i / n + offset);
            s = 0.5f + (i % 3) * 0.25f;
            b = 1.0f - ((i + 2) % 3) * 0.25f;

            palette.add(Color.getHSBColor(h, s, b));
        }

        return palette;
    }

    /**
     * returns the boards on the field
     * @return  the boards
     */
    public Board[][] getBoards() {
        return boards;
    }

    /**
     * the things to do in a tick
     */
    public void tick() {
        snake.move();

        GridTile gt = snake.getFieldPos().getBoard().getTile((snake.getFieldPos().getPos()));
        gt.steppedOn(snake);

        if (snake.checkDeath()) {
            Main.toDeathScreen(snake.getPoint());
        }
    }

    /**
     * draws the contents of the field
     * @param g the Graphics object to draw on
     */
    public void draw(Graphics g) {
        for (Board[] row : boards) {
            for (Board board : row) {
                board.draw(g);
            }
        }
    }
}
