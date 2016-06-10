package Playboard;

import amang.Playboard.Playboard;
import amang.Playboard.PlayboardPatternSearch;
import amang.Playboard.SearchResult;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayboardPatternSearchTest_SearchVerticalWinnerColumn {
    private static int MAX_COLUMN = 7;
    private static int MAX_ROW = 6;

    @Test
    public void testSearchVerticalWinnerColumn_boundary_1() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0";
        classUnderTest.parseFromString(fieldString);
        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchVerticalWinnerColumn();
        assertEquals(PlayboardPatternSearch.UNUSED_SLOT, searchResult.getBotId());
        assertEquals(PlayboardPatternSearch.COLUMN_NOT_FOUND, searchResult.getColumnId());
    }

    @Test
    public void testSearchVerticalWinnerColumn_boundary_2() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "2,0,0,0,0,0,0;" +
                                "2,0,0,0,0,0,0;" +
                                "2,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0";
        classUnderTest.parseFromString(fieldString);
        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchVerticalWinnerColumn();
        assertEquals(PlayboardPatternSearch.UNUSED_SLOT, searchResult.getBotId());
        assertEquals(PlayboardPatternSearch.COLUMN_NOT_FOUND, searchResult.getColumnId());
    }

    @Test
    public void testSearchVerticalWinnerColumn_case_1() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0";
        classUnderTest.parseFromString(fieldString);
        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchVerticalWinnerColumn();
        assertEquals(1, searchResult.getBotId());
        assertEquals(0, searchResult.getColumnId());
    }

    @Test
    public void testSearchVerticalWinnerColumn_case_2() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "2,0,0,0,0,0,0;" +
                                "2,0,0,0,0,0,0;" +
                                "2,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0";
        classUnderTest.parseFromString(fieldString);
        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchVerticalWinnerColumn();
        assertEquals(2, searchResult.getBotId());
        assertEquals(0, searchResult.getColumnId());
    }

    @Test
    public void testSearchVerticalWinnerColumn_case_3() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0;" +
                                "2,0,0,0,0,0,0;" +
                                "2,0,0,0,0,0,0";
        classUnderTest.parseFromString(fieldString);
        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchVerticalWinnerColumn();
        assertEquals(1, searchResult.getBotId());
        assertEquals(0, searchResult.getColumnId());
    }

    @Test
    public void testSearchVerticalWinnerColumn_case_4() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "2,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0;" +
                                "1,2,0,0,0,0,0;" +
                                "2,2,0,0,0,0,0;" +
                                "2,2,0,0,0,0,0";
        classUnderTest.parseFromString(fieldString);
        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchVerticalWinnerColumn();
        assertEquals(2, searchResult.getBotId());
        assertEquals(1, searchResult.getColumnId());
    }

    @Test
    public void testSearchVerticalWinnerColumn_case_5() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "2,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,1;" +
                                "1,1,0,0,0,0,1;" +
                                "1,2,0,0,0,0,1;" +
                                "2,2,0,0,0,0,2;" +
                                "2,2,0,0,0,0,2";
        classUnderTest.parseFromString(fieldString);
        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchVerticalWinnerColumn();
        assertEquals(1, searchResult.getBotId());
        assertEquals(6, searchResult.getColumnId());
    }
}
