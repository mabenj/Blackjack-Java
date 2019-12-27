package main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

import Card.Card;
import Card.CardDeck;
import Players.Dealer;
import Players.Player;
import UI.NicknameQuery;


/**
 * Luokassa on toteutettu suurin osa itse pelin toiminnasta ja sis‰lt‰‰ siihen liittyv‰t metodit.
 * Luokka sis‰lt‰‰ pelin pelaajan, jakajan, listan k‰ytett‰vist‰ korteista (oletuksena 3 pakkaa eli 3*52 korttia) ja tiedon k‰ytett‰v‰st‰ kielest‰.
 * 
 * @author mabenj
 *
 */
public class Game {
	private Player player;
	private Dealer dealer;
	private ArrayList<Card> deck;
	private LANG language;
	
	private boolean easterEgg;
	private int egX, egY;
	
	/**
	 * Luo Game-instanssin.
	 * 
	 * @param numDecks	k‰ytett‰vien pakkojen lukum‰‰r‰
	 */
	public Game(int numDecks) {
		language = LANG.ENG;
		
		NicknameQuery q = new NicknameQuery(getIcons());
		player = new Player(q.getName());
		dealer = new Dealer();
		
		deck = populateDeckList(numDecks);
		
		easterEgg = false;
		egX = 220;
		egY = 180;
	}
	
	/**
	 * K‰ytett‰viss‰ olevat kielet.
	 * 
	 *
	 */
	public enum LANG{
		FIN, ENG
	};
	
	/**
	 * Palauttaa k‰ytˆss‰ olevan kielen.
	 * 
	 * @return	LANG	k‰ytˆss‰ oleva kieli
	 */
	public LANG getLang() {
		return language;
	}
	
	/**
	 * Asettaa pelin kieleksi parametrina annetun.
	 * 
	 * @param language	asetettava kieli
	 */
	public void setLang(LANG language) {
		this.language = language;
	}
	
	/**
	 * Palauttaa pelin pelaajan.
	 * 
	 * @return	Player	pelin pelaaja
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Palauttaa pelin jakajan.
	 * 
	 * @return	Dealer	pelin jakaja
	 */
	public Dealer getDealer() {
		return dealer;
	}
	
	/**
	 * Palauttaa tiedon onko "Easter Egg" aktivoitu.
	 * 
	 * @return	boolean	easter egg on aktivoitu
	 */
	public boolean getEasterEgg() {
		return easterEgg;
	}
	
	/**
	 * Palauttaa "Easter Eggin" x-koordinaatin.
	 * 
	 * @return	int	Easter Eggin x-koordinaatti
	 */
	public int getEgX() {
		return egX;
	}
	
	/**
	 * Asettaa "Easter Eggin" x-koordinaatin.
	 * 
	 * @param x	Easter Eggin x-koordinaatti
	 */
	public void setEgX(int x) {
		this.egX = x;
	}
	
	/**
	 * Palauttaa "Easter Eggin" y-koordinaatin.
	 * 
	 * @return	int	Easter Eggin y-koordinaatti
	 */
	public int getEgY() {
		return egY;
	}
	
	/**
	 * Asettaa "Easter Eggin" y-koordinaatin.
	 * 
	 * @param y	Easter Eggin y-koordinaatti
	 */
	public void setEgY(int y) {
		this.egY = y;
	}
	
	/**
	 * Asettaa tiedon onko "Easter Egg" aktivoitu.
	 * 	
	 * @param easterEgg	onko easter egg aktivoitu
	 */
	public void setEasterEgg(boolean easterEgg){
		this.easterEgg = easterEgg;
	}
	
