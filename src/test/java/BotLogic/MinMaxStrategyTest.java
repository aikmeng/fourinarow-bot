package BotLogic;

import amang.BotLogic.MinMaxStrategy;
import amang.Playboard.Playboard;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MinMaxStrategyTest {
    private static int MAX_COLUMN = 7;
    private static int MAX_ROW = 6;

    @Before
    public void setUpStreams() {
        try {
            File file = new File("err.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            PrintStream printStream = new PrintStream(fileOutputStream);
            System.setErr(printStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void cleanUpStreams() {
        System.setErr(System.err);
    }

    @Test
    public void testMakeTurn_Bot_Id_One() {
        Playboard playboard = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0";
        playboard.parseFromString(fieldString);
        int botId = 1;

        MinMaxStrategy classUnderTest = new MinMaxStrategy(botId, playboard);
        assertEquals(3, classUnderTest.makeTurn());
    }

    @Test
    public void testMakeTurn_Bot_Id_Two() {
        Playboard playboard = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0";
        playboard.parseFromString(fieldString);
        int botId = 2;

        MinMaxStrategy classUnderTest = new MinMaxStrategy(botId, playboard);
        assertEquals(3, classUnderTest.makeTurn());
    }

    @Test
    public void testMakeTurn_Find_Winner_Column_case_1() {
        Playboard playboard = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0;" +
                                "1,2,2,0,0,2,0";
        playboard.parseFromString(fieldString);
        int botId = 1;

        MinMaxStrategy classUnderTest = new MinMaxStrategy(botId, playboard);
        assertEquals(0, classUnderTest.makeTurn());
    }

    @Test
    public void testMakeTurn_Block_Winner_Column_case_1() {
        Playboard playboard = new Playboard(MAX_COLUMN, MAX_ROW);
        String fieldString =    "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "0,0,0,0,0,0,0;" +
                                "1,0,0,0,0,0,0;" +
                                "1,2,2,0,2,1,0";
        playboard.parseFromString(fieldString);
        int botId = 1;

        MinMaxStrategy classUnderTest = new MinMaxStrategy(botId, playboard);
        assertEquals(3, classUnderTest.makeTurn());
    }
}
