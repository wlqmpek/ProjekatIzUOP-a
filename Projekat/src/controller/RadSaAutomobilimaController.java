
package controller;

import enumi.Gorivo;
import enumi.Marka;
import enumi.Model;
import enumi.Pol;
import enumi.Specijalizacija;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Automobil;
import model.Serviser;
import view.RadSaAutomobilimaView;
import view.RadSaServiserimaView;

public class RadSaAutomobilimaController {
	
	private RadSaAutomobilimaView rsav;
	private boolean izmena = false;
	private Automobil tempAutomobil;
	
	public RadSaAutomobilimaController(Stage primaryStage) throws NumberFormatException, Exception {
		this.rsav = ((RadSaAutomobilimaView)primaryStage);
		this.rsav.popuniTabelu(AutomobilController.getNeObrisaniAutomobili());
		RadSaAutomobilimaView.getVlasnici().addAll(FXCollections.observableArrayList(MusterijaController.getNeObrisaniMusterije()));
		dodeliFunkcionalnostDugmicima();
	}

	private void dodeliFunkcionalnostDugmicima() {
		rsav.dodeliFunkcionalnostDugmetuSacuvaj(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				try {
					pokupiIzPoljaIKreirajAutomobil();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		rsav.dodeliFunkcionalnostOpcijiIzbrisi(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				AutomobilController.izbrisiAutomobil(rsav.getTabela().getSelectionModel().getSelectedItem());
				rsav.getTabela().getItems().removeAll(rsav.getTabela().getSelectionModel().getSelectedItem());
			}
		});
		
		rsav.dodeliFunkcionalnostOpcijiIzmeni(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				prepisiVrednostiUTekstualnaPolja();
			}
		});
	}
	
	
	//uzima vrednosti iz tekstualnih polja i kreira servisera
	private void pokupiIzPoljaIKreirajAutomobil() {
		
		Automobil noviAutomobil;
		System.out.println("Izmena = " + izmena);
		
		if(izmena == false) {
			String vlasnik = rsav.getVlasnikBox().getSelectionModel().getSelectedItem().getOznaka();
			Marka marka = rsav.getMarkaBox().getSelectionModel().getSelectedItem();
			Model model = rsav.getModelBox().getSelectionModel().getSelectedItem();
			Gorivo gorivo = rsav.getGorivoBox().getSelectionModel().getSelectedItem();
			
			try {
				short zapreminaMotora = Short.valueOf(rsav.getTfZapreminaMotora().getText());
				short godinaProizvodnje = Short.valueOf(rsav.getTfGodinaProizvodnje().getText());
				short snagaMotora = Short.valueOf(rsav.getTfSnagaMotora().getText());
				noviAutomobil = new Automobil(vlasnik, marka, model, godinaProizvodnje, zapreminaMotora, snagaMotora, gorivo, false);
				rsav.getTabela().getItems().add(noviAutomobil);
				AutomobilController.upisiAutomobilUFajl(noviAutomobil);
				rsav.resetujPolja();
			} catch (NumberFormatException e) {
				rsav.izbaciPorukuOGresci(e.getMessage());
			} catch (Exception e) {
				rsav.izbaciPorukuOGresci(e.getMessage());
			}
			
		} else {
			String vlasnik = rsav.getVlasnikBox().getSelectionModel().getSelectedItem().getOznaka();
			Marka marka = rsav.getMarkaBox().getSelectionModel().getSelectedItem();
			Model model = rsav.getModelBox().getSelectionModel().getSelectedItem();
			Gorivo gorivo = rsav.getGorivoBox().getSelectionModel().getSelectedItem();
			
			try {
				short zapreminaMotora = Short.valueOf(rsav.getTfZapreminaMotora().getText());
				short godinaProizvodnje = Short.valueOf(rsav.getTfGodinaProizvodnje().getText());
				short snagaMotora = Short.valueOf(rsav.getTfSnagaMotora().getText());
				noviAutomobil = new Automobil(tempAutomobil.getOznaka(), vlasnik, marka, model, godinaProizvodnje, zapreminaMotora, snagaMotora, gorivo, false);
				AutomobilController.izbrisiIzUcitanihAutomobilaSaOznakom(tempAutomobil.getOznaka());
				rsav.getTabela().getItems().add(noviAutomobil);
				AutomobilController.upisiAutomobilUFajl(noviAutomobil);
				rsav.getTabela().getItems().remove(tempAutomobil);
				AutomobilController.sacuvajIzmeneUFajl();
				rsav.resetujPolja();
				
				izmena = false;
				tempAutomobil = null;
				
			} catch (NumberFormatException e) {
				rsav.izbaciPorukuOGresci(e.getMessage());
			} catch (Exception e) {
				rsav.izbaciPorukuOGresci(e.getMessage());
			}
		}
		
	}
	
	private void prepisiVrednostiUTekstualnaPolja() {
		
		tempAutomobil = rsav.getTabela().getSelectionModel().getSelectedItem();
		rsav.getVlasnikBox().setValue(tempAutomobil.getVlasnik());
		rsav.getMarkaBox().setValue(tempAutomobil.getMarka());
		rsav.getModelBox().setValue(tempAutomobil.getModel());
		rsav.getGorivoBox().setValue(tempAutomobil.getGorivo());
		rsav.getTfGodinaProizvodnje().setText(String.valueOf(tempAutomobil.getGodinaProizvodnje()));
		rsav.getTfZapreminaMotora().setText(String.valueOf(tempAutomobil.getZapreminaMotora()));
		rsav.getTfSnagaMotora().setText(String.valueOf(tempAutomobil.getSnagaMotora()));
		rsav.getTfGodinaProizvodnje().setText(String.valueOf(tempAutomobil.getGodinaProizvodnje()));
		izmena = true;
	}
}