	/**
	 * Nollaa pelin pelaajat (pelaajan ja jakajan).
	 * 
	 * Metodi lis‰‰ pelaajien kortit pelin pakkaan, tyhjent‰‰ pelaajien k‰det ja sekoittaa pakan.
	 * Metodi palauttaa myˆs pelaajien attribuutit oletusarvoisiksi, lukuunottamatta panosta ja voittosummaa.
	 * 
	 */
	public void resetPlayers() {	
		//lis‰t‰‰n pelaajan kortit pakkaan
		for(Card card : player.getHand()) {
			deck.add(card);
		}
		
		//lis‰t‰‰n jakajan kortit pakkaan
		for(Card card : dealer.getHand()) {
			deck.add(card);
		}
		
		//tyhjennet‰‰n pelaajan k‰si
		player.setHand(new ArrayList<Card>());
		//tyhjennet‰‰n jakajan k‰si
		dealer.setHand(new ArrayList<Card>());
		
		Collections.shuffle(deck);
		
		//player.setBet(0);
		player.setHasBlackjack(false);
		player.setIsBust(false);
		player.setReadyToPlay(true);
		player.setIsStanding(false);
		player.setWinner(false);
		//player.setBetPlaced(false);
		player.setBetWin(0);
		
		dealer.setHasBlackjack(false);
		dealer.setIsBust(false);
		dealer.setIsStanding(false);
		dealer.setWinner(false);
	}
	
	/**
	 * Metodi tekee jakajan liikkeen.
	 * Jos jakajan summa on yli 16, jakaja j‰‰. Muussa tapauksessa jakajan k‰teen lis‰t‰‰n kortti.
	 * 
	 */
	public void dealerMove() {
		
		int sum = dealer.getHandSum();
		
		if(dealer.hasAce() && dealer.getHandSum()+10 <=21) {
			sum += 10;
		}
		
		if(sum > 16) {
			dealer.setIsStanding(true);
		}else {
			addToDealerHand();
		}
	}
	
	/**
	 * Lis‰‰ kortin pelaajan k‰teen, jonka j‰lkeen kyseinen kortti poistetaan pakasta.
	 * 
	 */
	public void addToPlayerHand() {
		player.addToHand(deck.get(0));
		deck.remove(0);
	}
	
	/**
	 * Lis‰‰ kortin jakajan k‰teen, jonka j‰lkeen kyseinen kortti poistetaan pakasta.
	 * 
	 */
	public void addToDealerHand() {
		dealer.addToHand(deck.get(0));
		deck.remove(0);
	}
	
	/**
	 * K‰ytet‰‰n alustavaan jakoon.
	 * Jos pelaajalla on alle 2 korttia, lis‰t‰‰n kortti sek‰ pelaajan ett‰ jakajan k‰teen.
	 * 
	 */
	public void dealOne() {
		
		if(player.getHand().size() < 2) {
			addToDealerHand();
			
			addToPlayerHand();
		}
	}
	
	/**
	 * Lis‰‰ sek‰ pelaajalle ett‰ jakajalle kaksi korttia ja poistaa n‰m‰ kortit pakasta.
	 * Metodi tulostaa annetut kortit.
	 * 
	 */
	public void deal() {
		player.addToHand(deck.get(0));
		deck.remove(0);
		
		dealer.addToHand(deck.get(0));
		deck.remove(0);
		
		player.addToHand(deck.get(0));
		deck.remove(0);
		
		dealer.addToHand(deck.get(0));
		deck.remove(0);
		
		System.out.println("New cards have been dealt.\n");
		
		System.out.println("You have: " + player.getHand().get(0).getRank() + " of " + player.getHand().get(0).getSuit()
				+ " and " + player.getHand().get(1).getRank() + " of " + player.getHand().get(1).getSuit());
		System.out.println("Your sum is: " + player.getHandSum());
		
		System.out.println("The dealer has: " + dealer.getHand().get(0).getRank() + " of " + dealer.getHand().get(0).getSuit()
				+ " and " + dealer.getHand().get(1).getRank() + " of " + dealer.getHand().get(1).getSuit());
		System.out.println("The dealer's sum is: " + dealer.getHandSum() + "\n");
	}
	
	/**
	 * Piirt‰‰ pelaajien kortit pˆyd‰lle.
	 * 
	 * @param g2	Graphics2D-olio
	 * @param playerStanding	onko pelaaja j‰‰nyt
	 */
	public void paintCards(Graphics2D g2, boolean playerStanding) {
		boolean drawSecondCard = (player.hasBlackjack() || dealer.hasBlackjack() || player.isBust()) ? true : false;
		
		player.paintHand(g2, playerStanding);
		dealer.paintHand(g2, playerStanding, drawSecondCard);
	}
	
