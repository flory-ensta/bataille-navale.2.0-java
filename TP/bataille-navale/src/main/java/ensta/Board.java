package ensta;

import java.io.PrintStream;

public class Board {
    private Character[] charArray = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    protected String name;
    protected ShipState[][] boats_array;
    protected Boolean[][] hits_array;
    protected int size;

    public Board(String name, int size) { // Check size < 26 later)
        this.name = name;
        this.boats_array = new ShipState[size][size];
        this.hits_array = new Boolean[size][size];
        this.size = size;
    }

    public Board(String name) {
        this(name,10);
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
                if (hits_array[i - 1][j - 1]==null)
                    System.out.print(". ");
                if (!hits_array[i - 1][j - 1])
                    System.out.print("x ");
                else 
                    System.out.print(ColorUtil.colorize("x ",ColorUtil.Color.RED));
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

    public static void main(String[] args) {
    }
}