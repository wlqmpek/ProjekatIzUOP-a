package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import view.RadSaServiserimaView;

public class RadSaServiserimaController {
	private RadSaServiserimaView rssv;
	
	public RadSaServiserimaController(Stage primaryStage) {
		this.rssv = ((RadSaServiserimaView)primaryStage);
		dodeliFunkcionalnostDugmicima();
	}

	private void dodeliFunkcionalnostDugmicima() {
		
		rssv.dodeliFunkcionalnostDugmetuOk(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				
			}
		});
		
	}
	
}
