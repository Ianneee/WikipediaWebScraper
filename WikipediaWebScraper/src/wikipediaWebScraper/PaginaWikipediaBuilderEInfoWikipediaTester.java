package wikipediaWebScraper;

import java.util.function.Supplier;

public class PaginaWikipediaBuilderEInfoWikipediaTester {
	
	public static void main(String args[]) {
		try {
			WikipediaNavigator wn = new WikipediaNavigator("chrome");
			String sorgente = wn.getHtmlPagina("https://it.wikipedia.org/wiki/Licinio");
//			String sorgente = wn.getHtmlPagina("www.google.it");
			wn.closeBrowser();
			
			Supplier<ParserTabellaWikipedia> parser = ParserSinotticoWikipedia::new; 
			
			PaginaWikipedia paginawpd = new PaginaWikipediaBuilder()
											.titoloPagina(InfoWikipedia.titoloPagina(sorgente))
											.urlImmagine(InfoWikipedia.urlImmagine(sorgente))
											.url(InfoWikipedia.urlPagina(sorgente))
											.sinottico(InfoWikipedia.sinottico(sorgente))
											.build();
			
			System.out.println(paginawpd.getTitle());
			System.out.println(paginawpd.getUrl());
			System.out.println(paginawpd.getUrlImmagine());
			Sinottico sinottico = paginawpd.getSinottico();
			System.out.println(sinottico);

			
			if (sinottico != null) {
				for (RigaSinottico riga : sinottico) {
					System.out.println(riga);
				}
			} else {
				System.out.println("Sinottico non presente");
			}
			
		} catch (BrowserErratoException e) {
			e.printStackTrace();
		}
	}

}
