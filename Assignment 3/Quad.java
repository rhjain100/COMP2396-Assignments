import java.util.Arrays;
/**
 * 
 * Quad class is a subclass of Hand which is used to model a Quad hand with methods to check if the hand is a valid quad type, and also return the top card of the hand if it is a valid quad
 * 
 * 
 * @author Rishabh
 *
 */
@SuppressWarnings("serial")
public class Quad extends Hand {
	/**
	 * Constructor that helps in creating a Quad object holding the quad hand of the specific player and quad played by that player 
	 * 
	 * @param player
	 * 		CardGamePlayer object containing specified player with the current hand 
	 * 
	 * @param card
	 * 		CardList object containing list of cards being played by the active player
	 */
	public Quad(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	public Card getTopCard() {
		this.sort();
		if(this.getCard(0).getRank()==this.getCard(1).getRank()) {
			return this.getCard(3);
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
		if (ranks[1] == ranks[2] && ranks[2] == ranks[3] && ranks[3]==ranks[4]){
			return true;
		}
		else if(ranks[0]==ranks[1] && ranks[1] == ranks[2] && ranks[2] == ranks[3]) {
			return true;
		}
		return false;
	}
	
	public String getType() {
		String a = "Quad";
		return a;
	}
	
}
