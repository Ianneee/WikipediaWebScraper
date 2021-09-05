import wikipediaWebScraper.RigaTabella;

/**
 * La classe rappresenta una riga delle tabelle contenute nella pagina Wikipedia dove sono
 * elencate tutte le dinastie e imperatori romani.
 * La riga contiene il nome dell'imperatore, il link alla sua pagina internet,
 * la dinastia e il link alla dinastia.
 * 
 * @author Ian Tirso Cini
 *
 */
public class RigaTabellaDinastie implements RigaTabella{
	private String nomeImperatore;
	private String urlPagina;
	private String dinastia;
	private String urlDinastia;
	
	public RigaTabellaDinastie(String nomeImperatore, String urlPagina, String dinastia, String urlDinastia) {
		this.nomeImperatore = nomeImperatore;
		this.urlPagina = urlPagina;
		this.dinastia = dinastia;
		this.urlDinastia = urlDinastia;
	}
	
	public String getNomeImperatore() {
		return this.nomeImperatore;
	}
	
	public String getUrlPagina() {
		return this.urlPagina;
	}
	
	public String getDinastia() {
		return this.dinastia;
	}
	
	public String getUrlDinastia() {
		return this.urlDinastia;
	}

	/**
	 * Builder per la riga della tabella di Wikipedia rappresentante una dinastia.
	 * 
	 * @author Ian Tirso Cini
	 *
	 */
	static class Builder {

		private String nomeImperatore;
		private String urlPagina;
		private String dinastia;
		private String urlDinastia;
		
		public Builder nomeImperatore(String nomeImperatore){
			this.nomeImperatore = nomeImperatore;
			return this;
		}
		
		public Builder urlPagina(String urlPagina){
			this.urlPagina = urlPagina;
			return this;
		}
		
		public Builder dinastia(String dinastia){
			this.dinastia = dinastia;
			return this;
		}
		
		public Builder urlDinastia(String urlDinastia){
			this.urlDinastia = urlDinastia;
			return this;
		}
		
		public RigaTabellaDinastie build() {
			return new RigaTabellaDinastie(nomeImperatore, urlPagina, dinastia, urlDinastia);
		}
			
	}
	
	public static void main(String args[]) {
		Builder builder = new RigaTabellaDinastie.Builder();
		RigaTabellaDinastie nuovaRiga = builder.dinastia("Prova").nomeImperatore("Tizio Caio").urlDinastia("www.www.com").build();
		System.out.println(nuovaRiga.getDinastia());
	}
	
}
