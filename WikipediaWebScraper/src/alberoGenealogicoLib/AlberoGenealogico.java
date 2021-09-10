package alberoGenealogicoLib;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import alberoGenealogicoLib.Persona.Sesso;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.Graphs;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;

/**
 * La classe rappresenta un albero genealogico.
 * Possono essere inserite classi Persona che rappresentano le persone appartenenti a quella famiglia.
 * Le relazioni di parentela tra i vari componenti sono di tipo genitore-figlio, e tra moglie-marito.
 * Si possono aggiungere anche i gradi di parentela tra le diverse generazioni: il capostipite della famiglia
 * deve essere inserito di grado 0, i suoi figli grado 1, i figli dei figli grado 2 etc.
 * 
 * La classe, essendo strutturata per un progetto di esame universitario, non prende in considerazione
 * la liquidità sessuale per poter modellare con semplicità e rapidità le richieste del progetto.
 * 
 * @author Ian Tirso Cini
 *
 */
public class AlberoGenealogico {
	
	/**
	 * Il grafo con le relazioni di parentela della famiglia.
	 */
	private Graph<Persona, DefaultWeightedEdge> albero;
	
	/**
	 * Il nome della famiglia.
	 */
	private String nomeFamiglia;
	
	/**
	 * Il capostipite e punto di ingresso al grafo.
	 */
	private Persona capostipite;
	
	private final double MOGLIE = 1.00;
	
	private final double MARITO = 2.00;
	
	private final double FIGLIO = 3.00;
	
	/**
	 * Mappa con le generazioni per grado.
	 */
	private Map<Integer, List<Persona>> generazioni;
	
	/**
	 * Costruttore di un albero genealogico. Passare come argomento il nome della famiglia.
	 * 
	 * @param nomeFamiglia Il nome della famiglia.
	 */
	public AlberoGenealogico(String nomeFamiglia) {
		this.nomeFamiglia = nomeFamiglia;
		albero = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		generazioni = new TreeMap<Integer, List<Persona>>();
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
		Graphs.addEdgeWithVertices(albero, genitore, figlio, FIGLIO);
	}
	
	/**
	 * Aggiungi un figlio ai genitori passati come argomento.
	 * 
	 * @param padre Il padre.
	 * @param madre La madre.
	 * @param figlio Il figlio.
	 */
	public void addFiglio(Persona padre, Persona madre, Persona figlio) {
		if (padre != madre) {
			Graphs.addEdgeWithVertices(albero, padre, figlio, FIGLIO);
			Graphs.addEdgeWithVertices(albero, madre, figlio, FIGLIO);
		}
	}
	
	/**
	 * Aggiungi alle generazioni la Persona passata come argomento. Passare come argomento
	 * la generazione di appartenenza.
	 * 
	 * @param gradoGenerazione Il grado della generazione della persona.
	 * @param persona La persona da aggiungere.
	 */
	public void aggiungiAGenerazione(int gradoGenerazione, Persona persona) {
		generazioni.computeIfAbsent(gradoGenerazione, k -> new ArrayList<Persona>());
		generazioni.get(gradoGenerazione).add(persona);
	}
	
	/**
	 * Ritorna la mappa con le generazioni.
	 * 
	 * @return La mappa con le generazioni.
	 */
	public Map<Integer, List<Persona>> getGenerazioni(){
		return generazioni;
	}
	
	/**
	 * Crea una relazione di matrimonio tra due Persone.
	 * 
	 * @param marito Il marito.
	 * @param moglie La moglio.
	 */
	public void addConiuge(Persona marito, Persona moglie) {
		if (marito.getSesso() == Sesso.UOMO && moglie.getSesso() == Sesso.DONNA) {
			Graphs.addEdgeWithVertices(albero, marito, moglie, MOGLIE);
			Graphs.addEdgeWithVertices(albero, moglie, marito, MARITO);
		} else if (marito.getSesso() == Sesso.DONNA && moglie.getSesso() == Sesso.UOMO) {
			// Se moglie e marito sono stati passati in modo inverso
			Graphs.addEdgeWithVertices(albero, marito, moglie, MARITO);
			Graphs.addEdgeWithVertices(albero, moglie, marito, MOGLIE);
		}
	}
	
	public void getConiugi(Persona coniuge) {
		List<Persona> coniugi = Graphs.successorListOf(albero, coniuge);
		for (Persona prs : coniugi) {
			DefaultWeightedEdge arco = albero.getEdge(coniuge, prs);
		}
	}
	
	public void getMogli(Persona uomo) {
		if (uomo.getSesso() == Sesso.UOMO) {
			
		}
		
	}
	
	public List<Persona> getFigli(Persona persona) {
		List<Persona> figli = new ArrayList<>();
		Set<DefaultWeightedEdge> archi = albero.outgoingEdgesOf(persona);
		for (DefaultWeightedEdge arco : archi) {
			if (albero.getEdgeWeight(arco) == FIGLIO) {
				figli.add(albero.getEdgeTarget(arco));
			}
		}
		return figli;
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
	
	public Graph<Persona, DefaultWeightedEdge> getAlbero() {
		return albero;
	}

}
