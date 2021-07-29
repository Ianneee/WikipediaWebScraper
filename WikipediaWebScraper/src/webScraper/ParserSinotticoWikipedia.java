package webScraper;

import java.util.Scanner;

public class ParserSinotticoWikipedia implements ParserSinottico {

	@Override
	public Sinottico analizzaSinottico(String sinottico) {
		return createSinottico(sinottico);
	}
	
	/**
	 * Analizza il codice Html contenente il sinottico per ogni riga della tabella. Il ciclo finisce quando
	 * non ci sono più righe, quindi quando non ci sono più tag contententi "tr".
	 * End+5 è il conteggio per andare oltre al tag di chiusura </tr>
	 * 
	 * @param sinottico della pagina Wikipedia.
	 * @return Classe Sinottico con le informazioni ricavate.
	 */
	protected Sinottico createSinottico(String sinottico) {
		Sinottico sin = new Sinottico();
		while (sinottico.contains("<tr")) {
			int start = sinottico.indexOf("<tr");
			int end = sinottico.indexOf("</tr");
			Sinottico.Info info = analizzaRiga(sinottico.substring(start, end));
			sinottico = sinottico.substring(end + 5);
//			System.out.println("SINOTTICO ----- \n" + sinottico);
			//TODO aggiungere Info a Sinotico (sin)
		}
		return null;
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
	private Sinottico.Info analizzaRiga(String riga) {
		if (riga.contains("<th") && riga.contains("<td")) {
			String categoria = testoEsternoTag(riga);
			System.out.println("\nCategoria valida: " + categoria + "\n ------------");
			analisiCella(riga);
			//TODO ritornare i risultati
		}
		return null;
	}
	
	/**
	 * In maniera ricorsiva cerco il primo testo fuori dai vari tag di formattazione della riga
	 * del sinottico passata come input, quello visibile all'utente nella pagina.
	 * 
	 * @param riga del sinottico.
	 * @return Il testo contenuto fuori dai tag.
	 */
	protected String testoEsternoTag(String riga) {
//		System.out.println("porzione di riga in analisi: " + riga + "\n");
		// La riga ha lunghezza zero e non contiene nessuna informazione utile.
		if (riga.length() == 0) {
			return null;
		}
		riga = riga.trim();
		int pos = 0;
		// Sono fuori dal tag, cerco quindi l'inizio del tag successivo.
		if (riga.charAt(pos) != '<') {
			while (pos < riga.length() && riga.charAt(pos) != '<' && riga.charAt(pos) != '(') {
				pos++;
			}
			return riga.substring(0, pos).trim();
		}
		// Sono dentro ad un tag, cerco la fine di questo.
		while (riga.charAt(pos) != '>') {
			pos++;
		}
		pos++;
		return testoEsternoTag(riga.substring(pos));
	}
	
	/**
	 * Estrae il link indicato dal tag href e gli concatena la parte https://it.wikipedia.org non presente.
	 * @param riga del sinottico.
	 * @return il link di Wikipedia valido.
	 */
	protected String estraiLink(String riga) {
		int start = 0;
		while (riga.charAt(start) != '\"') {
			start++;
		}
		start++;
		int end = start;
		while(riga.charAt(end) != '\"') {
			end++;
		}
		return "https://it.wikipedia.org" + riga.substring(start, end);
	}
	
	// TODO smontare il codice in funzioni e ritornare i risultati corretti
	private void analisiCella(String riga) {
		System.out.println(riga);
		int start = riga.indexOf("<td");
		int end = riga.indexOf("</td>"); 
		// Rimozione primo tag <td .. >
		while (riga.charAt(start) != '>') {
			start++;
		}
		start++;
		while (start <= end) {
			char letteraIniziale = riga.charAt(start);

			if (letteraIniziale == '<') {
				int subStart = start;
				while(riga.charAt(subStart) != '>') {
					subStart++;
				}
				subStart++;
				String tag = riga.substring(start, subStart);
//				System.out.println("Riga tag: " + tag);
				if (tag.contains("href")) {
					String link = estraiLink(tag);
					start = subStart;
					String nomeLink = testoEsternoTag(riga.substring(start));
					System.out.println(link + " : " + nomeLink);
					while(riga.charAt(start) != '<' && start <= end){
					start++;
						if (riga.charAt(start) == '(') {
								start = testoTraParentesi(riga, start);
						} 
					}
				} else {
					start = subStart;
//					System.out.println("SubStringa dopo taglio: " + riga.substring(start));
				}
			} else {
				if (letteraIniziale == '(') {
					start = testoTraParentesi(riga, start);	
				} else if (letteraIniziale == '\n' || letteraIniziale == ')') {
					start++;
				} else {
					String elemento = testoEsternoTag(riga.substring(start));
					System.out.println("Elemento senza link: " + elemento);
					if (elemento == null) {
//						System.out.println("Elemento che ha prodotto null: " + riga.substring(start, end));
					}
					while (riga.charAt(start) != '<' && start <= end) {
						start++;
						if (riga.charAt(start) == '(') {
							while (riga.charAt(start) != ')') {
								start++;
							}
						} 
					}
				}
			} 
		}
		
	}
	/**
	 * Parsa la parte della riga racchiusa tra parentesi per escluderla.
	 * 
	 * @param riga del sinottico in analisi.
	 * @param start Posizione attuale del parsing.
	 * @return Il punto di start aggiornato.
	 */
	public int testoTraParentesi(String riga, int start) {
		while (riga.charAt(start) != ')') {
			start++;
		}
		return start;
	}
	
}
