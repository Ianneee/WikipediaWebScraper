package iRomaniModel;

import java.util.List;
import java.util.ArrayList;

import wikipediaWebScraperLib.ParserSinotticoWikipedia;

/**
 * La classe è uno scraper per la pagina di Wikipedia con la lista delle dinastie degli
 * imperatori romani (https://it.wikipedia.org/wiki/Imperatori_romani).
 * 
 * La procedura di analisi del codice è totalmente automatizzata, deve ricevere solo il
 * codice sorgente della pagina.
 * 
 * @author Ian Tirso Cini
 *
 */
public class ParserTabellaDinastie {
	
	/**
	 * Lunghezza del tag html di fine riga.
	 */
	private final int LUNGHEZZA_TAG = 5;
	
	
	/**
	 * Url della dinastia precedente alla dinastia dei Severi, per il caso particolare.
	 */
	private final String GUERRA_CIVILE_1 = "https://it.wikipedia.org/wiki/Guerra_civile_romana_(193-197)";
	
	
	/**
	 * Il metodo riceve il sorgente della pagina Wikipedia degli imperatori romani
	 * e ritorna una Lista contenente le tabelle delle dinastie come oggetti TabellaDinastia.
	 * 
	 * @param sorgente Sorgente Html della pagina Wikipedia.
	 * @return Lista con le tabelle.
	 */
	public List<TabellaDinastie> analisiSorgente(String sorgente){
		List<TabellaDinastie> raccoglitoreTabelle = new ArrayList<>();
		
		// Punta il codice al nome della prima dinastia nel corpo della pagina
		int inizioTabella = sorgente.indexOf("<h4>");
		sorgente = sorgente.substring(inizioTabella);
		
		while (true) {
			
			int fineTabella = sorgente.indexOf("</table>");
			
			TabellaDinastie tabella = analisiHtmlTabella(sorgente.substring(0, fineTabella));
			raccoglitoreTabelle.add(tabella);
			
			sorgente = sorgente.substring(fineTabella + LUNGHEZZA_TAG);

			// Caso particolare per non saltare la dinastia dei Severi
			if (tabella.getUrlDinastia().equals(GUERRA_CIVILE_1)) {

				// Dinastia dei severi ha il titolo con formato diverso
				inizioTabella = sorgente.indexOf("<h3>");
			} else {
				
				inizioTabella = sorgente.indexOf("<h4>");
				
			}
			
			if (inizioTabella != -1) {
				
				sorgente = sorgente.substring(inizioTabella);
				
			} else {
				
				break;
				
			}
		}
		
		return raccoglitoreTabelle;
		
	}
	
	/**
	 * Il metodo riceve la parte di codice html in cui viene rappresentata una dinastia,
	 * dal nome della dinastia, compreso tra i tag con h4 e la tabella sottostante.
	 * La porzione di codice deve essere già stata sezionata prima, eventuali controlli 
	 * devono essere fatti esternamente.
	 * 
	 * @param sorgente Il codice Html da analizzare.
	 * @return La TabellaDinastie recuperata dalla porzione di codice.
	 */
	private TabellaDinastie analisiHtmlTabella(String sorgente) {
		
		// Prendo il nome della dinastia che è fra il tag h4
		int fineRigaTitolo = sorgente.indexOf("</h4>");
		
		// Se sono nel caso particolare della dinastia dei Severi
		if (fineRigaTitolo == -1) {
			fineRigaTitolo = sorgente.indexOf("</h3>");
		}
		
		// Taglio la riga con il nome della dinastia
		String riga = sorgente.substring(0, fineRigaTitolo);
		
		// Riutilizzo il metodo della classe parserSinottico per estrarre il testo
		String nomeDinastia = ParserSinotticoWikipedia.testoEsternoTag(riga);
		nomeDinastia += " " + dataDinastia(riga);
		
		TabellaDinastie tabellaDinastia = new TabellaDinastie(nomeDinastia);
		tabellaDinastia.setUrlDinastia(cercaLink(riga));
		
		// La tabella è racchiusa tra i tag <table ...> </table>
		sorgente = sorgente.substring(sorgente.indexOf("<table class=\"wikitable\""));
		
		cercaImperatori(sorgente, tabellaDinastia);
		return tabellaDinastia;
	}
	
	/**
	 * Dalla riga contenente il nome della dinastia, estrai la data racchiusa tra le parentesi.
	 * 
	 * @param riga La riga con il titolo della dinastia.
	 * @return La data.
	 */
	private String dataDinastia(String riga) {

		int start = riga.indexOf("(");

		riga = riga.substring(start);

		start = riga.indexOf(")");
		
		return riga.substring(0, start + 1);
	}

