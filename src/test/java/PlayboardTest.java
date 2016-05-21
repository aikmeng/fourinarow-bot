import amang.Playboard;
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

    @Test
    public void testGetTopRowValue_boundary_1() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0";
        classUnderTest.parseFromString(fieldString);
        assertEquals(0, classUnderTest.getTopRowValue(3));
    }

    @Test
    public void testGetTopRowValue_boundary_2() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,2,0,0,0;" +
                                "0,0,0,1,0,0,0;" +
                                "0,0,0,2,0,0,0;" +
                                "0,0,0,1,0,0,0;" +
                                "0,0,0,2,0,0,0;" +
                                "0,0,0,1,0,0,0";
        classUnderTest.parseFromString(fieldString);
        assertEquals(-1, classUnderTest.getTopRowValue(3));
    }

    @Test
    public void testGetTopRowValue_scenario_1() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,1,0,0,0";
        classUnderTest.parseFromString(fieldString);
        assertEquals(1, classUnderTest.getTopRowValue(3));
    }

    @Test
    public void testGetTopRowValue_scenario_2() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,2,0,0,0;" +
                                "0,0,0,1,0,0,0;" +
                                "0,0,0,1,0,0,0;" +
                                "0,0,0,1,0,0,0;" +
                                "0,0,0,1,0,0,0";
        classUnderTest.parseFromString(fieldString);
        assertEquals(2, classUnderTest.getTopRowValue(3));
    }

    @Test
    public void testGetColumnTopRowWithDifferentBotId_boundary_1() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0";
        classUnderTest.parseFromString(fieldString);

        int botId = 2;
        assertEquals(-1, classUnderTest.getColumnTopRowWithDifferentBotId(botId));
    }

    @Test
    public void testGetColumnTopRowWithDifferentBotId_boundary_2() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "2,2,2,2,2,2,2;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0";
        classUnderTest.parseFromString(fieldString);

        int botId = 2;
        assertEquals(-1, classUnderTest.getColumnTopRowWithDifferentBotId(botId));
    }

    @Test
    public void testGetColumnTopRowWithDifferentBotId_scenario_1() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,1,0,0,0";
        classUnderTest.parseFromString(fieldString);

        int botId = 2;
        assertEquals(3, classUnderTest.getColumnTopRowWithDifferentBotId(botId));
    }

    @Test
    public void testGetColumnTopRowWithDifferentBotId_scenario_2() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,2,0,0,0;" +
                                "0,0,0,1,0,0,0";
        classUnderTest.parseFromString(fieldString);

        int botId = 2;
        assertEquals(-1, classUnderTest.getColumnTopRowWithDifferentBotId(botId));
    }

    @Test
    public void testGetColumnTopRowWithDifferentBotId_scenario_3() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0;" +
                "0,0,2,1,0,0,0";
        classUnderTest.parseFromString(fieldString);

        int botId = 2;
        assertEquals(3, classUnderTest.getColumnTopRowWithDifferentBotId(botId));
    }

    @Test
    public void testGetColumnTopRowWithDifferentBotId_scenario_4() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0;" +
                "0,1,2,1,0,0,0";
        classUnderTest.parseFromString(fieldString);

        int botId = 2;
        assertEquals(1, classUnderTest.getColumnTopRowWithDifferentBotId(botId));
    }

    @Test
    public void testGetColumnTopRowWithDifferentBotId_scenario_5() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,2,0,0,0,0,0;" +
                                "0,1,2,1,0,0,0";
        classUnderTest.parseFromString(fieldString);

        int botId = 2;
        assertEquals(3, classUnderTest.getColumnTopRowWithDifferentBotId(botId));
    }
}
