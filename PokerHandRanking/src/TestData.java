import org.json.simple.JSONArray;

public class TestData {

    public JSONArray getJsonArray0() {
        // ["JH", "4C", "4S", "JC", "9H"];
        JSONArray ja = new JSONArray();
        ja.add("JH");
        ja.add("4C");
        ja.add("4S");
        ja.add("JC");
        ja.add("9H");
        return ja;
    }

    public Card[] getCardArray0() {
        Card[] ca = new Card[5];
        ca[0] = new Card(11, 1);
        ca[1] = new Card(4, 2);
        ca[2] = new Card(4, 4);
        ca[3] = new Card(11, 2);
        ca[4] = new Card(9, 1);
        return ca;
    }

    public PokerHand getPokerHand0() {
        Card[] ca = getCardArray0();
        return new PokerHand(ca);
    }


    public JSONArray getJsonArray1() {
        JSONArray ja = new JSONArray();
        ja.add("KC");
        ja.add("JS");
        ja.add("4C");
        ja.add("10S");
        ja.add("QH");
        return ja;
    }

    public Card[] getCardArray1() {
        Card[] ca = new Card[5];
        ca[0] = new Card(13, 2);
        ca[1] = new Card(11, 4);
        ca[2] = new Card(4, 2);
        ca[3] = new Card(10, 4);
        ca[4] = new Card(12, 1);
        return ca;
    }

    public PokerHand getPokerHand1() {
        Card[] ca = getCardArray1();
        PokerHand ph1 = new PokerHand(ca[0], ca[1], ca[2], ca[3], ca[4]);
        return ph1;
    }

    public JSONArray getJsonArray2() {
        // ["JH", "4C", "4S", "JC", "10D"];
        JSONArray ja = new JSONArray();
        ja.add("JH");
        ja.add("4C");
        ja.add("4S");
        ja.add("JC");
        ja.add("10D");
        return ja;
    }
    public Card[] getCardArray2() {
        // ["JH", "4C", "4S", "JC", "10D"];
        Card[] ca = new Card[5];
        ca[0] = new Card(11, 1);
        ca[1] = new Card(4, 2);
        ca[2] = new Card(4, 4);
        ca[3] = new Card(11, 2);
        ca[4] = new Card(10, 3);
        return ca;
    }

    public PokerHand getPokerHand2() {
        // ["JH", "4C", "4S", "JC", "10D"];
        Card[] ca = getCardArray2();
        return new PokerHand(ca);
    }

    public JSONArray getJsonArray3() {
        JSONArray ja = new JSONArray();
        ja.add("KC");
        ja.add("JS");
        ja.add("4C");
        ja.add("10S");
        ja.add("QH");
        ja.add("QS");
        ja.add("QC");
        ja.add("QD");
        ja.add("4H");
        ja.add("JH");
        return ja;
    }

    public Card[] getCardArray3() {
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
        return manyca;
    }

    public PokerHand getPokerHand3() {
        Card[] ca = getCardArray3();
        PokerHand ph3 = new PokerHand(ca);
        return ph3;
    }

    public JSONArray getJsonArray4() {
        // ["8H", "4C", "4S", "JC", "10D"];
        JSONArray ja = new JSONArray();
        ja.add("8H");
        ja.add("4C");
        ja.add("4S");
        ja.add("JC");
        ja.add("10D");
        return ja;
    }
    public Card[] getCardArray4() {
        // ["8H", "4C", "4S", "JC", "10D"];
        Card[] ca = new Card[5];
        ca[0] = new Card(8, 1);
        ca[1] = new Card(4, 2);
        ca[2] = new Card(4, 4);
        ca[3] = new Card(11, 2);
        ca[4] = new Card(10, 3);
        return ca;
    }

    public PokerHand getPokerHand4() {
        // ["8H", "4C", "4S", "JC", "10D"];
        Card[] ca = getCardArray4();
        return new PokerHand(ca);
    }
}
