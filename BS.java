import java.io.*;
import java.util.*;
public class BS { // Creating the card game BS
	public static void main(String [] args) {
		// Problem is the beginning, the ace of spades isn't being removed and isn't being played from the correct hand half the time
		// End product must find the person with the Ace of Spades and start the game with the Player's card

		
		/*  ________________
		 * |GLOBAL VARIABLES|
		 * |________________|
		 * 
		 ----INTEGERS----
		 * int winner - index of the person who won
		 * int numOfPlayers - The amount of players playing the game
		 * int checkStart - Variable used to make sure deal sent back the correct integer and that
		 * 					the cards were dealt correctly 
		 * int sysExitIfTen - Variable used that keeps track of how many times the while loop involving checkStart has
		 * 						looped through 
		 * int rando - Used to generate a random number between 0 and numOfPLayers to decide who starts off the game
		 * int rotation - Keeps track of the rotation, the next person after player who plays the ace of spades will go 
		 * int rotation_temp - Will be used to temporarily represent the number of the player whose turn it is
		 * int cardValue - Value of the card that is to be played for the round 
		 * int caller - Index of the player that is calling BS
		 * int caller_temp - Will be used to print out number of the player who called BS 
		 * ----INTEGERS----
		 * 
		 * ----BOOLEAN----
		 * boolean bs - Represents whether the BS call was correct or wrong (TRUE if correct, FALSE if wrong)
		 * ----BOOLEAN----
		 * 
		 * ----CHARACTER----
		 * char y_or_n - Extracted character from String line that determines whether or not the user types Yes or No
		 * ----CHARACTER----
		 * 
		 * ----STRING----
		 * String val_to_string - Used to turn the card value to a string in order to tell everyone the card value someone
		 * 							has played or at least is supposed to have played
		 * String indexes - Line that contains the positions of the cards in the player's hand that is chosen to be put in 
		 * 					play
		 * String line - Used to extract the first character of the string to determine whether the user typed in a yes or
		 * 				no
		 * ----STRING----
		 * 
		 * ----CARD----
		 * Card[] pile_bound - Card array that holds the cards that the player plans to push onto the pile
		 * ----CARD----
		 * 
		 * ----DECK----
		 * Deck deck - Creates the deck that will be used for the game
		 * ----DECK----
		 * 
		 * ----SCANNER----
		 * Scanner sc - Scanner used throughout the program
		 * ----SCANNER----
		 */
		
		//----INTEGERS----//
		int winner = 0;
		int numOfPlayers;
		int checkStart;
		int sysExitIfTen;
		int rando;
		int rotation;
		int rotation_temp;
		int cardValue;
		int caller;
		int caller_temp;
		//----INTEGERS----//
		
		//----BOOLEAN----//
		boolean bs;
		//----BOOLEAN----//
		
		//----CHARACTER----//
		char y_or_n;
		//----CHARACTER----//
		
		//----STRING----//
		String val_to_string;
		String indexes;
		String line;
		//----STRING----//
		
		//----CARD----//
		Card[] pile_bound;
		//----CARD----//
		
		//----DECK----//
		Deck deck = new Deck(false);
		deck.shuffle();
		//----DECK----//
		
		//----SCANNER----//
		Scanner sc = new Scanner(System.in);
		//----SCANNER----//
/**********************************************Step 1: Get the Number of Players**************************************************/		
		
		System.out.println("Number of Players: ");		// Get the # of players from the
		numOfPlayers = Integer.parseInt(sc.nextLine());  // keyboard
		while(numOfPlayers > 4 || numOfPlayers <= 1) {
			System.out.println("Too many players, must be 2-4 players"); // Makes sure # of players
			System.out.println("Number of Players: ");				  // does not exceed 4
			numOfPlayers = Integer.parseInt(sc.nextLine());
		}
/**********************************************Step 1: Get the Number of Players**************************************************/		
	
/***************************************************Step 2: Deal the Cards********************************************************/
		ArrayList<ArrayList<Card>> hands = new ArrayList<>();
		checkStart = deal(numOfPlayers, hands, deck);
		
		sysExitIfTen = 0;
		while(checkStart != 1) {
			checkStart = deal(numOfPlayers, hands, deck);
			sysExitIfTen++;
			if(sysExitIfTen == 10)
				System.exit(0);
		}
		System.out.println("Let's play BS!\n");
/*************************************************Step 2: Deal the Cards******************************************************/
		

/*************************************************Step 3: Start the Pile******************************************************/	
		Stack<Card> thePile = new Stack<>();
		rando = (int) (Math.random()*numOfPlayers); // Randomly starts the rotation depending on numOfPlayers
		rotation = rando; // Keeps track of the rotation, the next person after player who plays the ace of spades will go
		cardValue = 1; // Keeps track of the number or face card that is supposed to be played 
		
		if((numOfPlayers == 2 && rotation == 1) || (numOfPlayers == 3 && rotation == 2) || (numOfPlayers == 4 && rotation == 3))
			rotation = 0;
		else
			rotation++;
		System.out.println("Player #" + (rotation+1) + ", it is your turn\n");
/*************************************************Step 3: Start the Pile******************************************************/
		
/****************************************************Step 4: The Play*********************************************************/
		// Start of the while loop
		if(numOfPlayers == 2) {
			while(hands.get(0).size() > 0 && hands.get(1).size() > 0){
				// Sorts the hand from least to greatest (Ace to King)
				selectionSortTheHand(hands, rotation);
				
				// Will show the player's hand whose turn it is
				showTheHand(hands, rotation, cardValue);
				
				// Will be used to refer to the person who's turn it is by their correct player moniker
				rotation_temp = rotation + 1;
				
				// Will contain the card(s) chosen to be played
				pile_bound = null;
					
				// Prompts the user to input the indexes of cards that he/she wants to play
				System.out.println("Cards you want to put in play: ");

				// Positions of the cards in the player's hand that is chosen to be put in play
				indexes = sc.nextLine();
				while(indexes.equals("")){ // **TEMPORARY PATCH** // For some reason, after BS is called, an empty string is passed
					System.out.println(); // **TEMPORARY PATCH** // to indexes 
					System.out.println("Cards you want to put in play: "); // **TEMPORARY PATCH** // 
					indexes = sc.nextLine(); // **TEMPORARY PATCH** //
				}
					
				// Instantiates the card array that will hold the cards that are to be played
				pile_bound = putInPlay(hands, rotation, indexes, sc);
				
				// Shows the amount of cards they played to everyone
				System.out.println();
				if(pile_bound.length == 1) {
					val_to_string = valToString(cardValue);
					System.out.println("Player " + rotation_temp + " played a(n) " + val_to_string);
					System.out.println();
				}
				else {
					val_to_string = valToString(cardValue);
					System.out.println("Player " + rotation_temp + " played " + pile_bound.length + " " + val_to_string + "s");
					System.out.println();
				}
					
				// Prompts users whether they want to call BS or not
				System.out.println("Does anyone want to call BS, 'Yes' or 'No'? ");
				line = sc.nextLine();
				y_or_n = line.charAt(0);
				y_or_n = checkIfYesOrNo(y_or_n, sc);
				
				// If y_or_n is 'y' or 'Y', call BS
				if(y_or_n == 'y' || y_or_n == 'Y') {
					
					// Prompts the user to type in which player is calling BS
					System.out.println("Player that is calling BS: ");
					caller = sc.nextInt();
					caller--;
					
					// Will be used to refer to the caller by correct player moniker
					caller_temp = caller + 1;
					
					// Calls the BS function and instantiates the bs boolean variable to either true or false
					bs = BS(pile_bound, rotation, cardValue, numOfPlayers, caller, sc);
					
					// If bs is true, then the player that played the cards gets the pile 
					if(bs) {
						for(Card x : thePile)
							hands.get(rotation).add(x);
						
						// Empties the Pile so that the next person that receives it doesn't get the old cards
						int BS_counter = 0;
						int limit = thePile.size();
						while(BS_counter < limit) {
							thePile.pop();
							BS_counter++;
						}
						
						// Print to console what happened
						System.out.println("BS was called by Player " + caller_temp + " and Player " + rotation_temp + " was LYING");
						System.out.println("Player " + rotation_temp + " gets the Pile\n");
					}
					
					// If bs is not true, then the player that called BS will get the pile plus the cards placed
					// in the pilebound array and the player that played the cards will have the cards that he/she
					// played removed from their hand
					else {
						// Removing the cards from the rotation player's hands
						for(Card x : pile_bound)
							hands.get(rotation).remove(x); 
						
						// Caller gets the cards in pile_bound
						for(Card y : pile_bound)
							hands.get(caller).add(y);
							
						// Caller also gets the Pile
						for(Card z : thePile)
							hands.get(caller).add(z);
						
						// Empties the Pile so that the next person that receives it doesn't get the old cards
						int BS_counter = 0;
						int limit = thePile.size();
						while(BS_counter < limit) {
							thePile.pop();
							BS_counter++;
						}
						
						// Print to console what happened
						System.out.println("BS was called by Player " + caller_temp + " and Player " + rotation_temp + " told the TRUTH");
						System.out.println("Player " + caller_temp + " gets the Pile\n");
					}
				} // end of y_or_n if statement
				
			/* After we get out of that if statement, Three things could've happened : 
			 * 1.) No one called BS in which case the pile_bound array will be added to the Pile and the cards in the 
			 * 		pile_bound array will be removed from the rotation players hands and the next turn will proceed
			 * 
			 * 2.) Someone called BS and was correct, in which case the rotation player receives all of the cards in 
			 * 		the Pile. Do not add the cards in the pile_bound array as they have not been removed from the 
			 * 		player's hand yet.
			 * 
			 * 3.) Someone called BS and was wrong, in which case, the player that called BS receives the cards in the 
			 * 		pile_bound array and the Pile and the rotation player will have the cards he placed in the pile_bound
			 * 		array removed from his hand. 
			 */
				
				// If no one called BS
				else {
					// Removing the cards from the rotation player's hands	
					for(Card x : pile_bound)
						hands.get(rotation).remove(x); 
					
					// Pushes the cards from the pile_bound array into the Pile
					for(Card y : pile_bound)
						thePile.push(y);
				}
							
			if(rotation == 1) // If rotation is equal to 1, that means we are on player 2's turn and since it is over, we now
				rotation = 0;	// must go to Player 1's turn by setting rotation equal to 0
			else
				rotation++;
				
			if(cardValue == 13) // If the card to play was a King, the next card to be played is an Ace
				cardValue = 1;
			else
				cardValue++;
				
			System.out.println();
			System.out.println("Next turn please");
			System.out.println();
			} //end of the while loop
		} // end of numOfPlayers == 2 if statement
		
		if(numOfPlayers == 3) {
			while(hands.get(0).size() > 0 && hands.get(1).size() > 0 && hands.get(2).size() > 0){
				// Sorts the hand from least to greatest (Ace to King)
				selectionSortTheHand(hands, rotation);
				
				// Will show the player's hand whose turn it is
				showTheHand(hands, rotation, cardValue);
				
				// Will be used to refer to the person who's turn it is by their correct player moniker
				rotation_temp = rotation + 1;
				
				// Will contain the card(s) chosen to be played
				pile_bound = null;
					
				// Prompts the user to input the indexes of cards that he/she wants to play
				System.out.println("Cards you want to put in play: ");

				// Positions of the cards in the player's hand that is chosen to be put in play
				indexes = sc.nextLine();
				while(indexes.equals("")){ // **TEMPORARY PATCH** // For some reason, after BS is called, an empty string is passed
					System.out.println(); // **TEMPORARY PATCH** // to indexes 
					System.out.println("Cards you want to put in play: "); // **TEMPORARY PATCH** // 
					indexes = sc.nextLine(); // **TEMPORARY PATCH** //
				}
					
				// Instantiates the card array that will hold the cards that are to be played
				pile_bound = putInPlay(hands, rotation, indexes, sc);
				
				// Shows the amount of cards they played to everyone
				System.out.println();
				if(pile_bound.length == 1) {
					val_to_string = valToString(cardValue);
					System.out.println("Player " + rotation_temp + " played a(n) " + val_to_string);
					System.out.println();
				}
				else {
					val_to_string = valToString(cardValue);
					System.out.println("Player " + rotation_temp + " played " + pile_bound.length + " " + val_to_string + "s");
					System.out.println();
				}
					
				// Prompts users whether they want to call BS or not
				System.out.println("Does anyone want to call BS, 'Yes' or 'No'? ");
				line = sc.nextLine();
				y_or_n = line.charAt(0);
				y_or_n = checkIfYesOrNo(y_or_n, sc);
				
				// If y_or_n is 'y' or 'Y', call BS
				if(y_or_n == 'y' || y_or_n == 'Y') {
					
					// Prompts the user to type in which player is calling BS
					System.out.println("Player that is calling BS: ");
					caller = sc.nextInt();
					caller--;
					
					// Will be used to refer to the caller by correct player moniker
					caller_temp = caller + 1;
					
					// Calls the BS function and instantiates the bs boolean variable to either true or false
					bs = BS(pile_bound, rotation, cardValue, numOfPlayers, caller, sc);
					
					// If bs is true, then the player that played the cards gets the pile 
					if(bs) {
						for(Card x : thePile)
							hands.get(rotation).add(x);
						
						// Empties the Pile so that the next person that receives it doesn't get the old cards
						int BS_counter = 0;
						int limit = thePile.size();
						while(BS_counter < limit) {
							thePile.pop();
							BS_counter++;
						}
						
						// Print to console what happened
						System.out.println("BS was called by Player " + caller_temp + " and Player " + rotation_temp + " was LYING");
						System.out.println("Player " + rotation_temp + " gets the Pile\n");
					}
					
					// If bs is not true, then the player that called BS will get the pile plus the cards placed
					// in the pilebound array and the player that played the cards will have the cards that he/she
					// played removed from their hand
					else {
						// Removing the cards from the rotation player's hands
						for(Card x : pile_bound)
							hands.get(rotation).remove(x); 
						
						// Caller gets the cards in pile_bound
						for(Card y : pile_bound)
							hands.get(caller).add(y);
							
						// Caller also gets the Pile
						for(Card z : thePile)
							hands.get(caller).add(z);
						
						// Empties the Pile so that the next person that receives it doesn't get the old cards
						int BS_counter = 0;
						int limit = thePile.size();
						while(BS_counter < limit) {
							thePile.pop();
							BS_counter++;
						}
						
						// Print to console what happened
						System.out.println("BS was called by Player " + caller_temp + " and Player " + rotation_temp + " told the TRUTH");
						System.out.println("Player " + caller_temp + " gets the Pile\n");
					}
				} // end of y_or_n if statement
				
			/* After we get out of that if statement, Three things could've happened : 
			 * 1.) No one called BS in which case the pile_bound array will be added to the Pile and the cards in the 
			 * 		pile_bound array will be removed from the rotation players hands and the next turn will proceed
			 * 
			 * 2.) Someone called BS and was correct, in which case the rotation player receives all of the cards in 
			 * 		the Pile. Do not add the cards in the pile_bound array as they have not been removed from the 
			 * 		player's hand yet.
			 * 
			 * 3.) Someone called BS and was wrong, in which case, the player that called BS receives the cards in the 
			 * 		pile_bound array and the Pile and the rotation player will have the cards he placed in the pile_bound
			 * 		array removed from his hand. 
			 */
				
				// If no one called BS
				else {
					// Removing the cards from the rotation player's hands	
					for(Card x : pile_bound)
						hands.get(rotation).remove(x); 
					
					// Pushes the cards from the pile_bound array into the Pile
					for(Card y : pile_bound)
						thePile.push(y);
				}
							
			if(rotation == 2) // If rotation is equal to 2, that means we are on player 3's turn and since it is over, we now
				rotation = 0;	// must go to Player 1's turn by setting rotation equal to 0
			else
				rotation++;
				
			if(cardValue == 13) // If the card to play was a King, the next card to be played is an Ace
				cardValue = 1;
			else
				cardValue++;
				
			System.out.println();
			System.out.println("Next turn please");
			System.out.println();
			} // end of while
		} // end of numOfPlayers == 3 if statement
		
		if(numOfPlayers == 4) {
			while(hands.get(0).size() > 0 && hands.get(1).size() > 0 && hands.get(2).size() > 0 && hands.get(3).size() > 0) {
				// Sorts the hand from least to greatest (Ace to King)
				selectionSortTheHand(hands, rotation);
				
				// Will show the player's hand whose turn it is
				showTheHand(hands, rotation, cardValue);
				
				// Will be used to refer to the person who's turn it is by their correct player moniker
				rotation_temp = rotation + 1;
				
				// Will contain the card(s) chosen to be played
				pile_bound = null;
					
				// Prompts the user to input the indexes of cards that he/she wants to play
				System.out.println("Cards you want to put in play: ");

				// Positions of the cards in the player's hand that is chosen to be put in play
				indexes = sc.nextLine();
				while(indexes.equals("")){ // **TEMPORARY PATCH** // For some reason, after BS is called, an empty string is passed
					System.out.println(); // **TEMPORARY PATCH** // to indexes 
					System.out.println("Cards you want to put in play: "); // **TEMPORARY PATCH** // 
					indexes = sc.nextLine(); // **TEMPORARY PATCH** //
				}
					
				// Instantiates the card array that will hold the cards that are to be played
				pile_bound = putInPlay(hands, rotation, indexes, sc);
				
				// Shows the amount of cards they played to everyone
				System.out.println();
				if(pile_bound.length == 1) {
					val_to_string = valToString(cardValue);
					System.out.println("Player " + rotation_temp + " played a(n) " + val_to_string);
					System.out.println();
				}
				else {
					val_to_string = valToString(cardValue);
					System.out.println("Player " + rotation_temp + " played " + pile_bound.length + " " + val_to_string + "s");
					System.out.println();
				}
					
				// Prompts users whether they want to call BS or not
				System.out.println("Does anyone want to call BS, 'Yes' or 'No'? ");
				line = sc.nextLine();
				y_or_n = line.charAt(0);
				y_or_n = checkIfYesOrNo(y_or_n, sc);
				
				// If y_or_n is 'y' or 'Y', call BS
				if(y_or_n == 'y' || y_or_n == 'Y') {
					
					// Prompts the user to type in which player is calling BS
					System.out.println("Player that is calling BS: ");
					caller = sc.nextInt();
					caller--;
					
					// Will be used to refer to the caller by correct player moniker
					caller_temp = caller + 1;
					
					// Calls the BS function and instantiates the bs boolean variable to either true or false
					bs = BS(pile_bound, rotation, cardValue, numOfPlayers, caller, sc);
					
					// If bs is true, then the player that played the cards gets the pile 
					if(bs) {
						for(Card x : thePile)
							hands.get(rotation).add(x);
						
						// Empties the Pile so that the next person that receives it doesn't get the old cards
						int BS_counter = 0;
						int limit = thePile.size();
						while(BS_counter < limit) {
							thePile.pop();
							BS_counter++;
						}
						
						// Print to console what happened in terms of the BS call
						System.out.println("BS was called by Player " + caller_temp + " and Player " + rotation_temp + " was LYING");
						System.out.println("Player " + rotation_temp + " gets the Pile\n");
					}
					
					// If bs is not true, then the player that called BS will get the pile plus the cards placed
					// in the pilebound array and the player that played the cards will have the cards that he/she
					// played removed from their hand
					else {
						// Removing the cards from the rotation player's hands
						for(Card x : pile_bound)
							hands.get(rotation).remove(x); 
						
						// Caller gets the cards in pile_bound
						for(Card y : pile_bound)
							hands.get(caller).add(y);
							
						// Caller also gets the Pile
						for(Card z : thePile)
							hands.get(caller).add(z);
						
						// Empties the Pile so that the next person that receives it doesn't get the old cards
						int BS_counter = 0;
						int limit = thePile.size();
						while(BS_counter < limit) {
							thePile.pop();
							BS_counter++;
						}
						
						// Print to console what happened
						System.out.println("BS was called by Player " + caller_temp + " and Player " + rotation_temp + " told the TRUTH");
						System.out.println("Player " + caller_temp + " gets the Pile\n");
					}
				} // end of y_or_n if statement
				
			/* After we get out of that if statement, Three things could've happened : 
			 * 1.) No one called BS in which case the pile_bound array will be added to the Pile and the cards in the 
			 * 		pile_bound array will be removed from the rotation players hands and the next turn will proceed
			 * 
			 * 2.) Someone called BS and was correct, in which case the rotation player receives all of the cards in 
			 * 		the Pile. Do not add the cards in the pile_bound array as they have not been removed from the 
			 * 		player's hand yet.
			 * 
			 * 3.) Someone called BS and was wrong, in which case, the player that called BS receives the cards in the 
			 * 		pile_bound array and the Pile and the rotation player will have the cards he placed in the pile_bound
			 * 		array removed from his hand. 
			 */
				
				// If no one called BS
				else {
					// Removing the cards from the rotation player's hands	
					for(Card x : pile_bound)
						hands.get(rotation).remove(x); 
					
					// Pushes the cards from the pile_bound array into the Pile
					for(Card y : pile_bound)
						thePile.push(y);
				}
							
			if(rotation == 3) // If rotation is equal to 3, that means we are on player 4's turn and since it is over, we now
				rotation = 0;	// must go to Player 1's turn by setting rotation equal to 0
			else
				rotation++;
				
			if(cardValue == 13) // If the card to play was a King, the next card to be played is an Ace
				cardValue = 1;
			else
				cardValue++;
				
			System.out.println();
			System.out.println("Next turn please");
			System.out.println();
			} // end of while
		} // end of numOfPlayers == 4 if statement
		
		System.out.println();
		System.out.println("GAME OVER\n");
		
		// Find the index of the Player who won 
		for(int i = 0; i < numOfPlayers; i++) 
			if(hands.get(i).isEmpty())
				winner = i;
		
		winner++;
		System.out.println("Player " + winner + " won the game of BS!!!");
		
	/****************************************************Step 4: The Play*********************************************************/
	
	}	// end of main //
	
