package iRomaniModel;

import wikipediaWebScraperLib.InfoWikipedia;
import wikipediaWebScraperLib.PaginaWikipedia;
import wikipediaWebScraperLib.PaginaWikipediaBuilder;
import wikipediaWebScraperLib.RigaNonPresenteException;
import wikipediaWebScraperLib.WikipediaNavigator;
import wikipediaWebScraperLib.WikipediaUrlErratoException;
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
	private List<String> paroleVietate = List.of(
			"adottivi:", "morta a 4 mesi", "nessuno", "e della nipote", 
			"nessuno", "adottivo", "adottivo:",", nato nel",
			"217", ", la madre di", "due figli, uno di nome", "da flaccilla",
			"da galla"
			);

	/**
	 * Pagine Wikipedia da non inserire tra i risultati.
	 */
	private List<String> pagineWikiErrate = List.of("217", "Flavia Domitilla");
	
	/**
	 * Dinastia Giulio Cesare.
	 */
	private final String GIULIO_CESARE = "Gens Iulia (49_a.C.-44_a.C.)";
	
	/**
	 * Dinastia Giulio-Claudia.
	 */
	private final String GIULIO_CLAUDIA = "Dinastia giulio-claudia (27_a.C.-68_d.C.)";
	
	/**
	 * Dinastia imperatori adottivi.
	 */
	private final String IMPERATORI_ADOTTIVI = "Imperatori adottivi (96-192)";
	
	/**
	 * Antonio Pio imperatore dinastia imperatori adottivi.
	 */
	private final String IMPERATORE_PIO = "Antonino Pio";
	
	/**
	 * Faustina Minore figlia Antonino Pio.
	 */
	private final String FAUSTINA_MINORE = "Annia Galeria Faustina";
	/**
	 * Inizializza la classe con il nome di una dinastia presente nella pagina Wikipedia
	 * degli imperatori romani.
	 * Il nome deve essere scritto come nella suddetta pagina, altrimenti viene lanciata
	 * l'eccezione DinastiaNonTrovataException.
	 * 
	 * @param nomeDinastia Il nome della dinastia.
	 * @throws DinastiaNonTrovataException
	 */
	public CostruisciAlberoGenealogico(String nomeDinastia) throws DinastiaNonTrovataException{
		dinastia = WikiImperatoriRomaniPagina.getInstance().getDinastia(nomeDinastia);
		
		urlImperatori = new LinkedHashMap<String, Integer>();

		urlImperatori(dinastia.getUrlImperatori());
		nomeDinastia = dinastia.getNomeDinastia();
		

		if (dinastia.getNomeDinastia().equals(GIULIO_CESARE)) {
			
			TabellaDinastie giulioClaudia = WikiImperatoriRomaniPagina.getInstance().getDinastia(GIULIO_CLAUDIA);
			urlImperatori(giulioClaudia.getUrlImperatori());
			
		}
	}
	
	/**
	 * Da il via allo scraping delle pagine Wikipedia della dinastia scelta.
	 */
	public void init() throws WikipediaUrlErratoException{
		// Attiva il browser
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
	private void costruisciAlberi() throws WikipediaUrlErratoException{
		urlImperatori.forEach((key, value) -> {
			
			if (urlImperatori.get(key) == 0) {
				
				// Istanzia un albero genealogico per la dinastia
				AlberoGenealogico albero = new AlberoGenealogico(nomeDinastia);
				
				AnticoRomano capostipite = null;
				
				try {
					
				// Passo l'url dell'imperatore e l'AlberoGenealogico vuoto per la creazione
				capostipite = costruzioneAlberoRicorsiva(key, albero);
				
				} catch (WikipediaUrlErratoException error) {
					new WikipediaUrlErratoException();
				}
				
				capostipite.setImperatore();
				
				// Imposto il capostipite dell'albero genealogico.
				albero.addPersona(capostipite);
				albero.setCapostipite(capostipite);
				alberiGenealogici.add(albero);
			}
		});
	}
	
	private AnticoRomano costruzioneAlberoRicorsiva(String urlWikipedia, AlberoGenealogico albero) throws WikipediaUrlErratoException{
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
		
		// Se la dinastia è quella degli imperatori adottivi
		// Sposto Faustina Minore in fondo alla lista per un effetto grafico migliore
		if (dinastia.getNomeDinastia().equals(IMPERATORI_ADOTTIVI) && romano.getNome().equals(IMPERATORE_PIO)) {

			for (int i = 0; i < wikiInfoFigli.size(); i++) {

				// Prendo l'informazione del sinottico riguardante il figlio 
				Informazione figlio = wikiInfoFigli.get(i);
				
				// Controllo se il nome è quello per Faustina minore
				if (figlio.getNomeInfo().equals(FAUSTINA_MINORE)){

					// La levo dalla lista e la reinserisco al fondo
					wikiInfoFigli.remove(i);
					wikiInfoFigli.add(figlio);
					
					break;
				}
			}
		}
		
		// Se ci sono figli, per ognuno passa l'url a costruzioneAlberoRicorsiva
		// Al ritorno crea parentela nell'albero con la persona ritornata
		if (wikiInfoFigli != null) {
			
			for(Informazione informazione : wikiInfoFigli) {

				if (informazione.getUrl() != null && !pagineWikiErrate.contains(informazione.getNomeInfo())) {

					AnticoRomano figlio = costruzioneAlberoRicorsiva(informazione.getUrl(), albero);
					
					albero.addFiglio(romano, figlio);

				} else if (!paroleVietate.contains(informazione.getNomeInfo().toLowerCase()) && 
						!pagineWikiErrate.contains(informazione.getNomeInfo())){
					
					AnticoRomano figlio = new AnticoRomano(informazione.getNomeInfo(), informazione.getNomeInfo());

					albero.addFiglio(romano, figlio);
					
				}
			}
		}
		
		// Return dell'AnticoRomano creato
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
										sinotticoHtml(InfoWikipedia.estraiSinottico(sorgente)).
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
		
		// Escludi eventuale testo presente tra parentesi
		if (nome.contains("(")) {
			nome = nome.substring(0, nome.indexOf("(")).trim();
		}
		
		String url = wiki.getUrl();
		
		AnticoRomano romano = new AnticoRomano(nome, url);
		
		romano.setUrlImmagine(wiki.getUrlImmagine());
		
		romano.setPaginaWikipedia(wiki);
		
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

}
