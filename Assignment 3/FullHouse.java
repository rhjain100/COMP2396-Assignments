import java.util.Arrays;
/**
 * 
 * 
 * FullHouse Class is a subclass of Hand which is used to model a FullHouse hand and check if the hand is a valid FullHouse, and also return the top card of a FullHouse hand
 * 
 * 
 * @author Rishabh
 *
 */
@SuppressWarnings("serial")
public class FullHouse extends Hand{
	/**
	 * This constructor helps creating a FullHouse object holding the fullhouse hand of specific player and cards played by that player 
	 * 
	 * @param player
	 * 		CardGamePlayer object containing specified player with the current hand 
	 * 
	 * @param card
	 * 		CardList object containing list of cards being played by the active player
	 */
	public FullHouse(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	
	public Card getTopCard() {
		this.sort();
		if(this.getCard(0).getRank()==this.getCard(1).getRank() && this.getCard(0).getRank()==this.getCard(2).getRank()) {
			return this.getCard(2);
		} else {
			return this.getCard(4);
		}
	}
	public boolean isValid() {
		if(this.size()!=5)
			return false;
		int[] ranks = new int[5];
		
		for(int i=0; i<5; i++) {
			ranks[i] = this.getCard(i).getRank();
		}
		Arrays.sort(ranks);
		if(ranks[0]==ranks[1] && ranks[1] == ranks[2] && ranks[3] == ranks[4]) {
			return true;
		}
		if(ranks[0]==ranks[1] && ranks[2] == ranks[3] && ranks[3] == ranks[4]) {
			return true;
		}
		return false;
	}
	
	public String getType() {
		String s = "FullHouse";
		return s;
	}

}
