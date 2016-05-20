package amang.BotLogic;

import amang.Playboard;
import java.util.Random;

// Offensive Strategy
public class FirstMoverStrategy implements IBotStrategy {
    private Playboard mPlayboard;
    private int mBotId;
    private int mCurrentRound;
    private int mLastMove;

    public FirstMoverStrategy(Playboard playboard, int botId) {
        mPlayboard = playboard;
        mBotId = botId;
    }

    @Override
    public void updateRound(int round) {
        mCurrentRound = round;
    }

    @Override
    public int makeTurn() {
        if (mCurrentRound == 1) {
            return firstRoundMove();
        } else {
            return findNextMove();
        }
    }

    private int firstRoundMove() {
        int nextMove = mPlayboard.getMiddleColumn();
        trackValidateMove(nextMove);
        return nextMove;
    }

    private int findNextMove() {
        int topRowValue = mPlayboard.getTopRowValue(mLastMove);
        if(topRowValue == mBotId) {
            if(trackValidateMove(mLastMove)){
                return mLastMove;
            }
        }

        int randomMove = makeRandomMove();
        while(!trackValidateMove(randomMove)) {
            randomMove = makeRandomMove();
        }
        return randomMove;
    }

    private boolean trackValidateMove(int targetColumn) {
        if(mPlayboard.isColumnFull(targetColumn)) {
            return false;
        }

        mLastMove = targetColumn;
        return true;
    }

    private int makeRandomMove() {
        int move = new Random().nextInt(7);
        return move;
    }
}
