package iRomaniView;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DefaultListenableGraph;

import com.google.common.graph.Graph;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;

import alberoGenealogicoLib.AlberoGenealogico;
import alberoGenealogicoLib.Persona;

import java.util.List;

import iRomani.CostruisciAlberoGenealogico;
import iRomani.DinastiaNonTrovataException;

import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import com.mxgraph.layout.mxCompactTreeLayout;

public class TestView {
	 private static void createAndShowGui() {
	        JFrame frame = new JFrame("DemoGraph");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        ListenableGraph<Persona, DefaultWeightedEdge> g = buildGraph();
	        JGraphXAdapter<Persona, DefaultWeightedEdge> graphAdapter = 
	                new JGraphXAdapter<Persona, DefaultWeightedEdge>(g);

	        mxIGraphLayout layout = new mxHierarchicalLayout(graphAdapter);
	        layout.execute(graphAdapter.getDefaultParent());

	        frame.add(new mxGraphComponent(graphAdapter));

	        frame.pack();
	        frame.setLocationByPlatform(true);
	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGui();
	            }
	        });
	    }

	    public static class MyEdge extends DefaultWeightedEdge {
	        @Override
	        public String toString() {
	            return String.valueOf(getWeight());
	        }
	    }

	    public static ListenableGraph<Persona, DefaultWeightedEdge> buildGraph() {
	    	try {
	    	CostruisciAlberoGenealogico albero = new CostruisciAlberoGenealogico("Dinastia giulio-claudia");
	    	List<AlberoGenealogico> alberi = albero.getAlberiGenealogici();
	    	SimpleDirectedWeightedGraph<Persona, DefaultWeightedEdge> grafo = (SimpleDirectedWeightedGraph<Persona, DefaultWeightedEdge>)alberi.get(0).getAlbero();
	    	
//	    	SimpleDirectedWeightedGraph<Persona, DefaultWeightedEdge> grafo = ((SimpleDirectedWeightedGraph)alberi.get(0).getAlbero());
	        DefaultListenableGraph<Persona, DefaultWeightedEdge> g = 
	            new DefaultListenableGraph<Persona, DefaultWeightedEdge>(grafo);

//	        String x1 = "x1";
//	        String x2 = "x2";
//	        String x3 = "x3";
//
//	        g.addVertex(x1);
//	        g.addVertex(x2);
//	        g.addVertex(x3);
//
//	        MyEdge e = g.addEdge(x1, x2);
//	        g.setEdgeWeight(e, 1);
//	        e = g.addEdge(x2, x3);
//	        g.setEdgeWeight(e, 2);
//
//	        e = g.addEdge(x3, x1);
//	        g.setEdgeWeight(e, 3);

	        return g;
	    	} catch (DinastiaNonTrovataException error) {
	    		error.printStackTrace();
	    	}
	    	return null;
	    }

}
