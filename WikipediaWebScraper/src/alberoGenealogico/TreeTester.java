package alberoGenealogico;

public class TreeTester {
	
	public static void main(String[] args) {
		Tree num1 = new Tree("numero 1");
		System.out.println(num1.getName());
		System.out.println(num1.countParent());
		System.out.println(num1.countSons());
		
		Tree num2 = new Tree("numero 2");
		num1.addSon(num2);
		System.out.println(num1.countParent());
		System.out.println(num1.countSons());
		System.out.println(num1.toString());
		
	}

}
