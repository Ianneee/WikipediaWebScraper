package wikipediaWebScraperLib;

/**
 * La classe rappresenta una pagina di Wikipedia Italia e salva le informazioni principali
 * della pagina registrata.
 * 
 * @author Ian Tirso Cini
 *
 */
public class PaginaWikipedia extends WikipediaWebPage {

	/**
	 * L'indirizzo url della pagina.
	 */
	private String url;
	
	/**
	 * Il titolo della pagina senza sigla Wikipedia.
	 */
	private String titoloPagina;

	/**
	 * L'url dell'immagine principale della pagina, se presente.
	 */
	private String urlImmagine;
	
	/**
	 * Il sinottico della pagina.
	 */
	private Sinottico sinottico;
	
	/**
	 * Il codice html con il sinottico
	 */
	private String sinotticoHtml;
	
	/**
	 * Costruisce una pagina Wikipedia.
	 * 
	 * @param url L'url della pagina.
	 * @param titoloPagina Il titolo della pagina.
	 * @param urlImmagine L'url dell'immagine.
	 * @param sinottico Il Sinottico della pagina.
	 * @param sinotticoHtml Il codice html con del sinottico.
	 */
	public PaginaWikipedia(String url, String titoloPagina, String urlImmagine, Sinottico sinottico, String sinotticoHtml) {
		this.url = url;
		this.titoloPagina = titoloPagina;
		this.urlImmagine = urlImmagine;
		this.sinottico = sinottico;
		this.sinotticoHtml = sinotticoHtml;
	}
	
	/**
	 * Ritorna il titolo della pagina.
	 * 
	 * @return Il titolo.
	 */
	@Override
	public String getTitle() {
		return titoloPagina;
	};
	
	/**
	 * Ritorna l'url della pagina.
	 * 
	 * @return L'url della pagina.
	 */
	@Override
	public String getUrl() {
		return url;
	};
	
	/**
	 * Ritorna l'url dell'immagine.
	 * @return L'url dell'immagine.
	 */
	public String getUrlImmagine() {
		return urlImmagine;
	}
	
	/**
	 * Ritorno un oggetto di tipo Sinottico con le informazioni della pagina.
	 * @return Sinottico della pagina Wikipedia.
	 */
	public Sinottico getSinottico() {
		return sinottico;
	}
	
	@Override
	public String toString() {
		return titoloPagina;
	}
	
	/**
	 * Ritorna il codice html con il sinottico.
	 * 
	 * @return Il codice html con il sinottico.
	 */
	public String getSinotticoHtml() {
		return sinotticoHtml;
	}

}
