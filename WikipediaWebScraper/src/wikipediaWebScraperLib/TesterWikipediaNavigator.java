package wikipediaWebScraperLib;

public class TesterWikipediaNavigator {
	
	
	public static void main(String args[]) {

		
		// Test per url errato
		System.out.println("Test url errato - Expected: Url di Wikipedia passato in formato errato, non inizia con https://it.wikipedia.org/wiki/");
		
		WikipediaNavigator wn = new WikipediaNavigator();
		
		String sorgente = null;
		try {
			
		sorgente = wn.getHtmlPagina("www.google.it");
		
		} catch (WikipediaUrlErratoException error) {
			
			error.printStackTrace();
			
		}
		
		
		// Test pagina Giulio Cesare
		try {
				
		sorgente = wn.getHtmlPagina("https://it.wikipedia.org/wiki/Gaio_Giulio_Cesare");
		
		} catch (WikipediaUrlErratoException error) {
			
			error.printStackTrace();
			
		}
		
		System.out.println("Sorgente parziale expected: <html class=\"client-js ve-available\" lang=\"it\" dir=\"ltr\"><head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Gaio Giulio Cesare - Wikipedia</title> [...]");
		System.out.println("\n" + sorgente.substring(0, 500) + "\n");
		
		
		// Test pagina wikipedia casuale
		try {
			
		sorgente = wn.getHtmlPagina("https://it.wikipedia.org/wiki/Informatica");
		
		} catch (WikipediaUrlErratoException error) {
			error.printStackTrace();
		}
		
		System.out.println("Sorgente parziale expected:<html class=\"client-js ve-available\" lang=\"it\" dir=\"ltr\"><head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Informatica - Wikipedia</title> [...]");
		System.out.println("\n" + sorgente.substring(0, 500) + "\n");
		
		
		// Chiusura web browser
		wn.closeBrowser();

	}

}
