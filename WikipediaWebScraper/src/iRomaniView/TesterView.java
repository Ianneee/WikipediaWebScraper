package iRomaniView;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.List;

import alberoGenealogicoLib.AlberoGenealogico;

/**
 * La classe contiene l'interfaccia grafica dell'applicazione.
 * Possiede i metodi per comunicare con i vari pannelli e componenti che la costituiscono.
 * 
 * @author Ian Tirso Cini
 *
 */
public class TesterView {
	

	/**
	 * JFrame della classe
	 */
	private JFrame frame;

	/**
	 * Il panel con l'elenco delle dinastie
	 */
	private PanelMenuDinastie panelMenuDinastie;
	
	/**
	 * Il panel dove vengono visualizzate le dinastie
	 */
	private PanelPrincipale panelPrincipale;
	
	/**
	 * Il pannello con le informazioni prese dal sinottico sui personaggi storici.
	 */
	private PanelFinestraInfoBox panelInfoBox;
	
	/**
	 * La scelta iniziale dell'utente.
	 */
	private int selezioneIniziale;
	
	
	/**
	 * Costruisce la view istanziando un JFrame.
	 */
	public TesterView() {
		
		// Creazione frame
		frame = new JFrame("iRomani - Arbores consanguinitatis ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1370, 750);
		frame.setLayout(null);
		
		// Icona di JFrame
		ImageIcon icona = new ImageIcon("res/icon.png");
		frame.setIconImage(icona.getImage());
		
		
		// Creazione dei pannelli ed aggiunta al frame
		panelMenuDinastie = new PanelMenuDinastie();
		frame.add(panelMenuDinastie);
		
		panelPrincipale = new PanelPrincipale();
		frame.add(panelPrincipale);
		
		panelInfoBox = new PanelFinestraInfoBox();
		frame.add(panelInfoBox);
		
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		// Messaggio di avviso di apertura
		messaggioAlert();
	}
	
	/**
	 * Messaggio iniziale di conferma inizio programma.
	 */
	private void messaggioAlert() {
		String messaggio = "iRomani, per caricare la lista delle dinastie\n"
						 + "disponibili effettuerà immediatamente uno scraping\n"
						 + "da Wikipedia per poter inizializzare il menù con\n"
						 + "la lista delle dinastie.\n"
						 + "Devi avere installato sul tuo computer il browser\n"
						 + "Chrome versione 93.0.4577.82 per poter continuare.\n"
						 + "Per annullare premere \"No\" ";
		
		selezioneIniziale = JOptionPane.showConfirmDialog(frame, messaggio, "iRomani - Hai tutto installato?", JOptionPane.YES_NO_OPTION);
	}
	
	
	/**
	 * La selezione iniziale dell'utente.
	 * 
	 * @return La selezione iniziale.
	 */
	public int getSelezioneIniziale() {
		return selezioneIniziale;
	}
	
	/**
	 * Il metodo istanzia il panel dove vengono illustrati gli alberi genealogici di una dinastia.
	 * Il metodo accetta una lista contenente gli alberi per disegnare anche più dinastie.
	 * Il pannello viene poi aggiunto al JFrame per la visualizzazione.
	 * 
	 * @param alberi La lista contentente gli alberi genealogici.
	 */
	public void tabPanelAlberi(List<AlberoGenealogico> alberi) {
		// Istanzia il pannello con i tab con gli alberi genealogici.
		PanelAlberiGenealogici panel = new PanelAlberiGenealogici(alberi);

		// Inizializza il pannello
		panel.costruisciPannello();
		
		// Aggiungo il pannello con i tab al pannello principale dove visualizzarlo.
		panelPrincipale.disegnaAlbero(panel);

	}
	
	/**
	 * Ritorna il JFrame.
	 * 
	 * @return Il Jframe.
	 */
	public JFrame getJFrame() {
		return frame;
	}
	
	/**
	 * Ritorna il pannello con il menu di selezione delle dinastie.
	 * 
	 * @return Il pannello con il menu.
	 */
	public PanelMenuDinastie getPanelMenuDinastie() {
		return panelMenuDinastie;
	}
	
	/**
	 * Ritorna il pannello dove viene visualizzato il sinottico del 
	 * personaggio storico selezionato.
	 * 
	 * @return Il pannello con le informazioni.
	 */
	public PanelFinestraInfoBox getPanelInfoBox() {
		return panelInfoBox;
	}
	
}
