package ensta;

/**
 * Carrier
 */
public class Carrier extends AbstractShip {
    public Carrier(Direction direction) {
        super('C', "Aircraft Carrier", 5, direction);
    }
    public Carrier() {
        this(Direction.EAST);
    }

}