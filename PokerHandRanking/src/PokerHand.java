import java.util.Arrays;
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
        if (len == 5) {
            this.evalHand = evalFive();
        } else if (len > 5) {
            this.evalHand = findBestHand();
        } else {
            throw new IllegalArgumentException("The card array argument has less than five cards");
        }
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
        for (int i = 0; i < 5; i++) {
            int tmpIndex = cardHM.get(i);
            evalHand[tmpIndex] = sortedHandByNumber[i];
        }

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


    /**
     * Sort the card array by number, return the sort report array. <p>
     * The index of the row is the number (rank) shown on the card, from 2 to 14;<p>
     * The first column of  is the appearance frequency, from 0 to 4;
     * The second column of the beginning index of the number, from 0 to the length of card array;
     * @param ca the Card array to be sorted
     * @return the two-dimensional integer array indicating the frequency and begin index of each number
     */
    private int[][] advSortByNumber(Card[] ca) {
        this.sortByNumber(ca);
        int len = ca.length;
        int[][] ans = new int[15][2];

        for (int i = 0; i < len; i++) {
            int num = ca[i].getNumber();
            if (ans[num][0] == 0) {
                ans[num][1] = i;
            }
            ans[num][0]++;
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
    private int[][] advSortBySuit(Card[] ca) {
        this.sortBySuit(ca);
        int len = ca.length;
        int[][] ans = new int[5][2];
        for (int i = 0; i < len; i++) {
            int sui = ca[i].getSuit();
            if (ans[sui][0] == 0) {
                ans[sui][1] = i;
            }
            ans[sui][0]++;
        }
        return ans;
    }

    /**
     * Compare two 5-card array in the same rank; return the higher one.
     * @param a the first 5-card array
     * @param b the second 5-card array
     * @return the higher ranking array. return the first array if equals.
     */
    private Card[] cardArrayCompare (Card[] a, Card[] b) {
        if (a.length != 5 || b.length != 5)
            throw new IllegalArgumentException("Not valid tmpEvalArray");

        if (a[1] == null) return b;
        for (int i = 0; i < 5; i++) {
            if (a[i].getNumber() > b[i].getNumber()) {
                return a;
            } else if (a[i].getNumber() < b[i].getNumber()) {
                return b;
            }
        }
        return a; //a equals b
    }

    /**
     * Takes 5 or more cards and returns the best 5-card array
     * @return the 5-card array to the evalHand variable
     */
    public Card[] findBestHand() {
        int len = this.hand.length;
        rank = 1;

        //Determine if Royal Flush, Straight Flush, or Flush
        Card[] sortedHandBySuit = duplicateHand(this.hand);
        int[][] suitTable = this.advSortBySuit(sortedHandBySuit);

        Card[] tmpEvalHand = new Card[5];
        //RoyalFLush
        tmpEvalHand = isRoyalFlush(sortedHandBySuit, suitTable);
        if (tmpEvalHand != null) {
            this.rank = 10;
            return tmpEvalHand;
        }
        //StraightFlush
        tmpEvalHand = isStraightFlush(sortedHandBySuit, suitTable);
        if (tmpEvalHand != null) {
            this.rank = 9;
            return tmpEvalHand;
        }

        Card[] sortedHandByNumber = duplicateHand(this.hand);
        int[][] numTable = this.advSortByNumber(sortedHandByNumber);

        //Four Of A Kind
        tmpEvalHand = isFourOfAKind(sortedHandByNumber, numTable);
        if (tmpEvalHand != null) {
            this.rank = 8;
            return tmpEvalHand;
        }

        //FullHouse
        tmpEvalHand = isFullHouse(sortedHandByNumber, numTable);
        if (tmpEvalHand != null) {
            this.rank = 7;
            return tmpEvalHand;
        }

        //Flush
        tmpEvalHand = isFlush(sortedHandBySuit, suitTable);
        if (tmpEvalHand != null) {
            this.rank = 6;
            return tmpEvalHand;
        }

        //Straight
        tmpEvalHand = isStraight(sortedHandByNumber, numTable);
        if (tmpEvalHand != null) {
            this.rank = 5;
            return tmpEvalHand;
        }

        //Three of a Kind
        tmpEvalHand = isThreeOfAKind(sortedHandByNumber, numTable);
        if (tmpEvalHand != null) {
            this.rank = 4;
            return tmpEvalHand;
        }

        //Two Pair
        tmpEvalHand = isTwoPair(sortedHandByNumber, numTable);
        if (tmpEvalHand != null) {
            this.rank = 3;
            return tmpEvalHand;
        }

        //One Pair
        tmpEvalHand = isOnePair(sortedHandByNumber, numTable);
        if (tmpEvalHand != null) {
            this.rank = 2;
            return tmpEvalHand;
        }

        //High Card
        tmpEvalHand = Arrays.copyOfRange(sortedHandByNumber, 0, 5);
        return tmpEvalHand;
    }

    /****************** Start of Rank Evaluation Methods *******************/
    /**
     * isXXX() methods Return the best five cards under the XX rank; not considering other ranks.
     * @param sca
     * @param numTable
     * @return the 5-card array if this.hand is at this rank; return null if not;
     */

    private Card[] isOnePair(Card[] sca, int[][] numTable) {
        Card[] ca = new Card[5];
        for (int i = 14; i >= 2; i--) {
            if (numTable[i][0] == 2) {
                ca[0] = sca[numTable[i][1]];
                ca[1] = sca[numTable[i][1] + 1];
                if (numTable[i][1] == 0) { //if the pair start from the 1st position of sca
                    ca[2] = sca[2];
                    ca[3] = sca[3];
                    ca[4] = sca[4];
                } else if (numTable[i][1] == 1){ //if the pair start from the 2nd of sca
                    ca[2] = sca[0];
                    ca[3] = sca[3];
                    ca[4] = sca[4];
                } else if (numTable[i][1] == 2) { //if the pair start from the 3rd of sca
                    ca[2] = sca[0];
                    ca[3] = sca[1];
                    ca[4] = sca[4];
                } else { //if the pair start from of after the 4th element of sca
                    ca[2] = sca[0];
                    ca[3] = sca[1];
                    ca[4] = sca[2];
                }
                return ca;
            }
        }
        return null;
    }

    private Card[] isTwoPair(Card[] sca, int[][] numTable) {
        Card[] ca = new Card[5];
        int firstPairIdx = -1, secondPairIdx = -1; //Set the indexes of first and second pairs
        for (int i = 14; i >= 2; i--) {
            if (firstPairIdx == -1 && numTable[i][0] == 2) {
                firstPairIdx = numTable[i][1];
            } else if (secondPairIdx == -1 && numTable[i][0] == 2) {
                secondPairIdx = numTable[i][1];
                break; //if find two pairs, break the loop
            }
        }
        if (firstPairIdx == -1 || secondPairIdx == -1)
            return null;
        else {
            ca[0] = sca[firstPairIdx];
            ca[1] = sca[firstPairIdx + 1];
            ca[2] = sca[secondPairIdx];
            ca[3] = sca[secondPairIdx + 1];
            for (int i = 0; i < 5; i++) {
                if (i != firstPairIdx && i != firstPairIdx + 1 && i != secondPairIdx && i != secondPairIdx + 1) {
                    ca[4] = sca[i];
                    return ca;
                }
            }
            throw new IllegalArgumentException("Didn't find the 5th card in evalHand in isTwoPair()");
        }
    }

    private Card[] isThreeOfAKind(Card[] sca, int[][] numTable) {
        Card[] ca = new Card[5];
        for (int i = 14; i >= 2; i--) {
            if (numTable[i][0] == 3) {
                ca[0] = sca[numTable[i][1]];
                ca[1] = sca[numTable[i][1] + 1];
                ca[2] = sca[numTable[i][1] + 2];
                if (numTable[i][1] == 0) { //if the triple start from of beginning of sca
                    ca[3] = sca[3];
                    ca[4] = sca[4];
                } else if (numTable[i][1] == 1){ //if the triple start from the 2nd of sca
                    ca[3] = sca[0];
                    ca[4] = sca[4];
                } else { //if the triple start from or after the 3rd element of sca
                    ca[3] = sca[0];
                    ca[4] = sca[1];
                }
                return ca;
            }
        }
        return null;
    }

    private Card[] isStraight(Card[] sca, int[][] numTable) {
        Card[] ca = new Card[5];
        for (int i = 14; i - 4 >= 2; i--) {
            if (numTable[i][0] > 0 && numTable[i - 1][0] > 0 && numTable[i - 2][0] > 0
                                   && numTable[i - 3][0] > 0 && numTable[i - 4][0] > 0) {
                ca[0] = sca[numTable[i][1]];
                ca[1] = sca[numTable[i - 1][1]];
                ca[2] = sca[numTable[i - 2][1]];
                ca[3] = sca[numTable[i - 3][1]];
                ca[4] = sca[numTable[i - 4][1]];
                return ca;
            }
        }
        if (numTable[5][0] > 0 && numTable[14][0] > 0 && numTable[4][0] > 0
                && numTable[3][0] > 0 && numTable[2][0] > 0) {
            ca[0] = sca[numTable[5][1]];
            ca[1] = sca[numTable[4][1]];
            ca[2] = sca[numTable[3][1]];
            ca[3] = sca[numTable[2][1]];
            ca[4] = sca[numTable[14][1]];
            return ca;
        }
        return null;
    }

    private Card[] isFlush(Card[] sca, int[][] suitTable) {
        Card[] ca = new Card[5];
        for (int i = 1; i < 5; i++) {
            if (suitTable[i][0] >= 5) {
                Card[] tmp = new Card[5];
                int beginIdx = suitTable[i][1];
                tmp[0] = sca[beginIdx];
                tmp[1] = sca[beginIdx + 1];
                tmp[2] = sca[beginIdx + 2];
                tmp[3] = sca[beginIdx + 3];
                tmp[4] = sca[beginIdx + 4];
                ca = cardArrayCompare(ca, tmp);
            }
        }
        if (ca[1] == null)
            return null;
        else {
            return ca;
        }
    }

    private Card[] isFullHouse(Card[] sca, int[][] numTable) {
        Card[] ca = new Card[5];
        int tripleIdx = -1, pairIdx = -1; //Set the first card index of the triple and pair
        for (int i = 14; i >= 2; i--) {
            if (tripleIdx == -1 && numTable[i][0] == 3) {
                tripleIdx = numTable[i][1]; //get the first triple cards
            }
            if (pairIdx == -1 && numTable[i][0] == 2) {
                pairIdx = numTable[i][1]; //get the first pair cards
            }
            if (pairIdx != -1 && tripleIdx != -1)
                break;
        }
        if (tripleIdx == -1 || pairIdx == -1)
            return null;
        else {
            ca[0] = sca[tripleIdx];
            ca[1] = sca[tripleIdx + 1];
            ca[2] = sca[tripleIdx + 2];
            ca[3] = sca[pairIdx];
            ca[4] = sca[pairIdx + 1];
            return ca;
        }
    }

    private Card[] isFourOfAKind(Card[] sca, int[][] numTable) {
        Card[] ca = new Card[5];
        for (int i = 14; i >= 2; i--) {
            if (numTable[i][0] == 4) {
                ca[0] = sca[numTable[i][1]];
                ca[1] = sca[numTable[i][1] + 1];
                ca[2] = sca[numTable[i][1] + 2];
                ca[3] = sca[numTable[i][1] + 3];
                //the last card should be the 1st or the 5th card in the sorted array sca
                if (numTable[i][1] == 0)
                    ca[4] = sca[4];
                else
                    ca[4] = sca[0];
                return ca;
            }
        }
        return null;
    }

    private Card[] isStraightFlush(Card[] sca, int[][] suitTable) {
        Card[] straightFlushEvalArray = new Card[5];
        for (int i = 1; i < 5; i++) {
            if (suitTable[i][0] >= 5) {
                //flushCA are cards in same suit and sorted in descending order. No duplicated cards.
                Card[] flushCA = Arrays.copyOfRange(sca,
                        suitTable[i][1],
                        suitTable[i][1] + suitTable[i][0]);
                int len = suitTable[i][0];

                if (straightFlushEvalArray[1] == null //if no other flush found, use this smallest straight flush
                        && flushCA[0].getNumber() == 14
                        && flushCA[len - 1].getNumber() == 2
                        && flushCA[len - 1].getNumber() + 3 == flushCA[len - 4].getNumber()) {
                    straightFlushEvalArray[0] = flushCA[len - 4];
                    straightFlushEvalArray[1] = flushCA[len - 3];
                    straightFlushEvalArray[2] = flushCA[len - 2];
                    straightFlushEvalArray[3] = flushCA[len - 1];
                    straightFlushEvalArray[4] = flushCA[0];
                } else {
                    for (int j = 0; j + 4 < len; j++) {
                        if (flushCA[j].getNumber() == flushCA[j + 4].getNumber() + 4) {
                            Card[] tmpEvalArray = new Card[5];
                            tmpEvalArray[0] = flushCA[j];
                            tmpEvalArray[1] = flushCA[j + 1];
                            tmpEvalArray[2] = flushCA[j + 2];
                            tmpEvalArray[3] = flushCA[j + 3];
                            tmpEvalArray[4] = flushCA[j + 4];
                            straightFlushEvalArray = cardArrayCompare(straightFlushEvalArray, tmpEvalArray);
                        }
                    }
                }

            }
        }
        if (straightFlushEvalArray[1] == null)
            return null;
        else {
            return straightFlushEvalArray;
        }
    }

    private Card[] isRoyalFlush(Card[] sortedHandBySuit, int[][] suitTable) {
        for (int i = 1; i < 5; i++) {
            if (suitTable[i][0] >= 5) {
                //The idx card and following 4 cards in same suit and sorted in descending order. No duplicated cards.
                int idx = suitTable[i][1];
                if (sortedHandBySuit[idx].getNumber() == 14
                        && sortedHandBySuit[idx].getNumber() - 4 == sortedHandBySuit[idx + 4].getNumber()) {
                    return Arrays.copyOfRange(sortedHandBySuit, idx, idx + 5);
                }
            }
        }
        return null;
    }

    /****************** End of Rank Evaluation Methods *******************/



}
