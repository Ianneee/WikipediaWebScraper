package iRomani;

import wikipediaWebScraperLib.InfoWikipedia;
import wikipediaWebScraperLib.PaginaWikipedia;
import wikipediaWebScraperLib.PaginaWikipediaBuilder;
import wikipediaWebScraperLib.RigaNonPresenteException;
import wikipediaWebScraperLib.WikipediaNavigator;
import wikipediaWebScraperLib.RigaSinottico.Informazione;
import java.util.List;
import alberoGenealogicoLib.AlberoGenealogico;


public class CostruisciAlberoGenealogico {
	
	private String nomeDinastia;
	private WikipediaNavigator webSurfer;
	private TabellaDinastie dinastia;
	private AlberoGenealogico albero;
	
	/**
	 * Inizializza la classe con il nome di una dinastia presente nella pagina Wikipedia
	 * degli imperatori romani.
	 * Il nome deve essere scritto come nella suddetta pagina, altrimenti viene lanciata
	 * l'eccezzione DinastiaNonTrovataException.
	 * 
	 * @param nomeDinastia Il nome della dinastia.
	 * @throws DinastiaNonTrovataException
	 */
	public CostruisciAlberoGenealogico(String nomeDinastia) throws DinastiaNonTrovataException{
		dinastia = WikiImperatoriRomaniPagina.getInstance().getDinastia(nomeDinastia);
	}
	
	public void init() {
		webSurfer = new WikipediaNavigator();
		albero = new AlberoGenealogico(nomeDinastia);
		costruisciAlbero();
	}
	

	public void costruisciAlbero() {
		// Prendi il primo imperatore, sarà il punto di accessso.
		TabellaDinastie.Riga primoImperatore = dinastia.getRiga(1);
		String urlPrimoImperatore = primoImperatore.getUrlPagina();
		
		// Recupero il sorgente del primo imperatore
		String sorgente = webSurfer.getHtmlPagina(urlPrimoImperatore);
		
		// Costruisco la pagina Wikipedia
		PaginaWikipedia wikiImperatore = costruisciPaginaWikipedia(sorgente);
		
		AnticoRomano imperatore = costruisciRomano(wikiImperatore);
		imperatore.setImperatore();
		cercaFigli(wikiImperatore);
		
	}
	
	/**
	 * Costruisce la pagina Wikipedia corrispondente al sorgente passato
	 * come argomento.
	 * 
	 * @param sorgente Sorgente di una pagina Wikipedia.
	 * @return L'oggetto PaginaWikipedia corrispondente.
	 */
	public static PaginaWikipedia costruisciPaginaWikipedia(String sorgente) {
		PaginaWikipediaBuilder builder = new PaginaWikipediaBuilder();
		PaginaWikipedia paginaWiki = builder.
								     	titoloPagina(InfoWikipedia.titoloPagina(sorgente)).
									    urlImmagine(InfoWikipedia.urlImmagine(sorgente)).
									    url(InfoWikipedia.urlPagina(sorgente)).
										sinottico(InfoWikipedia.sinottico(sorgente)).
										build();
		return paginaWiki;
	}
	
	/**
	 * Costruisce l'AnticoRomano dalla PaginaWikipedia passata.
	 * 
	 * @param wiki La pagina Wikipedia.
	 * @return L'AnticoRomano costruito dai dati della pagina Wikipedia.
	 */
	public static AnticoRomano costruisciRomano(PaginaWikipedia wiki) {
		String nome = wiki.getTitle();
		String url = wiki.getUrl();
		
		AnticoRomano romano = new AnticoRomano(nome, url);
		return romano;
	}
	
	/**
	 * Per la pagina Wikipedia passata ricava l'informazione sui figli, se
	 * presente.
	 * 
	 * @param wiki La pagina Wikipedia.
	 * @return La lista con i figli.
	 */
	public static List<Informazione> cercaFigli(PaginaWikipedia wiki) {
		try {
			List<Informazione> info = wiki.getInformazioneSinottico("Figli");
			for (Informazione figlio : info) {
				
			}
			return info;
		} catch (RigaNonPresenteException error){
			return null;
		}
	}
	
	public static void main(String[] args) {
		try {
			CostruisciAlberoGenealogico albero = new CostruisciAlberoGenealogico("Dinastia GIULIO-claudia");
		} catch (DinastiaNonTrovataException error) {
			error.printStackTrace();
		}
	}
}
