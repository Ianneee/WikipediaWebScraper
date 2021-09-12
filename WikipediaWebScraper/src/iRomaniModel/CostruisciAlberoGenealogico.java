package iRomaniModel;

import wikipediaWebScraperLib.InfoWikipedia;
import wikipediaWebScraperLib.PaginaWikipedia;
import wikipediaWebScraperLib.PaginaWikipediaBuilder;
import wikipediaWebScraperLib.RigaNonPresenteException;
import wikipediaWebScraperLib.WikipediaNavigator;
import wikipediaWebScraperLib.RigaSinottico.Informazione;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import alberoGenealogicoLib.AlberoGenealogico;


/**
 * La classe permette per una dinastia degli imperatori romani scelta di creare un oggetto di tipo
 * AlberoGenealogico.
 * 
 * @author Ian Tirso Cini
 *
 */
public class CostruisciAlberoGenealogico {
	
	/**
	 * Il nome della dinastia.
	 */
	private String nomeDinastia;
	
	/**
	 * Il WikipediaNavigator per aprire le pagine web.
	 */
	private WikipediaNavigator webSurfer;
	
	/**
	 * La TabellaDinastie della dinastia scelta.
	 */
	private TabellaDinastie dinastia;
	
	/**
	 * Mappa con gli url degli imperatori della dinastia.
	 */
	private LinkedHashMap<String, Integer> urlImperatori;
	
	/**
	 * Gli alberi genealogici della dinastia.
	 */
	private List<AlberoGenealogico> alberiGenealogici;
	
