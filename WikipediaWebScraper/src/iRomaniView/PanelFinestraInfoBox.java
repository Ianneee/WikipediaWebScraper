package iRomaniView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.JComboBox;

import alberoGenealogicoLib.AlberoGenealogico;
import alberoGenealogicoLib.Persona;
import iRomaniModel.AnticoRomano;
import wikipediaWebScraperLib.PaginaWikipedia;

/**
 * La classe contiene il pannello dove è possibile visualizzare il sinottico per le persone
 * della dinastia selezionata.
 * Se non è presente nessun sinottico per quella persona, non viene inserita nell'elenco.
 * 
 * @author Ian Tirso Cini
 *
 */
public class PanelFinestraInfoBox extends JPanel {
	
	/**
	 * La larghezza del pannello.
	 */
	private final int LARGHEZZA = 290;

	/**
	 * L'altezza del pannello.
	 */
	private final int ALTEZZA = 520;
	
	/**
	 * La posizione orizzontale.
	 */
	private final int X = 5;
	
	/**
	 * La posizione verticale.
	 */
	private final int Y = 160;
	
	/**
	 * Il menù a tendina dove scegliere il nome del personaggio.
	 */
	private JComboBox<PaginaWikipedia> box;
	
	/**
	 * Il bottone per scegliere il personaggio.
	 */
	private JButton seleziona;
	
	/**
	 * Il pannello che contiene menu a tendina e bottone.
	 */
	private JPanel contenitoreTopPagina;
	
	/**
	 * Il pannello che contiene le informazioni sul personaggio scelto.
	 */
	private JScrollPane scrollPaneSinottico;
	
	/**
	 * Il testo sul personaggio scelto.
	 */
	private JTextPane testoSinottico;
	
	/**
	 * Il costruttore imposta la posizione ed il layout del pannello.
	 */
	public PanelFinestraInfoBox() {
		
		setBounds(X, Y, LARGHEZZA, ALTEZZA);
		
		setLayout(new BorderLayout());
		
		// Istanzio i componenti all'interno del pannello.
		componentiVuoti();
		
	}
	
	/**
	 * Il metodo istanzia ed inserisce tutti i componenti del pannello al suo interno.
	 */
	private void componentiVuoti() {
		
		// Menu tendina
		box = new JComboBox<PaginaWikipedia>();
		box.setBounds(0, 0, LARGHEZZA, 25);
		
		// Pulsante
		seleziona = new JButton("Seleziona");
		seleziona.setBounds(0, 25, LARGHEZZA, 25);
		
		// Pannello che contiene bottone e menu.
		contenitoreTopPagina = new JPanel();
		
		contenitoreTopPagina.setPreferredSize(new Dimension(LARGHEZZA, 50));
		contenitoreTopPagina.setLayout(null);
		
		contenitoreTopPagina.add(box);
		contenitoreTopPagina.add(seleziona);
		
		add(contenitoreTopPagina, BorderLayout.PAGE_START);
		
		// Schermata testo
		testoSinottico = new JTextPane();
		testoSinottico.setText("Scegli un nome nel menù per avere le informazioni\n"
						+ "su quel personaggio storico.");
		
		testoSinottico.setEditable(false);
		
		// Pannello scrollabile dove inserire il testo.
		scrollPaneSinottico = new JScrollPane(testoSinottico);
		
		scrollPaneSinottico.setPreferredSize(new Dimension(LARGHEZZA, ALTEZZA));
		scrollPaneSinottico.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		add(scrollPaneSinottico, BorderLayout.CENTER);
		
	}
	
	/**
	 * Dalla lista degli alberi genealogici ricevuti per argomento costruisce
	 * il menù a tendina dove si possono scegliere i personaggi storici.
	 * 
	 * @param alberi Gli alberi genealogici della dinastia.
	 */
	public void costruisciMenuTendina(List<AlberoGenealogico> alberi) {
		
		box = new JComboBox<PaginaWikipedia>();
		
		for (AlberoGenealogico albero : alberi) {
			
			Set<Persona> persone = albero.getPersone();
			
			for (Persona persona : persone) {
				
				AnticoRomano romano = (AnticoRomano)persona;
				
				// Creo l'opzione sul menu a tendina solo se questi hanno il sinottico.
				if (romano.getPaginaWikipedia() != null &&
						romano.getPaginaWikipedia().getSinottico() != null) {

					box.addItem(romano.getPaginaWikipedia());
				}
			}
		}
		
		// Pulisco il contenitore dei pulsanti e lo ricreo con i nomi della dinastia in selezione.
		contenitoreTopPagina.removeAll();
		
		box.setBounds(0, 0, LARGHEZZA, 25);
		contenitoreTopPagina.add(box);
		
		contenitoreTopPagina.add(seleziona);
		
		repaint();
		setVisible(false);
		setVisible(true);
		
	}
	
//	public void setImage(String urlImmagine) {
//		
//		// ImageIcon con l'immagine presa dall'url
//		ImageIcon immagine = new ImageIcon(preparaImmagine(urlImmagine));
//		
//		
//
//		// Inserisci l'immagine
//		labelImmagine.setIcon(immagine);
//		labelImmagine.setBackground(Color.WHITE);
//
//		labelImmagine.setOpaque(true);
//		
//		// Testo al centro e sotto l'immagine
//		labelImmagine.setHorizontalTextPosition(JLabel.CENTER);
//		labelImmagine.setVerticalTextPosition(JLabel.BOTTOM);
//
//		labelImmagine.setHorizontalAlignment(JLabel.CENTER);
//		labelImmagine.setBounds(0, 0, LARGHEZZA, ALTEZZA);
//		add(labelImmagine, BorderLayout.CENTER);
//		
//	}
	
