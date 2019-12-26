package main;

import UI.BlackjackFrame;

/**
 * Pelin p‰‰luokka. Luokan main-metodi k‰ynnist‰‰ pelin suorituksen.
 * Luokassa voidaan hallita pelin pakkojen lukum‰‰r‰‰ ja peli-ikkunan kokoa.
 * 
 * @author mabenj
 *
 */
public class Main {
	private static final int NUM_DECKS = 3; //pakkojen lkm
	//private static final int WIDTH = 696+16, HEIGHT = 564+39+20;
	private static final int WIDTH = 696+6, HEIGHT = 564+29+20;

	public static void main(String[] args) {
		Game game = new Game(NUM_DECKS);
		new BlackjackFrame(WIDTH, HEIGHT, game);
	}
}