	/*
	 * Method : checkIfResponseValid
	 * 
	 * Input : Scanner sc
	 * 
	 * Output : Checks whether the user types yes or no and returns either y or n. If the user didn't 
	 * 			do neither, we just return N.
	 */
	
	static char checkIfYesOrNo(char y_or_n, Scanner sc) {
		
		switch(y_or_n) {
		case 'Y':
		case 'y':
		case 'N':
		case 'n':
			break;
			
		default:
			System.out.println();
			System.out.println("Response was invalid. There is no BS this round. Dumbass.");
			y_or_n = 'N';
			break;
		}
		return y_or_n;
	}
	
	/*
	 * Method : checkIfCardIsValid
	 * 
	 * Input : ArrayList<ArrayList<Card>> hands, int card, int rotation, Scanner sc
	 * 
	 * Output : Checks if the card that was chosen to be played is within the index bounds of the player's hand.
	 * 			If not, it prompts the user to input the card position again and will do so until the entry is 
	 * 			valid. It then decrements the value by 1 since the card indexes in the ArrayList go from 0 to n.
	 */
	
	static int checkIfCardIsValid(ArrayList<ArrayList<Card>> hands, int card, int rotation, Scanner sc) {
		int sizeOfHand = hands.get(rotation).size();
		sizeOfHand++;
		while(card < 1 || card > sizeOfHand) {
			System.out.println("You typed in " + card + " as a value. This is an invalid input.");
			System.out.println("Please input a valid card position between 1 and " + sizeOfHand);
			System.out.println("Card you want to put in play: ");
			card = sc.nextInt();
		}
		card--;
		return card;
	}
	
