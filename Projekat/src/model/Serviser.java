package model;

public class Serviser extends Korisnik {
	
	private Specijalizacija specijalizacija;
	private double plata;

	public Serviser(String ime, String prezime, String jMBG, Pol pol, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka, Specijalizacija specijalizacija, double plata) {
		super(ime, prezime, jMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka);
		this.plata = plata;
		this.specijalizacija = specijalizacija;
		
	}

	public Specijalizacija getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(Specijalizacija specijalizacija) {
		this.specijalizacija = specijalizacija;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}
	
	

}
