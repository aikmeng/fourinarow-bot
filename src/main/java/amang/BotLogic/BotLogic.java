package amang.BotLogic;

import amang.Playboard.Playboard;

public class BotLogic {
    private static final int STARTER_BOT_ID = 1;

    private Playboard mPlayboard;
    private OffensiveStrategy mBotStrategy;

    public BotLogic(Playboard playboard) {
        mPlayboard = playboard;
    }

    public void decideStrategy(int botId) {
        mBotStrategy = new OffensiveStrategy(botId, mPlayboard, new StrategyHelper(mPlayboard));
    }

    public int makeTurn() {
        return mBotStrategy.makeTurn();
    }
}
