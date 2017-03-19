import org.json.simple.JSONArray;


public class Game {
    public String fiveCard (JSONArray ja) {
        PokerHand ph = this.jsonArrayToPokerHand(ja);
        return ph.evalToString();
    }

    public PokerHand jsonArrayToPokerHand(JSONArray ja) {
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
//        JSONArray ja = new JSONArray()["JH", "4C", "4S", "JC", "9H"];
        TestData td = new TestData();
        JSONArray ja1 = td.getJsonArray1();
        System.out.println(ja1.toString());

        Game g  = new Game();
        System.out.println(g.fiveCard(ja1));
    }
}
