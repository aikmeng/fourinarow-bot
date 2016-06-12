// Copyright 2015 theaigames.com (developers@theaigames.com)

//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at

//        http://www.apache.org/licenses/LICENSE-2.0

//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//	
//    For the full copyright and license information, please view the LICENSE
//    file that was distributed with this source code.

package amang.Playboard;

public class Playboard {
    private final int INAROW = 4; /* Number of cells in a row needed for a win */
    private static String newline = System.getProperty("line.separator");

    private int[][] mBoard;
    private int mCols = 0, mRows = 0;

    public Playboard(int columns, int rows) {
        mCols = columns;
        mRows = rows;

        Initialize();
    }

    public Playboard(Playboard playboard) {
        mCols = playboard.getNrColumns();
        mRows = playboard.getNrRows();

        Initialize();

        for(int currentRow = 0; currentRow < mRows; currentRow++) {
            for(int currentColumn = 0; currentColumn < mCols; currentColumn++) {
                mBoard[currentColumn][currentRow] = playboard.getDisc(currentColumn, currentRow);
            }
        }
    }

    private void Initialize() {
        mBoard = new int[mCols][mRows];
        clearBoard();
    }

    /**
     * Sets the number of columns (this clears the board)
     *
     * @param args : int cols
     */
    public void setColumns(int cols) {
        mCols = cols;
        mBoard = new int[mCols][mRows];
    }

    /**
     * Sets the number of rows (this clears the board)
     *
     * @param args : int rows
     */
    public void setRows(int rows) {
        mRows = rows;
        mBoard = new int[mCols][mRows];
    }

    /**
     * Clear the board
     */
    public void clearBoard() {
        for (int x = 0; x < mCols; x++) {
            for (int y = 0; y < mRows; y++) {
                mBoard[x][y] = 0;
            }
        }
    }

    /**
     * Initialise field from comma separated String
     *
     * @param String :
     */
    public void parseFromString(String s) {
        s = s.replace(';', ',');
        String[] r = s.split(",");
        int counter = 0;
        for (int y = 0; y < mRows; y++) {
            for (int x = 0; x < mCols; x++) {
                mBoard[x][y] = Integer.parseInt(r[counter]);
                counter++;
            }
        }
    }

    /**
     * Returns the current piece on a given column and row
     *
     * @param args : int column, int row
     * @return : int
     */
    public int getDisc(int column, int row) {
        return mBoard[column][row];
    }

    /**
     * Returns whether a slot is open at given column
     *
     * @param args : int column
     * @return : Boolean
     */
    public Boolean isValidMove(int column) {
        return (mBoard[column][0] == 0);
    }

    @Override
    /**
     * Creates comma separated String with every cell.
     * @param args :
     * @return : String
     */
    public String toString() {
        String r = " ";
        int counter = 0;
        for (int y = 0; y < mRows; y++) {
            for (int x = 0; x < mCols; x++) {
                if (counter > 0) {
                    r += ",";
                }
                r += mBoard[x][y];
                counter++;
            }
            r += newline;
        }
        return r;
    }

    /**
     * Checks whether the field is full
     *
     * @return : Returns true when field is full, otherwise returns false.
     */
    public boolean isFull() {
        for (int x = 0; x < mCols; x++)
            for (int y = 0; y < mRows; y++)
                if (mBoard[x][y] == 0)
                    return false; // At least one cell is not filled
        // All cells are filled
        return true;
    }

    /**
     * Checks whether the given column is full
     *
     * @return : Returns true when given column is full, otherwise returns false.
     */
    public boolean isColumnFull(int column) {
        return (mBoard[column][0] != 0);
    }

    /**
     * @return : Returns the number of columns in the field.
     */
    public int getNrColumns() {
        return mCols;
    }

    /**
     * @return : Returns the number of rows in the field.
     */
    public int getNrRows() {
        return mRows;
    }

    public Boolean addDisc(int column, int disc) {
        if (column < mCols) {
            for (int y = mRows - 1; y >= 0; y--) { // From bottom column up
                if (mBoard[column][y] == 0) {
                    mBoard[column][y] = disc;
                    return true;
                }
            }
        }
        return false;
    }

    public int getWinner() {
        /* Check for horizontal wins */
        for (int x = 0; x < mCols; x++) {
            for (int y = 0; y < mRows; y++) {
                int n = mBoard[x][y];
                Boolean win = true;
                if (n != 0) {
                    for (int i = 0; i < INAROW; i++) {
                        if (x + i < mCols) {
                            if (n != (mBoard[x + i][y])) {
                                win = false;
                            }
                        } else {
                            win = false;
                        }
                    }
                    if (win) {
                        return n;
                    }
                }
            }
        }

        /* Check for vertical wins */
        for (int x = 0; x < mCols; x++) {
            for (int y = 0; y < mRows; y++) {
                int n = mBoard[x][y];
                Boolean win = true;
                if (n != 0) {
                    for (int i = 0; i < INAROW; i++) {
                        if (y + i < mRows) {
                            if (n != mBoard[x][y + i]) {
                                win = false;
                            }
                        } else {
                            win = false;
                        }
                    }
                    if (win) {
                        return n;
                    }
                }
            }
        }

        /* Check for diagonal wins */
        for (int x = 0; x < mCols; x++) {
            for (int y = 0; y < mRows; y++) {
                int n = mBoard[x][y];
                Boolean win = true;
                if (n != 0) {
                    for (int i = 0; i < INAROW; i++) {
                        if (x - i >= 0 && y + i < mRows) {
                            if (n !=mBoard[x - i][y + i]) {
                                win = false;
                            }
                        } else {
                            win = false;
                        }
                    }
                    if (win) {
                        return n;
                    }
                }
            }
        }
        /* Check for anti diagonal wins */
        for (int x = 0; x < mCols; x++) {
            for (int y = 0; y < mRows; y++) {
                int n = mBoard[x][y];
                Boolean win = true;
                if (n != 0) {
                    for (int i = 0; i < INAROW; i++) {
                        if (x + i < mCols && y + i < mRows) {
                            if (n != mBoard[x + i][y + i]) {
                                win = false;
                            }
                        } else {
                            win = false;
                        }
                    }
                    if (win) {
                        return n;
                    }
                }
            }
        }
        return 0;
    }
}
