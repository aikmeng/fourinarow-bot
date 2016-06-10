package amang.BotLogic;

import amang.Playboard.Playboard;
import amang.Playboard.SearchResult;

public class MinMaxStrategy implements IBotStrategy {
    private int mBotId;
    private int mOpponentBotId;
    private Playboard mPlayboard;
    public static final int WINNING_COLUMN_SCORE = 2100;
    public static final int LOSING_COLUMN_SCORE = -2100;
    public static final int DEPTH_COLUMN_SCORE = 100;
    public static final int INVALID_COLUMN_ID = -1;
    public static final int MAX_SEARCH_DEPTH = 3;
    public static final int FIRST_BOT_ID = 1;
    public static final int SECOND_BOT_ID = 2;

    public MinMaxStrategy(int botId, Playboard playboard) {
        mBotId = botId;
        mPlayboard = playboard;

        mOpponentBotId = FIRST_BOT_ID;
        if(mBotId != SECOND_BOT_ID) {
            mOpponentBotId = SECOND_BOT_ID;
        }
    }

    @Override
    public int makeTurn() {
        int maxColumn = mPlayboard.getNrColumns();

        int currentHighestScore = LOSING_COLUMN_SCORE;
        int highestScoreColumnId = INVALID_COLUMN_ID;
        int currentSearchDepth = 0;

        for(int currentColumnId = 0; currentColumnId < maxColumn; currentColumnId++) {
            if (mPlayboard.isColumnFull(currentColumnId)) {
                continue;
            }

            int currentColumnScore = CalculateColumnScore(maxColumn, currentColumnId, currentSearchDepth, mPlayboard);

            if(currentHighestScore < currentColumnScore) {
                currentHighestScore = currentColumnScore;
                highestScoreColumnId = currentColumnId;
            }
        }

        return highestScoreColumnId;
    }

    private int CalculateColumnScore(int maxColumn, int columnId, int currentSearchDepth, Playboard previousPlayboard) {
        // Hit current depth limit, return depth score
        if(currentSearchDepth > MAX_SEARCH_DEPTH) {
            return DEPTH_COLUMN_SCORE * currentSearchDepth;
        }

        SearchResult searchResult = previousPlayboard.getPlayboardPatternSearch().searchWinnerColumn(columnId);
        if(searchResult.getBotId() == mBotId) {
            return WINNING_COLUMN_SCORE - (DEPTH_COLUMN_SCORE * currentSearchDepth);
        } else if(searchResult.getBotId() == mOpponentBotId) {
            return LOSING_COLUMN_SCORE + (DEPTH_COLUMN_SCORE * currentSearchDepth);
        }

        // Winner not found, apply current column id
        Playboard currentPlayboard = new Playboard(previousPlayboard);
        currentPlayboard.addDisc(columnId, mBotId);

        // Apply opponent bot id, search recursively
        int accumulatedScore = 0;
        for(int currentColumnId = 0; currentColumnId < maxColumn; currentColumnId++) {
            if (currentPlayboard.isColumnFull(currentColumnId)) {
                continue;
            }

            Playboard nextPlayboard = new Playboard(currentPlayboard);
            if (!nextPlayboard.addDisc(currentColumnId, mOpponentBotId)) {
                continue;
            }

            //Check for bot winner after apply opponent change
            for(int nextColumnId = 0; nextColumnId < maxColumn; nextColumnId++) {
                if (nextPlayboard.isColumnFull(nextColumnId)) {
                    continue;
                }

                accumulatedScore += CalculateColumnScore(maxColumn, nextColumnId, currentSearchDepth++, nextPlayboard);
            }
        }

        return accumulatedScore;
    }
}
