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
            mBotStrategy = new FirstMoverStrategy(mPlayboard);
        } else {
            mBotStrategy = new SecondMoverStrategy(mPlayboard);
        }
    }

    public int makeTurn() {
        return mBotStrategy.makeTurn();
    }
}
