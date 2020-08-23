package main;

import controller.LogovanjeController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.LogovanjeView;

public class Main extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
		LogovanjeView lv = new LogovanjeView();
		LogovanjeController lc = new LogovanjeController(lv);
		lv.start(arg0);
		
	}

}
