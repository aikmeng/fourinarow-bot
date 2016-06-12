package amang.BotLogic;

import amang.Playboard.Playboard;

public class BotLogic {
    private Playboard mPlayboard;
    private IBotStrategy mBotStrategy;

    public BotLogic(Playboard playboard) {
        mPlayboard = playboard;
    }

    public void decideStrategy(int botId) {
        mBotStrategy = new MinMaxStrategy(botId, mPlayboard);
    }

    public int makeTurn() {
        return mBotStrategy.makeTurn();
    }
}
