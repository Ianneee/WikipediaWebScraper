package iRomaniView;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mxgraph.swing.mxGraphComponent;

import alberoGenealogicoLib.AlberoGenealogico;
import alberoGenealogicoLib.Persona;

public class TesterComponenteGrafo {

	public class PersonaTest implements Persona {

		String nome;
		
		public PersonaTest(String nome) {
			this.nome = nome;
		}
		
		public String getNome() {
			return nome;
		}
		
		@Override
		public String toString() {
			return nome;
		}
		
	}
	
	// Oggetti per il test
	AlberoGenealogico albero = new AlberoGenealogico("Famiglia di prova");
	PersonaTest padre = new PersonaTest("Padre");
	PersonaTest madre = new PersonaTest("Madre");
	PersonaTest figlio1 = new PersonaTest("Figlio 1");
	PersonaTest figlio2 = new PersonaTest("Figlio 2");
	PersonaTest nipote1 = new PersonaTest("Nipote 1");
	PersonaTest nipote2 = new PersonaTest("Nipote 2");
	
	// Costruzione albero da rappresentare
	public TesterComponenteGrafo() {
		
		// Costruzione albero genealogico
		albero.addFiglio(padre, figlio1);
		albero.addFiglio(madre, figlio1);
		
		albero.addFiglio(padre, figlio2);
		albero.addFiglio(madre, figlio2);
		
		albero.addFiglio(figlio1, nipote1);
		albero.addFiglio(figlio2, nipote2);
		
	}
	
	public mxGraphComponent test() {
		
		ComponenteGrafoIllustrato componente = new ComponenteGrafoIllustrato(albero);
		return componente.getComponente();
		
	}	
	
	public void costruisciFinestra(String titoloFinestra, mxGraphComponent component) {
		// Costruzione JFrame
		JFrame frame = new JFrame(titoloFinestra);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLayout(null);
		
		// Aggiunta componente con grafo
		component.setBounds(0, 0, 500, 500);
		frame.add(component);
		
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		TesterComponenteGrafo test = new TesterComponenteGrafo();
		
		test.costruisciFinestra("Padre Rosso", test.test());
		
	}

}
