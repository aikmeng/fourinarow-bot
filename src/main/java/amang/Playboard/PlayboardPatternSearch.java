package amang.Playboard;

public class PlayboardPatternSearch {
    public static final int COLUMN_INVALID = -1;
    public static final int COLUMN_NOT_FOUND = -1;
    public static final int COLUMN_UNUSED = 0;

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
                    return COLUMN_UNUSED;
                }
                return mBoard[column][y + 1];
            }
        }
        return COLUMN_INVALID;
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
        return COLUMN_NOT_FOUND;
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
        return COLUMN_NOT_FOUND;
    }

    // Possible to have multiple columns as solution. Just get one and return;
    public SearchResult searchHorizontalWinnerColumn() {
        for (int y = mRows - 1; y >= 0; y--) {
            for (int x = 0; x < mCols; x++) {
                if(x + 3 >= mCols) {
                    continue;
                }
                else if(mBoard[x][y] == COLUMN_UNUSED &&                                //return column must be unused
                        mBoard[x + 1][y] != COLUMN_UNUSED &&                            //current slot must be valid botid
                        (y == mRows - 1 || mBoard[x][y + 1] != COLUMN_UNUSED) &&    //check slot below is used
                        mBoard[x + 1][y] == mBoard[x + 2][y] &&                         //check for continuous pattern
                        mBoard[x + 2][y] == mBoard[x + 3][y]) {
                    return new SearchResult(mBoard[x + 1][y], x);
                }
                else if(mBoard[x + 1][y] == COLUMN_UNUSED &&
                        mBoard[x][y] != COLUMN_UNUSED &&
                        (y == mRows - 1 || mBoard[x + 1][y + 1] != COLUMN_UNUSED) &&    //check slot below is used
                        mBoard[x][y] == mBoard[x + 2][y] &&
                        mBoard[x + 2][y] == mBoard[x + 3][y]) {
                    return new SearchResult(mBoard[x][y], x + 1);
                }
                else if(mBoard[x + 2][y] == COLUMN_UNUSED &&
                        mBoard[x][y] != COLUMN_UNUSED &&
                        (y == mRows - 1 || mBoard[x + 2][y + 1] != COLUMN_UNUSED) &&    //check slot below is used
                        mBoard[x][y] == mBoard[x + 1][y] &&
                        mBoard[x + 1][y] == mBoard[x + 3][y]) {
                    return new SearchResult(mBoard[x][y], x + 2);
                }
                else if(mBoard[x + 3][y] == COLUMN_UNUSED &&
                        mBoard[x][y] != COLUMN_UNUSED &&
                        (y == mRows - 1 || mBoard[x + 3][y + 1] != COLUMN_UNUSED) &&    //check slot below is used
                        mBoard[x][y] == mBoard[x + 1][y] &&
                        mBoard[x + 1][y] == mBoard[x + 2][y]) {
                    return new SearchResult(mBoard[x][y], x + 3);
                }
            }
        }

        return new SearchResult(COLUMN_UNUSED, COLUMN_NOT_FOUND);
    }
}
