package alberoGenealogicoLib;

public class TreeTester {
	
	public static void main(String[] args) {
		Persona num1 = new Persona("numero 1");
		System.out.println(num1.getNome());
		System.out.println(num1.countParent());
		System.out.println(num1.totaleFigli());
		
		Persona num2 = new Persona("numero 2");
		num1.aggiungiFiglio(num2);
		System.out.println(num1.countParent());
		System.out.println(num1.totaleFigli());
		System.out.println(num1.toString());
		
	}

}
