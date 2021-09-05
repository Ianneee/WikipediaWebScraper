import wikipediaWebScraper.ParserTabellaWikipedia;

import wikipediaWebScraper.ParserSinotticoWikipedia;

public class ParserTabellaDinastie implements ParserTabellaWikipedia{
	
	@Override
	public TabellaDinastie analizzaTabella(String sorgente) {
		return null;
	}
	
	public TabellaDinastie inizio(String sorgente) {
		// Il nome della dinastia è fra il tag <h4> </h4>
		String riga = sorgente.substring(0, sorgente.indexOf("</h4>"));		
		// Riutilizzo il metodo della classe parserSinottico per estrarre il testo
		String nomeDinastia = ParserSinotticoWikipedia.estraiTestoEsternoTag(riga);
		TabellaDinastie dinastia = new TabellaDinastie(nomeDinastia);
		dinastia.setUrlDinastia(cercaLink(riga));
		// La tabella è racchiusa tra i tag <table ...> </table>
		sorgente = sorgente.substring(sorgente.indexOf("<table class=\"wikitable\""));
		
		cercaImperatori(sorgente, dinastia);
		return dinastia;
	}

	/**
	 * Cerca ed estrae il primo link trovato contenuto all'interno del tag <a href=" ... ">.
	 * 
	 * @param sorgente Il codice Html da cui estrarre il link.
	 * @return Il link alla pagina Wikipedia.
	 */
	public String cercaLink(String sorgente) {
		// Caso base: Se la lunghezza della stringa è 0 non ci sono link 
		if (sorgente.length() == 0) {
			return null;
		}
		int start = 0;
		int end = 1;
		// Cerca la fine del tag
		while (sorgente.charAt(end) != '>') {
			end++;
		}
		// Estrapolo solo il tag appena parsato
		String tag = sorgente.substring(start, end);
		if (tag.contains("href")) {
			// Riutilizzo il metodo della classe parserSinottico per estrarre il link
			return ParserSinotticoWikipedia.estraiLink(tag);
		}
		// Il tag precedente non ha un link, passo ad esaminare ricorsivamente il prossimo
		return cercaLink(sorgente.substring(end + 1));
	}
	
	public void cercaImperatori(String tabella, TabellaDinastie dinastia) {
		int primaRiga = tabella.indexOf("<tr>");
		int ultimaRiga = tabella.lastIndexOf("<tr>");
		if (primaRiga != ultimaRiga) {
			tabella = tabella.substring(tabella.indexOf("</tr>") + 5);
			String riga = tabella.substring(tabella.indexOf("<tr>", tabella.indexOf("</tr>")));
			String nomeImperatore = ParserSinotticoWikipedia.estraiTestoEsternoTag(riga);
			String urlImperatore = ParserSinotticoWikipedia.estraiLink(riga);
			
			
		}
	}
}
