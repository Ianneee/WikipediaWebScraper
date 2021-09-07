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
	 * L'url dell'immagine principale della pagina, se presente.
	 */
	private String urlImmagine;
	
	/**
	 * Il sinottico della pagina.
	 */
	private Sinottico sinottico;
	
	public PaginaWikipedia(String url, String titoloPagina, String urlImmagine, Sinottico sinottico) {
		super(url, titoloPagina);
		this.urlImmagine = urlImmagine;
		this.sinottico = sinottico;
	}
	
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

}
