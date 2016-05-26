package amang.BotLogic;

import amang.Playboard.Playboard;
import amang.Playboard.PlayboardPatternSearch;
import amang.Playboard.SearchResult;

// Offensive Strategy
public class FirstMoverStrategy implements IBotStrategy {
    private int mBotId;
    private boolean isFirstMove;
    private Playboard mPlayboard;
    private StrategyHelper mStrategyHelper;

    public FirstMoverStrategy(int botId, Playboard playboard, StrategyHelper strategyHelper) {
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
        SearchResult searchResult = mPlayboard.getPlayboardPatternSearch().searchHorizontalWinnerColumn();
        if(searchResult.getColumnId() != PlayboardPatternSearch.COLUMN_NOT_FOUND){
            return searchResult.getColumnId();
        }

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
