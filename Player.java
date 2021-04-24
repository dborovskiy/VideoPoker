/*
 * Daniel Borovskiy
 * db3428
 * 
 * Project 4 - Player Class
 * Creates a template for the player
 */

import java.util.ArrayList;

public class Player {
	
    // Declares the instance variables
    private ArrayList<Card> hand; // Stores the player's hand
    private double bankroll;
    private double bet;
	
    // The constructor initializes the instance variables
    public Player(){		
        hand = new ArrayList<Card>();
        bankroll = 100;
        bet = 0;
    }

    // Adds a card to the player's hand
    public void addCard(Card c){
        hand.add(c);
    }

    // Removes a card from the player's hand
    public void removeCard(Card c){
	    for (int i = 0; i < hand.size(); i++) {
            if (c.compareTo(hand.get(i)) == 0) {
                hand.remove(i);
            }
        }
    }
		
    // Sets the player's bet and subtracts it from their bankroll
    public void bets(double amt){
        bet = amt;
        bankroll -= bet;
    }

    // Adds a player's winning to their bankroll and prints winnings
    public void winnings(double odds){
        bankroll += (bet * odds);
        System.out.println("You won " + bet * odds);
    }

    // This accessor returns the player's bankroll
    public double getBankroll(){
        return bankroll;
    }
    
    // This accessor returns the player's current bet
    public double getBet() {
        return bet;
    }

    // This accessor returns the player's hand
    public ArrayList<Card> getHand() {
        return hand;
    }

} // Ends the Player class