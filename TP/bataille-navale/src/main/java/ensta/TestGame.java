package ensta;

import java.util.List;
import java.util.ArrayList;
import ensta.BattleShipsAI;

/**
 * TestGame
 */
public class TestGame {
    public static void main(String[] args) {
        Board board = new Board("AI Board", 15);
        board.printBoards();
        List<AbstractShip> ships = new ArrayList<AbstractShip>();
        ships.add(new Battleship());
        ships.add(new Carrier());
        ships.add(new Submarine());
        ships.add(new Destroyer());
        BattleShipsAI AI = new BattleShipsAI(board, board);
        int destroyedShip = 0;
        Hit hit;
        int[] coords;
        while (destroyedShip < 4) {
            hit = AI.sendHit(coords);
            System.out.println("Hit on" + Board.displayCell(coords) + " : " + hit);
            board.printBoards();
            if (hit.getValue() > 0) {
                destroyedShip++;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}