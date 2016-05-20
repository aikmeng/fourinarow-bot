package amang;

import java.util.Random;

public class BotLogic {
    private Playboard mPlayboard;

    public BotLogic(Playboard playboard) {
        mPlayboard = playboard;
    }

    public int makeTurn() {
        int move = new Random().nextInt(7);
        return move;
    }
}
