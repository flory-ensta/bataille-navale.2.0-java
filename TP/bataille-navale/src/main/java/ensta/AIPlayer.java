package ensta;

import java.io.Serializable;
import java.util.List;

public class AIPlayer extends Player {
    /*
     * ** Attribut
     */
    private BattleShipsAI ai;

    /*
     * ** Constructeur
     */
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    @Override
    public void putShips() {
        ai.putShips(this.ships);
    }

    @Override
    public Hit sendHit(int[] coords) {
        return ai.sendHit(coords);
    }
    // TODO AIPlayer must not inherit "keyboard behavior" from player. Call ai
    // instead.
}
