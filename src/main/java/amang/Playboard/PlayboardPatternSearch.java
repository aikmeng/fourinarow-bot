package amang.Playboard;

public class PlayboardPatternSearch {
    private int[][] mBoard;
    private int mCols = 0;
    private int mRows = 0;

    public PlayboardPatternSearch(int columns, int rows, int[][] board) {
        mCols = columns;
        mRows = rows;
        mBoard = board;
    }

    public int getTopRowValue(int column) {
        for (int y = mRows - 1; y >= 0; y--) {
            if(mBoard[column][y] == 0) {
                if(y == mRows - 1) {
                    return 0;
                }
                return mBoard[column][y + 1];
            }
        }
        return -1;
    }

    public int getColumnTopRowWithDifferentBotId(int botId) {
        for (int x = 0; x < mCols; x++) {
            for (int y = mRows - 1; y >= 0; y--) {
                if(mBoard[x][y] == 0) {
                    if(y == mRows - 1) {
                        break;
                    }
                    int previousRowValue = mBoard[x][y + 1];
                    if(previousRowValue != botId) {
                        return x;
                    }
                    break;
                }
            }
        }
        return -1;
    }

    public int getColumnTopRowWithSameBotId(int botId) {
        for (int x = 0; x < mCols; x++) {
            for (int y = mRows - 1; y >= 0; y--) {
                if(mBoard[x][y] == 0) {
                    if(y == mRows - 1) {
                        break;
                    }
                    int previousRowValue = mBoard[x][y + 1];
                    if(previousRowValue == botId) {
                        return x;
                    }
                    break;
                }
            }
        }
        return -1;
    }
}
