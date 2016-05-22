package amang.BotLogic;

import amang.Playboard;

public class BotLogic {
    private Playboard mPlayboard;
    private IBotStrategy mBotStrategy;

    public BotLogic(Playboard playboard) {
        mPlayboard = playboard;
    }

    public void decideStrategy(int botId) {
        if(botId == 1) {
            mBotStrategy = new FirstMoverStrategy(botId, mPlayboard, new StrategyHelper(mPlayboard));
        } else {
            mBotStrategy = new SecondMoverStrategy(botId, mPlayboard, new StrategyHelper(mPlayboard));
        }
    }

    public void updateRound(int round) {
        mBotStrategy.updateRound(round);
    }

    public int makeTurn() {
        return mBotStrategy.makeTurn();
    }
}
