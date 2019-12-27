package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.Game;

/**
 * Luokassa on toteutettu pelin graafinen k‰yttˆliittym‰. Luokassa on myˆs toteutettu osa itse pelin logiikasta paintComponent-metodissa.
 * 
 * @author mabenj
 *
 */
public class BlackjackPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private String current_bet, balance, blackjack, or, bust, push, wins, dealer_wins, bet_win, set_bet_b, clear_bet_b, deal_b, hit_b, stand_b, quit_b;
	
	private JButton deal, hit, stand, setBet, clearBet, bet50, bet100, bet500, bet1000, bet2000, bet5000, fi, eng, quit;	
	
	private ImageIcon activeTable, tableEng, tableFin, engFlag, fiFlag, chips1, chips2, chips3, chips4, chips5, jliha;
	
	private int width, height;
	
	private Color buttonBg;
	
	private Game game;
	private BlackjackFrame frame;
	
	/**
	 * Luo luokasta instanssin.
	 * 
	 * @param width	pelin ikkunan leveys
	 * @param height	pelin ikkunan korkeus
	 * @param game	pelin Game-instanssi
	 * @param frame	pelin ikkuna
	 */
	public BlackjackPanel(int width, int height, Game game, BlackjackFrame frame) {
		this.width = width;
		this.height = height;
		this.game = game;
		this.frame = frame;
		this.buttonBg = new Color(28,28,28);
		
		tableFin = new ImageIcon(getClass().getResource("/resources/table/table_fin.png"));
		tableEng = new ImageIcon(getClass().getResource("/resources/table/table_eng.png"));
		fiFlag = new ImageIcon(getClass().getResource("/resources/flags/fi.png"));
		engFlag = new ImageIcon(getClass().getResource("/resources/flags/eng.png"));
		chips1 = new ImageIcon(getClass().getResource("/resources/table/chips/chips_1.png"));
		chips2 = new ImageIcon(getClass().getResource("/resources/table/chips/chips_2.png"));
		chips3 = new ImageIcon(getClass().getResource("/resources/table/chips/chips_3.png"));
		chips4 = new ImageIcon(getClass().getResource("/resources/table/chips/chips_4.png"));
		chips5 = new ImageIcon(getClass().getResource("/resources/table/chips/chips_5.png"));
		jliha = new ImageIcon(getClass().getResource("/resources/table/jliha.png"));
		
		this.setLayout(new BorderLayout());
		this.add(getBottom(), BorderLayout.SOUTH);
	}
	
	/**
	 * Palauttaa JPanelin, joka mallintaa ikkunan alaosaa.
	 * 
	 * @return	JComponent	JPaneli, joka mallintaa ikkunan alaosaa
	 */
	private JComponent getBottom() {
		JPanel bottom = new JPanel();
		bottom.setFocusable(false);
		bottom.setOpaque(false);
		bottom.setLayout(new BorderLayout());
		
		bottom.add(getButtons(), BorderLayout.CENTER);
		bottom.add(getBottomBar(), BorderLayout.PAGE_END);
		
		return bottom;
	}
	
	/**
	 * Palauttaa JPanelin, joka mallintaa ikkunan alapalkkia.
	 * 	
	 * @return	JComponent	JPanel, joka mallintaa ikkunan alapalkkia
	 */
	private JComponent getBottomBar() {
		JPanel bottomBar = new JPanel();
		bottomBar.setFocusable(false);
		bottomBar.setOpaque(false);
		bottomBar.setPreferredSize(new Dimension(702, 20));
		
		return bottomBar;
	}
	
	/**
	 * Palauttaa JPanelin, jossa on pelin nappulat (JButton)
	 * 
	 * @return	JComponent JPanel, jossa pelin nappulat
	 */
	private JComponent getButtons() {
		JPanel bottom = new JPanel();
		bottom.setFocusable(false);
		bottom.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		bottom.setOpaque(false);
		bottom.setLayout(new GridLayout(1, 4, 10, 10));
		
		bottom.add(getBettingButtons());
		bottom.add(getSetClearBetButtons());
		bottom.add(getDealHitStandButtons());
		bottom.add(getQuitAndFlagsButton());
		
		
		return bottom;
	}
	
	/**
	 * Palauttaa JPanelin, jossa on pelin panoksen-korotus-napit.
	 * 
	 * @return	JComponent	JPanel, jossa on pelin panoksen-korotus-napit
	 */
	private JComponent getBettingButtons() {
		JPanel panel = new JPanel();
		//panel.setBorder(BorderFactory.createEmptyBorder(0, 3, 3, 0));
		panel.setFocusable(false);
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(3, 2, 3, 3));
		
		bet50 = new JButton("$50");
		bet50.addActionListener(new RaiseBetListener(50));
		bet50.setBackground(buttonBg);
		bet50.setForeground(Color.orange);
		bet50.setFocusable(false);
		
		bet100 = new JButton("$100");
		bet100.addActionListener(new RaiseBetListener(100));
		bet100.setBackground(buttonBg);
		bet100.setForeground(Color.orange);
		bet100.setFocusable(false);
		
		bet500 = new JButton("$500");
		bet500.addActionListener(new RaiseBetListener(500));
		bet500.setBackground(buttonBg);
		bet500.setForeground(Color.orange);
		bet500.setFocusable(false);
		
		bet1000 = new JButton("$1000");
		bet1000.addActionListener(new RaiseBetListener(1000));
		bet1000.setBackground(buttonBg);
		bet1000.setForeground(Color.orange);
		bet1000.setFocusable(false);
		
		bet2000 = new JButton("$2000");
		bet2000.addActionListener(new RaiseBetListener(2000));
		bet2000.setBackground(buttonBg);
		bet2000.setForeground(Color.orange);
		bet2000.setFocusable(false);
		
		bet5000 = new JButton("$5000");
		bet5000.addActionListener(new RaiseBetListener(5000));
		bet5000.setBackground(buttonBg);
		bet5000.setForeground(Color.orange);
		bet5000.setFocusable(false);
		
		panel.add(bet50);
		panel.add(bet100);
		panel.add(bet500);
		panel.add(bet1000);
		panel.add(bet2000);
		panel.add(bet5000);
		
		return panel;
	}
	
	/**
	 * Palauttaa JPanelin, jossa on pelin "Aseta panos" ja "Tyhjenn‰ panos" -napit.
	 * 
	 * @return	JComponent JPanel, jossa on pelin "Aseta panos" ja "Tyhjenn‰ panos" -napit
	 */
	private JComponent getSetClearBetButtons() {
		JPanel panel = new JPanel();
		panel.setFocusable(false);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40));
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(2, 1, 0, 3));;
		
		setBet = new JButton("Confirm Bet");
		setBet.addActionListener(new SetBetListener());
		setBet.setBackground(buttonBg);
		setBet.setForeground(Color.orange);
		setBet.setFocusable(false);
		
		clearBet = new JButton("Clear Bet");
		clearBet.addActionListener(new ClearBetListener());
		clearBet.setBackground(buttonBg);
		clearBet.setForeground(Color.orange);
		clearBet.setFocusable(false);
		
		panel.add(setBet);
		panel.add(clearBet);
		
		return panel;
	}
	
	/**
	 * Palauttaa JPanelin, jossa on pelin "Jaa", "Anna" ja "J‰‰" -napit.
	 * 
	 * @return	JComponent JPanel, jossa on pelin "Jaa", "Anna" ja "J‰‰" -napit
	 */
	private JComponent getDealHitStandButtons() {
		JPanel panel = new JPanel();
		panel.setFocusable(false);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 70));
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(3, 1, 3, 3));
		
		deal = new JButton("Deal");
		deal.addActionListener(new DealListener());
		deal.setBackground(buttonBg);
		deal.setForeground(Color.orange);
		deal.setFocusable(false);
		
		hit = new JButton("Hit");
		hit.addActionListener(new HitListener());
		hit.setBackground(buttonBg);
		hit.setForeground(Color.orange);
		hit.setFocusable(false);
		
		stand = new JButton("Stand");
		stand.addActionListener(new StandListener());
		stand.setBackground(buttonBg);
		stand.setForeground(Color.orange);
		stand.setFocusable(false);
		
		panel.add(deal);
		panel.add(hit);
		panel.add(stand);
		
		return panel;
	}
	
	/**
	 * Palauttaa JPanelin, jossa on pelin poistu- ja kielinapit.
	 * 
	 * @return	JComponent JPanel, jossa on pelin poistu- ja kielinapit
	 */
	private JComponent getQuitAndFlagsButton() {
		JPanel panel = new JPanel();
		panel.setFocusable(false);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(3,2,3,3));
		
		quit = new JButton("Quit");
		quit.addActionListener(new QuitListener());
		quit.setBackground(buttonBg);
		quit.setForeground(Color.orange);
		quit.setFocusable(false);
		
		fi = new JButton(fiFlag);
		fi.setPreferredSize(new Dimension(57, 26));
		fi.addActionListener(new FiListener());
		fi.setBackground(Color.white);
		fi.setFocusable(false);
		
		eng = new JButton(engFlag);
		eng.setPreferredSize(new Dimension(57, 26));
		eng.addActionListener(new EngListener());
		eng.setBackground(Color.white);
		eng.setFocusable(false);
		
		panel.add(new JLabel(""));
		panel.add(eng);
		panel.add(new JLabel(""));
		panel.add(fi);
		panel.add(new JLabel(""));
		panel.add(quit);
		
		return panel;
	}
	
	/**
	 * Asettaa pelin tekstit englanniksi
	 * 
	 */
	private void initEng() {
		activeTable = tableEng;
		
		current_bet = "CURRENT BET: ";
		balance = "BALANCE: ";
		blackjack = "BLACKJACK";
		or = " or ";
		bust = "BUST";
		push = "     PUSH";
		wins = " wins!";
		dealer_wins = "The Dealer wins";
		bet_win = "BET WIN: ";
		
		//jbuttons
		set_bet_b = "Confirm Bet";
		clear_bet_b = "Clear Bet";
		deal_b = "Deal";
		hit_b = "Hit";
		stand_b = "Stand";
		quit_b = "Quit";
	}
	
	/**
	 * Asettaa pelin tekstit suomeksi
	 * 
	 */
	private void initFin() {
		activeTable = tableFin;
		
		current_bet = "    PANOS: ";
		balance = "PELITILI: ";
		blackjack = "BLACKJACK";
		or = " tai ";
		bust = "  YLI";
		push = "TASATULOS";
		wins = " voitti!";
		dealer_wins = "      Jakaja voitti";
		bet_win = "VOITTO: ";
		
		//jbuttons
		set_bet_b = "Vahvista panos";
		clear_bet_b = "Tyhjenn‰ panos";
		deal_b = "Jaa";
		hit_b = "Anna";
		stand_b = "J‰‰";
		quit_b = "Poistu";
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		//kieli
		if(game.getLang() == Game.LANG.ENG) {
			initEng();
		}else {
			initFin();
		}
		
		//tausta
		g2.drawImage(activeTable.getImage(), 0, 0, 696, 564, null);
		g2.setColor(Color.black);
		g2.fillRect(0, 564, 696, 20);
		
		//pelimerkit
		if(game.getPlayer().getBet() >= 6000) {
			g2.drawImage(chips5.getImage(), 220, 290, 85, 65, null);			
		}else if(game.getPlayer().getBet() >= 4000){
			g2.drawImage(chips4.getImage(), 220, 290, 85, 65, null);			
		}else if(game.getPlayer().getBet() >= 1000) {
			g2.drawImage(chips3.getImage(), 220, 290, 85, 65, null);			
		}else if(game.getPlayer().getBet() >= 200) {
			g2.drawImage(chips2.getImage(), 220, 290, 85, 65, null);			
		}else if(game.getPlayer().getBet() > 0) {
			g2.drawImage(chips1.getImage(), 220, 290, 85, 65, null);			
		}
				
		//current bet
		if(!game.getPlayer().betPlaced()) {
			g2.setColor(Color.orange);
		}else {
			g2.setColor(Color.black);
		}
		g2.setFont(new Font("Arial", Font.BOLD, 13));
		g2.drawString(current_bet + "$" + (int) game.getPlayer().getBet(), 250, 445);
		
		g2.setColor(Color.black);
		g2.drawString(current_bet, 250, 445);
		
		//player name
		g2.setColor(Color.orange);
		g2.setFont(new Font("Arial", Font.BOLD, 13));
		
		FontMetrics fm = g2.getFontMetrics();
		int xPos = 696 - 5 - fm.stringWidth(game.getPlayer().getName());
		int yPos = 580;
		
		g2.drawString(game.getPlayer().getName(), xPos, yPos);
		
		//PLAYER's balance
		g2.setColor(Color.white);
		g2.setFont(new Font("Arial", Font.BOLD, 15));
		g2.drawString(balance + "$" + game.getPlayer().getBalance(), 5, 580);
		g2.setColor(Color.orange);
		g2.drawString(balance, 5, 580);
		
		//kortit
		game.paintCards(g2, game.getPlayer().isStanding());
		
		//spagettia t‰st‰ alasp‰in
		
		//pelaajan summa
		if(game.getPlayer().getHandSum() > 0) {
			if(game.getPlayer().hasBlackjack()) { //pelaaja blackjack
				g2.setColor(Color.green);
				g2.drawString("21", 340, 340);
				g2.setColor(Color.black);
				g2.drawString(blackjack, 310, 320);
				
				game.getPlayer().setIsStanding(true);
				game.getDealer().setIsStanding(true);			
			}else {
				if(game.getPlayer().hasAce() && game.getPlayer().getHandSum()+10 <= 21) {
					g2.setColor(Color.black);
					g2.drawString(String.valueOf(game.getPlayer().getHandSum()) + or + 
							String.valueOf(game.getPlayer().getHandSum()+10), 330, 340);
				}else {
					if(game.getPlayer().getHandSum() > 21) { //pelaaja yli
						g2.setColor(Color.black);
						g2.drawString(bust, 325, 320);
						
						g2.setColor(Color.red);
						g2.drawString(String.valueOf(game.getPlayer().getHandSum()), 340, 340);
						
						
						game.getDealer().setIsStanding(true);
						game.getPlayer().setIsStanding(true);
						//game.getPlayer().setReadyToPlay(true);
					}else {
						g2.setColor(Color.black);
						g2.drawString(String.valueOf(game.getPlayer().getHandSum()), 340, 340);						
					}
				}
			}
		}
		
		//jakajan summa
		if(game.getDealer().getHand().size() == 2 && !game.getPlayer().hasBlackjack()) { //eka jako
			if(game.getDealer().hasBlackjack()) {//jakaja blackjack
				g2.setColor(Color.black);
				g2.drawString("21", 340, 125);
				g2.drawString(blackjack, 240, 125);
				
				game.getDealer().setIsStanding(true);
				game.getPlayer().setIsStanding(true);
			}else {				
				if(game.getDealer().getHand().get(0).isAce()) {//eka on ‰ss‰
					g2.setColor(Color.black);
					g2.drawString(String.valueOf(game.getDealer().getHand().get(0).getRank()) + or + 
							String.valueOf(game.getDealer().getHand().get(0).getRank()+10), 320, 125);
				}else {//eka ei ole ‰ss‰
					if(game.getPlayer().isStanding()) {//pelaaja seisoo >> jakajan vuoro
						if(game.getDealer().getHand().get(1).isAce()) {//jakajan toka on ‰ss‰
							g2.setColor(Color.black);
							g2.drawString(String.valueOf(game.getDealer().getHandSum()) + or + 
									String.valueOf(game.getDealer().getHandSum()+10), 320, 125);						
						}else {
							g2.setColor(Color.black);
							g2.drawString(String.valueOf(game.getDealer().getHandSum()), 340, 125);
						}
					}else {
						g2.setColor(Color.black);
						g2.drawString(String.valueOf(game.getDealer().getHand().get(0).getRank()), 340, 125);					
					}
				}
			}
		}else {//muu kuin eka jako
			if (game.getDealer().getHandSum() > 0) {
				if (game.getDealer().hasAce() && game.getDealer().getHandSum() + 10 <= 21) {
					g2.setColor(Color.black);
					g2.drawString(String.valueOf(game.getDealer().getHandSum()) + or
							+ String.valueOf(game.getDealer().getHandSum() + 10), 320, 125);
				} else {
					if (game.getDealer().getHandSum() > 21) {// jakaja yli
						g2.setColor(Color.black);
						g2.drawString(bust, 290, 125);

						g2.setColor(Color.red);
						g2.drawString(String.valueOf(game.getDealer().getHandSum()), 340, 125);

						game.getDealer().setIsStanding(true);
						game.getPlayer().setIsStanding(true);
					} else {
						g2.setColor(Color.black);
						g2.drawString(String.valueOf(game.getDealer().getHandSum()), 340, 125);

					}
				}
			}
		}
		
		//selvitet‰‰n voittaja ja piirret‰‰n voittopotti
		if(game.getPlayer().isStanding() && game.getDealer().isStanding()) { // molemmat "seisoo/valmiita"
			
			game.specifyWinner();
			
			if(!game.getPlayer().isWinner() && !game.getDealer().isWinner()) {//tasapeli
				g2.setColor(Color.black);
				g2.drawString(push, 300, 230);
				
				game.push();
				
				g2.setColor(Color.green);
				g2.drawString(bet_win + "$" + game.getPlayer().getBetWin(), 300, 580);
				
				g2.setColor(Color.orange);
				g2.drawString(bet_win, 300, 580);
			}
			
			if(game.getPlayer().isWinner()) {//pelaaja voitti
				g2.setColor(Color.black);
				g2.drawString(game.getPlayer().getName() + wins, 280, 230);
				
				game.playerWin();
				
				g2.setColor(Color.green);
				g2.drawString(bet_win + "$" + game.getPlayer().getBetWin(), 300, 580);
				
				g2.setColor(Color.orange);
				g2.drawString(bet_win, 300, 580);
			}
			
			if(game.getDealer().isWinner()) {//jakaja voitti
				g2.setColor(Color.black);
				g2.drawString(dealer_wins, 280, 230);
				
				game.dealerWin();
				g2.setColor(Color.white);
				g2.drawString(bet_win + "$" + game.getPlayer().getBetWin(), 300, 580);
				g2.setColor(Color.orange);
				g2.drawString(bet_win, 300, 580);
			}
		}else {
			g2.setColor(Color.white);
			g2.drawString(bet_win + "$" + game.getPlayer().getBetWin(), 300, 580);
			g2.setColor(Color.orange);
			g2.drawString(bet_win, 300, 580);
		}
		
		// easter egg
		if (game.getEasterEgg()) {
			g2.drawImage(jliha.getImage(), game.getEgX(), game.getEgY(), 250, 200, null);
		}
		
	}	
	
	private class HitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!game.getPlayer().isReadyToPlay() && !game.getPlayer().isStanding()) {
				
				game.addToPlayerHand();
				
				repaint();
			}
		}
		
	}
	
	private class StandListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!game.getPlayer().isReadyToPlay() && !game.getPlayer().isStanding()) {
				game.getPlayer().setIsStanding(true);
				
				try {
					Thread.sleep(1000);
					repaint();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				Timer timer = new Timer();
				TimerTask tt = new TimerTask() {
					int count = 0;
					
					@Override
					public void run() {
						int dealerSum = (game.getDealer().hasAce() && game.getDealer().getHandSum()+10 <= 21) ? game.getDealer().getHandSum()+10 : game.getDealer().getHandSum();
						
						if(dealerSum > 16) {
							timer.cancel();
							game.getDealer().setIsStanding(true);
							if(dealerSum <= 21) {
								repaint();						
							}
						}else {
							count++;
							game.dealerMove();							
							repaint();						
						}
					}					
				};
				
				timer.schedule(tt, 1000, 1000);
			}			
		}		
	}
	
	private class DealListener implements ActionListener{
		boolean dealingReady = true;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(game.getPlayer().isReadyToPlay() && game.getPlayer().betPlaced() && dealingReady) {
				dealingReady = false;
				
				double oldBalance = game.getPlayer().getBalance();
				double betAmount = game.getPlayer().getBet();
				double newBalance = oldBalance - betAmount;
				game.getPlayer().setBalance(newBalance);
				
				game.resetPlayers(); // kortit pois pelaajilta
				
				Timer timer = new Timer();
				TimerTask tt = new TimerTask() {
					int count = 0;
					
					@Override
					public void run() {
						
						if(game.getPlayer().getHand().size() == 2) {
							timer.cancel();
							game.getPlayer().setReadyToPlay(false);
							dealingReady = true;
						}else {
							game.dealOne();
							repaint();
						}					
					}					
				};
				
				timer.schedule(tt, 200, 500);
				
				
			}
		}
		
	}
	
	private class SetBetListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(game.getPlayer().isReadyToPlay() && game.getPlayer().getBet() > 0) {
				
				game.getPlayer().setBetPlaced(true);
				
				repaint();
			}
			
			
		}
		
	}
	
	private class ClearBetListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(game.getPlayer().isReadyToPlay()) {
				game.getPlayer().setBet(0);
				game.getPlayer().setBetPlaced(false);
				repaint();
			}
			
		}
		
	}
	
	private class RaiseBetListener implements ActionListener{
		int amount;
		
		public RaiseBetListener(int amount) {
			this.amount = amount;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
			
			if(game.getPlayer().isReadyToPlay() && !game.getPlayer().betPlaced()){
				game.resetPlayers();
				
				game.getPlayer().setIsStanding(false);
				game.getDealer().setIsStanding(false);
				
				if(game.getPlayer().getBalance() - game.getPlayer().getBet() - amount >= 0) {
					game.getPlayer().raiseBet(amount);		
					repaint();
				}
			}
		}
		
	}
	
	private class EngListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(game.getLang() == Game.LANG.FIN) {		
				game.setLang(Game.LANG.ENG);
				
				initEng();
				
				deal.setText(deal_b);
				hit.setText(hit_b);
				stand.setText(stand_b);
				
				setBet.setText(set_bet_b);
				clearBet.setText(clear_bet_b);
				
				quit.setText(quit_b);
				quit.setFont(new Font("Arial", Font.BOLD, 12));
				
				repaint();
			}
		}
		
	}
	
	private class FiListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(game.getLang() == Game.LANG.ENG) {		
				game.setLang(Game.LANG.FIN);
				
				initFin();
				
				deal.setText(deal_b);
				hit.setText(hit_b);
				stand.setText(stand_b);
				
				setBet.setText(set_bet_b);
				clearBet.setText(clear_bet_b);
				
				quit.setText(quit_b);
				quit.setFont(new Font("Arial", Font.BOLD, 9));
				
				repaint();
			}
		}
		
	}
	
	private class QuitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			
		}
		
	}
}
