package wikipediaWebScraper;

public class SinotticoTester {
	public static void main(String args[]) {
		Sinottico sinottico = new Sinottico();
		RigaSinottico riga1 = new RigaSinottico("Riga n1");
		riga1.addInformazione("Info 1");
		riga1.addInformazione("Info 2");
		riga1.addInformazione("Info 3", "https://it.wikipedia.org/wiki/testinfo3");
		riga1.addUrl("Info 1","https://it.wikipedia.org/wiki/ test info 1");
		riga1.addInformazione("Info 4", "www.ciao.com");
		
		sinottico.addRiga(riga1);
		
		sinottico.creaRiga("Riga n2");
		sinottico.addInfoToRiga("Riga n2", "Tester 1");
		sinottico.addUrlInfoToRiga("Riga n2", "Tester 2", "https://it.wikipedia.org/wiki/testerrrr");
		
		sinottico.creaRiga("Riga n3");
		sinottico.addInfoToRiga("Riga n3", "Tester 22");
		
		for (RigaSinottico riga : sinottico) {
			System.out.println("***************");
			System.out.println(riga.getCategoria());
			for (RigaSinottico.Informazione info : riga) {
				System.out.println(info.getNomeInfo() + " : " + info.getUrl());
			}
		}
	}

}
