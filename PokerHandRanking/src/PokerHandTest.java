import org.junit.Test;
import static org.junit.Assert.*;

public class PokerHandTest {

    @org.junit.Test
    public void advSortByNumberTest() throws Exception {
        TestData td = new TestData();
        Card[] tmpCA = td.getCardArray3();
        PokerHand ph = new PokerHand(tmpCA);
        int[] ans = ph.advSortByNumber(tmpCA);
        int[] resultArray = {0,0,0,0,2,0,0,0,0,0,1,2,4,1,0};

        assertArrayEquals(resultArray, ans);
    }

    @org.junit.Test
    public void advSortBySuit() throws Exception {
        TestData td1 = new TestData();
        Card[] tmpCA = td1.getCardArray3();
        PokerHand ph = new PokerHand(tmpCA);

        int[] ans = ph.advSortBySuit(tmpCA);
        int[] resultArray = {0,3,3,1,3};

        TestData td2 = new TestData();
        int[] ans2 = ph.advSortBySuit(td2.getCardArray1());
        int[] resultArray2 = {0,1,2,0,2};

        assertArrayEquals(resultArray2, ans2);
        assertArrayEquals(resultArray, ans);
    }



}