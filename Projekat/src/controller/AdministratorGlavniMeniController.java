package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.AdministratorGlavniMeniView;
import view.RadSaServiserimaView;

public class AdministratorGlavniMeniController {
	
	public AdministratorGlavniMeniView agmv;

	public AdministratorGlavniMeniController(Stage primaryStage) {
		this.agmv = ((AdministratorGlavniMeniView)primaryStage);
		this.agmv.dodajFunkcionalnostDugmetuRadSaServiserima(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				agmv.close();
				//do ovde sam stigao
				new RadSaServiserimaController(new RadSaServiserimaView());
			}
		});
	}

}
