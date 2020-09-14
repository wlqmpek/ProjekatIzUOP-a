package controller;

import java.text.SimpleDateFormat;

import enumi.Marka;
import enumi.Model;
import enumi.Status;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Deo;
import model.Servis;
import model.Serviser;
import model.ServisnaKnjizica;
import view.RadSaDelovimaView;

public class RadSaDelovimaController {
	private RadSaDelovimaView rsdv;
	private boolean izmena = false;
	private Deo tempDeo;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
	public RadSaDelovimaController(Stage primaryStage) {
		this.rsdv = ((RadSaDelovimaView)primaryStage);
		this.rsdv.popuniTabelu(DeoController.neObrisaniDelovi());
		RadSaDelovimaView.getServisi().addAll(FXCollections.observableArrayList(ServisController.getNeObrisaniServisi()));
		dodeliFunkcionalnostDugmicima();
		
	}

	private void dodeliFunkcionalnostDugmicima() {
		rsdv.dodeliFunkcionalnostDugmetuSacuvaj(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				try {
					pokupiIzPoljaIKreirajServis();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		rsdv.dodeliFunkcionalnostOpcijiIzbrisi(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				DeoController.izbrisiDeo(rsdv.getTabela().getSelectionModel().getSelectedItem());
				rsdv.getTabela().getItems().removeAll(rsdv.getTabela().getSelectionModel().getSelectedItem());
			}
		});
		
		rsdv.dodeliFunkcionalnostOpcijiIzmeni(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				prepisiVrednostiUTekstualnaPolja();
			}
		});
		
		rsdv.dodeliFunkcionalnostOpcijiKreirajSimetricni(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					DeoController.kreirajSimetricanDeo(rsdv.getTabela().getSelectionModel().getSelectedItem());
					rsdv.popuniTabelu(DeoController.neObrisaniDelovi());
				} catch (Exception e) {
					rsdv.izbaciPorukuOGresci(e.getMessage());
				}
			}
		});
		
	}
	
	//uzima vrednosti iz tekstualnih polja i kreira Servisa
	private void pokupiIzPoljaIKreirajServis() {
		Deo noviDeo;
		if(izmena == false) {
			Marka marka = rsdv.getMarkaBox().getSelectionModel().getSelectedItem();
			Model model = rsdv.getModelBox().getSelectionModel().getSelectedItem();
			String naziv = rsdv.getTfNaziv().getText();
			Servis servis = rsdv.getServisBox().getSelectionModel().getSelectedItem();
			try {
				Double cena = Double.valueOf(rsdv.getTfCena().getText());
				noviDeo = new Deo(marka, model, naziv, cena, servis);
				rsdv.getTabela().getItems().add(noviDeo);
				DeoController.upisiDeoUFajl(noviDeo);
				rsdv.resetujPolja();
			} catch (NumberFormatException e) {
				rsdv.izbaciPorukuOGresci(e.getMessage());
			} catch (Exception e) {
				rsdv.izbaciPorukuOGresci(e.getMessage());
			}
			
		} else {
			Marka marka = rsdv.getMarkaBox().getSelectionModel().getSelectedItem();
			Model model = rsdv.getModelBox().getSelectionModel().getSelectedItem();
			String naziv = rsdv.getTfNaziv().getText();
			String servis;
				
			if(rsdv.getServisBox().getSelectionModel().getSelectedItem() == null) {
				servis = null;
			} else {
				servis = rsdv.getServisBox().getSelectionModel().getSelectedItem().getOznaka();
			}
			
			try {
				Double cena = Double.valueOf(rsdv.getTfCena().getText());
				noviDeo = new Deo(tempDeo.getOznaka(), marka, model, naziv, cena, servis, false);
				DeoController.izbrisiIzUcitanihDelovaSaOznakom(tempDeo.getOznaka());
				rsdv.getTabela().getItems().add(noviDeo);
				DeoController.upisiDeoUFajl(noviDeo);
				rsdv.getTabela().getItems().remove(tempDeo);
				DeoController.sacuvajIzmeneUFajl();
				rsdv.resetujPolja();
				
				izmena = false;
				tempDeo = null;
				
			} catch (NumberFormatException e) {
				rsdv.izbaciPorukuOGresci(e.getMessage());
			} catch (Exception e) {
				rsdv.izbaciPorukuOGresci(e.getMessage());
			}
		}
		
	}
	
	private void prepisiVrednostiUTekstualnaPolja() {	
		tempDeo = rsdv.getTabela().getSelectionModel().getSelectedItem();
		rsdv.getMarkaBox().setValue(tempDeo.getMarka());
		rsdv.getModelBox().setValue(tempDeo.getModel());
		rsdv.getTfNaziv().setText(tempDeo.getNaziv());
		rsdv.getTfCena().setText(String.valueOf(tempDeo.getCena()));
		rsdv.getServisBox().setValue(null);
		izmena = true;
	}
}
