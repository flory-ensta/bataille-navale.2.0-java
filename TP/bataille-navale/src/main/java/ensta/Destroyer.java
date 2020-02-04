package ensta;

import java.nio.channels.spi.AbstractSelectableChannel;

/**
 * Destroyer
 */
public class Destroyer extends AbstractShip {

    public Destroyer() {
        this.label = 'D';
        this.name = "Destroyer";
        this.size = 2;
        this.direction = EAST;
    }

    public Destroyer(DIrection direction) {
        Destroyer();
        this.direction = direction;
    }

}