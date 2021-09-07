package alberoGenealogicoLib;

import java.util.List;
import java.util.ArrayList;

/**
 * Questa classe rappresenta una persona.
 * Può contentenere altre persone che sono i coniugi, i figli ed i genitori.
 * 
 * @author Ian Tirso Cini
 *
 */
public class Persona {
	
	/**
	 * Il nome della persona.
	 */
	private String nome;
	
	/**
	 * Lista con i coniugi.
	 */
	private List<Persona> coniugi;
	
	/**
	 * Lista con i figli.
	 */
	private List<Persona> figli;
	
	/**
	 * La Persona padre.
	 */
	private Persona padre;
	
	/**
	 * La Persona madre.
	 */
	private Persona madre;
	
	/**
	 * Crea una persona, passare il nome come argomento.
	 * 
	 * @param nome Il nome della persona.
	 */
	public Persona(String nome) {
		this.nome = nome;
		coniugi = new ArrayList<Persona>();
		figli = new ArrayList<Persona>();
	}
	
	/**
	 * Aggiunge il padre della persona.
	 * 
	 * @param padre Il padre di questa persona.
	 */
	public void aggiungiPadre(Persona padre) {
		this.padre = padre;
	}
	
	/**
	 * Ritorna il padre di questa persona se presente.
	 * 
	 * @return Il padre di questa persona.
	 */
	public Persona getPadre() {
		return this.padre;
	}
	
	/**
	 * Ritorna il nome del padre di questa persona.
	 * 
	 * @return Il nome del padre di questa persona.
	 */
	public String getNomePadre() {
		if (padre != null) {
			return getPadre().getNome();
		} else {
			return null;
		}
	}
	
	/**
	 * Aggiungi un figlio a questa persona.
	 * 
	 * @param figlio Il figlio da aggiungere.
	 */
	public void aggiungiFiglio(Persona figlio) {
		figli.add(figlio);
	}
	
	/**
	 * Aggiungi un figlio a questa persona ed aggiungi questa persone come suo padre.
	 * 
	 * @param figlio Il figlio da aggiungere
	 */
	public void aggiungiFiglioConPadre(Persona figlio) {
		figli.add(figlio);
		figlio.aggiungiPadre(this);
	}

	/**
	 * Aggiunge un coniuge alla persona.
	 * 
	 * @param coniuge Il coniuge da aggiungere.
	 */
	public void aggiungiConiuge(Persona coniuge) {
		coniugi.add(coniuge);
		coniuge.aggiungiConiuge(this);
	}
	
	/**
	 * Ritorna il numero di figli.
	 * 
	 * @return Il numero di figli.
	 */
	public int totaleFigli() {
		return figli.size();
	}
	
	/**
	 * Ritorna il numero di coniugi. 
	 * 
	 * @return Il numero di coniugi.
	 */
	public int countParent() {
		return coniugi.size();
	}
	
	/**
	 * Ritorna una List con i figli di questa persona.
	 * 
	 * @return La List con i figlio.
	 */
	public List<Persona> getListaFigli() {
		return figli;
	}
	
	/**
	 * Ritorna una List con tutti i coniugi di questa persona.
	 * 
	 * @return La List con i coniugi.
	 */
	public List<Persona> getParentsRootArray() {
		return coniugi;
	}
	
	/**
	 * Ritorna il nome di questa persona.
	 * 
	 * @return Il nome di questa persona.
	 */
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(nome + " | ");
		if (madre != null) {
			sb.append("Madre: " + madre.getNome() + " | ");
		} else {
			sb.append("Madre: null | ");
		}
		if (padre != null) {
			sb.append("Padre: " + padre.getNome());
		} else {
			sb.append("Padre: null | ");
		}
		sb.append("Totale figli: " + figli.size());

		return sb.toString();
	}
	
	/**Metodo statico per la visita in ordine dell'albero creato nella direzione principale.
	 * Printa il metodo toString() con le informazioni sui dati contenuti nel nodo.
	 * @param radice La radice dell'albero da navigare. 
	 */
	public static void visitPreOrder(Persona radice) {
		System.out.println(radice.toString());
//		System.out.println(radice.getName());
		if (radice.totaleFigli() > 0) {
			for (Persona nodo : radice.getListaFigli()) {
				visitPreOrder(nodo);
			}
		}
	}
}
