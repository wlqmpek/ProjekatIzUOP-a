package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import view.AdministratorGlavniMeni;

public class AdministratorGlavniMeniController {
	public AdministratorGlavniMeni agm;

	public AdministratorGlavniMeniController(Stage primaryStage) {
		this.agm = ((AdministratorGlavniMeni)primaryStage);
		this.agm.dodajFunkcionalnost(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Boo!");
				
			}
		});
	}

}
