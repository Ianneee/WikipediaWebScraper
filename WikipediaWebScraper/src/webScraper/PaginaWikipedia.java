package webScraper;

import java.util.Scanner;
import java.util.function.Supplier;

/**
 * La classe rappresenta una pagina di Wikipedia Italia e salva le informazioni principali
 * della pagina registrata.
 * 
 * @author Ian Tirso Cini
 *
 */
public class PaginaWikipedia {
	/**
	 * Il sinottico ovvero la tabella laterale con le informazioni principali.
	 */
	private String sinottico;

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
	
	public PaginaWikipedia(String sorgente) {
		this.url = estraiUrl(sorgente);
		this.titoloPagina = titoloPagina(sorgente);
		this.sinottico = estraiSinottico(sorgente);
		this.urlImmagine = urlImmagine(sorgente);
	}
	
	/**
	 * Analizza il sorgente fornito in input ed estrae solamente la parte relativa al sinottico.
	 * @param sorgente Html di Wikipedia.
	 * @return Il sinottico della pagina.
	 */
	private String estraiSinottico(String sorgente) {
		int start = sorgente.indexOf("<table class=\"sinottico\"");

		if (start != -1) {
			String subSorgente = sorgente.substring(start);
			int end = subSorgente.indexOf("</tbody></table>");
			return subSorgente.substring(0, end);
		}
		return null;
	}
	
	/**
	 * Ricerca nel codice Html della pagina la riga contenente la dicitura canonical che contiene
	 * l'url da cui � stato preso.
	 * @param sorgente Html di wikipedia
	 * @return la Stringa con il link della pagina.
	 */
	private String estraiUrl(String sorgente) {
		int controllo = sorgente.indexOf("canonical");
		if (controllo != -1) {
			Scanner codice = new Scanner(sorgente);
			while (codice.hasNextLine()) {
				String riga = codice.nextLine();
				if (riga.contains("canonical")) {
					int start = riga.indexOf("href=\"") +6;
					int end = riga.indexOf("\">");
					return riga.substring(start, end);
				}
			}
		}
		return null;
	}
	
	/**
	 * Dal codice sorgente passato come parametro estrae il titolo della pagina e lo ripulisce da tag e
	 * dicitura Wikipedia.
	 * @param sorgente della pagina di Wikipedia.
	 * @return Il titolo della pagina.
	 */
	private String titoloPagina(String sorgente) {
		int start = sorgente.indexOf("<title>");
		if (start != -1) {
			int end = sorgente.indexOf("</title>");
			String title = sorgente.substring(start, end);
			title = title.substring(title.indexOf(">") + 1, title.indexOf(" - Wikipedia"));
			return title.trim();
		}
		return null;
	}
	
	/**
	 * Dal codice sorgente passato come input estrae l'url relativa all'immagine principale della pagina.
	 * @param sorgente della pagina Wikipedia
	 * @return L'url dell'immagine.
	 */
	private String urlImmagine(String sorgente) {
		int controllo = sorgente.indexOf("og:image");
		if (controllo != -1) {
			Scanner codice = new Scanner(sorgente);
			while (codice.hasNextLine()) {
				String riga = codice.nextLine();
				if (riga.contains("og:image")) {
					int start = riga.indexOf("content=\"") +9;
					int end = riga.indexOf("\">");
					return riga.substring(start, end);
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 */
	public void parsaSinottico() {
		ParserSinottico parser = new ParserSinotticoWikipedia();
		parser.analizzaSinottico(sinottico);
	}
	
	/**
	 * @param parser
	 */
	public void parsaSinottico(Supplier<ParserSinottico> parser) {
		ParserSinottico prs = parser.get();
		prs.analizzaSinottico(sinottico);
	}
	
	/**
	 * Ritorna la porzione di codice con il sinottico.
	 * @return Il sinottico.
	 */
	public String getSinottico() {
		return sinottico;
	}
	
	/**
	 * Ritorna il titolo della pagina.
	 * @return Il titolo.
	 */
	public String getTitle() {
		return titoloPagina;
	}
	
	/**
	 * Ritorna l'url della pagina.
	 * @return L'url della pagina.
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Ritorna l'url dell'immagine.
	 * @return L'url dell'immagine.
	 */
	public String getUrlImmagine() {
		return urlImmagine;
	}
}
