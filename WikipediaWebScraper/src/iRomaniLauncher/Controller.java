package iRomaniLauncher;

import iRomaniModel.DinastiaNonTrovataException;
import iRomaniModel.Model;
import iRomaniView.View;
import wikipediaWebScraperLib.PaginaWikipedia;
import wikipediaWebScraperLib.WikipediaUrlErratoException;

import java.util.List;

import javax.swing.JOptionPane;

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
	
	private boolean infoBoxAttivo = false;
	
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
	
	/**
	 * Attiva il programma.
	 */
	public void init() {
		int selezioneIniziale = view.getSelezioneIniziale();
		if (selezioneIniziale == 0 ) {
			initController();
		} 
	}
	
	/**
	 * Abilita le funzionalità della gui.
	 */
	private void initController() {
		// Ricerca dinastie per menu
		String[] dinastie = model.getListaDinastie();
		
		view.getPanelMenuDinastie().setDinastie(dinastie);
		
		view.getPanelMenuDinastie().getButtonSeleziona().addActionListener(e -> dinastiaSelezionata());
		
	}
	
	/**
	 * Dalla dinastia selezionata crea e mostra l'albero genealogico.
	 */
	private void dinastiaSelezionata() {
		// Il nome della dinastia selezionata dal box.
		String dinastiaSelezionata = (String)view.getPanelMenuDinastie().getBoxListaDinastie().getSelectedItem();

		List<AlberoGenealogico> alberiGenealogici = null;
		
		try {
			// Riceve la lista con gli alberi genealogici della dinastia scelta.
			alberiGenealogici = model.alberoGenealogicoDinastia(dinastiaSelezionata);
			
		} catch (DinastiaNonTrovataException error) {
			// Lancia popup errore
			JOptionPane.showMessageDialog(view.getJFrame(), "Errore inaspettato, riavviare il programma");
		} catch (WikipediaUrlErratoException error) {
			// Url errato passato a WikipediaNavigator
			JOptionPane.showMessageDialog(view.getJFrame(), "Errore inaspettato durante lo scraping,\n scegliere nuovamente la dinastia.");
		}
		
		if (alberiGenealogici != null) {
			// Istanzia il pannello con gli alberi genealogici grafici.
			view.tabPanelAlberi(alberiGenealogici);
		}
		
		costruisciInfoBox(alberiGenealogici);
	}
	
	/**
	 * Il metodo si occupa di far costruire il menu a tendina con il
	 * nome dei personaggi storici che hanno un sinottico appartenenti
	 * alla dinastia selezionata.
	 * 
	 * @param alberi Gli alberi genealogici della dinastia.
	 */
	private void costruisciInfoBox(List<AlberoGenealogico> alberi) {
		
		if (!infoBoxAttivo) {
			attivaPulsanteInfoBox();
		}
		
		view.getPanelInfoBox().costruisciMenuTendina(alberi);
		
	}
	
	/**
	 * Il metodo aggiorna il contenuto del pannello con le informazioni
	 * del personaggio storico selezionato.
	 */
	private void aggiornaInfoBox() {
		
		PaginaWikipedia pagina = (PaginaWikipedia)view.getPanelInfoBox().getMenuTendina().getSelectedItem();

		view.getPanelInfoBox().costruisciPannelloInfo(pagina);
		
	}
	
	private void attivaPulsanteInfoBox() {
		
		view.getPanelInfoBox().getBottoneSeleziona().addActionListener(e -> aggiornaInfoBox());
		infoBoxAttivo = true;
	}

}
