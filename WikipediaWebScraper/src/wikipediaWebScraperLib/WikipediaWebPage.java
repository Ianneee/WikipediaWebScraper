package wikipediaWebScraperLib;

/**
 * Classe astratta che rappresente una pagina di Wikipedia e le sue informazioni basilari.
 * 
 * @author Ian Tirso Cini
 *
 */
public class WikipediaWebPage { 
	/**
	 * L'indirizzo url della pagina.
	 */
	private String url;
	
	/**
	 * Il titolo della pagina senza sigla Wikipedia.
	 */
	private String titoloPagina;
	
	/**
	 * Costruisce una WikipediaWebPage dall'url e dal titolo della pagina
	 * passati come argomento.
	 * 
	 * @param url L'url della pagina.
	 * @param titoloPagina Il Titolo della pagina.
	 */
	public WikipediaWebPage(String url, String titoloPagina) {
		this.url = url;
		this.titoloPagina = titoloPagina;
	}
	
	/**
	 * Ritorna il titolo della pagina.
	 * 
	 * @return Il titolo.
	 */
	public String getTitle() {
		return titoloPagina;
	};
	
	/**
	 * Ritorna l'url della pagina.
	 * 
	 * @return L'url della pagina.
	 */
	public String getUrl() {
		return url;
	};

}
