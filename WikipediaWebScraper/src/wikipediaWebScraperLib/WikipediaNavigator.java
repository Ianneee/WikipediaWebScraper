package wikipediaWebScraperLib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Classe per estrarre il sorgente Html da una pagina Wikipedia scelta.
 * Possono essere richieste più pagine in maniera sequenziale con la stessa 
 * istanza dell'oggetto.
 * 
 * @author Ian Tirso Cini
 * 
 */
public class WikipediaNavigator {
	
	/**
	 * Il WebDriver che permette la navigazione.
	 */
	private WebDriver webDriver;
	
	/**
	 * Costruisce il WikipediaNavigator utilizzando il browser Chrome.
	 * Viene aperta una finestra del browser.
	 */
	public WikipediaNavigator() {
		buildChrome();
	}
	
	/**
	 * Utilizza Chrome come browser di navigazione e lancia il browser.
	 */
	private void buildChrome() {
		System.setProperty("webdriver.chrome.driver",  "res/chromedriver.exe");
		webDriver = new ChromeDriver();
	}

	/**
	 * Ritorna String contenente il sorgente della pagina Wikipedia fornito come parametro.
	 * L'url fornito per essere valido deve essere nella forma https://it.wikipedia.org/wiki/NOME-PAGINA
	 * Se viene fornito un indirizzo errato il metodo ritorna null.
	 * 
	 * @param url Url della pagina Wikipedia.
	 * @return Il sorgente della pagina aperta.
	 * @throws WikipediaUrlErratoException Errore lanciato per url errato.
	 */
	public String getHtmlPagina(String url) throws WikipediaUrlErratoException{
		if (urlValido(url)) {
			webDriver.get(url);
			return webDriver.getPageSource();
		} else {
			throw new WikipediaUrlErratoException();
		}
	}
	
	/**
	 * Controlla se l'url fornito è nel formato corretto.
	 * 
	 * @param url Url di wikipedia che cominci con https://it.wikipedia.org/wiki/
	 * @return Boolean di conferma.
	 */
	private boolean urlValido(String url) {
		if (url.startsWith("https://it.wikipedia.org/wiki/")) {
			return true;
		} 
		return false;
	}
	
	/**
	 * Chiude il browser aperto.
	 */
	public void closeBrowser() {
		webDriver.close();
	}

}
