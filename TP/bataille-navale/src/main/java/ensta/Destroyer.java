package ensta;

import java.nio.channels.spi.AbstractSelectableChannel;

/**
 * Destroyer
 */
public class Destroyer extends AbstractShip {

    public Destroyer(Direction direction) {
        super('D', "Destroyer", 2, direction);
    }
    public Destroyer() {
        this(Direction.EAST);
    }


}