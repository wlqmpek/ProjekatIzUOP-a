package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import enumi.Pol;
import enumi.Specijalizacija;

public class Serviser extends Korisnik {
	
	private Specijalizacija specijalizacija;
	private double plata;

	public Serviser(String ime, String prezime, String JMBG, Pol pol, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka, Specijalizacija specijalizacija, double plata) {
		super(ime, prezime, JMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka, false);
		this.plata = plata;
		this.specijalizacija = specijalizacija;
	}
	
	//konstruktor za kreiranje objekta iz fajla
	public Serviser(String oznaka, String ime, String prezime, String JMBG, Pol pol, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka, boolean obrisan, Specijalizacija specijalizacija, double plata) {
		super(oznaka, ime, prezime, JMBG, pol, adresa, brojTelefona, korisnickoIme, lozinka, obrisan);
		this.specijalizacija = specijalizacija;
		this.plata = plata;
	}
	
	//privremeno resenje za prikazivanje enum-a kada se odradi gui bice neki drop meni
	public Specijalizacija randomSpecijalizacija() {
		ArrayList<Specijalizacija> specijalizacije = new ArrayList<Specijalizacija>(Arrays.asList(Specijalizacija.values()));
		return specijalizacije.get(new Random().nextInt(4));
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

	@Override
	public String toString() {
		return "Serviser [Oznaka=" + getOznaka() + ", Ime=" + getIme() + ", Prezime=" + getPrezime()
				+ "]";
	}	
	
	

}
