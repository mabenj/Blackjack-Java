package UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;

/**
 * Luokka toteuttaa rajapinnan KeyListener. Luokan tarkoitus on kuunella k�ytt�j�n sy�tett� kuunes oikea merkkijono on sy�tetty, jolloin pelin "Easter Egg" on aktivoitu.
 * 
 * @author mabenj
 *
 */
public class EasterEggListener implements KeyListener{
	private Game game;
	private BlackjackPanel panel;
	private StringBuilder sb;
	
	public EasterEggListener(Game game, BlackjackPanel panel) {
		this.game = game;
		this.panel = panel;
		sb = new StringBuilder();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		if(!game.getEasterEgg()) {
			sb.append(e.getKeyChar());	
		}
		
		
		if(sb.toString().toLowerCase().contains("jari") && !game.getEasterEgg()) {
			sb = new StringBuilder();
			
			game.setEasterEgg(true);
			panel.repaint();
			
			System.out.println("Easter Egg Activated");
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
