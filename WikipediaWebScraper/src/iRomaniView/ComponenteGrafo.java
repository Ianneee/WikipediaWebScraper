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
import iRomaniModel.AnticoRomano;

import java.awt.Color;
import java.util.Map;


/**
 * La classe costruisce un Component con l'albero genealogico passato come argomento
 * disegnato.
 * 
 * @author Ian Tirso Cini
 *
 */
public class ComponenteGrafo {
	
	/**
	 * Il wrapper per il grafo.
	 */
	private JGraphXAdapter<Persona, Archi> grafo;
	
	private mxGraphComponent component;
	
	/**
	 * Lo stile di visualizzazione degli imperatori.
	 */
	private final String styleImperatore = "shape=ellipse;"
			+ "rounded=true;fontColor=080808;" 
			+ "fillColor=fde7b3;"
			+ "shadow=true;"
			+ "strokeColor=293133";
	
	/**
	 * Lo stile di visualizzazione dei romani.
	 */
	private final String styleRomano = "shape=rectangle;"
			+ "rounded=true;"
			+ "fontColor=080808;"	
			+ "fillColor=7cccbe;"
			+ "shadow=true;"
			+ "strokeColor=293133";
	
	/**
	 * Lo stile di visualizzazione degli archi
	 */
	private final String edgeStyle = "strokeColor=#000000;";
	
	/**
	 * Costruisci il componente a partire da un albero genealogico.
	 * @param albero
	 */
	public ComponenteGrafo(AlberoGenealogico albero) {
		
		Graph<Persona, Archi> grafo = albero.getAlbero();
		
		this.grafo = new JGraphXAdapter<Persona, Archi>(grafo);
	}
	
	/**
	 * Costruisci il componente con il grafo disegnato.
	 */
	public void init() {
		// Prendo e coloro i vertici del grafo
		Map<Persona, mxICell> vertici = grafo.getVertexToCellMap();
		coloraVerticiGrafo(vertici);
		
		// Prendo e coloro gli archi del grafo
		Map<Archi, mxICell> archi = grafo.getEdgeToCellMap();
		coloraArchiGrafo(archi);
		
		component =  settaComponente();
	}
	
	/**
	 * Ritorna il componente con il grafo disegnato.
	 * 
	 * @return Il componente.
	 */
	public mxGraphComponent getComponente() {
		if (component != null) {
			return component;
		}
		init();
		return component;
	}
	
	/**
	 * Colora i vertici del grafo a seconda se i vertici sono imperatori o no.
	 * 
	 * @param vertici I vertici del grafo.
	 */
	private void coloraVerticiGrafo(Map<Persona, mxICell> vertici) {

		mxIGraphModel model = grafo.getModel();
		
		vertici.forEach((persona, cella) ->{
			
			AnticoRomano romano = (AnticoRomano)persona;

			// Controllo se il romano è un imperatore o no e do uno stile diverso
			if (romano.isImperatore()) {
				model.setStyle(cella, styleImperatore);
			} else {
				model.setStyle(cella, styleRomano);
			}

			// Cambia le misure del vertice
			mxGeometry geometriaCella = cella.getGeometry();
			geometriaCella.setWidth(geometriaCella.getWidth() + 30);
			geometriaCella.setHeight(40);
			geometriaCella.translate(20.0, 60.0);
		});
		
	}
	
	/**
	 * Colora gli archi del grafo.
	 * 
	 * @param archi Gli archi del grafo.
	 */
	private void coloraArchiGrafo(Map<Archi, mxICell> archi) {
		mxIGraphModel model = grafo.getModel();
		
		for (mxICell cella : archi.values()) {
			
				model.setStyle(cella, edgeStyle);
				
		}
	}

	/**
	 * Costruisce il componente con il grafo.
	 * 
	 * @return Il componente.
	 */
	private mxGraphComponent settaComponente() {
		
		// Scelta layout grafico
		mxIGraphLayout layout = new mxHierarchicalLayout(grafo);
		layout.execute(grafo.getDefaultParent());
		
		// Inizializzo il componente
		mxGraphComponent componente = new mxGraphComponent(grafo);
		
		// Impostsazioni del componente
		componente.getViewport().setOpaque(true);
		componente.getViewport().setBackground(new Color(243, 241, 233));
		componente.setEnabled(false);
		
		return componente;
	}

}
