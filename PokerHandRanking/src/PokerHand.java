import java.util.HashMap;

public class PokerHand {
    private Card[] hand;
    private Card[] evalHand;
    private int rank;

    public PokerHand(Card c1, Card c2, Card c3, Card c4, Card c5) {
        this.hand = new Card[5];
        this.hand[0] = c1;
        this.hand[1] = c2;
        this.hand[2] = c3;
        this.hand[3] = c4;
        this.hand[4] = c5; //todo: Use HashSet to check duplicated cards
        this.evalHand = new Card[5];
        this.evalHand = evalFive();
    }


    public PokerHand(Card[] moreCards) {
        int len = moreCards.length;
        this.hand = new Card[len];
        for (int i = 0; i < len; i++) {
            this.hand[i] = moreCards[i];
        }
        this.evalHand = new Card[5];
        this.evalHand = evalFive();
    }

    /**
     * Get the evalHand card array with the ranking evaluation order
     * @return the card array evalHand
     */
    public Card[] getEvalHand() {
        return this.evalHand;
    }

    /**
     * Get the rank order, from 1 High Card to 10 Royal Straight
     * @return the integer indicating the rank
     */
    public int getRank() {
        return this.rank;
    }

    /**
     * Evaluate the rank of the five-card hand, and build the evalHand card array to compare the rank
     * @return the Card array to evalHand
     */
    public Card[] evalFive() {
        Card[] sortedHandByNumber = duplicateHand(this.hand);
        this.sortByNumber(sortedHandByNumber);
        // Set the HashMap to mark the order of evalHand
        // Key is the index of sortedHandByNumber array. Value is the position in evalHand array
        HashMap<Integer, Integer> cardHM = new HashMap<>();
        for (int i = 0; i < 5; i++)
            cardHM.put(i, 5);


        // Assume it's high card
        rank = 1;
        int firstPairIndex = 0;
        int secondPairIndex = 0;

        //One Pair
        for (int i = 0; i < 4; i++) {
            if (sortedHandByNumber[i].getNumber() == sortedHandByNumber[i + 1].getNumber()) {
                rank = 2;
                cardHM.replace(i, 0);
                cardHM.replace(i + 1, 1);
                firstPairIndex = i + 1;
                break;
            }
        }

        //Two Pairs
        if (rank == 2) {
            for (int i = firstPairIndex + 1; i < 4; i++) {
                if (sortedHandByNumber[i].getNumber() == sortedHandByNumber[i + 1].getNumber()) { //Two Pairs
                    rank = 3;
                    cardHM.replace(i, 2);
                    cardHM.replace(i + 1, 3);
                    secondPairIndex = i + 1;
                    break;
                }
            }
        }

        //Four of a kind or Full House
        if (rank == 3) {
            if (sortedHandByNumber[firstPairIndex].getNumber() == sortedHandByNumber[secondPairIndex].getNumber()) {
                rank = 8;
            } else {
                if (firstPairIndex == 1
                    && secondPairIndex == 3
                    && sortedHandByNumber[secondPairIndex].getNumber()
                        == sortedHandByNumber[secondPairIndex + 1].getNumber()) {
                    rank = 7;
                    cardHM.replace(secondPairIndex - 1, 0);
                    cardHM.replace(secondPairIndex, 1);
                    cardHM.replace(secondPairIndex + 1, 2);
                    cardHM.replace(firstPairIndex - 1, 3);
                    cardHM.replace(firstPairIndex, 4);
                }
                if (firstPairIndex == 1
                    && secondPairIndex == 4
                    && sortedHandByNumber[firstPairIndex].getNumber()
                        == sortedHandByNumber[firstPairIndex + 1].getNumber()) {
                    rank = 7;
                    cardHM.replace(firstPairIndex + 1, 2);
                    cardHM.replace(secondPairIndex - 1, 3);
                    cardHM.replace(secondPairIndex, 4);
                }
            }
        }
        //Three of a Kind
        if (rank == 2
            && firstPairIndex < 4
            && sortedHandByNumber[firstPairIndex].getNumber() == sortedHandByNumber[firstPairIndex + 1].getNumber()
            ) {
                rank = 4;
                cardHM.replace(firstPairIndex + 1, 2);
        }


        if (rank == 1) {
            //Straight
            if (sortedHandByNumber[0].getNumber() - 4 == sortedHandByNumber[4].getNumber()) {
                rank = 5;
                evalHand = duplicateHand(sortedHandByNumber);
            } else if (sortedHandByNumber[0].getNumber() == 14
                    && sortedHandByNumber[1].getNumber() - 3 == sortedHandByNumber[4].getNumber()) {
                rank = 5;
                for (int i = 1; i < 5; i++) {
                    evalHand[i - 1] = sortedHandByNumber[i];
                }
                evalHand[4] = sortedHandByNumber[0];
            }

            //Flush and Straight Flush
            boolean flush = true;
            for (int i = 0; i < 4; i++) {
                if (sortedHandByNumber[i].getSuit() != sortedHandByNumber[i + 1].getSuit())
                    flush = false;
            }
            if (flush && rank == 5) {
                rank = 9;
            } else if (flush) {
                rank = 6;
                evalHand = duplicateHand(sortedHandByNumber);
            }

            //Royal Flush
            if (rank == 9 && evalHand[0].getNumber() == 14) {
                rank = 10;
            }

            //High Card
            if (rank == 1) {
                evalHand = duplicateHand(sortedHandByNumber);
            }
            return evalHand;
        }

        //Get the rest cards in evalHand
        if (rank != 7) {
            int beginIndex = 0;
            if (rank == 2) beginIndex = 2;
            if (rank == 3 || rank == 8) beginIndex = 4;
            if (rank == 4) beginIndex = 3;
            for (int i = 0; i < 5; i++) {
                int tmp = cardHM.get(i);
                if (tmp == 5) {
                    cardHM.replace(i, beginIndex++);
                }
            }
        }
        evalHand = duplicateHand(sortedHandByNumber);

        return evalHand;
    }


