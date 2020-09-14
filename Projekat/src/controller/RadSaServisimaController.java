package controller;

import java.text.SimpleDateFormat;

import enumi.Pol;
import enumi.Specijalizacija;
import enumi.Status;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Servis;
import model.Serviser;
import model.ServisnaKnjizica;
import view.RadSaServiserimaView;
import view.RadSaServisimaView;

public class RadSaServisimaController {
	private RadSaServisimaView rssv;
	private boolean izmena = false;
	private Servis tempServis;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
	public RadSaServisimaController(Stage primaryStage) {
		this.rssv = ((RadSaServisimaView)primaryStage);
		this.rssv.popuniTabelu(ServisController.getNeObrisaniServisi());
		RadSaServisimaView.getServisneKnjizice().addAll(FXCollections.observableArrayList(ServisnaKnjizicaController.getNeObrisaneServisneKnjizice()));
		RadSaServisimaView.getServiseri().addAll(FXCollections.observableArrayList(ServiserController.getNeObrisaniServiseri()));
		rssv.getServiserBox().setValue(rssv.getServiseri().get(0));
		rssv.getServisnaKnjiizcaBox().setValue(rssv.getServisneKnjizice().get(0));
		dodeliFunkcionalnostDugmicima();
	}

	private void dodeliFunkcionalnostDugmicima() {
		rssv.dodeliFunkcionalnostDugmetuSacuvaj(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				try {
					pokupiIzPoljaIKreirajServis();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	});
		
		rssv.dodeliFunkcionalnostOpcijiIzbrisi(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ServisController.izbrisiServis(rssv.getTabela().getSelectionModel().getSelectedItem());
				rssv.getTabela().getItems().removeAll(rssv.getTabela().getSelectionModel().getSelectedItem());
			}
		});
		
		rssv.dodeliFunkcionalnostOpcijiIzmeni(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				prepisiVrednostiUTekstualnaPolja();
			}
		});
		
		
	}
	
	//uzima vrednosti iz tekstualnih polja i kreira Servisa
	private void pokupiIzPoljaIKreirajServis() {
		
		Servis noviServis;
		
		if(izmena == false) {
			ServisnaKnjizica servisnaKnjizica = rssv.getServisnaKnjiizcaBox().getSelectionModel().getSelectedItem();
			Serviser serviser = rssv.getServiserBox().getSelectionModel().getSelectedItem();
			Status status = rssv.getStatusBox().getSelectionModel().getSelectedItem();
				
			try {
				String datum = rssv.getTfDatum().getText();
				String opis = rssv.getTfOpis().getText();
				Double troskoviServisa = ServisController.troskoviVratiDouble(rssv.getTfTroskoviServisa().getText());
				noviServis = new Servis(servisnaKnjizica, serviser, sdf.parse(datum), opis,troskoviServisa, status);
				rssv.getTabela().getItems().add(noviServis);
				ServisController.upisiServisUFajl(noviServis);
				rssv.resetujPolja();
			} catch (NumberFormatException e) {
				rssv.izbaciPorukuOGresci(e.getMessage());
			} catch (Exception e) {
				rssv.izbaciPorukuOGresci(e.getMessage());
			}
			
		} else {
			ServisnaKnjizica servisnaKnjizica = rssv.getServisnaKnjiizcaBox().getSelectionModel().getSelectedItem();
			Serviser serviser = rssv.getServiserBox().getSelectionModel().getSelectedItem();
			Double troskoviServisa = ServisController.troskoviVratiDouble(rssv.getTfTroskoviServisa().getText());
			Status status = rssv.getStatusBox().getSelectionModel().getSelectedItem();
			
			
			try {
				String opis = rssv.getTfOpis().getText();
				String datum = rssv.getTfDatum().getText();
				noviServis = new Servis(tempServis.getOznaka(), servisnaKnjizica.getOznaka(), serviser.getOznaka(), datum, opis, troskoviServisa, status, false);
				ServisController.izbrisiIzUcitanihServisaSaOznakom(tempServis.getOznaka());
				
				rssv.getTabela().getItems().add(noviServis);
				ServisController.upisiServisUFajl(noviServis);
				rssv.getTabela().getItems().remove(tempServis);
				ServisController.sacuvajIzmeneUFajl();
				rssv.resetujPolja();
				
				izmena = false;
				tempServis = null;
				
			} catch (NumberFormatException e) {
				rssv.izbaciPorukuOGresci(e.getMessage());
			} catch (Exception e) {
				rssv.izbaciPorukuOGresci(e.getMessage());
			}
		}
		
	}
	
	private void prepisiVrednostiUTekstualnaPolja() {	
		tempServis = rssv.getTabela().getSelectionModel().getSelectedItem();
		rssv.getServisnaKnjiizcaBox().setValue(tempServis.getServisnaKnjizica());
		rssv.getServiserBox().setValue(tempServis.getServiser());
		rssv.getTfDatum().setText(tempServis.getDatum());
		rssv.getTfOpis().setText(tempServis.getOpis());
		rssv.getStatusBox().setValue(tempServis.getStatus());
		rssv.getTfTroskoviServisa().setText(String.valueOf(tempServis.getTroskoviUsluge()));
		izmena = true;
	}
}
