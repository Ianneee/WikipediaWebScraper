import java.util.List;

import wikipediaWebScraper.RigaTabella;
import wikipediaWebScraper.TabellaWikipedia;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * La classe rappresenta una tabella relativa ad una sola dinastia presente nella pagina Wikipedia
 * dove sono elencate dinastie ed imperatori romani.
 * La classe è un raccoglitore di oggetti di tipo Riga.
 * 
 * @author Ian Tirso Cini
 *
 */
public class TabellaDinastie extends TabellaWikipedia implements Iterable<TabellaDinastie.Riga> {
	
	/**
	 * Nome della dinastia.
	 */
	private String nomeDinastia;
	
	/**
	 * Link della dinastia.
	 */
	private String urlDinastia;
	
	/**
	 * Lista che contiene la righe di ogni imperatore rappresentato nella tabella.
	 */
	private List<TabellaDinastie.Riga> righe;
	
//	private Riga rigaEsempio = new Riga();
	
	/**
	 * Costruisce una TabellaDinastie fornendo il nome della dinastia.
	 * @param nomeDinastia
	 */
	public TabellaDinastie(String nomeDinastia) {
		this.nomeDinastia = nomeDinastia;
		righe = new ArrayList<>();
	}
	
	/**
	 * Imposta l'url della dinastia rappresentata nella tabella.
	 * @param urlDinastia
	 */
	public void setUrlDinastia(String urlDinastia) {
		this.urlDinastia = urlDinastia;
	}
	
	/**
	 * Aggiunge una riga rappresentante l'imperatore in quella riga.
	 * 
	 * @param nomeImperatore Il nome dell'imperatore.
	 * @param urlPagina L'url della pagina Wikipedia dell'imperatore.
	 */
	public void nuovaRiga(String nomeImperatore, String urlPagina) {
		Riga nuovaRiga = new Riga(nomeImperatore, urlPagina);
		this.addRiga(nuovaRiga);
	}
	
	/**
	 * Ritorna una riga presente nella tabella cercandola con il nome dell'imperatore.
	 * Se non viene trovata nessuna riga il metodo ritorna null.
	 */
	@Override
	public TabellaDinastie.Riga getRiga(String nomeImperatore) {
		for (Riga riga : righe ) {
			if (riga.getNomeImperatore().equals(nomeImperatore)) {
				return riga;
			}
		}
		return null;
	}
	
	/**
	 * Ritorna la riga richiesta tramite il suo indice.
	 * La prima riga della tabella è la numero 1.
	 * 
	 * @param numeroRiga Il numero della riga desiderata.
	 * @return La riga richiesta.
	 */
	public TabellaDinastie.Riga getRiga(int numeroRiga) {
		if (numeroRiga <= righe.size()) {
			return righe.get(numeroRiga -1);
		}
		return null;
	}
	
	/**
	 * Aggiunge una riga alla tabella dinastia.
	 * La riga aggiunta viene controllata che sia un'istanza di TabellaDinastie.Riga
	 * in quanto il metodo accetta oggetti che abbiano implementato l'interfaccia RigaTabella.
	 * Se la riga fornita come argomento è del tipo sbagliato questa non viene aggiunta.
	 */
	@Override
	public void addRiga(RigaTabella riga) {
		// TODO raise errore tipo della riga aggiunta errata
		
		if (riga.getClass() == /*rigaEsempio.getClass()*/ Riga.class ) {
			righe.add((Riga) riga);
		}
	}
	
	/**
	 * Ritorna la lista contenente le righe all'interno della tabella.
	 * 
	 * @return Lista con le righe.
	 */
	@Override
	public List<TabellaDinastie.Riga> getRighe(){
		return righe;
	}
	
	/**
	 * Iterator per la tabella contenente le informazioni sugli imperatori.
	 * 
	 * @return Iterator delle righe
	 */
	@Override
	public Iterator<TabellaDinastie.Riga> iterator(){
		return righe.iterator();
	}
	
	
	
	/**
	 * La classe rappresenta la riga di una tabella della pagina Wikipedia contenente
	 * le dinastie degli imperatori romani.
	 * 
	 * @author Ian Tirso Cini
	 *
	 */
	public class Riga implements RigaTabella{
		/**
		 * Il nome dell'imperatore.
		 */
		private String nomeImperatore;

		/**
		 * Url della pagina Wikipedia dell'imperatore.
		 */
		private String urlPagina;

		/**
		 * Url di dinastia di appartenza
		 */
		private String urlDinastia;
		
		private Riga(String nomeImperatore, String urlPagina) {
			this.nomeImperatore = nomeImperatore;
			this.urlPagina = urlPagina;
//			this.urlDinastia = urlDinastia;
		}
		
		private Riga() {}
		
		public String getNomeImperatore() {
			return this.nomeImperatore;
		}
		
		public String getUrlPagina() {
			return this.urlPagina;
		}
		
		public String getUrlDinastia() {
			return this.urlDinastia;
		}
		
		@Override
		public String toString() {
			return nomeImperatore + " | " + urlPagina + " | " + urlDinastia;
		}
	}
	
}
