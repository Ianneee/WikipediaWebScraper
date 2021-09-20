package iRomaniView;

/**
 * La classe contiene degli algoritmi per rimuovere link e citazioni dal sorgente di 
 * un sinottico prelevato da Wikipedia.
 * 
 * @author Ian Tirso Cini
 *
 */
public class ModificaHtmlSinottico {
	
	/**
	 * Il metodo riceve sorgente con il sinottico da pulire da link ed annotazioni
	 * e lo restituisce pulito.
	 * 
	 * @param htmlSinottico Il sinottico da sistemare
	 * @return Il codice con il sinottico pulito
	 */
	public static String puliziaSinottico(String htmlSinottico) {

		String sinotticoPulito = rimuoviLink(htmlSinottico);
		
		// Controllo se nel sinottico sono presenti parentesi quadre
		if (sinotticoPulito.contains("[")) {
			sinotticoPulito = rimuoviAnnotazioni(sinotticoPulito);
		}
		
		return sinotticoPulito;
	}
	
	/**
	 * Il metodo rimuove dal sorgente contente il sinottico le parti del codice
	 * non necessarie e/o non correttamente funzionanti offline.
	 * 
	 * @param htmlSinottico Il sorgente contenente il sinottico.
	 * @return Il sinottico pulito.
	 */
	public static String rimuoviLink(String htmlSinottico) {
		// Buffer che conterrà il nuovo sinottico.
		StringBuffer sb = new StringBuffer();

		// Aggiungo la parte di inizio tabella utile
		sb.append("<html><table class=\"sinottico\" style=\"width:300px;\"><tbody>");
		
		// Cerco la prima riga del sinottico che iniza con Nome
		int indexNome = htmlSinottico.indexOf("Nome");
		
		if (indexNome == -1) {
			// Il sinottico non ha una riga nome
			htmlSinottico =  riposizionaSinottico(htmlSinottico);
			
		} else {
		// Taglio la testa del sinottico con le immagini
		sb.append("<tr><th style=\"\">");
		
		// Taglio fino ad inizio della parte con le informazioni
		htmlSinottico= htmlSinottico.substring(htmlSinottico.indexOf("Nome"));
		}
		
		// Certo i tag con i link per eliminarli
		int end = htmlSinottico.indexOf("<a href");
		
		while (end != -1) {
			// Attacco allo string buffer la parte di sinottico "buona"
			sb.append(htmlSinottico.substring(0, end));
			
			// Cerco la fine del tag
			while (htmlSinottico.charAt(end) != '>') {
				end++;
			}
			
			// Rimuovo il tag
			htmlSinottico = htmlSinottico.substring(end + 1);
			
			// Cerco la chiusura del tag
			end = htmlSinottico.indexOf("</a>");
			
			// Al buffer attacco la parte contenuta tra i tag
			sb.append(htmlSinottico.substring(0, end));
			
			// Taglio la stringa a fine chiusura del tag </a>
			htmlSinottico = htmlSinottico.substring(end + 4);
			
			// Cerco il prossimo tag
			end = htmlSinottico.indexOf("<a href");
		}
		
		// Appendo anche l'ultima parte rimasta
		sb.append(htmlSinottico);
		
		// Ritorno il nuovo sinottico
		return sb.toString();
	}
	
	/**
	 * Il metodo posiziona al punto di inizio delle righe il sinottico che
	 * ha una composizione diversa, con meno informazioni, rispetto a quello
	 * solitamente trovato in altri imperatori romani.
	 * 
	 * @param htmlSinottico Il sorgente con il sinottico da analizzare.
	 * @return Il sinottico nella corretta posizione.
	 */
	public static String riposizionaSinottico(String htmlSinottico) {
		// Cerco la riga subito prima di quelle con le informazioni.
		int start = htmlSinottico.indexOf("<tr class=\"sinottico_divisione\">");
		
		// Taglio il sinottico a quella posizione
		htmlSinottico = htmlSinottico.substring(start);
		
		// Escludo la riga subito prima delle informazioni
		start = htmlSinottico.indexOf("</tr>");
		
		// Ritorno la stringa con il sinottico correttamente posizionato.
		return htmlSinottico.substring(start + 5);
		
	}
	
	/**
	 * Il metodo rimuove le parti con le parentesi dal sinottico che
	 * sono citazioni e riferimenti ad autori o fonti.
	 * 
	 * @param htmlSinottico il sinottico da pulire.
	 * @return il sinottico pulito.
	 */
	public static String rimuoviAnnotazioni(String htmlSinottico) {
		
		StringBuffer sb = new StringBuffer();
		
		// Cerco la prima parentesi quadra
		int parentesi = htmlSinottico.indexOf("[");
		
		while (parentesi != -1) {
			
			// Parte fino alla quadra aperta
			sb.append(htmlSinottico.substring(0, parentesi));
			
			// Posizione a chiusura quadra
			parentesi = htmlSinottico.indexOf("]");
			
			// Taglio il codice escludendo la quadra
			htmlSinottico = htmlSinottico.substring(parentesi + 1);
			
			// Aggiorno la posizione alla prossima parentesi
			parentesi = htmlSinottico.indexOf("[");
			
		}
		
		// Appendo l'ultima parte di codice
		sb.append(htmlSinottico);
		
		return sb.toString();
	}

}
