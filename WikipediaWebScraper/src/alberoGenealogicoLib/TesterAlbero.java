package alberoGenealogicoLib;

import java.util.List;

public class TesterAlbero {
	
	public class PersonaFittizia extends Persona {

		public PersonaFittizia(String nome) {
			super(nome);
		}

		@Override
		public Boolean thisPersonIs() {
			return true;
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
	
	public void testGenerazioni() {

		albero.aggiungiAGenerazione(0, padre);
		albero.aggiungiAGenerazione(0, madre);
		albero.aggiungiAGenerazione(1, figlio1);
		albero.aggiungiAGenerazione(1, figlio2);
		albero.aggiungiAGenerazione(2, nipote1);
		albero.aggiungiAGenerazione(2, nipote2);
		
		System.out.println("\nTest aggiungiAGenerazione e getGenerazioniMap");
		System.out.println("Expected: {0=[Padre, Madre], 1=[Figlio 1, Figlio 2], 2=[Nipote 1, Nipote 2]}");
		System.out.println(albero.getGenerazioniMap());

	}


	public static void main(String args[]) {
		
		TesterAlbero test = new TesterAlbero();
		test.testInserimentoEGet();
		
		test.testGenerazioni();

	}

}
