package alberoGenealogicoLib;

import alberoGenealogicoLib.Persona.Sesso;

public class PersonaTester {
	
	public static void main(String[] args) {
		Persona uomo = new Persona("Mario Rossi", Sesso.UOMO);
		System.out.println(uomo);
		System.out.println(uomo.getNome());
		System.out.println(uomo.getSesso());
		
		Persona donna = new Persona("Maria Rossa", Sesso.DONNA);
		System.out.println(donna);
		System.out.println(donna.getNome());
		System.out.println(donna.getSesso());
		
	}

}
