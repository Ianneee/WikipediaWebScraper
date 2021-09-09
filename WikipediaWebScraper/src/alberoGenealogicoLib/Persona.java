package alberoGenealogicoLib;

/**
 * Questa classe rappresenta una persona.
 * Può contentenere altre persone che sono i coniugi, i figli ed i genitori.
 * 
 * La classe, essendo strutturata per un progetto di esame universitario, non prende in considerazione
 * la liquidità sessuale per poter modellare con semplicità e rapidità le richieste del progetto.
 * 
 * @author Ian Tirso Cini
 *
 */
public class Persona {
	
	/**
	 * Enumerator per il sesso della persona: uomo o donna.
	 * 
	 * @author Ian Tirso Cini
	 *
	 */
	public enum Sesso{
		UOMO,
		DONNA
	}
	
	/**
	 * Il nome della persona.
	 */
	private String nome;
	
	/**
	 * Il sesso della persona.
	 */
	private Sesso sesso;
	
	
	/**
	 * Crea una persona, passare il nome ed il sesso come argomento.
	 * 
	 * @param nome Il nome della persona.
	 */
	public Persona(String nome, Sesso sesso) {
		this.nome = nome;
		this.sesso = sesso;
	}
	
	/**
	 * Ritorna il nome di questa persona.
	 * 
	 * @return Il nome di questa persona.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Ritorna il sesso della persona.
	 * 
	 * @return Il sesso.
	 */
	public Sesso getSesso() {
		return sesso;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
}
