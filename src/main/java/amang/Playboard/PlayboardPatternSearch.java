package amang.Playboard;

public class PlayboardPatternSearch {
    public static final int COLUMN_INVALID = -1;
    public static final int COLUMN_NOT_FOUND = -1;
    public static final int ROW_NOT_FOUND = -1;
    public static final int UNUSED_SLOT = 0;

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
                    return UNUSED_SLOT;
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
                } else if (mBoard[x][y] == UNUSED_SLOT &&                                //return column must be unused
                        mBoard[x + 1][y] != UNUSED_SLOT &&                            //current slot must be valid botid
                        (y == mRows - 1 || mBoard[x][y + 1] != UNUSED_SLOT) &&    //check slot below is used
                        mBoard[x + 1][y] == mBoard[x + 2][y] &&                         //check for continuous pattern
                        mBoard[x + 2][y] == mBoard[x + 3][y]) {
                    return new SearchResult(mBoard[x + 1][y], x);
                } else if (mBoard[x + 1][y] == UNUSED_SLOT &&
                        mBoard[x][y] != UNUSED_SLOT &&
                        (y == mRows - 1 || mBoard[x + 1][y + 1] != UNUSED_SLOT) &&    //check slot below is used
                        mBoard[x][y] == mBoard[x + 2][y] &&
                        mBoard[x + 2][y] == mBoard[x + 3][y]) {
                    return new SearchResult(mBoard[x][y], x + 1);
                } else if (mBoard[x + 2][y] == UNUSED_SLOT &&
                        mBoard[x][y] != UNUSED_SLOT &&
                        (y == mRows - 1 || mBoard[x + 2][y + 1] != UNUSED_SLOT) &&    //check slot below is used
                        mBoard[x][y] == mBoard[x + 1][y] &&
                        mBoard[x + 1][y] == mBoard[x + 3][y]) {
                    return new SearchResult(mBoard[x][y], x + 2);
                } else if (mBoard[x + 3][y] == UNUSED_SLOT &&
                        mBoard[x][y] != UNUSED_SLOT &&
                        (y == mRows - 1 || mBoard[x + 3][y + 1] != UNUSED_SLOT) &&    //check slot below is used
                        mBoard[x][y] == mBoard[x + 1][y] &&
                        mBoard[x + 1][y] == mBoard[x + 2][y]) {
                    return new SearchResult(mBoard[x][y], x + 3);
                }
            }
        }

        return new SearchResult(UNUSED_SLOT, COLUMN_NOT_FOUND);
    }

    // Possible to have multiple columns as solution. Just get one and return;
    public SearchResult searchHorizontalPotentialWinnerColumn() {
        for (int y = mRows - 1; y >= 0; y--) {
            for (int x = 0; x < mCols; x++) {
                if (x + 3 >= mCols) {
                    continue;
                } else if (mBoard[x + 1][y] != UNUSED_SLOT &&                            //check comparing slots have valid botid
                        (y == mRows - 1 || mBoard[x][y + 1] != UNUSED_SLOT) &&        //check no row below or row below the return column is occupied
                        (y == mRows - 1 || mBoard[x + 3][y + 1] != UNUSED_SLOT) &&    //check no row below or row below the other potential column is occupied
                        mBoard[x][y] == UNUSED_SLOT &&
                        mBoard[x + 1][y] == mBoard[x + 2][y] &&
                        mBoard[x + 3][y] == UNUSED_SLOT) {
                    return new SearchResult(mBoard[x + 1][y], x);
                }
            }
        }

        return new SearchResult(UNUSED_SLOT, COLUMN_NOT_FOUND);
    }


    public SearchResult searchVerticalWinnerColumn() {
        for (int x = 0; x < mCols; x++) {
            for (int y = mRows - 1; y >= 0; y--) {
                if (y - 3 < 0) {
                    continue;
                } else if (mBoard[x][y - 3] == UNUSED_SLOT &&            //return column must be unused
                        mBoard[x][y] != UNUSED_SLOT &&                //current slot must be valid botid
                        mBoard[x][y] == mBoard[x][y - 1] &&
                        mBoard[x][y - 1] == mBoard[x][y - 2]) {
                    return new SearchResult(mBoard[x][y], x);
                }
            }
        }

        return new SearchResult(UNUSED_SLOT, COLUMN_NOT_FOUND);
    }

    public SearchResult searchDiagonalUpWinnerColumn() {
        for (int y = mRows - 1; y >= 0; y--) {
            for (int x = 0; x < mCols; x++) {
                if (mBoard[x][y] != UNUSED_SLOT) {                                //check current slot is occupied.
                    continue;
                } else if (x + 3 < mCols && y - 3 >= 0 &&                           //ensure boundary not hit
                        mBoard[x + 1][y - 1] != UNUSED_SLOT &&                    //check valid botid before comparing
                        (y == mRows - 1 || mBoard[x][y + 1] != UNUSED_SLOT) &&    //check row below is occupied before returning
                        mBoard[x + 1][y - 1] == mBoard[x + 2][y - 2] &&
                        mBoard[x + 2][y - 2] == mBoard[x + 3][y - 3]) {
                    return new SearchResult(mBoard[x + 1][y - 1], x);
                } else if (x + 2 < mCols && y + 1 <= mRows - 1 &&                       //ensure boundary not hit
                        x - 1 >= 0 && y - 2 >= 0 &&                                      //ensure boundary not hit
                        mBoard[x - 1][y + 1] != UNUSED_SLOT &&                        //check valid botid before comparing
                        (y == mRows - 1 || mBoard[x][y + 1] != UNUSED_SLOT) &&        //check row below is occupied before returning
                        mBoard[x - 1][y + 1] == mBoard[x + 1][y - 1] &&
                        mBoard[x + 1][y - 1] == mBoard[x + 2][y - 2]) {
                    return new SearchResult(mBoard[x + 1][y - 1], x);
                } else if (x + 1 < mCols && y + 2 <= mRows - 1 &&                       //ensure boundary not hit
                        x - 2 >= 0 && y - 1 >= 0 &&                                      //ensure boundary not hit
                        mBoard[x - 2][y + 2] != UNUSED_SLOT &&                        //check valid botid before comparing
                        (y == mRows - 1 || mBoard[x][y + 1] != UNUSED_SLOT) &&        //check row below is occupied before returning
                        mBoard[x - 2][y + 2] == mBoard[x - 1][y + 1] &&
                        mBoard[x - 1][y + 1] == mBoard[x + 1][y - 1]) {
                    return new SearchResult(mBoard[x + 1][y - 1], x);
                } else if (x - 3 >= 0 && y + 3 <= mRows - 1 &&                       //ensure boundary not hit
                        mBoard[x - 3][y + 3] != UNUSED_SLOT &&                        //check valid botid before comparing
                        (y == mRows - 1 || mBoard[x][y + 1] != UNUSED_SLOT) &&        //check row below is occupied before returning
                        mBoard[x - 3][y + 3] == mBoard[x - 2][y + 2] &&
                        mBoard[x - 2][y + 2] == mBoard[x - 1][y + 1]) {
                    return new SearchResult(mBoard[x - 1][y + 1], x);
                }
            }
        }

        return new SearchResult(UNUSED_SLOT, COLUMN_NOT_FOUND);
    }

    public SearchResult searchDiagonalDownWinnerColumn() {
        for (int y = 0; y < mRows; y++) {
            for (int x = 0; x < mCols; x++) {
                if (mBoard[x][y] != UNUSED_SLOT) {                                //check current slot is occupied.
                    continue;
                } else if (x + 3 < mCols && y + 3 < mRows &&                        //ensure boundary not hit
                        mBoard[x + 1][y + 1] != UNUSED_SLOT &&                    //check valid botid before comparing
                        (y == mRows - 1 || mBoard[x][y + 1] != UNUSED_SLOT) &&    //check row below is occupied before returning
                        mBoard[x + 1][y + 1] == mBoard[x + 2][y + 2] &&
                        mBoard[x + 2][y + 2] == mBoard[x + 3][y + 3]) {
                    return new SearchResult(mBoard[x + 1][y + 1], x);
                } else if (x + 2 < mCols && y + 2 < mRows &&                        //ensure boundary not hit
                        x - 1 >= 0 && y - 1 >= 0 &&                                 //ensure boundary not hit
                        mBoard[x - 1][y - 1] != UNUSED_SLOT &&                    //check valid botid before comparing
                        (y == mRows - 1 || mBoard[x][y + 1] != UNUSED_SLOT) &&    //check row below is occupied before returning
                        mBoard[x - 1][y - 1] == mBoard[x + 1][y + 1] &&
                        mBoard[x + 1][y + 1] == mBoard[x + 2][y + 2]) {
                    return new SearchResult(mBoard[x + 1][y + 1], x);
                } else if (x + 1 < mCols && y + 1 < mRows &&                        //ensure boundary not hit
                        x - 2 >= 0 && y - 2 >= 0 &&                                 //ensure boundary not hit
                        mBoard[x - 2][y - 2] != UNUSED_SLOT &&                    //check valid botid before comparing
                        (y == mRows - 1 || mBoard[x][y + 1] != UNUSED_SLOT) &&    //check row below is occupied before returning
                        mBoard[x - 2][y - 2] == mBoard[x - 1][y - 1] &&
                        mBoard[x - 1][y - 1] == mBoard[x + 1][y + 1]) {
                    return new SearchResult(mBoard[x + 1][y + 1], x);
                } else if (x - 3 >= 0 && y - 3 >= 0 &&                        //ensure boundary not hit
                        mBoard[x - 3][y - 3] != UNUSED_SLOT &&                    //check valid botid before comparing
                        (y == mRows - 1 || mBoard[x][y + 1] != UNUSED_SLOT) &&    //check row below is occupied before returning
                        mBoard[x - 3][y - 3] == mBoard[x - 2][y - 2] &&
                        mBoard[x - 2][y - 2] == mBoard[x - 1][y - 1]) {
                    return new SearchResult(mBoard[x - 1][y - 1], x);
                }
            }
        }

        return new SearchResult(UNUSED_SLOT, COLUMN_NOT_FOUND);
    }

    public SearchResult searchWinnerColumn(int columnId) {
        int rowId = searchRowPosition(columnId);
        if(rowId == ROW_NOT_FOUND) {
            return new SearchResult(UNUSED_SLOT, COLUMN_NOT_FOUND);
        }

        if (mBoard[columnId][rowId] != UNUSED_SLOT){
            return new SearchResult(UNUSED_SLOT, COLUMN_NOT_FOUND);
        }

        SearchResult searchResult = searchHorizontalWinner(columnId, rowId);
        if(searchResult != null) {
            return searchResult;
        }

        searchResult = searchVerticalWinner(columnId, rowId);
        if(searchResult != null) {
            return searchResult;
        }

        searchResult = searchDiagonalUpWinner(columnId, rowId);
        if(searchResult != null) {
            return searchResult;
        }

        searchResult = searchDiagonalDownWinner(columnId, rowId);
        if(searchResult != null) {
            return searchResult;
        }

        return new SearchResult(UNUSED_SLOT, COLUMN_NOT_FOUND);
    }

    private SearchResult searchHorizontalWinner(int columnId, int rowId) {
        if (columnId + 3 < mCols &&                                             //check index out of range error
                mBoard[columnId + 1][rowId] != UNUSED_SLOT &&                   //slot must be valid botid before comparing
                mBoard[columnId + 1][rowId] == mBoard[columnId + 2][rowId] &&   //check for continuous horizontal pattern
                mBoard[columnId + 2][rowId] == mBoard[columnId + 3][rowId]) {
            return new SearchResult(mBoard[columnId + 1][rowId], columnId);
        }

        if (columnId - 1 >= 0 && columnId + 2 < mCols &&
                mBoard[columnId - 1][rowId] != UNUSED_SLOT &&
                mBoard[columnId - 1][rowId] == mBoard[columnId + 1][rowId] &&
                mBoard[columnId + 1][rowId] == mBoard[columnId + 2][rowId]) {
            return new SearchResult(mBoard[columnId - 1][rowId], columnId);
        }

        if (columnId - 2 >= 0 && columnId + 1 < mCols &&
                mBoard[columnId - 2][rowId] != UNUSED_SLOT &&
                mBoard[columnId - 2][rowId] == mBoard[columnId - 1][rowId] &&
                mBoard[columnId - 1][rowId] == mBoard[columnId + 1][rowId]) {
            return new SearchResult(mBoard[columnId - 2][rowId], columnId);
        }

        if (columnId - 3 >= 0 &&
                mBoard[columnId - 3][rowId] != UNUSED_SLOT &&
                mBoard[columnId - 3][rowId] == mBoard[columnId - 2][rowId] &&
                mBoard[columnId - 2][rowId] == mBoard[columnId - 1][rowId]) {
            return new SearchResult(mBoard[columnId - 3][rowId], columnId);
        }

        return null;
    }

    private SearchResult searchVerticalWinner(int columnId, int rowId) {
        if (rowId + 3 < mRows &&                                                //check index out of range error
                mBoard[columnId][rowId + 1] != UNUSED_SLOT &&                   //slot must be valid botid before comparing
                mBoard[columnId][rowId + 1] == mBoard[columnId][rowId + 2] &&   //check for continuous vertical pattern
                mBoard[columnId][rowId + 2] == mBoard[columnId][rowId + 3]) {
            return new SearchResult(mBoard[columnId][rowId + 1], columnId);
        }

        if (rowId - 1 >= 0 && rowId + 2 < mRows &&
                mBoard[columnId][rowId - 1] != UNUSED_SLOT &&
                mBoard[columnId][rowId - 1] == mBoard[columnId][rowId + 1] &&
                mBoard[columnId][rowId + 1] == mBoard[columnId][rowId + 2]) {
            return new SearchResult(mBoard[columnId][rowId - 1], columnId);
        }

        if (rowId - 2 >= 0 && rowId + 1 < mRows &&
                mBoard[columnId][rowId - 2] != UNUSED_SLOT &&
                mBoard[columnId][rowId - 2] == mBoard[columnId][rowId - 1] &&
                mBoard[columnId][rowId - 1] == mBoard[columnId][rowId + 1]) {
            return new SearchResult(mBoard[columnId][rowId - 2], columnId);
        }

        if (rowId - 3 >= 0 &&
                mBoard[columnId][rowId - 3] != UNUSED_SLOT &&
                mBoard[columnId][rowId - 3] == mBoard[columnId][rowId - 2] &&
                mBoard[columnId][rowId - 2] == mBoard[columnId][rowId - 1]) {
            return new SearchResult(mBoard[columnId][rowId - 3], columnId);
        }

        return null;
    }

    private SearchResult searchDiagonalUpWinner(int columnId, int rowId) {
        if (columnId + 3 < mCols &&                                                     //check index out of range error
                rowId - 3 >= 0 &&                                                       //check index out of range error
                mBoard[columnId + 1][rowId - 1] != UNUSED_SLOT &&                       //slot must be valid botid before comparing
                mBoard[columnId + 1][rowId - 1] == mBoard[columnId + 2][rowId - 2] &&   //check for continuous diagonal up pattern
                mBoard[columnId + 2][rowId - 2] == mBoard[columnId + 3][rowId - 3]) {
            return new SearchResult(mBoard[columnId + 1][rowId - 1], columnId);
        }

        if (columnId - 1 >= 0 && columnId + 2 < mCols &&
                rowId - 2 >= 0 && rowId + 1 < mRows &&
                mBoard[columnId - 1][rowId + 1] != UNUSED_SLOT &&
                mBoard[columnId - 1][rowId + 1] == mBoard[columnId + 1][rowId - 1] &&
                mBoard[columnId + 1][rowId - 1] == mBoard[columnId + 2][rowId - 2]) {
            return new SearchResult(mBoard[columnId - 1][rowId + 1], columnId);
        }

        if (columnId - 2 >= 0 && columnId + 1 < mCols &&
                rowId - 1 >= 0 && rowId + 2 < mRows &&
                mBoard[columnId - 2][rowId + 2] != UNUSED_SLOT &&
                mBoard[columnId - 2][rowId + 2] == mBoard[columnId - 1][rowId + 1] &&
                mBoard[columnId - 1][rowId + 1] == mBoard[columnId + 1][rowId - 1]) {
            return new SearchResult(mBoard[columnId - 2][rowId + 2], columnId);
        }

        if (columnId - 3 >= 0 &&
                rowId + 3 < mRows &&
                mBoard[columnId - 3][rowId + 3] != UNUSED_SLOT &&
                mBoard[columnId - 3][rowId + 3] == mBoard[columnId - 2][rowId + 2] &&
                mBoard[columnId - 2][rowId + 2] == mBoard[columnId - 1][rowId + 1]) {
            return new SearchResult(mBoard[columnId - 3][rowId + 3], columnId);
        }

        return null;
    }

    private SearchResult searchDiagonalDownWinner(int columnId, int rowId) {
        if (columnId - 3 < mCols &&                                                     //check index out of range error
                rowId - 3 >= 0 &&                                                       //check index out of range error
                mBoard[columnId - 1][rowId - 1] != UNUSED_SLOT &&                       //slot must be valid botid before comparing
                mBoard[columnId - 1][rowId - 1] == mBoard[columnId - 2][rowId - 2] &&   //check for continuous diagonal down pattern
                mBoard[columnId - 2][rowId - 2] == mBoard[columnId - 3][rowId - 3]) {
            return new SearchResult(mBoard[columnId - 1][rowId - 1], columnId);
        }

        if (columnId - 2 >= 0 && columnId + 1 < mCols &&
                rowId - 2 >= 0 && rowId + 1 < mRows &&
                mBoard[columnId + 1][rowId + 1] != UNUSED_SLOT &&
                mBoard[columnId + 1][rowId + 1] == mBoard[columnId - 1][rowId - 1] &&
                mBoard[columnId - 1][rowId - 1] == mBoard[columnId - 2 ][rowId - 2]) {
            return new SearchResult(mBoard[columnId + 1][rowId + 1], columnId);
        }

        if (columnId - 1 >= 0 && columnId + 2 < mCols &&
                rowId - 1 >= 0 && rowId + 2 < mRows &&
                mBoard[columnId + 2][rowId + 2] != UNUSED_SLOT &&
                mBoard[columnId + 2][rowId + 2] == mBoard[columnId + 1][rowId + 1] &&
                mBoard[columnId + 1][rowId + 1] == mBoard[columnId - 1][rowId - 1]) {
            return new SearchResult(mBoard[columnId + 2][rowId + 2], columnId);
        }

        if (columnId + 3 < mCols &&
                rowId + 3 < mRows &&
                mBoard[columnId + 3][rowId + 3] != UNUSED_SLOT &&
                mBoard[columnId + 3][rowId + 3] == mBoard[columnId + 2][rowId + 2] &&
                mBoard[columnId + 2][rowId + 2] == mBoard[columnId - 1][rowId - 1]) {
            return new SearchResult(mBoard[columnId + 3][rowId + 3], columnId);
        }

        return null;
    }

    private int searchRowPosition(int columnId) {
        for (int y = mRows - 1; y >= 0; y--) {
            if(mBoard[columnId][y] == UNUSED_SLOT) {
                return y;
            }
        }

        return ROW_NOT_FOUND;
    }
}
