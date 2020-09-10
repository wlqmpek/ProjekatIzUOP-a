package model;

import enumi.Pol;

public class Administrator extends Korisnik{
	
	private double plata;
	
	public Administrator(String ime, String prezime, String JMBG, Pol pol, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka, double plata) {
		super(ime, prezime, JMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka);
		this.plata = plata;
	}
	
	
	//koristi se za kreiranje Objekta iz fajla
	
	public Administrator(String oznaka, String ime, String prezime, String JMBG, Pol pol, String adresa,
			String brojTelefona, String korisnickoIme, String lozinka, boolean obrisan, double plata) {
		super(oznaka, ime, prezime, JMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka, obrisan);
		this.plata = plata;
	}


	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		if (plata <= 0) {
			throw new IllegalArgumentException("Uneta vrednost plate mora biti veca od 0");
		} else {
			this.plata = plata;
		}
	}

	@Override
	public String toString() {
		return "Administrator [plata=" + plata + ", toString()=" + super.toString() + "]";
	}

	
}
