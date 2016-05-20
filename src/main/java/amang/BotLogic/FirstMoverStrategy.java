package amang.BotLogic;

import amang.Playboard;
import java.util.Random;

public class FirstMoverStrategy implements IBotStrategy {
    private Playboard mPlayboard;

    public FirstMoverStrategy(Playboard playboard) {
        mPlayboard = playboard;
    }

    @Override
    public int makeTurn() {
        int move = new Random().nextInt(7);
        return move;
    }
}
