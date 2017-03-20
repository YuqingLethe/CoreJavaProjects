import org.json.simple.JSONArray;

import java.util.HashSet;


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
     * @return the string of the result and evaluation information
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

    /**
     * Takes 5-card hands JSONArray array and determines the winner. (Task #2)
     * @param jaa the array of JSONArray
     * @return the index of the winner hand and the evaluation information
     */
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
        String ans = "The " + (highestIndex + 1) + "th hand ranks highest: ";
        ans += highest.evalToString();
        return ans;
    }

    /**
     * Takes 5 or more cards and returns the best 5-card hand that can be made with those cards. (Task #3)
     * @param ja the JSONArray
     * @return the evaluation information of the best 5 cards.
     */
    public String returnBestFiveCardHand(JSONArray ja) {
        PokerHand ph = this.jsonArrayToPokerHand(ja);
        return ph.evalToString();
    }

    /**
     * Validate input as JSONArray and return them as PokerHand; Throw errors if illegal card found.
     * @param ja JSONArray input
     * @return PokerHand built from the argument
     */
    private PokerHand jsonArrayToPokerHand(JSONArray ja) {
        validateDuplicatedCard(ja);

        if (ja.size() < 5 || ja.size() > 52)
            throw new IllegalArgumentException("The number of cards should be at least 5 and less than 53");

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
                            throw new IllegalArgumentException("Not recognized number of the card with index of " + i);
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
                    throw new IllegalArgumentException("Not recognized suit in the card with index of " + i);
            }

            ca[i] = new Card(num, sui);
        }
        PokerHand ph = new PokerHand(ca);
        return ph;
    }

    /**
     * Check if duplicated card existed in JSONArray input
     * @param ja the input as JSONArray
     */
    private void validateDuplicatedCard (JSONArray ja) {
        Object[] oa = ja.toArray();

        HashSet<Object> hs = new HashSet<>();
        for (int i = 0; i < oa.length; i++) {
            if (hs.contains(oa[i])) {
                throw new IllegalArgumentException("The existed card with index of " + i);
            } else {
                hs.add(oa[i]);
            }
        }
    }

}
