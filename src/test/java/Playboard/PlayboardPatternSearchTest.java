package Playboard;

import amang.Playboard.Playboard;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayboardPatternSearchTest {
    private static int MAX_COLUMN = 7;
    private static int MAX_ROW = 6;

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
        assertEquals(0, classUnderTest.getPlayboardPatternSearch().getTopRowValue(3));
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
        assertEquals(-1, classUnderTest.getPlayboardPatternSearch().getTopRowValue(3));
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
        assertEquals(1, classUnderTest.getPlayboardPatternSearch().getTopRowValue(3));
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
        assertEquals(2, classUnderTest.getPlayboardPatternSearch().getTopRowValue(3));
    }
}
