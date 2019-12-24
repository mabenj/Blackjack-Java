package UI;

import java.awt.Image;
import java.util.List;

import javax.swing.JFrame;

import main.Game;

/**
 * Luokka mallintaa pelin ikkunaa. Luokka on JFrame-luokan aliluokka. 
 * Ikkuna sis‰lt‰‰ BlackjackPanel-tyyppisen JPanelin.
 * 
 * @author mabenj
 *
 */
public class BlackjackFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	/**
	 * Luo pelin ikkunan.
	 * 
	 * @param width	ikkunan leveys
	 * @param height	ikkunan korkeus
	 * @param game	pelin Game-instanssi
	 */
	public BlackjackFrame(int width, int height, Game game) {		
		List<Image> icons = game.getIcons();
		this.setIconImages(icons);
		
		BlackjackPanel bjPanel = new BlackjackPanel(width, height, game, this);
		EasterEgg eg = new EasterEgg(game, bjPanel);
		
		this.setFocusable(true);
		this.addKeyListener(eg);
		this.addMouseListener(eg);
		this.setTitle("Blackjack");
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(bjPanel);
		this.setResizable(false);
		this.setVisible(true);
	}
}