    /**
     * Compare the ranking between this PokerHand with the argument one.
     * @param ph the PokerHand to compare with
     * @return 1 if this PokerHand is greater than the argument; return -1 if less; return 0 if equal.
     */
    public int compareTo(PokerHand ph) {
        int arguRank = ph.getRank();
        if (this.rank == arguRank) {
            Card[] arguEval = ph.getEvalHand();
            for (int i = 0; i < 5; i++) {
                if (evalHand[i].getNumber() > arguEval[i].getNumber()) {
                    return 1;
                } else if (evalHand[i].getNumber() < arguEval[i].getNumber()) {
                    return -1;
                }
            }
            return 0;
        } else {
            if (this.rank > arguRank)
                return 1;
            else
                return -1;
        }
    }

    /***************************** Print Methods ****************************/

    /**
     * Output the Card array in the original order
     * @return the string of all cards
     */
    public String toString() {
        String ans = "";
        for (int i = 0; i < 5; i++) {
            ans += this.hand[i].toString() + "  ";
        }
        return ans;
    }

    /**
     * Output the rank result
     * @param rank the rank code from 1 High Card to 10 Royal Flush
     * @return the rank name in String
     */
    public String rankToString(int rank) {
        switch (this.rank) {
            case 1: return "High Card      ";
            case 2: return "One Pair       ";
            case 3: return "Two Pair       ";
            case 4: return "Three of a Kind";
            case 5: return "Straight       ";
            case 6: return "Flush          ";
            case 7: return "Full House     ";
            case 8: return "Four of a Kind ";
            case 9: return "Straight Flush ";
            case 10: return "Royal Flush   ";
            default: return "Not existed rank";
        }
    }

    /**
     * Output the result of rank evaluation.
     * @return the rank name and the cards in evaluation order
     */
    public String evalToString() {
        String ans = rankToString(this.rank) + " : ";
        for (int i = 0; i < 5; i++) {
            ans += evalHand[i].toString() + "  ";
        }
        return ans;
    }

    /***************************** Helper Methods ****************************/

