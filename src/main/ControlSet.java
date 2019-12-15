package main;

import java.util.ArrayList;

public class ControlSet {
	Dealer dealer;
	Player player;
	ArrayList<Card> deck;
	
	public ControlSet(Dealer dealer, Player player, ArrayList<Card> deck) {
		this.dealer = dealer;
		this.player = player;
		this.deck = deck;
	}
	
	public Dealer getDealer() {
		return dealer;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

}
