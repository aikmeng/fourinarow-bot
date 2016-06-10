package amang.BotLogic;

import amang.Playboard.Playboard;

public class BotLogic {
    private static final int STARTER_BOT_ID = 1;

    private Playboard mPlayboard;
    private IBotStrategy mBotStrategy;

    public BotLogic(Playboard playboard) {
        mPlayboard = playboard;
    }

    public void decideStrategy(int botId) {
        //mBotStrategy = new SimpleStrategy(botId, mPlayboard, new StrategyHelper(mPlayboard));
        mBotStrategy = new MinMaxStrategy(botId, mPlayboard);
    }

    public int makeTurn() {
        return mBotStrategy.makeTurn();
    }
}
