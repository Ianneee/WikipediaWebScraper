package alberoGenealogico;
import java.util.ArrayList;

/**Questa classe crea un alber n-ario che pu� avere i figli verso due direzioni.
 * Una direzione principale � contenuta in sons, la direzione secondaria in brothersRoot.
 * @author Ian Tirso Cini
 *
 */
public class Tree {
	
	private String root;
	private ArrayList<Tree>brothersRoot;
	private ArrayList<Tree> sons;
	private int brothersRootPointer;
	private int sonsPointer;
	private Tree rootPointer;
	
	/**Costruttore dell'albero con inserimento del nome della radice.
	 * @param nameRoot il nome della radice.
	 */
	public Tree(String nameRoot) {
		root = nameRoot;
		brothersRoot = new ArrayList<Tree>();
		sons = new ArrayList<Tree>();
		brothersRootPointer = 0;
		sonsPointer = 0;
		rootPointer = null;
	}
	
	/**Aggiunge il riferimento all'oggetto padre di questa radice.
	 * @param padre Il padre di questa radice.
	 */
	public void addRootPointer(Tree padre) {
		rootPointer = padre;
	}
	
	/**Ritorna il padre di questa radice.
	 * @return Il padre di questa radice.
	 */
	public Tree getFather() {
		return rootPointer;
	}
	
	/**Se questa radice ha un padre, ne ritorna il nome.
	 * @return Il nome del padre di questa radice.
	 */
	public String getFatherName() {
		if (rootPointer != null) {
			return this.getFather().getName();
		} else {
			return "N/D";
		}
	}
	
	/**Aggiunta di un figlio nella direzione principale della radice.
	 * @param son Il figlio da aggiungere.
	 */
	public void addSon(Tree son) {
		sons.add(son);
		son.addRootPointer(this);
	}

	/**Aggiunge un figlio nella direzione secondaria della radice.
	 * @param brother Il figlio da aggiungere.
	 */
	public void addParent(Tree brother) {
		brothersRoot.add(brother);
	}
	
	/**Ritorna il numero di figli contenuti nella direzione principale della radice.
	 * @return Il numero di figli.
	 */
	public int countSons() {
		return sons.size();
	}
	
	/**Ritorna il numero di figli contenuti nella direzione secondaria della radice.
	 * @return Il numero di figli
	 */
	public int countParent() {
		return brothersRoot.size();
	}
	
	/**Ritorna un ArrayList contenente tutti i figli della direzione
	 * principale.
	 * @return ArrayList dei figli.
	 */
	public ArrayList<Tree> getSonsArray() {
		return sons;
	}
	
	/**Ritorna un ArrayList contenente tutti i figlie della direzione secondaria.
	 * @return ArrayList dei figli.
	 */
	public ArrayList<Tree> getParentsRootArray() {
		return brothersRoot;
	}
	
	public String getName() {
		return this.root;
	}
	
	/**Overload del metodo to string per controllare i dati all'interno
	 * dell'oggetto Tree.
	 */
	public String toString() {
		return "-------------------------------\n" + 
	           "Nome radice: " + this.root + "\n" + 
		       "Nome padre: " + this.getFatherName() + "\n" +
			   "Numero figli direzione principale: " + this.countSons() + "\n" + 
			   "Numero figli direzione secondaria: " + this.countParent();
	}
	
	/**Metodo statico per la visita in ordine dell'albero creato nella direzione principale.
	 * Printa il metodo toString() con le informazioni sui dati contenuti nel nodo.
	 * @param radice La radice dell'albero da navigare. 
	 */
	public static void visitPreOrder(Tree radice) {
		System.out.println(radice.toString());
//		System.out.println(radice.getName());
		if (radice.countSons() > 0) {
			for (Tree nodo : radice.getSonsArray()) {
				visitPreOrder(nodo);
			}
		}
	}
}
