import wikipediaWebScraper.WikipediaNavigator;
import wikipediaWebScraper.BrowserErratoException;

public class AnalizzaPaginaDinastie {
	
	public static void main(String args[]) {
		try {
			WikipediaNavigator browser = new WikipediaNavigator("chrome");
			WikiImperatoriRomaniPagina imperatori = WikiImperatoriRomaniPagina.getInstance();
			String sorgente = browser.getHtmlPagina(imperatori.getUrl());
			browser.closeBrowser();
			System.out.println(sorgente);
			
			
		} catch (BrowserErratoException e) {
			e.printStackTrace();
		}
	}
	

}
