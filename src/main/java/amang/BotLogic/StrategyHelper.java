package amang.BotLogic;

import amang.Playboard.Playboard;

import java.util.Random;

public class StrategyHelper {
    private Playboard mPlayboard;
    private int mLastMove;

    public StrategyHelper(Playboard playboard) {
        mPlayboard = playboard;
        mLastMove = -1;
    }

    public int getLastMove() {
        return mLastMove;
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
