package wikipediaWebScraperLib;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;


/**
 * La classe rappresenta la tabella sinottico di una pagina Wikipedia da cui sono state
 * estrapolate le informazioni contenute all'interno.
 * 
 * @author Ian Tirso Cini
 *
 */
public class Sinottico extends TabellaWikipedia implements Iterable<RigaSinottico> {

	/**
	 * Lista che contiene tutte le righe aggiunte
	 */
	private List<RigaSinottico> righe;

	/**
	 * La classe delle righe che possono essere accettate da questa classe Sinottico
	 */
	private final String CLASSE_RIGA_SINOTTICO = RigaSinottico.class.getName();
	
	public Sinottico() {
		righe = new LinkedList<>();
	}
	
	/**
	 * Aggiunge una riga già creata.
	 * 
	 * @param riga del sinottico.
	 */
	@Override
	public void addRiga(RigaTabella riga) {
		// Il metodo accetta tutte classi che implementano l'interfaccia RigaTabella
		// ma per essere valide controllo che appartengano alla classe RigaSinottico richiedendo il tipo
		// alla riga ricevuta dal metodo e lo controllo.
		if (riga.getClass().getName().equals(CLASSE_RIGA_SINOTTICO)) {
			righe.add((RigaSinottico)riga);	
		}
		
	}
	
	/**
	 * Crea una nuova riga inserendo il nome categoria contenuto nella cella sinistra
	 * della riga.
	 * 
	 * @param categoria nella cella sinistra della riga.
	 */
	public void creaRiga(String categoria) {
		RigaSinottico riga = new RigaSinottico(categoria);
		righe.add(riga);
	}
	
	/** 
	 * Aggiungi il contenuto della cella destra della riga del sinottico con la 
	 * ricerca tramite il nome della categoria contenuto nella cella sinistra.
	 * 
	 * @param categoria contenuta nella cella sinistra del sinottico.
	 * @param informazione da aggiungere alla categoria.
	 */
	public void addInfoToRiga(String categoria, String informazione) {
		for (RigaSinottico riga : righe) {
			if (riga.getCategoria().equals(categoria)) {
				riga.addInformazione(informazione);
				break;
			}
		}
	}
	
	/**
	 * Aggiungi il contenuto della cella destra a cui è associato anche un link per la corrispettiva
	 * categoria contenuta nella cella sinistra.
	 * L'url per essere valido deve cominciare con https://it.wikipedia.org/wiki .
	 * 
	 * @param categoria contenuta nella cella sinistra del sinottico.
	 * @param informazione da aggiungere alla categoria.
	 * @param url associato a quell'informazione.
	 */
	public void addInfoUrlToRiga(String categoria, String informazione, String url) {
		for (RigaSinottico riga : righe) {
			if (riga.getCategoria().toLowerCase().equals(categoria.toLowerCase())) {
				riga.addInformazione(informazione, url);
				break;
			}
		}
	}
	
	/**
	 * Aggiungi il link per una specifica informazione già inserita.
	 * L'url per essere valido deve cominciare con https://it.wikipedia.org/wiki .
	 * 
	 * @param categoria contenuta nella cella sinistra del sinottico.
	 * @param informazione contenuta nella cella destra per la specifica categoria.
	 * @param url da aggiungere a quell'informazione.
	 */
	public void addUrlInfoToRiga(String categoria, String informazione, String url) {
		for (RigaSinottico riga : righe) {
			if (riga.getCategoria().toLowerCase().equals(categoria.toLowerCase())) {
				riga.addUrl(informazione, url);
				break;
			}
		}
	}
	
	/**
	 * Ritorna una specifica riga tramite il nome categoria nella cella destra, se presente.
	 * Se la riga non è presente lancia un errore di tipo RigaNonPresenteException.
	 * 
	 * @param categoria da cercare.
	 * @return la riga corrispondente a quella categoria.
	 * @throws RigaNonPresenteException
	 */
	@Override
	public RigaSinottico getRiga(String categoria) throws RigaNonPresenteException {
		for (RigaSinottico riga : righe) {
			if (riga.getCategoria().toLowerCase().equals(categoria.toLowerCase())) {
				return riga;
			}
		}
		throw new RigaNonPresenteException();
	}
	
	/**
	 * Ritorna tutte le righe contenute nel sinottico.
	 * 
	 * @return il set contenente le righe.
	 */
	@Override
	public List<RigaSinottico> getRighe(){
		return righe;
	}
	
	/**
	 * Restituisce Iterator delle righe.
	 */
	@Override
	public Iterator<RigaSinottico> iterator(){
		return righe.iterator();
	}
}
