package controller;

import java.text.SimpleDateFormat;

import enumi.Status;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Servis;
import model.Serviser;
import model.ServisnaKnjizica;
import view.RadSaServisnimKnjizicamaView;
import view.RadSaServisnimKnjizicamaView;

public class RadSaServisnimKnjizicamaController {
	private RadSaServisnimKnjizicamaView rsskv;
	private boolean izmena = false;
	private Servis tempServis;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
	public RadSaServisnimKnjizicamaController(Stage primaryStage) {
		this.rsskv = ((RadSaServisnimKnjizicamaView)primaryStage);
		this.rsskv.popuniTabelu(ServisnaKnjizicaController.getNeObrisaneServisneKnjizice());
		dodeliFunkcionalnostDugmicima();
	}

	private void dodeliFunkcionalnostDugmicima() {
		rsskv.dodeliFunkcionalnostOpcijiIzbrisi(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				AutomobilController.izbrisiAutomobil(rsskv.getTabela().getSelectionModel().getSelectedItem().getAutomobil());
				rsskv.getTabela().getItems().removeAll(rsskv.getTabela().getSelectionModel().getSelectedItem());
			}
		});
	}
}
