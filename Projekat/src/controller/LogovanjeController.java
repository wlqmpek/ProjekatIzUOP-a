package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import view.AdministratorGlavniMeni;
import view.LogovanjeView;
import model.Administrator;
import model.Korisnik;
import model.Musterija;
import model.Serviser;

public class LogovanjeController {
	
	private LogovanjeView logovanjeView;

	public LogovanjeController(LogovanjeView logovanjeView) {
		super();
		this.logovanjeView = logovanjeView;
		logovanjeView.dodajFunkcionalnostDugmetuOK(generisiFunkcionalnost());
	}
	
	
	public EventHandler<ActionEvent> generisiFunkcionalnost() {
		return new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				Korisnik korisnik = null;
				
				korisnik = ulogujSe();
				
				if(korisnik != null) {
					
					if(korisnik instanceof Administrator) {
						
						logovanjeView.primaryStage.close();
						logovanjeView.primaryStage = new AdministratorGlavniMeni();
						new AdministratorGlavniMeniController(logovanjeView.primaryStage);
						
						
					} else if(korisnik instanceof Serviser) {
						
						
						System.out.println("Serviser");
					} else if(korisnik instanceof Musterija) {
						System.out.println("Musterija");
					}
					
				} else {
					logovanjeView.izbaciPorukuOGresci("Proverite korisnicko ime i sifru!");
				}
			}
			
		};
	}
	
	private Korisnik ulogujSe() {
		
		Korisnik korisnik = null;
		
		korisnik = AdministratorController.nadjiAdministratoraPoKorisnickomImenu(logovanjeView.getKorisnickoIme());
		
		if(korisnik != null) {
			if(!korisnik.getLozinka().equals(logovanjeView.getLozinka())) {
				korisnik = null;
			}
		}
		
		if(korisnik == null) {
			korisnik = ServiserController.nadjiServiseraPoKorisnickomImenu(logovanjeView.getKorisnickoIme());
			
			if(korisnik != null) {
				if(!korisnik.getLozinka().equals(logovanjeView.getLozinka())) {
					korisnik = null;
				}
			}
			
		}
		
		if(korisnik == null) {
			korisnik = MusterijaController.nadjiMusterijuPoKorisnickomImenu(logovanjeView.getKorisnickoIme());
			
			if(korisnik != null) {
				if(!korisnik.getLozinka().equals(logovanjeView.getLozinka())) {
					korisnik = null;
				}
			}
			
		}
		
		return  korisnik;
	}

	
}
