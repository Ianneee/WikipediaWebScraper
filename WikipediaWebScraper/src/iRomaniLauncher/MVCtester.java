package iRomaniLauncher;

import iRomaniModel.Model;
import iRomaniView.View;

public class MVCtester {

	public static void main(String[] args) {
		Model m = new Model();
		View v = new View();
		Controller controller = new Controller(m, v);
		controller.initView();
//		controller.initController();

	}

}
