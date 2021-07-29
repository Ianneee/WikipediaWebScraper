package webScraper;

import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Classe per aprire pagine di Wikipedia Italia.
 * Le pagine possono essere aperte fornendo l'url completo tramite un metodo.
 * Si pu� estrarre il codice sorgente della pagina aperta ed analizzaro per ricavarne 
 * le informazioni principali.
 * 
 * @author Ian Tirso Cini
 *
 */
public class WikipediaScraper {
	private WebDriver webDriver;
	
	public WikipediaScraper(String browser) throws BrowserErratoException {
		if (browser.toLowerCase().equals("chrome")) {
			buildChrome();
		} else if (browser.toLowerCase().equals("firefox")) {
			buildFireFox();
		} else {
		throw new BrowserErratoException("Hai scelto un browser non supportato.");
		}
	}
	
	/**
	 * Utilizza Chrome come browser di navigazione.
	 */
	private void buildChrome() {
		System.setProperty("webdriver.chrome.driver",  "res/chromedriver.exe");
		webDriver = new ChromeDriver();
	}
	
	/**
	 * Utilizza Firefox come browser di navigazione.
	 */
	private void buildFireFox() {
		// TODO
	}
	
	/**
	 * Apre l'url di Wikipedia fornito in input. L'url deve cominciare con https://it.wikipedia.org/wiki/
	 * per essere considerato un url valido.
	 * @param url di Wikipedia da aprire.
	 */
	public void openUrl(String url) {
		if (url.startsWith("https://it.wikipedia.org/wiki/")) {
			webDriver.get(url);
		} else {
			// TODO lanciare un errore
			webDriver.get("https://en.wikipedia.org/wiki/Failure");
		}
	}

	/**
	 * Ritorna un oggetto di tipo PaginaWikipedia con le informazioni della pagina aperta nel browser.
	 * @return Un oggetto PaginaWikipedia.
	 */
	public PaginaWikipedia analizzaPagina() {
		return new PaginaWikipedia(htmlPagina());
	}
	
	/**
	 * Ritorna un oggetto di tipo PaginaWikipedia con le informazioni della pagina Wikipedia passata
	 * come argomento.
	 * L'url deve cominciare con https://it.wikipedia.org/wiki/ per essere considerato valido.
	 * @param url della pagina Wikipedia.
	 * @return Un oggetto PaginaWikipedia.
	 */
	public PaginaWikipedia anaizzaPagina(String url) {
		openUrl(url);
		return new PaginaWikipedia(htmlPagina());
	}
	
	/**
	 * Ritorna il sorgente html della pagina aperta dal web driver.
	 * @return Il codice html della pagina.
	 */
	public String htmlPagina() { return webDriver.getPageSource();
	}
	
	public static void main(String[] args) {
		try {
		WikipediaScraper ws = new WikipediaScraper("Chrome");
//		ws.openUrl("https://it.wikipedia.org/wiki/Tolomeo_XV");
		ws.openUrl("https://it.wikipedia.org/wiki/Settimio_Severo");
//		ws.openUrl("https://it.wikipedia.org/wiki/Dittatore_(storia_romana)");
//		ws.pageHtml();
		PaginaWikipedia sin = ws.analizzaPagina();
		System.out.println(sin.getSinottico());
		System.out.println(sin.getTitle());
		System.out.println(sin.getUrl());
		System.out.println(sin.getUrlImmagine());
		Supplier<ParserSinottico> supp = ParserSinotticoWikipedia::new;
		sin.parsaSinottico(supp);
//		System.out.println(ws.estraiSinottico("https://it.wikipedia.org/wiki/Augusto").getSinottico());
		} catch (BrowserErratoException e) {
			e.printStackTrace();
		}
	}

}
