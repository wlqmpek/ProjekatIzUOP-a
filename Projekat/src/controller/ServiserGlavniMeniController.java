package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import view.AdministratorGlavniMeniView;
import view.ServiserGlavniMeni;

public class ServiserGlavniMeniController {
	public ServiserGlavniMeni sgm;
	
	public ServiserGlavniMeniController(Stage primaryStage) {
		this.sgm = ((ServiserGlavniMeni)primaryStage);
		this.sgm.dodajFunkcionalnost(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Boo!");
				
			}
		});
	}
}
