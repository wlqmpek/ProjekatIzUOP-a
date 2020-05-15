package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import enumi.Pol;


abstract class Korisnik {
	
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
	
	public Korisnik(String ime, String prezime, String JMBG, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka) {
		
		super();
		this.oznaka = generisiOznaku();
		this.ime = ime;
		this.prezime = prezime;
		this.JMBG = JMBG;
		this.pol = randomPol();
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.obrisan = false;
	}
	
	//privremeno, pseudo-random izabran pol iz enuma
	public Pol randomPol() {
		ArrayList<Pol> pol = new ArrayList<Pol>(Arrays.asList(Pol.values()));
		return pol.get(new Random().nextInt(2));
	}
	
	public String generisiOznaku() {
		return UUID.randomUUID().toString();
	}
	public void setOznaka() {
		this.oznaka = oznaka;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getJMBG() {
		return JMBG;
	}
	public void setJMBG(String jMBG) {
		JMBG = jMBG;
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
		this.adresa = adresa;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
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
	
	
	
}