import java.util.ArrayList;
/**
 * The BigTwo class to represents the BigTwo object and contains logic about the game
 * 
 * @author Rishabh
 * 
 */
public class BigTwo implements CardGame {

	private Deck deck;
	private ArrayList<CardGamePlayer> playerList;
	private ArrayList<Hand> handsOnTable;
	private int currentIdx;
	private BigTwoTable table;
	/**
	 * 
	 * Checker for first player.
	 * 
	 */
	public static boolean firstPlayer = true;
	/**
	 * 
	 * Checker to check if move is legal or not.
	 * 
	 */
	public static boolean isLegal = true;
	/**
	 * 
	 * Number of passes
	 * 
	 */
	public static int numOfPasses=0;
	/**
	 * 
	 * Checker for game play, to show if game play is to be continued or not
	 * 
	 */
	public static boolean gamePlay =true;
	/**
	 * 
	 * Checker to check if three consecutive passes have been made or not
	 * 
	 */
	public static boolean passedThrice=false;
	/**
	 * 
	 * Checker to check if the pass button has been clicked.
	 * 
	 */
	public static boolean passClicked=false;
	/**
	 * 
	 * Checker to check if play button has been clicked.
	 * 
	 */
	public static boolean playClicked=false;
	/**
	 * 
	 *  A constructor called for the BigTwo card game being created.
	 *   
	 */
	public BigTwo() {
		handsOnTable= new ArrayList<>();
		playerList = new ArrayList<>();
		table = new BigTwoTable(this);
		for(int i=0; i<4; i++) {
			playerList.add(new CardGamePlayer());
		}
		
		
	}
	/**
	 * 
	 * Method to retrieve the deck of cards being played 
	 * 
	 * @return Deck object containing the cards currently being played 
	 * 
	 */
	
	public Deck getDeck() {
		return this.deck;
	}
	/**
	 * 
	 * Method to retrieve list of players playing
	 * 
	 * @return Arraylist containing the list of players 
	 * 
	 */
	public ArrayList<CardGamePlayer> getPlayerList() {
		return this.playerList;
	}
	/**
	 * 
	 * Method to retrieve current cards that have been played by the last player 
	 * 
	 * @return Arraylist containing the cards that have been played by the last player
	 * 
	 */
	public ArrayList<Hand> getHandsOnTable() {
		return this.handsOnTable;
	}
	/**
	 * 
	 * Method to retrieve the index of the active player
	 * 
	 * @return an int type showing index of the active player which could either be 0, 1, 2 or 3 
	 * 
	 */
	public int getCurrentIdx() {
		return this.currentIdx;
	}
	
	/* 
	 *  {@inheritDoc}
	 */
	public int getNumOfPlayers() {
		return playerList.size(); 
	}
	
	
	/**
	 * 
	 * A method for starting/restarting the game with a given shuffled deck of cards.
	 * 
	 * @param deck object given as the shuffled deck of cards to be used to play the game 
	 * 
	 */
	public void start(Deck deck) {
		handsOnTable.clear();
		
		for(int i=0; i<4; i++) {
			playerList.get(i).removeAllCards();
			for(int j=0; j<13; j++) {
				Card card = deck.removeCard(0);
				playerList.get(i).addCard(card);
				if(card.getSuit()==0 && card.getRank()==2) {
					currentIdx=i;
				}
				
			}
		}
		for(int i=0; i<4; i++) {
			playerList.get(i).getCardsInHand().sort();
		}
		
		table.setActivePlayer(currentIdx);
		table.repaint();
	}
	/**
	 * 
	 * A method for making a move by a player with the specified playerID using the cards specified by the list of indices. 
	 * 
	 */
	public void makeMove(int playerID, int[] cardIdx) {
		checkMove(playerID, cardIdx);
	}
	
