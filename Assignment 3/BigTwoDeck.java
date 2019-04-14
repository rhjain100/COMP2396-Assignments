/**
 * 
 * The BigTwoDeck class is a subclass of Deck used to model a deck of cards to be used in the BigTwo card game
 * 
 * @author Rishabh
 *
 */
@SuppressWarnings("serial")
public class BigTwoDeck extends Deck {
	public void initialize() {
		removeAllCards();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				BigTwoCard card = new BigTwoCard(i, j);
				addCard(card);
			}
		}
	}
}
