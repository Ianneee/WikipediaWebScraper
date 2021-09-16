package alberoGenealogicoLib;

public class TesterPersona {
	
	public class PersonaFittizia extends Persona {

		public PersonaFittizia(String nome) {
			super(nome);
		}

		@Override
		public boolean thisPersonIs() {
			return true;
		}
		
	}
	
	public void testPersona() {
		Persona persona = new PersonaFittizia("Rossi Mario");
		
		String nome = persona.getNome();
		
		System.out.println("Test getNome - Expected: Rossi Mario");
		System.out.println(nome);
		
		Boolean bool = persona.thisPersonIs();
		System.out.println("Test thisPersonIs - Expected: true");
		System.out.println(bool);
		
		System.out.println("toString:");
		System.out.println(persona);
		
	}
	
	public static void main(String[] args) {
				
		TesterPersona test = new TesterPersona();
		
		test.testPersona();
	}

}
