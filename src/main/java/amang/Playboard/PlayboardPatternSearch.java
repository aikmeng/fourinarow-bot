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
            if (mBoard[column][y] == 0) {
                if (y == mRows - 1) {
                    return COLUMN_UNUSED;
                }
                return mBoard[column][y + 1];
            }
        }
        return COLUMN_INVALID;
    }

    // Possible to have multiple columns as solution. Just get one and return;
    public SearchResult searchHorizontalWinnerColumn() {
        for (int y = mRows - 1; y >= 0; y--) {
            for (int x = 0; x < mCols; x++) {
                if (x + 3 >= mCols) {
                    continue;
                } else if (mBoard[x][y] == COLUMN_UNUSED &&                                //return column must be unused
                        mBoard[x + 1][y] != COLUMN_UNUSED &&                            //current slot must be valid botid
                        (y == mRows - 1 || mBoard[x][y + 1] != COLUMN_UNUSED) &&    //check slot below is used
                        mBoard[x + 1][y] == mBoard[x + 2][y] &&                         //check for continuous pattern
                        mBoard[x + 2][y] == mBoard[x + 3][y]) {
                    return new SearchResult(mBoard[x + 1][y], x);
                } else if (mBoard[x + 1][y] == COLUMN_UNUSED &&
                        mBoard[x][y] != COLUMN_UNUSED &&
                        (y == mRows - 1 || mBoard[x + 1][y + 1] != COLUMN_UNUSED) &&    //check slot below is used
                        mBoard[x][y] == mBoard[x + 2][y] &&
                        mBoard[x + 2][y] == mBoard[x + 3][y]) {
                    return new SearchResult(mBoard[x][y], x + 1);
                } else if (mBoard[x + 2][y] == COLUMN_UNUSED &&
                        mBoard[x][y] != COLUMN_UNUSED &&
                        (y == mRows - 1 || mBoard[x + 2][y + 1] != COLUMN_UNUSED) &&    //check slot below is used
                        mBoard[x][y] == mBoard[x + 1][y] &&
                        mBoard[x + 1][y] == mBoard[x + 3][y]) {
                    return new SearchResult(mBoard[x][y], x + 2);
                } else if (mBoard[x + 3][y] == COLUMN_UNUSED &&
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

    // Possible to have multiple columns as solution. Just get one and return;
    public SearchResult searchHorizontalPotentialWinnerColumn() {
        for (int y = mRows - 1; y >= 0; y--) {
            for (int x = 0; x < mCols; x++) {
                if (x + 3 >= mCols) {
                    continue;
                } else if (mBoard[x + 1][y] != COLUMN_UNUSED &&                            //check comparing slots have valid botid
                        (y == mRows - 1 || mBoard[x][y + 1] != COLUMN_UNUSED) &&        //check no row below or row below the return column is occupied
                        (y == mRows - 1 || mBoard[x + 3][y + 1] != COLUMN_UNUSED) &&    //check no row below or row below the other potential column is occupied
                        mBoard[x][y] == COLUMN_UNUSED &&
                        mBoard[x + 1][y] == mBoard[x + 2][y] &&
                        mBoard[x + 3][y] == COLUMN_UNUSED) {
                    return new SearchResult(mBoard[x + 1][y], x);
                }
            }
        }

        return new SearchResult(COLUMN_UNUSED, COLUMN_NOT_FOUND);
    }


    public SearchResult searchVerticalWinnerColumn() {
        for (int x = 0; x < mCols; x++) {
            for (int y = mRows - 1; y >= 0; y--) {
                if (y - 3 < 0) {
                    continue;
                } else if (mBoard[x][y - 3] == COLUMN_UNUSED &&            //return column must be unused
                        mBoard[x][y] != COLUMN_UNUSED &&                //current slot must be valid botid
                        mBoard[x][y] == mBoard[x][y - 1] &&
                        mBoard[x][y - 1] == mBoard[x][y - 2]) {
                    return new SearchResult(mBoard[x][y], x);
                }
            }
        }

        return new SearchResult(COLUMN_UNUSED, COLUMN_NOT_FOUND);
    }

    public SearchResult searchDiagonalUpWinnerColumn() {
        for (int y = mRows - 1; y >= 0; y--) {
            for (int x = 0; x < mCols; x++) {
                if (mBoard[x][y] != COLUMN_UNUSED) {                                //check current slot is occupied.
                    continue;
                } else if (x + 3 < mCols && y - 3 >= 0 &&                           //ensure boundary not hit
                        mBoard[x + 1][y - 1] != COLUMN_UNUSED &&                    //check valid botid before comparing
                        (y == mRows - 1 || mBoard[x][y + 1] != COLUMN_UNUSED) &&    //check row below is occupied before returning
                        mBoard[x + 1][y - 1] == mBoard[x + 2][y - 2] &&
                        mBoard[x + 2][y - 2] == mBoard[x + 3][y - 3]) {
                    return new SearchResult(mBoard[x + 1][y - 1], x);
                } else if (x + 2 < mCols && y + 1 <= mRows - 1 &&                       //ensure boundary not hit
                        x - 1 >= 0 && y - 2 >= 0 &&                                      //ensure boundary not hit
                        mBoard[x - 1][y + 1] != COLUMN_UNUSED &&                        //check valid botid before comparing
                        (y == mRows - 1 || mBoard[x][y + 1] != COLUMN_UNUSED) &&        //check row below is occupied before returning
                        mBoard[x - 1][y + 1] == mBoard[x + 1][y - 1] &&
                        mBoard[x + 1][y - 1] == mBoard[x + 2][y - 2]) {
                    return new SearchResult(mBoard[x + 1][y - 1], x);
                } else if (x + 1 < mCols && y + 2 <= mRows - 1 &&                       //ensure boundary not hit
                        x - 2 >= 0 && y - 1 >= 0 &&                                      //ensure boundary not hit
                        mBoard[x - 2][y + 2] != COLUMN_UNUSED &&                        //check valid botid before comparing
                        (y == mRows - 1 || mBoard[x][y + 1] != COLUMN_UNUSED) &&        //check row below is occupied before returning
                        mBoard[x - 2][y + 2] == mBoard[x - 1][y + 1] &&
                        mBoard[x - 1][y + 1] == mBoard[x + 1][y - 1]) {
                    return new SearchResult(mBoard[x + 1][y - 1], x);
                } else if (x - 3 >= 0 && y + 3 <= mRows - 1 &&                       //ensure boundary not hit
                        mBoard[x - 3][y + 3] != COLUMN_UNUSED &&                        //check valid botid before comparing
                        (y == mRows - 1 || mBoard[x][y + 1] != COLUMN_UNUSED) &&        //check row below is occupied before returning
                        mBoard[x - 3][y + 3] == mBoard[x - 2][y + 2] &&
                        mBoard[x - 2][y + 2] == mBoard[x - 1][y + 1]) {
                    return new SearchResult(mBoard[x - 1][y + 1], x);
                }
            }
        }

        return new SearchResult(COLUMN_UNUSED, COLUMN_NOT_FOUND);
    }

    public SearchResult searchDiagonalDownWinnerColumn() {
        for (int y = 0; y < mRows; y++) {
            for (int x = 0; x < mCols; x++) {
                if (mBoard[x][y] != COLUMN_UNUSED) {                                //check current slot is occupied.
                    continue;
                } else if (x + 3 < mCols && y + 3 < mRows &&                        //ensure boundary not hit
                        mBoard[x + 1][y + 1] != COLUMN_UNUSED &&                    //check valid botid before comparing
                        (y == mRows - 1 || mBoard[x][y + 1] != COLUMN_UNUSED) &&    //check row below is occupied before returning
                        mBoard[x + 1][y + 1] == mBoard[x + 2][y + 2] &&
                        mBoard[x + 2][y + 2] == mBoard[x + 3][y + 3]) {
                    return new SearchResult(mBoard[x + 1][y + 1], x);
                } else if (x + 2 < mCols && y + 2 < mRows &&                        //ensure boundary not hit
                        x - 1 >= 0 && y - 1 >= 0 &&                                 //ensure boundary not hit
                        mBoard[x - 1][y - 1] != COLUMN_UNUSED &&                    //check valid botid before comparing
                        (y == mRows - 1 || mBoard[x][y + 1] != COLUMN_UNUSED) &&    //check row below is occupied before returning
                        mBoard[x - 1][y - 1] == mBoard[x + 1][y + 1] &&
                        mBoard[x + 1][y + 1] == mBoard[x + 2][y + 2]) {
                    return new SearchResult(mBoard[x + 1][y + 1], x);
                } else if (x + 1 < mCols && y + 1 < mRows &&                        //ensure boundary not hit
                        x - 2 >= 0 && y - 2 >= 0 &&                                 //ensure boundary not hit
                        mBoard[x - 2][y - 2] != COLUMN_UNUSED &&                    //check valid botid before comparing
                        (y == mRows - 1 || mBoard[x][y + 1] != COLUMN_UNUSED) &&    //check row below is occupied before returning
                        mBoard[x - 2][y - 2] == mBoard[x - 1][y - 1] &&
                        mBoard[x - 1][y - 1] == mBoard[x + 1][y + 1]) {
                    return new SearchResult(mBoard[x + 1][y + 1], x);
                } else if (x - 3 >= 0 && y - 3 >= 0 &&                        //ensure boundary not hit
                        mBoard[x - 3][y - 3] != COLUMN_UNUSED &&                    //check valid botid before comparing
                        (y == mRows - 1 || mBoard[x][y + 1] != COLUMN_UNUSED) &&    //check row below is occupied before returning
                        mBoard[x - 3][y - 3] == mBoard[x - 2][y - 2] &&
                        mBoard[x - 2][y - 2] == mBoard[x - 1][y - 1]) {
                    return new SearchResult(mBoard[x - 1][y - 1], x);
                }
            }
        }

        return new SearchResult(COLUMN_UNUSED, COLUMN_NOT_FOUND);
    }

    //TODO: Implement
    public SearchResult searchWinnerColumn(int columnId) {
        return new SearchResult(-1, -1);
    }
}
