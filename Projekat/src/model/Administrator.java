package model;

public class Administrator extends Korisnik{
	
	private double plata;
	
	public Administrator(String ime, String prezime, String jMBG, Pol pol, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka, double plata) {
		super(ime, prezime, jMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka);
		this.plata = plata;
	}

	
	
	
	
}
