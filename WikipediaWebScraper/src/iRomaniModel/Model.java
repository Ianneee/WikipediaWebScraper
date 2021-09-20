package iRomaniModel;

import java.util.List;

import alberoGenealogicoLib.AlberoGenealogico;

import wikipediaWebScraperLib.WikipediaUrlErratoException;

/**
 * Classe model che permette l'interazione con le classi che elaborano i dati
 * riguardanti il progetto.
 * 
 * @author Ian Tirso Cini
 *
 */
public class Model {
	
	/**
	 * Istanza di WikiImperatoriPagina
	 */
	WikiImperatoriRomaniPagina paginaImperatori;
	
	/**
	 * Istanzia la pagina contenente l'elenco delle dinastie degli imperatori romani.
	 */
	public void startModel() {
		paginaImperatori = WikiImperatoriRomaniPagina.getInstance();
	}
	
	/**
	 * Ritorna la lista delle dinastie.
	 * 
	 * @return L'array con le dinastie.
	 */
	public String[] getListaDinastie() {
		return WikiImperatoriRomaniPagina.getInstance().getElencoDinastie();
	}
	
	/**
	 * Ritorna gli alberi genealogici della dinastia che viene passata come argomento.
	 * 
	 * @param nomeDinastia Il nome della dinastia desiderata.
	 * @return La lista con gli AlberiGenealogici scelti.
	 * @throws DinastiaNonTrovataException L'errore con il nome della dinastia errata.
	 * @throws WikipediaUrlErratoException L'errore con il sito Wikipedia passato in formato errato.
	 */
	public List<AlberoGenealogico> alberoGenealogicoDinastia(String nomeDinastia) throws DinastiaNonTrovataException, WikipediaUrlErratoException {


		CostruisciAlberoGenealogico generatore = new CostruisciAlberoGenealogico(nomeDinastia);
		
		// Costruisco l'albero genealogico della dinastia tramite scraping
		generatore.init();
		
		return generatore.getAlberiGenealogici();
		
	}
	
}
