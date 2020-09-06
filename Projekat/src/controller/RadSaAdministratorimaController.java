package controller;

import enumi.Pol;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Administrator;
import view.RadSaAdministratorimaView;

public class RadSaAdministratorimaController {
	private RadSaAdministratorimaView rsav;
	private boolean izmena = false;
	private Administrator tempAdministrator;
	
	public RadSaAdministratorimaController(Stage primaryStage) {
		this.rsav = ((RadSaAdministratorimaView)primaryStage);
		this.rsav.popuniTabelu(AdministratorController.getNeObrisaniAdministratori());
		dodeliFunkcionalnostDugmicima();
	}

	private void dodeliFunkcionalnostDugmicima() {
		rsav.dodeliFunkcionalnostDugmetuSacuvaj(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					pokupiIzPoljaIKreirajAdministratora();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		rsav.dodeliFunkcionalnostOpcijiIzbrisi(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				AdministratorController.izbrisiAdministratora(rsav.getTabela().getSelectionModel().getSelectedItem());
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
	
	private void pokupiIzPoljaIKreirajAdministratora() {
		Administrator noviAdministrator;
		System.out.println("Izmena = " + izmena);
		
		if(izmena == false) {
			String ime = rsav.getTfIme().getText();
			String prezime = rsav.getTfPrezime().getText();
			String JMBG = rsav.getTfJMBG().getText();
			Pol pol = rsav.getPolBox().getSelectionModel().getSelectedItem();
			String adresa = rsav.getTfAdresa().getText();
			String brojTelefona = rsav.getTfBrojTelefona().getText();
			String korisnickoIme = rsav.getTfKorisnickoIme().getText();
			String lozinka = rsav.getTfLozinka().getText();
				
			try {
				double plata = Double.valueOf(rsav.getTfPlata().getText());
				noviAdministrator = new Administrator(ime, prezime, JMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka, plata);
				rsav.getTabela().getItems().add(noviAdministrator);
				AdministratorController.upisiAdministratoraUFajl(noviAdministrator);
				rsav.resetujPolja();
			} catch (NumberFormatException e) {
				rsav.izbaciPorukuOGresci(e.getMessage());
			} catch (Exception e) {
				rsav.izbaciPorukuOGresci(e.getMessage());
			}
			
		} else {
			String ime = rsav.getTfIme().getText();
			String prezime = rsav.getTfPrezime().getText();
			String JMBG = rsav.getTfJMBG().getText();
			Pol pol = rsav.getPolBox().getSelectionModel().getSelectedItem();
			String adresa = rsav.getTfAdresa().getText();
			String brojTelefona = rsav.getTfBrojTelefona().getText();
			String korisnickoIme = rsav.getTfKorisnickoIme().getText();
			String lozinka = rsav.getTfLozinka().getText();
			
			try {
				double plata = Double.valueOf(rsav.getTfPlata().getText());
				noviAdministrator = new Administrator(tempAdministrator.getOznaka(), ime, prezime, JMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka, false, plata);
				AdministratorController.izbrisiIzUcitanihAdministratoraSaOznakom(tempAdministrator.getOznaka());
				rsav.getTabela().getItems().add(noviAdministrator);
				AdministratorController.upisiAdministratoraUFajl(noviAdministrator);
				rsav.getTabela().getItems().remove(tempAdministrator);
				AdministratorController.sacuvajIzmeneUFajl();
				rsav.resetujPolja();
				
				izmena = false;
				tempAdministrator = null;
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void prepisiVrednostiUTekstualnaPolja() {
		
		tempAdministrator = rsav.getTabela().getSelectionModel().getSelectedItem();
		rsav.getTfIme().setText(tempAdministrator.getIme());
		rsav.getTfPrezime().setText(tempAdministrator.getPrezime());
		rsav.getTfJMBG().setText(tempAdministrator.getJMBG());
		rsav.getPolBox().setValue(tempAdministrator.getPol());
		rsav.getTfAdresa().setText(tempAdministrator.getAdresa());
		rsav.getTfBrojTelefona().setText(tempAdministrator.getBrojTelefona());
		rsav.getTfKorisnickoIme().setText(tempAdministrator.getKorisnickoIme());
		rsav.getTfLozinka().setText(tempAdministrator.getLozinka());
		rsav.getTfPlata().setText(String.valueOf(tempAdministrator.getPlata()));
		izmena = true;
	}

	
}
