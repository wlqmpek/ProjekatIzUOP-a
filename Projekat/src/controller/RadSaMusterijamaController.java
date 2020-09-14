package controller;

import java.util.ArrayList;

import enumi.Pol;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Musterija;
import view.RadSaMusterijamaView;

public class RadSaMusterijamaController {
	private RadSaMusterijamaView rsmv;
	private boolean izmena = false;
	private Musterija tempMusterija;
	
	public RadSaMusterijamaController(Stage primaryStage) {
		this.rsmv = ((RadSaMusterijamaView)primaryStage);
		this.rsmv.popuniTabelu(MusterijaController.getNeObrisaniMusterije());
		dodeliFunkcionalnostDugmicima();
	}

	private void dodeliFunkcionalnostDugmicima() {
		rsmv.dodeliFunkcionalnostDugmetuSacuvaj(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				try {
					pokupiIzPoljaIKreirajMusterijaa();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		rsmv.dodeliFunkcionalnostOpcijiIzbrisi(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				MusterijaController.izbrisiMusteriju(rsmv.getTabela().getSelectionModel().getSelectedItem());
				rsmv.getTabela().getItems().removeAll(rsmv.getTabela().getSelectionModel().getSelectedItem());
			}
		});
		
		rsmv.dodeliFunkcionalnostOpcijiIzmeni(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				prepisiVrednostiUTekstualnaPolja();
			}
		});
		
		
	}
	
	//uzima vrednosti iz tekstualnih polja i kreira Musterijaa
	private void pokupiIzPoljaIKreirajMusterijaa() {
		
		Musterija novaMusterija;
		System.out.println("Izmena = " + izmena);
		
		if(izmena == false) {
			String ime = rsmv.getTfIme().getText();
			String prezime = rsmv.getTfPrezime().getText();
			String JMBG = rsmv.getTfJMBG().getText();
			Pol pol = rsmv.getPolBox().getSelectionModel().getSelectedItem();
			String adresa = rsmv.getTfAdresa().getText();
			String brojTelefona = rsmv.getTfBrojTelefona().getText();
			String korisnickoIme = rsmv.getTfKorisnickoIme().getText();
			String lozinka = rsmv.getTfLozinka().getText();
				
			try {
				byte poeni = Byte.valueOf(rsmv.getTfPoeni().getText());
				novaMusterija = new Musterija(ime, prezime, JMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka, poeni, false);
				rsmv.getTabela().getItems().add(novaMusterija);
				MusterijaController.upisiMusterijuUFajl(novaMusterija);
				rsmv.resetujPolja();
			} catch (NumberFormatException e) {
				rsmv.izbaciPorukuOGresci(e.getMessage());
			} catch (Exception e) {
				rsmv.izbaciPorukuOGresci(e.getMessage());
			}
			
		} else {
			String ime = rsmv.getTfIme().getText();
			String prezime = rsmv.getTfPrezime().getText();
			String JMBG = rsmv.getTfJMBG().getText();
			Pol pol = rsmv.getPolBox().getSelectionModel().getSelectedItem();
			String adresa = rsmv.getTfAdresa().getText();
			String brojTelefona = rsmv.getTfBrojTelefona().getText();
			String korisnickoIme = rsmv.getTfKorisnickoIme().getText();
			String lozinka = rsmv.getTfLozinka().getText();
			
			
			try {
				byte poeni = Byte.valueOf(rsmv.getTfPoeni().getText());
				novaMusterija = new Musterija(tempMusterija.getOznaka(), ime, prezime, JMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka, poeni, false);
				MusterijaController.izbrisiIzUcitanihMusterijaSaOznakom(tempMusterija.getOznaka());
				rsmv.getTabela().getItems().add(novaMusterija);
				MusterijaController.upisiMusterijuUFajl(novaMusterija);
				rsmv.getTabela().getItems().remove(tempMusterija);
				MusterijaController.sacuvajIzmeneUFajl();
				rsmv.resetujPolja();
				
				izmena = false;
				tempMusterija = null;
				
			} catch (NumberFormatException e) {
				rsmv.izbaciPorukuOGresci(e.getMessage());
			} catch (Exception e) {
				rsmv.izbaciPorukuOGresci(e.getMessage());
			}
		}
		
	}
	
	private void prepisiVrednostiUTekstualnaPolja() {
		
		tempMusterija = rsmv.getTabela().getSelectionModel().getSelectedItem();
		rsmv.getTfIme().setText(tempMusterija.getIme());
		rsmv.getTfPrezime().setText(tempMusterija.getPrezime());
		rsmv.getTfJMBG().setText(tempMusterija.getJMBG());
		rsmv.getPolBox().setValue(tempMusterija.getPol());
		rsmv.getTfAdresa().setText(tempMusterija.getAdresa());
		rsmv.getTfBrojTelefona().setText(tempMusterija.getBrojTelefona());
		rsmv.getTfKorisnickoIme().setText(tempMusterija.getKorisnickoIme());
		rsmv.getTfLozinka().setText(tempMusterija.getLozinka());
		rsmv.getTfPoeni().setText(String.valueOf(tempMusterija.getBrojPoena()));
		izmena = true;
	}
}