	/*
	 * Method : BS
	 * 
	 * Input : ArrayList<ArrayList<Card>> hands, Card[] pile_bound, Stack<Card> thePile, 
	 * 			int rotation, int cardValue, int numOfPlayers
	 * 
	 * Output : Prompts the other players whether they want to call BS or not. If they type Yes, the player number 
	 * 			that is calling BS needs to be typed in to make sure who is calling it specifically since that matters.
	 * 			The pilebound array is then checked, if there is one card that comes back as not matching the card 
	 * 			value for the round, then the BS call was correct and the player that played the BS cards takes back
	 * 			the cards he played, plus whatever was in the pile. If the player played the correct value and wasn't
	 * 			lying, then the player that called BS receives the cards that were played in pilebound and whatever
	 * 			was in the pile. 
	 */
	
	static boolean BS(Card[] pile_bound, int rotation, int cardValue, int numOfPlayers, int caller, Scanner sc) {
		// Will either return true or false
		boolean bs = false;
		
		// if the caller input was invalid
		while(caller < 0 || caller > numOfPlayers-1) { // Values will change depending on numOfPlayers
			System.out.println("The input was wrong, type player numbers 1 through " + numOfPlayers);
			System.out.println("Player that is calling BS: ");
			caller = sc.nextInt();
			caller--;
		}
			
		// Checks whether the cards the player played match the card value that should be played. If not,
		// then bs is set to true and bs is returned after everything
		for(Card x : pile_bound) 
			if(x.getValue() != cardValue)
				bs = true;
		
		return bs;
	}
	
