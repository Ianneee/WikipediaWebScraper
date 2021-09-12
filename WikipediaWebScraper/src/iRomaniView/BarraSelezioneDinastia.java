package iRomaniView;

import javax.swing.JComboBox;

public class BarraSelezioneDinastia {
	
	private JComboBox menu;
	
	public BarraSelezioneDinastia(String[] dinastie) {
		this.menu = new JComboBox<>(dinastie);
	}
	
	public JComboBox getMenu() {
		return menu;
	}

}
