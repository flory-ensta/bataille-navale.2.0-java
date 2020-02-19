package ensta;

public class ShipState {
    private AbstractShip ship;
    private boolean struck;

    public ShipState() {
        // this.ship = new AbstractShip(); On ne l'initialise pas et on le laisse à null si pas de bateau.
        this.struck = false;
    }

    public ShipState(AbstractShip ship) {
        this.ship = ship;
        this.struck = false;
    }

    public void addStrike() {
        if (!struck)
            ship.addStrike();
        struck = true;
    }

    public boolean isStruck() {
        return struck;
    }

    public String toString() {
        if (ship != null) {
            if (struck)
                return ColorUtil.colorize(ship.getLabel(), ColorUtil.Color.RED);
            else
                return ColorUtil.colorize(ship.getLabel(), ColorUtil.Color.WHITE);
        } else
            return ".";
    }

    public boolean isSunk() {
        return ship.isSunk();
    }

    public void setShip(AbstractShip ship) {
        this.ship = ship;
    }

    public AbstractShip getShip() {
        return ship;
    }
}