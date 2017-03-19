import org.json.simple.JSONArray;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void fiveCard1() throws Exception {

    }

    @Test
    public void jsonArrayToPokerHand1() throws Exception {
        TestData td = new TestData();
        PokerHand phExp = td.getPokerHand0();

        JSONArray ja = td.getJsonArray0();
        Game g = new Game();
        PokerHand ans = g.jsonArrayToPokerHand(ja);

        assertTrue("Two PokerHands are Equal", phExp.compareTo(ans) == 0);
        //Even the contents are same, these two PokerHands are different Objects if were built in different ways.
    }

    @Test
    public void jsonArrayToPokerHand2() throws Exception {
        TestData td = new TestData();
        PokerHand phExp = td.getPokerHand2();

        JSONArray ja = td.getJsonArray2();
        Game g = new Game();
        PokerHand phAns = g.jsonArrayToPokerHand(ja);

        assertTrue("Return the PokerHand with same Cards", phExp.compareTo(phAns) == 0);
    }

}