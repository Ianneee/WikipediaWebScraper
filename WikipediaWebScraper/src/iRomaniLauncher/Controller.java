package iRomaniLauncher;

import iRomaniModel.Model;
import iRomaniView.View;

public class Controller {
	
	private Model model;
	private View view;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	public void initView() {

	}
	
	public void initController() {
		// Ricerca dinastie per menu
		String[] dinastie = model.getListaDinastie();
		view.setDinastie(dinastie);
		
	}

}
