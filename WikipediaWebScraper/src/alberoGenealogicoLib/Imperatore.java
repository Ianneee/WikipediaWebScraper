package alberoGenealogicoLib;

public class Imperatore extends AnticoRomano {
	
	private String dataImpero;
	private Imperatore predecessore;
	private Imperatore successore;
	
	/**Costruisce un oggetto imperatore fornendo come paramentro il nome.
	 * @param nome Il nome dell'imperatore.
	 */
	public Imperatore(String nome) {
		super(nome);
	}

	/**Ritorna la data del regno dell'imperatore.
	 * @return La data del regno.
	 */
	public String getDataImpero() {
		return dataImpero;
	}

	/**Registra la data dell'impero dell'imperatore.
	 * @param dataImpero La data del regno.
	 */
	public void setDataImpero(String dataImpero) {
		this.dataImpero = dataImpero;
	}

	/**Ritorna l'oggetto con il precedente imperatore.
	 * @return Il precedente imperatore.
	 */
	public Imperatore getPredecessore() {
		return predecessore;
	}

	/**Registra il precedente imperatore come oggetto imperatore.
	 * @param predecessore L'oggetto imperatore predecedente. 
	 */
	public void setPredecessore(Imperatore predecessore) {
		this.predecessore = predecessore;
	}

	/**Ritorna l'oggetto con l'iperatore successivo.
	 * @return L'oggetto imperatore successivo.
	 */
	public Imperatore getSuccessore() {
		return successore;
	}

	/**Registra l'imperatore successivo come oggetto imperatore.
	 * @param successore L'oggetto imperatore successivo.
	 */
	public void setSuccessore(Imperatore successore) {
		this.successore = successore;
	}
	
	
}
