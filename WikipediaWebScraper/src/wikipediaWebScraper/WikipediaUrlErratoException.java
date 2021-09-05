package wikipediaWebScraper;

/**
 * Errore che segnala il passaggio di un link Wikipedia in formato errato.
 * @author Ian Tirso Cini
 */
public class WikipediaUrlErratoException extends Exception{
	
	public WikipediaUrlErratoException() {
		super("Url di Wikipedia passato in formato errato, non inizia con https://it.wikipedia.org/wiki/");
	}
	
	public WikipediaUrlErratoException(String message) {
		super(message);
	}

}
