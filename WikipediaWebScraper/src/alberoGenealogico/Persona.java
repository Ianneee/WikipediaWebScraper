package alberoGenealogico;

public class Persona extends Tree{
	
	class Tester {

	}

	public Persona(String nome) {
		super(nome);
	}
	
	public String toString() {
		return super.toString() + "\n" +
	           "Tipo classe: Persona";
	}
}
