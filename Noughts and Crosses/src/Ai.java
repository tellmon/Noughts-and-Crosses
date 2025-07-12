import java.util.Random;

/**
 * The {@code Ai} class simulates an AI that makes moves in a Tic-Tac-Toe game by analyzing
 * possible winning or blocking moves or by making random guesses within certain constraints.
 */
public class Ai { 
	
	/**
	 * Indicates whether the AI has finished making a valid move.
	 */
	private Boolean set = false;
	
	/**
	 * Indicates whether the AI has successfully performed an action during its turn.
	 * If false, the AI generates a random number to guess a square.
	 */
	private Boolean done = false;
	
	/**
	 * Stores a randomly generated number between 0 and 8 to represent a square on the board.
	 */
	private int ranNum = 0;
	
	/**
	 * Tracks how many guesses the AI has made. If the count exceeds 1000, it stops guessing
	 * to prevent endless looping.
	 */
	private int count = 0;
	
	Random rand = new Random();

	/**
	 * Analyzes the game board to find a winning or blocking move. If no such move is found,
	 * the AI makes a random move. The method updates the symbols on the board and returns
	 * the modified board.
	 * 
	 * @param myTitles An array of {@code Title} objects representing the game board tiles.
	 * @return An updated array of {@code Title} objects with the AI's move applied.
	 */
	public Title[] blockAndWinsMove(Title[] myTitles) {	
		
		// Check for winning or blocking moves in rows
		for (int i = 0; i < 9; i += 3) { 			
			if (!done && myTitles[i].getSymbol() == myTitles[1 + i].getSymbol() 
				&& myTitles[i].getSymbol() != '-' && myTitles[2 + i].getSymbol() == '-') {
				myTitles[2 + i].setSymbol('x'); 
				done = true;
			}
					
			if (!done && myTitles[i].getSymbol() == myTitles[2 + i].getSymbol() 
				&& myTitles[i].getSymbol() != '-' && myTitles[1 + i].getSymbol() == '-') {
				myTitles[1 + i].setSymbol('x');
				done = true;
			}
					
			if (!done && myTitles[1 + i].getSymbol() == myTitles[2 + i].getSymbol() 
				&& myTitles[1 + i].getSymbol() != '-' && myTitles[i].getSymbol() == '-') {
				myTitles[i].setSymbol('x');
				done = true;
			}	
		}	
		
		// Check for winning or blocking moves in columns
		for (int i = 0; i < 3; i += 1) {
			if (!done && myTitles[i].getSymbol() == myTitles[3 + i].getSymbol() 
				&& myTitles[i].getSymbol() != '-' && myTitles[6 + i].getSymbol() == '-') {
				myTitles[6 + i].setSymbol('x');	
				done = true;
			}
		
			if (!done && myTitles[i].getSymbol() == myTitles[6 + i].getSymbol() 
				&& myTitles[i].getSymbol() != '-' && myTitles[3 + i].getSymbol() == '-') {
				myTitles[3 + i].setSymbol('x');	
				done = true;
			}
			
			if (!done && myTitles[3 + i].getSymbol() == myTitles[6 + i].getSymbol() 
				&& myTitles[3 + i].getSymbol() != '-' && myTitles[i].getSymbol() == '-') {
				myTitles[i].setSymbol('x');	
				done = true;
			}
		}
		
		// Check for winning or blocking moves in diagonals
		if (!done && myTitles[0].getSymbol() == myTitles[4].getSymbol() 
			&& myTitles[0].getSymbol() != '-' && myTitles[8].getSymbol() == '-') {
			myTitles[8].setSymbol('x');
			done = true;
		}
		
		if (!done && myTitles[0].getSymbol() == myTitles[8].getSymbol() 
			&& myTitles[0].getSymbol() != '-' && myTitles[4].getSymbol() == '-') {
			myTitles[4].setSymbol('x');
			done = true;
		}
		
		if (!done && myTitles[4].getSymbol() == myTitles[8].getSymbol() 
			&& myTitles[4].getSymbol() != '-' && myTitles[0].getSymbol() == '-') {
			myTitles[0].setSymbol('x');
			done = true;
		}
		
		if (!done && myTitles[2].getSymbol() == myTitles[4].getSymbol() 
			&& myTitles[2].getSymbol() != '-' && myTitles[6].getSymbol() == '-') {
			myTitles[6].setSymbol('x');
			done = true;
		}
		
		if (!done && myTitles[4].getSymbol() == myTitles[6].getSymbol() 
			&& myTitles[4].getSymbol() != '-' && myTitles[2].getSymbol() == '-') {
			myTitles[2].setSymbol('x');
			done = true;
		}
		
		if (!done && myTitles[2].getSymbol() == myTitles[6].getSymbol() 
			&& myTitles[2].getSymbol() != '-' && myTitles[4].getSymbol() == '-') {
			myTitles[4].setSymbol('x');
			done = true;
		}
	
		// Make a random move if no winning or blocking moves are found
		if (!done) {
			while (!set) {
				ranNum = rand.nextInt(9); // Random number between 0 and 8
				
				if (myTitles[ranNum].getSymbol() == '-') {
					myTitles[ranNum].setSymbol('x');
					set = true;
				}
				
				count++;
				
				if (count > 1000) {
					set = true;
				}
			}
		}
	
		// Reset variables for the next turn
		done = false;
		set = false;
		count = 0;
		
		return myTitles;
	}

	/**
	 * Resets all internal variables used by the AI.
	 */
	public void reset() {
		done = false;	
		set = false;
		count = 0;
	}
}
