package iRomaniView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
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
	private final int LARGHEZZA = 420;

	/**
	 * L'altezza del pannello.
	 */
	private final int ALTEZZA = 520;
	
	/**
	 * La posizione orizzontale.
	 */
	private final int X = 7;
	
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
		
		// Menu tendina
		box();
		
		// Pulsante
		seleziona();
		
		// Pannello che contiene bottone e menu.
		contenitoreTopPagina();
		
		// Schermata testo
		testoSinottico();
		
		// Pannello scrollabile dove inserire il testo.
		scrollPaneSinottico();
		
	}
	
	/**
	 * Istanzia il JComboBox
	 */
	private void box() {
		// Menu tendina
		box = new JComboBox<PaginaWikipedia>();
		box.setBounds(0, 0, LARGHEZZA, 25);
	}
	
	/**
	 * Istanzia il bottone seleziona
	 */
	private void seleziona() {
		// Pulsante
		seleziona = new JButton("Seleziona");
		seleziona.setFocusable(false);
		seleziona.setBounds(0, 25, LARGHEZZA, 25);
	}
	
	/**
	 * Istanzia il panello con menu e bottone
	 */
	private void contenitoreTopPagina() {
		contenitoreTopPagina = new JPanel();
		
		contenitoreTopPagina.setPreferredSize(new Dimension(LARGHEZZA, 50));
		contenitoreTopPagina.setLayout(null);
		
		contenitoreTopPagina.add(box);
		contenitoreTopPagina.add(seleziona);
		
		add(contenitoreTopPagina, BorderLayout.PAGE_START);
	}
	
	/**
	 * Istanzia il JTextPane dove appare il testo del sinottico
	 */
	private void testoSinottico() {
		testoSinottico = new JTextPane();
		testoSinottico.setText("Scegli un nome nel menù per avere le informazioni\n"
						+ "su quel personaggio storico.");
		
		testoSinottico.setEditable(false);
	}
	
	/**
	 * Istanzia il pannello scrollabile dove inserire il pannello con il testo del sinottico
	 */
	private void scrollPaneSinottico() {
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
	
	/**
	 * Il metodo riceve il codice html di un sinottico e crea il 
	 * JTextPane dalle informazioni ricevute.
	 * 
	 * @param htmlSinottico Il sinottico.
	 */
	private void istanziaPannelloSinottico(String htmlSinottico) {
		
		// Al sinottico aggiungo la parte mancante del codice (quella finale) 
		htmlSinottico += "</tbody></table></html>";
		
		// Cancello i componenti da sostituire
		testoSinottico.removeAll();
		
		
		testoSinottico.setContentType("text/html");
		
		// Ripulisco il sinottico dagli elementi non necessari.
		testoSinottico.setText(ModificaHtmlSinottico.puliziaSinottico(htmlSinottico));
		
		testoSinottico.setBounds(0, 0, LARGHEZZA, ALTEZZA);
		
		testoSinottico.setEditable(false);
		
		testoSinottico.repaint();
		testoSinottico.setVisible(false);
		testoSinottico.setVisible(true);
		
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
		istanziaPannelloSinottico("<html>" + pagina.getSinotticoHtml());

	}
	
	
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
