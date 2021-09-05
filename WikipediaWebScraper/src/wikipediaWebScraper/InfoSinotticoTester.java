package wikipediaWebScraper;

public class InfoSinotticoTester {
	public static void main(String args[]) {
		RigaSinottico sinottico = new RigaSinottico("Categoria test");
		sinottico.addInformazione("Info 1");
		sinottico.addInformazione("Info 2");
		sinottico.addInformazione("Info 3", "https://it.wikipedia.org/wiki/testinfo3");
		sinottico.addUrl("Info 1","https://it.wikipedia.org/wiki/ test info 1");
		sinottico.addInformazione("Info 4", "www.ciao.com");
		System.out.println(sinottico.toString());
		System.out.println();
		for (RigaSinottico.Informazione inf : sinottico) {
			System.out.println("Nome informazione: " + inf.getNomeInfo());
			System.out.println("Link: " + inf.getUrl());
		}
	}

}
