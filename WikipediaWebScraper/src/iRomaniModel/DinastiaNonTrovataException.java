package iRomaniModel;

/**
 * Eccezione di dinastia non trovata all'interno della lista.
 * 
 * @author Ian Tirso Cini
 *
 */
public class DinastiaNonTrovataException extends Exception {

	/**
	 * Errore lanciato se la dinastia cercata non è presente nella lista.
	 */
	public DinastiaNonTrovataException() {
		super("La dinastia cercata non è presente.");
	}
}
