package alberoGenealogicoLib;

import java.util.List;
import alberoGenealogicoLib.Persona.Sesso;

public class AlberoTester {
	
	public static void main(String args[]) {
		AlberoGenealogico albero = new AlberoGenealogico("I Cesaroni");
		
		Persona cesare = new Persona("Cesare Cesaroni", Sesso.UOMO);
		
		albero.addPersona(cesare);
		albero.addPersona(cesare);
		
		Persona cesara = new Persona("Cesara la Moglie", Sesso.DONNA);
		Persona cesarino = new Persona("Cesarino Figlio 1 Cesarini", Sesso.UOMO);
		Persona cesariella = new Persona("Cesariella Figlio 2 Cesaroni", Sesso.DONNA);
		
		
		albero.addFiglio(cesare, cesarino);
		albero.addFiglio(cesare, cesariella);
		albero.addFiglio(cesara, cesarino);
		albero.addConiuge(cesare, cesara);
		albero.addFiglio(cesare, cesariella);
		
		
		System.out.println(albero);
		
		albero.getConiugi(cesare);
		
		System.out.println("\nI figli di Cesare Cesaroni");
		List<Persona> figliCesare = albero.getFigli(cesare);
		for (Persona figlio : figliCesare) {
			System.out.println(figlio);
		}
		
		System.out.println("\nI figli di Cesara Cesarona");
		List<Persona> figliCesara = albero.getFigli(cesara);
		for (Persona figlio : figliCesara) {
			System.out.println(figlio);
		}

	}

}
