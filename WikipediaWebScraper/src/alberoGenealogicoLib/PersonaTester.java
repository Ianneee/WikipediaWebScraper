package alberoGenealogicoLib;

public class PersonaTester {
	public static void main(String[] args) {
		PersonaEstesa ciccio = new PersonaEstesa("ciccio");
		System.out.println(ciccio.toString());
		
		PersonaEstesa franko = new PersonaEstesa("frank");
		ciccio.aggiungiConiuge(franko);
		System.out.println(ciccio.toString());
	}

}
