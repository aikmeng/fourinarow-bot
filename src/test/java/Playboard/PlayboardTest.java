import amang.Playboard.Playboard;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayboardTest {
    private static int MAX_COLUMN = 7;
    private static int MAX_ROW = 6;

    @Test
    public void testGetNrColumns() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        assertEquals(MAX_COLUMN, classUnderTest.getNrColumns());
    }

    @Test
    public void testGetNrRows() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        assertEquals(MAX_ROW, classUnderTest.getNrRows());
    }

    @Test
    public void testParseFromString() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "1,1,1,1,1,1,1;" +
                                "1,1,1,1,1,1,1;" +
                                "1,1,1,1,1,1,1;" +
                                "1,1,1,1,1,1,1;" +
                                "1,1,1,1,1,1,1;" +
                                "1,1,1,1,1,1,1";
        classUnderTest.parseFromString(fieldString);

        for(int currentRow = MAX_ROW - 1; currentRow >= 0; currentRow--) {
            for(int currentColumn = 0; currentColumn < MAX_ROW; currentColumn++) {
                assertEquals(1, classUnderTest.getDisc(currentColumn, currentRow));
            }
        }
    }

    @Test
    public void testIsColumnFull() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,1,0,0,0,0;" +
                                "0,0,1,0,0,0,0;" +
                                "0,0,1,0,0,0,0;" +
                                "0,0,1,0,0,0,0;" +
                                "0,0,1,0,0,0,0;" +
                                "0,0,1,0,0,0,0";

        classUnderTest.parseFromString(fieldString);
        assertTrue(classUnderTest.isColumnFull(2));
    }

    @Test
    public void testIsValidMove_returns_true() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,1,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0";

        classUnderTest.parseFromString(fieldString);
        assertTrue(classUnderTest.isValidMove(2));
    }

    @Test
    public void testIsValidMove_returns_false() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,2,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0";

        classUnderTest.parseFromString(fieldString);
        assertFalse(classUnderTest.isValidMove(2));
    }

    @Test
    public void testIsFull() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "1,1,1,1,1,1,1;" +
                                "1,1,1,1,1,1,1;" +
                                "1,1,1,1,1,1,1;" +
                                "1,1,1,1,1,1,1;" +
                                "1,1,1,1,1,1,1;" +
                                "1,1,1,1,1,1,1";
        classUnderTest.parseFromString(fieldString);
        assertTrue(classUnderTest.isFull());
    }

    @Test
    public void testGetMiddleColumn() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        assertEquals(3, classUnderTest.getMiddleColumn());
    }
}
