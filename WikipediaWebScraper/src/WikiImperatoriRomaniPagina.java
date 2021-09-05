import wikipediaWebScraper.TabellaWikipedia;
import wikipediaWebScraper.WikipediaWebPage;


public class WikiImperatoriRomaniPagina extends WikipediaWebPage {
	
	private static WikiImperatoriRomaniPagina instance = null;
	private TabellaWikipedia dinastie;
	private final String TITOLO_PAGINA = "Dinastie imperatori romani";
	private final String URL_PAGINA = "https://it.wikipedia.org/wiki/Imperatori_romani";
	
	private WikiImperatoriRomaniPagina(TabellaWikipedia dinastie) {
		super(null, null);
		this.dinastie = dinastie;
	}
	
	private WikiImperatoriRomaniPagina() {
		super(null, null);
	}
	
	public static WikiImperatoriRomaniPagina getInstance() {
		if (instance == null) {
			instance = new WikiImperatoriRomaniPagina();
		}
		return instance;
	}
	
	public static WikiImperatoriRomaniPagina getInstance(TabellaWikipedia dinastie) {
		if (instance == null) {
			instance = new WikiImperatoriRomaniPagina(dinastie);
		}
		return instance;
	}
	
	@Override
	public String getTitle() {
		return TITOLO_PAGINA;
	}
	
	@Override
	public String getUrl() {
		return URL_PAGINA;
	}
	
	public TabellaWikipedia getTabellaDinastie() {
		return dinastie;
	}

}
