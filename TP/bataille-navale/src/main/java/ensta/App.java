package ensta;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // System.out.println("Hello World!");
        Board myBoard = new Board("myBoard");
        myBoard.print();
        Board myOtherBoard = new Board("myOtherBoard", 15);
        myOtherBoard.print();
    }
}
