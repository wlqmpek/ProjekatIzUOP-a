package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import controller.MusterijaController;
import enumi.Pol;


public abstract class Korisnik {
	
	private String oznaka;
	private String ime;
	private String prezime;
	private String JMBG;
	private Pol pol;
	private String adresa;
	private String brojTelefona;
	private String korisnickoIme;
	private String lozinka;
	private boolean obrisan;
	
	public Korisnik(String oznaka, String ime, String prezime, String JMBG, Pol pol, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka, boolean obrisan) {
		super();
		this.oznaka = oznaka;
		this.ime = ime;
		this.prezime = prezime;
		this.JMBG = JMBG;
		this.pol = pol;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.obrisan = obrisan;
	}

	public Korisnik(String ime, String prezime, String JMBG, Pol pol, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka) {
		super();
		this.oznaka = generisiOznaku();
		this.ime = ime;
		this.prezime = prezime;
		this.JMBG = JMBG;
		this.pol = pol;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.obrisan = false;
	}
	
	
	
	public Korisnik(String ime, String prezime, String jMBG, Pol pol, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka, boolean obrisan) {
		super();
		this.oznaka = this.generisiOznaku();
		this.ime = ime;
		this.prezime = prezime;
		JMBG = jMBG;
		this.pol = pol;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.obrisan = obrisan;
	}

	//privremeno, pseudo-random izabran pol iz enuma
	public Pol randomPol() {
		ArrayList<Pol> pol = new ArrayList<Pol>(Arrays.asList(Pol.values()));
		return pol.get(new Random().nextInt(2));
	}
	
	public String generisiOznaku() {
		return UUID.randomUUID().toString();
	}
	public String getOznaka() {
		return oznaka;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		if(ime == null) {
			throw new IllegalArgumentException("Ime ne sme biti null!");
		} else if (ime.isEmpty()) {
			throw new IllegalArgumentException("Ime ne sme biti prazno!");
		} else {
			this.ime = ime;
		}
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		if(prezime == null) {
			throw new IllegalArgumentException("Prezime ne sme biti null!");
		} else if (prezime.isEmpty()) {
			throw new IllegalArgumentException("Prezime ne sme biti prazno!");
		} else {
			this.prezime = prezime;
		}
	}
	
	public String getJMBG() {
		return JMBG;
	}
	
	public void setJMBG(String jMBG) {
		if(jMBG == null) {
			throw new IllegalArgumentException("JMBG ne sme biti null!");
		} else if (jMBG.isEmpty()) {
			throw new IllegalArgumentException("JMBG ne sme biti prazno!");
		} else {
			this.JMBG = jMBG;
		}
	}
	
	public Pol getPol() {
		return pol;
	}
	
	public void setPol(Pol pol) {
		this.pol = pol;
	}
	
	public String getAdresa() {
		return adresa;
	}
	
	public void setAdresa(String adresa) {
		if(adresa == null) {
			throw new IllegalArgumentException("Adresa ne sme biti null!");
		} else if (adresa.isEmpty()) {
			throw new IllegalArgumentException("Adresa ne sme biti prazno!");
		} else {
			this.adresa = adresa;
		}
	}
	
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		if(brojTelefona == null) {
			throw new IllegalArgumentException("Broj telefona ne sme biti null!");
		} else if (brojTelefona.isEmpty()) {
			throw new IllegalArgumentException("Broj telefona ne sme biti prazno!");
		} else {
			this.brojTelefona = brojTelefona;
		}
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		if(korisnickoIme == null) {
			throw new IllegalArgumentException("Korisnicko ime ne sme biti null!");
		} else if (korisnickoIme.isEmpty()) {
			throw new IllegalArgumentException("Korisnicko ime ne sme biti prazno!");
		} else {
			this.korisnickoIme = korisnickoIme;
		}
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public boolean isObrisan() {
		return obrisan;
	}
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "Korisnik [oznaka=" + oznaka + ", ime=" + ime + ", prezime=" + prezime + "]";
	}

	
	
	
}
