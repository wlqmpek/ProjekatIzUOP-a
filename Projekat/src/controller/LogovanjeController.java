package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import view.AdministratorGlavniMeniView;
import view.LogovanjeView;
import view.ServiserGlavniMeni;
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
						logovanjeView.primaryStage = new AdministratorGlavniMeniView();
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
	
	//proverava da li se poklapaju username i pw iz labela
	private Korisnik ulogujSe() {
		
		Korisnik korisnik = null;
		
		korisnik = AdministratorController.nadjiAdministratoraPoKorisnickomImenu(logovanjeView.getKorisnickoIme());
		
		if(korisnik != null) {
			//proveravamo da li se lozinka poklapa ako se poklapa administrator ostaje a ako se ne poklopi
			//administrator otpada
			if(!korisnik.getLozinka().equals(logovanjeView.getLozinka())) {
				korisnik = null;
			}
		}
		
		if(korisnik == null) {
			korisnik = ServiserController.nadjiServiseraPoKorisnickomImenu(logovanjeView.getKorisnickoIme());
			
			if(korisnik != null) {
				//proveravamo da li se lozinka poklapa ako se poklapa serviser ostaje a ako se ne poklopi
				//serviser otpada
				if(!korisnik.getLozinka().equals(logovanjeView.getLozinka())) {
					korisnik = null;
				}
			}
		}
		
		if(korisnik == null) {
			korisnik = MusterijaController.nadjiMusterijuPoKorisnickomImenu(logovanjeView.getKorisnickoIme());
			
			if(korisnik != null) {
				//proveravamo da li se lozinka poklapa ako se poklapa musterija ostaje a ako se ne poklopi
				//musterija otpada
				if(!korisnik.getLozinka().equals(logovanjeView.getLozinka())) {
					korisnik = null;
				}
			}
			
		}
		
		//ako se ne poklopi lozinka vraca se null
		return  korisnik;
	}

	
}