	/*
	 * Method : putInPlay
	 * 
	 * Input : ArrayList<ArrayList<Card>> hands, int rotation, String indexes, Scanner sc
	 * 
	 * Output : Takes the line that the user inputs from the keyboard and turns it into an array of integers.
	 * 			It makes sure that the indexes are valid and sorts it from least to greatest to make it 
	 * 			easier to remove the cards from the players hands. A card array is then created to be 
	 * 			returned so we can call BS before comitting it to the pile.
	 */
	
	static Card[] putInPlay(ArrayList<ArrayList<Card>> hands, int rotation, String indexes, Scanner sc) {
		// We take in these values as one string so I want to split up the data by whitespace
		String[] indexes_array = indexes.split(" "); 
		
		// int array to store the indexes as integers
		int[] int_indexes_array = new int[indexes_array.length];
		
		// Fills the int_indexes_array from the String array 
		for(int i = 0; i < indexes_array.length; i++) {
			int card = Integer.parseInt(indexes_array[i]);
			card = checkIfCardIsValid(hands, card, rotation, sc);
			int_indexes_array[i] = card;
		}
		
		//Sorts the int array from least to greatest
		for(int i = 0; i < int_indexes_array.length-1; i++) {
			for(int j = i+1; j < int_indexes_array.length; j++) {
				if(int_indexes_array[i] > int_indexes_array[j]) {
					int temp = int_indexes_array[i];
					int_indexes_array[i] = int_indexes_array[j];
					int_indexes_array[j] = temp;
				}
			}
		}
		// The card array that we will return 
		Card[] pile_bound = new Card[int_indexes_array.length];
		
		// Filling up the card array with the cards from the player's hand that he/she wants to play
		for(int i = 0; i < int_indexes_array.length; i++) 
			pile_bound[i] = hands.get(rotation).get(int_indexes_array[i]);
		
		return pile_bound;
	}
	
