package controller;

import enumi.Gorivo;
import enumi.Marka;
import enumi.Model;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Automobil;
import model.Musterija;
import model.Servis;
import view.MusterijaGlavniMeniView;
import view.RadSaAutomobilimaView;

public class MusterijaGlavniMeniController {
	private MusterijaGlavniMeniView mgmv;
	
	public MusterijaGlavniMeniController(Stage primaryStage, Musterija musterija) {
		this.mgmv = ((MusterijaGlavniMeniView)primaryStage);
		this.mgmv.popuniTabelu(AutomobilController.nadjiAutomobilePoIdVlasnika(musterija.getOznaka()));
		RadSaAutomobilimaView.getVlasnici().addAll(FXCollections.observableArrayList(MusterijaController.getNeObrisaniMusterije()));
		dodeliFunkcionalnostDugmicima();
	}

	private void dodeliFunkcionalnostDugmicima() {
		mgmv.dodeliFunkcionalnostDugmetuSacuvaj(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				try {
					kreirajServis();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//ovde dodati funkcionalnost drop menija
		mgmv.dodeliFunkcionalnostOpcijiZakaziServis(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Automobil selektovanAutomobil = mgmv.getTabela().getSelectionModel().getSelectedItem();
				if(selektovanAutomobil != null) {
					mgmv.getTfKratakOpis().setDisable(false);
					mgmv.getDugmeMarka().setText(selektovanAutomobil.getMarka().toString());
					mgmv.getDugmeModel().setText(selektovanAutomobil.getModel().toString());
					mgmv.getDugmeGodina().setText(String.valueOf(selektovanAutomobil.getGodinaProizvodnje()));
					mgmv.getDugmeZapremina().setText(String.valueOf(selektovanAutomobil.getZapreminaMotora()));
					mgmv.getDugmeSnaga().setText(String.valueOf(selektovanAutomobil.getSnagaMotora()));
					mgmv.getDugmeGorivo().setText(String.valueOf(selektovanAutomobil.getGodinaProizvodnje()));
					mgmv.getTfKratakOpis().setEditable(true);
					mgmv.getTfKratakOpis().setPromptText("Kratak opis");
				}
				
			}
		});
	}
	
	
	//uzima vrednosti iz tekstualnih polja i kreira servisera
	private void kreirajServis() {
		
		
		Servis noviServis = new Servis(ServisnaKnjizicaController.generisiServisnuKnjizicuZaAutomobil(mgmv.getTabela().getSelectionModel().getSelectedItem()), mgmv.getTfKratakOpis().getText());
		ServisController.upisiServisUFajl(noviServis);
		mgmv.resetujPolja();
		noviServis = null;
		
	}
	
	private void prepisiVrednostiUTekstualnaPolja() {
		
//		tempAutomobil = mgmv.getTabela().getSelectionModel().getSelectedItem();
//		mgmv.getVlasnikBox().setValue(tempAutomobil.getVlasnik());
//		mgmv.getMarkaBox().setValue(tempAutomobil.getMarka());
//		mgmv.getModelBox().setValue(tempAutomobil.getModel());
//		mgmv.getGorivoBox().setValue(tempAutomobil.getGorivo());
//		mgmv.getTfGodinaProizvodnje().setText(String.valueOf(tempAutomobil.getGodinaProizvodnje()));
//		mgmv.getTfZapreminaMotora().setText(String.valueOf(tempAutomobil.getZapreminaMotora()));
//		mgmv.getTfSnagaMotora().setText(String.valueOf(tempAutomobil.getSnagaMotora()));
//		mgmv.getTfGodinaProizvodnje().setText(String.valueOf(tempAutomobil.getGodinaProizvodnje()));
//		izmena = true;
	}
}
