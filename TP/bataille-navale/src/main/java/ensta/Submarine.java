package ensta;

/**
 * Submarine
 */
public class Submarine extends AbstractShip {
    public Submarine(Direction direction) {
        super('S', "Submarine", 3, direction);
    }
    public Submarine() {
        this(Direction.EAST);
    }

}