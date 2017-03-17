
public class Card {
    private int suit; //suit of the card, from 1 - 4.
    private int number; // rank of the card, from 2 - 14.

    /**
     * Constructor of the Card with parameters
     * @param a rank
     * @param b suit
     */
    public Card(int a, int b) {
        this.number = a;
        this.suit = b;
    }

    // todo: assign random card,
    // todo: add check system by HashSet in other class

    /**
     * Return the suit of the Card
     * @return the suit value
     */
    public int getSuit() {
        return suit;
    }

    /**
     * Return the rank of the Card
     * @return the rank value
     */
    public int getNumber() {
        return number;
    }

    /**
     * Compare To the parameter Card c. If this.number is higher than c.number,
     * return true; otherwise return false; <p>
     * If they have the same number, compare the suit. If this.suit is higher than c.suit,
     * return true; otherwise return false;
     * @param c the Card to compare with
     * @return if this Card is higher than C
     */
    public boolean compareNumberTo(Card c) {
        if (this.getNumber() > c.getNumber()
                || (this.getNumber() == c.getNumber()
                        && this.getSuit() > c.getSuit())
            ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param c the Card to compare with
     * @ if the Card is higher than C
     */
    public boolean compareSuitTo(Card c) {
        if (this.getSuit() > c.getSuit()
                || (this.getSuit() == c.getSuit()
                    && this.getNumber() > c.getNumber())
            ) {
            return true;
        } else {
            return false;
        }
    }


    public String toString() {
        String ans = "";
        switch (this.getNumber()) {
            case 10:
                ans += "10";
                break;
            case 11:
                ans += "J ";
                break;
            case 12:
                ans += "Q ";
                break;
            case 13:
                ans += "K ";
                break;
            case 14:
                ans += "A ";
                break;
            default:
                ans += this.getNumber() + " ";
        }
        switch(this.getSuit()) {
            case 1:
                ans += "Hearts  ";
                break;
            case 2:
                ans += "Clubs   ";
                break;
            case 3:
                ans += "Diamonds";
                break;
            case 4:
                ans += "Spades  ";
                break;
        }
        return ans;
    }

    public static void main(String[] args) {
        Card c = new Card(2, 3);
        System.out.println(c.toString());
    }
}
