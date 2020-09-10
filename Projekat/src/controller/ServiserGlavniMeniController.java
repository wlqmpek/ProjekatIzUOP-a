package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Serviser;
import view.AdministratorGlavniMeniView;
import view.ServiserGlavniMeniView;

public class ServiserGlavniMeniController {
	public ServiserGlavniMeniView sgmv;
	
	public ServiserGlavniMeniController(Stage primaryStage, Serviser serviser) {
		this.sgmv = ((ServiserGlavniMeniView)primaryStage);
		this.sgmv.popuniTabelu(ServisController.getServiseServisera(serviser));
	}
}
