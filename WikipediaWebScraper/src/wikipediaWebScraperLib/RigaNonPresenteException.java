package wikipediaWebScraperLib;

/**
 * Errore se una riga ricarcata non è presente.
 * 
 * @author Ian Tirso Cini
 *
 */
public class RigaNonPresenteException extends Exception {
	
	public RigaNonPresenteException() {
		super("La riga ricercata non è presente nel sinottico");
	}

}
