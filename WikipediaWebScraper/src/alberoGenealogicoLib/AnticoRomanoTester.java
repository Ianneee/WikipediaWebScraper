package alberoGenealogicoLib;

public class AnticoRomanoTester {
	public static void main(String[] args) {
		
		AnticoRomano Giulio = new AnticoRomano("Giulio");
		AnticoRomano Adriano = new AnticoRomano("Adriano");
		AnticoRomano Cesare = new AnticoRomano("Cesare");
		AnticoRomano Tullio = new AnticoRomano("Tullio");
		AnticoRomano Livia = new AnticoRomano("Livia");
		
		Giulio.aggiungiFiglio(Adriano);
		Giulio.aggiungiFiglio(Cesare);
		Cesare.aggiungiFiglio(Tullio);
		Tullio.aggiungiFiglio(Livia);
		
		Persona.visitPreOrder(Giulio);
		
	}
}
