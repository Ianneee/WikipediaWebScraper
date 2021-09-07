package alberoGenealogico;

public class PersonaTester {
	public static void main(String[] args) {
		Persona ciccio = new Persona("ciccio");
		System.out.println(ciccio.toString());
		
		Persona franko = new Persona("frank");
		ciccio.addParent(franko);
		System.out.println(ciccio.toString());
	}

}
