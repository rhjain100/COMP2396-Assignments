import java.util.ArrayList;
/**
 * The BigTwo class to represents the BigTwo object and contains logic about the game
 * 
 * @author Rishabh
 * 
 */
public class BigTwo {

	private Deck deck;
	private ArrayList<CardGamePlayer> playerList;
	private ArrayList<Hand> handsOnTable;
	private int currentIdx;
	private BigTwoConsole bigTwoConsole;
	/**
	 *  A constructor called for the BigTwo card game being created. 
	 */
	public BigTwo() {
		handsOnTable= new ArrayList<>();
		bigTwoConsole = new BigTwoConsole(this); //what is parameter??
		playerList = new ArrayList<>();
		
		for(int i=0; i<4; i++) {
			playerList.add(new CardGamePlayer());
		}
	}
	/**
	 * Method to retrieve the deck of cards being played 
	 * 
	 * @return
	 * 		Returns deck object containing the cards currently being played 
	 */
	
	public Deck getDeck() {
		return this.deck;
	}
	/**
	 * Method to retrieve list of players playing
	 * 
	 * @return Arraylist containing the list of players 
	 * 
	 */
	public ArrayList<CardGamePlayer> getPlayerList() {
		return this.playerList;
	}
	/**
	 * Method to retrieve current cards that have been played by the last player 
	 * 
	 * @return Arraylist containing the cards that have been played by the last player
	 */
	public ArrayList<Hand> getHandsOnTable() {
		return this.handsOnTable;
	}
	/**
	 * Method to retrieve the index of the active player
	 * 
	 * @return an int type showing index of the active player which could either be 0, 1, 2 or 3 
	 */
	public int getCurrentIdx() {
		return this.currentIdx;
	}
	/**
	 * a method for starting a Big Two card game. It creates a Big Two card game, create and shuffle a deck of cards, and start the game with the deck of cards.
	 * 
	 * @param Deck BigTwoDeck object given as the shuffled deck of cards to be used to play the game 
	 * 
	 */
	public void start(BigTwoDeck deck) {
		bigTwoConsole = new BigTwoConsole(this);
		CardList hand = new CardList();
		CardList cards = new CardList();
		CardGamePlayer player = new CardGamePlayer();
		int[] cardsSelected;
		Hand checkHand;
		for(int i=0; i<4; i++) {
			for(int j=0; j<13; j++) {
				playerList.get(i).addCard(deck.getCard((i*13)+j));
				
			}
		}
		
		for(int i=0; i<4; i++) {
			playerList.get(i).getCardsInHand().sort();
		}
		
		int pn = 0;
		
		for(int i=0; i<4; i++) {
			if(playerList.get(i).getCardsInHand().contains(new Card(0,2))) {
				pn = i;
				bigTwoConsole.setActivePlayer(pn);
				bigTwoConsole.repaint();
				break;
			}
		}
		int newpn = pn;
		int numOfPasses = 0;
		boolean gamePlay= true;
		boolean checks=true;
		
		
		boolean checker =true;
		while(checker==true) {
			
			player = playerList.get(newpn);
			cards = player.getCardsInHand();
			cardsSelected = bigTwoConsole.getSelected();
			hand = player.play(cardsSelected);
			
			if(numOfPasses!=3 && hand==null) {
				if(gamePlay == true) {
					System.out.println("Not a legal move!!!\n");
					checks=false;
				}
				else {
					System.out.println("{Pass}\n");
					newpn= (newpn+1)%4;
					numOfPasses+=1;
					bigTwoConsole.setActivePlayer(newpn);
				}
				
			} else if( numOfPasses==3 && hand== null) {
				System.out.println("Not a legal move!!!\n");
				checks=false;
			}
			
			else if((hand.contains(new Card(0,2))==false) && newpn == pn-- ) {
				pn++;
				System.out.println("Not a legal move!!!\n");
				checks=false;
				
			}
				
			
			else if( numOfPasses!=3 && handsOnTable.size()!=0 && hand.size() != handsOnTable.get(handsOnTable.size()-1).size() ) {
				System.out.println("Not a legal move!!!\n");
				checks=false;
			}
			
			else {
				gamePlay = false;		
				pn = -1;
				if(numOfPasses == 3) {
					numOfPasses = 0;
					handsOnTable.clear();
				}
				checkHand = composeHand(player, hand);
				if(checkHand!=null) {
					if(handsOnTable.size()!=0) {
						if(checkHand.beats(handsOnTable.get(handsOnTable.size()-1))) {
							handsOnTable.add(checkHand);
							numOfPasses = 0;
							
							System.out.println("{"+checkHand.getType()+"}" + checkHand.toString() + "\n" );
						
							
							newpn = (newpn +1) % 4;
							
							bigTwoConsole.setActivePlayer(newpn);
							
							
							for(int i=0; i<cardsSelected.length; i++) {
								cards.removeCard(cardsSelected[i] - i) ;
							}
						}	
						else {
							System.out.println("Not a legal move!!!\n");
							checks=false;
						}
					}
					
					
					else {	
						gamePlay = false;	
						handsOnTable.add(checkHand);
						numOfPasses = 0;
						System.out.println("{"+checkHand.getType()+"} "+checkHand.toString()+"\n" );	
						newpn=(newpn+1)%4;
						bigTwoConsole.setActivePlayer(newpn);
						for(int i=0; i<cardsSelected.length; i++) {
							cards.removeCard(cardsSelected[i] - i) ;
						}
					}
				}
					
				else{
					System.out.println("Not a legal move!!!" + "\n");
					checks=false;
				}
			}
			
			if(cards.isEmpty() == true) {
				checker = false;
			}
					
			if(checker == true) {
				if(checks==true) {
					bigTwoConsole.repaint();	
				} else {
					checks=true;
				}
			}
			
		}			
	
		for(int i=0; i<playerList.size(); i++) {
			CardGamePlayer displayPlayer = playerList.get(i);
			String nm;
			nm = displayPlayer.getName();
			System.out.println("<"+ nm+">");
			displayPlayer.getCardsInHand().print(true,true);
		}
		
		System.out.println("<Table>");
		
		Hand isLast;
		if(handsOnTable.isEmpty()==false) {
			isLast = handsOnTable.get(handsOnTable.size()-1);
		}
		else {
			isLast = null;
		}
		if(isLast!= null ) {
			System.out.print("	<" +isLast.getPlayer().getName()+"> {"+isLast.getType()+ "} ");
			isLast.print(true,false);
		}
		else {
			System.out.println("[Empty]");
		}
		System.out.println("");
		
		
		
		System.out.println("Game Ends");
		
		for(int i=0; i< playerList.size() && i<4 ; i++) {
			if(playerList.get(i).getCardsInHand().size() != 0) {
				System.out.println(playerList.get(i).getName() + " has " + playerList.get(i).getCardsInHand().size() + " cards in hand.") ;	
			}
			else {
				System.out.println(playerList.get(i).getName() + " wins the game.");
			}
		}
					
	}

			
	/**
	 * A method for starting a Big Two card game. It creates a Big Two card game, creates and shuffles a deck of cards, and starts the game with the deck of cards.
	 * 
	 * @param args not being used
	 * 
	 */
			

