package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Servis;
import model.Serviser;
import view.AdministratorGlavniMeniView;
import view.RadSaServisimaView;
import view.ServiserGlavniMeniView;

public class ServiserGlavniMeniController {
	public ServiserGlavniMeniView sgmv;
	private Servis tempServis;
	
	public ServiserGlavniMeniController(Stage primaryStage, Serviser serviser) {
		this.sgmv = ((ServiserGlavniMeniView)primaryStage);
		this.sgmv.popuniTabelu(ServisController.getServiseServisera(serviser));
		ServiserGlavniMeniView.getServisneKnjizice().addAll(FXCollections.observableArrayList(ServisnaKnjizicaController.getNeObrisaneServisneKnjizice()));
		dodeliFunkcionalnostDugmicima();
	}
	
	private void dodeliFunkcionalnostDugmicima() {
		sgmv.dodeliFunkcionalnostDugmetuSacuvaj(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				try {
					//pokupiIzPoljaIKreirajServis();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		sgmv.dodeliFunkcionalnostOpcijiIzmeni(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				prepisiSvaPolja();
			}
		});
		
		sgmv.dodeliFunkcionalnostOpcijiDodajNovi(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
			}
		});
		
		sgmv.dodeliFunkcionalnostOpcijiDodajDelove(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
			}
		});
	}
	
	public void prepisiSvaPolja() {
		tempServis = sgmv.getTabela().getSelectionModel().getSelectedItem();
		sgmv.getServisnaKnjiizcaBox().setValue(tempServis.getServisnaKnjizica());
		sgmv.getServisnaKnjiizcaBox().setEditable(false);
		sgmv.getTfDatum().setText(tempServis.getDatum());
		sgmv.getTfDatum().setEditable(false);
		sgmv.getTfOpis().setText(tempServis.getOpis());
		sgmv.getTfOpis().setEditable(false);
		sgmv.getStatusBox().setValue(tempServis.getStatus());
		sgmv.getStatusBox().setEditable(false);
		sgmv.getDugmeTroskoviServisa().setText(String.valueOf(tempServis.getTroskoviServisa()));
		sgmv.getDugmeTroskoviServisa().setDisable(true);
		sgmv.getDugmeCenaServisa().setText(String.valueOf(ServisController.vratiTroskoveServisa(servis)));
		sgmv.getDugmeCenaServisa().setDisable(true);
		
	}
}
