package iRomaniView;

import java.awt.Color;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxICell;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxStylesheet;

import alberoGenealogicoLib.AlberoGenealogico;
import alberoGenealogicoLib.Archi;
import alberoGenealogicoLib.Persona;
import iRomaniModel.AnticoRomano;
import iRomaniModel.CostruisciAlberoGenealogico;
import iRomaniModel.DinastiaNonTrovataException;

public class TestACaso {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Grafo di prova");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		JGraphXAdapter<Persona, Archi> grafo = new JGraphXAdapter<>(buildGraph());
		// Passaggio grafo a wrapper Jgraphx adapter
//		JGraphXAdapter<Persona, Archi> grafo = new JGraphXAdapter<>(buildGraphNormale());
		
//		grafo.setEdgeLabelsMovable(false);
//		grafo.setCellsEditable(false);
//		grafo.setCellsMovable(false);
//		grafo.setCellsResizable(false);
//		grafo.setCellsSelectable(false);
//		grafo.setCellsLocked(true);
//		
//		grafo.setEnabled(false);
		
		
//		mxStylesheet stylesheet = grafo.getStylesheet();
		
//		grafo.getModel().beginUpdate();
//		try {
//		Hashtable<String, Object> style = new Hashtable<String, Object>();
//		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_HEXAGON);
//		style.put(mxConstants.STYLE_OPACITY, 50);
//		style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
//		stylesheet.putCellStyle(mxConstants.STYLE_ROUNDED, style);
//		
//		Object[] celle = grafo.getCellToVertexMap().keySet().toArray();
////		
//		grafo.setCellStyle("shape=rectangle;rounded=true;fontColor=080808;"+mxConstants.STYLE_FILLCOLOR+"=ccccff", celle);
//		
//		Map<Persona, mxICell> vertici = grafo.getVertexToCellMap();
//		for (Persona romani : vertici.keySet()) {
//			AnticoRomano romano = (AnticoRomano)romani;
//			if (romano.getImperatore()) {
//				mxICell cellaGrafo = vertici.get(romani);
//				mxGeometry geometry = cellaGrafo.getGeometry();
//				geometry.setWidth(geometry.getWidth() + 20);
//				geometry.setHeight(35);
//				geometry.translate(20.0, 60.0);
//				cellaGrafo.setStyle("shape=rectangle;rounded=true;fontColor=080808;"+mxConstants.STYLE_FILLCOLOR+"=ccccff");
//			} else {
//
//			}
//		}
//		Map<mxICell, Archi> archi = grafo.getCellToEdgeMap();
//		
//		for (Object cella : celle) {
//			mxCell cellaGrafo = (mxCell)cella;
//			mxGeometry geometry = cellaGrafo.getGeometry();
//			geometry.setWidth(geometry.getWidth() + 20);
//			geometry.setHeight(35);
//			geometry.translate(20.0, 60.0);
//			cellaGrafo.setStyle("shape=rectangle;rounded=true;fontColor=080808;"+mxConstants.STYLE_FILLCOLOR+"=ccccff");
//		}

//		grafo.clearSelection(); 
//		grafo.selectAll();
//		Object[] cells = grafo.getSelectionCells(); //here you have all cells

		// Iterate into graph to change cells
//		for (Object c : cells) {
//			mxCell cell = (mxCell) c; //cast
//			mxGeometry geometry = cell.getGeometry();
////			String stileCella = cell.getStyle();
//			
//			Object[] celle = grafo.getCellToVertexMap().keySet().toArray();
//			
//			grafo.setCellStyle("shape=rectangle;rounded=true;fontColor=080808;"+mxConstants.STYLE_FILLCOLOR+"=ccccff", celle);
//
//			if (cell.isVertex()) { //isVertex
//				// Here I can change vertex dimensions 
//				geometry.setWidth(geometry.getWidth() + 20);
//				geometry.setHeight(35);
//				geometry.translate(20.0, 60.0);
////				cell.setStyle("shape=cloud;rounded=true;fontColor=ffffc800;");
//				System.out.println(cell.getStyle());
//			}
//
//		}
		
			
//			Object[] mappa =  grafo.getCellToVertexMap().keySet().toArray();
//			grafo.setCellStyle(mxConstants.SHAPE_HEXAGON, mappa);
//		} finally {
//			grafo.getModel().endUpdate();
//		}
//		
//		grafo.getModel().beginUpdate();
//		try {
//			Object[] mappa =  grafo.getCellToVertexMap().keySet().toArray();
//			for (Object obj : mappa) {
//				mxICell cella = (mxICell)obj;
//				cella.setStyle("ROUNDED=1");
//				System.out.println(cella);
//				System.out.println(cella.getStyle());
//			}
//		} finally {
//			grafo.getModel().endUpdate();
//		}
		

//		grafo.getModel().beginUpdate();
//		try {
//			grafo.setCellStyle(mxConstants.SHAPE_CYLINDER, grafo.getCellToEdgeMap().keySet().toArray());
//		} finally {
//			grafo.getModel().endUpdate();
//		}
		
		AlberoGenealogico albero = buildGraphNormale();
		ComponenteGrafo costruttoreComponente = new ComponenteGrafo(albero);
		mxGraphComponent component = costruttoreComponente.init();
		
//		mxIGraphLayout layout = new mxHierarchicalLayout(grafo);
//		layout.execute(grafo.getDefaultParent());
//		mxGraphComponent component = new mxGraphComponent(grafo);
////		component.setDragEnabled(false);
//		component.getViewport().setOpaque(true);
//		component.getViewport().setBackground(Color.WHITE);
//		component.setEnabled(false);
		frame.add(component);
		frame.pack();
//		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}



	public static ListenableGraph<Persona, Archi> buildGraph(){
	    	try {
	    	CostruisciAlberoGenealogico albero = new CostruisciAlberoGenealogico("Dinastia giulio-claudia");
	    	List<AlberoGenealogico> alberi = albero.getAlberiGenealogici();
	    	SimpleDirectedWeightedGraph<Persona, Archi> grafo = (SimpleDirectedWeightedGraph<Persona, Archi>)alberi.get(0).getAlbero();
	    	
	        DefaultListenableGraph<Persona, Archi> g = new DefaultListenableGraph<Persona, Archi>(grafo);
	        return g;
	    	} catch (DinastiaNonTrovataException error) {
	    		error.printStackTrace();
	    	}
	    	return null;
	}
	
	public static AlberoGenealogico buildGraphNormale(){
    	try {
    	CostruisciAlberoGenealogico albero = new CostruisciAlberoGenealogico("Dinastia dei Flavi");
    	List<AlberoGenealogico> alberi = albero.getAlberiGenealogici();
    	return alberi.get(0);
    	} catch (DinastiaNonTrovataException error) {
    		error.printStackTrace();
    	}
    	return null;
	}
}
