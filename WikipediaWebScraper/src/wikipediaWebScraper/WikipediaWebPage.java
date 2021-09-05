package wikipediaWebScraper;

/**
 * Classe astratta che rappresente una pagina di Wikipedia e le sue informazioni basilari.
 * 
 * @author Ian Tirso Cini
 *
 */
public abstract class WikipediaWebPage { 
	/**
	 * L'indirizzo url della pagina.
	 */
	private String url;
	
	/**
	 * Il titolo della pagina senza sigla Wikipedia.
	 */
	private String titoloPagina;
	
	public WikipediaWebPage(String url, String titoloPagina) {
		this.url = url;
		this.titoloPagina = titoloPagina;
	}
	
	/**
	 * Ritorna il titolo della pagina.
	 * @return Il titolo.
	 */
	public String getTitle() {
		return titoloPagina;
	};
	
	/**
	 * Ritorna l'url della pagina.
	 * @return L'url della pagina.
	 */
	public String getUrl() {
		return url;
	};

}
