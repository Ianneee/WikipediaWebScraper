package alberoGenealogicoLib;

public class TesterPersona {
	
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
	
	public void testPersona() {
		Persona persona = new PersonaFittizia("Rossi Mario");
		
		String nome = persona.getNome();
		
		System.out.println("Test getNome - Expected: Rossi Mario");
		System.out.println(nome);
		
		System.out.println("toString:");
		System.out.println(persona);
		
	}
	
	public static void main(String[] args) {
				
		TesterPersona test = new TesterPersona();
		
		test.testPersona();
	}

}
