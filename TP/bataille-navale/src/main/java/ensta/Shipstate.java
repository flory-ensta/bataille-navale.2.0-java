package ensta;

public class ShipState {
    private AbstractShip ship;
    private boolean struck;

    public void addStrike() {
        if (!struck)
            ship.addStrike();
        struck = true;
    }

    public boolean isStruck() {
        return struck;
    }

    public String toString() {
        if (struck)
            System.out.print(ColorUtil.colorize(ship.getLabel(), ColorUtil.Color.RED));
        else
            System.out.print(ship.getLabel());
    }

    public boolean isSunk() {
        return ship.isSunk();
    }
    public AbstractShip getShip() {
        return ship;
    }
}