package main;

import java.util.ArrayList;

public class Main implements Runnable {
	private static final int NUM_DECKS = 3; //pakkojen lkm
	
	private BlackjackWindow blackjack;
	private Game game;
	private boolean running;
	
	private Player player;
	private Dealer dealer;
	private ArrayList<Card> deck;

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		game = new Game();
		
		Dealer dealer = new Dealer();
		Player player = new Player("Player");
		deck = game.populateDeckList(NUM_DECKS);
		
		blackjack = new BlackjackWindow(game, player, dealer, deck);	
		
		
		//start();
	}

	private void start() {
		running = true;
		Thread thread = new Thread(this);
		thread.start();
		
	}

	@Override
	public void run() {
		/**
		while(running) {
			game.deal(player, dealer);
			
			System.out.println("You have: " + player.getHand().get(0).getRank() + " of " + player.getHand().get(0).getSuit());
			System.out.println(" and " + player.getHand().get(1).getRank() + " of " + player.getHand().get(1).getSuit());
			System.out.println("Their sum is: " + player.getHandSum());
			
			System.out.println("Dealer has: " + dealer.getHand().get(0).getRank() + " of " + dealer.getHand().get(0).getSuit());
			System.out.println(" and " + dealer.getHand().get(1).getRank() + " of " + dealer.getHand().get(1).getSuit());
			System.out.println("Their sum is: " + dealer.getHandSum());
			
			running = false;
		}*/
	}
}
