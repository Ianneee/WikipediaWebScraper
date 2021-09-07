import wikipediaWebScraper.WikipediaWebPage;
import wikipediaWebScraper.WikipediaNavigator;
import java.util.List;
import wikipediaWebScraper.BrowserErratoException;


/**
 * La classe rappresenta la pagina Wikipedia dove sono elencate tutte le dinastie degli imperatori romani
 * (https://it.wikipedia.org/wiki/Imperatori_romani).
 * Viene utilizzato il design pattern del singleton perchè questa pagina è unica e per lo scraping
 * della pagina web è sufficiente che sia eseguito una sola volta.
 * La classe contiente una List con tutte le dinastie ed i rispettivi imperatori presenti nella pagina.
 * 
 * @author Ian Tirso Cini
 *
 */
public class WikiImperatoriRomaniPagina extends WikipediaWebPage {
	
	/**
	 * Istanza della pagini di Wikipedia sugli imperatori romani.
	 */
	private static WikiImperatoriRomaniPagina instance = null;

	/**
	 * TabellaWikipedia contente le dinastie presenti all'interno della pagina.
	 */
	private List<TabellaDinastie> dinastie;

	/**
	 * Il titolo della pagina Wikipedia.
	 */
	private static final String TITOLO_PAGINA = "Dinastie imperatori romani";

	/**
	 * L'url della pagina Wikipedia
	 */
	private static final String URL_PAGINA = "https://it.wikipedia.org/wiki/Imperatori_romani";
	
	/**
	 * Ritorna l'istanza della pagina Wikipedia con gli imperatori romani.
	 * 
	 * @return La pagina che rappresenta gli imperatori romani.
	 */
	public static WikiImperatoriRomaniPagina getInstance() {
		if (instance == null) {
			instance = new WikiImperatoriRomaniPagina();
			instance.analisiSorgentePagina();
		}
		return instance;
	}
	
	/**
	 * Costruttore privato per l'utilizzo del design pattern singleton.
	 * Al super costruttore vengono passati l'url della pagina corretta ed
	 * il titolo.
	 */
	private WikiImperatoriRomaniPagina() {
		super(URL_PAGINA, TITOLO_PAGINA);
	}
	
	/**
	 * Ritorna il titolo della pagina.
	 * 
	 * @return Il titlo della pagina.
	 */
	@Override
	public String getTitle() {
		return super.getTitle();
	}
	
	/**
	 * Ritorna l'url della pagina.
	 * 
	 * @return L'url della pagina.
	 */
	@Override
	public String getUrl() {
		return super.getUrl();
	}
	
	/**
	 * Ritorna la lista contentente tutte le TabellaDinastie con le informazioni
	 * reperite dalla pagina Wikipedia.
	 * 
	 * @return Lista con tutte le dinastie degli imperatori romani.
	 */
	public List<TabellaDinastie> getDinastie(){
		return dinastie;
	}
	
	/**
	 * Metodo privato che effettua lo scraping dalla pagina di Wikipedia e
	 * chiama la classe ParserTabellaDinastie per analizzare il sorgente.	 * 
	 */
	private void analisiSorgentePagina() {
		try {
			
			WikipediaNavigator browser = new WikipediaNavigator("chrome");
//			WikiImperatoriRomaniPagina imperatori = WikiImperatoriRomaniPagina.getInstance();
			String sorgente = browser.getHtmlPagina(URL_PAGINA);
			browser.closeBrowser();
			ParserTabellaDinastie parser = new ParserTabellaDinastie();
			List<TabellaDinastie> dinastie = parser.analisiSorgente(sorgente);
			this.dinastie = dinastie;
			
		} catch (BrowserErratoException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ritorna un array di String con il nome di tutte le dinastie presenti
	 * nella pagina Wikipedia.
	 * 
	 * @return L'array con l'elenco delle dinastie.
	 */
	public String[] getElencoDinastie() {
		String[] elencoDinastie = new String[dinastie.size()];
		
		for (int i = 0; i < dinastie.size(); i++) {
			elencoDinastie[i] = dinastie.get(i).getNomeDinastia();
		}
		
		return elencoDinastie;
	}
	
	/**
	 * Ritorna la dinastia desiderata passandone il nome come argomento.
	 * Se la dinastia non è presente viene ritornato null.
	 * 
	 * @param nomeDinastia Il nome della dinastia desiderata.
	 * @return TabellaDinastie della dinastia cercata.
	 */
	public TabellaDinastie getDinastia(String nomeDinastia) {
		for (TabellaDinastie dinastia : dinastie) {
			if (dinastia.getNomeDinastia().equals(nomeDinastia)){
				return dinastia;
			}
		}
		return null;
	}
	

}
