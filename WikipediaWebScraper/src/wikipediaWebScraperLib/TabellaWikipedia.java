package wikipediaWebScraperLib;

import java.util.Collection;

/**
 * Classe astratta che rappresenta una tabella di Wikipedia.
 * Le classi che rappresentano le righe della tabella devono implementare l'interfaccia RigaTabella.
 * I campi sono a libera scelta dello sviluppatore.
 * 
 * @author Ian Tirso Cini
 *
 */
public abstract class TabellaWikipedia<T extends RigaTabella> {
	
	/**
	 * Ritorna la riga contenuta all'interno della tabella mediante ricerca tramite stringa
	 * passata come argomento.
	 * 
	 * @param identificativoRiga La chiave di ricerca della riga.
	 * @return La riga trovata.A
	 * @throws RigaNonPresenteException Errore lanciato per riga non trovata.
	 */
	public abstract T getRiga(String identificativoRiga) throws RigaNonPresenteException;

	
	/**
	 * Aggiungi una riga alla tabella, riga che implementi l'interfaccia RigaTabella.
	 * 
	 * @param riga La riga da aggiungere.
	 */
	public abstract void addRiga(T riga);
	
	/**
	 * Ritorna tutte le righe contenute all'interno della tabella.
	 * 
	 * @return Collection contentente le righe.
	 */
	public abstract Collection<T> getRighe();

}
