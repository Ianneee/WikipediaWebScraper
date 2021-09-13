package iRomaniView;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * La classe è il JPanel sinistro dell'applicazione in cui è possibile scegliere
 * dalla lista la dinastia di cui si vuole avere la rappresentazione grafica.
 * 
 * @author Ian Tirso Cini
 *
 */
public class panelMenuDinastie extends JPanel {
	
	/**
	 * JComboBox contenente l'elenco delle dinastie.
	 */
	private JComboBox<String> boxListaDinastie;
	
	/**
	 * L'etichetta sopra la JComboBox
	 */
	private JLabel labelScegli;
	
	/**
	 * Il pulsante che permette di scegliere la dinastia.
	 */
	private JButton buttonSeleziona;

	/**
	 * Costruisce il pannello e posiziona i componenti.
	 */
	public panelMenuDinastie() {
		
		labelScegli();
		boxListaDinastie();
		buttonSeleziona();
		
		setLayout(new GridBagLayout());
		
		// Regolatore di posizione
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 0, 5, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(labelScegli, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(boxListaDinastie, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(buttonSeleziona, gbc);
		
		// Impostazioni grafiche per la barra laterale
		setBackground(Color.WHITE);
		setBounds(0, 10, 300, 200);
		setVisible(true);
	}
	
	/**
	 * Istanzia la JComboBox.
	 */
	private void boxListaDinastie() {
		// Menu dinastie
		boxListaDinastie = new JComboBox<String>();
		boxListaDinastie.setSize(155, 25);
		boxListaDinastie.addItem("Caricamento . . .");
	}
	
	/**
	 * Istanzia il bottone.
	 */
	private void buttonSeleziona() {
		// Bottone
		buttonSeleziona = new JButton("Seleziona");
		buttonSeleziona.setFocusable(false);
	}
	
	/**
	 * Istanzia l'etichetta
	 */
	private void labelScegli() {

		labelScegli = new JLabel("Scegli una dinastia");
		labelScegli.setOpaque(false);
		labelScegli.setHorizontalAlignment(JLabel.CENTER);
		labelScegli.setVerticalAlignment(JLabel.TOP);
		
		labelScegli.setFont(new Font(null, Font.PLAIN, 18));
		labelScegli.setVisible(true);
	
	}
	
	/**
	 * Riceve l'array contenente l'elenco delle dinastie e riempie la JComboBox.
	 * 
	 * @param dinastie L'array con l'elenco delle dinastie.
	 */
	public void setDinastie(String[] dinastie) {
		for (String dinastia : dinastie) {
			boxListaDinastie.addItem(dinastia);
		}
		boxListaDinastie.removeItemAt(0);
	}
	
	/**
	 * Ritorna il bottone.
	 * 
	 * @return Il bottone.
	 */
	public JButton getButtonSeleziona() {
		return buttonSeleziona;
	}
	
	/**
	 * Ritorna la JComboBox.
	 * 
	 * @return La JComboBox.
	 */
	public JComboBox<String> getBoxListaDinastie() {
		return boxListaDinastie;
	}
}
