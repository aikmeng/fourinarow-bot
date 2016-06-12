package amang.BotLogic;

import amang.Playboard.Playboard;
import java.util.ArrayList;
import java.util.Date;

public class MinMaxStrategy implements IBotStrategy {
    private int mBotId;
    private int mOpponentBotId;
    private int mCurrentBotId;
    private int mChoiceColumnId;
    private int mCurrentSearchDepth;

    private Playboard mPlayboard;
    
    public static final int WIN_SCORE = 43;
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

        mCurrentBotId = mBotId;
        minMax(mPlayboard, maxColumns);

        return mChoiceColumnId;
    }

    public int minMax(Playboard playboard, int maxColumns) {
        int gameScore = calculateGameScore(playboard);
        if(gameScore != -1) {
            return gameScore;
        }

        ArrayList<Integer> gameScores = new ArrayList<>();
        ArrayList<Integer> moves = new ArrayList<>();

        mCurrentSearchDepth++;

        for(int currentColumnId = 0; currentColumnId < maxColumns; currentColumnId++) {
            Playboard movePlayboard = new Playboard(playboard);
            if (!movePlayboard.addDisc(currentColumnId, mCurrentBotId)) {
                continue;
            }

            int currentGameScore = minMax(movePlayboard, maxColumns);
            System.err.println(new Date().getTime() + " Analyse depth " + mCurrentSearchDepth + " Add Column Id " + currentColumnId + " Game Score " + currentGameScore);
            System.err.println(movePlayboard);

            gameScores.add(currentGameScore);
            moves.add(currentColumnId);
        }
        mCurrentSearchDepth--;

        if(mCurrentBotId == mBotId) {
            int highestScoreIndex = FindMaxValueIndex(gameScores);
            mChoiceColumnId = moves.get(highestScoreIndex);
            mCurrentBotId = mOpponentBotId;
            return gameScores.get(highestScoreIndex);
        } else {
            int lowestScoreIndex = FindMinValueIndex(gameScores);
            mChoiceColumnId = moves.get(lowestScoreIndex);
            mCurrentBotId = mBotId;
            return gameScores.get(lowestScoreIndex);
        }
    }

    private int calculateGameScore(Playboard playboard) {
        int winnerId = playboard.getPlayboardPatternSearch().getWinner();
        if(winnerId == mBotId) {
            return WIN_SCORE - mCurrentSearchDepth;
        } else if(winnerId == mOpponentBotId) {
            return mCurrentSearchDepth - WIN_SCORE;
        } else if(playboard.isFull() || mCurrentSearchDepth > MAX_SEARCH_DEPTH) {
            return 0;
        } else {
            // game not ended
            return -1;
        }
    }

    private int FindMinValueIndex(ArrayList<Integer> arrayList) {
        int minValue = Integer.MAX_VALUE;
        int minIndex = -1;
        for(int index = 0; index < arrayList.size(); index++) {
            int elementValue = arrayList.get(index);
            if(elementValue < minValue) {
                minValue = elementValue;
                minIndex = index;
            }
        }
        return minIndex;
    }

    private int FindMaxValueIndex(ArrayList<Integer> arrayList) {
        int maxValue = Integer.MIN_VALUE;
        int maxIndex = -1;
        for(int index = 0; index < arrayList.size(); index++) {
            int elementValue = arrayList.get(index);
            if(elementValue > maxValue) {
                maxValue = elementValue;
                maxIndex = index;
            }
        }
        return maxIndex;
    }
}
