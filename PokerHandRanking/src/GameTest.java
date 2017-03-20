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

}