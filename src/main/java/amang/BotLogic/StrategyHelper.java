package amang.BotLogic;

import amang.Playboard.Playboard;
import amang.Playboard.PlayboardPatternSearch;
import amang.Playboard.SearchResult;

import java.util.Random;

public class StrategyHelper {
    private Playboard mPlayboard;
    private int mLastMove;

    public StrategyHelper(Playboard playboard) {
        mPlayboard = playboard;
        mLastMove = -1;
    }

    public int findNextMove(int botId) {
        SearchResult searchResult = mPlayboard.getPlayboardPatternSearch().searchVerticalWinnerColumn();
        if(searchResult.getColumnId() != PlayboardPatternSearch.COLUMN_NOT_FOUND){
            return searchResult.getColumnId();
        }

        searchResult = mPlayboard.getPlayboardPatternSearch().searchHorizontalWinnerColumn();
        if(searchResult.getColumnId() != PlayboardPatternSearch.COLUMN_NOT_FOUND){
            return searchResult.getColumnId();
        }

        int topRowValue = mPlayboard.getPlayboardPatternSearch().getTopRowValue(mLastMove);
        if(topRowValue == botId) {
            if(trackValidateMove(mLastMove)){
                return mLastMove;
            }
        }

        return findRandomMove();
    }

    public int findRandomMove() {
        int randomMove = makeRandomMove();
        while(!trackValidateMove(randomMove)) {
            randomMove = makeRandomMove();
        }

        return randomMove;
    }

    public boolean trackValidateMove(int targetColumn) {
        if(mPlayboard.isColumnFull(targetColumn)) {
            return false;
        }

        mLastMove = targetColumn;
        return true;
    }

    public int makeRandomMove() {
        int move = new Random().nextInt(7);
        return move;
    }
}
