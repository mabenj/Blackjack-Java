package Players;

/**
 * Luokka mallintaa pelin jakajaa. Luokka on luokan Player aliluokka.
 * 
 * @author mabenj
 *
 */
public class Dealer extends Player{
	
	/**
	 * Konstruktori kutsuu yläluokan konstruktoria nimiparametrina "Dealer".
	 * Konstruktori myös asettaa jakajan tilin saldoksi 0.
	 * 
	 */
	public Dealer() {
		super("Dealer");
		balance = 0;
	}
}
