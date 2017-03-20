import static org.junit.Assert.*;

public class PokerHandTest {

    /*

    @org.junit.Test
    public void advSortByNumberTest() throws Exception {
        TestData td = new TestData();
        Card[] tmpCA = td.getCardArray3();
        PokerHand ph = new PokerHand(tmpCA);
        //After Sort By Number should be 13,12,12,12,12,11,11,10,4,4
        int[][] ans = ph.advSortByNumber(tmpCA);
        int[][] resultArray = {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {2, 8},
                {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0},{1, 7},
                {2, 5}, {4, 1}, {1, 0}, {0, 0}};

        assertArrayEquals(resultArray, ans);
    }

    @org.junit.Test
    public void advSortBySuitTest() throws Exception {
        TestData td1 = new TestData();
        Card[] tmpCA = td1.getCardArray3();
        PokerHand ph = new PokerHand(tmpCA);

        int[][] ans = ph.advSortBySuit(tmpCA);
        int[][] resultArray = {{0, 0}, {3, 7}, {3, 4}, {1, 3}, {3, 0}};

        int[][] ans2 = ph.advSortBySuit(td1.getCardArray1());
        int[][] resultArray2 = {{0, 0}, {1, 4}, {2, 2}, {0, 0}, {2, 0}};

        assertArrayEquals(resultArray2, ans2);
        assertArrayEquals(resultArray, ans);
    }
    */
    @org.junit.Test
    public void evalToStringTest() throws Exception {
        Card[] ca = new Card[5];
        ca[0] = new Card(11, 2);
        ca[1] = new Card(11, 4);
        ca[2] = new Card(10, 2);
        ca[3] = new Card(10, 4);
        ca[4] = new Card(10, 1);
        PokerHand ph1 = new PokerHand(ca[0], ca[1], ca[2], ca[3], ca[4]);

        Card[] caa = new Card[5];
        caa[0] = new Card(3, 2);
        caa[1] = new Card(2, 2);
        caa[2] = new Card(14, 2);
        caa[3] = new Card(4, 2);
        caa[4] = new Card(5, 2);
        PokerHand ph2 = new PokerHand(caa);

        System.out.println(ph1.toString());
        System.out.println(ph1.evalToString());
        System.out.println(ph2.toString());
        System.out.println(ph2.evalToString());
        System.out.println(ph1.compareTo(ph2));
    }

    @org.junit.Test
    public void evalFiveTest() throws Exception {
        TestData td = new TestData();
        PokerHand ph = td.getPokerHand4();
        Card[] caAns = ph.evalFive();

        assertEquals(2, ph.getRank());

        // ["8H", "4C", "4S", "JC", "10D"];
        Card[] ca = new Card[5];
        ca[0] = new Card(4, 4);
        ca[1] = new Card(4, 2);
        ca[2] = new Card(11, 2);
        ca[3] = new Card(10, 3);
        ca[4] = new Card(8, 1);
        PokerHand phExp = new PokerHand(ca);

        for (int i = 0;  i< 5; i++) {
            assertEquals(ca[i].getNumber(), caAns[i].getNumber());
            assertEquals(ca[i].getSuit(), caAns[i].getSuit());
        }
    }


    @org.junit.Test
    public void findBestHandTest10() throws Exception { //RoyalFlush
        Card[] ca = new Card[13];
        ca[0] = new Card(8, 2);
        ca[1] = new Card(14, 2);
        ca[2] = new Card(13, 2);
        ca[3] = new Card(11, 2);
        ca[4] = new Card(12, 2);
        ca[5] = new Card(10, 2);
        ca[6] = new Card(10, 3);
        ca[7] = new Card(10, 1);
        ca[8] = new Card(14, 4);
        ca[9] = new Card(13, 4);
        ca[10] = new Card(11, 4);
        ca[11] = new Card(12, 4);
        ca[12] = new Card(10, 4);
        PokerHand pk = new PokerHand(ca);
        System.out.println(pk.evalToString());
    }

    @org.junit.Test
    public void findBestHandTest9() throws Exception { //StraightFlush
        Card[] ca = new Card[13];
        ca[0] = new Card(8, 2);
        ca[1] = new Card(14, 2);
        ca[2] = new Card(3, 2);
        ca[3] = new Card(11, 2);
        ca[4] = new Card(12, 2);
        ca[5] = new Card(10, 2);
        ca[6] = new Card(9, 2);
        ca[7] = new Card(10, 1);
        ca[8] = new Card(4, 1);
        ca[9] = new Card(3, 1);
        ca[10] = new Card(14, 1);
        ca[11] = new Card(2, 1);
        ca[12] = new Card(5, 1);
        PokerHand pk = new PokerHand(ca);
        System.out.println(pk.evalToString());
    }

