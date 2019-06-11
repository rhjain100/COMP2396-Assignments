import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * 
 * The BigTwoTable class implements the CardGameTable interface. It is used to build a GUI for the Big Two card game and handle all user actions.
 * 
 * 
 * @author Rishabh
 *
 */

public class BigTwoTable implements CardGameTable {
	private CardGame game;
	private boolean[] selected;
	private int activePlayer;
	private JFrame frame;
	private JPanel bigTwoPanel;
	private JButton playButton;
	private JButton passButton;
	private JTextArea msgArea;
	private Image[][] cardImages;
	private Image cardBackImage;
	private Image[] avatars;
	private JMenu menu;
	private JMenuBar menuBar;
	private JMenuItem quit;
	private JMenuItem restart;
	
	private ArrayList<CardGamePlayer> playerList;
	private ArrayList <Hand> handsOnTable;
	
	/**
	 * 
	 * A constructor for creating a BigTwoTable. It sets up the frames and its components for the GUI.
	 * 
	 * @param game The parameter game is a reference to a card game associates with this table.
	 * 
	 * 
	 */
	public BigTwoTable(CardGame game) {
		this.game = game;
		playerList = game.getPlayerList();
		handsOnTable = game.getHandsOnTable();
		selected = new boolean[13];
		this.resetSelected();
		frame = new JFrame("Big Two Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(1,2));
		
		bigTwoPanel = new BigTwoPanel();
		bigTwoPanel.setLayout(new GridBagLayout());
		
		menu = new JMenu("Game Menu");
		menuBar = new JMenuBar();
		quit = new JMenuItem("End Game");
		restart = new JMenuItem("Restart Game");
		quit.addActionListener(new QuitButtonListener());
		restart.addActionListener(new RestartButtonListener());
		menu.add(quit);
		menu.add(restart);
		menuBar.add(menu);
		
		JPanel leftFrame = new JPanel();
		JPanel rightFrame = new JPanel();
		leftFrame.setLayout(new BorderLayout());
		
		GridBagConstraints dim = new GridBagConstraints();
		dim.gridx = 0;
		dim.ipadx = 0;
		dim.ipady = 0;
		dim.gridy = 0;
		dim.anchor = GridBagConstraints.NORTHWEST;
		dim.insets = new Insets(2,8,0,0);
		dim.gridwidth = 1;
		dim.gridheight =1;
		dim.weightx = 0.5;
		dim.weighty = 0.5;
		
		playButton = new JButton("Play");
		passButton = new JButton("Pass");
		playButton.addActionListener(new PlayButtonListener());
		passButton.addActionListener(new PassButtonListener());
		JPanel buttons = new JPanel();
		buttons.add(playButton);
		buttons.add(passButton);
		
		leftFrame.setBackground(Color.GREEN.darker().darker());
		leftFrame.add(buttons, BorderLayout.SOUTH);
		
		msgArea = new JTextArea();
		leftFrame.add(bigTwoPanel);
		
		rightFrame.setLayout(new BorderLayout());
		JScrollPane scrollPane= new JScrollPane(msgArea);
		rightFrame.add(scrollPane);
		
		avatars = new Image[4];
		avatars[0] = new ImageIcon("src/avatars/Thanos.png").getImage();
		avatars[1] = new ImageIcon("src/avatars/IronMan.png").getImage();
		avatars[2] = new ImageIcon("src/avatars/BlackPanther.png").getImage();
		avatars[3] = new ImageIcon("src/avatars/CaptainAmerica.png").getImage();
		cardBackImage = new ImageIcon("src/cards/b.gif").getImage();
		
		
		
		JLabel row0;
		JLabel row1;
		JLabel row2;
		JLabel row3;
		
		row0 = new JLabel();
		bigTwoPanel.add(row0, dim);
		
		row1 = new JLabel();
		dim.gridy = 2;
		bigTwoPanel.add(row1, dim);
		
		row2 = new JLabel();
		dim.gridy = 4;
		bigTwoPanel.add(row2, dim);
		
		row3 = new JLabel();
		dim.gridy = 6;
		bigTwoPanel.add(row3, dim);

		
		cardImages = new Image[4][13];
		char[] suits = {'d', 'c', 'h', 's'};
		char[] ranks = {'a', '2', '3', '4', '5', '6', '7', '8', '9', 't', 'j', 'q', 'k'};
		for(int i=0; i<4; i++) {
			for(int j=0; j<13; j++) {
				String filname = "src/cards/" + ranks[j] + suits[i] + ".gif";
				cardImages[i][j] = new ImageIcon(filname).getImage();
			}
		}
		frame.setSize(1280,720);
		frame.add(leftFrame);
		frame.add(rightFrame);
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
		
	}
	
	/**
	 * 
	 * A method for setting the index of the active player (i.e., the current player). 
	 * 
	 */
	
	public void setActivePlayer(int activePlayer) {
		if(activePlayer < playerList.size() && activePlayer>=0) {
			this.activePlayer = activePlayer;
		} else {
			this.activePlayer = -1;
		}
	}
	
	/**
	 * 
	 * A method for getting an array of indices of the cards selected. 
	 * 
	 */
	public int[] getSelected() {
		int c=0;
		for(int i=0; i<selected.length; i++) {
			if(selected[i]==true) {
				c++;
			}
		}
		int[] indSelected = new int[c];
		if(c!=0) {
			int x=0;
			for(int i=0; i<selected.length; i++) {
				if(selected[i]==true) {
					indSelected[x]=i;
					x++;
					}
			}	
		}
		return indSelected;
	}
		
	/**
	 * 
	 * A method for resetting the list of selected cards. 
	 * 
	 */
	public void resetSelected() {
		for(int i=0; i<13; i++) {
			selected[i]=false;
		}
	}
	
	/**
	 * 
	 * A method for repainting the GUI. 
	 * 
	 */
	public void repaint() {
		frame.repaint();
	}
	
	/**
	 * 
	 * A method for printing the specified string to the message area of the GUI. 
	 * 
	 */
	public void printMsg(String msg) {
		msgArea.append(msg+"\n");
		frame.repaint();
	}
	
	/**
	 * 
	 * A method for clearing the message area of the GUI. 
	 * 
	 */
	
	public void clearMsgArea() {
		msgArea.setText(null);
	}
	
	/**
	 * 
	 * A method for resetting the GUI. 
	 * 
	 */
	public void reset() {
		resetSelected();
		clearMsgArea();
		enable();
		BigTwoDeck bigTwoDeck = new BigTwoDeck();
		bigTwoDeck.initialize();
		bigTwoDeck.shuffle();
		game.start(bigTwoDeck);
		
	}
	
	
	/**
	 * 
	 * A method for enabling user interactions with the GUI.
	 * 
	 */
	public void enable() {
		playButton.setEnabled(true);
		passButton.setEnabled(true);
		bigTwoPanel.setEnabled(true);
		
	}
	
	/**
	 * 
	 * A method for disabling user interactions with the GUI.
	 * 
	 */
	
	public void disable() {
		playButton.setEnabled(false);
		passButton.setEnabled(false);
		bigTwoPanel.setEnabled(false);
		
	}
	/**
	 * 
	 * An inner class that extends the JPanel class and implements the MouseListener interface. Overrides the paintComponent() method inherited from the JPanel class to draw the card game table. Implements the mouseClicked() method from the MouseListener interface to handle mouse click events. 
	 * 
	 * @author Rishabh
	 *
	 */
	
	class BigTwoPanel extends JPanel implements MouseListener {

		private static final long serialVersionUID = 1L;
		
		/**
		 * 
		 * BigTwoPanel constructor that helps to add Mouse Listener to the panels.
		 * 
		 */
		
		public BigTwoPanel() {
			this.addMouseListener(this);
		}
		

		/*  
		 * {@inheritDoc}
		 */
		public void paintComponent(Graphics graphics) {
			

			for(int i=0; i<playerList.get(activePlayer).getNumOfCards(); i++) {
				
				int rank = playerList.get(activePlayer).getCardsInHand().getCard(i).getRank();
				int suit = playerList.get(activePlayer).getCardsInHand().getCard(i).getSuit();
				
				if(!selected[i]) {
					graphics.drawImage(cardImages[suit][rank], (150+i*20), (30+130*activePlayer), this);
									}
				else if(selected[i]){
					graphics.drawImage(cardImages[suit][rank], (150+i*20), (30+130*activePlayer-15), this);

				}	
			}
			
			int imgpos[] = {14, 148, 279,410};
			for(int i=0; i<4; i++) {
				graphics.drawImage(avatars[i], 0, imgpos[i], this);
			}
			
			
			for(int j = 0; j<4 ; j++) {
				for(int k=0; k<playerList.get(j).getNumOfCards(); k++) {
					if(j!=activePlayer) {
						graphics.drawImage(cardBackImage, (150+k*20), (30+130*j), this);
					}
					
				}
			}
			
			graphics.drawLine(0, 0, 722, 2);
			graphics.drawLine(0, 140, 722, 140);
			graphics.drawLine(0, 270, 722, 270);
			graphics.drawLine(0, 400, 722, 400);
			graphics.drawLine(0, 530, 722, 530);
			
			graphics.setColor(Color.BLACK);
			
			graphics.setFont(new Font("TimesNewRoman", Font.ITALIC, 12));
			if(activePlayer==0 && !game.endOfGame()) {
				graphics.setColor(Color.BLUE);
			}
			graphics.drawString("Player 0", 0, 16);
			graphics.setColor(Color.BLACK);
			if(activePlayer==1 && !game.endOfGame()) {
				graphics.setColor(Color.BLUE);
			}
			graphics.drawString("Player 1", 0, 152);
			graphics.setColor(Color.BLACK);
			if(activePlayer==2 && !game.endOfGame()) {
				graphics.setColor(Color.BLUE);
			}
			graphics.drawString("Player 2", 0, 282);
			graphics.setColor(Color.BLACK);
			if(activePlayer==3 && !game.endOfGame()) {
				graphics.setColor(Color.BLUE);
			}
			graphics.drawString("Player 3", 0, 412);
			graphics.setColor(Color.BLACK);
			
			handsOnTable = game.getHandsOnTable();
			if(handsOnTable.size()!=0) {
				String outp = ("Played by " + handsOnTable.get(handsOnTable.size()-1).getPlayer().getName()+":");
				
				graphics.setFont(new Font("TimesNewRoman", Font.BOLD, 12));
				graphics.drawString(outp, 0, 543);
				
				
				for (int i=0; i<handsOnTable.get(handsOnTable.size()-1).size(); i++ ) {
					
					int a = handsOnTable.get(handsOnTable.size()-1).getCard(i).getSuit();
					int b = handsOnTable.get(handsOnTable.size()-1).getCard(i).getRank();
					graphics.drawImage(cardImages[a][b], (0+i*15), (48+125*4), this);
				}
	
			}
			
		}
		

		/*  
		 * {@inheritDoc}
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int mx = e.getX(), my=e.getY();
			int p =game.getPlayerList().get(activePlayer).getNumOfCards()-1;
			for(int i = p; i>=0; i-- ) {
				if( (my>=30+130*activePlayer-15 && my<=30+130*activePlayer+94-15) && (mx>=150+i*20 && mx<=150+i*20+70) && selected[i]==true) {
					selected[i] =false;
					break;
				}
				else if((my>=30+130*activePlayer && my<=30+130*activePlayer+94) && (mx>=150+i*20 && mx<=150+i*20+70) && selected[i] == false) {
					selected[i] =true;
					break;
				}
			}
			
			frame.repaint();
		}
		

		/*  
		 * {@inheritDoc}
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		/*  
		 * {@inheritDoc}
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		/*  
		 * {@inheritDoc}
		 */
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		/*  
		 * {@inheritDoc}
		 */
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
	/**
	 * 
	 * an inner class that implements the ActionListener interface. Implements the actionPerformed() method from the ActionListener interface to handle button-click events for the “Play” button. When the “Play” button is clicked, it calls the makeMove() method of the CardGame object to make a move.
	 * 
	 * @author Rishabh
	 *
	 */
	class PlayButtonListener implements ActionListener {
		/**
		 * 
		 * Method to perform Play Button logic on the click of the play button.
		 * 
		 */
		public void actionPerformed(ActionEvent e) {
			BigTwo.playClicked=true;
			int[] selCards = getSelected();
			game.makeMove(activePlayer,selCards);
			resetSelected();
			frame.repaint();
		}
	}
	/**
	 * 
	 * An inner class that implements the ActionListener interface. Implements the actionPerformed() method from the ActionListener interface to handle button-click events for the “Pass” button. When the “Pass” button is clicked, it calls the makeMove() method of the CardGame object to make a move.
	 * 
	 * @author Rishabh
	 *
	 */
	class PassButtonListener implements ActionListener {
		/**
		 * 
		 * Method to perform Pass Button logic on the click of the Pass button.
		 * 
		 */
		public void actionPerformed(ActionEvent e) {
			BigTwo.passClicked=true;
			game.makeMove(activePlayer, null);
			resetSelected();
			frame.repaint();
		}
	}
	
	/**
	 * 
	 * An inner class that implements the ActionListener interface. Implements the actionPerformed() method from the ActionListener interface to handle menu-item-click events for the “Restart” menu item. 
	 * 
	 * @author Rishabh
	 *
	 */
	class RestartButtonListener implements ActionListener {
		/**
		 * 
		 * Method to perform Restart Button logic on the click of the Restart button.
		 * 
		 */
		public void actionPerformed(ActionEvent e) {	
			reset();
			clearMsgArea();
			BigTwoDeck bigTwoDeck = new BigTwoDeck();
			bigTwoDeck.shuffle();
			BigTwo.firstPlayer=true;
			BigTwo.isLegal=true;
			BigTwo.numOfPasses=0;
			BigTwo.gamePlay = true;
			BigTwo.passedThrice=false;
			game.start(bigTwoDeck);
			
			
		}
	}
	/**
	 * 
	 * an inner class that implements the ActionListener interface. Implements the actionPerformed() method from the ActionListener interface to handle menu-item-click events for the “Quit” menu item.
	 * 
	 * @author Rishabh
	 *
	 */
	class QuitButtonListener implements ActionListener {
		/*  
		 * {@inheritDoc}
		 */
		public void actionPerformed(ActionEvent e) {		
			System.exit(0);
		}
	}
	
}
