package ensta;

/**
 * Carrier
 */
public class Carrier extends AbstractShip {
    public Carrier() {
        this.label = 'C';
        this.name = "Aircraft Carrier";
        this.size = 5;
        this.direction = EAST;
    }

    public Carrier(Direction direction) {
        this.direction = direction;
    }
}