    @org.junit.Test
    public void findBestHandTest8() throws Exception { //Four of a kind
        Card[] ca = new Card[11];
        ca[0] = new Card(8, 1);
        ca[1] = new Card(4, 2);
        ca[2] = new Card(4, 4);
        ca[3] = new Card(11, 2);
        ca[4] = new Card(4, 3);
        ca[5] = new Card(4, 1);
        ca[6] = new Card(5, 2);
        ca[7] = new Card(10, 1);
        ca[8] = new Card(8, 2);
        ca[9] = new Card(8, 3);
        ca[10] = new Card(8, 4);
        PokerHand pk = new PokerHand(ca);
        System.out.println(pk.evalToString());
    }

    @org.junit.Test
    public void findBestHandTest7() throws Exception { //FullHouse
        Card[] ca = new Card[13];
        ca[0] = new Card(8, 2);
        ca[1] = new Card(8, 1);
        ca[2] = new Card(8, 4);
        ca[3] = new Card(11, 2);
        ca[4] = new Card(12, 4);
        ca[5] = new Card(10, 2);
        ca[6] = new Card(5, 2);
        ca[7] = new Card(10, 1);
        ca[8] = new Card(4, 1);
        ca[9] = new Card(3, 1);
        ca[10] = new Card(14, 1);
        ca[11] = new Card(5, 3);
        ca[12] = new Card(5, 1);
        PokerHand pk = new PokerHand(ca);
        System.out.println(pk.evalToString());
    }

    @org.junit.Test
    public void findBestHandTest6() throws Exception { //Flush
        Card[] ca = new Card[13];
        ca[0] = new Card(8, 2);
        ca[1] = new Card(14, 2);
        ca[2] = new Card(3, 2);
        ca[3] = new Card(2, 2);
        ca[4] = new Card(12, 2);
        ca[5] = new Card(10, 2);
        ca[6] = new Card(9, 2);
        ca[7] = new Card(10, 1);
        ca[8] = new Card(4, 1);
        ca[9] = new Card(3, 1);
        ca[10] = new Card(8, 1);
        ca[11] = new Card(2, 1);
        ca[12] = new Card(5, 1);
        PokerHand pk = new PokerHand(ca);
        System.out.println(pk.evalToString());
    }

    @org.junit.Test
    public void findBestHandTest5() throws Exception { //Flush
        Card[] ca = new Card[13];
        ca[0] = new Card(8, 2);
        ca[1] = new Card(14, 3);
        ca[2] = new Card(7, 2);
        ca[3] = new Card(6, 4);
        ca[4] = new Card(12, 2);
        ca[5] = new Card(10, 3);
        ca[6] = new Card(9, 2);
        ca[7] = new Card(10, 1);
        ca[8] = new Card(4, 1);
        ca[9] = new Card(3, 4);
        ca[10] = new Card(8, 1);
        ca[11] = new Card(2, 4);
        ca[12] = new Card(5, 1);
        PokerHand pk = new PokerHand(ca);
        System.out.println(pk.evalToString());
    }

    @org.junit.Test
    public void findBestHandTest4() throws Exception { //Three of a kind
        Card[] ca = new Card[9];
        ca[0] = new Card(8, 2);
        ca[1] = new Card(14, 3);
        ca[2] = new Card(8, 1);
        ca[3] = new Card(8, 4);
        ca[4] = new Card(12, 2);
        ca[5] = new Card(12, 3);
        ca[6] = new Card(9, 2);
        ca[7] = new Card(12, 1);
        ca[8] = new Card(10, 4);
        PokerHand pk = new PokerHand(ca);
        System.out.println(pk.evalToString());
    }

    @org.junit.Test
    public void findBestHandTest3() throws Exception { //Two Pair
        Card[] ca = new Card[9];
        ca[0] = new Card(13, 2);
        ca[1] = new Card(4, 3);
        ca[2] = new Card(10, 1);
        ca[3] = new Card(8, 4);
        ca[4] = new Card(12, 2);
        ca[5] = new Card(7, 3);
        ca[6] = new Card(9, 2);
        ca[7] = new Card(12, 1);
        ca[8] = new Card(10, 4);
        PokerHand pk = new PokerHand(ca);
        System.out.println(pk.evalToString());
    }
    @org.junit.Test
    public void findBestHandTest2() throws Exception { //One Pair
        Card[] ca = new Card[7];
        ca[0] = new Card(11, 2);
        ca[1] = new Card(11, 3);
        ca[2] = new Card(10, 1);
        ca[3] = new Card(14, 4);
        ca[4] = new Card(12, 2);
        ca[5] = new Card(7, 3);
        ca[6] = new Card(9, 2);
        PokerHand pk = new PokerHand(ca);
        System.out.println(pk.evalToString());
    }

    @org.junit.Test
    public void findBestHandTest1() throws Exception { //High card
        Card[] ca = new Card[7];
        ca[0] = new Card(11, 2);
        ca[1] = new Card(2, 3);
        ca[2] = new Card(10, 1);
        ca[3] = new Card(14, 4);
        ca[4] = new Card(12, 2);
        ca[5] = new Card(7, 3);
        ca[6] = new Card(9, 2);
        PokerHand pk = new PokerHand(ca);
        System.out.println(pk.evalToString());
    }

}