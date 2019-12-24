package Card;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;


/**
 * Luokka mallintaa yhtä pelin korttia.
 * 
 * @author mabenj
 *
 */
public class Card {
	private SUITS suit; //maa
	private int rank; //arvo
	private boolean isAce = false; //onko ässä
	private ImageIcon picture; //kuva
	
	/**
	 * Luo instanssin luokasta. Konstruktori määrittää kortille automaattisesti oikean arvon parametrina annetun arvon perusteella.
	 * Esimerkiksi jos parametri on 13 (kuningas), konstruktori määrää kortille oikean arvon 10.
	 * 
	 * Huom! Ässän arvo parametrina on 1, ei 11.
	 * 
	 * @param suit	kortin maa
	 * @param rank	kortin numeerinen arvo 1, 2, ..., 13
	 */
	public Card(SUITS suit, int rank) {
		this.suit = suit;
		
		//ässä
		if(rank == 1) {
			isAce = true;
			this.rank = 1;
		}

		//ei-kuvakortti
		if(rank <= 10 && rank > 1) {
			this.rank = rank;
		}
		
		//kuvakortti
		if(rank > 10) {
			this.rank = 10;
		}
		
		//asetetaan kuvat
		if(suit == SUITS.CLUBS) {
			picture = new ImageIcon(getClass().getResource("/resources/deck/clubs/c_" + rank + ".png"));
		}
		if(suit == SUITS.DIAMONDS) {
			picture = new ImageIcon(getClass().getResource("/resources/deck/diamonds/d_" + rank + ".png"));
		}
		if(suit == SUITS.HEARTS) {
			picture = new ImageIcon(getClass().getResource("/resources/deck/hearts/h_" + rank + ".png"));
		}
		if(suit == SUITS.SPADES) {
			picture = new ImageIcon(getClass().getResource("/resources/deck/spades/s_" + rank + ".png"));
		}
	}
	
	/**
	 * Piirtää tämän kortin kuvan pelipöydälle. Jos kortteja on enemmän kuin 4 kpl, piirretään kortit lomittain.
	 * 
	 * @param g2	Graphics-olio
	 * @param playersTurn	onko kyseessä pelaajan kortti
	 * @param facedown	piiretäänkö kortti väärinpäin
	 * @param cardNum	kortin järjestysnumero kädessä
	 * @param over4Cards	onko kortteja enemmän kuin 4 kpl
	 */
	public void paintCard(Graphics2D g2, boolean playersTurn, boolean facedown, int cardNum, boolean over4Cards) {
		int xPos = over4Cards ? 270 + 20*cardNum: 270 + 37*cardNum;		
		int yPos = playersTurn ? 362 : 50 ;
		
		if(facedown) {
			ImageIcon fdPic = new ImageIcon(getClass().getResource("/resources/deck/card_face_down.png"));
			g2.drawImage(fdPic.getImage(), xPos, yPos, null);
		}else {
			g2.drawImage(picture.getImage(), xPos, yPos, null);
		}
		
		
		
	}
	
	/**
	 * Kortin maat.
	 * CLUBS = Risti
	 * DIAMONDS = Ruutu
	 * HEARTS = Hertta
	 * SPADES = Pata
	 *
	 *
	 */
	public enum SUITS{
		CLUBS, DIAMONDS, HEARTS, SPADES
	}
	
	/**
	 * Palauttaa kortin maan.
	 * 
	 * @return	SUITS kortin maa
	 */
	public SUITS getSuit() {
		return suit;
	}
	
	/**
	 * Palauttaa kortin arvon (blackjack arvo, eli kuningas on 10 jne.)
	 * 
	 * Huom! Ässä on arvoltaan 1.
	 * 
	 * @return	int	kortin blackjack-arvo
	 */
	public int getRank() {
		return rank;
	}
	
	/**
	 * Palauttaa korttiin liitetyn kuvan.
	 * 
	 * @return	ImageIcon	kortin kuva
	 */
	public ImageIcon getPicture() {
		return picture;
	}
	
	/**
	 * Palauttaa tiedon onko kortti ässä.
	 * 
	 * @return	boolean kortti on ässä
	 */
	public boolean isAce() {
		return isAce;
	}
}
