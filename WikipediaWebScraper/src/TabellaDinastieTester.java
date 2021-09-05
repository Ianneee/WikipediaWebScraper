import java.util.List;

public class TabellaDinastieTester {
	
	public static void main(String args[]) {

		TabellaDinastie tabella = new TabellaDinastie("Dinastia di prova");
		tabella.nuovaRiga("Imperatore imperatoris", "www.prova.com");
		tabella.nuovaRiga("Imperatore num due", "www.prova.com");
		List<TabellaDinastie.Riga> righe = tabella.getRighe();
		System.out.println(righe);
		
		System.out.println("\nTest for each della classe:");
		// Test per il for each
		for (TabellaDinastie.Riga riga : righe) {
			System.out.println(riga);
			System.out.println(riga.getNomeImperatore());
			System.out.println(riga.getUrlPagina() + "\n");
			
		}
	}

}
