package iRomaniView;

import java.util.List;
import alberoGenealogicoLib.AlberoGenealogico;
import alberoGenealogicoLib.Persona;
import java.util.ArrayList;
import javax.swing.JTabbedPane;

import com.mxgraph.swing.mxGraphComponent;

/**
 * La classe è il pannello in cui vengono raccolti i componenti che hanno gli alberi genealogici disegnati.
 * Riceve la lista contenente gli alberi genealogici di cui si vuole avere la rappresentazione
 * e contiene il pannello dove si può consultare la rappresentazione.
 * Ogni albero genealogico della dinastia viene rappresentato all'interno di un tab che ha come titolo
 * l'imperatore che inizia quella discendenza.
 * 
 * @author Ian Tirso Cini
 *
 */
public class PanelAlberiGenealogici extends JTabbedPane {
	
	/**
	 * La lista degli alberi genealogici.
	 */
	private List<AlberoGenealogico> alberi;
	
	/**
	 * La larghezza della finestra
	 */
	private final int LARGHEZZA = 1010;
	
	/**
	 * L'altezza della finestra
	 */
	private final int ALTEZZA = 670;
	
	/** 
	 * Costruisce il panel con la lista degli alberi genealogici
	 * che si vogliono disegnare.
	 * 
	 * @param alberi Gli alberi genealogici
	 */
	public PanelAlberiGenealogici(List<AlberoGenealogico> alberi) {
		this.alberi = alberi;
		setBounds(0, 0, LARGHEZZA, ALTEZZA);	
	}
	
	/**
	 * Comando iniziare la costruzione delle parti grafiche riguardanti gli 
	 * alberi genealogici ed inserirli nel pannello. 
	 */
	public void costruisciPannello() {
		// Istanzia i componenti grafici dove sono rappresentati gli alberi
		List<mxGraphComponent> alberiVisual = istanziaAlberiVisuali();
		
		// Costruisci il pannello aggiungendo le grafiche.
		buildPannello(alberiVisual);
	}
	
	/**
	 * Istanzia i componenti in cui vi sono gli alberi genealogici
	 * visualizzabili.
	 * 
	 * @return La lista con i componenti con gli alberi.
	 */
	private List<mxGraphComponent> istanziaAlberiVisuali(){

		List<mxGraphComponent> alberiVisual = new ArrayList<>();
		
		// Ciclo tra gli alberi passati al costruttore della classe
		for (AlberoGenealogico albero : alberi) {

			// Passo ogni AlberoGenealogico alla classe che costruisce il componente che illustra i grafi.
			ComponenteGrafoIllustrato grafo = new ComponenteGrafoIllustrato(albero);
			grafo.init();
			
			// Aggiungo alla lista da ritornare
			alberiVisual.add(grafo.getComponente());
			
		}	
		return alberiVisual;
	}
	
	/**
	 * Aggiunge i componenti con i grafi illustrati al pannello
	 * e crea una scheda (tab) per ognuno.
	 *  
	 * @param grafi I componenti con gli alberi genealogici.
	 */
	private void buildPannello(List<mxGraphComponent> grafi) {
		
		for (int i = 0; i < grafi.size(); i++) {

			Persona romano = alberi.get(i).getCapostipite();
			
			add(romano.getNome(), grafi.get(i));
		}
	}
	

}