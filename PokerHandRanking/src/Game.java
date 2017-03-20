import org.json.simple.JSONArray;


public class Game {
    /**
     * Takes a 5-card hand as a JSON array and determines its category (Task #1)
     * @param ja the 5-card hand
     * @return the ranking information and evaluation cards information
     */
    public String fiveCards (JSONArray ja) {
        PokerHand ph = this.jsonArrayToPokerHand(ja);
        return ph.evalToString();
    }

    /**
     * Takes two 5-card hands and determines the winner. (Task #2)
     * @param ja1 the first 5-card hand
     * @param ja2 the second 5-card hand
     * @return the string of the result and evaluation cards
     */
    public String compareTwoHands(JSONArray ja1, JSONArray ja2) {
        String ans = "";
        PokerHand ph1 = this.jsonArrayToPokerHand(ja1);
        PokerHand ph2 = this.jsonArrayToPokerHand(ja2);
        int cmp = ph1.compareTo(ph2);
        if (cmp > 0) {
            ans += "First Hand ranks higher: \n";
        } else if (cmp < 0) {
            ans += "Second Hand ranks higher: \n";
        } else {
            ans += "Two Hands end in a tie: \n";
        }
        ans += ph1.evalToString() + "\n";
        ans += ph2.evalToString() + "\n";
        return ans;
    }
    public String compareManyHands(JSONArray[] jaa) {
        PokerHand highest = this.jsonArrayToPokerHand(jaa[0]);
        int highestIndex = 0;
        for (int i = 1; i < jaa.length; i++) {
            PokerHand ph = this.jsonArrayToPokerHand(jaa[i]);
            if (highest.compareTo(ph) < 0) {
                highest = ph;
                highestIndex = i;
            }
        }
        String ans = "The i " + "th hand ranks highest: ";
        ans += highest.evalToString();
        return ans;
    }

    private PokerHand jsonArrayToPokerHand(JSONArray ja) {
        Object[] oa = ja.toArray();
        Card[] ca = new Card[oa.length];

        for (int i = 0; i < oa.length; i++) {
            int num = 0, sui = 0; //The number and suit of the Card

            char suit;
            if (oa[i].toString().length() == 3) {
                num = 10;
                suit = oa[i].toString().charAt(2);
            } else {
                //Determine the Card num
                char number = oa[i].toString().charAt(0);
                suit = oa[i].toString().charAt(1);
                if (Character.isDigit(number)) {
                    num = Character.getNumericValue(number);
                } else {
                    switch (number) {
                        case 'J':
                            num = 11;
                            break;
                        case 'Q':
                            num = 12;
                            break;
                        case 'K':
                            num = 13;
                            break;
                        case 'A':
                            num = 14;
                            break;
                        default:
                            System.out.println("Not recognized number in the " + i + "th card.");
                    }
                }
            }
            //Determine the Card suit
            switch(suit) {
                case 'H':
                    sui = 1;
                    break;
                case 'C':
                    sui =  2;
                    break;
                case 'D':
                    sui = 3;
                    break;
                case 'S':
                    sui = 4;
                    break;
                default:
                    System.out.println("Not recognized suit in the " + i + "th card.");
            };

            ca[i] = new Card(num, sui);
        }
        PokerHand ph = new PokerHand(ca);
        return ph;
    }

    public static void main(String[] args) {
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
}
