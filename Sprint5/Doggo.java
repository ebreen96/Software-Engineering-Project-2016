/*
 * Eimear O'Shea Breen - 15487912
 * Siobhán O'Sullivan - 15519453
 * Eoghan McDermott - 15345451
 */
public class Doggo implements Bot {
	
	// The public API of YourTeamName must not change
	// You cannot change any other classes
	// YourTeamName may not alter the state of the board or the player objects
	// It may only inspect the state of the board and the player objects

	private boolean hasRolled = false;
	private boolean turnFinished = false;
	
	private BoardAPI board;
	private PlayerAPI player;
	
	
	
	Doggo (BoardAPI board, PlayerAPI player, DiceAPI dice) {
		this.board = board;
		this.player = player;
		return;
	}
	
	public String getName () {
		return "Doggo";
	}
	
	public String getCommand () {
		
		if(player.isInJail()){//check if the player is in jail
			return jailDecision();
		}
		
		String command = "";//need to give it an initial value
		
		if(!hasRolled){//initial command should be roll
			command = "roll";
			hasRolled = true;
		}
		else{
			if(board.isProperty(player.getPosition()) && !board.getProperty(player.getPosition()).isOwned()){//check if current position is a property and not owned
				command = "buy";//buy property if you are on a valid square
			}
				turnFinished = true;//after rolling & buying your turn is finished
		}
		
		if(player.getBalance() < 50)//if in danger of becoming bankrupt
		{
			command = "mortgage" + player.getLatestProperty().getShortName();
		}

		if(turnFinished){//reset values and end turn
			command = "done";
			hasRolled = false;
			turnFinished = false;
		}
		
		return command;
	}
	
	public String getDecision () {
		// Add your code here
			return "pay";
	}
	


	private String jailDecision(){
		
		if(player.hasGetOutOfJailCard())
			return "card";//use a get out of jail free card if you have it
		else
			return "pay";//otherwise pay
	}
}