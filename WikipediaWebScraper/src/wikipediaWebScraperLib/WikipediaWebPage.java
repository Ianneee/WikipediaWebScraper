package wikipediaWebScraperLib;

/**
 * Classe astratta che rappresente una pagina di Wikipedia e le sue informazioni basilari.
 * 
 * @author Ian Tirso Cini
 *
 */
public abstract class WikipediaWebPage { 

	
	/**
	 * Ritorna il titolo della pagina.
	 * 
	 * @return Il titolo della pagina.
	 */
	public abstract String getTitle();
	
	/**
	 * Ritorna l'url della pagina.
	 * 
	 * @return L'url della pagina.
	 */
	public abstract String getUrl();

}