	/**
	 * 
	 * A method for checking a move made by a player. 
	 * 
	 */
	public void checkMove(int playerID, int[] cardIdx) {
		Hand checkHand = null;
		CardList cardList = new CardList();
		CardList hand = new CardList();
		CardGamePlayer player = new CardGamePlayer();
		player = playerList.get(currentIdx);
		cardList = player.getCardsInHand();
		hand = player.play(cardIdx);
		table.repaint();
		if(currentIdx == playerID) {
			//table.printMsg("boom0");
			if(hand == null && firstPlayer == true) {
				isLegal = false;
				//table.printMsg("Boom1");
			}
			
			else {
				
				if(hand!=null) {
					//table.printMsg("boom!null");
					checkHand = composeHand(player,hand);
					if(firstPlayer==true && checkHand!=null) {
						//table.printMsg("boom!null2");
						if(checkHand.contains(new Card(0,2)) && gamePlay==true){
							gamePlay=false;
							isLegal=false;
						}
						if(!gamePlay) {
							table.printMsg("Player "+playerID +"'s turn:");
							table.printMsg("{" + checkHand.getType()+"}"+ checkHand.toString()+"\n");
							handsOnTable.add(checkHand);
							numOfPasses=0;
							if(!passedThrice) {
								firstPlayer= false;
							} else {
								firstPlayer = true;
							}
							currentIdx=(currentIdx+1)%4;
							table.setActivePlayer(currentIdx);
							for(int i=0; i<checkHand.size(); i++) {
								cardList.removeCard(checkHand.getCard(i));
							}
							passedThrice=false;	
							isLegal=true;
							//table.printMsg("boom2");
						}
					}
					else if(handsOnTable.size()!=0 && checkHand!=null) {
						int m=handsOnTable.size()-1;
						if(checkHand.beats(handsOnTable.get(m)) && handsOnTable.get(m).size() == checkHand.size()) {
							//table.printMsg("boom3");
							table.printMsg("Player "+playerID +"'s turn:");
							table.printMsg("{" + checkHand.getType() + "}" + checkHand.toString() + "\n");
							handsOnTable.add(checkHand);
							numOfPasses=0;
							isLegal=true;
							if(!passedThrice) {
								firstPlayer=false;
							} else {
								firstPlayer=true;
								passedThrice=false;
							}
							currentIdx=(currentIdx+1)%4;
							table.setActivePlayer(currentIdx);
							for(int i=0; i<checkHand.size(); i++) {
								cardList.removeCard(checkHand.getCard(i));
							}	
						} else {
							isLegal=false;
						}
					} else {
						isLegal = false;
					}		
						
				} else {
					if(playClicked==true && passClicked==false) {
						table.printMsg("Player "+playerID +"'s turn:");
						table.printMsg("Not a legal move!!! Please select cards to Play.\n");
						playClicked=false;
						
					}
					else if(passClicked==true) {
						BigTwo.passClicked=false;
						//table.printMsg("boom4");
						numOfPasses+=1;
						
						table.printMsg("Player "+playerID +"'s turn:");
						table.printMsg("{Pass}\n");
						currentIdx= (currentIdx+1)%4;
						if(numOfPasses==3) {
							numOfPasses=0;
							passedThrice=true;
							isLegal=true;
						}
						table.setActivePlayer(currentIdx);
						if(!passedThrice) {
							firstPlayer=false;
						} else {
							firstPlayer = true;
						}
						passedThrice=false;	
						
					}

				}
				
			}
			
			if(gamePlay==true) {
				//table.printMsg("boom5");
				isLegal=false;
			}
			
			if(!isLegal) {
				//table.printMsg("boom6");
				if(hand!=null) {
					//table.printMsg("boom7");
					table.printMsg("Player "+playerID +"'s turn:");
					table.printMsg(hand.toString() + " <== Not a legal move!!!\n");
				} else {
					table.printMsg("Player "+playerID +"'s turn:");
					table.printMsg("Not a legal move!!!\n");
					
				}
				isLegal=true;
			}
			
			
		}	
		if(endOfGame() == true) {
			table.printMsg("");
			table.printMsg("Game ends");
			
			
			for(int i = 0; i < playerList.size();i++)
			{
					if(playerList.get(i).getCardsInHand().size() == 0)
					{
						table.printMsg("Player " + i + " wins the game"); 
					}
				
				else
				{
					table.printMsg("Player " + i + " has " + playerList.get(i).getCardsInHand().size() + " cards in hand");
				}
			}
			

			table.disable();
			for(int i=0; i<4; i++) {
				playerList.get(i).removeAllCards();
			}
			handsOnTable.clear();
			table.disable();
			
		}
		table.resetSelected();
		table.repaint();
		

	}
	/* 
	 *  {@inheritDoc}
	 */
	public boolean endOfGame() {
		int prev;
		if (currentIdx != 0) {
			prev = currentIdx - 1;
		} else {
			prev = 3;
		}
		
		if (playerList.get(prev).getNumOfCards()==0) {
			return true;
		}
		return false;
	}

					
	

			
	/**
	 * 
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
	 * 
	 * Method to return a valid hand that is seen from all the list of cards the player plays.
	 * 
	 * @param player CardGamePlayer object containing a list of players currently in the game 
	 * 
	 * @param cards CardList object which containing a list of cards the active player plays
	 * 
	 * @return the type of hand
	 * 
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
	
//	public void resetvars() {
//		firstPlayer = true;
//		isLegal = true;
//		numOfPasses=0;
//		gamePlay =true;
//		passedThrice=false;
//	}
	
	
	
}
