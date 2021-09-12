package iRomaniView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View {
	
	private JComboBox<String> listaDinastie;
	private JFrame frame;
	private JLabel scegliLabel;
	private JLabel caricamento;
	private JPanel menuSx;
	private JButton scegliDinastia;
	
	public View() {
		
		// Creazione frame
		frame = new JFrame("Alberi genealogici imperatori romani");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 600);
		frame.setLayout(null);
		
		scegliLabel();
		labelCaricamento();
		menuDinastie();
		bottoneScegli();
		menuSx();
		
		JPanel schermata = new JPanel();
		schermata.setVisible(true);
		schermata.setBounds(200, 0, 700, 600);
		
		frame.add(menuSx);
		frame.add(menuSx);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
	}
	
	public JFrame getJFrame() {
		return frame;
	}
	
	private void scegliLabel() {
		// Label sopra elenco dinastie
		scegliLabel = new JLabel("Scegli una dinastia");
		scegliLabel.setOpaque(true);
		scegliLabel.setHorizontalAlignment(JLabel.CENTER);
		scegliLabel.setFont(new Font(null, Font.PLAIN, 14));
		scegliLabel.setVisible(true);
		
		frame.add(scegliLabel);
	}
	
	private void labelCaricamento() {
		// Label caricamento
		caricamento = new JLabel("Caricamento . . . Scraping dinastie in corso");
		caricamento.setOpaque(true);
		caricamento.setHorizontalAlignment(JLabel.CENTER);
		caricamento.setVerticalAlignment(JLabel.VERTICAL);
		caricamento.setVisible(true);
		
		frame.add(caricamento);
	}
	
	private void menuSx() {
		// Pannello
		menuSx = new JPanel();
		menuSx.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		menuSx.add(scegliLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		menuSx.add(listaDinastie, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		menuSx.add(scegliDinastia, gbc);
		
		menuSx.setBackground(Color.WHITE);
		menuSx.setBounds(0, 0, 200, 600);
		menuSx.setVisible(true);
	}
	
	private void menuDinastie() {
		// Menu dinastie
		listaDinastie = new JComboBox<String>();
		listaDinastie.setSize(155, 25);
		listaDinastie.addItem("Caricamento . . .");
	}
	
	private void bottoneScegli() {
		// Bottone
		scegliDinastia = new JButton("Seleziona");
		scegliDinastia.setFocusable(false);
	}
	
	public void setDinastie(String[] dinastie) {
		for (String dinastia : dinastie) {
			listaDinastie.addItem(dinastia);
		}
		listaDinastie.removeItemAt(0);
		caricamento.setVisible(false);
		System.out.println(listaDinastie.getSize());
	}
	
	public JComboBox<String> getListaDinastie(){
		return listaDinastie;
	}
	
	
}
