package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import enumi.Pol;
import enumi.Specijalizacija;

public class Serviser extends Korisnik {
	
	private Specijalizacija specijalizacija;
	private double plata;

	public Serviser(String ime, String prezime, String JMBG, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka, Specijalizacija specijalizacija, double plata) {
		super(ime, prezime, JMBG, adresa, brojTelefona, korisnickoIme, lozinka);
		this.plata = plata;
		this.specijalizacija = randomSpecijalizacija(); //kapiram da ce ovde umesto random stvari biti nekakav drop meni za selektovanje
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
		return "Serviser [specijalizacija=" + specijalizacija + ", plata=" + plata + ", toString()=" + super.toString()
				+ "]";
	}

	
	
	

}
