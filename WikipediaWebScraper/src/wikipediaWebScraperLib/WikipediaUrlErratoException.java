package wikipediaWebScraperLib;

/**
 * Errore che segnala il passaggio di un link Wikipedia in formato errato.
 * 
 * @author Ian Tirso Cini
 */
public class WikipediaUrlErratoException extends Exception{
	
	/**
	 * Errore con messaggio di url errato.
	 */
	public WikipediaUrlErratoException() {
		super("Url di Wikipedia passato in formato errato, non inizia con https://it.wikipedia.org/wiki/");
	}
	
	/**
	 * Errore con messaggio personalizzato.
	 * 
	 * @param message Il messaggio da visualizzare.
	 */
	public WikipediaUrlErratoException(String message) {
		super(message);
	}

}
