package iRomaniModel;

/**
 * La classe rappresenta un imperatore.
 * 
 * @author Ian Tirso Cini
 *
 */
public class Imperatore extends AnticoRomano{
	
	
	/**
	 * Costruisce con il nome e l'url della pagina Wikipedia
	 * 
	 * @param nome Il nome dell'imperatore.
	 * @param url L'url della pagina Wikipedia.
	 */
	public Imperatore(String nome, String url) {
		super(nome, url);
	}
	
	@Override
	public String toString() {
		return "\uD83D\uDC51\n" + super.getNome();
	}

}
