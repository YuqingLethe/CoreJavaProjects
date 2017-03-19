import org.json.simple.JSONArray;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void fiveCard1() throws Exception {
        TestData td = new TestData();
        String stringExp = td.getPokerHand0().toString();

        JSONArray ja = td.getJsonArray0();
        Game g = new Game();
        String ans = g.fiveCard(ja);

        assertEquals("Test Data Set 0: Test of fiveCard()", stringExp, ans);
    }

    @Test
    public void jsonArrayToPokerHand1() throws Exception {
        TestData td = new TestData();
        PokerHand phExp = td.getPokerHand0();

        JSONArray ja = td.getJsonArray0();
        Game g = new Game();
        PokerHand ans = g.jsonArrayToPokerHand(ja);

        assertTrue("Test Data Set 0: Two PokerHands are Equal", phExp.compareTo(ans) == 0);
        //Even the contents are same, these two PokerHands are different Objects if were built in different ways.
    }

    @Test
    public void jsonArrayToPokerHand2() throws Exception {
        TestData td = new TestData();
        PokerHand phExp = td.getPokerHand2();

        JSONArray ja = td.getJsonArray2();
        Game g = new Game();
        PokerHand phAns = g.jsonArrayToPokerHand(ja);

        assertTrue("Test Data Set 2: Return the PokerHand with same Cards", phExp.compareTo(phAns) == 0);
    }

}