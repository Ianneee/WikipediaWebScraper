package iRomani;
import java.util.List;

import wikipediaWebScraperLib.RigaTabella;
import wikipediaWebScraperLib.TabellaWikipedia;

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
	 * 
	 * @param nomeDinastia
	 */
	public TabellaDinastie(String nomeDinastia) {
		this.nomeDinastia = nomeDinastia;
		righe = new ArrayList<>();
	}
	
	/**
	 * Imposta l'url della dinastia rappresentata nella tabella.
	 * 
	 * @param urlDinastia
	 */
	public void setUrlDinastia(String urlDinastia) {
		this.urlDinastia = urlDinastia;
	}
	
	/**
	 * Ritorna l'url della pagina della dinastia.
	 * 
	 * @return L'url della pagina.
	 */
	public String getUrlDinastia() {
		return urlDinastia;
	}
	
	/**
	 * Ritorna il nome della dinastia.
	 * 
	 * @return Il nome della dinastia.
	 */
	public String getNomeDinastia() {
		return nomeDinastia;
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
		} else {
			return null;
		}
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
		
		if (riga.getClass() ==  Riga.class ) {
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
	
	@Override
	public String toString() {
		return "Nome dinastia: " + nomeDinastia + " | url dinastia: " + urlDinastia;
 	}
	
	/**
	 * Ritorna una lista con gli url alle pagine Wikipedia degli imperatori della 
	 * dinastia.
	 * 
	 * @return Lista con gli url.
	 */
	public List<String> getUrlImperatori(){
		List<String> urlImperatori =  new ArrayList<>();
		for (Riga riga : righe) {
			urlImperatori.add(riga.getUrlPagina());
		}
		return urlImperatori;
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
		 * Costruttore della riga, fornire il nome dell'imperatore e della sua pagina Wikipedia.
		 * 
		 * @param nomeImperatore Il nome dell'imperatore.
		 * @param urlPagina Url della pagine dell'imperatore.
		 */
		public Riga(String nomeImperatore, String urlPagina) {
			this.nomeImperatore = nomeImperatore;
			this.urlPagina = urlPagina;
		}
		
		/**
		 * Ritorna il nome dell'imperatore.
		 * 
		 * @return Stringa con il nome dell'imperatore.
		 */
		public String getNomeImperatore() {
			return nomeImperatore;
		}
		
		/**
		 * Ritorna stringa con l'url della pagina Wikipedia con l'imperatore.
		 * @return Stringa con l'url della pagina.
		 */
		public String getUrlPagina() {
			return urlPagina;
		}
		
		@Override
		public String toString() {
			return nomeImperatore + " | " + urlPagina ;
		}
	}
	
}
