package alberoGenealogicoLib;

public abstract class PersonaEstesa extends Persona{
	
	class Tester {

	}

	public PersonaEstesa(String nome) {
		super(nome);
	}
	
	public String toString() {
		return super.toString() + "\n" +
	           "Tipo classe: Persona";
	}
}
