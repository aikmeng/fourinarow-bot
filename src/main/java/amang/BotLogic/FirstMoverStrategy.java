package amang.BotLogic;

import amang.Playboard.Playboard;

// Offensive Strategy
public class FirstMoverStrategy implements IBotStrategy {
    private static final int FIRST_ROUND = 1;

    private int mBotId;
    private int mCurrentRound;

    private Playboard mPlayboard;
    private StrategyHelper mStrategyHelper;

    public FirstMoverStrategy(int botId, Playboard playboard, StrategyHelper strategyHelper) {
        mBotId = botId;
        mPlayboard = playboard;
        mStrategyHelper = strategyHelper;
    }

    @Override
    public void updateRound(int round) {
        mCurrentRound = round;
    }

    @Override
    public int makeTurn() {
        if (mCurrentRound == FIRST_ROUND) {
            return firstRoundMove();
        } else {
            return findNextMove();
        }
    }

    private int firstRoundMove() {
        int nextMove = mPlayboard.getMiddleColumn();
        mStrategyHelper.trackValidateMove(nextMove);
        return nextMove;
    }

    private int findNextMove() {
        int lastMove = mStrategyHelper.getLastMove();
        int topRowValue = mPlayboard.getPlayboardPatternSearch().getTopRowValue(lastMove);
        if(topRowValue == mBotId) {
            if(mStrategyHelper.trackValidateMove(lastMove)){
                return lastMove;
            }
        }

        return mStrategyHelper.findRandomMove();
    }
}
