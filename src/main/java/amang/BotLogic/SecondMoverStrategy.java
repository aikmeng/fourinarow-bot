package amang.BotLogic;

import amang.Playboard.Playboard;
import amang.Playboard.PlayboardPatternSearch;
import amang.Playboard.SearchResult;

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
        SearchResult searchResult = mPlayboard.getPlayboardPatternSearch().searchHorizontalWinnerColumn();
        if(searchResult.getColumnId() != PlayboardPatternSearch.COLUMN_NOT_FOUND){
            return searchResult.getColumnId();
        }

        int blockingColumn = mPlayboard.getPlayboardPatternSearch().getColumnTopRowWithDifferentBotId(mBotId);
        if (blockingColumn != PlayboardPatternSearch.COLUMN_NOT_FOUND) {
            mStrategyHelper.trackValidateMove(blockingColumn);
            return blockingColumn;
        }

        int buildingColumn = mPlayboard.getPlayboardPatternSearch().getColumnTopRowWithSameBotId(mBotId);
        if (buildingColumn != PlayboardPatternSearch.COLUMN_NOT_FOUND) {
            mStrategyHelper.trackValidateMove(buildingColumn);
            return buildingColumn;
        }

        return mStrategyHelper.findRandomMove();
    }
}