	/**
	 * T‰ytt‰‰ pelin korttilistan parametrina annetun korttipakkojen lukum‰‰r‰ll‰.
	 * 
	 * @param numDecks	korttipakkojen lukum‰‰r‰
	 * @return	ArrayList<Card>	lista, jossa on pyydetty m‰‰r‰ kortteja
	 */
	public ArrayList<Card> populateDeckList(int numDecks) {
		ArrayList<Card> decks = new ArrayList<Card>();
		
		// luodaan NUM_DECKS:in verran pakkoja ja laitetaan ne listaan
		for (int i = 0; i < numDecks; i++) {
			CardDeck cards = new CardDeck();
			for (int j = 0; j < cards.getDeck().size(); j++) {
				decks.add(cards.getDeck().get(j));
			}
		}
		return decks;
	}
	
	/**
	 * Kutsutaan kun pelaaja voittaa.
	 * Metodi laskee pelaajalle oikean voittosumman ja lis‰‰ sen pelaajan tilille.
	 * Metodi nollaa pelaajan panoksen, asettaa pelaajan betPlaced-attribuutin ep‰todeksi ja asettaa pelaajan valmiiksi pelaamaan.
	 * 
	 */
	public void playerWin() {
		double bet = player.getBet();
		double winAmount = player.hasBlackjack() ? bet + bet*1.5 : bet*2;
		
		double oldBalance = player.getBalance();
		double newBalance = oldBalance + winAmount;
		
		player.setBalance(newBalance);
		player.setBetWin(winAmount);
		
		player.setBet(0);
		player.setBetPlaced(false);
		
		player.setReadyToPlay(true);
	}
	
	/**
	 * Kutsutaan kun jakaja voittaa.
	 * Metodi nollaa pelaajan panoksen, asettaa pelaajan betPlaced-attribuutin ep‰todeksi ja asettaa pelaajan valmiiksi pelaamaan.
	 * 
	 */
	public void dealerWin() {
		player.setBet(0);
		player.setBetPlaced(false);
		
		player.setReadyToPlay(true);
	}
	
	/**
	 * Kutsutaan kun p‰‰dyt‰‰n tasatilanteeseen.
	 * Metodi palauttaa pelaajalle panoksen, nollaa pelaajan panoksen, asettaa pelaajan betPlaced-attribuutin ep‰todeksi ja asettaa pelaajan valmiiksi pelaamaan. 
	 * 
	 */
	public void push() {
		double winAmount = player.getBet();
		double oldBalance = player.getBalance();
		double newBalance = oldBalance + winAmount;
		
		player.setBalance(newBalance);
		player.setBetWin(winAmount);
		
		player.setBet(0);
		player.setBetPlaced(false);
		
		player.setReadyToPlay(true);
	}
	
	/**
	 * Selvitt‰‰ kuka voitti pelin.
	 * 
	 */
	public void specifyWinner() {
		int playerSum;
		int dealerSum;
		
		//pelaajan summa
		if(player.hasAce() && player.getHandSum()+10 <= 21) {
			playerSum = player.getHandSum()+10;
		}else {
			playerSum = player.getHandSum();
		}
		
		//jakajan summa
		if(dealer.hasAce() && dealer.getHandSum()+10 <= 21) {
			dealerSum = dealer.getHandSum()+10;
		}else {
			dealerSum = dealer.getHandSum();
		}
		
		
		if(player.isBust()) dealer.setWinner(true);//pelaaja yli >> jakaja voittaa
		
		if(!player.isBust()) {
			if(dealer.isBust()) {//jakaja yli >> pelaaja voittaa
				player.setWinner(true);
			}else {
				if(playerSum > dealerSum) {//pelaaja isompi >> pelaaja voittaa
					player.setWinner(true);
				}else {
					if(playerSum < dealerSum) {//pelaaja pienempi >> jakaja voittaa
						dealer.setWinner(true);
					}
				} //muu tapaus (kumpikaan ei voittaja) == tasapeli
			}
		}
	}	

	/**
	 * Palauttaa listan pelin JFramen pikkukuvista.
	 * 
	 * @return	List<Image>	pelin JFramen pikkukuvat
	 */
	public List<Image> getIcons() {
		ImageIcon icon64 = new ImageIcon(getClass().getResource("/resources/frame_icon64.png"));
		ImageIcon icon128 = new ImageIcon(getClass().getResource("/resources/frame_icon128.png"));
		
		List<Image> icons = new ArrayList<Image>();
		icons.add(icon64.getImage());
		icons.add(icon128.getImage());
		
		return icons;
	}
}
