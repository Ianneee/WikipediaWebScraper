package wikipediaWebScraperLib;

import java.util.List;
import wikipediaWebScraperLib.RigaSinottico.Informazione;

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
	
	/**
	 * Cerca la riga all'interno del sinottico dal nome della categoria dell'informazione.
	 * Viene ritornata tutta la riga come oggetto di tipo RigaSinottico.
	 * Se la riga non è presente viene lanciato un errore RigaNonPresenteException.
	 * 
	 * @param categoria La categoria presente nella cella sinistra della riga.
	 * @return La RigaSinottico corrispondente.
	 * @throws RigaNonPresenteException
	 */
	public RigaSinottico getRigaSinottico(String categoria) throws RigaNonPresenteException {
		return sinottico.getRiga(categoria);
	}
	
	/**
	 * Cerca la riga dal nome della categoria dell'informazione. Viene ritornata la Lista di Informazione
	 * corrispondenti a quella riga.
	 * Se la categoria non viene trovata tra le righe presenti nel sinottico viene lanciato
	 * un errore di tipo RigaNonPresenteException.
	 * 
	 * @param categoria La categoria presente nella cella sinistra della riga.
	 * @return La lista di oggetti Informazione della riga trovata.
	 * @throws RigaNonPresenteException
	 */
	public List<Informazione> getInformazioneSinottico(String categoria) throws RigaNonPresenteException {
		RigaSinottico riga = sinottico.getRiga(categoria);
		if (riga != null) {
			return riga.getInformazioni();
		} else {
			return null;
		}
		
	}

}
