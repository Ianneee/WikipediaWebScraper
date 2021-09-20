package iRomaniModel;

import java.util.Objects;

import wikipediaWebScraperLib.PaginaWikipedia;

import alberoGenealogicoLib.Persona;

/**
 * La classe rappresenta un antico romano, personaggio storico, costruito in base
 * alle informazioni reperite su Wikipedia.
 * 
 * @author Ian Tirso Cini
 *
 */
public class AnticoRomano implements Persona, Comparable<AnticoRomano>{

	/**
	 * Il nome dell'antico romano.
	 */
	private String nome;
	
	/**
	 * L'url della pagina Wikipedia dell'antico romano corrispondente.
	 */
	private String url;
	
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
		this.nome = nome;
		this.url = url;
	}
	
	/**
	 * Ritorna l'url della pagina Wikipedia dell'antico romano.
	 * 
	 * @return L'url.
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Ritorna il nome dell'antico romano.
	 */
	public String getNome() {
		return nome;
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
		return nome.compareTo(romano.getNome());
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
		return nome;
	}

}