	/**
	 * Lista con le parole di casi particolari da evitare.
	 */
	private List<String> paroleVietate = List.of("adottivi:", "morta a 4 mesi", "nessuno", 
											"e della nipote", "nessuno", "adottivo", ", nato nel",
											"217");
	
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
		urlImperatori(dinastia.getUrlImperatori());
		nomeDinastia = dinastia.getNomeDinastia();
	}
	
	/**
	 * Da il via allo scraping delle pagine Wikipedia della dinastia scelta.
	 */
	public void init() {
		webSurfer = new WikipediaNavigator();
		alberiGenealogici = new ArrayList<>();
		costruisciAlberi();
		webSurfer.closeBrowser();
	}
	
	/**
	 * Ritorna la lista con gli alberi genealogici della dinastia.
	 * 
	 * @return La lista con gli alberi genealogici.
	 */
	public List<AlberoGenealogico> getAlberiGenealogici(){
		if (alberiGenealogici == null) {
			init();
		}
		return alberiGenealogici;
	}
	
	/**
	 * Crea la Map con la lista degli url per ogni imperatore e un Integer a 0
	 * che serve da segnalino di controllo per verificare che ne sia stato costruito
	 * l'albero genealogico.
	 * 
	 * @param listaUrl Gli url degli imperatori della dinastia.
	 */
	private void urlImperatori(List<String> listaUrl) {
		urlImperatori = new LinkedHashMap<String, Integer>();
		for (String url : listaUrl) {
			urlImperatori.put(url, 0);
		}
	}
	
	/**
	 * Controlla ogni imperatore all'interno della mapp urlImperatori;
	 * se il valore della chiave è settato a 0 o l'imperatore è il primo della dinastia
	 * altrimenti non fa parte della linea di sangue dell'imperatore precedente, viene
	 * quindi inserito in un diverso albero genealogico.
	 */
	private void costruisciAlberi() {
		urlImperatori.forEach((key, value) -> {
			if (urlImperatori.get(key) == 0) {
				// Istanzia un albero genealogico per la dinastia
				AlberoGenealogico albero = new AlberoGenealogico(nomeDinastia);
				// Passo l'url dell'imperatore e l'AlberoGenealogico vuoto per la creazione
				AnticoRomano capostipite = costruzioneAlberoRicorsiva(key, albero, 0);
				// Imposto il capostipite dell'albero genealogico.
				albero.setCapostipite(capostipite);
				alberiGenealogici.add(albero);
			}
		});
	}
	
	private AnticoRomano costruzioneAlberoRicorsiva(String urlWikipedia, AlberoGenealogico albero, int profondita) {
		// Da url di wiki preleva il sorgente e costruisce la pagina
		String sorgente = webSurfer.getHtmlPagina(urlWikipedia);
		PaginaWikipedia wikiPersonaggio = costruisciPaginaWikipedia(sorgente);
		// Dalla pagina costruisce l'antico romano
		AnticoRomano romano = costruisciRomano(wikiPersonaggio);
		// Controllo se AnticoRomano appena istanziato è stato un imperatore
		if (urlImperatori.containsKey(romano.getUrl())) {
			romano.setImperatore();
			// Nella mappa degli url metto l'imperatore visitato ad 1
			urlImperatori.replace(romano.getUrl(), 1);
		}
		// Cerca i figli dalla PaginaWikipedia e ritorna la lista di questi.
		List<Informazione> wikiInfoFigli = cercaFigli(wikiPersonaggio);
		// Se ci sono figli, per ognuno passa l'url a costruzioneAlberoRicorsiva
		// Al ritorno crea parentela nell'albero con la persona ritornata
		if (wikiInfoFigli != null) {
			for(Informazione informazione : wikiInfoFigli) {
				System.out.println(informazione);
				if (informazione.getUrl() != null) {
					AnticoRomano figlio = costruzioneAlberoRicorsiva(informazione.getUrl(), albero, profondita+1);
					aggiungiFiglio(albero, romano, figlio);
				} else if (!paroleVietate.contains(informazione.getNomeInfo().toLowerCase())){
					AnticoRomano figlio = new AnticoRomano(informazione.getNomeInfo(), informazione.getNomeInfo());
					aggiungiFiglio(albero, romano, figlio);
					albero.aggiungiAGenerazione(profondita+1, figlio);
				}
			}
		}
		// Return dell'AnticoRomano creato
		albero.aggiungiAGenerazione(profondita, romano);
		return romano;
	}
	
	/**
	 * Costruisce la pagina Wikipedia corrispondente al sorgente passato
	 * come argomento.
	 * 
	 * @param sorgente Sorgente di una pagina Wikipedia.
	 * @return L'oggetto PaginaWikipedia corrispondente.
	 */
	private static PaginaWikipedia costruisciPaginaWikipedia(String sorgente) {
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
	private AnticoRomano costruisciRomano(PaginaWikipedia wiki) {
		String nome = wiki.getTitle();
		if (nome.contains("(")) {
			nome = nome.substring(0, nome.indexOf("(")).trim();
		}
		String url = wiki.getUrl();
		
		AnticoRomano romano = new AnticoRomano(nome, url);
		romano.setUrlImmagine(wiki.getUrlImmagine());
		return romano;
	}
	
	/**
	 * Per la pagina Wikipedia passata ricava l'informazione sui figli, se
	 * presente.
	 * 
	 * @param wiki La pagina Wikipedia.
	 * @return La lista con i figli.
	 */
	private List<Informazione> cercaFigli(PaginaWikipedia wiki) {
		try {
			return wiki.getInformazioneSinottico("Figli");
			} catch (RigaNonPresenteException error){
			return null;
		}
	}
	
	/**
	 * Aggiunge all'albero genealogico una relazione padre figlio.
	 * 
	 * @param padre Il padre.
	 * @param figlio Il figlio.
	 */
	private void aggiungiFiglio(AlberoGenealogico albero, AnticoRomano padre, AnticoRomano figlio) {
		albero.addFiglio(padre, figlio);
	}
	
	
	// Tester interno
	public static void main(String[] args) {
		try {
			CostruisciAlberoGenealogico albero = new CostruisciAlberoGenealogico("Guerra civile romana");
			albero.init();
		} catch (DinastiaNonTrovataException error) {
			error.printStackTrace();
		}
	}

}
