package model;

import java.util.ArrayList;

import controller.AutomobilController;
import controller.MusterijaController;
import enumi.Pol;

public class Musterija extends Korisnik {
	
	private byte brojPoena = 0;
	
	//koristimo za kreiranje iz fajla
	public Musterija(String oznaka, String ime, String prezime, String JMBG, Pol pol, String adresa,
			String brojTelefona, String korisnickoIme, String lozinka, byte brojPoena, boolean obrisan) {
		super(oznaka, ime, prezime, JMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka, obrisan);
		setBrojPoena(brojPoena);
	}
	

	public Musterija(String ime, String prezime, String JMBG, Pol pol, String adresa,
			String brojTelefona, String korisnickoIme, String lozinka,  byte brojPoena, boolean obrisan) {
		super(ime, prezime, JMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka, obrisan);
		setBrojPoena(brojPoena);
	}



	public byte getBrojPoena() {
		return brojPoena;
	}

	public void setBrojPoena(byte brojPoena) {
		if (brojPoena > 10 || brojPoena < 0) {
			throw new IllegalArgumentException("Uneta vrednost poena biti <= 10 kao i >= 0");
		} else {
			this.brojPoena = brojPoena;
		}
	}
	
	public ArrayList<Automobil> getAutomobili() {
		return AutomobilController.nadjiAutomobilePoIdVlasnika(this.getOznaka());
	}


	
	
	
}
