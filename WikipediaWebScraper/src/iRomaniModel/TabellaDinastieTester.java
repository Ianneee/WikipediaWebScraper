package iRomaniModel;
import java.util.List;

public class TabellaDinastieTester {
	
	public static void main(String args[]) {

		TabellaDinastie tabella = new TabellaDinastie("Dinastia di prova");
		tabella.setUrlDinastia("www.Dinastia di Prova.it");
		System.out.println("Nome dinastia: " + tabella.getNomeDinastia());
		System.out.println("Url dinastia: " + tabella.getUrlDinastia());
		
		tabella.nuovaRiga("Imperatore numero 1", "www.imperatoreuno.com");
		tabella.nuovaRiga("Imperatore numero 2", "www.imperatoredue.com");
		
		List<TabellaDinastie.Riga> righe = tabella.getRighe();
		System.out.println(righe);
		
		System.out.println("\nTest for-each della classe:");
		// Test per il for each
		for (TabellaDinastie.Riga riga : righe) {
			System.out.println(riga.getNomeImperatore());
			System.out.println(riga.getUrlPagina() + "\n");
		}
		
		TabellaDinastie.Riga riga1 = tabella.getRiga(1);
		System.out.println("\nRiga numero 1:\nExpected: Imperatore numero 1 | www.imperatoreuno.com\n" + riga1);
		
		TabellaDinastie.Riga rigaErrata = tabella.getRiga(42);
		System.out.println("\nRiga numero 42:\nExpected: null\n" + rigaErrata);
		
		TabellaDinastie.Riga rigaPerNome = tabella.getRiga("Imperatore numero 1");
		System.out.println("\nRiga per nome:\nExpected: Imperatore numero 1 | www.imperatoreuno.com\n" + rigaPerNome);
		
		TabellaDinastie.Riga rigaNomeErrato = tabella.getRiga("Imperatore 42");
		System.out.println("\nRiga per nome:\nExpected: null\n" + rigaNomeErrato);
		
		System.out.println("\nTest getUrlImperatori - Expected: [www.imperatoreuno.com, www.imperatoredue.com]");
		List<String> urlImperatori = tabella.getUrlImperatori();
		System.out.println(urlImperatori);
		
		System.out.println("\nTest tabella vuota");
		TabellaDinastie tabellaVuota = new TabellaDinastie("");
		System.out.println("Test getNomeDinastia - Expected: ");
		System.out.println(tabellaVuota.getNomeDinastia());
		System.out.println("Test getUrlDinastia - Expected: null");
		System.out.println(tabellaVuota.getUrlDinastia());
		System.out.println("Test getRiga - Expected: null");
		System.out.println(tabellaVuota.getRiga(42));
		System.out.println("Test getRighe - Expected: lista vuota");
		System.out.println(tabellaVuota.getRighe());
		System.out.println("Test getUrlImperatori - Expected: lista vuota");
		System.out.println(tabellaVuota.getUrlImperatori());
		
	}

}
