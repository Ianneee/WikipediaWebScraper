package wikipediaWebScraperLib;

/**
 * Parser per un sinottico di una pagina Wikipedia.
 * Analizza le righe di un sinottico e ne estrae le informazioni solo se questa è formata da cella destra
 * e cella sinistra.
 * Se alle informazioni nella cella destra sono associati dei link, vengono recuperati anche questi.
 * Questo parser è ottimizzato nell'analisi di sinottici per le dinastie romane, in quanto i casi particolari/anomali
 * sono stati recuparati da quelle pagine.
 *  
 * @author Ian Tirso Cini
 *
 */
public class ParserSinotticoWikipedia implements ParserTabellaWikipedia {

	@Override
	public Sinottico analizzaTabella(String sinottico) {
		return createSinottico(sinottico);
	}
	
	/**
	 * Analizza il codice Html contenente il sinottico parsando ogni riga della tabella una alla volta. 
	 * Il ciclo finisce quando non ci sono più righe, quindi quando non ci sono più tag contententi <tr ...>.
	 * End+5 è il conteggio per andare oltre al tag di chiusura <\/tr>
	 * 
	 * @param sinottico della pagina Wikipedia.
	 * @return Classe Sinottico con le informazioni ricavate.
	 */
	private static Sinottico createSinottico(String sinottico) {
		Sinottico sin = new Sinottico();
		
		// Scorro il sorgente e analizzo righe finchè ne trovo
		while (sinottico.contains("<tr")) {
			
			int start = sinottico.indexOf("<tr");
			int end = sinottico.indexOf("</tr");
			
			// Passo al metodo analizzaRiga una riga per volta
			RigaSinottico nuovaRiga = analizzaRiga(sinottico.substring(start, end));
			
			if (nuovaRiga != null) {
				sin.addRiga(nuovaRiga);
			}
			
			// Escludo dal sorgente la riga appena analizzata
			sinottico = sinottico.substring(end + 5);
			
		}
		return sin;
	}
	
	/**
	 * Estrapola le informazioni della riga della tabella sinottico passata come input.
	 * La riga viene analizzata solo se sono presenti sia la cella sinistra che destra quindi
	 * se sono presenti sia il tag "th" che quello "td".
	 * La prima analisi viene fatta sulla cella sinistra che conterrà il nome della categoria
	 * dell'informazione.
	 * 
	 * @param riga del sinottico.
	 * @return Sinottico.info con le informazioni della riga.
	 */
	private static RigaSinottico analizzaRiga(String riga) {
		
		// Se la riga ha i tag <th ...> e <td ...> ha sia la cella sinistra(th) che destra(td) (è un pattern di Wikipedia per il sinottico)
		if (riga.contains("<th") && riga.contains("<td")) {
			
			// Recupero il testo nella cella sinistra
			String categoria = estraiTestoEsternoTag(riga);
			
			// Creo una nuova riga con la categoria appena trovata
			RigaSinottico nuovaRiga = new RigaSinottico(categoria);
			
			// Analizzo la cella a destra della riga
			analisiCella(riga, nuovaRiga);
			return nuovaRiga;
		}
		
		// La riga era composta da una sola cella
		return null;
	}
	
	/**
	 * Metodo statico per estrapolare dalla riga del codice sorgente di Wikipedia il testo
	 * fuori da un tag Html, se presente.
	 * Ritorna null se non è presente nessun testo.
	 * 
	 * @param riga La riga di codice Html.
	 * @return Il testo esterno alla riga.
	 */
	static public String testoEsternoTag(String riga) {
		return estraiTestoEsternoTag(riga);
	}
	
	/**
	 * In maniera ricorsiva cerco il primo testo fuori dai vari tag di formattazione della riga
	 * del sinottico passata come input, quello visibile all'utente nella pagina.
	 * 
	 * @param riga del sinottico.
	 * @return Il testo contenuto fuori dai tag.
	 */
	static private String estraiTestoEsternoTag(String riga) {
		// Caso base: la riga ha lunghezza zero e quindi non c'è nessun testo all'esterno dei tag.
		if (riga.length() == 0) {
			return null;
		}
		
		riga = riga.trim();
		
		int start = 0;
		
		// Sono fuori dal tag, cerco l'inizio del tag successivo o fino alla parentesi tonda (caso particolare).
		if (riga.charAt(start) != '<') {
			
			while (start < riga.length() && riga.charAt(start) != '<' && riga.charAt(start) != '(') {
				start++;
			}
			
			return riga.substring(0, start).trim();
		}
		
		// Sono dentro ad un tag, cerco la fine di questo per uscirne.
		while (riga.charAt(start) != '>') {
			start++;
		}
		
		start++;
		// Continuo la ricerca ricorsivamente
		return estraiTestoEsternoTag(riga.substring(start));
	}
	
	/**
	 * Metodo statico per estrarre il link nel tag <a href= ...> e gli concatena 
	 * la parte https://it.wikipedia.org che non è non presente.
	 * Il metodo estrae il link, non controlla la validità della stringa passata, il controllo
	 * deve essere fatto prima.
	 * 
	 * @param riga La stringa contentente il tag da cui reperire il link.
	 * @return Il link di Wikipedia.
	 */
	static public String estrapolaLink(String riga) {
		return estraiLink(riga);
	}
	
