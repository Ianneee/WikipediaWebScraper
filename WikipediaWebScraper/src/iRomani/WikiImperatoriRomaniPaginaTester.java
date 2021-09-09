package iRomani;
import java.util.List;

public class WikiImperatoriRomaniPaginaTester {
	
	public static void main(String args[]) {
		WikiImperatoriRomaniPagina pagDinastie = WikiImperatoriRomaniPagina.getInstance();
		System.out.println("Titolo della pagina: " + pagDinastie.getTitle());
		System.out.println("Url della pagina: " + pagDinastie.getUrl());
		
		List<TabellaDinastie> dinastie = pagDinastie.getDinastie();
		System.out.println("\nElenco delle dinastie e imperatori:");
		for (TabellaDinastie tabella : dinastie) {
			 System.out.println("\nDinastia: " + tabella.getNomeDinastia() + " | Url: " + tabella.getUrlDinastia());
			 System.out.println("Elenco degli imperatori:");
			 for (TabellaDinastie.Riga riga : tabella) {
				 System.out.println("\t" + riga);
			 }
		 }
		 
		 String[] elencoDinastie = pagDinastie.getElencoDinastie();
		 System.out.println("\nElenco dinastie da array:");
		 for (String dinastia : elencoDinastie) {
			 System.out.println(dinastia);
		 }
		 
		 try {
			 System.out.println("\nRicerca dinastia per nome: " + elencoDinastie[4]);
			 TabellaDinastie dinastia = pagDinastie.getDinastia(elencoDinastie[4]);
			 System.out.println(dinastia);
		 } catch (DinastiaNonTrovataException errore) {
			 errore.printStackTrace();
		 }
		 
		 try {
			 System.out.println("\nRicerca dinastia per nome errato");
			 TabellaDinastie dinastiaErrata = pagDinastie.getDinastia("42");
			 System.out.println(dinastiaErrata);
		 } catch (DinastiaNonTrovataException errore) {
			 errore.printStackTrace();
		 }
		 
		 System.out.println("Test Concluso");
	}
}
