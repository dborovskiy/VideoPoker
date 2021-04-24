/*
 * Daniel Borovskiy
 * db3428
 * 
 * Project 4 - Card Class
 * Creates a template for a card
 */

public class Card implements Comparable<Card> {
	
	private int suit; // Uses integers 1-4 to encode the suit (alphabetically)
	private int rank; // Uses integers 1-13 to encode the rank
	
    // The constructor initializes the suit and rank
	public Card(int s, int r) {
		suit = s;
        rank = r;
	}
    
    // This accessor returns the "difference" between two cards
	public int compareTo(Card c) {
        
        // Declares wrapped objects for both cards' ranks
        Integer thisOrderedRank;
        Integer otherOrderedRank;
        
        // Initializes both wrapped variables to the respective cards' ranks
        // Treats an ace as 14 so that it is sorted above a king
        if (this.rank == 1) {
            thisOrderedRank = Integer.valueOf(14);
        } else {
            thisOrderedRank = Integer.valueOf(this.rank);
        }
        if (c.rank == 1) {
            otherOrderedRank = Integer.valueOf(14);
        } else {
            otherOrderedRank = Integer.valueOf(c.rank);
        }
        
        
        if (thisOrderedRank.compareTo(otherOrderedRank) != 0) {
            // Returns the difference of the ranks if they are not equal
            return thisOrderedRank.compareTo(otherOrderedRank);
        } else {
            // Returns the difference of the suits if the ranks are equal
            Integer thisSuit = Integer.valueOf(this.suit);
            Integer otherSuit = Integer.valueOf(c.suit);
            return thisSuit.compareTo(otherSuit);
        }
	}
	
    // This accessor returns a Card as a String
	public String toString() {
        
        // Declares and initializes a String array of suit names
        String[] suits = {"clubs", "diamonds", "hearts", "spades"};
        
        // Declares and initializes namedRank which stores the name of the rank for J-A
        // or the number rank for 2-10
        String namedRank;
        if (rank == 1) {
            namedRank = "Ace";
        } else if (rank == 11) {
            namedRank = "Jack";
        } else if (rank == 12) {
            namedRank = "Queen";
        } else if (rank == 13) {
            namedRank = "King";
        } else {
            namedRank = Integer.toString(rank);
        }
        
        // Returns the String in the format [rank] of [suit]
        return namedRank + " of " + suits[suit-1]; 
	}
    
    // This accessor returns the card's rank
    public int getRank() {
        return rank;
    }
    
    // This accessor returns the card's suit
    public int getSuit() {
        return suit;
    }
    
} // Ends the Card class
