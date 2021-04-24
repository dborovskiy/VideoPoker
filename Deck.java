/*
 * Daniel Borovskiy
 * db3428
 * 
 * Project 4 - Deck Class
 * Creates a template for a deck
 */

public class Deck {
	
    // Declares instance variables
    private Card[] cards; // An array to hold cards
    private int top; // The index of the top of the deck
    
    // Declares useful constants
    private static final int SUITS = 4;
    private static final int RANKS = 13;
    private static final int CARDS = SUITS*RANKS;
	
    // The constructor initializes the instanice variables
    public Deck() {
        top = 0; // Initializes the top of the deck at index 0
        cards = new Card[52]; // Initializes a Card array of length 52
        
        // Declares and initialises 52 cards (one of each rank-suit combination)
        // and populates the array with the cards
        int i = 0;
        for (int s = 1; s <= SUITS; s++) {
            for (int r = 1; r <= RANKS; r++) {
                cards[i] = new Card (s, r);
                i++;
            }
        }
    }
	
    // This mutator shuffles the deck
    public void shuffle() {
        // This constant store how many card swaps are to be done
        final int SWITCHES = 1000;
        
        // SWITCHES times: swaps two randomly chosen cards
        for (int i = 0; i < SWITCHES; i++) {
            int firstIndex = (int) (Math.random()*(CARDS));
            int secondIndex = (int) (Math.random()*(CARDS));
            Card firstCard = cards[firstIndex];
            cards[firstIndex] = cards[secondIndex];
            cards[secondIndex] = firstCard;
        }
    }
	
    // This accessor returns the next card of the deck
    public Card deal() {
        
        // If all cards have been delt, the deck is shuffled
        // and distribution starts at index 0
        if (top == 52) {
            this.shuffle();
            top = 0;
        }
        
        top++;
        return cards[top-1];
    }

} // Ends the Deck class
