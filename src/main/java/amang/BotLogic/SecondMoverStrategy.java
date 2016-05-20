package amang.BotLogic;

import amang.Playboard;
import java.util.Random;

// Defensive Strategy
public class SecondMoverStrategy implements IBotStrategy {
    private Playboard mPlayboard;
    private int mBotId;

    public SecondMoverStrategy(Playboard playboard, int botId) {
        mPlayboard = playboard;
        mBotId = botId;
    }

    @Override
    public void updateRound(int round) {
    }

    @Override
    public int makeTurn() {
        int move = new Random().nextInt(7);
        return move;
    }
}
