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
	 * Il wrapper per il grafo.
	 */
	private JGraphXAdapter<Persona, Archi> grafo;
	
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
	 * Il grafo della dinastia.
	 */
	private	Graph<Persona, Archi> grafoOriginale;
	
	/**
	 * Costruisci il componente a partire da un albero genealogico.
	 * @param albero
	 */
	public ComponenteGrafoIllustrato(AlberoGenealogico albero) {
		
		grafoOriginale = albero.getAlbero();
		
		this.grafo = new JGraphXAdapter<Persona, Archi>(grafoOriginale);
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
		
		// Costruisci il componente grafico
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
			
//			AnticoRomano romano = (AnticoRomano)persona;

			// Controllo se il romano è un imperatore (polimorfismo p.thisPersonIs)  
			if (persona.thisPersonIs()) {
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
	 */
	private void coloraArchiGrafo(Map<Archi, mxICell> archi) {
		mxIGraphModel model = grafo.getModel();

		archi.forEach((arco, cella) -> {
			
			Persona romano = grafoOriginale.getEdgeSource(arco);
			
			// Controllo se è un imperatore (polimorfismo p.thisPersonIs)
			if (romano.thisPersonIs()) {
				
				model.setStyle(cella, STYLE_ARCHI_IMP);
				
			} else {
				
				model.setStyle(cella, STYLE_ARCHI);
				
			}
		});
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
		
		// Inizializzo il componente contenente il grafo
		mxGraphComponent componente = new mxGraphComponent(grafo);
		
		// Impostazioni visualizzazione del componente
		componente.getViewport().setOpaque(true);
		componente.getViewport().setBackground(new Color(243, 241, 233));
		componente.setEnabled(false);
		
		return componente;
	}

}
