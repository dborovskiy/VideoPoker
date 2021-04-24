/*
 * Daniel Borovskiy
 * db3428
 * 
 * Project 4 - Game Class
 * Conducts a game of video poker
 */


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Game {
	
    // Declares the instance variables
	private Player p;
	private Deck cards;
    private Scanner input;
    private boolean isTestGame;
    private double wonOdds;
    
    // Declares + initializes constants for odds of each combination
    private static final double ROYAL_FLUSH_ODDS = 250;
    private static final double STRAIGHT_FLUSH_ODDS = 50;
    private static final double FOUR_OF_A_KIND_ODDS = 25;
    private static final double FULL_HOUSE_ODDS = 6;
    private static final double FLUSH_ODDS = 5;
    private static final double STRAIGHT_ODDS = 4;
    private static final double THREE_OF_A_KIND_ODDS = 3;
    private static final double TWO_PAIRS_ODDS = 2;
    private static final double ONE_PAIR_ODDS = 1;
    private static final double HIGH_CARD_ODDS = 0;
    
    // Declares + initializes array of letters for suits
    private static final String[] suits = {"c", "d", "h", "s"};
	
    // This constructor takes in a String array as a parameter
	public Game(String[] testHand) {
		p = new Player();
        cards = new Deck();
        input = new Scanner(System.in);
        isTestGame = true;
        
        // Creates a card for each element in the parameter and adds it to hand
        for (int i = 0; i < testHand.length; i++) {
            int suit = -1;
            for (int j = 0; j < suits.length; j++) {
                String letterSuit = testHand[i].substring(0,1);
                if (suits[j].equals(letterSuit)) {
                    suit = j+1;
                }
            }
            int rank = Integer.parseInt(testHand[i].substring(1));
            Card c = new Card(suit, rank);
            p.addCard(c);
        }
	}
	
    // The no-parameter version of the constructor
	public Game(){
        p = new Player();
        cards = new Deck();
		input = new Scanner(System.in);
        isTestGame = false;
	}
	
    // This method plays the game
	public void play(){
        
        // If a test-hand was passed in, the hand and combo are printed
        if (isTestGame) {
            printHand();
            System.out.println(checkHand(p.getHand()));
            
        // If no test hand was passed in, a normal game is played
        } else {
            System.out.println("You start with a bankroll of 100");
            cards.shuffle();

            // Gives new hands until the player wants to stop
            boolean again = true;
            while (again == true) {
                
                // Clears the player's hand
                for (int i = p.getHand().size()-1; i >= 0; i--) {
                    p.removeCard(p.getHand().get(i));
                }
                
                bet(); // Asks for and sets the bet
                deal(5); // Deals 5 cards to a player
                remove(); // Removes cards the player wants to replace
                
                // Deals enough cards to make the hand 5 again and prints it
                deal(5 - p.getHand().size());
                printHand();

                // Prints the output of the checkHand method and new balance
                System.out.println("\nYou got a " + checkHand(p.getHand()));
                p.winnings(wonOdds);
                System.out.println("Your new balance is " + p.getBankroll());

                System.out.println("\nDo you want to play again? 1=Yes 2=No");
                again = (input.nextInt() == 1);
            }
        }
	}

    // This method runs all tests in descending order and returns the
    // combination of the hand. It also sets wonOdds accordingly.
	public String checkHand(ArrayList<Card> hand){
		Collections.sort(hand);
        if (checkRoyalFlush(hand)) {
            wonOdds = ROYAL_FLUSH_ODDS;
            return "Royal Flush";
        } else if (checkStraightFlush(hand)) {
            wonOdds = STRAIGHT_FLUSH_ODDS;
            return "Straight Flush";
        } else if (checkFourOfAKind(hand)) {
            wonOdds = FOUR_OF_A_KIND_ODDS;
            return "Four of a Kind";
        } else if (checkFullHouse(hand)) {
            wonOdds = FULL_HOUSE_ODDS;
            return "Full House";
        } else if (checkFlush(hand)) {
            wonOdds = FLUSH_ODDS;
            return "Flush";
        } else if (checkStraight(hand)) {
            wonOdds = STRAIGHT_ODDS;
            return "Straight";
        } else if (checkThreeOfAKind(hand)) {
            wonOdds = THREE_OF_A_KIND_ODDS;
            return "Three of a Kind";
        } else if (checkTwoPairs(hand)) {
            wonOdds = TWO_PAIRS_ODDS;
            return "Two Pair";
        } else if (checkOnePair(hand)) {
            wonOdds = ONE_PAIR_ODDS;
            return "One Pair";
        } else {
            wonOdds = HIGH_CARD_ODDS;
            return "High Card";
        }
	}
	
    // This method deals some number of cards
    private void deal (int amount) {
        for (int i = 0; i < amount; i++) {
            p.addCard(cards.deal());
        }
    }
    
    // This method prints each card in the hand
    private void printHand () {
        System.out.println("\nYour Current Hand:");
        for (int i = 0; i < p.getHand().size(); i++) {
            System.out.println(p.getHand().get(i));
        }
        //System.out.println("\n");
    }
    
    // This method runs until a permissible bet is entered
    private void bet() {
        int attempts = 0;
        double bet = 0;
        while (bet < 1 || bet > 5) {
            if (attempts == 0) {
                System.out.println("How much do you want to bet? 1-5");
            } else {
                System.out.println("Your bet must be between 1 and 5:");
            }
            bet = input.nextDouble();
            attempts++;
        }
        p.bets(bet);
    }
    
    // This method removes cards until the player is happy with their hand
    private void remove() {
        String toRemove = "";
        while (toRemove.compareTo("0") != 0 && p.getHand().size() != 0) {
            printHand();
            System.out.println("\nWhich card would you like replaced? " +
                               "Ex: s1 = Ace of Spades. Enter 0 if none.");
            toRemove = input.next();

            if (toRemove.compareTo("0") != 0 && toRemove.compareTo("") != 0) {
                // Locates and removes the necessary card
                int suit = -1;
                for (int j = 0; j < suits.length; j++) {
                    String letterSuit = toRemove.substring(0,1);
                    if (suits[j].equals(letterSuit)) {
                        suit = j+1;
                    }
                }
                int rank = Integer.parseInt(toRemove.substring(1));
                Card c = new Card(suit, rank);
                p.removeCard(c);
            }
            
        }
    }
    
    // This accessor returns whether the hand has a royal flush
    private boolean checkRoyalFlush(ArrayList<Card> hand) {
        boolean hasRoyalFlush = false;
        if (checkStraightFlush(hand)) { // Continues if there's a straight flush
            if (hand.get(4).getRank() == 1 && hand.get(3).getRank() == 13) {
                // Has a royal flush if last two cards are K,A
                hasRoyalFlush = true;
            }
        }
        return hasRoyalFlush;
    }
    
    // This accessor returns whether the hand has a straight flush
    private boolean checkStraightFlush(ArrayList<Card> hand) {
        // Returns true if the hand has a straight and a flush
        return (checkStraight(hand) && checkFlush(hand));
    }
    
    // This accessor returns whether the hand has four of a kind
    private boolean checkFourOfAKind(ArrayList<Card> hand) {
        boolean hasFourOfAKind = false;
        for (int i = 0; i < 2; i++) {
            Card w = hand.get(i);
            Card x = hand.get(i+1);
            Card y = hand.get(i+2);
            Card z = hand.get(i+3);
            if (w.getRank() == x.getRank() && w.getRank() == y.getRank() &&
               w.getRank() == z.getRank()) {
                // If any four consecutive cards have same rank,
                // there is a quadruple
                hasFourOfAKind = true;
            }
        }
        return hasFourOfAKind;
    }
    
    // This accessor returns whether the hand has a full house
    private boolean checkFullHouse(ArrayList<Card> hand) {
        
        // Creates a copy of the hand array (with new objects, not references)
        ArrayList<Card> copy = new ArrayList<Card>();
        for (Card x : hand) {
            copy.add(new Card(x.getSuit(), x.getRank()));
        }
        
        int i = 0;
        boolean tripleFound = false;
        while (i < 3 && !tripleFound) {
            Card x = copy.get(i);
            Card y = copy.get(i+1);
            Card z = copy.get(i+2);
            if (x.getRank() == y.getRank() && x.getRank() == z.getRank()) {
                // If a triple is found, it is removed from the copy array
                copy.remove(i+2);
                copy.remove(i+1);
                copy.remove(i);
                tripleFound = true;
            }
            i++;
        }
        // Returns true if a triple was found and remaining cards are a pair
        return (tripleFound && checkOnePair(copy));
    }
    
    // This accessor returns whether the hand has a flush
    private boolean checkFlush(ArrayList<Card> hand) {
        boolean hasFlush = true;
        for (Card x : hand) {
            for (Card y : hand) {
                // If any two cards have different ranks, there is no flush
                if (x.getSuit() != y.getSuit()) {
                    hasFlush = false;
                }
            }
        }
        return hasFlush;
    }
    
    // This accessor returns whether the hand has a straight
    private boolean checkStraight(ArrayList<Card> hand) {
        boolean hasStraight = false;
        if (!checkOnePair(hand)) { // First checks that there are no pairs
            // Checks if the first four cards have consecutive ranks
            if (hand.get(3).getRank() == hand.get(0).getRank() + 3) {
                
                // Checks if the last two cards have consecutive ranks
                if (hand.get(4).getRank() == hand.get(3).getRank() + 1) {
                    hasStraight = true;
                }
                
                // Checks if the last two cards are K-A
                if (hand.get(4).getRank() == 1 && hand.get(3).getRank() == 13) {
                    hasStraight = true;
                }
                
                // Checks if the first and last card are 2, A respectively
                if (hand.get(4).getRank() == 1 && hand.get(0).getRank() == 2) {
                    hasStraight = true;
                }
            }
        }
        return hasStraight;
    }
    
    // This accessor returns whether the hand has a triple
    private boolean checkThreeOfAKind(ArrayList<Card> hand) {
        boolean hasThreeOfAKind = false;
        for (int i = 0; i < 3; i++) {
            Card x = hand.get(i);
            Card y = hand.get(i+1);
            Card z = hand.get(i+2);
            if (x.getRank()==y.getRank() && x.getRank()==z.getRank()) {
                // If any three consecutive cards have same rank,
                // there is a triple
                hasThreeOfAKind = true;
            }
        }
        return hasThreeOfAKind;
    }
    
    // This accessor returns whether the hand has two pairs
    private boolean checkTwoPairs(ArrayList<Card> hand) {
        
        // Creates a copy of the hand array (with new objects, not references)
        ArrayList<Card> copy = new ArrayList<Card>();
        for (Card x : hand) {
            copy.add(new Card(x.getSuit(), x.getRank()));
        }
        
        int i = 0;
        boolean pairFound = false;
        while (i < 4 && !pairFound) {
            Card x = copy.get(i);
            Card y = copy.get(i+1);
            if (x.getRank() == y.getRank()) {
                // If a pair is found, it is removed from the copy array
                copy.remove(i+1);
                copy.remove(i);
                pairFound = true;
            }
            i++;
        }
        return checkOnePair(copy); // Returns if the leftover array has a pair
    }
    
    // This accessor returns whether the hand has a pair
    private boolean checkOnePair(ArrayList<Card> hand) {
        boolean hasOnePair = false;
        for (int i = 0; i < hand.size()-1; i++) {
            Card x = hand.get(i);
            Card y = hand.get(i+1);
            if (x.getRank() == y.getRank()) {
                // If any two consecutive cards have same rank, there is a pair
                hasOnePair = true;
            }
        }
        return hasOnePair;
    }
} // Ends the Game class
