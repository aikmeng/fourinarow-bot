package BotLogic;

import amang.BotLogic.MinMaxStrategy;
import amang.Playboard.Playboard;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinMaxStrategyTest {
    private static int MAX_COLUMN = 7;
    private static int MAX_ROW = 6;

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
}
