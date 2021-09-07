package wikipediaWebScraperLib;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * La classe rappresenta una riga del sinottico di Wikipedia. La cella sinistra
 * è rappresentata dalla categoria, le informazioni della cella destra vengono
 * raccolte in una collection tramite una classe di tipo Informazione.
 * L'aggiunta di informazioni può essere fatta solo tramite questa classe.
 * 
 * @author Ian Tirso Cini
 * 
 */
public class RigaSinottico implements RigaTabella, Iterable<RigaSinottico.Informazione> {
	
	/**
	 * La categoria ovvero la cella sinistra della riga
	 */
	private String categoria;
	
	/**
	 * Le informazioni contenute nella cella destra della riga
	 */
	private List<Informazione> cellaDestra;
	
	/**
	 * Costruisce la riga sinottico dalla categoria (l'informazione contenuta nella cella sinistra).
	 * 
	 * @param categoria La categoria dell'informazione contenuta nella riga.
	 */
	public RigaSinottico(String categoria) {
		this.categoria = categoria;
		this.cellaDestra = new ArrayList<>();
	}
	
	/**
	 * Ritorna il nome della categoria dell'informazione nella riga (cella sinistra).
	 * @return La categoria della riga.
	 */
	public String getCategoria() {
		return categoria;
	}
	
	/**
	 * Ritorna la lista con le informazioni contenute nella cella destra della riga.
	 * @return List con le informazioni.
	 */
	public List<Informazione> getInformazioni(){
		return cellaDestra;
	}
	
	/**
	 * L'iteratore delle informazioni contenute nella cella destra.
	 */
	@Override
	public Iterator<Informazione> iterator(){
		return cellaDestra.iterator();
	}
	
	/**
	 * Aggiunge un oggetto che rappresenta un'informazione contenuta nella
	 * cella destra della riga del sinottico.
	 * 
	 * @param nomeInfo L'informazione.
	 */
	public void addInformazione(String nomeInfo) {
		cellaDestra.add(new Informazione(nomeInfo));
	}
	
	/**
	 * Aggiunge un oggetto che rappresenta un'informazione contenuta nella cella
	 * destra della riga del sinottico con il suo url che inizi con https://it.wikipedia.org/wiki.
	 * @param nomeInfo L'informazione.
	 * @param url Il link dell'informazione.
	 */
	public void addInformazione(String nomeInfo, String url) {
		cellaDestra.add(new Informazione(nomeInfo, url));
	}
	
	/**
	 * Aggiunge il link all'informazione passata come argomento se presente.
	 * L'url per essere valido deve cominciare con https://it.wikipedia.org/wiki .
	 * @param nomeInfo L'informazione.
	 * @param url Il link dell'informazione.
	 */
	public void addUrl(String nomeInfo, String url) {
		for (Informazione info : cellaDestra) {
			if (info.getNomeInfo().equals(nomeInfo)) {
				info.setUrl(url);
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ Categoria informazione: " + categoria + " ]\n");
		for (Informazione info : cellaDestra) {
			sb.append(info.toString() + "\n");			
		}
		return sb.toString();
	}

	
	/**
	 * La classe rappresenta una informazione presente nella cella destra di una riga del sinottico.
	 * La classe è intesa da usare associata a quella InfoSinottico che rappresenta
	 * l'intera riga.
	 * Per ogni informazione c'è un campo nomeInfo e uno url che contiene il link se 
	 * presente.
	 * 
	 * @author Ian Tirso Cini
	 *
	 */
	public class Informazione {

		/**
		 * Il nome dell'informazione.
		 */
		private String nomeInfo;

		/**
		 * Url a cui punta l'informazione.
		 */
		private String url;
		
		/**
		 * Costruttore solo con il nome dell'informazione.
		 * 
		 * @param nomeInfo L'informazione.
		 */
		public Informazione(String nomeInfo) {
			this.nomeInfo = nomeInfo;
			this.url = null;
		}
		
		/**
		 * Costruttore con nome dell'informazione e link.
		 * Il link per essere valido deve cominciare con https://it.wikipedia.org/wiki .
		 * 
		 * @param nomeInfo Nome dell'informazione.
		 * @param url link dell'informazione.
		 */
		public Informazione(String nomeInfo, String url) {
			this.nomeInfo = nomeInfo;
			setUrl(url);
		}
		
		/**
		 * Salva l'url passato come parametro.
		 * L'url per essere valido deve cominciare con https://it.wikipedia.org/wiki .
		 * 
		 * @param url Il link.
		 */
		public void setUrl(String url) {
			if (url.startsWith("https://it.wikipedia.org/wiki")) {
				this.url = url;
			} else {
				this.url = null;
			}
		}
		
		/**
		 * Ritorna la stringa con l'informazione rappresentata.
		 *
		 * @return L'informazione.
		 */
		public String getNomeInfo() {
			return nomeInfo;
		}
		
		/**
		 * Ritorna la stringa con l'url associato a questa informazione.
		 * 
		 * @return Il link.
		 */
		public String getUrl() {
			return url;
		}
		
		@Override
		public String toString() {
			return "Info: " + nomeInfo + " | " + "Url: " + url;
		}
	}
	
}
