
public class RigaTabellaDinastieBuilder {

	private String nomeImperatore;
	private String urlPagina;
	private String dinastia;
	private String urlDinastia;
	
	public RigaTabellaDinastieBuilder nomeImperatore(String nomeImperatore){
		this.nomeImperatore = nomeImperatore;
		return this;
	}
	
	public RigaTabellaDinastieBuilder urlPagina(String urlPagina){
		this.urlPagina = urlPagina;
		return this;
	}
	
	public RigaTabellaDinastieBuilder dinastia(String dinastia){
		this.dinastia = dinastia;
		return this;
	}
	
	public RigaTabellaDinastieBuilder urlDinastia(String urlDinastia){
		this.urlDinastia = urlDinastia;
		return this;
	}
	
	public RigaTabellaDinastie build() {
		return new RigaTabellaDinastie(nomeImperatore, urlPagina, dinastia, urlDinastia);
	}
		
}
