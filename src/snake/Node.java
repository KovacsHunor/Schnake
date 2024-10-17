package snake;

public class Node {
    private Vector pos;
    private Board board;
    public Node(Board b, Vector p){
        board = b;
        pos = p;
    }

    public Node(Node n){
        board = n.board;
        pos = n.pos;
    }

    public Vector getPos(){
        return pos;
    }

    public void setPos(Vector p){
        pos = new Vector(p);
    }

    public Board getBoard(){
        return board;
    }

    public void setBoard(Board b) {
        board = b;
    }
    
}
