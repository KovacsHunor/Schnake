package logic.fruit;

import java.awt.*;
import java.util.Random;
import logic.field.Board;
import logic.field.BoardPos;
import logic.field.Field;
import logic.field.GridObject;
import logic.snake.Snake;
import logic.util.Vector;

public abstract class Fruit extends GridObject {
    private static final Random rnd = new Random();

    private static BoardPos firstEmpty() {
        for (Board[] boardRow : Field.getInstance().getBoards()) {
            for (Board board2 : boardRow) {
                for (int j = 0; j < board2.getTileNum(); j++) {
                    for (int k = 0; k < board2.getTileNum(); k++) {
                        Vector pos = new Vector(j, k);
                        if (board2.getTile(pos).isEmpty()) {
                            return new BoardPos(board2, pos);
                        }
                    }
                }
            }
        }
        return null;
    }

    private static BoardPos newBoardPos() {
        Field field = Field.getInstance();
        Board board;
        Vector pos;
        int i = 0;
        do {
            board = field.getBoards()[rnd.nextInt(field.getBoards().length)][rnd.nextInt(field.getBoards().length)];
            pos = new Vector(rnd.nextInt(board.getTileNum()), rnd.nextInt(board.getTileNum()));
            i++;
        } while (!(board.getTile(pos).isEmpty() && (field.getBoardNum() == 1 || board != field.getPlayer().getBoard())) && i < 100);
        if (i < 100) {
            return new BoardPos(board, pos);
        }

        BoardPos boardPos = firstEmpty();
        if (boardPos != null) {
            return boardPos;
        }

        field.getPlayer().kill();
        return null;

    }

    public static void newFruit() {
        double ran = rnd.nextDouble();
        BoardPos boardPos = newBoardPos();
        Fruit fruit;

        if (boardPos == null)
            return;
        //0.7, 0.85, 1
        if (ran <= 0.15) {
            fruit = new ShuffleFruit(boardPos.getBoard(), boardPos.getPos());
        } else if (ran <= 0.3) {
            TeleportFruit pair = new TeleportFruit(boardPos.getBoard(), boardPos.getPos());
            boardPos.getBoard().putOnTile(boardPos.getPos(), pair);
            boardPos = newBoardPos();
            if (boardPos == null)
                return;

            fruit = new TeleportFruit(boardPos.getBoard(), boardPos.getPos());
            ((TeleportFruit) fruit).setPair(pair);
            pair.setPair(fruit);

        } else {
            fruit = new NormalFruit(boardPos.getBoard(), boardPos.getPos());
        }

        boardPos.getBoard().putOnTile(boardPos.getPos(), fruit);
    }

    protected Fruit(Board b, Vector p) {
        board = b;
        pos = p;
    }

    public void eaten() {
        Snake s = Field.getInstance().getPlayer();
        s.grow();
        s.setPoint(s.getPoint() + getValue());
        newFruit();
    }

    @Override
    public void steppedOn() {
        destroy();
        eaten();
    }

    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(
                (pos.x + 1 + (board.getPos().x) * (board.getTileNum() + 3))
                        * board.getTileSize(),
                (pos.y + 1 + (board.getPos().y) * (board.getTileNum() + 3))
                        * board.getTileSize(),
                board.getTileSize(),
                board.getTileSize());
    }

    protected abstract int getValue();
}
