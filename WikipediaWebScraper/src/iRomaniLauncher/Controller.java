package iRomaniLauncher;

import iRomaniModel.DinastiaNonTrovataException;
import iRomaniModel.Model;
import iRomaniView.View;
import java.util.List;
import alberoGenealogicoLib.AlberoGenealogico;

public class Controller {
	
	/**
	 * Il model del progetto.
	 */
	private Model model;

	/**
	 * La GUI del progetto.
	 */
	private View view;
	
	/**
	 * Istanzia il controller dal model e dalla view passati.
	 * 
	 * @param model Il model.
	 * @param view La view.
	 */
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}
	
	public void initView() {

	}
	
	/**
	 * Abilita le funzionalità della gui.
	 */
	public void initController() {
		// Ricerca dinastie per menu
		String[] dinastie = model.getListaDinastie();
		view.setDinastie(dinastie);
		
		view.getButtonSeleziona().addActionListener(e -> dinastiaSelezionata());
	}
	
	/**
	 * Dalla dinastia selezionata crea e mostra l'albero genealogico.
	 */
	private void dinastiaSelezionata() {
		// Il nome della dinastia selezionata dal box.
		String dinastiaSelezionata = (String)view.getBoxListaDinastie().getSelectedItem();

		List<AlberoGenealogico> alberiGenealogici = null;
		
		try {
			// Riceve la lista con gli alberi genealogici della dinastia scelta.
			alberiGenealogici = model.alberoGenealogicoDinastia(dinastiaSelezionata);
			
		} catch (DinastiaNonTrovataException error) {
			// Lancia popup errore
		}
		
		if (alberiGenealogici != null) {
			// Istanzia il pannello con gli alberi genealogici grafici.
			view.tabPanelAlberi(alberiGenealogici);
		}
	}

}
