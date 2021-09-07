package alberoGenealogico;

public class AnticoRomanoTester {
	public static void main(String[] args) {
		
		AnticoRomano Giulio = new AnticoRomano("Giulio");
		AnticoRomano Adriano = new AnticoRomano("Adriano");
		AnticoRomano Cesare = new AnticoRomano("Cesare");
		AnticoRomano Tullio = new AnticoRomano("Tullio");
		AnticoRomano Livia = new AnticoRomano("Livia");
		
		Giulio.addSon(Adriano);
		Giulio.addSon(Cesare);
		Cesare.addSon(Tullio);
		Tullio.addSon(Livia);
		
		Tree.visitPreOrder(Giulio);
		
	}
}
