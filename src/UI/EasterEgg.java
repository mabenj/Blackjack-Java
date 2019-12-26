package UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import main.Game;

/**
 * Luokka toteuttaa rajapinnan KeyListener. Luokan tarkoitus on kuunella käyttäjän syötettä kuunes oikea merkkijono on syötetty, jolloin pelin "Easter Egg" on aktivoitu.
 * 
 * @author mabenj
 *
 */
public class EasterEgg implements KeyListener, MouseListener, Runnable{
	private Game game;
	private BlackjackPanel panel;
	private StringBuilder sb;
	
	private int x, y, width, height;
	private boolean right, left, up, down;
	
	public EasterEgg(Game game, BlackjackPanel panel) {
		this.game = game;
		this.panel = panel;
		sb = new StringBuilder();
		
		x = game.getEgX();
		y = game.getEgY();	
		
		right = down = true;
		left = up = false;
		
		//width = panel.getWidth();
		//height = panel.getHeight();
		width = 696;
		height = 564;
		
	}
	
	@Override
	public void run() {
		System.out.println("Easter Egg thread running.");

		while (game.getEasterEgg()) {
			if (x + 250 >= width) {
				right = false;
				left = true;
			}

			if (x <= 0) {
				left = false;
				right = true;
			}

			if (y + 200 >= height) {
				down = false;
				up = true;
			}

			if (y <= 0) {
				up = false;
				down = true;
			}

			if (up) {
				y--;
			}

			if (down) {
				y++;
			}

			if (left) {
				x--;
			}

			if (right) {
				x++;
			}

			game.setEgX(x);
			game.setEgY(y);

			panel.repaint();
			
			try {
				Thread.sleep(7);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Easter Egg thread shutting down.");
		Thread.currentThread().interrupt();
	}
	
	public void moveIt() {
		
		Timer timer = new Timer();
		TimerTask tt = new TimerTask() {
			
			@Override
			public void run() {
				
				if(!game.getEasterEgg()) {
					timer.cancel();
				}else {
					
					if(x + 250 >= width) {
						right = false;
						left = true;
					}
					
					if(x <= 0) {
						left = false;
						right = true;
					}
					
					if(y + 200 >= height) {
						down = false;
						up = true;
					}
					
					if(y <= 0) {
						up = false;
						down = true;
					}
					
					
					if(up) {
						y--;
					}
					
					if(down) {
						y++;
					}
					
					if(left) {
						x--;
					}
					
					if(right) {
						x++;
					}
					
					
					game.setEgX(x);
					game.setEgY(y);
					
					panel.repaint();
				}					
			}					
		};
		
		timer.schedule(tt, 7, 7);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		if(!game.getEasterEgg()) {
			sb.append(e.getKeyChar());	
		}else {
			game.setEasterEgg(false);
			panel.repaint();
		}
		
		
		if(sb.toString().toLowerCase().contains("jari") && !game.getEasterEgg()) {
			sb = new StringBuilder();
			
			game.setEasterEgg(true);
			panel.repaint();
			
			System.out.println("Easter Egg Activated");
			
			new Thread(this).start();
			
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		game.setEasterEgg(false);
		panel.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
