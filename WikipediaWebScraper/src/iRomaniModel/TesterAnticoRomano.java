package iRomaniModel;

public class TesterAnticoRomano {
	
	public static void main(String[] args) {
		AnticoRomano personaggio = new AnticoRomano("Cesare", null);
		System.out.println(personaggio);
		
		personaggio.setImperatore();
		System.out.println(personaggio);
		
	}

}
