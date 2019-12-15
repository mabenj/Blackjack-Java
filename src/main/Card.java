package main;

import javax.swing.ImageIcon;

public class Card {
	private SUITS suit; //maa
	private int rank; //arvo
	private boolean isAce = false; //onko ässä
	private ImageIcon picture; //kuva
	
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
	
	public enum SUITS{
		CLUBS, DIAMONDS, HEARTS, SPADES
	}
	
	public SUITS getSuit() {
		return suit;
	}
	
	public int getRank() {
		return rank;
	}
	
	public ImageIcon getPicture() {
		return picture;
	}
	
	public boolean isAce() {
		return isAce;
	}
}
