package ensta;

/**
 * Submarine
 */
public class Submarine extends AbstractShip {
    public Submarine() {
        this.label = 'S';
        this.name = "Submarine";
        this.size = 3;
        this.direction = EAST;
    }

    public Submarine(Direction direction) {
        this.direction = direction;
    }
}