package ensta;

public class TestBoard {
    public static void main(String[] args) {
        Board myBoard = new Board("myBoard");
        myBoard.print();
        Board myOtherBoard = new Board("myOtherBoard", 15);
        myOtherBoard.print();
    }
}