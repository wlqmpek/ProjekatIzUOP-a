package controller;

import java.util.ArrayList;

import enumi.Pol;
import enumi.Specijalizacija;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Serviser;
import view.RadSaServiserimaView;

public class RadSaServiserimaController {
	private RadSaServiserimaView rssv;
	private boolean izmena = false;
	private Serviser tempServiser;
	
	public RadSaServiserimaController(Stage primaryStage) {
		this.rssv = ((RadSaServiserimaView)primaryStage);
		this.rssv.popuniTabelu(ServiserController.getNeObrisaniServiseri());
		dodeliFunkcionalnostDugmicima();
	}

	private void dodeliFunkcionalnostDugmicima() {
		rssv.dodeliFunkcionalnostDugmetuSacuvaj(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				try {
					pokupiIzPoljaIKreirajServisera();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		rssv.dodeliFunkcionalnostOpcijiIzbrisi(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				ServiserController.izbrisiServisera(rssv.getTabela().getSelectionModel().getSelectedItem());
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
	
	//uzima vrednosti iz tekstualnih polja i kreira servisera
	private void pokupiIzPoljaIKreirajServisera() {
		
		Serviser noviServiser;
		System.out.println("Izmena = " + izmena);
		
		if(izmena == false) {
			String ime = rssv.getTfIme().getText();
			String prezime = rssv.getTfPrezime().getText();
			String JMBG = rssv.getTfJMBG().getText();
			Pol pol = rssv.getPolBox().getSelectionModel().getSelectedItem();
			String adresa = rssv.getTfAdresa().getText();
			String brojTelefona = rssv.getTfBrojTelefona().getText();
			String korisnickoIme = rssv.getTfKorisnickoIme().getText();
			String lozinka = rssv.getTfLozinka().getText();
			Specijalizacija specijalizacija = rssv.getSpecijalizacijaBox().getSelectionModel().getSelectedItem();
				
			try {
				double plata = Double.valueOf(rssv.getTfPlata().getText());
				noviServiser = new Serviser(ime, prezime, JMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka, specijalizacija, plata);
				rssv.getTabela().getItems().add(noviServiser);
				ServiserController.upisiServiseraUFajl(noviServiser);
				rssv.resetujPolja();
			} catch (NumberFormatException e) {
				rssv.izbaciPorukuOGresci(e.getMessage());
			} catch (Exception e) {
				rssv.izbaciPorukuOGresci(e.getMessage());
			}
			
		} else {
			String ime = rssv.getTfIme().getText();
			String prezime = rssv.getTfPrezime().getText();
			String JMBG = rssv.getTfJMBG().getText();
			Pol pol = rssv.getPolBox().getSelectionModel().getSelectedItem();
			String adresa = rssv.getTfAdresa().getText();
			String brojTelefona = rssv.getTfBrojTelefona().getText();
			String korisnickoIme = rssv.getTfKorisnickoIme().getText();
			String lozinka = rssv.getTfLozinka().getText();
			Specijalizacija specijalizacija = rssv.getSpecijalizacijaBox().getSelectionModel().getSelectedItem();
			
			try {
				double plata = Double.valueOf(rssv.getTfPlata().getText());
				noviServiser = new Serviser(tempServiser.getOznaka(), ime, prezime, JMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka, false, specijalizacija, plata);
				ServiserController.izbrisiIzUcitanihServiseraSaOznakom(tempServiser.getOznaka());
				rssv.getTabela().getItems().add(noviServiser);
				ServiserController.upisiServiseraUFajl(noviServiser);
				rssv.getTabela().getItems().remove(tempServiser);
				ServiserController.sacuvajIzmeneUFajl();
				rssv.resetujPolja();
				
				izmena = false;
				tempServiser = null;
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void prepisiVrednostiUTekstualnaPolja() {
		
		tempServiser = rssv.getTabela().getSelectionModel().getSelectedItem();
		rssv.getTfIme().setText(tempServiser.getIme());
		rssv.getTfPrezime().setText(tempServiser.getPrezime());
		rssv.getTfJMBG().setText(tempServiser.getJMBG());
		rssv.getPolBox().setValue(tempServiser.getPol());
		rssv.getTfAdresa().setText(tempServiser.getAdresa());
		rssv.getTfBrojTelefona().setText(tempServiser.getBrojTelefona());
		rssv.getTfKorisnickoIme().setText(tempServiser.getKorisnickoIme());
		rssv.getTfLozinka().setText(tempServiser.getLozinka());
		rssv.getSpecijalizacijaBox().setValue(tempServiser.getSpecijalizacija());
		rssv.getTfPlata().setText(String.valueOf(tempServiser.getPlata()));
		izmena = true;
	}
}
