package alberoGenealogicoLib;

import java.util.List;

public class TesterAlbero {
	
	public class PersonaFittizia implements Persona {
		
		String nome;
		
		public PersonaFittizia(String nome) {
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
	Persona padre = new PersonaFittizia("Padre");
	Persona madre = new PersonaFittizia("Madre");
	Persona figlio1 = new PersonaFittizia("Figlio 1");
	Persona figlio2 = new PersonaFittizia("Figlio 2");
	Persona nipote1 = new PersonaFittizia("Nipote 1");
	Persona nipote2 = new PersonaFittizia("Nipote 2");
	
	public TesterAlbero() {
		
		// Costruzione albero genealogico
		albero.addFiglio(padre, figlio1);
		albero.addFiglio(madre, figlio1);
		
		albero.addFiglio(padre, figlio2);
		albero.addFiglio(madre, figlio2);
		
		albero.addFiglio(figlio1, nipote1);
		albero.addFiglio(figlio2, nipote2);
		
	}
	
	public void testInserimentoEGet() {
		
		
		System.out.println("Test getCapostipite - Expected: Padre");	
		albero.setCapostipite(padre);
		Persona capostipite = albero.getCapostipite();
		System.out.println(capostipite);
		
		
		System.out.println("Test getFigli - Expected: [Figlio 1, Figlio 2]");
		List<Persona> figliDelPadre = albero.getFigli(padre);
		System.out.println(figliDelPadre);
		
		
		System.out.println("Test getGenitori - Expected: [Padre, Madre]");
		List<Persona> genitoriFiglio1 = albero.getGenitori(figlio1);
		System.out.println(genitoriFiglio1);
		
		
		System.out.println("Test getGenitori - Expected: [Figlio 1]");
		List<Persona> genitoriNipote1 = albero.getGenitori(nipote1);
		System.out.println(genitoriNipote1);
	}


	public static void main(String args[]) {
		
		TesterAlbero test = new TesterAlbero();
		test.testInserimentoEGet();
		
	}

}