    /**
     * Use the selection sort to sort the cards by numbers, in descending order.
     * @param ca the Card array to be sorted
     */
    private void sortByNumber(Card[] ca) {
        int len = ca.length;
        for (int i = 0; i < len - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (ca[j].compareNumberTo(ca[maxIndex]))
                    maxIndex = j;
            }
            Card tmp = ca[i];
            ca[i] = ca[maxIndex];
            ca[maxIndex] = tmp;
        }
    }

    /**
     * Use the selection sort to sort the cards by suits, in descending order.
     * @param ca the Card array to be sorted
     */
    private void sortBySuit(Card[] ca) {
        int len = ca.length;
        for (int i = 0; i < len; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (ca[j].compareSuitTo(ca[maxIndex]))
                    maxIndex = j;
            }
            Card tmp = ca[i];
            ca[i] = ca[maxIndex];
            ca[maxIndex] = tmp;
        }
    }


    /**
     * Helper method to copy the Card array
     * @param c the card array to be copied
     * @return the new card array
     */
    private Card[] duplicateHand (Card[] c) {
        int len = c.length;
        Card[] newHand = new Card[len];
        for (int i = 0; i < len; i++) {
            newHand[i] = c[i];
        }
        return newHand;
    }


    public static void main(String[] args) {
        Card[] ca = new Card[5];
        ca[0] = new Card(13, 2);
        ca[1] = new Card(11, 4);
        ca[2] = new Card(4, 2);
        ca[3] = new Card(10, 4);
        ca[4] = new Card(12, 1);
        PokerHand ph1 = new PokerHand(ca[0], ca[1], ca[2], ca[3], ca[4]);

        Card[] caa = new Card[5];
        caa[0] = new Card(3, 3);
        caa[1] = new Card(2, 1);
        caa[2] = new Card(14, 2);
        caa[3] = new Card(4, 2);
        caa[4] = new Card(5, 2);
        PokerHand ph2 = new PokerHand(caa);

        System.out.println(ph1.toString());
        System.out.println(ph1.evalToString());
        System.out.println(ph2.toString());
        System.out.println(ph2.evalToString());
        System.out.println(ph1.compareTo(ph2));



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

        PokerHand ph3 = new PokerHand(caa);

        int[] tmp = ph3.advSortByNumber(manyca);

    }

    public Card[] findBestHand() {
        int len = this.hand.length;

        Card[] sortedHandBySuit = duplicateHand(this.hand);
        this.sortBySuit(sortedHandBySuit);

        for (int i = 0; i + 4 < len; i = i + 4) {
            if (sortedHandBySuit[i].getSuit() == sortedHandBySuit[i].getSuit()
                && sortedHandBySuit[i].getNumber() - 4 == sortedHandBySuit[i].getNumber()) {
                rank = 9;
            }
        }

        Card[] sortedHandByNumber = duplicateHand(this.hand);
        this.sortByNumber(sortedHandByNumber);

        return sortedHandByNumber;
    }

    /**
     * Sort the card array by number, return the sort report array. <p>
     * The index of the array is the number (rank) shown on the card, from 2 to 14;<p>
     * the value of the array is the appearance frequency, from 0 to 4;
     * @param ca the Card array to be sorted
     * @return the integer array indicate how many cards of the same rank
     */
    public int[] advSortByNumber(Card[] ca) {
        this.sortByNumber(ca);
        int len = ca.length;
        int[] ans = new int[15];

        for (int i = 0; i < len; i++) {
            ans[ca[i].getNumber()]++;
        }

        for (int i = 0; i < 15; i++) {
            System.out.println(i + " " + ans[i]);
        }
        return ans;
    }

    /**
     * Sort the card array by suit, return the sort report array. <p>
     * The index of the array is the suit shown on the card, from 1 to 4;<p>
     * the value of the array is the appearance frequency, from 0 to 13;
     * @param ca the card array to be sorted
     * @return the integer array indicating how many cards are in the same suit
     */
    public int[] advSortBySuit(Card[] ca) {
        this.sortBySuit(ca);
        int len = ca.length;
        int[] ans = new int[5];
        for (int i = 0; i < len; i++) {
            ans[ca[i].getSuit()]++;
        }
        return ans;
    }
}
