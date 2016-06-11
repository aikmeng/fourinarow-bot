package amang.BotLogic;

import amang.Playboard.Playboard;
import amang.Playboard.SearchResult;

public class MinMaxStrategy implements IBotStrategy {
    private int mBotId;
    private int mOpponentBotId;
    private int mCurrentSearchDepth;
    private Playboard mPlayboard;
    
    public static final int TURN_SCORE = 10;
    public static final int INVALID_COLUMN_ID = -1;
    public static final int MAX_SEARCH_DEPTH = 3;
    public static final int FIRST_BOT_ID = 1;
    public static final int SECOND_BOT_ID = 2;

    public MinMaxStrategy(int botId, Playboard playboard) {
        System.err.println("Using new strategy");

        mBotId = botId;
        mPlayboard = playboard;

        mOpponentBotId = FIRST_BOT_ID;
        if(mBotId != SECOND_BOT_ID) {
            mOpponentBotId = SECOND_BOT_ID;
        }
    }

    @Override
    public int makeTurn() {
        int maxColumns = mPlayboard.getNrColumns();
        int maxRows = mPlayboard.getNrRows();
        int maxTurns = maxRows * maxColumns / 2;

        int currentHighestScore = Integer.MIN_VALUE;
        int highestScoreColumnId = INVALID_COLUMN_ID;

        for(int currentColumnId = 0; currentColumnId < maxColumns; currentColumnId++) {
            System.err.println("Analyse " + currentColumnId + "...");
            mCurrentSearchDepth = 0;

            if (mPlayboard.isColumnFull(currentColumnId)) {
                continue;
            }

            int currentColumnScore = CalculateColumnScore(maxColumns, maxTurns, currentColumnId, mPlayboard);
            System.err.println("CurrentColumnId " + currentColumnId + " CurrentScore " + currentColumnScore);

            if(currentHighestScore < currentColumnScore) {
                currentHighestScore = currentColumnScore;
                highestScoreColumnId = currentColumnId;
            }
        }

        return highestScoreColumnId;
    }

    private int CalculateColumnScore(int maxColumns, int maxTurns, int columnId, Playboard previousPlayboard) {
        // Hit current depth limit, return depth score
        if(mCurrentSearchDepth > MAX_SEARCH_DEPTH) {
            return 0;
        }

        SearchResult searchResult = previousPlayboard.getPlayboardPatternSearch().searchWinnerByColumnId(columnId);
        if(searchResult.getBotId() == mBotId) {
            return (maxTurns - mCurrentSearchDepth) * TURN_SCORE;
        } else if(searchResult.getBotId() == mOpponentBotId) {
            return (maxTurns - mCurrentSearchDepth) * TURN_SCORE * -1;
        }

        // Winner not found, apply current column id
        Playboard currentPlayboard = new Playboard(previousPlayboard);
        currentPlayboard.addDisc(columnId, mBotId);

        // Apply opponent bot id, search recursively
        int accumulatedScore = 0;
        mCurrentSearchDepth++;
        for(int currentColumnId = 0; currentColumnId < maxColumns; currentColumnId++) {
            if (currentPlayboard.isColumnFull(currentColumnId)) {
                continue;
            }

            Playboard nextPlayboard = new Playboard(currentPlayboard);
            if (!nextPlayboard.addDisc(currentColumnId, mOpponentBotId)) {
                continue;
            }

            //Check for bot winner after apply opponent change
            for(int nextColumnId = 0; nextColumnId < maxColumns; nextColumnId++) {
                if (nextPlayboard.isColumnFull(nextColumnId)) {
                    continue;
                }

                int currentScore = CalculateColumnScore(maxColumns, maxTurns, nextColumnId, nextPlayboard);
                accumulatedScore += currentScore;


            }
        }
        mCurrentSearchDepth--;

        return accumulatedScore;
    }
}
