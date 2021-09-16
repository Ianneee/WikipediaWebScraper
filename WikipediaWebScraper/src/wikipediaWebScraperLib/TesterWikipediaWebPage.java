package wikipediaWebScraperLib;

public class TesterWikipediaWebPage {
	
	public static void main(String args[]) {
		WikipediaWebPage wiki = new WikipediaWebPage("www.wikipedia.it", "Wikipedia");
		
		System.out.println("Test getUrl - Expected: www.wikipedia.it");
		System.out.println(wiki.getUrl());
		
		System.out.println("Test getTitle - Expected: Wikipedia");
		System.out.println(wiki.getTitle());
	}

}
