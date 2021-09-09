package iRomaniLauncher;

import iRomani.WikiImperatoriRomaniPagina;
import iRomani.TabellaDinastie;
import iRomani.CostruisciAlberoGenealogico;

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
			System.out.println(dinastia);
		}
	}
	
}
