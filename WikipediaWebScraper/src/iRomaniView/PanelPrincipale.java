package iRomaniView;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * La classe è il pannello centrale dell'applicazione, dove vengono visualizzati
 * gli alberi genealogici.
 * Per la rappresentazione deve ricevere un PanelAlberiGenealogici da visualizzare.
 * 
 * @author Ian Tirso Cini
 *
 */
public class PanelPrincipale extends JPanel {
	
	/**
	 * L'altezza.
	 */
	private final int ALTEZZA = 670;
	
	/**
	 * La larghezza.
	 */
	private final int LARGHEZZA = 1020;
	
	/**
	 * La posizione orizzontale.
	 */
	private final int X = 300;
	
	/**
	 * La posizione verticale.
	 */
	private final int Y = 10;
	
	/**
	 * Istanzia il pannello con le varie impostazioni.
	 */
	public PanelPrincipale() {
		setVisible(true);
		setBounds(X, Y, LARGHEZZA, ALTEZZA);
		
		setLayout(null);
	}
	
	/**
	 * Il Metodo riceve il PanelAlberiGenealogici contenente gli alberi genealogici disegnati.
	 * 
	 * @param alberiGenealogici Il PanelAlberiGenealogici con gli alberi genealogici.
	 */
	public void disegnaAlbero(PanelAlberiGenealogici alberiGenealogici) {
		removeAll();
		add(alberiGenealogici);
		repaint();
		
		setVisible(false);
		setVisible(true);
	}

}
