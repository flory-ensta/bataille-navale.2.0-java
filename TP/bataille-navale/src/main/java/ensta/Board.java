package ensta;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class Board {
    private Character[] charArray = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    protected String name;
    protected Character[][] boats_array;
    protected boolean[][] hits_array;
    protected int size;

    public Board(String name, int size) { // Check size < 26 later)
        this.name = name;
        this.boats_array = new Character[size][size];
        this.hits_array = new boolean[size][size];
        this.size = size;
    }

    public Board(String name) {
        this(name, 10);
    }

    public int getGridSize() {
        return this.size;
    }

    private void printFirstLine() {
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.print(charArray[i].toString() + " ");
        }
        System.out.println("");
    }

    // private initGrids() {
    // for (int i = 0; i < size; i++)
    // for (int j = 0; j < size; j++) {
    // hits_array[i][j] = false;
    // boats_array[i][j] = '.';
    // }
    // }

    private void printBoats() {
        printFirstLine();
        for (int i = 1; i <= size; i++) {
            if (i < 10)
                System.out.print(i + "  ");
            else
                System.out.print(i + " ");
            for (int j = 1; j <= size; j++) {
                System.out.print(". ");
            }
            System.out.println("");
        }
    }

    private void printHits() {
        printFirstLine();
        for (int i = 1; i <= size; i++) {
            if (i < 10)
                System.out.print(i + "  ");
            else
                System.out.print(i + " ");
            for (int j = 1; j <= size; j++) {
                if (!hits_array[i - 1][j - 1])
                    System.out.print(". ");
                if (hits_array[i - 1][j - 1])
                    System.out.print("x ");
            }
            System.out.println("");
        }
    }

    public void printBoards() {
        System.out.print("Navires :");
        for (int i = 1; i < size - 1; i++) {
            System.out.print("  ");
        }
        System.out.println(" Frappes :");
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.print(charArray[i].toString() + " ");
        }
        System.out.print("   ");
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.print(charArray[i].toString() + " ");
        }
        System.out.println("");

        for (int i = 1; i < size + 1; i++) {
            if (i < 10)
                System.out.print(i + "  ");
            else
                System.out.print(i + " ");
            for (int j = 0; j < size; j++) {
                if (boats_array[i - 1][j] == null)
                    System.out.print(". ");
                else
                    System.out.print(boats_array[i - 1][j] + " ");
            }
            System.out.print("   ");
            if (i < 10)
                System.out.print(i + "  ");
            else
                System.out.print(i + " ");
            for (int j = 0; j < size; j++) {
                if (!hits_array[i - 1][j])
                    System.out.print(". ");
                else
                    System.out.print("x ");
            }
            System.out.println("");
        }

    }

    public void print() {
        System.out.println("Navires :");
        printBoats();
        System.out.println("Frappes :");
        printHits();
    }

    public int getSize() {
        return this.size;
    }

    void putShip(AbstractShip ship, int x, int y) throws OutOfBound, IncorrectPosition {
        if (x < 0 || x >= size || y < 0 || y >= size)
            throw new OutOfBound("Positions incorrectes");
        switch (ship.getDirection()) {
        case NORTH:
            if (x - ship.getSize() + 1 < 0)
                throw new OutOfBound("Positions incorrectes");
            for (int i = 0; i < ship.getSize(); i++)
                if (this.boats_array[x - i][y] != null)
                    throw new IncorrectPosition("A ship is already at this position");
            for (int i = 0; i < ship.getSize(); i++)
                this.boats_array[x - i][y] = ship.getLabel();
            break;
        case SOUTH:
            if (x + ship.getSize() - 1 >= size)
                throw new OutOfBound("Positions incorrectes");
            for (int i = 0; i < ship.getSize(); i++)
                if (this.boats_array[x + i][y] != null)
                    throw new IncorrectPosition("A ship is already at this position");
            for (int i = 0; i < ship.getSize(); i++)
                this.boats_array[x + i][y] = ship.getLabel();
            break;
        case WEST:
            if (y - ship.getSize() + 1 < 0)
                throw new OutOfBound("Positions incorrectes");
            for (int i = 0; i < ship.getSize(); i++)
                if (this.boats_array[x][y - 1] != null)
                    throw new IncorrectPosition("A ship is already at this position");
            for (int i = 0; i < ship.getSize(); i++)
                this.boats_array[x][y - i] = ship.getLabel();
            break;

        case EAST:
            if (y + ship.getSize() - 1 >= size)
                throw new OutOfBound("Positions incorrectes");
            for (int i = 0; i < ship.getSize(); i++)
                if (this.boats_array[x][y + i] != null)
                    throw new IncorrectPosition("A ship is already at this position");
            for (int i = 0; i < ship.getSize(); i++)
                this.boats_array[x][y + i] = ship.getLabel();
            break;

        }

    }

    boolean hasShip(int x, int y) throws OutOfBound {
        if (x < 0 || x >= size || y < 0 || y >= size)
            throw new OutOfBound("Positions incorrectes");
        return (boats_array[x][y] != null);
    }

    void setHit(boolean hit, int x, int y) throws OutOfBound {
        if (x < 0 || x >= size || y < 0 || y >= size)
            throw new OutOfBound("Positions incorrectes");
        hits_array[x][y] = hit;
    }

    boolean getHit(int x, int y) throws OutOfBound {
        if (x < 0 || x >= size || y < 0 || y >= size)
            throw new OutOfBound("Positions incorrectes");
        return hits_array[x][y];
    }

    public static void main(String[] args) {
    }
}