	/*
	 * Method : showTheHand
	 * 
	 * Input : ArrayList<ArrayList<Card>> hands, int placeHolder, int cardValue
	 * 
	 * Output : Accepts the hands ArrayList, the person in the rotation, and the card value to be played.
	 * 			Shows who's turn it is and shows the player's whole hand, suit and card value, so he/she 
	 * 			can choose the cards to be played. If the card value is not 2-10, it will show either,
	 * 			Ace, Jack, Queen, or King
	 */
	
	static void showTheHand(ArrayList<ArrayList<Card>> hands, int placeHolder, int cardValue) {
		System.out.println("Player #" + (placeHolder+1) + ", this is your hand: ");
		for(int i = 0; i < hands.get(placeHolder).size(); i++) {
			Card card = hands.get(placeHolder).get(i);
			System.out.println("(" + (i + 1) + ")" + " - VALUE: " + card.getValueAsString() + " SUIT: " + card.getSuitAsString());
		}
		System.out.println();
		if(cardValue == 1)
			System.out.println("The card value to play is an Ace");
		else if(cardValue == 11)
			System.out.println("The card value to play is a Jack");
		else if(cardValue == 12)
			System.out.println("The card value to play is a Queen");
		else if(cardValue == 13)
			System.out.println("The card value to play is a King");
		else
			System.out.println("The card value to play is " + cardValue);
	}
	
