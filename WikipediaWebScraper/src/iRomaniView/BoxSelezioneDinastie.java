package iRomaniView;

import javax.swing.JComboBox;

public class BoxSelezioneDinastie {
	
	/**
	 * Il box che contiene le scelte
	 */
	private JComboBox menu;
	
	/**
	 * Passare come argomento le dinastie.
	 * 
	 * @param dinastie Le dinastie degli imperatori romani.
	 */
	public BoxSelezioneDinastie(String[] dinastie) {
		this.menu = new JComboBox<>(dinastie);
	}
	
	/**
	 * Ritorna il ComboBox.
	 * @return
	 */
	public JComboBox getMenu() {
		return menu;
	}

}