	/**
	 * Cerca ed estrae il primo link trovato contenuto all'interno del tag con href.
	 * 
	 * @param sorgente Il codice Html da cui estrarre il link.
	 * @return Il link alla pagina Wikipedia.
	 */
	private String cercaLink(String sorgente) {
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
			return ParserSinotticoWikipedia.estrapolaLink(tag);
		}
		
		// Il tag precedente non ha un link, passo ad esaminare ricorsivamente il prossimo
		return cercaLink(sorgente.substring(end + 1));
	}
	
	/**
	 * Il metodo analizza della porzione di codice passata come argomento, e dalle colonne
	 * presenti recupera le informazioni necessarie.
	 * 
	 * @param htmlTabella La porzione di codice della riga.
	 * @param tabellaDinastia La TabellaDinastia da completare.
	 */
	private void cercaImperatori(String htmlTabella, TabellaDinastie tabellaDinastia) {
		// Scarto le prime due righe contenenti i titoli delle colonne
		htmlTabella = htmlTabella.substring(htmlTabella.indexOf("</tr>"));
		htmlTabella = htmlTabella.substring(htmlTabella.indexOf("</tr>") + LUNGHEZZA_TAG);
		
		// Posiziono gli indici di inizio e fine
		int inizioRiga = htmlTabella.indexOf("<tr>");
		int fineRiga = htmlTabella.indexOf("</tr>");
		
		// Ciclo finchè non ci sono più righe
		while (inizioRiga != -1) {
			
			// Taglio una riga della tabella
			String riga = htmlTabella.substring(inizioRiga, fineRiga);
			
			// Salvo le informazioni necessarie
			Record informazioni = analisiRiga(riga);

			if (informazioni != null) {
				
				// Aggiungo alla tabella il nome e l'url dell'imperatore trovato
				tabellaDinastia.nuovaRiga(informazioni.getNome(), informazioni.getUrl());
			}
			
			// Taglio il codice della tabella
			htmlTabella = htmlTabella.substring(fineRiga + LUNGHEZZA_TAG);
			
			// Aggiorno le posizioni degli indici
			inizioRiga = htmlTabella.indexOf("<tr>");
			fineRiga = htmlTabella.indexOf("</tr>");
		}
	}
	
	/**
	 * Prelevo da una riga di una tabella Html la cella contenente il nome dell'imperatore
	 * e l'url alla sua pagina.
	 * 
	 * @param codiceRiga La riga di una tabella Html.
	 * @return Oggetto Record contenente le informazioni.
	 */
	private Record analisiRiga(String codiceRiga){
		// La riga è composta da una sola cella
		if (codiceRiga.indexOf("</td>") == codiceRiga.lastIndexOf("</td>")){
			return null;
		}
		
		// Elimino la prima cella che contiene una foto/immagine
		codiceRiga = codiceRiga.substring(codiceRiga.indexOf("</td>") + LUNGHEZZA_TAG);
		
		// Se all'interno della cella c'è "Associato al trono" prima del
		// nome dell'imperatore lo scarto.
		if (codiceRiga.contains("Associato al trono")) {
			codiceRiga = codiceRiga.substring(codiceRiga.indexOf("<a href"));
		}
		
		// Salvo le informazioni trovate
		return new Record(ParserSinotticoWikipedia.testoEsternoTag(codiceRiga),
						  ParserSinotticoWikipedia.estrapolaLink(codiceRiga));
	}
	
	/**
	 * Classe interna per salvare le informazioni relative all'imperatore presente
	 * in una riga della tabella.
	 */
	private class Record{
		
		/**
		 * Nome dell'imperatore.
		 */
		private String nomeImperatore;
		
		/**
		 * Url della pagina Wikipedia dell'imperatore.
		 */
		private String urlImperatore;
		
		/**
		 * Costruisce un record con le informazioni prelevate dalla cella
		 * della tabella.
		 * 
		 * @param nomeImperatore Il nome dell'imperatore.
		 * @param urlImperatore L'url alla pagina Wikipedia dedicata.
		 */
		public Record(String nomeImperatore, String urlImperatore) {
			
			this.nomeImperatore = nomeImperatore;
			this.urlImperatore = urlImperatore;
			
		}
		
		/**
		 * Ritorna il nome dell'imperatore.
		 * 
		 * @return Il nome dell'imperatore.
		 */
		public String getNome() {
			return nomeImperatore;
		}
		
		/**
		 * Ritorna l'url alla pagina Wikipedia.
		 * 
		 * @return L'url della pagina Wikipedia.
		 */
		public String getUrl() {
			return urlImperatore;
		}
	}
}
