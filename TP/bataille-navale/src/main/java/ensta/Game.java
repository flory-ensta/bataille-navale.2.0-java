package ensta;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    /*
     * *** Constante
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    /*
     * *** Attributs
     */
    private Player player1;
    private Player player2;
    private Scanner sin;

    /*
     * *** Constructeurs
     */
    public Game() {
    }

    public Game init() {
        if (!loadSave()) {
            // init attributes
            System.out.println("entre ton nom:");

            // Done use a scanner to read player name
            String username = sin.nextLine();

            // Done init boards
            Board b1, b2;
            b1 = new Board(username, 15);
            b2 = new Board("AI", 15);
            // Done init this.player1 & this.player2

            // // Init player 1 
            /// !!!!!!!!!!!!!! USE LA FONCTION DU BAS DE LA PAGE //////
            // AbstractShip myShip1 = new Battleship();
            // AbstractShip myShip2 = new Carrier();
            // AbstractShip myShip3 = new Submarine();
            // AbstractShip myShip4 = new Submarine();
            // AbstractShip myShip5 = new Destroyer();
            // List<AbstractShip> ships1 = new ArrayList<AbstractShip>();
            // ships1.add(myShip1);
            // ships1.add(myShip2);
            // ships1.add(myShip3);
            // ships1.add(myShip4);
            // ships1.add(myShip5);
            // player1 = new Player(b1, b2, ships1);
            
            // // Init player 2 (AI)
            // AbstractShip aiShip1 = new Battleship();
            // AbstractShip aiShip2 = new Carrier();
            // AbstractShip aiShip3 = new Submarine();
            // AbstractShip aiShip4 = new Submarine();
            // AbstractShip aiShip5 = new Destroyer();
            // List<AbstractShip> ships2 = new ArrayList<AbstractShip>();
            // ships2.add(aiShip1);
            // ships2.add(aiShip2);
            // ships2.add(aiShip3);
            // ships2.add(aiShip4);
            // ships2.add(aiShip5);
            // player2 = new AIPlayer(b2, b1, ships2);

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
        Hit hit;

        // main loop
        b1.print();
        boolean done;
        do {
            hit = Hit.MISS; // Done player1 send a hit
            // hit = player1.sendHit(coords);
            boolean strike = hit != Hit.MISS; // Done set this hit on his board (b1)
            // b1.setHit(hit,coords[0],coords[1]);

            done = updateScore();
            b1.print();
            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

            save();

            if (!done && !strike) {
                do {
                    hit = Hit.MISS; // Done player2 send a hit.
                    // player2.sendHit(coords);

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
        // try {
        //     // TODO bonus 2 : uncomment
        //     // if (!SAVE_FILE.exists()) {
        //     // SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
        //     // }

        //     // TODO bonus 2 : serialize players

        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }

    private boolean loadSave() {
        // if (SAVE_FILE.exists()) {
        //     try {
        //         // TODO bonus 2 : deserialize players

        //         return true;
        //     } catch (IOException | ClassNotFoundException e) {
        //         e.printStackTrace();
        //     }
        // }
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
                (coords[1] + 1), msg);
        return ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new Battleship(),
                new Carrier() });
    }
}