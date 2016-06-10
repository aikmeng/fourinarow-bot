package Playboard;

import amang.Playboard.Playboard;
import amang.Playboard.PlayboardPatternSearch;
import amang.Playboard.SearchResult;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayboardPatternSearchTest_SearchWinnerByColumnId {
    private static int MAX_COLUMN = 7;
    private static int MAX_ROW = 6;

    @Test
    public void testSearchWinnerByColumnId_boundary_1() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0";

        classUnderTest.parseFromString(fieldString);

        for(int columnId = 0; columnId < MAX_COLUMN; columnId++) {
            SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchWinnerByColumnId(0);
            assertEquals(PlayboardPatternSearch.UNUSED_SLOT, searchResult.getBotId());
            assertEquals(PlayboardPatternSearch.COLUMN_NOT_FOUND, searchResult.getColumnId());
        }
    }

    @Test
    public void testSearchWinnerByColumnId_horizontal_1() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,2,2,2,0,0,0";

        classUnderTest.parseFromString(fieldString);

        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchWinnerByColumnId(0);
        assertEquals(2, searchResult.getBotId());
        assertEquals(0, searchResult.getColumnId());
    }

    @Test
    public void testSearchWinnerByColumnId_horizontal_2() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,2,2,2,0";

        classUnderTest.parseFromString(fieldString);

        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchWinnerByColumnId(6);
        assertEquals(2, searchResult.getBotId());
        assertEquals(6, searchResult.getColumnId());
    }

    @Test
    public void testSearchWinnerByColumnId_horizontal_3() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,2,0,2,2,0";

        classUnderTest.parseFromString(fieldString);

        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchWinnerByColumnId(3);
        assertEquals(2, searchResult.getBotId());
        assertEquals(3, searchResult.getColumnId());
    }

    @Test
    public void testSearchWinnerByColumnId_vertical_up_1() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "2,0,0,0,0,0,0;" +
                                "2,0,0,0,0,0,0;" +
                                "2,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0";

        classUnderTest.parseFromString(fieldString);

        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchWinnerByColumnId(0);
        assertEquals(2, searchResult.getBotId());
        assertEquals(0, searchResult.getColumnId());
    }

    @Test
    public void testSearchWinnerByColumnId_vertical_up_2() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,2;" +
                                "0,0,0,0,0,0,2;" +
                                "0,0,0,0,0,0,2";

        classUnderTest.parseFromString(fieldString);

        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchWinnerByColumnId(6);
        assertEquals(2, searchResult.getBotId());
        assertEquals(6, searchResult.getColumnId());
    }

    @Test
    public void testSearchWinnerByColumnId_diagonal_up_1() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,2,0,0,0;" +
                                "0,0,2,1,0,0,0;" +
                                "0,2,1,1,0,0,0;" +
                                "0,1,2,1,0,0,0";

        classUnderTest.parseFromString(fieldString);

        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchWinnerByColumnId(0);
        assertEquals(2, searchResult.getBotId());
        assertEquals(0, searchResult.getColumnId());
    }

    @Test
    public void testSearchWinnerByColumnId_diagonal_up_2() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,2,2;" +
                                "0,0,0,0,2,2,1;" +
                                "0,0,0,2,1,1,2;" +
                                "0,0,1,1,2,2,1;" +
                                "0,1,2,1,2,1,2";

        classUnderTest.parseFromString(fieldString);

        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchWinnerByColumnId(6);
        assertEquals(2, searchResult.getBotId());
        assertEquals(6, searchResult.getColumnId());
    }

    @Test
    public void testSearchWinnerByColumnId_diagonal_up_3() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,2,2;" +
                                "0,0,0,0,0,2,1;" +
                                "0,0,0,2,1,1,2;" +
                                "0,0,2,1,2,2,1;" +
                                "0,1,2,1,2,1,2";

        classUnderTest.parseFromString(fieldString);

        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchWinnerByColumnId(4);
        assertEquals(2, searchResult.getBotId());
        assertEquals(4, searchResult.getColumnId());
    }

    @Test
    public void testSearchWinnerByColumnId_diagonal_down_1() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "2,2,0,0,0,0,0;" +
                                "1,2,2,0,0,0,0;" +
                                "1,1,2,2,0,0,0";

        classUnderTest.parseFromString(fieldString);

        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchWinnerByColumnId(0);
        assertEquals(2, searchResult.getBotId());
        assertEquals(0, searchResult.getColumnId());
    }

    @Test
    public void testSearchWinnerByColumnId_diagonal_down_2() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,2,0,0,0;" +
                                "0,0,0,1,2,0,0;" +
                                "0,0,0,2,1,2,0;" +
                                "0,0,0,1,2,2,0";

        classUnderTest.parseFromString(fieldString);

        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchWinnerByColumnId(6);
        assertEquals(2, searchResult.getBotId());
        assertEquals(6, searchResult.getColumnId());
    }

    @Test
    public void testSearchWinnerByColumnId_diagonal_down_3() {
        Playboard classUnderTest = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,2,0,0,0,0;" +
                                "0,0,1,2,0,0,0;" +
                                "0,0,2,1,0,0,0;" +
                                "0,0,1,2,1,2,0;" +
                                "0,0,2,1,2,2,0";

        classUnderTest.parseFromString(fieldString);

        SearchResult searchResult = classUnderTest.getPlayboardPatternSearch().searchWinnerByColumnId(4);
        assertEquals(2, searchResult.getBotId());
        assertEquals(4, searchResult.getColumnId());
    }

}