	/*
	 * Method : deal
	 * 
	 * Input : int numOfPlayers, ArrayList<ArrayList<Card>> hands, Deck deck
	 * 
	 * Output : Accepts the amount of players, the hands ArrayList, and the deck of cards so 
	 * 			the cards can be dealt among the players
	 */
	
	static int deal(int numOfPlayers, ArrayList<ArrayList<Card>> hands, Deck deck) {
		deck.shuffle();
		Card dealtCard = new Card();
		ArrayList<Card> list1 = new ArrayList<>(); // Each list represents a hand that will have a certain amount of cards
		ArrayList<Card> list2 = new ArrayList<>(); // This will be 2 - 4 person game so there will be at most 4 hands
		ArrayList<Card> list3 = new ArrayList<>();
		ArrayList<Card> list4 = new ArrayList<>();
		int counter = 0;
		
		switch(numOfPlayers){ // Depending on the amount of players, the cards will be dealt differently
		case 2:
				for(int i = 0; i < 52/numOfPlayers; i++) { // 31 cards will be dealt to each player
					dealtCard = deck.dealCard();
					list1.add(dealtCard); // Player 1's hand
					counter++;
					dealtCard = deck.dealCard();
					list2.add(dealtCard); // Player 2's hand
					counter++;
				}
			hands.add(list1); // They are then added to the overall hands ArrayList
			hands.add(list2);
			break;
			
		case 4:
			for(int i = 0; i < 52/numOfPlayers; i++) {
				dealtCard = deck.dealCard();
				list1.add(dealtCard); // Player 1's hand
				counter++;
				dealtCard = deck.dealCard();
				list2.add(dealtCard); // Player 2's hand
				counter++;
				dealtCard = deck.dealCard();
				list3.add(dealtCard); // Player 3's hand
				counter++;
				dealtCard = deck.dealCard();
				list4.add(dealtCard); // Player 4's hand
				counter++;
			}
			hands.add(list1);
			hands.add(list2);
			hands.add(list3);
			hands.add(list4);
			break;
			
		default:
			for(int i = 0; i < 52/numOfPlayers; i++) {
				dealtCard = deck.dealCard();
				list1.add(dealtCard); // Player 1's hand
				counter++;
				dealtCard = deck.dealCard();
				list2.add(dealtCard); // Player 2's hand
				counter++;
				dealtCard = deck.dealCard();
				list3.add(dealtCard); // Player 3's hand
				counter++;
			}
			dealtCard = deck.dealCard(); // There will be 1 card left over 
			list1.add(dealtCard);		// Which will then be given to
			counter++;					// Player 1
			hands.add(list1);			
			hands.add(list2);
			hands.add(list3);
			break;
		}
		if(counter != 52)
			return -1;
		else 
			return 1;
	}
	
