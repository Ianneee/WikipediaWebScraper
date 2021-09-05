package wikipediaWebScraper;

/**
 * Builder di oggetti del tipo PaginaWikipedia. Seguire il funzionamente del "builder pattern" nell'utilizzo della classe.
 * 
 * @author Ian Tirso Cini
 *
 */
public class PaginaWikipediaBuilder {
	
	/**
	 * L'url della pagina.
	 */
	private String url;
	
	/**
	 * Il titolo della pagina.
	 */
	private String titoloPagina;
	
	/**
	 * L'url dell'immagine principale.
	 */
	private String urlImmagine;
	
	/**
	 * Il sinottico della pagina.
	 */
	private Sinottico sinottico;
	
	public PaginaWikipediaBuilder() {};
	
	public PaginaWikipediaBuilder url(String url) {
		this.url = url;
		return this;
	}
	
	/**
	 * Imposta il titolo della pagina Wikipedia.
	 * @param titoloPagina Il titolo della pagina.
	 * @return Il builder stesso.
	 */
	public PaginaWikipediaBuilder titoloPagina(String titoloPagina) {
		this.titoloPagina = titoloPagina;
		return this;
	}
	
	/**
	 * Imposta l'url dell'immagine principale della pagina.
	 * @param urlImmagine L'url dell'immagine.
	 * @return Il builder stesso.
	 */
	public PaginaWikipediaBuilder urlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
		return this;
	}
	
	/**
	 * L'oggetto Sinottico associato alla pagina.
	 * @param sinottico L'oggetto Sinottico della pagina.
	 * @return Il builder stesso.
	 */
	public PaginaWikipediaBuilder sinottico(Sinottico sinottico) {
		this.sinottico = sinottico;
		return this;
	}
	
	/**
	 * Restituisce una PaginaWikipedia con i paramentri scelti.
	 * @return Un oggetto PaginaWikipedia con i parametri scelti.
	 */
	public PaginaWikipedia build() {
		return new PaginaWikipedia(url, titoloPagina, urlImmagine, sinottico);
	}

}
