/**
 * 
 * Pair class is a subclass of Hand which is used to model a Pair hand with methods to check if the hand is a valid pair type, and also return the top card of the hand if it is a valid pair
 * 
 * 
 * @author Rishabh
 *
 */
@SuppressWarnings("serial")
public class Pair extends Hand{
	/**
	 * Constructor that helps in creating a Pair object holding the pair hand of the specific player and cards played by that player 
	 * 
	 * @param player
	 * 		CardGamePlayer object containing specified player with the current hand 
	 * 
	 * @param card
	 * 		CardList object containing list of cards being played by the active player
	 */
	public Pair(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	
	public Card getTopCard() {
		this.sort();
		return this.getCard(1);
	}
	public boolean isValid() {
		if(this.size()==2) {
			if(this.getCard(0).getRank()==this.getCard(1).getRank()) {
				return true;
			}
		}
		return false;
	}
	
	public String getType() {
		String s = "Pair";
		return s;
	}

}