	public static void main(String args[]) {
		BigTwo b = new BigTwo();
		BigTwoDeck d = new BigTwoDeck();
		d.initialize();
		d.shuffle();
		b.start(d);
		
	}
	/**
	 * Method to return a valid hand that is seen from all the list of cards the player plays.
	 * 
	 * @param player CardGamePlayer object containing a list of players currently in the game 
	 * 
	 * @param cards CardList object which containing a list of cards the active player plays
	 * 
	 * @return the type of hand
	 */
	private Hand composeHand(CardGamePlayer player, CardList cards) {	
		Single single = new Single(player, cards);
		Pair pair = new Pair(player, cards);
		Triple triple = new Triple(player, cards);
		Straight straight = new Straight(player, cards);
		Flush flush = new Flush(player, cards);
		FullHouse fullhouse = new FullHouse(player, cards);
		Quad quad = new Quad(player, cards);
		StraightFlush straightflush = new StraightFlush(player, cards);
		if(single.isValid() == true) {
			return single; 
		}
			
		if(pair.isValid() == true) {
			return pair; 
		}
		if(triple.isValid() == true) {
			return triple; 
		}
		if(straight.isValid() == true) {
			return straight; 
		}
		else if(flush.isValid() == true) {
			return flush;
		}
		else if(fullhouse.isValid() == true) {
			return fullhouse;
		}
		else if(quad.isValid() == true) {
			return quad;
		}
		else if(straightflush.isValid() == true) {
			return straightflush;
		}
		return null;
		
	}
	
	
	
}
