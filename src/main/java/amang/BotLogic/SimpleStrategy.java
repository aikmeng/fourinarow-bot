package amang.BotLogic;

import amang.Playboard.Playboard;

public class SimpleStrategy implements IBotStrategy {
    private int mBotId;
    private boolean isFirstMove;
    private Playboard mPlayboard;
    private StrategyHelper mStrategyHelper;

    public SimpleStrategy(int botId, Playboard playboard, StrategyHelper strategyHelper) {
        mBotId = botId;
        mPlayboard = playboard;
        mStrategyHelper = strategyHelper;
        isFirstMove = true;
    }

    @Override
    public int makeTurn() {
        if (isFirstMove) {
            isFirstMove = false;
            return firstRoundMove();
        }

        return findNextMove();
    }

    private int firstRoundMove() {
        int nextMove = mPlayboard.getMiddleColumn();
        mStrategyHelper.trackValidateMove(nextMove);
        return nextMove;
    }

    private int findNextMove() {
        return mStrategyHelper.findNextMove(mBotId);
    }
}
