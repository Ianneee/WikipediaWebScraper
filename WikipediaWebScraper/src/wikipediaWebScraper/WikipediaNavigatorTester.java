package wikipediaWebScraper;

public class WikipediaNavigatorTester {
	
	
	public static void main(String args[]) {
		try {
		WikipediaNavigator wn = new WikipediaNavigator("CHRome");
		String sorgente = wn.getHtmlPagina("www.google.it");
		System.out.println("Sorgente expected: null");
		System.out.println("Sorgente:" + sorgente  + "\n");
		
		sorgente = wn.getHtmlPagina("https://it.wikipedia.org/wiki/Gaio_Giulio_Cesare");
		System.out.println("Sorgente parziale expected: <html class=\"client-js ve-available\" lang=\"it\" dir=\"ltr\"><head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Gaio Giulio Cesare - Wikipedia</title> [...]");
		System.out.println("Sorgente: " + sorgente.substring(0, 500) + "\n");
		
		sorgente = wn.getHtmlPagina("https://it.wikipedia.org/wiki/Informatica");
		System.out.println("Sorgente parziale expected:<html class=\"client-js ve-available\" lang=\"it\" dir=\"ltr\"><head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Informatica - Wikipedia</title> [...]");
		System.out.println("Sorgente: " + sorgente.substring(0, 500) + "\n");
		
		wn.closeBrowser();

		} catch (BrowserErratoException e) {
			e.printStackTrace();
		} 
	}

}
