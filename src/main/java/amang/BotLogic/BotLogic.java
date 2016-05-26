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
        if(botId == STARTER_BOT_ID) {
            mBotStrategy = new FirstMoverStrategy(botId, mPlayboard, new StrategyHelper(mPlayboard));
        } else {
            mBotStrategy = new SecondMoverStrategy(botId, mPlayboard, new StrategyHelper(mPlayboard));
        }
    }

    public int makeTurn() {
        return mBotStrategy.makeTurn();
    }
}
