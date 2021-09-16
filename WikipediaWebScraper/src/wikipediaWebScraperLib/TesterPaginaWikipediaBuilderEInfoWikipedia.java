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
public class TesterPaginaWikipediaBuilderEInfoWikipedia {
	
	public static void main(String args[]) {
		
		WikipediaNavigator navigator = new WikipediaNavigator();
		
		String sorgente = null;
		
		try {
			
		sorgente = navigator.getHtmlPagina("https://it.wikipedia.org/wiki/Domiziano");
		
		} catch (WikipediaUrlErratoException error) {
			
			error.printStackTrace();
			
		}
		
		// Test Costruzione PaginaWikipedia con builder
		System.out.println("Test PaginaWikipediaBuilder");
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

		
		// Builder reset e PaginaWikipedia null
		System.out.println("\nTest Reset builder e PaginaWikipedia campi null");
		
		builder.reset();
		
		PaginaWikipedia buildReset = builder.build();
		
		System.out.println("Titolo pagina: " + buildReset.getTitle());
		System.out.println("Url pagina: " + buildReset.getUrl());
		System.out.println("Url immagine: " + buildReset.getUrlImmagine());
		sinottico = buildReset.getSinottico();
		System.out.println("Sinottico:" + sinottico);;
		
		
		// Test wiki no sinottico e immagine
		try {
			
		sorgente = navigator.getHtmlPagina("https://it.wikipedia.org/wiki/Aiuto:Disambiguazione");
		
		} catch (WikipediaUrlErratoException error) {
			
			error.printStackTrace();
			
		}
		
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
