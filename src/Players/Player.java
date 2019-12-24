package Players;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Card.Card;

/**
 * Luokka mallintaa pelin pelaajaa.
 * 
 * @author mabenj
 *
 */
public class Player {
	private final String nickname;
	
	private boolean hasBlackjack;
	private boolean isBust;
	private boolean readyToPlay;
	private boolean isStanding;
	private boolean win;
	private boolean betPlaced;
	
	private ArrayList<Card> hand;
	private double bet;
	protected double balance;
	private double betWin;
	
	/**
	 * Luo pelaaja-instanssin. Parametrina annettu merkkijono on pelaajan nimi.
	 * Pelaajalle asetetaan pelitilin saldoksi 10 000.
	 * 
	 * @param nickname	pelaajan nimi
	 */
	public Player(String nickname) {
		if(nickname.equals("")) {
			nickname = "Jari Avanto";
		}
		
		hasBlackjack = false;
		isBust = false;
		readyToPlay = true;
		isStanding = false;
		win = false;
		betPlaced = false;
		
		this.nickname = nickname;
		hand = new ArrayList<Card>();
		bet = 0;
		balance = 10000;
		betWin = 0;
		
	}
	
	/**
	 * Palauttaa pelaajan nimen.
	 * 
	 * @return	String	pelaajan nimi
	 */
	public String getName() {
		return nickname;
	}
	
	/**
	 * Palauttaa pelaajan asettaman panoksen.
	 * 
	 * @return	double pelaajan panos
	 */
	public double getBet() {
		return bet;
	}
	
	/**
	 * Palauttaa pelaajan pelitilin saldon.
	 * 
	 * @return	double pelaajan saldo
	 */
	public double getBalance() {
		return balance;
	}
	
	/**
	 * Palauttaa pelaajan voittosumman.
	 * 
	 * @return	double pelaajan voittosumma
	 */
	public double getBetWin() {
		return betWin;
	}
	
	/**
	 * Asettaa pelaajan voittosummaksi parametrina annetun.
	 * 
	 * @param betWin	pelaajan voittosumma
	 */
	public void setBetWin(double betWin) {
		this.betWin = betWin;
	}
	
	/**
	 * Palauttaa tiedon onko pelaaja voittaja.
	 * 
	 * @return	boolean pelaaja on voittaja
	 */
	public boolean isWinner() {
		return win;
	}
	
	/**
	 * Asettaa tiedon onko pelaaja voittaja.
	 * 
	 * @param winner	pelaaja on voittaja
	 */
	public void setWinner(boolean winner) {
		this.win = winner;
	}
	
	/**
	 * Palauttaa tiedon onko pelaajalla ‰ss‰ k‰dess‰‰n.
	 * 
	 * @return	boolean k‰si sis‰lt‰‰ ‰ss‰n
	 */
	public boolean hasAce() {
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i).isAce()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Palauttaa teidon onko pelaajalla blackjack.
	 * 
	 * @return	boolean pelaajalla on blackjack
	 */
	public boolean hasBlackjack() {
		if(hand.size() == 2 && hasAce() && getHandSum()+10 == 21) {
			hasBlackjack = true;
			return true;
		}
		return false;
	}
	
	/**
	 * Palauttaa tiedon onko pelaajan korttien summa yli 21
	 * 
	 * @return	boolean pelaajan summa yli 21
	 */
	public boolean isBust() {
		if(getHandSum() > 21) return true;
		
		return false;
	}
	
	/**
	 * Palauttaa tiedon onko pelaaja valmiina pelaamaan.
	 * 	
	 * @return	boolean pelaaja on valmis pelaamaan
	 */
	public boolean isReadyToPlay() {
		return readyToPlay;
	}
	
	/**
	 * Palauttaa tiedon seisooko pelaaja / onko pelaaja j‰‰nyt
	 * 
	 * @return	boolean pelaaja on j‰‰nyt
	 */
	public boolean isStanding() {
		return isStanding;
	}
	
	/**
	 * Palauttaa teidon onko pelaaja vahvistanut panoksensa.
	 * 
	 * @return	boolean	pelaaja on vahvistanut panoksensa
	 */
	public boolean betPlaced() {
		return betPlaced;
	}
	
	/**
	 * Asettaa teidon onko pelaaja asettanut panoksensa.
	 * 
	 * @param betPlaced	pelaaja on vahvistanut panoksensa
	 */
	public void setBetPlaced(boolean betPlaced) {
		this.betPlaced = betPlaced;
	}
	
	/**
	 * Asettaa teidon seisooko pelaaja / onko pelaaja j‰‰nyt.
	 * 
	 * @param standing	pelaaja on j‰‰nyt
	 */
	public void setIsStanding(boolean standing) {
		this.isStanding = standing;
	}
	
