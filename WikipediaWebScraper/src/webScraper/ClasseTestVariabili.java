package webScraper;

public class ClasseTestVariabili {
	public static void addValue(Integer n) {
		n = n+2;
		n = n++;
		System.out.println(n);
	}
	
	public static String editStringa(String s) {
		int start = 5;
		s = s.substring(start);
		System.out.println(s);
		return s;
	}
	
	public static void main(String args[]) {
//		Integer numero = 2;
//		addValue(numero);
//		numero++;
//		System.out.println(numero);
		String riga = "Ciao come stai?";
		riga = editStringa(riga);
		System.out.println(riga);
	}
}
