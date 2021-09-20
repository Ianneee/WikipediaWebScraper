package wikipediaWebScraperLib;

/**
 * Builder di oggetti del tipo PaginaWikipedia. La classe segue l'utilizzo del design patter "Builder".
 * Restituisce un oggetto PaginaWikipedia che rappresenta la pagina Wikipedia di cui si sono fornite
 * le informazioni.
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
	
	/**
	 * L'html del sinottico;
	 */
	private String sinotticoHtml;
	
	/**
	 * Imposta l'url della pagina Wikipedia.
	 * 
	 * @param urlPagina L'url della pagina.
	 * @return Il builder stesso.
	 */
	public PaginaWikipediaBuilder url(String urlPagina) {
		this.url = urlPagina;
		return this;
	}
	
	/**
	 * Imposta il titolo della pagina Wikipedia.
	 * 
	 * @param titoloPagina Il titolo della pagina.
	 * @return Il builder stesso.
	 */
	public PaginaWikipediaBuilder titoloPagina(String titoloPagina) {
		this.titoloPagina = titoloPagina;
		return this;
	}
	
	/**
	 * Imposta l'url dell'immagine principale della pagina.
	 * 
	 * @param urlImmagine L'url dell'immagine.
	 * @return Il builder stesso.
	 */
	public PaginaWikipediaBuilder urlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
		return this;
	}
	
	/**
	 * L'oggetto Sinottico associato alla pagina.
	 * 
	 * @param sinottico L'oggetto Sinottico della pagina.
	 * @return Il builder stesso.
	 */
	public PaginaWikipediaBuilder sinottico(Sinottico sinottico) {
		this.sinottico = sinottico;
		return this;
	}
	
	/**
	 * Il sorgente con il sinottico della pagina.
	 * 
	 * @param sinotticoHtml Il sinottico.
	 * @return Il builder stesso.
	 */
	public PaginaWikipediaBuilder sinotticoHtml(String sinotticoHtml) {
		this.sinotticoHtml = sinotticoHtml;
		return this;
	}
	
	/**
	 * Restituisce una PaginaWikipedia con i paramentri scelti.
	 * 
	 * @return Un oggetto PaginaWikipedia con i parametri scelti.
	 */
	public PaginaWikipedia build() {
		return new PaginaWikipedia(url, titoloPagina, urlImmagine, sinottico, sinotticoHtml);
	}
	
	/**
	 * Reset dello stato attuale del builder.
	 * Tutti i campi vengono settati a null.
	 */
	public void reset() {
		this.url = null;
		this.titoloPagina = null;
		this.urlImmagine = null;
		this.sinottico = null;
		this.sinotticoHtml = null;
	}

}
