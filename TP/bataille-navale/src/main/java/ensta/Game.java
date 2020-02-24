package ensta;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game implements java.io.Serializable {

    /*
     * *** Constante
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    private static final long serialVersionUID = 1L;

    /*
     * *** Attributs
     */
    private Player player1;
    private Player player2;
    private transient Scanner sin;

    /*
     * *** Constructeurs
     */
    public Game() {
    }

    public Game init() {
        if (!loadSave()) {
            // init attributes
            sin = new Scanner(System.in);
            System.out.println("entre ton nom:");

            // DONE use a scanner to read player name
            String username = sin.nextLine();

            // DONE init boards
            Board b1, b2;
            b1 = new Board(username, 15);
            b2 = new Board("AI", 15);
            // DONE init this.player1 & this.player2
            List<AbstractShip> ships1 = createDefaultShips();
            this.player1 = new Player(b1, b2, ships1);
            List<AbstractShip> ships2 = createDefaultShips();
            this.player2 = new AIPlayer(b2, b1, ships2);

            System.out.println("Bonne chance " + username);
            b1.print();
            // place player ships
            player1.putShips();
            player2.putShips();
        }
        return this;
    }

    /*
     * *** Méthodes
     */
    public void run() {
        int[] coords = new int[2];
        Board b1 = player1.board;
        Board b2 = player2.board; //// TODO Remove this
        Hit hit;

        // main loop
        b1.print();
        boolean done;
        do {
            hit = Hit.MISS; // DONE player1 send a hit
            b2.print(); // TODO Remove
            hit = player1.sendHit(coords); // PB écrit aussi dans l'opponent board

            boolean strike = hit != Hit.MISS; // DONE set this hit on his board (b1)
            try {
                b1.setHit(strike, coords[1], coords[0]);
            } catch (Exception e) {
                System.out.println(e);
            }

            done = updateScore();
            b1.print();
            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

            save();

            if (!done && !strike) {
                do {
                    hit = Hit.MISS; // DONE player2 send a hit.
                    hit = player2.sendHit(coords); // Pb d'IA, elle se focus sur un truc alors qu'il est coulé

                    strike = hit != Hit.MISS;
                    if (strike) {
                        b1.print();
                    }
                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                    done = updateScore();

                    if (!done) {
                        save();
                    }
                } while (strike && !done);
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        sin.close();
    }

    private void save() {
        try {
            // TODO bonus 2 : uncomment
            if (!SAVE_FILE.exists()) {
                SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
            }

            // TODO bonus 2 : serialize players
            FileOutputStream file = new FileOutputStream(SAVE_FILE);

            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
            file.close();

            System.out.println("La partie a été sauvegardée");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean loadSave() {
        if (SAVE_FILE.exists()) {
            try {
                // TODO bonus 2 : deserialize players
                FileInputStream file = new FileInputStream(SAVE_FILE);
                ObjectInputStream in = new ObjectInputStream(file);
                Game gameSaved = null;
                gameSaved = (Game) in.readObject();
                in.close();
                file.close();

                this.player1 = gameSaved.player1;
                this.player2 = gameSaved.player2;

                gameSaved = null;
                System.out.println("La partie a été chargée");

                return true;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean updateScore() {
        for (Player player : new Player[] { player1, player2 }) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
            case MISS:
                msg = hit.toString();
                break;
            case STIKE:
                msg = hit.toString();
                color = ColorUtil.Color.RED;
                break;
            default:
                msg = hit.toString() + " coulé";
                color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>", ((char) ('A' + coords[0])),
                (coords[1] + 1), msg); /////////////// WE INVERTED THERE On a le problème suivant : Les coordonnées
                                       /////////////// écrites ici ne correspondent que pour l'un des deux joueurs. Et
                                       /////////////// les hits apparaissent en trop sur l'opponent board.
        return ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new Battleship(),
                new Carrier() });
    }

}