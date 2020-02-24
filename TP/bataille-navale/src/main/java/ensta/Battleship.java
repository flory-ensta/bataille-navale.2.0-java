package ensta;

/**
 * Battleship
 */
public class Battleship extends AbstractShip {
    public Battleship(Direction direction) {
        super('B', "Battleship", 4, direction);
    }
    public Battleship() {
        this(Direction.EAST);
    }
}