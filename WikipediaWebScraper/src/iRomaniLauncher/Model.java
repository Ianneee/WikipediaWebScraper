package iRomaniLauncher;

import iRomani.WikiImperatoriRomaniPagina;
import iRomani.CostruisciAlberoGenealogico;
import iRomani.DinastiaNonTrovataException;

public class Model {
	
	WikiImperatoriRomaniPagina paginaImperatori;
	
	public Model() {}
	
	public void startModel() {
		paginaImperatori = WikiImperatoriRomaniPagina.getInstance();
	}
	
	public String[] getListaDinastie() {
		return paginaImperatori.getElencoDinastie();
	}
	
	public void alberoGenealogicoDinastia(String nomeDinastia) {
		// chiamare iromani.algoritmi
	}
	
	public static void main(String[] args) {
		Model pagina = new Model();
		pagina.startModel();
		String[] dinastie = pagina.getListaDinastie();
		
		for (String dinastia : dinastie) {
			try {
				CostruisciAlberoGenealogico albero = new CostruisciAlberoGenealogico(dinastia);
				albero.init();
			} catch (DinastiaNonTrovataException error) {
				error.printStackTrace();
			}
		}
	}
	
}
