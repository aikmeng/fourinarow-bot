package amang.BotLogic;

import amang.Playboard.Playboard;

// Defensive Strategy
public class SecondMoverStrategy implements IBotStrategy {
    private int mBotId;
    private int mCurrentRound;
    private Playboard mPlayboard;
    private StrategyHelper mStrategyHelper;

    public SecondMoverStrategy(int botId, Playboard playboard, StrategyHelper strategyHelper) {
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
        int blockingColumn = mPlayboard.getPlayboardPatternSearch().getColumnTopRowWithDifferentBotId(mBotId);
        if (blockingColumn != -1) {
            mStrategyHelper.trackValidateMove(blockingColumn);
            return blockingColumn;
        }

        int buildingColumn = mPlayboard.getPlayboardPatternSearch().getColumnTopRowWithSameBotId(mBotId);
        if (buildingColumn != -1) {
            mStrategyHelper.trackValidateMove(buildingColumn);
            return buildingColumn;
        }

        return mStrategyHelper.findRandomMove();
    }
}
