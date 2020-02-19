public class Card {
	public final static int HEARTS = 0;   // Codes for the 4 suits, plus Joker.
    public final static int CLUBS = 1;
    public final static int DIAMONDS = 2;
    public final static int SPADES = 3;
    public final static int JOKER = 4;

    public final static int ACE = 1;      // Codes for the non-numeric cards.
    public final static int JACK = 11;    //   Cards 2 through 10 have their 
    public final static int QUEEN = 12;   //   numerical values for their codes.
    public final static int KING = 13;
    
    private final int suit; // A card's suit cannot changed once it is set
    private final int value; // a card's value cannot change once it is set 
    
    /*************************************CONSTRUCTORS*********************************************/
    
    /*
     * Constructor for the empty parameter constructor
     */
    public Card () {
    		suit = JOKER;
    		value = 1;
    }
    
    /*
     * Creates a card with a specified suit and value.
     * The value must be in the range 1 through 13, with 1 representing an Ace.
     * For a Joker, the value can be anything. The suit of the new card must be 
     * one of the values Card.SPADES, Card.HEARTS, Card.DIAMONDS, Card.CLUBS, or Card.JOKER.
     */
    public Card(int val, int cardSuit) {
        if (cardSuit != SPADES && cardSuit != HEARTS && cardSuit != DIAMONDS && 
                cardSuit != CLUBS && cardSuit != JOKER)
            throw new IllegalArgumentException("Illegal suit");
        if (cardSuit != JOKER && (val < 1 || val > 13))
            throw new IllegalArgumentException("Illegal value");
        value = val;
        suit = cardSuit;
    }
    
    /*************************************CONSTRUCTORS*********************************************/
    
    
    /***************************GETTERS************************************/
    public int getValue() { return value; } // Returns the value of the card
    public int getSuit() { return suit; } // Returns the suit of the card 
    public String toString() { return "Value: " + getValueAsString() + "Suit: " + getSuitAsString(); }
    
    /*
     * Returns a String representation of the card's suit which will be
     * either "Spades", "Hearts", "Diamonds", "Clubs" or "Joker".
     */
    public String getSuitAsString() {
        switch (suit) {
        case SPADES:   return "Spades";
        case HEARTS:   return "Hearts";
        case DIAMONDS: return "Diamonds";
        case CLUBS:    return "Clubs";
        default:       return "Joker";
        }
    }
    
    /*
     * Returns a String representation of the card's value.
     * which will be one of the strings "Ace", "2", "3", ..., "10", 
     * "Jack", "Queen", or "King".  For a Joker, the will be
     * numerical.
     */
    public String getValueAsString() {
        if (suit == JOKER)
            return "" + value;
        else {
            switch (value) {
            case 1:   return "Ace";
            case 2:   return "2";
            case 3:   return "3";
            case 4:   return "4";
            case 5:   return "5";
            case 6:   return "6";
            case 7:   return "7";
            case 8:   return "8";
            case 9:   return "9";
            case 10:  return "10";
            case 11:  return "Jack";
            case 12:  return "Queen";
            default:  return "King";
            }
        }
    }
    /***************************GETTERS************************************/   
}
