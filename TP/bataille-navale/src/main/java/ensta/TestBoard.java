package ensta;

import java.util.List;
import java.util.ArrayList;

public class TestBoard {
    public static void main(String[] args) {
        // Board myBoard = new Board("myBoard");
        Board oponnentBoard = new Board("oponnentBoard", 15);
        Board myOtherBoard = new Board("myOtherBoard", 15);
        AbstractShip myShip = new Battleship(Direction.SOUTH);
        AbstractShip myShip2 = new Carrier(Direction.WEST);
        AbstractShip myShip3 = new Submarine(Direction.EAST);
        AbstractShip myShip4 = new Destroyer(Direction.NORTH);
        List<AbstractShip> ships = new ArrayList<AbstractShip>();
        ships.add(myShip);
        ships.add(myShip2);
        ships.add(myShip3);
        ships.add(myShip4);

        Player player = new Player(myOtherBoard, oponnentBoard, ships);
        player.putShips();
        myOtherBoard.printBoards();
        int[] coords = new int[2];
        player.sendHit(coords);
        System.out.println(coords[0]+ " " + coords[1]);

        oponnentBoard.printBoards();

    }
}