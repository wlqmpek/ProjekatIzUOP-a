package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import enumi.Status;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Deo;
import model.Servis;
import model.Serviser;
import model.ServisnaKnjizica;
import view.AdministratorGlavniMeniView;
import view.PopupTabelaDelovaView;
import view.RadSaServisimaView;
import view.ServiserGlavniMeniView;

public class ServiserGlavniMeniController {
	public ServiserGlavniMeniView sgmv;
	private Servis tempServis;
	private boolean izmena = false;
	private Serviser ulogovanServiser;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	private PopupTabelaDelovaView pp = new PopupTabelaDelovaView();
	
	private Servis selekServ;
	
	public ServiserGlavniMeniController(Stage primaryStage, Serviser serviser) {
		this.ulogovanServiser = serviser;
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
					pokupiIzPoljaIKreirajServis();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		sgmv.dodeliFunkcionalnostOpcijiIzmeni(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(sgmv.getTabela().getSelectionModel().getSelectedItem().getStatus() == Status.ZAKAZAN) {
					prepisiSvaPolja();
				} else {
					sgmv.izbaciPorukuOGresci("Za odabrani servis ne mozete izvrsiti opciju Izmeni!");
				}
			}
		});
		
		
		sgmv.dodeliFunkcionalnostOpcijiDodajDelove(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(sgmv.getTabela().getSelectionModel().getSelectedItem().getStatus() == Status.ZAKAZAN) {
					selekServ = sgmv.getTabela().getSelectionModel().getSelectedItem();
					System.out.println("Selek " +selekServ);
					pp.show(sgmv);
					pp.popuniTabelu(DeoController.neIskorisceniDelovi());
					dodajFunkcPP();
				} else {
					sgmv.izbaciPorukuOGresci("Za odabrani servis ne mozete izvrsiti opciju Dodaj delove!");
				}
				
			}
		});
		
		sgmv.dodeliFunkcionalnostOpcijiZavrsi(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(sgmv.getTabela().getSelectionModel().getSelectedItem().getStatus() == Status.ZAKAZAN) {
					
					
					
				} else {
					sgmv.izbaciPorukuOGresci("Za odabrani servis ne mozete izvrsiti opciju Zavrsi Servis!");
				}
			}
		});
	}
	
	private void dodajFunkcPP() {
		pp.getDugmeOtkazi().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				pp.hide();
			}
		});
		
		pp.getDugmeZavrsi().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				DeoController.dodajDeloveServisu(new ArrayList<Deo>(pp.getTabela().getSelectionModel().getSelectedItems()), selekServ);
				pp.hide();
				sgmv.popuniTabelu(ServisController.getServiseServisera(ulogovanServiser));
			}
		});
	}
	
	public void prepisiSvaPolja() {
		tempServis = sgmv.getTabela().getSelectionModel().getSelectedItem();
		sgmv.getServisnaKnjiizcaBox().setValue(tempServis.getServisnaKnjizica());
		sgmv.getServisnaKnjiizcaBox().setDisable(true);
		sgmv.getTfDatum().setText(tempServis.getDatum());
		sgmv.getTfDatum().setEditable(false);
		sgmv.getTfOpis().setText(tempServis.getOpis());
		sgmv.getStatusBox().setValue(tempServis.getStatus());
		sgmv.getStatusBox().setDisable(true);
		sgmv.getDugmeTroskoviUsluge().setText(String.valueOf(tempServis.getTroskoviUsluge()));
		sgmv.getDugmeTroskoviUsluge().setDisable(true);
		sgmv.getDugmeCenaDelova().setText(String.valueOf(ServisController.cenaDelova(tempServis).doubleValue()));
		sgmv.getDugmeCenaDelova().setDisable(true);
		izmena = true;
	}
	
	private void enable() {
		sgmv.getDugmeCenaDelova().setDisable(false);
		sgmv.getDugmeTroskoviUsluge().setDisable(false);
		sgmv.getServisnaKnjiizcaBox().setDisable(false);
		sgmv.getTfDatum().setEditable(true);
	}
	
	private void pokupiIzPoljaIKreirajServis() {
		
		Servis noviServis;
		
		if(izmena == false) {
			ServisnaKnjizica servisnaKnjizica = sgmv.getServisnaKnjiizcaBox().getSelectionModel().getSelectedItem();
			
			String datum = sgmv.getTfDatum().getText();
			String opis = sgmv.getTfOpis().getText();
			Double troskoviUsluge = ServisController.troskoviVratiDouble(sgmv.getDugmeTroskoviUsluge().getText());
			Status status = sgmv.getStatusBox().getSelectionModel().getSelectedItem();
				
			try {
				noviServis = new Servis(servisnaKnjizica, ulogovanServiser, sdf.parse(datum), opis, troskoviUsluge, status);
				sgmv.getTabela().getItems().add(noviServis);
				ServisController.upisiServisUFajl(noviServis);
				sgmv.resetujPolja();
			} catch (NumberFormatException e) {
				sgmv.izbaciPorukuOGresci(e.getMessage());
			} catch (Exception e) {
				sgmv.izbaciPorukuOGresci(e.getMessage());
			}
			
		} else {
			ServisnaKnjizica servisnaKnjizica = sgmv.getServisnaKnjiizcaBox().getSelectionModel().getSelectedItem();
			String datum = sgmv.getTfDatum().getText();
			String opis = sgmv.getTfOpis().getText();
			Double troskoviUsluge = ServisController.proveraNullDouble(sgmv.getDugmeTroskoviUsluge().getText());
			Status status = sgmv.getStatusBox().getSelectionModel().getSelectedItem();
			
			
			try {
				noviServis = new Servis(tempServis.getOznaka(), servisnaKnjizica.getOznaka(), ulogovanServiser.getOznaka(), datum, opis, troskoviUsluge, status, false);
				ServisController.izbrisiIzUcitanihServisaSaOznakom(tempServis.getOznaka());
				sgmv.getTabela().getItems().add(noviServis);
				ServisController.upisiServisUFajl(noviServis);
				sgmv.getTabela().getItems().remove(tempServis);
				ServisController.sacuvajIzmeneUFajl();
				sgmv.resetujPolja();
				enable();
				
				izmena = false;
				tempServis = null;
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