	/*
	 * Method : valToString
	 * 
	 * Input : int cardValue
	 * 
	 * Output : Returns the string interpretation of the card value that is supposed to be played
	 */
	
	static String valToString(int cardValue) {
		switch(cardValue) {
		case 1:
			return "Ace";
		case 2:
			return "Two";
		case 3:
			return "Three";
		case 4:
			return "Four";
		case 5:
			return "Five";
		case 6:
			return "Six";
		case 7:
			return "Seven";
		case 8:
			return "Eight";
		case 9:
			return "Nine";
		case 10:
			return "Ten";
		case 11:
			return "Jack";
		case 12: 
			return "Queen";
		case 13:
			return "King";
		default:
			return "Wrong Input";
		}
	}
	
	
	
	
//**********************************************************SORTS*************************************************************//
	/*
	 * Method : selectionSortTheHand
	 * 
	 * Input : ArrayList<ArrayList<Card>> hands, int rotation
	 * 
	 * Output : Uses the selection sort algorithm to sort the hand of whichever player's turn it is. For
	 * 			each i from 0 n-2, it finds the minimum element from hands.get(rotation).get(i) to 
	 * 			hands.get(rotation).get(n - 1) and swaps it with hands.get(rotation).get(i). Finding all
	 * 			of the minimum elements from n - 1 in order, the array will be sorted. 
	 */

