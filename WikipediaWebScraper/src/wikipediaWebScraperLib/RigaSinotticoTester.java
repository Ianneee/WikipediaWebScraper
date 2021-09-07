package wikipediaWebScraperLib;

public class RigaSinotticoTester {
	public static void main(String args[]) {
		RigaSinottico sinottico = new RigaSinottico("Categoria test");
			
		sinottico.addInformazione("Info 1");
		sinottico.addInformazione("Info 2 - Senza Link");
		sinottico.addInformazione("Info 3", "https://it.wikipedia.org/wiki/testinfo3");
		sinottico.addUrl("Info 1","https://it.wikipedia.org/wiki/ test info 1");
		sinottico.addInformazione("Info 4 - Link formato errato", "www.ciao.com");
		
		System.out.println(sinottico);
		System.out.println();
		
		System.out.println("getCategoria - Expected: Categoria test");
		System.out.println(sinottico.getCategoria() + "\n");
		
		// Test for-each
		for (RigaSinottico.Informazione info : sinottico) {
			System.out.println("Nome informazione: " + info.getNomeInfo());
			System.out.println("Link: " + info.getUrl());
		}
	}

}
