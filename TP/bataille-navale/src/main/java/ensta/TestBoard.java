package ensta;

public class TestBoard {
    public static void main(String[] args) {
        // Board myBoard = new Board("myBoard");
        Board myOtherBoard = new Board("myOtherBoard", 15);
        AbstractShip myShip = new Battleship(Direction.SOUTH);
        AbstractShip myShip2 = new Carrier(Direction.WEST);
        AbstractShip myShip3 = new Submarine(Direction.EAST);
        AbstractShip myShip4 = new Destroyer(Direction.NORTH);
        try {
            myOtherBoard.putShip(myShip, 12, 7);
            myOtherBoard.putShip(myShip2, 12, 2);
            myOtherBoard.putShip(myShip3, 1, 3);
            myOtherBoard.putShip(myShip4, 2, 10);
            System.out.println(myOtherBoard.hasShip(1, 0));
            System.out.println(myOtherBoard.hasShip(1, 1));
            // System.out.println(myOtherBoard.hasShip(1, 15));
            for (int i = 0; i < 15; i++) {
                myOtherBoard.setHit((myOtherBoard.hasShip(1, i)), 1, i);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(myShip.toString());
        myOtherBoard.printBoards();
    }
}