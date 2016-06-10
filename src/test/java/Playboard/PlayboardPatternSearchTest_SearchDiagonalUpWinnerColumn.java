package Playboard;

import amang.Playboard.Playboard;
import amang.Playboard.PlayboardPatternSearch;
import amang.Playboard.SearchResult;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayboardPatternSearchTest_SearchDiagonalUpWinnerColumn {
    private static int MAX_COLUMN = 7;
    private static int MAX_ROW = 6;

    @Test
    public void testSearchDiagonalUpWinnerColumn_boundary_1() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0";
        classUnderTest.parseFromString(fieldString);
        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchDiagonalUpWinnerColumn();
        assertEquals(PlayboardPatternSearch.COLUMN_UNUSED, searchResult.getBotId());
        assertEquals(PlayboardPatternSearch.COLUMN_NOT_FOUND, searchResult.getColumnId());
    }

    @Test
    public void testSearchDiagonalUpWinnerColumn_negative_case_1() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,1,0,0,0;" +
                                "0,0,1,1,0,0,0;" +
                                "0,2,2,2,0,0,0;" +
                                "1,1,2,2,0,0,0";
        classUnderTest.parseFromString(fieldString);
        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchDiagonalUpWinnerColumn();
        assertEquals(PlayboardPatternSearch.COLUMN_UNUSED, searchResult.getBotId());
        assertEquals(PlayboardPatternSearch.COLUMN_NOT_FOUND, searchResult.getColumnId());
    }

    @Test
    public void testSearchDiagonalUpWinnerColumn_negative_case_2() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,1,0,0,0;" +
                                "0,0,2,2,0,0,0;" +
                                "0,1,2,1,2,2,1;" +
                                "1,2,1,2,2,1,1";
        classUnderTest.parseFromString(fieldString);
        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchDiagonalUpWinnerColumn();
        assertEquals(PlayboardPatternSearch.COLUMN_UNUSED, searchResult.getBotId());
        assertEquals(PlayboardPatternSearch.COLUMN_NOT_FOUND, searchResult.getColumnId());
    }

    @Test
    public void testSearchDiagonalUpWinnerColumn_negative_case_3() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,1,0,0,0;" +
                                "0,0,1,2,0,0,0;" +
                                "0,0,2,1,0,0,0;" +
                                "1,0,1,2,0,2,1";
        classUnderTest.parseFromString(fieldString);
        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchDiagonalUpWinnerColumn();
        assertEquals(PlayboardPatternSearch.COLUMN_UNUSED, searchResult.getBotId());
        assertEquals(PlayboardPatternSearch.COLUMN_NOT_FOUND, searchResult.getColumnId());
    }

    @Test
    public void testSearchDiagonalUpWinnerColumn_case_1() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,1,0,0,0;" +
                                "0,0,1,2,0,0,0;" +
                                "0,0,2,1,0,0,0;" +
                                "1,1,1,2,0,0,0";
        classUnderTest.parseFromString(fieldString);
        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchDiagonalUpWinnerColumn();
        assertEquals(1, searchResult.getBotId());
        assertEquals(1, searchResult.getColumnId());
    }

    @Test
    public void testSearchDiagonalUpWinnerColumn_case_2() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,2,0,0;" +
                                "0,0,0,2,1,0,0;" +
                                "0,0,2,1,2,0,0;" +
                                "0,0,1,2,2,2,0";
        classUnderTest.parseFromString(fieldString);
        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchDiagonalUpWinnerColumn();
        assertEquals(2, searchResult.getBotId());
        assertEquals(1, searchResult.getColumnId());
    }

    @Test
    public void testSearchDiagonalUpWinnerColumn_case_3() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,1,0,0;" +
                                "0,0,0,2,2,1,0;" +
                                "0,0,1,2,2,2,0;" +
                                "1,1,2,1,1,2,2";
        classUnderTest.parseFromString(fieldString);
        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchDiagonalUpWinnerColumn();
        assertEquals(2, searchResult.getBotId());
        assertEquals(5, searchResult.getColumnId());
    }

    @Test
    public void testSearchDiagonalUpWinnerColumn_case_4() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,1;" +
                                "0,0,0,0,0,0,1;" +
                                "0,0,0,0,1,2,2;" +
                                "1,2,2,1,1,1,2;" +
                                "2,1,2,1,2,1,1";
        classUnderTest.parseFromString(fieldString);
        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchDiagonalUpWinnerColumn();
        assertEquals(1, searchResult.getBotId());
        assertEquals(5, searchResult.getColumnId());
    }

    @Test
    public void testSearchDiagonalUpWinnerColumn_case_5() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,2,2;" +
                                "0,0,0,0,2,1,1;" +
                                "0,1,0,2,1,1,2;" +
                                "2,2,1,1,2,1,1";
        classUnderTest.parseFromString(fieldString);
        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchDiagonalUpWinnerColumn();
        assertEquals(2, searchResult.getBotId());
        assertEquals(6, searchResult.getColumnId());
    }

    @Test
    public void testSearchDiagonalUpWinnerColumn_case_6() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,1,0,0,0;" +
                                "0,0,1,1,0,0,0;" +
                                "0,1,2,2,1,2,2;" +
                                "0,2,1,2,2,1,1;" +
                                "2,1,2,1,2,1,2;" +
                                "2,2,1,1,2,1,1";
        classUnderTest.parseFromString(fieldString);
        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchDiagonalUpWinnerColumn();
        assertEquals(1, searchResult.getBotId());
        assertEquals(0, searchResult.getColumnId());
    }
}