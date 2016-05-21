package amang.BotLogic;

import amang.Playboard;
import java.util.Random;

// Defensive Strategy
public class SecondMoverStrategy implements IBotStrategy {
    private Playboard mPlayboard;
    private int mBotId;
    private int mCurrentRound;
    private int mLastMove;

    public SecondMoverStrategy(Playboard playboard, int botId) {
        mPlayboard = playboard;
        mBotId = botId;
        mLastMove = -1;
    }

    @Override
    public void updateRound(int round) {
        mCurrentRound = round;
    }

    @Override
    public int makeTurn() {
        int blockingColumn = mPlayboard.getColumnTopRowWithDifferentBotId(mBotId);
        if (blockingColumn != -1) {
            return blockingColumn;
        }

        if (mLastMove == -1) {
            int randomMove = makeRandomMove();
            trackValidateMove(randomMove);
            return randomMove;
        }

        int buildingColumn = mPlayboard.getColumnTopRowWithSameBotId(mBotId);
        if (buildingColumn != -1) {
            return buildingColumn;
        }

        int randomMove = makeRandomMove();
        trackValidateMove(randomMove);
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
