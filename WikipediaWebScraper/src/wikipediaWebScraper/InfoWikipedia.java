package wikipediaWebScraper;

import java.util.function.Supplier;

/**
 * Classe di utilità per il reperimento di informazioni dal codice sorgente della pagina Wikipedia
 * passata come argomento ai metodi.
 * 
 * @author Ian Tirso Cini
 *
 */
public class InfoWikipedia {
	
	/**
	 * Estrae l'url dell'immagine principale della pagina Wikipedia, se presente.
	 * 
	 * @param sorgente Sorgente Html della pagina Wikipedia.
	 * @return L'url dell'immagine principale.
	 */
	public static String urlImmagine(String sorgente) {
		// L'url dell'immagine principale è nel tag che inizia con <meta property="og:image" ...>
		int controllo = sorgente.indexOf("og:image");
		if (controllo != -1) {
			// Taglio la stringa con il sorgente al punto dell'immagine
			sorgente = sorgente.substring(controllo);
			int start = sorgente.indexOf("https");
			int end = start;
			// Fine dell'indirizzo all'interno del tag
			while (sorgente.charAt(end) != '"'){
				end++;
			}
			return sorgente.substring(start, end);
		}
		return null;
	}
	
	/**
	 * Estrae l'url della pagina dal codice sorgente.
	 * 
	 * @param sorgente Sorgente Html della pagina Wikipedia.
	 * @return L'url della pagina.
	 */
	public static String urlPagina(String sorgente) {
		// L'url della pagina è nel tag <link rel="canonical" ..>
		int controllo = sorgente.indexOf("rel=\"canonical\"");
		if (controllo != -1) {
			// Taglio la stringa del sorgente al punto dell'url
			sorgente = sorgente.substring(controllo);
			int start = sorgente.indexOf("https");
			int end = start;
			// Fine dello spazio contenente l'url
			while (sorgente.charAt(end) != '"') {
				end++;
			}
			return sorgente.substring(start, end);
		}
		return null;
	}
	
	/**
	 * Estrae il titolo della pagina Wikipedia senza "- Wikipedia" alla fine.
	 * 
	 * @param sorgente Sorgente Html della pagina Wikipedia.
	 * @return Il titolo della pagina.
	 */
	public static String titoloPagina(String sorgente) {
		// Il titolo della pagina è fra i tag <title> ... </title>
		int start = sorgente.indexOf("<title>");
		if (start != -1) {
			int end = sorgente.indexOf("</title>");
			String title = sorgente.substring(start, end);
			// Pulisco la stringa con il titolo
			title = title.substring(title.indexOf(">") + 1, title.indexOf(" - Wikipedia"));
			return title.trim();
		}
		return null;
	}
	
	/**
	 * Analizza il sinottico della pagina, se presente.
	 * 
	 * @param sorgente Sorgente Html della pagina Wikipedia.
	 * @return Oggetto Sinottico con le informazioni della pagina.
	 */
	public static Sinottico sinottico(String sorgente) {
		String sinottico = estraiSinottico(sorgente);
		if (sinottico != null) {
			ParserTabellaWikipedia parser = new ParserSinotticoWikipedia();
			return (Sinottico) parser.analizzaTabella(sinottico);
		}
		return null;
	}
	
	/**
	 * Analizza il sinottico della pagina, se presente.
	 * Oltre al sorgente bisogna passare un Supplier con un parser che implementi l'interfaccia ParserTabellaWikipedia.
	 * 
	 * @param sorgente Sorgente Html della pagina Wikipedia.
	 * @param parser Parser che implementa l'interfaccia ParserTabellaWikipedia.
	 * @return Oggetto Sinottico con le informazioni della pagina.
	 */
	public static Sinottico sinottico(String sorgente, Supplier<ParserTabellaWikipedia> parser) {
		String sinottico = estraiSinottico(sorgente);
		if (sinottico != null) {
			ParserTabellaWikipedia prs = parser.get();
			return (Sinottico) prs.analizzaTabella(sinottico);
		}
		return null;
	}
	
	/**
	 * Restituisce la sezione di codice del sinottico, se presente.
	 * @param sorgente Sorgente Html della pagina Wikipedia.
	 * @return Porzione di codice del sinottico.
	 */
	public static String estraiSinottico(String sorgente) {
		// Il sinottico inizio in uno specifico tag <table class= ...>
		int start = sorgente.indexOf("<table class=\"sinottico\"");
		if (start != -1) {
			// Taglio in modo da avere solo la tabella sinottico
			String subSorgente = sorgente.substring(start);
			int end = subSorgente.indexOf("</tbody></table>");
			return subSorgente.substring(0, end);
		}
		return null;
	}
	
	/**
	 * Restituisce l'oggetto TabellaWikipedia dal sorgente passato.
	 * Il Supplier deve instanziare un parser che implementa l'interfaccia ParserTabellaWikipedia.
	 * 
	 * @param sorgente Il codice da analizzare.
	 * @param parser Il parser per analizzare il sorgente.
	 * @return L'oggetto TabellaWikipedia con i dati corrispondenti.
	 */
	public static TabellaWikipedia tabella(String sorgente, Supplier<ParserTabellaWikipedia> parser) {
		ParserTabellaWikipedia prs = parser.get();
		return prs.analizzaTabella(sorgente);
	}

}
