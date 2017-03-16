
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
        this.hand[4] = c5;
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
    public Card[] evalFive() {
        Card[] sortedHandByNumber = this.hand;
        this.sortByNumber(sortedHandByNumber);

        // Assume it's high card
        rank = 1;
        int firstPairIndex = 0;
        int secondPairIndex = 0;

        //One Pair
        for (int i = 0; i < 4; i++) {
            if (sortedHandByNumber[i].getNumber() == sortedHandByNumber[i + 1].getNumber()) {
                rank = 2;
                evalHand[0] = sortedHandByNumber[i];
                evalHand[1] = sortedHandByNumber[i + 1];
                firstPairIndex = i + 1;
            }
        }
        //Two Pairs
        if (rank == 2) {
            for (int i = firstPairIndex + 1; i < 4; i++) {
                if (sortedHandByNumber[i].getNumber() == sortedHandByNumber[i + 1].getNumber()) { //Two Pairs
                    rank = 3;
                    evalHand[2] = sortedHandByNumber[i];
                    evalHand[3] = sortedHandByNumber[i + 1];
                    secondPairIndex = i + 1;
                }
            }
        }
        //Four of a kind or Full House
        if (rank == 3) {
            if (sortedHandByNumber[firstPairIndex].getNumber() == sortedHandByNumber[secondPairIndex].getNumber()) {
                rank = 8;
            } else {
                //Two conditions of the full house
                if (firstPairIndex == 1
                    && secondPairIndex == 3
                    && sortedHandByNumber[3].getNumber() == sortedHandByNumber[4].getNumber()) {
                    rank = 7;
                    evalHand[4] = evalHand[0];
                    evalHand[0] = sortedHandByNumber[4];
                    //swap evalHand[1] with evalHand[3];
                    Card tmp = evalHand[1];
                    evalHand[1] = evalHand[3];
                    evalHand[3] = tmp;
                }
                if (firstPairIndex == 1
                    && secondPairIndex == 4
                    && sortedHandByNumber[1].getNumber() == sortedHandByNumber[2].getNumber()) {
                    rank = 7;
                    evalHand[4] = evalHand[2];
                    evalHand[2] = sortedHandByNumber[2];
                }
            }
        }
        //Three of a Kind
        if (rank == 2
            && firstPairIndex < 4
            && sortedHandByNumber[firstPairIndex].getNumber() == sortedHandByNumber[firstPairIndex + 1].getNumber()
            ) {
                rank = 4;
                evalHand[2] = sortedHandByNumber[firstPairIndex + 1];
                if (evalHand[4] == null)
                    System.out.println("Deal with null evalHand"); //todo: null evalHand
        }

        //Straight
        if (rank == 1) {
            if (sortedHandByNumber[0].getNumber() - 4 == sortedHandByNumber[4].getNumber()) {
                rank = 5;
                evalHand = sortedHandByNumber; //todo: verify if it is right
            }
            else if (sortedHandByNumber[0].getNumber() == 14
                && sortedHandByNumber[1].getNumber() - 3 == sortedHandByNumber[4].getNumber()) {
                rank = 5;
                for (int i = 1; i < 5; i++) {
                    evalHand[i - 1] = sortedHandByNumber[i];
                }
                evalHand[4] = sortedHandByNumber[0];
            }
        }

        //Flush
        if (rank == 1 || rank == 5) {
            boolean flush = true;
            for (int i = 0; i < 4; i++) {
                if (sortedHandByNumber[i].getSuit() != sortedHandByNumber[i + 1].getSuit())
                    flush = false;
            }
            if (flush && rank == 5) {
                rank = 9;
            } else if (flush) {
                rank = 6;
                evalHand = sortedHandByNumber;
            }
        }

        //Royal Flush
        if (rank == 9 && evalHand[0].getNumber() == 14) {
            rank = 10;
        }
        return evalHand;
    }

    private void complementEvalHand(Card[] source) {

    }


    public String rankToString(int rank) {
        switch (rank) {
            case 1: return "High Card";
            case 2: return "One Pair";
            case 3: return "Two Pair";
            case 4: return "Three of a Kind";
            case 5: return "Straight";
            case 6: return "Flush";
            case 7: return "Full House";
            case 8: return "Four of a Kind";
            case 9: return "Straight Flush";
            case 10: return "Royal Flush";
            default: return "Not existed rank";
        }
    }

    public String toString() {
        String ans = rankToString(this.rank) + " : ";
        for (int i = 0; i < 4; i++) {
            ans += evalHand[i].toString() + ",   ";
        }
        ans += evalHand[4].toString();
        return ans;
    }

    public static void main(String[] args) {
        Card[] ca = new Card[5];
        ca[0] = new Card(6, 2);
        ca[1] = new Card(4, 3);
        ca[2] = new Card(3, 2);
        ca[3] = new Card(2, 1);
        ca[4] = new Card(5, 2);
 //       PokerHand ph = new PokerHand(ca[0], ca[1], ca[2], ca[3], ca[4]);
        PokerHand ph = new PokerHand(ca);
        System.out.println(ph.toString());

    }
}
