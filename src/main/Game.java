package main;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
	
	
	public Game() {
				
	}
	
	public void resetPlayers(Player player, Dealer dealer, ArrayList<Card> deck) {	
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
		
		player.setBet(0);
		player.setHasBlackjack(false);
		player.setIsBust(false);
		player.setReadyToPlay(true);
		
		dealer.setHasBlackjack(false);
		dealer.setIsBust(false);
	}
	
	public void deal(Player player, Player dealer, ArrayList<Card> deck) {
		player.addToHand(deck.get(0));
		deck.remove(0);
		
		dealer.addToHand(deck.get(0));
		deck.remove(0);
		
		player.addToHand(deck.get(0));
		deck.remove(0);
		
		dealer.addToHand(deck.get(0));
		deck.remove(0);
	}
	
	
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
}
