package amang.BotLogic;

import amang.Playboard;
import java.util.Random;

public class SecondMoverStrategy implements IBotStrategy {
    private Playboard mPlayboard;

    public SecondMoverStrategy(Playboard playboard) {
        mPlayboard = playboard;
    }

    @Override
    public int makeTurn() {
        int move = new Random().nextInt(7);
        return move;
    }
}
