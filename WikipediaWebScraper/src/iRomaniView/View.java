package iRomaniView;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.util.List;
import alberoGenealogicoLib.AlberoGenealogico;

/**
 * La classe contiene l'interfaccia grafica dell'applicazione.
 * Possiede i metodi per comunicare con i vari pannelli e componenti che la costituiscono.
 * 
 * @author Ian Tirso Cini
 *
 */
public class View {
	

	/**
	 * JFrame della classe
	 */
	private JFrame frame;

	/**
	 * Il panel con l'elenco delle dinastie
	 */
	private panelMenuDinastie panelMenuDinastie;
	
	/**
	 * Il panel dove vengono visualizzate le dinastie
	 */
	private panelPrincipale panelCentrale;
	
	
	public View() {
		
		// Creazione frame
		frame = new JFrame("Alberi genealogici imperatori romani");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1350, 750);
		frame.setLayout(null);
		
		panelMenuDinastie = new panelMenuDinastie();
		frame.add(panelMenuDinastie);
		
		panelCentrale = new panelPrincipale();
		frame.add(panelCentrale);
		
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
	}

	/**
	 * Carica le dinastie nel ComboBox.
	 * 
	 * @param dinastie Le dinastie.
	 */
	public void setDinastie(String[] dinastie) {
		panelMenuDinastie.setDinastie(dinastie);
	}
	
	/**
	 * Il metodo istanzia il panel dove vengono illustrati gli alberi genealogici di una dinastia.
	 * Il metodo accetta una lista contenente gli alberi per disegnare anche più dinastie.
	 * Il pannello viene poi aggiunto al JFrame per la visualizzazione.
	 * 
	 * @param alberi La lista contentente gli alberi genealogici.
	 */
	public void tabPanelAlberi(List<AlberoGenealogico> alberi) {
		panelAlberiGenealogici panel = new panelAlberiGenealogici(alberi);
		panel.costruisciPannello();
		
		panelCentrale.disegnaAlbero(panel);
		
		frame.setVisible(false);
		frame.setVisible(true);
	}
	
	/**
	 * Ritorna il JFrame
	 * @return
	 */
	public JFrame getJFrame() {
		return frame;
	}
	
	/**
	 * Ritorna la JComboBox dove vengono inserite le dinastie.
	 * 
	 * @return La JComboBox delle dinastie.
	 */
	public JComboBox<String> getBoxListaDinastie(){
		return panelMenuDinastie.getBoxListaDinastie();
	}
	
	/**
	 * Ritorna il bottone presente sotto la JComboBox.
	 * 
	 * @return Il bottone.
	 */
	public JButton getButtonSeleziona() {
		return panelMenuDinastie.getButtonSeleziona();
	}
	
}
