package ensta;

import java.util.List;
import java.util.ArrayList;
import ensta.BattleShipsAI;

/**
 * TestGame
 */
public class TestGame {
    public static void main(String[] args) {

        int[] n = new int[1]; 
        Game newGame = new Game();
        newGame = newGame.init(n);
        newGame.run(n);
    }
}