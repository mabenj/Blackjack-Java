package Players;

/**
 * Luokka mallintaa pelin jakajaa. Luokka on luokan Player aliluokka.
 * 
 * @author mabenj
 *
 */
public class Dealer extends Player{
	
	/**
	 * Konstruktori kutsuu yl�luokan konstruktoria nimiparametrina "Dealer".
	 * Konstruktori my�s asettaa jakajan tilin saldoksi 0.
	 * 
	 */
	public Dealer() {
		super("Dealer");
		balance = 0;
	}
}
