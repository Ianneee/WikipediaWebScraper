package wikipediaWebScraperLib;

/**
 * Questa classe effettua il test delle classi:
 *  - PaginaWikipedia
 *  - PaginaWikipediaBuilder
 *  - InfoWikipedia
 *  - ParserSinotticoWikipedia
 *  
 * @author Ian Tirso Cini
 *
 */
public class PaginaWikipediaBuilderEInfoWikipediaTester {
	
	public static void main(String args[]) {
		
		WikipediaNavigator navigator = new WikipediaNavigator();
		String sorgente = navigator.getHtmlPagina("https://it.wikipedia.org/wiki/Licinio");
//		navigator.closeBrowser();
		
		// Utilizzo del builder
		PaginaWikipediaBuilder builder = new PaginaWikipediaBuilder();
		PaginaWikipedia paginawpd = 	builder.
											titoloPagina(InfoWikipedia.titoloPagina(sorgente)).
											urlImmagine(InfoWikipedia.urlImmagine(sorgente)).
											url(InfoWikipedia.urlPagina(sorgente)).
											sinottico(InfoWikipedia.sinottico(sorgente)).
											build();
		
		// Utilizzo di tutti i metodi di PaginaWikipedia
		System.out.println("Titolo pagina: " + paginawpd.getTitle());
		System.out.println("Url pagina: " + paginawpd.getUrl());
		System.out.println("Url immagine: " + paginawpd.getUrlImmagine());
		Sinottico sinottico = paginawpd.getSinottico();
		System.out.println("Sinottico:\n" + sinottico);

		
		for (RigaSinottico riga : sinottico) {
			System.out.println(riga);
		}
		
		System.out.println("Test dei metodi di InfoSinotticoWikipedia - Expected: null (x4)");
		System.out.println(InfoWikipedia.titoloPagina("Ciao questo è un test"));
		System.out.println(InfoWikipedia.urlImmagine("Ciao questo è un test"));
		System.out.println(InfoWikipedia.urlPagina("Ciao questo è un test"));
		System.out.println(InfoWikipedia.sinottico("Ciao questo è un test"));
		
		
		sorgente = navigator.getHtmlPagina("https://it.wikipedia.org/wiki/Aiuto:Disambiguazione");
		
		// Utilizzo del builder
		builder.reset();
		paginawpd = builder.
						titoloPagina(InfoWikipedia.titoloPagina(sorgente)).
						urlImmagine(InfoWikipedia.urlImmagine(sorgente)).
						url(InfoWikipedia.urlPagina(sorgente)).
						sinottico(InfoWikipedia.sinottico(sorgente)).
						build();
		
		
		navigator.closeBrowser();
		
		System.out.println("\nTest per pagina wikipedia priva di sinottico e di immagine:");
		// Utilizzo di tutti i metodi di PaginaWikipedia
		System.out.println("Titolo pagina: " + paginawpd.getTitle());
		System.out.println("Url pagina: " + paginawpd.getUrl());
		System.out.println("Url immagine: " + paginawpd.getUrlImmagine());
		sinottico = paginawpd.getSinottico();
		System.out.println("Sinottico:" + sinottico);;
	}

}