    public static void selectionSortTheHand(ArrayList<ArrayList<Card>> hands, int rotation) {
    for(int i = 0; i < hands.get(rotation).size() - 1; i++) {
        
        int minIndex = findMinimumIndex(hands, rotation, i, hands.get(rotation).size());
        
        swap(hands, rotation, minIndex, i);
    }
}
   
   /*
	 * Method : findMinimumIndex
	 * 
	 * Input : ArrayList<ArrayList<Card>> hands, int rotation, int start, int end
	 * 
	 * Output : Sets an int variable called minIndex to the start value that was passed and sets an
	 * 			int variable minValue to the cardValue of the card at the minIndex, wherever and what-
	 * 			ever that card may be. It then compares all subsequent card values to the minValue and
	 * 			if the subsequent card value is smaller than the current minValue, minValue is changed
	 * 			to whatever the smaller value is and minIndex is changed to whatever index the smaller
	 * 			card belonged to. It will run through to the end of the hand until and return the index
	 * 			that contains the smallest card from i to n - 1. All this is is a subroutine to 
	 * 			selectionSort(). It finds the index of the minimum element in the array from 
	 * 			hands.get(rotation).get(start) to ... .get(rotation).get(end-1)
	 */

   private static int findMinimumIndex (ArrayList<ArrayList<Card>> hands, int rotation, int start, int end) {
   // The minimum index which is what we will be returning which is being initialized
   	// to the first element of the Array List
   int minIndex = start;
   
   // Represents the current minimum value card at the moment 
   Card cd = hands.get(rotation).get(start);
   int minValue = cd.getValue();
   
   // Will be filled up by the values of the other cards in the hand and will be compared
   // to minValue
   int tempValue;
   
   // Combing through the hand to find the minimum index 
   for(int i = start + 1; i < end; i++) {
   		cd = hands.get(rotation).get(i);
   		tempValue = cd.getValue();
   		
   		// If tempValue is greater than the current minValue, then we must change the value of 
   		// minValue as well as the minIndex which is i
       if(tempValue < minValue) {
           minValue = tempValue;
           minIndex = i;
       }
   }
   return minIndex;
}
   
   /*
	  * Method : swap
	  * 
	  * Input : ArrayList <ArrayList<Card>> hands, int rotation, int num1, int num2
	  * 
	  * Output : Swaps the card in the ith position in the hands ArrayList with the card that was found
	  * 				using the minIndex method
	  */
	
 private static void swap(ArrayList<ArrayList<Card>> hands, int rotation, int num1, int num2) {
 		// num1 is the index where the smallest number at the moment is located
 		// num2 is the index in which you are at in selection sort for loop 
 		// cd_min represents the card that is the smallest value card at the moment
 		// cd_swap represents the card that is at the index of the selection sort for loop
 		Card cd_min, cd_swap;
 		cd_min = hands.get(rotation).get(num1);
 		cd_swap= hands.get(rotation).get(num2);
 		
 		// cd_min goes to the index of the selection sort for loop
 		hands.get(rotation).set(num2, cd_min);
 		
 		// cd_swap goes to the index where cd_min used to be
 		hands.get(rotation).set(num1, cd_swap);
 		}
//**********************************************************SORTS*************************************************************//
   
} // end of class
