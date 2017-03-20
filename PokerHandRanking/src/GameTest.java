import org.json.simple.JSONArray;
import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class GameTest {

    /*
    private  Game gm; // the class under test
    private Method m;
    private static String METHOD_NAME = "jsonArrayToPokerHand";
    private Class[] parameterTypes;
    private Object[] parameters;
    /**
     * Test the private method jsonArrayToPokerHand()
     * /
    public void setUp() throws Exception {
        gm = new Game();
        parameterTypes = new Class[1];
        parameterTypes[0] = java.lang.String.class;
        m = gm.getClass().getDeclaredMethod(METHOD_NAME, parameterTypes);
        m.setAccessible(true);
        parameters = new Object[1];

    }

    @Test
    private void testPrivateMethod() throws Exception {
        TestData td = new TestData();

        parameters[0] = td.getJsonArray0();
        PokerHand result = (PokerHand) m.invoke(gm, parameters);

        assertNotNull(result);


        PokerHand phExp = td.getPokerHand0();

        assertTrue("Test Data Set 0: Two PokerHands are Equal", phExp.compareTo(result) == 0);
        //Even the contents are same, these two PokerHands are different Objects if were built in different ways.
    }

    @Test
    private void jsonArrayToPokerHand1() throws Exception {



        TestData td = new TestData();
        PokerHand phExp = td.getPokerHand0();

        JSONArray ja = td.getJsonArray0();
        Game g = new Game();

        Field field = g.getClass().getDeclaredField("jsonArrayToPokerHand");
        field.setAccessible(true);
//        field.set(g, value);

        assertTrue("Test Data Set 0: Two PokerHands are Equal", phExp.compareTo(phExp) == 0);
        //Even the contents are same, these two PokerHands are different Objects if were built in different ways.
    }

    @Test
    private void jsonArrayToPokerHand2() throws Exception {
        TestData td = new TestData();
        PokerHand phExp = td.getPokerHand2();

        JSONArray ja = td.getJsonArray2();
        Game g = new Game();
        PokerHand phAns = g.jsonArrayToPokerHand(ja);

        assertTrue("Test Data Set 2: Return the PokerHand with same Cards", phExp.compareTo(phAns) == 0);
    }
    */

    @Test
    public void fiveCards1() throws Exception {
        TestData td = new TestData();
        String stringExp = td.getPokerHand0().evalToString();

        JSONArray ja = td.getJsonArray0();
        Game g = new Game();
        String ans = g.fiveCards(ja);

        assertEquals("Test Data Set 0: Test of fiveCard() passed", stringExp, ans);
    }

    @Test
    public void fiveCards2() throws Exception {
        JSONArray ja = new JSONArray();
        ja.add("8H");
        ja.add("4L"); //invalid suit
        ja.add("4S");
        ja.add("JC");
        ja.add("10D");
        Game g = new Game();
        String exp = "Not recognized suit in the card with index of 1";
        try {
            g.fiveCards(ja);
        } catch (IllegalArgumentException e) {
            assertEquals(exp, e.getMessage());
        }
    }

    @Test
    public void fiveCards3() throws Exception {
        JSONArray ja = new JSONArray();
        ja.add("8H");
        ja.add("1H"); //invalid number
        ja.add("4S");
        ja.add("JC");
        ja.add("10D");
        Game g = new Game();
        String exp = "Not recognized number in the card with index of 1";
        try {
            g.fiveCards(ja);
        } catch (IllegalArgumentException e) {
            assertEquals(exp, e.getMessage());
        }
    }

    @Test
    public void fiveCards4() throws Exception {
        JSONArray ja = new JSONArray();
        ja.add("8H");
        ja.add("8H"); //duplicated card
        ja.add("4S");
        ja.add("JC");
        ja.add("10D");
        Game g = new Game();
        String exp = "The existed card with index of 1";
        try {
            g.fiveCards(ja);
        } catch (IllegalArgumentException e) {
            assertEquals(exp, e.getMessage());
        }
    }

    @Test
    public void fiveCards5() throws Exception {
        JSONArray ja = new JSONArray();
        ja.add("8H");
        ja.add("7H");
        ja.add("4S");
        ja.add("JC"); //Less than 5 cards
        Game g = new Game();
        String exp = "The number of cards should be at least 5 and less than 53";
        try {
            g.fiveCards(ja);
        } catch (IllegalArgumentException e) {
            assertEquals(exp, e.getMessage());
        }
    }

    @Test
    public void compareManyHandsTest() throws Exception {
        System.out.println("============ Test compareManyHands() =========");
        TestData td = new TestData();
        JSONArray ja1 = td.getJsonArray0();
        JSONArray ja2 = td.getJsonArray2();
        JSONArray ja4 = td.getJsonArray4();
        System.out.println(ja1.toString());
        System.out.println(ja2.toString());
        System.out.println(ja4.toString());

        Game g  = new Game();
        JSONArray[] jaa = new JSONArray[3];
        jaa[0] = ja1; jaa[1] = ja2; jaa[2] = ja4;
        System.out.println(g.compareManyHands(jaa));
    }

    @Test
    public void compareTwoHandsTest() throws Exception {
        System.out.println("============ Test compareTwoHands() =========");
        TestData td = new TestData();
        JSONArray ja1 = td.getJsonArray0();
        JSONArray ja2 = td.getJsonArray2();
        System.out.println(ja1.toString());
        System.out.println(ja2.toString());

        Game g  = new Game();
        System.out.println(g.compareTwoHands(ja1, ja2));
    }
    @Test
    public void returnBestFiveCardHandTest() throws Exception {
        TestData td = new TestData();
        JSONArray ja1 = td.getJsonArray3();

        Game g  = new Game();
        String ans = g.returnBestFiveCardHand(ja1);
        String exp = td.getPokerHand3().evalToString();
        assertEquals("returnBestFiveCardHand() test ", exp, ans);
    }

}