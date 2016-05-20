import org.junit.Test;
import amang.Field;
import static org.junit.Assert.*;

public class FieldTest {
    private static int MAX_COLUMN = 7;
    private static int MAX_ROW = 6;

    @Test
    public void testGetNrColumns() {
        Field classUnderTest = new Field(MAX_COLUMN, MAX_ROW);
        assertEquals(MAX_COLUMN, classUnderTest.getNrColumns());
    }

    @Test
    public void testGetNrRows() {
        Field classUnderTest = new Field(MAX_COLUMN, MAX_ROW);
        assertEquals(MAX_ROW, classUnderTest.getNrRows());
    }

    @Test
    public void testParseFromString() {
        Field classUnderTest = new Field(MAX_COLUMN, MAX_ROW);
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
        Field classUnderTest = new Field(MAX_COLUMN, MAX_ROW);
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
        Field classUnderTest = new Field(MAX_COLUMN, MAX_ROW);
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
        Field classUnderTest = new Field(MAX_COLUMN, MAX_ROW);
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
        Field classUnderTest = new Field(MAX_COLUMN, MAX_ROW);
        String fieldString =    "1,1,1,1,1,1,1;" +
                                "1,1,1,1,1,1,1;" +
                                "1,1,1,1,1,1,1;" +
                                "1,1,1,1,1,1,1;" +
                                "1,1,1,1,1,1,1;" +
                                "1,1,1,1,1,1,1";
        classUnderTest.parseFromString(fieldString);
        assertTrue(classUnderTest.isFull());
    }
}
