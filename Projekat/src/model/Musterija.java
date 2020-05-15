package model;

import enumi.Pol;

public class Musterija extends Korisnik {
	
	private byte brojPoena = 0;

	public Musterija(String oznaka, String ime, String prezime, String JMBG, Pol pol, String adresa,
			String brojTelefona, String korisnickoIme, String lozinka, byte brojPoena, boolean obrisan) {
		super(oznaka, ime, prezime, JMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka, obrisan);
		this.brojPoena = brojPoena;
	}
	
	

	public Musterija(String ime, String prezime, String JMBG, String adresa, String brojTelefona, String korisnickoIme,
			String lozinka, byte brojPoena) {
		super(ime, prezime, JMBG, adresa, brojTelefona, korisnickoIme, lozinka);
		this.brojPoena = brojPoena;
	}



	public byte getBrojPoena() {
		return brojPoena;
	}

	public void setBrojPoena(byte brojPoena) {
		if (brojPoena > 10) {
			throw new IllegalArgumentException("Uneta vrednost mora biti <= 10");
		}
		
	}

	@Override
	public String toString() {
		return "Musterija [brojPoena=" + brojPoena + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
