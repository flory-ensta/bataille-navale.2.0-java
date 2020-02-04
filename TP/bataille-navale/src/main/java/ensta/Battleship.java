package ensta;

/**
 * Battleship
 */
public class Battleship extends AbstractShip {
    public Battleship() {
        this.label = 'B';
        this.name = "Battleship";
        this.size = 4;
        this.direction = EAST;
    }

    public Battleship(Direction direction) {
        this.direction = direction;
    }

}