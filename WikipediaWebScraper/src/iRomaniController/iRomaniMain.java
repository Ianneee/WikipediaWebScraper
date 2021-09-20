package iRomaniController;

import iRomaniModel.Model;
import iRomaniView.TesterView;

/**
 * Lancia il programma iRomani.
 * 
 * @author Ian Tirso Cini
 *
 */
public class iRomaniMain {

	/**
	 * Main di iRomani.
	 * 
	 * @param args Non necessita di args.
	 */
	public static void main(String[] args) {
		Model m = new Model();
		TesterView v = new TesterView();
		Controller controller = new Controller(m, v);
		controller.init();
	}

}
