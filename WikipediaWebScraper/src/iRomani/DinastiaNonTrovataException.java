package iRomani;

/**
 * Eccezione di dinastia non trovata all'interno della lista.
 * 
 * @author Ian Tirso Cini
 *
 */
public class DinastiaNonTrovataException extends Exception {

	public DinastiaNonTrovataException() {
		super("La dinastia cercata non è presente.");
	}
}
