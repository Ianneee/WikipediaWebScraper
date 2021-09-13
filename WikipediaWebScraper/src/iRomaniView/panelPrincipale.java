package iRomaniView;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * La classe è il pannello centrale dell'applicazione, dove vengono visualizzati
 * gli alberi genealogici.
 * 
 * @author Ian Tirso Cini
 *
 */
public class panelPrincipale extends JPanel {
	
	/**
	 * Istanzia il pannello con le varie impostazioni.
	 */
	public panelPrincipale() {
		setVisible(true);
		setBounds(300, 10, 1020, 670);
		
		setLayout(null);
	}
	
	/**
	 * Il Metodo riceve il JTabbedPane contenente gli alberi genealogici disegnati.
	 * 
	 * @param albero Il JTabbedPane con gli alberi genealogici.
	 */
	public void disegnaAlbero(JTabbedPane albero) {
		removeAll();
		add(albero);
		repaint();
	}

}
