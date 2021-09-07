package wikipediaWebScraperLib;

/**
 * Interfaccia per l'analisi di tabelle all'interno del codice Html delle
 * pagine Wikipedia.
 * Si può passare in formato stringa l'intero codice sorgente o solo il codice della tabella,
 * questo è a discrezione del programmatore.
 * 
 * @author Ian Tirso Cini
 *
 */
public interface ParserTabellaWikipedia {
	
	/**
	 * Invocare questo metodo per eseguire l'analisi.
	 * 
	 * @param sorgente Sorgente o tabella Html della pagina Wikipedia.
	 * @return Oggetto TabellaWikipedia con la tabella analizzata.
	 */
	TabellaWikipedia analizzaTabella(String sorgente); 
	
}
