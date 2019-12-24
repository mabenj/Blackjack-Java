package Card;

import java.util.ArrayList;
import java.util.Collections;

import Card.Card.SUITS;

/**
 * Luokka mallintaa peliss‰ k‰ytett‰v‰‰ korttipakkaa. Korttipakka koostuu 52 Card-luokan kortista.
 * 
 * @author mabenj
 *
 */
public class CardDeck {
	ArrayList<Card> cardDeck;
	SUITS[] suitArray = {SUITS.CLUBS, SUITS.DIAMONDS, SUITS.HEARTS, SUITS.SPADES}; //taulukko, jossa maat
	
	
	/**
	 * Luo korttipakka instanssin, jossa Arraylistissa 52 kpl Card-luokan kortteja.
	 * Konstruktori myˆs sekoittaa pakan Collections.shuffle()-metodilla.
	 * 
	 */
	public CardDeck() {
		cardDeck = new ArrayList<Card>();
		populateCardDeck();
		Collections.shuffle(cardDeck); //sekoitetaan pakka
	}
	
	/**
	 * Palauttaa korttipakan ArrayListana, jonka alkiot ovat Card-luokan kortteja.
	 * 
	 * @return	ArrayList<Card>	korttipakka
	 */
	public ArrayList<Card> getDeck(){
		return cardDeck;
	}

	//t‰ytet‰‰n pakka(lista) 52:lla kortilla
	//lista tulee olemaan j‰rjestyksess‰ risti 1, 2, 3..., ruutu 1, 2, 3..., hertta 1, 2, 3..., pata 1, 2, 3...
	
	/**
	 * Metodi t‰ytt‰‰ luokan korttipakan 52:lla Card-luokan kortilla.
	 * 
	 * Lista tulee olemaan j‰rjestyksess‰ risti 1, 2, 3..., ruutu 1, 2, 3..., hertta 1, 2, 3..., pata 1, 2, 3... jne.
	 * 
	 */
	private void populateCardDeck() {
		
		//k‰yd‰‰n maat l‰pi (4)
		for(int i = 0; i < suitArray.length; i++) {
			
			//k‰yd‰‰n arvot l‰pi 1... 13. Ensimm‰inen on aina ‰ss‰
			for(int j = 0; j < 13; j++) {
				cardDeck.add(new Card(suitArray[i], j+1));
			}
		}
		
	}
}
