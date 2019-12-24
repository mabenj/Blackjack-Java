package Card;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;


/**
 * Luokka mallintaa yht� pelin korttia.
 * 
 * @author mabenj
 *
 */
public class Card {
	private SUITS suit; //maa
	private int rank; //arvo
	private boolean isAce = false; //onko �ss�
	private ImageIcon picture; //kuva
	
	/**
	 * Luo instanssin luokasta. Konstruktori m��ritt�� kortille automaattisesti oikean arvon parametrina annetun arvon perusteella.
	 * Esimerkiksi jos parametri on 13 (kuningas), konstruktori m��r�� kortille oikean arvon 10.
	 * 
	 * Huom! �ss�n arvo parametrina on 1, ei 11.
	 * 
	 * @param suit	kortin maa
	 * @param rank	kortin numeerinen arvo 1, 2, ..., 13
	 */
	public Card(SUITS suit, int rank) {
		this.suit = suit;
		
		//�ss�
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
	 * Piirt�� t�m�n kortin kuvan pelip�yd�lle. Jos kortteja on enemm�n kuin 4 kpl, piirret��n kortit lomittain.
	 * 
	 * @param g2	Graphics-olio
	 * @param playersTurn	onko kyseess� pelaajan kortti
	 * @param facedown	piiret��nk� kortti v��rinp�in
	 * @param cardNum	kortin j�rjestysnumero k�dess�
	 * @param over4Cards	onko kortteja enemm�n kuin 4 kpl
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
	 * Huom! �ss� on arvoltaan 1.
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
	 * Palauttaa tiedon onko kortti �ss�.
	 * 
	 * @return	boolean kortti on �ss�
	 */
	public boolean isAce() {
		return isAce;
	}
}