	/**
	 * Asettaa tiedon onko pelaajalla blackjack.
	 * 
	 * @param blackjack	pelaajalla on blackjack
	 */
	public void setHasBlackjack(boolean blackjack) {
		this.hasBlackjack = blackjack;
	}
	
	/**
	 * Asettaa tiedon onko pelaajan korttien summa yli 21.
	 * 
	 * @param bust	pelaajan korttien summa on yli 21
	 */
	public void setIsBust(boolean bust) {
		this.isBust = bust;
	}
	
	/**
	 * Asettaa tiedon onko pelaaja valmis pelaamaan.
	 * 
	 * @param ready	pelaaja on valmis pelaamaan
	 */
	public void setReadyToPlay(boolean ready) {
		this.readyToPlay = ready;
	}
	
	/**
	 * Korottaa pelaajan panosta parametrina annetun luvun verran.
	 * 
	 * @param bet	kuinka paljon pelaajan panosta korotetaan
	 */
	public void raiseBet(double bet) {
		this.bet += bet;
	}
	
	/**
	 * Asettaa pelaajan panokseksi parametrina annetun luvun.
	 * 
	 * @param bet	pelaajan panos
	 */
	public void setBet(double bet) {
		this.bet = bet;
	}
	
	/**
	 * Palauttaa pelaajan k‰dess‰ olevat kortit ArrayListissa.
	 * 
	 * @return	ArrayList<Card>	pelaajan k‰dess‰ olevat kortit
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	/**
	 * Asettaa pelaajan k‰dess‰ olevat kortit parametrina annetun listan mukaisiksi.
	 * 
	 * @param hand	pelaajan kortit
	 */
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	/**
	 * Asettaa pelaajan k‰teen parametrina annetun kortin.
	 * 
	 * @param card	kortti joka lis‰t‰‰n pelaajan k‰teen
	 */
	public void addToHand(Card card){
		hand.add(card);
	}
	
	/**
	 * Palauttaa pelaajan k‰dess‰ olevien korttien summan.
	 * Metodi laskee ‰ss‰n arvolla 1.
	 * 
	 * @return	pelaajan k‰dess‰ olevien korttien summa
	 */
	public int getHandSum() {
		int aceCount = 0;
		int sum = 0;
		
		for(Card card : hand) {
			sum += card.getRank();
			if(card.isAce()) {
				aceCount++;
			}
		}
		
		return sum;
	}
	
	/**
	 * Asettaa pelaajan saldoksi parametrina annetun luvun.
	 * 
	 * @param balance	pelaajan saldo
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/**
	 * Piirt‰‰ pelaajan k‰dess‰ olevat kortit pelipˆyd‰lle.
	 * Jakajan toinen kortti piirret‰‰n selk‰puoli ylˆsp‰in
	 * 
	 * @param g2	Graphics2D-olio
	 * @param playerStanding	pelaaja on j‰‰nyt
	 */
	public void paintHand(Graphics2D g2, boolean playerStanding) {
		boolean playersTurn = (nickname.equals("Dealer")) ? false : true;
		boolean over4Cards = this.getHand().size() > 4 ? true : false;
		
		for(int i = 0; i < hand.size(); i++) {
			if(!playersTurn && hand.size() == 2 && i == 1 && !playerStanding) { //jakajan kortit(eka jako, toinen kortti v‰‰rinp‰in)
				hand.get(i).paintCard(g2, playersTurn, true, i, over4Cards);
			}else {
				hand.get(i).paintCard(g2, playersTurn, false, i, over4Cards);				
			}
		}
	}
	
	/**
	 * Piirt‰‰ pelaajan k‰dess‰ olevat kortit pelipˆyd‰lle.
	 * Jakajan toinen kortti piirret‰‰n parametrista drawSecondCard perusteella
	 * 
	 * @param g2	Graphics2D-olio
	 * @param playerStanding	pelaaja on j‰‰nyt
	 * @param drawSecondCard	jakajan toinen kortti piirret‰‰n oikeinp‰in
	 */
	public void paintHand(Graphics2D g2, boolean playerStanding, boolean drawSecondCard) {
		boolean playersTurn = (nickname.equals("Dealer")) ? false : true;
		boolean over4Cards = this.getHand().size() > 4 ? true : false;
		
		for(int i = 0; i < hand.size(); i++) {
			if(!playersTurn && hand.size() == 2 && i == 1 && !playerStanding && !drawSecondCard) { //jakajan kortit(eka jako (kummallakaan ei BJ), toinen kortti v‰‰rinp‰in)
				hand.get(i).paintCard(g2, playersTurn, true, i, over4Cards);
			}else {
				hand.get(i).paintCard(g2, playersTurn, false, i, over4Cards);				
			}
		}
	}
}
