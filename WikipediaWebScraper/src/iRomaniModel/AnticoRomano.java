package iRomaniModel;

import java.util.Objects;
import wikipediaWebScraperLib.PaginaWikipedia;

import alberoGenealogicoLib.Persona;

public class AnticoRomano extends Persona implements Comparable<AnticoRomano>{
	
	/**
	 * L'url della pagina Wikipedia dell'antico romano corrispondente.
	 */
	private String url;
	
	/**
	 * L'url dell'immagine su Wikipedia.
	 */
	private String urlImmagine;
	
	/**
	 * Booleano che indica se l'antico romano è stato un imperatore.
	 */
	private Boolean isImperatore = false;
	
	/**
	 * La PaginaWikipedia del personaggio storico.
	 */
	private PaginaWikipedia paginaWikipedia;
	
	/**
	 * Costruisce un AnticoRomano, personaggio storico preso da Wikipedia
	 * fornendo nome e url corrispondenti.
	 * 
	 * @param nome Il nome della persona.
	 * @param url L'url della pagina Wikipedia.
	 */
	public AnticoRomano(String nome, String url) {
		super(nome, Sesso.UOMO);
		this.url = url;
	}
	
	/**
	 * Ritorna l'url della pagina Wikipedia dell'antico romano.
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Registra l'url dell'immagine di Wikipedia.
	 * 
	 * @param urlImmagine L'url dell'immagine.
	 */
	public void setUrlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
	}
	
	/**
	 * Ritorna l'url dell'immagine.
	 * 
	 * @return L'url dell'immagine.
	 */
	public String getUrlImmagine() {
		return urlImmagine;
	}
	
	/**
	 * Imposta l'AnticoRomano come imperatore.
	 */
	public void setImperatore() {
		isImperatore = true;
	}
	
	/**
	 * Mette falso allo stato di imperatore.
	 */
	public void unsetImperatore() {
		isImperatore = false;
	}
	
	/**
	 * Ritorna vero se il personaggio storico è stato segnato come imperatore,
	 * falso altrimenti.
	 * 
	 * @return Booleano che indica se fu un imperatore o no.
	 */
	public Boolean isImperatore() {
		return this.isImperatore;
	}
	
	/**
	 * Aggiunge la paginaWikipedia al personaggio storico.
	 * 
	 * @param paginaWikipedia La pagina Wikipedia.
	 */
	public void setPaginaWikipedia(PaginaWikipedia paginaWikipedia) {
		this.paginaWikipedia = paginaWikipedia;
	}
	
	/**
	 * Ritorna la pagina Wikipedia del personaggio storico.
	 * 
	 * @return La pagina Wikipedia.
	 */
	public PaginaWikipedia getPaginaWikipedia() {
		return paginaWikipedia;
	}
	
	/**
	 * Confronta se due oggetti AnticoRomano sono uguali.
	 * Il confronto viene eseguito sull'url della pagina Wikipedia corrispondente
	 * in quanto elemento unico e distintivo tra due personaggi differenti.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		}
		if (o == this) {
			return true;
		}
		AnticoRomano input = (AnticoRomano)o;
		return input.getUrl().equals(this.getUrl());
	}
	
	/**
	 * Il confronto viene eseguito tra i nomi di due oggetti per ordinarli
	 * in maniera lessicografica.
	 */
	@Override
	public int compareTo(AnticoRomano romano) {
		return this.getNome().compareTo(romano.getNome());
	}
	
	/**
	 * Calcolo dell'hashcode dell'oggetto dall'url della pagina Wikipedia
	 * corrispondente al personaggio.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(url);
	}
	
	@Override
	public String toString() {
		if (isImperatore) {
			return "Imperatore\n" + super.getNome();
		}
		return super.getNome();
	}

}
