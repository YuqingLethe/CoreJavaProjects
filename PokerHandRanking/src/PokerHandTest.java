import static org.junit.Assert.*;

/**
 * Created by yuqing on 3/17/17.
 */
public class PokerHandTest {

    @org.junit.Test
    public void advSortByNumberTest() throws Exception {
        Card[] tmpCA = getCardArray3();
        PokerHand ph = new PokerHand(tmpCA);
        int[] ans = ph.advSortByNumber(tmpCA);
        int[] resultArray = {0,0,0,0,2,0,0,0,0,0,1,2,4,1,0};

        assertArrayEquals(resultArray, ans);
    }

    @org.junit.Test
    public void advSortBySuit() throws Exception {
        Card[] tmpCA = getCardArray3();
        PokerHand ph = new PokerHand(tmpCA);

        int[] ans = ph.advSortBySuit(tmpCA);
        int[] resultArray = {0,3,3,1,3};

        int[] ans2 = ph.advSortBySuit(getCardArray1());
        int[] resultArray2 = {0,1,2,0,2};

        assertArrayEquals(resultArray2, ans2);
        assertArrayEquals(resultArray, ans);
    }

    private Card[] getCardArray1() {
        Card[] ca = new Card[5];
        ca[0] = new Card(13, 2);
        ca[1] = new Card(11, 4);
        ca[2] = new Card(4, 2);
        ca[3] = new Card(10, 4);
        ca[4] = new Card(12, 1);
        PokerHand ph1 = new PokerHand(ca[0], ca[1], ca[2], ca[3], ca[4]);
        return ca;
    }

    private PokerHand getPokerHand1() {
        Card[] ca = getCardArray1();
        PokerHand ph1 = new PokerHand(ca[0], ca[1], ca[2], ca[3], ca[4]);
        return ph1;
    }


    private Card[] getCardArray3() {
        Card[] manyca = new Card[10];
        manyca[0] = new Card(13, 2);
        manyca[1] = new Card(11, 4);
        manyca[2] = new Card(4, 2);
        manyca[3] = new Card(10, 4);
        manyca[4] = new Card(12, 1);
        manyca[5] = new Card(12, 4);
        manyca[6] = new Card(12, 2);
        manyca[7] = new Card(12, 3);
        manyca[8] = new Card(4, 1);
        manyca[9] = new Card(11, 1);

        PokerHand ph3 = new PokerHand(manyca);

        return manyca;
    }

}