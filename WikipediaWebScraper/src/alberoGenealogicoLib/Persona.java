package alberoGenealogicoLib;

/**
 * Questa classe rappresenta una persona.
 * 
 * @author Ian Tirso Cini
 *
 */
public abstract class Persona implements ThisPersonIs {
	
	/**
	 * Il nome della persona.
	 */
	private String nome;

	/**
	 * Crea una persona, passare il nome ed il sesso come argomento.
	 * 
	 * @param nome Il nome della persona.
	 */
	public Persona(String nome) {
		this.nome = nome;
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
		return nome;
	}
	
}
