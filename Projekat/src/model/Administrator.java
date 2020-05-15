package model;

public class Administrator extends Korisnik{
	
	private double plata;
	
	public Administrator(String ime, String prezime, String JMBG, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka, double plata) {
		super(ime, prezime, JMBG, adresa, brojTelefona, korisnickoIme, lozinka);
		this.plata = plata;
	}
	
	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	@Override
	public String toString() {
		return "Administrator [plata=" + plata + ", toString()=" + super.toString() + "]";
	}

	
}
