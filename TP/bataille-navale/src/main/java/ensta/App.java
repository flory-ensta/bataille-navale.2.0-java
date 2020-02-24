package ensta;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        int[] n = new int[1];
        Game newGame = new Game();
        newGame = newGame.init(n);
        newGame.run(n);
    }
}
