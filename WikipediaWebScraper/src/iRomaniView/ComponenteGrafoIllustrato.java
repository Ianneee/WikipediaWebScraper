package iRomaniView;

import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;

import alberoGenealogicoLib.AlberoGenealogico;
import alberoGenealogicoLib.Archi;
import alberoGenealogicoLib.Persona;
import iRomaniModel.Imperatore;

import java.awt.Color;
import java.util.Map;


/**
 * La classe costruisce un Component con l'albero genealogico passato come argomento
 * disegnato.
 * Per ogni oggetto presente all'interno dell'albero genealogico vengono scelti i colori
 * della rappresentazione.
 * La classe ritorna il mxGraphComponent che è il componente da aggiungere al JFrame o ad un
 * JPanel per avere la rappresentazione grafica.
 * 
 * @author Ian Tirso Cini
 *
 */
public class ComponenteGrafoIllustrato {
	
	/**
	 * Componente con visualizzazione dell'albero genealogico.
	 */
	private mxGraphComponent component;
	
	/**
	 * Lo stile di visualizzazione degli imperatori.
	 */
	private final String STYLE_IMPERATORE = "shape=rectangle;"
			+ "rounded=true;"
			+ "fontColor=080808;" 
			+ "fillColor=FF6961;"
			+ "shadow=false;"
			+ "strokeColor=FF6961";
	
	/**
	 * Lo stile di visualizzazione dei romani.
	 */
	private final String STYLE_ROMANO = "shape=rectangle;"
			+ "rounded=false;"
			+ "fontColor=080808;"	
			+ "fillColor=BFC7E2;"
			+ "shadow=false;"
			+ "strokeColor=BFC7E2";
	
	/**
	 * Il colore degli archi degli archi.
	 */
	private final String STYLE_ARCHI = "strokeColor=#000000;";
	
	/**
	 * Il colore degli archi per gli imperatori.
	 */
	private final String STYLE_ARCHI_IMP = "strokeColor=C23B22";
	

	/**
	 * Costruisci il componente a partire da un albero genealogico.
	 * 
	 * @param albero L'albero genealogico.
	 */
	public ComponenteGrafoIllustrato(AlberoGenealogico albero) {
		// Grafo con l'albero genealogico
		Graph<Persona, Archi> grafo = albero.getAlbero();
		
		// Wrapper del Graph in mxGraph
		JGraphXAdapter<Persona, Archi> wrapGrafo = new JGraphXAdapter<Persona, Archi>(grafo);
		
		
		init(wrapGrafo, grafo);
	}
	

	/**
	 * Costruisci il componente con il grafo disegnato impostando
	 * i colori per i vertici e gli archi.
	 * 
	 * @param wrapGrafo Il grafo con il wrapper.
	 * @param grafo Il grafo.
	 */
	private void init(JGraphXAdapter<Persona, Archi> wrapGrafo, Graph<Persona, Archi> grafo) {
		// Prendo e coloro i vertici del grafo
		Map<Persona, mxICell> vertici = wrapGrafo.getVertexToCellMap();
		coloraVerticiGrafo(vertici, wrapGrafo);
		
		// Prendo e coloro gli archi del grafo
		Map<Archi, mxICell> archi = wrapGrafo.getEdgeToCellMap();
		coloraArchiGrafo(archi, wrapGrafo, grafo);
		
		// Costruisci il componente grafico
		component =  settaComponente(wrapGrafo);
	}
	
	/**
	 * Ritorna il componente con il grafo disegnato.
	 * 
	 * @return Il componente.
	 */
	public mxGraphComponent getComponente() {
			return component;
	}
	
	/**
	 * Colora i vertici del grafo a seconda se i vertici sono imperatori o no.
	 * 
	 * @param vertici I vertici del grafo.
	 * @param wrapGrafo Il wrapper con il grafo.
	 */
	private void coloraVerticiGrafo(Map<Persona, mxICell> vertici, JGraphXAdapter<Persona, Archi> wrapGrafo) {
		// La classe di mxGraph che si occupa degli stili di colore
		mxIGraphModel model = wrapGrafo.getModel();
		
		vertici.forEach((persona, cella) ->{
			
			// Controllo se il romano è un imperatore
			// e decido quale colorazione
			if (persona instanceof Imperatore) {
				model.setStyle(cella, STYLE_IMPERATORE);
			} else {
				model.setStyle(cella, STYLE_ROMANO);
			}

			// Cambia le misure del rettangolo appartenente del vertice
			mxGeometry geometriaCella = cella.getGeometry();
			geometriaCella.setWidth(geometriaCella.getWidth() + 10);
			geometriaCella.setHeight(30);
		});
		
	}
	
	/**
	 * Colora gli archi del grafo.
	 * 
	 * @param archi Gli archi del grafo.
	 * @param wrapGrafo Il wrapper con il grafo.
	 * @param grafo Il grafo.
	 */
	private void coloraArchiGrafo(Map<Archi, mxICell> archi, JGraphXAdapter<Persona, Archi> wrapGrafo, Graph<Persona, Archi> grafo) {
		// La classe di mxGraph che si occupa degli stili di colore

		mxIGraphModel model = wrapGrafo.getModel();

		archi.forEach((arco, cella) -> {
			
			Persona romano = grafo.getEdgeSource(arco);
			
			// Controllo se è un imperatore e decido lo stile di colore
			if (romano instanceof Imperatore) {
				model.setStyle(cella, STYLE_ARCHI_IMP);
			} else {
				model.setStyle(cella, STYLE_ARCHI);
			}
		});
	}

	/**
	 * Costruisce il componente con il grafo.
	 * 
	 * @param wrapGrafo Il wrapper con il grafo.
	 * @return Il componente.
	 */
	private mxGraphComponent settaComponente(JGraphXAdapter<Persona, Archi> wrapGrafo) {
		
		// Scelta layout grafico
		mxIGraphLayout layout = new mxHierarchicalLayout(wrapGrafo);
		
		// Applica il layot al grafo
		layout.execute(wrapGrafo.getDefaultParent());
		
		// Inizializzo il componente con il grafo
		mxGraphComponent componente = new mxGraphComponent(wrapGrafo);
		
		// Impostazioni visualizzazione del componente
		componente.getViewport().setOpaque(true);
		componente.getViewport().setBackground(new Color(243, 241, 233));
		componente.setEnabled(false);
		
		return componente;
	}

}
