package model;



public class Musterija extends Korisnik {
	
	
	private byte brojPoena = 0;
	
	public Musterija(String ime, String prezime, String jMBG, Pol pol, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka, byte brojPoena) {
		super(ime, prezime, jMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka);
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
	
	
	
}
