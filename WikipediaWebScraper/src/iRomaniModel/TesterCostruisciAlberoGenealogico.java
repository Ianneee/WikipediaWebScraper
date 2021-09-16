package iRomaniModel;

import wikipediaWebScraperLib.WikipediaUrlErratoException;

public class TesterCostruisciAlberoGenealogico {
	
	public static void main(String[] args) {
		CostruisciAlberoGenealogico albero = null;
		
		try {
			albero = new CostruisciAlberoGenealogico("Guerra civile romana (306-324)");
		} catch (DinastiaNonTrovataException error) {
			error.printStackTrace();
		}
		
		try {
			albero.init();
		} catch (WikipediaUrlErratoException error) {
			error.printStackTrace();
		}
		
		System.out.println("Test guerra civile romana getAlberiGenealogici - Expected:");
		System.out.println("[([👑 "
				+ "Flavio Severo, Severiano], [=(👑 "
				+ "Flavio Severo,Severiano)]), ([👑 "
				+ "Licinio, Valerio Liciniano Licinio], [=(👑 "
				+ "Licinio,Valerio Liciniano Licinio)]), ([👑 "
				+ "Massimino Daia], [])]");
		
		System.out.println("\n" + albero.getAlberiGenealogici());
		
		try {
			albero = new CostruisciAlberoGenealogico("42");
		} catch (DinastiaNonTrovataException error){
			error.printStackTrace();
			System.out.println("Dinastia nome errato\n");
		}
		
	}
}
