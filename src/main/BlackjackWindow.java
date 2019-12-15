package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BlackjackWindow extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 696, HEIGHT = 564;
	
	private Game game;
	private Player player;
	private Dealer dealer;
	private ArrayList<Card> deck;
	
	private ImageIcon background;
	private JFrame frame;
	private NicknameQuery nameQuery;
	
	private ArrayList<JLabel> cardLabels = new ArrayList<JLabel>();
	private JLabel nameTxt;
	
	
	public BlackjackWindow(Game game, Player player, Dealer dealer, ArrayList<Card> deck) {	
		this.game = game;
		this.player = player;
		this.dealer = dealer;
		this.deck = deck;
		
		frame = new JFrame();
		frame.add(this);
		
		initButtons();		
		initGameWindow();
		
		nameQuery = new NicknameQuery(nameTxt);		
	}
	
	private void initGameWindow() {
		background = new ImageIcon(getClass().getResource("/resources/table/table.png"));
		
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Blackjack");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	protected void initButtons() {
		
		//deal, hit, stand
		JButton deal = new JButton("Deal");
		deal.setBounds(299, 478, 100, 25);
		deal.addActionListener(new dealListener());
		JButton hit = new JButton("Hit");
		hit.setBounds(299, 506, 100, 25);
		hit.addActionListener(new hitListener());
		JButton stand = new JButton("Stand");
		stand.setBounds(299, 534, 100, 25);
		
		//set bet, clear bet
		JButton setBet = new JButton("Set Bet");
		setBet.setBounds(174, 478, 100, 39);
		JButton clearBet = new JButton("Clear Bet");
		clearBet.setBounds(174, 520, 100, 39);
		
		//current bet
		JLabel currentBet = new JLabel("CURRENT BET: " + player.getBet());
		currentBet.setBounds(250, 428, 200, 25);
		currentBet.setFont(new Font("Arial", Font.BOLD, 13));
		currentBet.setForeground(Color.black);
		
		//bets
		JButton bet50 = new JButton("$50");
		bet50.setBounds(6, 478, 80, 25);
		JButton bet100 = new JButton("$100");
		bet100.setBounds(6, 506, 80, 25);
		JButton bet500 = new JButton("$500");
		bet500.setBounds(6, 534, 80, 25);
		JButton bet1000 = new JButton("$1000");
		bet1000.setBounds(89, 478, 80, 25);
		JButton bet2000 = new JButton("$2000");
		bet2000.setBounds(89, 506, 80, 25);
		JButton bet5000 = new JButton("$5000");
		bet5000.setBounds(89, 534, 80, 25);
		
		//PLAYER's balance
		nameTxt = new JLabel("Your");
		nameTxt.setBounds(450, 490, 200, 25);
		nameTxt.setFont(new Font("Arial", Font.BOLD, 15));
		nameTxt.setForeground(Color.orange);
		JLabel balanceTxt = new JLabel("BALANCE: ");
		balanceTxt.setBounds(450, 510, 100, 25);
		balanceTxt.setFont(new Font("Arial", Font.BOLD, 15));
		balanceTxt.setForeground(Color.orange);
		JLabel balance = new JLabel("$" + player.getBalance());
		balance.setBounds(450, 530, 100, 25);
		balance.setFont(new Font("Arial", Font.BOLD, 15));
		balance.setForeground(Color.black);
		
		//quit
		JButton quit = new JButton("Quit");
		quit.setBounds(590, 534, 100, 25);
		quit.addActionListener(new quitListener());
		
		frame.add(deal);
		frame.add(hit);
		frame.add(stand);
		
		frame.add(setBet);
		frame.add(clearBet);
		
		frame.add(currentBet);		
		
		frame.add(bet50);
		frame.add(bet100);
		frame.add(bet500);
		frame.add(bet1000);
		frame.add(bet2000);
		frame.add(bet5000);
		
		frame.add(nameTxt);
		frame.add(balanceTxt);
		frame.add(balance);
		
		frame.add(quit);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, WIDTH, HEIGHT, this);
		}	
	}
		
	private void paintCards(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
		
		//poistetaan vanhat kortit
		for(int i = 0; i < cardLabels.size(); i++) {
			this.remove(cardLabels.get(i));
		}
		
		//pelaajan summa
		JLabel playerSum = new JLabel();
		if(player.hasBlackjack()) {//blackjack
			playerSum.setText("21");
		}else {
			if(player.hasAce() && player.getHandSum()+10 <= 21) {
				playerSum.setText(String.valueOf(player.getHandSum()) + " or " + String.valueOf(player.getHandSum()+10));
			}else{
				playerSum.setText(String.valueOf(player.getHandSum()));			
			}			
		}
		playerSum.setVisible(true);
		playerSum.setFont(new Font("Arial", Font.BOLD, 17));
		this.add(playerSum);
		cardLabels.add(playerSum);
		
		//jakajan summa
		JLabel dealerSum = new JLabel();
		if(dealer.hasBlackjack()) {//blackjack
			dealerSum.setText("21");;
		}else {
			if(dealer.getHand().size() == 2) { //eka jako (ekan kortin summa ainoastaan)
				dealerSum.setText(String.valueOf(dealer.getHand().get(0).getRank()));			
				if(dealer.getHand().get(0).getRank() == 1) { //eka kortti on ässä
					dealerSum.setText(String.valueOf(dealer.getHand().get(0).getRank()) + " or " + String.valueOf(dealer.getHand().get(0).getRank()+10));
				}
			}else { //ei ole eka jako
				if(dealer.hasAce() && dealer.getHandSum()+10 <= 21) {
					dealerSum.setText(String.valueOf(dealer.getHandSum()) + " or " + String.valueOf(dealer.getHandSum()+10));				
				}else {
					dealerSum.setText(String.valueOf(dealer.getHandSum()));
				}
			}			
		}
		dealerSum.setVisible(true);
		dealerSum.setFont(new Font("Arial", Font.BOLD, 17));
		this.add(dealerSum);
		cardLabels.add(dealerSum);				
		
		// pelaajan käsi
		JLabel[] labelArrayPlayer = new JLabel[playerHand.size()];

		for (int i = 0; i < labelArrayPlayer.length; i++) {
			labelArrayPlayer[i] = new JLabel();
		}

		for (int i = 0; i < labelArrayPlayer.length; i++) {
			ImageIcon tmpPic = playerHand.get(i).getPicture();

			labelArrayPlayer[i].setIcon(tmpPic);
			labelArrayPlayer[i].setVisible(true);
			this.add(labelArrayPlayer[i]);
			
			cardLabels.add(labelArrayPlayer[i]);
		}

		// jakajan käsi
		JLabel[] labelArrayDealer = new JLabel[dealerHand.size()];
		if (dealerHand.size() == 2) { // onko ensimmäinen jako

			// eka jako (toka kortti ylösalaisin)
			JLabel firstCard = new JLabel();
			JLabel secondCard = new JLabel(); // ylösalaisin

			ImageIcon firstPic = dealerHand.get(0).getPicture();
			ImageIcon secondPic = new ImageIcon(getClass().getResource("/resources/deck/card_face_down.png"));

			firstCard.setIcon(firstPic);
			secondCard.setIcon(secondPic);
			firstCard.setVisible(true);
			secondCard.setVisible(true);
			this.add(firstCard);
			this.add(secondCard);

			labelArrayDealer[0] = firstCard;
			labelArrayDealer[1] = secondCard;
			
			cardLabels.add(labelArrayDealer[0]);
			cardLabels.add(labelArrayDealer[1]);
			
		} else { // muu kuin ensimmäinen jako
			
			for (int i = 0; i < labelArrayDealer.length; i++) {
				labelArrayDealer[i] = new JLabel();
			}

			for (int i = 0; i < labelArrayPlayer.length; i++) {
				ImageIcon tmpPic = dealerHand.get(i).getPicture();

				labelArrayDealer[i].setIcon(tmpPic);
				labelArrayDealer[i].setVisible(true);
				this.add(labelArrayDealer[i]);
				
				cardLabels.add(labelArrayDealer[i]);
			}
		}
		
		//pelaaja blackjack
		JLabel blackjackLabelP = new JLabel("");
		if(player.hasBlackjack()) {			
			blackjackLabelP.setText("BLACKJACK!");
			blackjackLabelP.setVisible(true);
			blackjackLabelP.setFont(new Font("Arial", Font.BOLD, 21));
			this.add(blackjackLabelP);
		}
		cardLabels.add(blackjackLabelP);
		
		//jakaja blackjack
		JLabel blackjackLabelD = new JLabel("");
		JLabel blackjackLabelDcard = new JLabel("");
		if(dealer.hasBlackjack()) {
			//teksti
			blackjackLabelD.setText("BLACKJACK");
			blackjackLabelD.setVisible(true);
			blackjackLabelD.setFont(new Font("Arial", Font.BOLD, 19));
			this.add(blackjackLabelD);
			
			//numero
			blackjackLabelDcard.setIcon(dealer.getHand().get(1).getPicture());
			blackjackLabelDcard.setVisible(true);
			this.add(blackjackLabelDcard);
		}
		cardLabels.add(blackjackLabelD);
		cardLabels.add(blackjackLabelDcard);
		
		//pelaaja meni yli
		JLabel bustLabelP = new JLabel("");
		if(player.getHandSum() > 21) {
			player.setIsBust(true);
			player.setReadyToPlay(true);
			
			bustLabelP.setText("Bust");
			bustLabelP.setVisible(true);
			bustLabelP.setFont(new Font("Arial", Font.BOLD, 19));
			bustLabelP.setForeground(Color.red);;
			this.add(bustLabelP);			
		}
		cardLabels.add(bustLabelP);
		
		//jakaja meni yli
		JLabel bustLabelD = new JLabel("");
		if(dealer.getHandSum() > 21) {
			dealer.setIsBust(true);
			
			bustLabelD.setText("Bust");
			bustLabelD.setVisible(true);
			bustLabelD.setFont(new Font("Arial", Font.BOLD, 19));
			bustLabelD.setForeground(Color.red);;
			this.add(bustLabelD);			
		}
		cardLabels.add(bustLabelD);

		
		this.validate();

		// pelaaja
		int xCoord = 270;
		for (int i = 0; i < labelArrayPlayer.length; i++) {
			labelArrayPlayer[i].setBounds(xCoord, 362, 35, 55);

			xCoord += 35 + 2;
		}
		playerSum.setBounds(332, 330, 200, 20);
		playerSum.setForeground(Color.darkGray);
		if(player.hasAce()) {
			playerSum.setBounds(315, 330, 200, 20);
		}
		if(player.hasBlackjack()) {
			blackjackLabelP.setBounds(280, 290, 200, 50);
			playerSum.setBounds(332, 330, 200, 20);
			playerSum.setForeground(Color.green);
		}
		if(player.isBust()) {
			bustLabelP.setBounds(320, 290, 200, 50);
			playerSum.setForeground(Color.red);
		}

		// jakaja
		xCoord = 270;
		for (int i = 0; i < labelArrayDealer.length; i++) {
			labelArrayDealer[i].setBounds(xCoord, 50, 35, 55);

			xCoord += 35 + 2;
		}
		dealerSum.setBounds(332, 110, 200, 20);
		if(dealer.getHand().get(0).getRank() == 1) {
			dealerSum.setBounds(310, 110, 200, 20);			
		}
		if(dealer.hasBlackjack()) {
			this.remove(labelArrayDealer[1]);
			blackjackLabelD.setBounds(207, 110, 200, 20);
			blackjackLabelDcard.setBounds(307, 50, 35, 55);
			dealerSum.setBounds(332, 110, 200, 20);
		}
		if(dealer.isBust()) {
			bustLabelD.setBounds(280, 109, 200, 20);
			dealerSum.setForeground(Color.red);
		}
		
	}
	
	private class standListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
	
	private class hitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!player.isBust()) {
				player.addToHand(deck.get(0));
				deck.remove(0);
				
				System.out.print("You have: ");
				for(int i = 0; i < player.getHand().size(); i++) {
					if(i == 0) {
						System.out.print(player.getHand().get(i).getRank() + " of " + player.getHand().get(i).getSuit());					
					}else{
						System.out.print(" and " + player.getHand().get(i).getRank() + " of " + player.getHand().get(i).getSuit());										
					}
				}
				System.out.println();
				System.out.println("Your sum is: " + player.getHandSum());
				
				System.out.println();
				
				System.out.print("Dealer has: ");
				for(int i = 0; i < dealer.getHand().size(); i++) {
					if(i == 0) {
						System.out.print(dealer.getHand().get(i).getRank() + " of " + dealer.getHand().get(i).getSuit());					
					}else{
						System.out.print(" and " + dealer.getHand().get(i).getRank() + " of " + dealer.getHand().get(i).getSuit());										
					}
				}
				System.out.println();
				System.out.println("Dealer's sum is: " + dealer.getHandSum());
				
				paintCards(player.getHand(), dealer.getHand());				
			}			
		}		
	}
	
	private class dealListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(player.isReadyToPlay()) {
				game.resetPlayers(player, dealer, deck); //kortit pois pelaajilta
				game.deal(player, dealer, deck);
				System.out.println("New set of cards has been dealt.");

				System.out.println(
						"You have: " + player.getHand().get(0).getRank() + " of " + player.getHand().get(0).getSuit() + 
						" and " + player.getHand().get(1).getRank() + " of " + player.getHand().get(1).getSuit());
				System.out.println("Your sum is: " + player.getHandSum());
				
				System.out.println();

				System.out.println(
						"Dealer has: " + dealer.getHand().get(0).getRank() + " of " + dealer.getHand().get(0).getSuit() + 
						" and " + dealer.getHand().get(1).getRank() + " of " + dealer.getHand().get(1).getSuit());
				System.out.println("Dealer's sum is: " + dealer.getHandSum());
				System.out.println();
				
				paintCards(player.getHand(), dealer.getHand());
				
				player.setReadyToPlay(false);
			}
		}

	}
	
	private class quitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
