Daniel Borovskiy
db3428
Programming Project 4

This program consists of 5 Classes:

    PokerTest Class:
     - This class contains the main method

    Game Class
     - This class defines an instance of a game
     - There are two working versions of this game:
       - One takes in a pre-made hand: this version is to test that
         cards and combinations are handled correctly.
       - The other plays a normal game
         - The player is asked to make a bet
         - Then, cards are shuffled
         - Next, the player chooses which cards to replace
         - Finally, the hand is evaluated and winnings are assigned
           - To determine which combination the player has, helper methods
             are called each for a different combination are called in
             descending order

    Player Class
     - This class handles instances of players
     - Its methods can add/ remove cards to/ from a player's hand
     - Accessors allow other classes to see a player's hand or balance

    Deck Class
     - This class handles instances of decks
     - It has methods to shuffle and deal cards
     - The "top" marker is tracked to determine which card to deal next

    Card Class
     - This class handles single cards
     - Each card is associated with a rank and a suit
     - The class implements Comparable to allow card sorting
     - The class also has accessors to return a card's suit and rank
     - The class also has a toString method to output a card to the user

No sources other than class, office hours, or the textbook were used
