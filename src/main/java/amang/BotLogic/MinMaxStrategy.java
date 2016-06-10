package amang.BotLogic;

import amang.Playboard.Playboard;
import amang.Playboard.SearchResult;

public class MinMaxStrategy implements IBotStrategy {
    private int mBotId;
    private boolean isFirstMove;
    private Playboard mPlayboard;
    private StrategyHelper mStrategyHelper;
    public static final int WINNING_COLUMN_SCORE = 1000;
    public static final int LOSING_COLUMN_SCORE = -1000;
    public static final int DEFAULT_COLUMN_SCORE = 0;
    public static final int INVALID_COLUMN_ID = -1;
    public static final int MAX_SEARCH_DEPTH = 3;

    public MinMaxStrategy(int botId, Playboard playboard, StrategyHelper strategyHelper) {
        mBotId = botId;
        mPlayboard = playboard;
        mStrategyHelper = strategyHelper;
        isFirstMove = true;
    }

    @Override
    public int makeTurn() {
        int maxColumn = mPlayboard.getNrColumns();

        int currentHighestScore = LOSING_COLUMN_SCORE;
        int highestScoreColumnId = INVALID_COLUMN_ID;

        for(int columnId = 0; columnId < maxColumn; columnId++) {
            if (mPlayboard.isColumnFull(columnId)) {
                continue;
            }

            int currentColumnScore = CalculateColumnScore(columnId);

            if(currentHighestScore < currentColumnScore) {
                currentHighestScore = currentColumnScore;
                highestScoreColumnId = columnId;
            }
        }

        return highestScoreColumnId;
    }

    private int CalculateColumnScore(int columnId) {
        Playboard currentPlayboard = new Playboard(mPlayboard);
        int currentSearchDepth = 0;

        return CalculateColumnScore(columnId, currentSearchDepth, currentPlayboard, 0);
    }

    private int CalculateColumnScore(int columnId, int currentSearchDepth, Playboard currentPlayboard, int currentPathScore) {
        if(currentSearchDepth > MAX_SEARCH_DEPTH) {
            return currentPathScore + DEFAULT_COLUMN_SCORE;
        }

        SearchResult searchResult = currentPlayboard.getPlayboardPatternSearch().searchWinnerColumn(columnId);
        if(searchResult.getBotId() == mBotId) {
            return WINNING_COLUMN_SCORE;
        } else if(searchResult.getBotId() != mBotId) {
            return LOSING_COLUMN_SCORE;
        }

        return CalculateColumnScore(columnId, currentSearchDepth++, currentPlayboard, currentPathScore);
    }
}
