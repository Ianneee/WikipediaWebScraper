package alberoGenealogicoLib;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.Graphs;

import java.util.List;
import java.util.Set;

/**
 * La classe rappresenta un albero genealogico.
 * Possono essere inserite classi Persona che rappresentano le persone appartenenti a quella famiglia.
 * Si può aggiungere il livello generazionale delle persone inserite.
 * 
 * @author Ian Tirso Cini
 *
 */
public class AlberoGenealogico {
	
	/**
	 * Il grafo con le relazioni di parentela della famiglia.
	 */
	private Graph<Persona, Archi> albero;
	
	/**
	 * Il nome della famiglia.
	 */
	private String nomeFamiglia;
	
	/**
	 * Il capostipite e punto di ingresso al grafo.
	 */
	private Persona capostipite;
	
	/**
	 * Costruttore di un albero genealogico. Passare come argomento il nome della famiglia.
	 * 
	 * @param nomeFamiglia Il nome della famiglia.
	 */
	public AlberoGenealogico(String nomeFamiglia) {
		
		this.nomeFamiglia = nomeFamiglia;
		
		albero = new SimpleDirectedGraph<>(Archi.class);
		
	}
	
	/**
	 * Aggiungi una Persona all'albero genealogico.
	 * 
	 * @param persona La Persona da aggiungere.
	 */
	public void addPersona(Persona persona) {
		albero.addVertex(persona);
	}
	
	/**
	 * Inserisce la persona come capostipite della famiglia.
	 * 
	 * @param capostipite Il capostipite.
	 */
	public void setCapostipite(Persona capostipite) {
		this.capostipite = capostipite;
	}
	
	/**
	 * Ritorna il capostipite della famiglia.
	 * 
	 * @return Il capostipite.
	 */
	public Persona getCapostipite() {
		return capostipite;
	}
	
	/**
	 * Aggiungi un figlio al genitore passato come argomento.
	 * 
	 * @param genitore Il genitore.
	 * @param figlio Il figlio.
	 */
	public void addFiglio(Persona genitore, Persona figlio) {
		Graphs.addEdgeWithVertices(albero, genitore, figlio);
	}
	
	/**
	 * Ritorna la lista di tutti i figli della persona passata per
	 * argomento.
	 * 
	 * @param genitore Il genitore.
	 * @return La lista con i figli.
	 */
	public List<Persona> getFigli(Persona genitore){
		return Graphs.successorListOf(albero, genitore);
	}
	
	/**
	 * Ritorna la lista dei genitori del figlio passato come argomento.
	 * 
	 * @param figlio Il figlio.
	 * @return La lista con i genitori.
	 */
	public List<Persona> getGenitori(Persona figlio){
		return Graphs.predecessorListOf(albero, figlio);
	}
	
	/**
	 * Ritorna un set con tutte le persone all'interno dell'albero genealogico.
	 * 
	 * @return Il set con tutte le persone.
	 */
	public Set<Persona> getPersone() {
		return albero.vertexSet();
	}
	
	/**
	 * Ritorna il nome della famiglia.
	 * 
	 * @return Il nome della famiglia.
	 */
	public String getNomeFamiglia() {
		return nomeFamiglia;
	}
	
	@Override
	public String toString() {
		return albero.toString();
	}
	
	/**
	 * Ritorna il grafo con la dinastia.
	 * 
	 * @return Il grafo.
	 */
	public Graph<Persona, Archi> getAlbero() {
		return albero;
	}
	
}