	/**
	 * Il metodo riceve il codice html di un sinottico e crea il 
	 * JTextPane dalle informazioni ricevute.
	 * 
	 * @param testo Il sinottico
	 */
	private void istanziaPannelloSinottico(String htmlSinottico, String urlImmagine) {
		
		// Al sinottico aggiungo la parte mancante del codice (quella finale) 
		htmlSinottico += "</tbody></table></html>";
		
		// Cancello i componenti da sostituire
		testoSinottico.removeAll();
		
		// Inserimento immagine da rivedere
		
//		ImageIcon image = new ImageIcon(preparaImmagine(urlImmagine));
		
//		testoSinottico.insertIcon(image);
		
		testoSinottico.setContentType("text/html");
		
		// Ripulisco il sinottico dagli elementi non necessari.
		testoSinottico.setText(pulisciSinottico(htmlSinottico));
		
		testoSinottico.setBounds(0, 0, LARGHEZZA, ALTEZZA);
		
		testoSinottico.setEditable(false);
		
		testoSinottico.repaint();
		testoSinottico.setVisible(false);
		testoSinottico.setVisible(true);
		
	}
	
	/**
	 * Il metodo rimuove dal sorgente contente il sinottico le parti del codice
	 * non necessarie e/o non correttamente funzionanti offline.
	 * 
	 * @param htmlSinottico Il sorgente contenente il sinottico.
	 * @return Il sinottico pulito.
	 */
	private String pulisciSinottico(String htmlSinottico) {
		// Buffer che conterrà il nuovo sinottico.
		StringBuffer sb = new StringBuffer();

		// Aggiungo la parte di inizio tabella utile
		sb.append("<html><table class=\"sinottico\" style=\"width:300px;\"><tbody>");
		
		// Cerco la prima riga del sinottico che iniza con Nome
		int indexNome = htmlSinottico.indexOf("Nome");
		
		if (indexNome == -1) {
			// Il sinottico non ha una riga nome
			htmlSinottico =  sinotticoIncompleto(htmlSinottico);
			
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
	private String sinotticoIncompleto(String htmlSinottico) {
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
	 * Dalla PaginaWikipedia corrispondente ad un personaggio storico
	 * preleva il sinottico e ne costruisce un pannello di testo
	 * visualizzabile.
	 * 
	 * @param pagina La pagina di wikipedia della persona.
	 */
	public void costruisciPannelloInfo(PaginaWikipedia pagina) {

		// Costrisce il pannello contenente le informazioni prese dal sinottico
		istanziaPannelloSinottico("<html>" + pagina.getSinotticoHtml(), pagina.getUrlImmagine());

	}
	
	/**
	 * Ridimensiona l'immagine presa dall'url.
	 * 
	 * @param urlImmagine L'immagine da ridimensionare.
	 * @return L'immagine ridimensionata.
	 */
//	private Image preparaImmagine(String urlImmagine) {
//		
//		BufferedImage immagine = null;
//				
//		URL url = null;
//		
//		try {
//			url = new URL(urlImmagine);
//			immagine = ImageIO.read(url);
//		} catch (MalformedURLException error) {
//			// TODO scegli catch
//		} catch (IOException error) {
//			// TODO scegli catch
//		}
//		
//		
//		// Dimensioni originali
//		int altezza = immagine.getHeight();
//		int larghezza = immagine.getWidth();
//		
//		// Nuove dimensioni
//		int nuovaLarghezza = (LARGHEZZA / 3) * 2;
//		int nuovaAltezza = (nuovaLarghezza * altezza) / larghezza;
//
//		// Ritorno l'immagine ridimensionata
//		return immagine.getScaledInstance(nuovaLarghezza, nuovaAltezza, BufferedImage.SCALE_SMOOTH);
//		
//	}
	
	/**
	 * Ritorna la JComboBox con i nomi.
	 * 
	 * @return La JComboBox.
	 */
	public JComboBox<PaginaWikipedia> getMenuTendina() {
		return box;
	}
	
	/**
	 * Ritorna il bottone seleziona del pannello.
	 * 
	 * @return Il bottone seleziona.
	 */
	public JButton getBottoneSeleziona() {
		return seleziona;
	}

}
