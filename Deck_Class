public class Deck {
	private Card[] deck; // This will hold the deck of cards
	private int usedCards; // Keep track of the cards used in a deck

	/*************************************CONSTRUCTORS*********************************************/
	/*
     * Constructs a deck of playing cards, The deck contains the usual 52 cards and can contain 
     * two Jokers in addition, for a total of 54 cards. The cards are in a sorted order and the 
     * shuffle() method can be called to randomize the deck. If the boolean includeJokers is true, 
     * two Jokers are included in the deck; if false, there are no Jokers in the deck.
     */
    public Deck(boolean includeJokers) {
        if (includeJokers)
            deck = new Card[54];
        else
            deck = new Card[52];
        int count = 0; // How many cards have been created so far.
        for (int suit = 0; suit <= 3; suit++) {
            for (int value = 1; value <= 13; value++) {
                deck[count] = new Card(value,suit);
                count++;
            }
        }
        if (includeJokers) {
            deck[52] = new Card(1,Card.JOKER);
            deck[53] = new Card(2,Card.JOKER);
        }
        usedCards = 0;
    }
    
    public Deck() {
        this(false);  // Just call the other constructor 
    }
	/*************************************CONSTRUCTORS*********************************************/
    
    /*************************************METHODS*********************************************/
    /* Method : shuffle
     * Input : N/A
     * Output : Put all the used cards back into the deck (if any), and
     * shuffle the deck.
     */
    public void shuffle() {
        for (int i = deck.length-1; i > 0; i--) {
            int rand = (int)(Math.random()*(i+1));
            Card temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
        usedCards = 0;
    }
    
    /* Method : cardsLeft
     * Input : N/A
     * Output : Returns the number of cards that are still left in the deck as the game 
     * 		    proceeds. The return value would be 52 or 54 when the deck is first 
     * 		    created or after the deck has been shuffled. It decreases by 1 each time 
     * 		    the dealCard() method is called.
     */
    public int cardsLeft() { return deck.length - usedCards; }
    
    /* Method : dealCard
     * Input : N/A
     * Output : Removes the next card from the deck and returns it. Cannot call this
     * 		    method if there are no cards left in the deck as it will throw an
     * 		    exception.
     */
    public Card dealCard() {
    		if(cardsLeft() == 0)
    			return null;
    			//throw new IllegalStateException("The deck is empty");
        usedCards++;
        return deck[usedCards - 1]; // This is not removing cards from the array, just
        							   // simply keeping track of how many cards used
     }
    
    /* Method : hasJokers
     * Input : N/A
     * Output : Test whether the deck contains Jokers. Returns true if this is a 54-card 
     * 		    deck containing two jokers, or false if this is a 52 card deck that contains no 
     * 		    jokers.
     */
    public boolean hasJokers() { return (deck.length == 54); }
    
    /*************************************METHODS*********************************************/
}