	/**
	 * Estrae il link nel tag <a href= ...> e gli concatena la parte https://it.wikipedia.org che non è non presente.
	 * Il metodo estrae il link, non controlla la validità della stringa passata, il controllo
	 * deve essere fatto prima.
	 * 
	 * @param riga del sinottico.
	 * @return il link di Wikipedia valido.
	 */
	static private String estraiLink(String riga) {
		int start = 0;
		
		// Cerco la fine di href=
		while (riga.charAt(start) != '"') {
			start++;
		}
		
		start++;
		int end = start;
		
		// Cerco la fine dell'url
		while(riga.charAt(end) != '"') {
			end++;
		}
		
		// Aggiungo la parte mancante per avere un url valido per il WikipediaNavigator
		return "https://it.wikipedia.org" + riga.substring(start, end);
	}
	
	/**
	 * Parsa la cella destra della riga del sinottico passata come argomento.
	 * 
	 * @param riga Riga del sinottico in analisi.
	 * @param rigaSinottico La riga dove inserire i risultati dell'analisi.
	 */
	private static void analisiCella(String riga, RigaSinottico rigaSinottico) {
		
		int start = riga.indexOf("<td");
		int end = riga.indexOf("</td>"); 
		
		// Rimozione del primo tag <td .. >
		while (riga.charAt(start) != '>') {
			start++;
		}
		
		start++;
		
		while (start < end) {
			
			char letteraIniziale = riga.charAt(start);
			
			if (letteraIniziale == '<') {
				start = internoTag(riga, start, end, rigaSinottico);
			} else {
				// Caso particolare di testo che non voglio
				if (letteraIniziale == '(') {
					start = testoTraParentesi(riga, start);	
				} else if (letteraIniziale == '\n' || letteraIniziale == ')' || letteraIniziale == ' ') {
					// Casi particolari, li scavalco per trovare un altro caso utile
					start++;
				} else {
					start = esternoTag(riga, start, end, rigaSinottico);
				}
			} 
		}
		
	}
	
	/**
	 * Parsa la parte della riga racchiusa tra parentesi per escluderla.
	 * 
	 * @param riga Riga del sinottico in analisi.
	 * @param start Posizione attuale del parser.
	 * @return Il puntatore start aggiornato.
	 */
	private static int testoTraParentesi(String riga, int start) {
		while (riga.charAt(start) != ')') {
			start++;
		}
		
		return ++start;
	}
	
	/**
	 * Analizza il tag Html della riga in analisi.
	 * 
	 * @param riga Riga del sinottico in analisi.
	 * @param start Posizione attuale del parser.
	 * @param end Posizione finale della riga.
	 * @return Il puntatore start aggiornato.
	 */
	private static int internoTag(String riga, int start, int end, RigaSinottico rigaSinottico) {
		int endTag = start;
		
		// Prelevo il tag trovato
		while(riga.charAt(endTag) != '>') {
			endTag++;
		}
		
		endTag++;
		
		String tag = riga.substring(start, endTag);
		
		// Se il tag è di un link, questo è ad un dato utile
		if (tag.contains("href") && tag.contains("/wiki")) {
			
			// Estraggo link e testo associato
			String link = estraiLink(tag);
			
			start = endTag;
			
			String nomeLink = estraiTestoEsternoTag(riga.substring(start));
			
			// Salvo l'informazione ottenuta
			rigaSinottico.addInformazione(nomeLink, link);
			
			// Puntatore start all'inizio del tag successivo
			while(riga.charAt(start) != '<' && start <= end){
			start++;
				if (riga.charAt(start) == '(') {
						start = testoTraParentesi(riga, start);
				} 
			}
			
		} else {
			return endTag;
		}	
		
		return start;
	}
	
	/**
	 * Analizza il testo fuori dai tag Html. Se il testo estratto ha una dimensioni di lunghezza 1
	 * questo non viene aggiunto come elemento del sinottico in quanto segno di punteggiatura o 
	 * simili.
	 * 
	 * @param riga Riga del sinottico in analisi.
	 * @param start Posizione attuale del parser.
	 * @param end posizione finale della riga.
	 * @param rigaSinottico Riga dove aggiungere il risultato.
	 * @return Il puntatore start aggiornato.
	 */
	private static int esternoTag(String riga, int start, int end, RigaSinottico rigaSinottico) {
			// Prelevo il testo fuori dai tag (fra i tag)
			String elemento = estraiTestoEsternoTag(riga.substring(start));
			
			// Controllo casi particolari di testo che non voglio prelevare (note, congiunzioni, casi particolari)
			if (elemento != null && elemento.length() > 1 && elemento.charAt(0) != '[' && elemento.charAt(0) != '"') {
				rigaSinottico.addInformazione(elemento);
			}
			
			// Posiziono il puntatore all'inizio del prossimo tag
			while (riga.charAt(start) != '<' && start <= end) {
				start++;
				
				// Escludo tutto quello che è dentro alle parentesi tonde
				if (riga.charAt(start) == '(') {
					while (riga.charAt(start) != ')') {
						start++;
					}
				} 
			}
			
		return start;
	}	
	
}
