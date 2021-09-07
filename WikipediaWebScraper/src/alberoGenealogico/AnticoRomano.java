package alberoGenealogico;

public class AnticoRomano extends Persona {

	private String urlWikipedia;
	private String urlFoto;
	private String dataNascita;
	private String dataMorte;
	private Boolean adottato;
	private String dinastia;
	
	/**Costruisce un oggetto Antico Romano che fa riferimento ad una persona
	 * fornendo come parametro il nome del personaggio storico.
	 * @param nome Il nome del personaggio storico.
	 */
	public AnticoRomano(String nome) {
		super(nome);
	}

	/**Ritorna l'indirizzo di Wikipedia del personaggio storico.
	 * @return Url wikipedia.
	 */
	public String getUrlWikipedia() {
		return urlWikipedia;
	}

	/**Registra l'indirizzo url di Wikipedia del personaggio storico.
	 * @param urlWikipedia Indirizzo url di wikipedia.
	 */
	public void setUrlWikipedia(String urlWikipedia) {
		this.urlWikipedia = urlWikipedia;
	}

	/**Ritorna l'indirizzo url Wikipedia della foto del personaggio storico.
	 * @return Url wikipedia della foto.
	 */
	public String getUrlFoto() {
		return urlFoto;
	}

	/**Registra l'indirizzo url di Wikipedia della foto del personaggio storico.
	 * @param urlFoto
	 */
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	/**Ritorna la data di nascita del personaggio storico in una stringa.
	 * @return La data di nascita.
	 */
	public String getDataNascita() {
		return dataNascita;
	}

	/**Registra la data di nascita del personaggio storico in una stringa.
	 * @param dataNascita La data di nascita.
	 */
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	/**Ritorna la data di morte del personaggio storico in una stringa.
	 * @return La data di morte.
	 */
	public String getDataMorte() {
		return dataMorte;
	}

	/**Registra la data di morte del personaggio storico.
	 * @param dataMorte La data di morte.
	 */
	public void setDataMorte(String dataMorte) {
		this.dataMorte = dataMorte;
	}

	/**Ritorna un valore boolean per indicare se il personaggio storico � stato adottato.
	 * @return Boolean se la persona � stata adottata.
	 */
	public Boolean getAdottato() {
		return adottato;
	}

	/**Settare su false se il personaggio storico � stato adottato
	 * false altrimenti.
	 * @param adottato Booleano true o false.
	 */
	public void setAdottato(Boolean adottato) {
		this.adottato = adottato;
	}

	/**Ritorna la dinastia a cui appartiene il personaggio storico.
	 * @return La dinastia.
	 */
	public String getDinastia() {
		return dinastia;
	}

	/**Registra la dinastia del personaggio storico.
	 * @param dinastia Il nome della dinastia.
	 */
	public void setDinastia(String dinastia) {
		this.dinastia = dinastia;
	}
	
	public String toString() {
		return "-------------------------------\n" + 
		           "Nome del personaggio storico: " + this.getName() + "\n" + 
			       "Nome del padre: " + this.getFatherName() + "\n" +
				   "Numero figli (direzione principale): " + this.countSons() + "\n" + 
				   "Elementi nella direzione secondaria: " + this.countParent() + "\n" +
				   "Tipo classe: AnticoRomano \n";
	}
}
