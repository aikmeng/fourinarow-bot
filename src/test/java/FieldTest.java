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
        String fieldString = "1,1,1,1,1,1,1;1,1,1,1,1,1,1;1,1,1,1,1,1,1;1,1,1,1,1,1,1;1,1,1,1,1,1,1;1,1,1,1,1,1,1";
        classUnderTest.parseFromString(fieldString);

        for(int currentRow = MAX_ROW - 1; currentRow >= 0; currentRow--) {
            for(int currentColumn = 0; currentColumn < MAX_ROW; currentColumn++) {
                assertEquals(1, classUnderTest.getDisc(currentColumn, currentRow));
            }
        }
    }
}
