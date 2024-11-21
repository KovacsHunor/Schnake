package logic.field;

import gui.game.FieldGui;
import gui.main.Main;
import gui.views.Game;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import logic.fruit.Fruit;
import logic.snake.Snake;
import logic.util.Dir;
import logic.util.Utils;
import logic.util.Vector;


// Based on Singleton design pattern
public final class Field{
    private final Random rnd = new Random();
    private Board[][] boards;
    
    private Game game;
    private FieldGui gui;

    private int boardNum = 2;
    private int tileNum = 6;
    private int tileSize = 50;
    
    private Snake player;
    private int dTime = 0;

    private static Field field;
    
    public int getBoardNum(){
        return boardNum;
    }

    public int getTileNum(){
        return tileNum;
    }

    public int getTileSize(){
        return tileSize;
    }

    private Field(){

    }

    public static Field newInstance(Game game, FieldGui gui, int b, int t){
        field = new Field(game, gui, b, t);
        field.init();
        return field;
    }

    public static Field newInstance(){
        field = new Field(field.game, field.gui, field.boardNum, field.tileNum);
        field.init();
        return field;
    }
    
    public static Field getInstance(){
        if(field == null){
            throw new IllegalArgumentException();
        }
        return field;
    }
    
    private Field(Game game, FieldGui gui, int b, int t) {
        this.gui = gui;
        this.game = game;
        boardNum = b;
        tileNum = t;
    }
    
    public Game getGame(){
        return game;
    }
    
    public Snake getPlayer(){
        return player;
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
    }
    
    private void init() {
        tileSize = 1000/(Math.max(boardNum, 2)*(tileNum+3)-1);
        boards = new Board[boardNum][boardNum];

        for (int i = 0; i < boardNum; i++) {
            for (int j = 0; j < boardNum; j++) {
                boards[i][j] = new Board(new Vector(i, j));
            }
        }
        
        player = new Snake(boards[0][0], new Color(255, 89, 94));
        
        shuffleSides();
        Fruit.newFruit();
        Board.setPolygons();
    }


    

    //function for colors distinct to the human eye
    private float distinctColorFunction(float x) {
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
            h = distinctColorFunction(x);
            s = 0.5f + (i % 3) * 0.25f;
            b = 1.0f - ((i + 2) % 3) * 0.25f;

            palette.add(Color.getHSBColor(h, s, b));
        }

        return palette;
    }

    public Board[][] getBoards() {
        return boards;
    }

    public void tick() {
        dTime = (dTime + 1) % ((1000 / Utils.SPEED) / Utils.TICK);
        if (dTime == 0) {
            player.move();

            GridTile gt = player.getFieldPos().getBoard().getTile((player.getFieldPos().getPos()));
            gt.steppedOn();

            if (player.checkDeath()) {
                Main.toDeathScreen(player.getPoint());
            }
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
}
