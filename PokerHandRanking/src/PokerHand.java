/**
 * Created by yuqing on 3/13/17.
 */
public class PokerHand {
    private Card[] hand;
    private int rank;

    public PokerHand(Card c1, Card c2, Card c3, Card c4, Card c5) {
        this.hand = new Card[5];
        hand[0] = c1; //todo: use this.?
        hand[1] = c2;
        hand[2] = c3;
        hand[3] = c4;
        hand[4] = c5;
    }


    public PokerHand(Card[] moreCards) {
        int len = moreCards.length;
        this.hand = new Card[len];
        for (int i = 0; i < len; i++) {
            this.hand[0] = moreCards[0];
        }
    }

    /**
     * Use the selection sort to sort the cards by numbers, in descending order.
     * @param ca the Card array to be sorted
     */
    private void sortByNumber(Card[] ca) {
        int len = ca.length;
        for (int i = 0; i < len; i++) {
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

    public static void main(String[] args) {
        Card[] ca = new Card[5];
        ca[0] = new Card(14, 2);
        ca[1] = new Card(4, 4);
        ca[2] = new Card(13, 1);
        ca[3] = new Card(12, 3);
        ca[4] = new Card(10, 1);

        sortByNumber(ca);
        for (int i = 0; i < 5; i++) {
            System.out.println(ca[i].toString());
        }
    }
}
